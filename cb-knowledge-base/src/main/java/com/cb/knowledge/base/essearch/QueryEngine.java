package com.cb.knowledge.base.essearch;

import cn.hutool.cache.impl.LRUCache;
import com.cb.knowledge.base.domain.DataCostant;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/18 23:52
 * @Copyright (c) 2025
 * @Description 查询引擎
 */
public class QueryEngine {
    private static volatile QueryEngine instance;
    private final IndexManager indexManager;
    private final LRUCache<String, BitSet> queryCache = new LRUCache<>(100);

    private QueryEngine(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    public static void setManager(IndexManager indexManager) {
        if (instance == null) {
            synchronized (QueryEngine.class) {
                if (instance == null) {
                    instance = new QueryEngine(indexManager);
                }
            }
        }
    }

    public static QueryEngine getInstance() {
        return instance;
    }

    public List<String> search(String query, boolean isAnd) {
        List<String> terms = ChineseTokenizer.tokenize(query);
        if (terms.isEmpty()) {
            return Collections.emptyList();
        }

        String cacheKey = terms + "|" + isAnd;
        BitSet result = queryCache.get(cacheKey);
        if (result != null) {
            return loadDocuments(result);
        }

        try {
            result = isAnd ? executeAndQuery(terms) : executeOrQuery(terms);
            queryCache.put(cacheKey, result);
            return loadDocuments(result);
        } catch (IOException e) {
            throw new RuntimeException("查询失败", e);
        }
    }

    private BitSet executeAndQuery(List<String> terms) throws IOException {
        BitSet result = null;
        for (String term : terms) {
            BitSet bits = indexManager.search(term);
            if (bits == null || bits.isEmpty()) {
                return new BitSet();
            }

            if (result == null) {
                result = (BitSet) bits.clone();
            } else {
                result.and(bits);
                if (result.isEmpty()) break;
            }
        }
        return result != null ? result : new BitSet();
    }

    private BitSet executeOrQuery(List<String> terms) throws IOException {
        BitSet result = new BitSet();
        for (String term : terms) {
            BitSet bits = indexManager.search(term);
            result.or(bits);
        }
        return result;
    }

    private List<String> loadDocuments(BitSet docIds) {
        List<String> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DataCostant.SQLITE_DB)) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT path FROM documents WHERE doc_id = ?");

            for (int i = docIds.nextSetBit(0); i != -1; i = docIds.nextSetBit(i+1)) {
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    results.add(rs.getString("path"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("加载文档失败", e);
        }
        return results;
    }
}
