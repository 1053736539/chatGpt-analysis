package com.cb.knowledge.base.essearch.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/17 8:49
 * @Copyright (c) 2025
 * @Description 索引存储块
 */
@Getter
public class IndexBlock {
    /** 索引块的键 */
    private final String blockKey;

    /** 文档ID列表 */
    private final List<Integer> docIds;

    public IndexBlock(String blockKey) {
        this(blockKey, new ArrayList<>());
    }

    public IndexBlock(String blockKey, List<Integer> docIds) {
        this.blockKey = blockKey;
        this.docIds = docIds;
    }

    public List<Integer> getDocIds() {
        return Collections.unmodifiableList(docIds);
    }

}
