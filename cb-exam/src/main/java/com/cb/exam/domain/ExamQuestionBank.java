package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 题库对象 exam_question_bank
 *
 * @author hu
 * @date 2023-11-01
 */
public class ExamQuestionBank extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 题库名字
     */
    @Excel(name = "题库名字")
    private String name;

    /**
     * 题库描述
     */
    private String description;


    /* 注释之前的 开始*/
    /**
     * 创建人
     */
    /*@Excel(name = "创建人")
    private String createUser;*/

    /**
     * 修改人
     */
    /*@Excel(name = "修改人")
    private String modifyUser;*/

    /**
     * 修改时间
     */
    /*@JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;*/
    /* 注释之前的 结束 */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("description", getDescription())
                .toString();
    }
}
