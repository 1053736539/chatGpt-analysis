package com.cb.exportSql.domain;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/4/25 17:55
 * @Copyright (c) 2025
 * @Description 含有sql内容的文件类
 */
public class SqlFile {
    /** 文件名 **/
    private StringJoiner fileName;

    /** 文件内容 **/
    private List<String> fileContent;

    /** 文件下标 **/
    @Getter
    @Setter
    private int fileIndex;

    /** 是否为sql数据 **/
    @Getter
    @Setter
    private boolean isSql;

    /** 表名 **/
    @Getter
    @Setter
    private String tableName;

    public SqlFile() {
        this.fileName = new StringJoiner("-");
        this.fileContent = new ArrayList<>(32);
    }

    public String getFileName() {
        return fileName.toString();
    }

    public void setFileName(String... fileNames) {
        if (fileNames != null) {
            for (String fileName : fileNames) {
                this.fileName.add(fileName);
            }
        } else {
            this.fileName.add(System.currentTimeMillis() + "");
        }

    }

    public String getFileContent() {
        StringBuilder builder = new StringBuilder("^");
        for (String content : fileContent) {
            builder.append(content);
        }
        builder.append("$");
        return builder.toString();
    }

    public List<String> getFileContentList() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        if (StrUtil.isNotBlank(fileContent)) {
            this.fileContent.add(fileContent);
        }
    }
}
