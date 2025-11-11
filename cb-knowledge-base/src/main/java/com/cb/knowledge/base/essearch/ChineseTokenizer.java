package com.cb.knowledge.base.essearch;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 17:35
 * @Copyright (c) 2025
 * @Description 中文分词器
 */
public class ChineseTokenizer {
    private static final Pool<IKSegmenter> segmenterPool = new Pool<>(5); // 对象池

    public static List<String> tokenize(String text) {
        IKSegmenter segmenter = null;
        try {
            segmenter = segmenterPool.borrowObject();
            return doTokenize(text, segmenter);
        } catch (Exception e) {
            throw new RuntimeException("分词失败", e);
        } finally {
            if (segmenter != null) segmenterPool.returnObject(segmenter);
        }
    }

    private static List<String> doTokenize(String text, IKSegmenter segmenter) throws IOException {
        List<String> tokens = new ArrayList<>();
        try (StringReader reader = new StringReader(text)) {
            segmenter.reset(reader);
            Lexeme lexeme;
            while ((lexeme = segmenter.next()) != null) {
                String word = lexeme.getLexemeText().trim();
                if (!word.isEmpty() && word.length() < 20) { // 过滤过长词
                    tokens.add(word.toLowerCase());
                }
            }
        }
        return tokens;
    }

    // 简单对象池实现
    private static class Pool<T> {
        private final Queue<T> pool = new LinkedList<>();

        public Pool(int size) {
            for (int i = 0; i < size; i++) {
                pool.add(createSegmenter());
            }
        }

        public synchronized T borrowObject() {
            return pool.isEmpty() ? createSegmenter() : pool.poll();
        }

        public synchronized void returnObject(T obj) {
            pool.offer(obj);
        }

        @SuppressWarnings("unchecked")
        private T createSegmenter() {
            return (T) new IKSegmenter(new StringReader(""), true);
        }
    }
}
