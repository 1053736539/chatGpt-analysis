package com.cb.rpa.domain;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 昆明市纪监委网站对象 rpa_kmjjw_website
 *
 * @author ouyang
 * @date 2024-10-22
 */
@Document(indexName = "rpa_kmjjw_website")
public class RpaKmjjwWebsite extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @Id
    private String id;

    /**
     * 标题
     */
    @Excel(name = "标题")
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String title;

    /**
     * 标签
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String labels;

    /**
     * 内容
     */
    @Excel(name = "内容")
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String content;

    /**
     * 来源于
     */
    @Excel(name = "来源于")
    private String originate;

    /**
     * 编辑
     */
    @Excel(name = "编辑")
    private String editor;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    /**
     * 网页地址
     */
    @Excel(name = "网页地址")
    private String linkUrl;

    /**
     * 是否推送至AI知识库 0 否 1是
     */
    @Excel(name = "是否推送至AI知识库 0 否 1是")
    private String pushAiKnowledge;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setOriginate(String originate) {
        this.originate = originate;
    }

    public String getOriginate() {
        return originate;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEditor() {
        return editor;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getPushAiKnowledge() {
        return pushAiKnowledge;
    }

    public void setPushAiKnowledge(String pushAiKnowledge) {
        this.pushAiKnowledge = pushAiKnowledge;
    }
}
