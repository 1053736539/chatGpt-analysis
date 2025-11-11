package com.cb.task.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

/**
 * 任务公文类型对象 biz_task_doc_type
 * 
 * @author yangxin
 * @date 2023-11-11
 */
public class BizTaskDocType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 模块类型 system-系统公文类型 dept-部门自建类型 */
    @Excel(name = "模块类型 system-系统公文类型 dept-部门自建类型")
    private String moduleType;

    /** 所属部门id 部门自建时不为空 */
//    @Excel(name = "所属部门id 部门自建时不为空")
    private Long deptId;

    /** 分类code */
    @Excel(name = "分类code")
    private String code;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String label;

    /** 删除标记 0-正常 1-删除 */
    private String delFlag;

    /** 分数 */
    private Integer score;


    /** 以下为扩展字段 **/

    @Transient
    @Excel(name = "所属部门")
    private String deptName;


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setModuleType(String moduleType) 
    {
        this.moduleType = moduleType;
    }

    public String getModuleType() 
    {
        return moduleType;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setLabel(String label) 
    {
        this.label = label;
    }

    public String getLabel() 
    {
        return label;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("moduleType", getModuleType())
            .append("deptId", getDeptId())
            .append("code", getCode())
            .append("label", getLabel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
