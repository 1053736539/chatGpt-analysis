package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得对象 biz_probity_lecture
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityLecture extends BaseEntity {
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
     * 讲学
     */
    @Excel(name = "讲学")
    private String lecture;

    /**
     * 写作
     */
    @Excel(name = "写作")
    private String writing;

    /**
     * 咨询
     */
    @Excel(name = "咨询")
    private String consult;

    /**
     * 审稿
     */
    @Excel(name = "审稿")
    private String review;

    /**
     * 书画
     */
    @Excel(name = "书画")
    private String calligraphyPainting;

    /**
     * 其他
     */
    @Excel(name = "其他")
    private String other;

    /**
     * 合计
     */
    @Excel(name = "合计")
    private String total;

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

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getLecture() {
        return lecture;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getWriting() {
        return writing;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    public String getConsult() {
        return consult;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setCalligraphyPainting(String calligraphyPainting) {
        this.calligraphyPainting = calligraphyPainting;
    }

    public String getCalligraphyPainting() {
        return calligraphyPainting;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
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
                .append("lecture", getLecture())
                .append("writing", getWriting())
                .append("consult", getConsult())
                .append("review", getReview())
                .append("calligraphyPainting", getCalligraphyPainting())
                .append("other", getOther())
                .append("total", getTotal())
                .toString();
    }
}
