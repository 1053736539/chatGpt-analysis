package com.cb.knowledge.base.essearch;

import cn.hutool.cache.impl.LRUCache;
import com.cb.knowledge.base.domain.DataCostant;
import com.cb.knowledge.base.essearch.model.IndexBlock;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 19:36
 * @Copyright (c) 2025
 * @Description 索引存储管理器
 */
public class IndexManager {
    private static final String INDEX_DIR = "index/";
    private final Map<String, BitSet> invertedIndex = new HashMap<>();
    private final LRUCache<String, IndexBlock> cache;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final AtomicInteger docIdCounter = new AtomicInteger(DataCostant.INDEX_ID);

    public IndexManager(int cacheSize) {
        this.cache = new LRUCache<>(cacheSize);
        initIndexDir();
    }

    /**
     * 索引文件并返回分配的文档ID
     */
    public int indexDocument(File file) throws Exception {
        String content = DocumentParser.parse(file);
        int docId = docIdCounter.getAndIncrement();

        List<String> terms = ChineseTokenizer.tokenize(content);
        addDocument(docId, terms); // 调用核心添加方法

        // 记录元数据
        try (Connection conn = DriverManager.getConnection(DataCostant.SQLITE_DB)) {
            PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO documents(path, last_modified, doc_id) VALUES(?,?,?)");
            ps.setString(1, file.getAbsolutePath());
            ps.setLong(2, file.lastModified());
            ps.setInt(3, docId);
            ps.executeUpdate();
        }
        return docId;
    }

    public void addDocument(int docId, List<String> terms) throws IOException {
        lock.writeLock().lock();
        try {
            Map<String, List<Integer>> blockMap = new HashMap<>();
            for (String term : terms) {
                String blockKey = getBlockKey(term);
                blockMap.computeIfAbsent(blockKey, k -> new ArrayList<>())
                        .add(docId);
            }

            for (Map.Entry<String, List<Integer>> entry : blockMap.entrySet()) {
                updateBlockFile(entry.getKey(), entry.getValue());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public synchronized void updateDocument(int docId, File file) throws Exception {
        String content = DocumentParser.parse(file);
        List<String> terms = ChineseTokenizer.tokenize(content);

        // 先移除旧索引
        invertedIndex.values().forEach(bs -> bs.clear(docId));

        // 添加新索引
        for (String term : terms) {
            String blockKey = getBlockKey(term);
            BitSet bs = invertedIndex.computeIfAbsent(blockKey, k -> new BitSet());
            bs.set(docId);
            // 更新磁盘存储
            persistBlock(blockKey);
        }
    }

    public BitSet search(String term) throws IOException {
        lock.readLock().lock();
        try {
            String blockKey = getBlockKey(term);
            IndexBlock block = cache.get(blockKey);
            if (block == null) {
                block = loadBlockFromDisk(blockKey);
                cache.put(blockKey, block);
            }
            return decodeToBitSet(block.getDocIds());
        } finally {
            lock.readLock().unlock();
        }
    }

    private void updateBlockFile(String blockKey, List<Integer> docIds) throws IOException {
        Path path = Paths.get(INDEX_DIR, blockKey + ".blk");
        byte[] data = DeltaCompression.encode(docIds);

        Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private IndexBlock loadBlockFromDisk(String blockKey) throws IOException {
        Path path = Paths.get(INDEX_DIR, blockKey + ".blk");
        if (!Files.exists(path)) {
            return new IndexBlock(blockKey);
        }

        byte[] data = Files.readAllBytes(path);
        List<Integer> docIds = DeltaCompression.decode(data);
        return new IndexBlock(blockKey, docIds);
    }

    private String getBlockKey(String term) {
        if (term.isEmpty()) {
            return "default";
        }
        char firstChar = term.charAt(0);
        return Character.isLetter(firstChar)? String.valueOf(firstChar).toLowerCase() : "other";
    }

    private void initIndexDir() {
        try {
            Files.createDirectories(Paths.get(INDEX_DIR));
        } catch (IOException e) {
            throw new RuntimeException("无法创建索引目录", e);
        }
    }

    private BitSet decodeToBitSet(List<Integer> docIds) {
        BitSet bitSet = new BitSet();
        if (docIds == null || docIds.isEmpty()) {
            return bitSet;
        }

        int maxDocId = Collections.max(docIds);
        bitSet.set(0, maxDocId + 1, false); // 预分配空间

        for (int docId : docIds) {
            bitSet.set(docId);
        }
        return bitSet;
    }

    private void persistBlock(String blockKey) throws IOException {
        BitSet bitSet = invertedIndex.get(blockKey);
        if (bitSet == null) return;

        List<Integer> docIds = new ArrayList<>();
        for (int i = bitSet.nextSetBit(0); i != -1; i = bitSet.nextSetBit(i+1)) {
            docIds.add(i);
        }

        Path blockPath = Paths.get(INDEX_DIR, blockKey + ".blk");
        byte[] data = DeltaCompression.encode(docIds);
        Files.write(blockPath, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // 系统启动时加载已有索引
    public void loadExistingIndex() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(INDEX_DIR), "*.blk")) {
            for (Path blockPath : stream) {
                String blockKey = blockPath.getFileName().toString().replace(".blk", "");
                byte[] data = Files.readAllBytes(blockPath);
                List<Integer> docIds = DeltaCompression.decode(data);
                BitSet bitSet = decodeToBitSet(docIds);
                invertedIndex.put(blockKey, bitSet);
            }
        }
    }

    public void warmUp() {
        try (Connection conn = DriverManager.getConnection(DataCostant.SQLITE_DB)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM documents");
            if (rs.next() && rs.getInt(1) > 0) {
                // 预加载常用词项
                loadBlock("a");
                loadBlock("b");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBlock(String blockKey) {
        try {
            IndexBlock block = loadBlockFromDisk(blockKey);
            cache.put(blockKey, block);
        } catch (IOException e) {
            System.err.println("预热加载失败: " + blockKey);
        }
    }
}
