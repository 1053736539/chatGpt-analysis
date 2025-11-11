package com.cb.knowledge.base.domain.entity;

import com.cb.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 20:16
 * @Copyright (c) 2025
 * @Description  知识库实体
 */
@Getter
@Setter
public class KnowledgeBase extends BaseEntity {
    private static final long serialVersionUID = 110L;

    /** 主键id */
    private Integer id;

    /** 文件名称 */
    private String fileName;

    /** 文件类型 */
    private String fileType;

    /** 文件级别 */
    private String fileLevel;

    /** 文件简述 */
    private String fileDesc;
    
    /** 文件标签 **/
    private String fileTags;

    /** 文件路径 */
    private String filePath;

    /** 文件大小 */
    private Integer fileSize;

    /** 文件后缀 */
    private String fileSuffix;

    /** 是否删除 **/
    private String delFlag = "0";

    /** 平板开发要求增加原文文本 */
    private String fileContent;

    /** 平板开发要求增加原文分词文本 */
    private String fileTokenizer;

    /** 搜索id列表 */
    @Transient
    private List<Integer> ids;

    /** 文件级别名称 **/
    @Transient
    private String fileLevelName;

    /** 文件类型名称 **/
    @Transient
    private String fileTypeName;

}
