package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户基层工作经历信息对象 sys_user_grassroots_work_info
 *
 * @author ruoyi
 * @date 2023-11-04
 */
public class SysUserGrassrootsWorkInfo {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String grassrootsWorkId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间")
    private String startDate;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间")
    private String endDate;

    /**
     * 工作地
     */
    @Excel(name = "工作地")
    private String workplace;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private String delFlag;

    public void setGrassrootsWorkId(String grassrootsWorkId) {
        this.grassrootsWorkId = grassrootsWorkId;
    }

    public String getGrassrootsWorkId() {
        return grassrootsWorkId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("grassrootsWorkId", getGrassrootsWorkId())
                .append("userId", getUserId())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .append("workplace", getWorkplace())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
