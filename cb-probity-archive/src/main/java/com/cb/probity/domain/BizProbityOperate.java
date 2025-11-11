package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-19.本人或本人子女操办婚丧嫁娶情况对象 biz_probity_operate
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityOperate extends BaseEntity {
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
     * 操办事项
     */
    @Excel(name = "操办事项")
    private String operateItem;

    /**
     * 操办时间
     */
    @Excel(name = "操办时间")
    private String operateTime;

    /**
     * 操办地点
     */
    @Excel(name = "操办地点")
    private String operateAddr;

    /**
     * 人数
     */
    @Excel(name = "人数")
    private String peopleNum;

    /**
     * 是否进行报告备案
     */
    @Excel(name = "是否进行报告备案")
    private String filingFlag;

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

    public void setOperateItem(String operateItem) {
        this.operateItem = operateItem;
    }

    public String getOperateItem() {
        return operateItem;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateAddr(String operateAddr) {
        this.operateAddr = operateAddr;
    }

    public String getOperateAddr() {
        return operateAddr;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setFilingFlag(String filingFlag) {
        this.filingFlag = filingFlag;
    }

    public String getFilingFlag() {
        return filingFlag;
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
                .append("operateItem", getOperateItem())
                .append("operateTime", getOperateTime())
                .append("operateAddr", getOperateAddr())
                .append("peopleNum", getPeopleNum())
                .append("filingFlag", getFilingFlag())
                .toString();
    }
}
