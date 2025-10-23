package com.cb.worksituation.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;

/**
 * 部门评分-头对象 BUS_DEP_REVIEW_HEADER
 *
 * @author ruoyi
 * @date 2025-10-11
 */
public class BusDepReviewHeader extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 编码
     */
    @Excel(name = "编码")
    private String headCode;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String headName;

    /**
     * 分类
     */
    @Excel(name = "分类")
    private String headType;

    /**
     * 得分
     */
    @Excel(name = "得分")
    private BigDecimal headScore;

    /**
     * 所属评分表Id
     */
    @Excel(name = "所属评分表Id")
    private String busDepReviewId;

    /**
     * 说明
     */
    @Excel(name = "说明")
    private String instructions;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private BigDecimal headOrder;

    /**
     * 评分说明Id
     */
    @Excel(name = "评分说明Id")
    private String busDepExplId;



    /**
     * 是否多部门打分（1-是，2-否）
     */
    @Excel(name = "是否多部门打分")
    private Integer multDeptScore;


    /**
     * 评分说明
     */
    @Transient
    private BusDepExpl busDepExpl;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setHeadCode(String headCode) {
        this.headCode = headCode;
    }

    public String getHeadCode() {
        return headCode;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadType(String headType) {
        this.headType = headType;
    }

    public String getHeadType() {
        return headType;
    }

    public void setHeadScore(BigDecimal headScore) {
        this.headScore = headScore;
    }

    public BigDecimal getHeadScore() {
        return headScore;
    }

    public void setBusDepReviewId(String busDepReviewId) {
        this.busDepReviewId = busDepReviewId;
    }

    public String getBusDepReviewId() {
        return busDepReviewId;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setHeadOrder(BigDecimal headOrder) {
        this.headOrder = headOrder;
    }

    public BigDecimal getHeadOrder() {
        return headOrder;
    }

    public void setBusDepExplId(String busDepExplId) {
        this.busDepExplId = busDepExplId;
    }

    public String getBusDepExplId() {
        return busDepExplId;
    }

    public BusDepExpl getBusDepExpl() {
        return busDepExpl;
    }

    public void setBusDepExpl(BusDepExpl busDepExpl) {
        this.busDepExpl = busDepExpl;
    }

    public Integer getMultDeptScore() {
        return multDeptScore;
    }

    public void setMultDeptScore(Integer multDeptScore) {
        this.multDeptScore = multDeptScore;
    }


}
