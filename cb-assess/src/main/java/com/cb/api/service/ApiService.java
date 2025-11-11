package com.cb.api.service;

public interface ApiService {

    /**
     * 统计平时考核待办数量
     * @return
     */
    public int countAssessToDoNum();

    /**
     * 统计纪委打分数量
     * @return
     */
    public int countDisciplineScoringNum();

    public int countPartyCommitteeNum();

    /**
     * 待办任务数量
     * @return
     */
    public int countTaskMyTodoNum();

    /**
     * 待认领数量
     * @return
     */
    public int countTaskMyUnClaimNum();

    /**
     * 超期数量
     * @return
     */
    public int countTaskMyExpireNum();

    /**
     * 查询自己的政治素质考察待办任务
     * @return
     */
    public int selectQualityCountMyTask();

    /**
     * 统计自己的优秀推荐互评待办
     * @return
     */
    public int selectMyExcellentEvaluationCount();

    /**
     * 查询自己的民主品策待填
     * @return
     */
    int selectCountMyDemocratic();

    int selectAnnualReviewTypeACount();

    int selectAnnualReviewTypeBCount();
}
