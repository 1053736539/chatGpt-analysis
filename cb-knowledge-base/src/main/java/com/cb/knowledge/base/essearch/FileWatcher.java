package com.cb.knowledge.base.essearch;

import com.cb.knowledge.base.domain.DataCostant;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 22:41
 * @Copyright (c) 2025
 * @Description 增量文件监控
 */
public class FileWatcher implements Runnable {
    private final WatchService watchService;
    private final Map<WatchKey, Path> keys = new HashMap<>();
    private final ExecutorService executor;
    private final IndexManager indexManager;
    private volatile boolean running = true;
    // 添加目录缓存过期机制
    private final Map<Path, Long> dirCache = new ConcurrentHashMap<>();
    private static final long CACHE_EXPIRE_MS = 60_000; // 1分钟

    public FileWatcher(String dirPath, int threadPoolSize, IndexManager indexManager) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
        this.indexManager = indexManager;
        Path rootPath = Paths.get(dirPath);

        // 初始注册所有子目录
        registerAll(rootPath);
    }

    // 递归注册目录及其子目录
    private void registerAll(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (Files.isSymbolicLink(dir)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                register(dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (!Files.isDirectory(file)) {
                    executor.submit(() -> processFile(file.toFile()));
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("无法访问文件: " + file);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    // 注册单个目录
    private void register(Path dir) throws IOException {
        try {
            if (Files.isReadable(dir)) {
                WatchKey key = dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                keys.put(key, dir);
            }
        } catch (AccessDeniedException e) {
            System.err.println("无权限访问目录: " + dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (running) {
                WatchKey key = watchService.take();
                Path dir = keys.get(key);

                for (WatchEvent<?> event : key.pollEvents()) {
                    Path name;
                    name = ((WatchEvent<Path>)event).context();
                    Path child = dir.resolve(name);

                    // 处理新建目录
                    if (event.kind() == ENTRY_CREATE && Files.isDirectory(child)) {
                        registerAll(child);
                    }

                    // 处理文件事件
                    if (Files.isRegularFile(child)) {
                        executor.submit(() -> processFile(child.toFile()));
                    }
                }
                key.reset();
            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        running = false;
        executor.shutdown();
    }

    private void processFile(File file) {
        if (file.getPath().endsWith("/.DS_Store")) {
            return;
        }
        if (!shouldProcess(file.toPath())) {
            return;
        }
        try (Connection conn = DriverManager.getConnection(DataCostant.SQLITE_DB)) {
            PreparedStatement ps = conn.prepareStatement(
            "SELECT doc_id, last_modified FROM documents WHERE path = ?"
            );
            ps.setString(1, file.getAbsolutePath());
            ResultSet rs = ps.executeQuery();

            long lastModified = file.lastModified();
            if (rs.next()) {
                // 更新逻辑
                if (rs.getLong("last_modified") == lastModified) return;
                int docId = rs.getInt("doc_id");

                // 重新索引文档
                String content = DocumentParser.parse(file);
                List<String> terms = ChineseTokenizer.tokenize(content);
                indexManager.addDocument(docId, terms); // 调用核心方法

                // 更新元数据
                PreparedStatement updateStmt = conn.prepareStatement(
                "UPDATE documents SET last_modified = ? WHERE doc_id = ?");
                updateStmt.setLong(1, lastModified);
                updateStmt.setInt(2, docId);
                updateStmt.executeUpdate();
            } else {
                // 新增文档
                indexManager.indexDocument(file); // 调用文件索引方法
            }
        } catch (Exception e) {
            System.err.println("处理文件失败: " + file.getName());
            e.printStackTrace();
        }
    }

    private boolean shouldProcess(Path path) {
        long now = System.currentTimeMillis();
        return dirCache.compute(path, (k, v) -> {
            if (v == null || now - v > CACHE_EXPIRE_MS) {
                return now;
            }
            return v; // 未过期则跳过处理
        }) == now;
    }
}
