package com.cb.filemanage.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务文件对象 biz_attach
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public class BizAttach extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 附件ID */
    private String attachId;

    /**
     * 文件夹ID
     */
    private String folderId;

    /** 原文件名 */
    @Excel(name = "原文件名")
    private String oldName;

    /** 新文件名 */
    @Excel(name = "新文件名")
    private String newName;

    /** 文件后缀 */
    @Excel(name = "文件后缀")
    private String extName;

    /** 存储路径 */
    @Excel(name = "存储路径")
    private String path;
    /**
     * 密级
     */
    @Excel(name = "密级")
    private Integer secret;
    /** 所属模块 */
    @Excel(name = "所属模块")
    private Integer modules;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 文件版本 */
    @Excel(name = "文件版本")
    private String version;

    /** 是否删除 1 正常 2 删除 */
    private Integer delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAttachId(String attachId) 
    {
        this.attachId = attachId;
    }

    public String getAttachId() 
    {
        return attachId;
    }
    public void setOldName(String oldName) 
    {
        this.oldName = oldName;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getOldName()
    {
        return oldName;
    }
    public void setNewName(String newName) 
    {
        this.newName = newName;
    }

    public String getNewName() 
    {
        return newName;
    }
    public void setExtName(String extName) 
    {
        this.extName = extName;
    }

    public String getExtName() 
    {
        return extName;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setModules(Integer modules) 
    {
        this.modules = modules;
    }

    public Integer getSecret() {
        return secret;
    }

    public void setSecret(Integer secret) {
        this.secret = secret;
    }

    public Integer getModules()
    {
        return modules;
    }
    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("attachId", getAttachId())
            .append("oldName", getOldName())
            .append("newName", getNewName())
            .append("extName", getExtName())
            .append("path", getPath())
            .append("modules", getModules())
            .append("fileSize", getFileSize())
            .append("version", getVersion())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
