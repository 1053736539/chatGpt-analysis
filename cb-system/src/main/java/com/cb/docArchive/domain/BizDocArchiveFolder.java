package com.cb.docArchive.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 文书档案文件夹目录对象 biz_doc_archive_folder
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
public class BizDocArchiveFolder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文书档案 文件夹id */
    private String folderId;

    /** 文件夹名称 */
    @Excel(name = "文件夹名称")
    private String folderName;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 删除标记 1-正常 2-删除 */
    private String delFlag;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private String parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;

    /** 子部门 */
    private List<?> children = new ArrayList<>();

    /** 以下为扩展字段 **/

    /** 创建人名称 */
    @Transient
    private String createByName;

    public void setFolderId(String folderId) 
    {
        this.folderId = folderId;
    }

    public String getFolderId() 
    {
        return folderId;
    }
    public void setFolderName(String folderName) 
    {
        this.folderName = folderName;
    }

    public String getFolderName() 
    {
        return folderName;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("folderId", getFolderId())
            .append("folderName", getFolderName())
            .append("parentId", getParentId())
            .append("sort", getSort())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
