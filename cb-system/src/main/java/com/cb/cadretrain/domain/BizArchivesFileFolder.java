package com.cb.cadretrain.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.cb.filemanage.domain.BizFileFolder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 干部政治素质档案文件夹表对象 biz_archives_file_folder
 * 
 * @author yangcd
 * @date 2023-11-07
 */
public class BizArchivesFileFolder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 文件夹ID */
    private String folderId;

    /** 上级文件夹ID */
    @Excel(name = "上级文件夹ID")
    private String folderParentId;

    /** 文件夹名称 */
    @Excel(name = "文件夹名称")
    private String folderName;

    /** 删除标记 1正常 2删除 */
    private Integer delFlag;

    private List<BizArchivesFileFolder> children = new ArrayList<>();

    public List<BizArchivesFileFolder> getChildren() {
        return children;
    }

    public void setChildren(List<BizArchivesFileFolder> children) {
        this.children = children;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setFolderId(String folderId) 
    {
        this.folderId = folderId;
    }

    public String getFolderId() 
    {
        return folderId;
    }
    public void setFolderParentId(String folderParentId) 
    {
        this.folderParentId = folderParentId;
    }

    public String getFolderParentId() 
    {
        return folderParentId;
    }
    public void setFolderName(String folderName) 
    {
        this.folderName = folderName;
    }

    public String getFolderName() 
    {
        return folderName;
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
            .append("sort", getSort())
            .append("folderId", getFolderId())
            .append("folderParentId", getFolderParentId())
            .append("folderName", getFolderName())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }


}
