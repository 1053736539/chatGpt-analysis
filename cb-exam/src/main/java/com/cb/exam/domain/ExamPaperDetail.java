package com.cb.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 exam_paper_detail
 *
 * @author hu
 * @date 2021-12-13
 */
public class ExamPaperDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 试卷的id
     */
    @Excel(name = "试卷的id")
    private Long paperId;

    /**
     * 题库的id
     */
    @Excel(name = "题库的id")
    private String bankIds;

    /**
     * 标签,字符串切割
     */
    @Excel(name = "标签,字符串切割")
    private String labelIds;


    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private String modifyUser;

    /**
     * 单选题数量
     */
    @Excel(name = "单选题数量")
    private Long selectCount;

    /**
     * 多选题数量
     */
    @Excel(name = "多选题数量")
    private Long selectsCount;

    /**
     * 判断题数量
     */
    @Excel(name = "判断题数量")
    private Long judgeCount;

    /**
     * 问答题数量
     */
    @Excel(name = "问答题数量")
    private Long answerCount;

    /**
     * 根据试卷详情拉取试题数据需要的筛选的已选择数据
     */
    private Long[] questionId;

    /**
     * 题库的数组
     */
    private Long[] labelList;

    /**
     * 标签的数组
     */
    private Long[] bankList;

    public void setQuestionId(Long[] questionId) {
        this.questionId = questionId;
    }

    public Long[] getQuestionId() {
        return questionId;
    }

    public Long[] getLabelList() {
        return labelList;
    }

    public void setLabelList(Long[] labelList) {
        this.labelList = labelList;
    }

    public Long[] getBankList() {
        return bankList;
    }

    public void setBankList(Long[] bankList) {
        this.bankList = bankList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setBankIds(String bankIds) {
        this.bankIds = bankIds;
    }

    public String getBankIds() {
        return bankIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getLabelIds() {
        return labelIds;
    }


    public void setSelectCount(Long selectCount) {
        this.selectCount = selectCount;
    }

    public Long getSelectCount() {
        return selectCount;
    }

    public void setSelectsCount(Long selectsCount) {
        this.selectsCount = selectsCount;
    }

    public Long getSelectsCount() {
        return selectsCount;
    }

    public void setJudgeCount(Long judgeCount) {
        this.judgeCount = judgeCount;
    }

    public Long getJudgeCount() {
        return judgeCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public ExamPaperDetail() {
    }

    /***
     * @Summary 添加数据使用的构造方法
     * @Param: [paperId, bankIds, labelIds, selectCount, selectsCount, judgeCount, answerCount]
     * @Author: hu
     * @Date: 2023/11/08
     * @Description
     */
    public ExamPaperDetail(Long paperId, String bankIds, String labelIds, Long selectCount, Long selectsCount, Long judgeCount, Long answerCount) {
        this.paperId = paperId;
        this.bankIds = bankIds;
        this.labelIds = labelIds;
        this.selectCount = selectCount;
        this.selectsCount = selectsCount;
        this.judgeCount = judgeCount;
        this.answerCount = answerCount;
    }

    /***
     * @Summary 查询题目使用的构造方法
     * @Param: [bank, label, selectCount, selectsCount, judgeCount, answerCount]
     * @Author: hu
     * @Date: 2023/11/08
     * @Description
     */
    public ExamPaperDetail(Long[] bank, Long[] label, Long selectCount, Long selectsCount, Long judgeCount, Long answerCount) {
        this.bankList = bank;
        this.labelList = label;
        this.selectCount = selectCount;
        this.selectsCount = selectsCount;
        this.judgeCount = judgeCount;
        this.answerCount = answerCount;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("paperId", getPaperId())
                .append("bankIds", getBankIds())
                .append("labelIds", getLabelIds())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateBy())
                .append("modifyTime", getUpdateTime())
                .append("modifyUser", getUpdateBy())
                .append("selectCount", getSelectCount())
                .append("selectsCount", getSelectsCount())
                .append("judgeCount", getJudgeCount())
                .append("answerCount", getAnswerCount())
                .toString();
    }
}
