package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录对象 biz_probity_investigate
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityInvestigate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * probity_id
     */
    @Excel(name = "probity_id")
    private String probityId;

    /**
     * 被追究时间
     */
    @Excel(name = "被追究时间")
    private String investigateTime;

    /**
     * 被追究原因
     */
    @Excel(name = "被追究原因")
    private String investigateReason;

    /**
     * 调查处罚机关
     */
    @Excel(name = "调查处罚机关")
    private String investigateDept;

    /**
     * 处理阶段
     */
    @Excel(name = "处理阶段")
    private String handleStage;

    /**
     * 处理结果
     */
    @Excel(name = "处理结果")
    private String handleResult;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProbityId(String probityId) {
        this.probityId = probityId;
    }

    public String getProbityId() {
        return probityId;
    }

    public void setInvestigateTime(String investigateTime) {
        this.investigateTime = investigateTime;
    }

    public String getInvestigateTime() {
        return investigateTime;
    }

    public void setInvestigateReason(String investigateReason) {
        this.investigateReason = investigateReason;
    }

    public String getInvestigateReason() {
        return investigateReason;
    }

    public void setInvestigateDept(String investigateDept) {
        this.investigateDept = investigateDept;
    }

    public String getInvestigateDept() {
        return investigateDept;
    }

    public void setHandleStage(String handleStage) {
        this.handleStage = handleStage;
    }

    public String getHandleStage() {
        return handleStage;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandleResult() {
        return handleResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("probityId", getProbityId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("investigateTime", getInvestigateTime())
                .append("investigateReason", getInvestigateReason())
                .append("investigateDept", getInvestigateDept())
                .append("handleStage", getHandleStage())
                .append("handleResult", getHandleResult())
                .toString();
    }
}
