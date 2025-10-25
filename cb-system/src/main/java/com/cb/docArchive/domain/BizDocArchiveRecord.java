package com.cb.docArchive.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * 文书档案记录对象 biz_doc_archive_record
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
public class BizDocArchiveRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 档号 */
    private String archiveNo;

    /** 全宗号 */
    @Excel(name = "全宗号")
    private String fondsNo;

    /** 档案门类代码 */
    @Excel(name = "档案门类代码")
    private String archiveKindCode;

    /** 归档年度 */
    @Excel(name = "归档年度")
    private String archiveYear;

    /** 保管期限 */
    @Excel(name = "保管期限")
    private String retentionPeriod;

    /** 机构（问题） */
    @Excel(name = "机构", readConverterExp = "问=题")
    private String organizationProblem;

    /** 件号 */
    @Excel(name = "件号")
    private String partNo;

    /** 责任者 */
    @Excel(name = "责任者")
    private String responsibility;

    /** 题名 */
    @Excel(name = "题名")
    private String title;

    /** 文件编号 */
    @Excel(name = "文号")
    private String fileNo;

    /** 文件时间 */
    @Excel(name = "文件时间")
    private String fileDate;

    /** 页数 */
    @Excel(name = "页数")
    private Integer filePage;

    /** 密级 */
    @Excel(name = "密级")
    private String secretLevel;

    /** 附件id */
    @Excel(name = "附件id")
    private String attachId;

    /** 文件夹名称 */
    @Excel(name = "文件夹名称")
    private String folderId;

    /** 以下为扩展字段 **/
    @Transient
    private String createByName;//创建人名称
    @Transient
    private String attachName;
    @Transient
    private List<String> folderIds;// 查询条件-所属目录ids

    public void setArchiveNo(String archiveNo) 
    {
        this.archiveNo = archiveNo;
    }

    public String getArchiveNo() 
    {
        return archiveNo;
    }
    public void setFondsNo(String fondsNo) 
    {
        this.fondsNo = fondsNo;
    }

    public String getFondsNo() 
    {
        return fondsNo;
    }
    public void setArchiveKindCode(String archiveKindCode) 
    {
        this.archiveKindCode = archiveKindCode;
    }

    public String getArchiveKindCode() 
    {
        return archiveKindCode;
    }
    public void setArchiveYear(String archiveYear) 
    {
        this.archiveYear = archiveYear;
    }

    public String getArchiveYear() 
    {
        return archiveYear;
    }
    public void setRetentionPeriod(String retentionPeriod) 
    {
        this.retentionPeriod = retentionPeriod;
    }

    public String getRetentionPeriod() 
    {
        return retentionPeriod;
    }
    public void setOrganizationProblem(String organizationProblem) 
    {
        this.organizationProblem = organizationProblem;
    }

    public String getOrganizationProblem() 
    {
        return organizationProblem;
    }
    public void setPartNo(String partNo) 
    {
        this.partNo = partNo;
    }

    public String getPartNo() 
    {
        return partNo;
    }
    public void setResponsibility(String responsibility) 
    {
        this.responsibility = responsibility;
    }

    public String getResponsibility() 
    {
        return responsibility;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setFileNo(String fileNo) 
    {
        this.fileNo = fileNo;
    }

    public String getFileNo() 
    {
        return fileNo;
    }
    public void setFileDate(String fileDate) 
    {
        this.fileDate = fileDate;
    }

    public String getFileDate() 
    {
        return fileDate;
    }
    public void setFilePage(Integer filePage) 
    {
        this.filePage = filePage;
    }

    public Integer getFilePage() 
    {
        return filePage;
    }
    public void setSecretLevel(String secretLevel) 
    {
        this.secretLevel = secretLevel;
    }

    public String getSecretLevel() 
    {
        return secretLevel;
    }
    public void setAttachId(String attachId) 
    {
        this.attachId = attachId;
    }

    public String getAttachId() 
    {
        return attachId;
    }
    public void setFolderId(String folderId) 
    {
        this.folderId = folderId;
    }

    public String getFolderId() 
    {
        return folderId;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public List<String> getFolderIds() {
        return folderIds;
    }

    public void setFolderIds(List<String> folderIds) {
        this.folderIds = folderIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("archiveNo", getArchiveNo())
            .append("fondsNo", getFondsNo())
            .append("archiveKindCode", getArchiveKindCode())
            .append("archiveYear", getArchiveYear())
            .append("retentionPeriod", getRetentionPeriod())
            .append("organizationProblem", getOrganizationProblem())
            .append("partNo", getPartNo())
            .append("responsibility", getResponsibility())
            .append("title", getTitle())
            .append("fileNo", getFileNo())
            .append("fileDate", getFileDate())
            .append("filePage", getFilePage())
            .append("secretLevel", getSecretLevel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("attachId", getAttachId())
            .append("folderId", getFolderId())
            .toString();
    }
}
