package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 干部档案附件信息对象 RS_DESCRIPT
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public class RsDescript extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
//    @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC, prompt = "档案序号")
    private Integer id;

    /** 干部档案目录ID */
    @Excel(name = "干部档案目录ID")
    private Integer archId;

    /** 长度 */
    @Excel(name = "长度")
    private Integer length;

    /** 路径 */
    @Excel(name = "路径")
    private String path;

    /** pdf-key值 */
    @Excel(name = "pdf-key值")
    private String pdfKey;

    /** 序号 */
    @Excel(name = "序号")
    private Integer serialMumber;

    /** 原文件名 */
    @Excel(name = "原文件名")
    private String oldFileName;

    /** 新文件名 */
    @Excel(name = "新文件名")
    private String newFileName;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 高清图片长度 */
    @Excel(name = "高清图片长度")
    private Integer gaoqingLeangth;

    /** 高清pdf-key */
    @Excel(name = "高清pdf-key")
    private String gaoqingPdfKey;


    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setArchId(Integer archId) 
    {
        this.archId = archId;
    }

    public Integer getArchId() 
    {
        return archId;
    }
    public void setLength(Integer length) 
    {
        this.length = length;
    }

    public Integer getLength() 
    {
        return length;
    }
    public void setPdfKey(String pdfKey) 
    {
        this.pdfKey = pdfKey;
    }

    public String getPdfKey() 
    {
        return pdfKey;
    }
    public void setSerialMumber(Integer serialMumber) 
    {
        this.serialMumber = serialMumber;
    }

    public Integer getSerialMumber() 
    {
        return serialMumber;
    }
    public void setOldFileName(String oldFileName) 
    {
        this.oldFileName = oldFileName;
    }

    public String getOldFileName() 
    {
        return oldFileName;
    }
    public void setNewFileName(String newFileName) 
    {
        this.newFileName = newFileName;
    }

    public String getNewFileName() 
    {
        return newFileName;
    }
    public void setGaoqingLeangth(Integer gaoqingLeangth) 
    {
        this.gaoqingLeangth = gaoqingLeangth;
    }

    public Integer getGaoqingLeangth() 
    {
        return gaoqingLeangth;
    }
    public void setGaoqingPdfKey(String gaoqingPdfKey) 
    {
        this.gaoqingPdfKey = gaoqingPdfKey;
    }

    public String getGaoqingPdfKey() 
    {
        return gaoqingPdfKey;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("archId", getArchId())
            .append("length", getLength())
            .append("pdfKey", getPdfKey())
            .append("serialMumber", getSerialMumber())
            .append("oldFileName", getOldFileName())
            .append("newFileName", getNewFileName())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("gaoqingLeangth", getGaoqingLeangth())
            .append("gaoqingPdfKey", getGaoqingPdfKey())
            .append("path", getPath())
            .toString();
    }
}
