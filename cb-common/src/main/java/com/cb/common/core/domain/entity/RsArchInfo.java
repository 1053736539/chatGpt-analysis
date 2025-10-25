package com.cb.common.core.domain.entity;

import java.util.Date;

import com.cb.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 干部档案目录对象 RS_ARCH_INFO
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
public class RsArchInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
//    @Excel (name = "序号", cellType = Excel.ColumnType.NUMERIC, prompt = "档案序号")
    private Integer id;

    /** 原主键ID */
    @Excel(name = "原主键ID", type = Excel.Type.IMPORT, prompt = "档案目录表主键")
    private Integer archId;

    /** 用户ID */
    @Excel(name = "用户ID", type = Excel.Type.IMPORT, prompt = "档案系统用户编号")
    private Integer rsId;


    /** 备注 */
    @Excel(name = "BZ")
    private String remark;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 材料日期CLRQ */
    @Excel(name = "CLRQ", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.IMPORT )
    private Date materialTime;

    /** 材料条目 */
    @Excel(name = "材料条目")
    private String materialName;

    /** 材料分类 */
    @Excel(name = "材料分类",  type = Excel.Type.IMPORT)
    private Integer materialType;

    /** 份数 */
    @Excel(name = "份数",type = Excel.Type.IMPORT)
    private Integer fs;

    /** 顺序号 */
    @Excel(name = "顺序号", type = Excel.Type.IMPORT)
    private Integer serialNumber;

    /** 页数 */
    @Excel(name = "页数",type = Excel.Type.IMPORT)
    private Integer pageNumber;

    /** 材料形成年 */
    @Excel(name = "材料形成年", type = Excel.Type.IMPORT, prompt = "年")
    private Integer materialYear;

    /** 材料形成月 */
    @Excel(name = "材料形成月", type = Excel.Type.IMPORT, prompt = "月")
    private Integer materialMonth;

    /** 材料形成日 */
    @Excel(name = "材料形成日", type = Excel.Type.IMPORT, prompt = "日")
    private Integer materialDay;

    /** 查阅ID */
    @Excel(name = "查阅ID", type = Excel.Type.IMPORT)
    private Integer checkId;

    /** 检查处理 */
    @Excel(name = "检查处理")
    private String checkField;

    /** 审查处理 */
    @Excel(name = "审查处理")
    private String examineFileld;

    /** 环境条件 */
    @Excel(name = "环境条件")
    private String condition;

    /** 原目录ID */
    @Excel(name = "原目录ID")
    private String oldArchid;

    /** 原用户ID */
    @Excel(name = "原用户ID")
    private String oldRsid;

    /** 卷书材料ID */
    @Excel(name = "卷书材料ID")
    private String jsclId;

    /** 添加日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加日期", width = 30, dateFormat = "yyyy-MM-dd",type = Excel.Type.IMPORT )
    private Date addTime;

    /**  关联用户表ID */
    private Long userId;

    private  Integer beginYear;

    private  Integer endYear;

    public Integer getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    public void setRsId(Integer rsId) 
    {
        this.rsId = rsId;
    }

    public Integer getRsId() 
    {
        return rsId;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialType(Integer materialType) 
    {
        this.materialType = materialType;
    }

    public Integer getMaterialType() 
    {
        return materialType;
    }
    public void setSerialNumber(Integer serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public Integer getSerialNumber() 
    {
        return serialNumber;
    }
    public void setPageNumber(Integer pageNumber) 
    {
        this.pageNumber = pageNumber;
    }

    public Integer getPageNumber() 
    {
        return pageNumber;
    }
    public void setMaterialYear(Integer materialYear) 
    {
        this.materialYear = materialYear;
    }

    public Integer getMaterialYear() 
    {
        return materialYear;
    }
    public void setMaterialMonth(Integer materialMonth) 
    {
        this.materialMonth = materialMonth;
    }

    public Integer getMaterialMonth() 
    {
        return materialMonth;
    }
    public void setMaterialDay(Integer materialDay) 
    {
        this.materialDay = materialDay;
    }

    public Integer getMaterialDay() 
    {
        return materialDay;
    }
    public void setMaterialTime(Date materialTime) 
    {
        this.materialTime = materialTime;
    }

    public Date getMaterialTime() 
    {
        return materialTime;
    }
    public void setFs(Integer fs) 
    {
        this.fs = fs;
    }

    public Integer getFs() 
    {
        return fs;
    }
    public void setCheckId(Integer checkId) 
    {
        this.checkId = checkId;
    }

    public Integer getCheckId() 
    {
        return checkId;
    }
    public void setCheckField(String checkField) 
    {
        this.checkField = checkField;
    }

    public String getCheckField() 
    {
        return checkField;
    }
    public void setExamineFileld(String examineFileld) 
    {
        this.examineFileld = examineFileld;
    }

    public String getExamineFileld() 
    {
        return examineFileld;
    }
    public void setCondition(String condition) 
    {
        this.condition = condition;
    }

    public String getCondition() 
    {
        return condition;
    }
    public void setOldArchid(String oldArchid) 
    {
        this.oldArchid = oldArchid;
    }

    public String getOldArchid() 
    {
        return oldArchid;
    }
    public void setOldRsid(String oldRsid) 
    {
        this.oldRsid = oldRsid;
    }

    public String getOldRsid() 
    {
        return oldRsid;
    }
    public void setJsclId(String jsclId) 
    {
        this.jsclId = jsclId;
    }

    public String getJsclId() 
    {
        return jsclId;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("archId", getArchId())
            .append("rsId", getRsId())
            .append("remark", getRemark())
            .append("materialName", getMaterialName())
            .append("materialType", getMaterialType())
            .append("serialNumber", getSerialNumber())
            .append("pageNumber", getPageNumber())
            .append("materialYear", getMaterialYear())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("materialMonth", getMaterialMonth())
            .append("materialDay", getMaterialDay())
            .append("materialTime", getMaterialTime())
            .append("fs", getFs())
            .append("checkId", getCheckId())
            .append("checkField", getCheckField())
            .append("examineFileld", getExamineFileld())
            .append("condition", getCondition())
            .append("oldArchid", getOldArchid())
            .append("oldRsid", getOldRsid())
            .append("jsclId", getJsclId())
            .append("addTime", getAddTime())
            .toString();
    }
}
