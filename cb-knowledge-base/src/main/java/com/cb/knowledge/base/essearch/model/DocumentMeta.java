package com.cb.knowledge.base.essearch.model;

import lombok.Getter;

import java.nio.file.Path;
import java.time.Instant;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/17 9:50
 * @Copyright (c) 2025
 * @Description 文档元数据
 */
@Getter
public class DocumentMeta {
    private final Path filePath;
    private final Instant lastIndexed;
    private final int docId;

    public DocumentMeta(Path filePath, Instant lastIndexed, int docId) {
        this.filePath = filePath;
        this.lastIndexed = lastIndexed;
        this.docId = docId;
    }
}
