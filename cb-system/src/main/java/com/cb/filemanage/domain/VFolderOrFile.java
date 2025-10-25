package com.cb.filemanage.domain;

import com.cb.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 文件夹和视图对象 v_folder_or_file
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public class VFolderOrFile
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String parentId;

    private String name;

    private Long type;
    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 附件地址 type = 1 是有数据
     */
    private String fileUrl;
    /**
     * 文件大小
     */
    private Double size;

    private String extName;

    private Long folderNum;

    private Long fileNum;

    private Integer secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public Long getFolderNum() {
        return folderNum;
    }

    public void setFolderNum(Long folderNum) {
        this.folderNum = folderNum;
    }

    public Long getFileNum() {
        return fileNum;
    }

    public void setFileNum(Long fileNum) {
        this.fileNum = fileNum;
    }

    public Integer getSecret() {
        return secret;
    }

    public void setSecret(Integer secret) {
        this.secret = secret;
    }
}
