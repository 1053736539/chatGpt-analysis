package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessTaskEvaluateRecord;

/**
 * 考核评议记录Service接口
 *
 * @author ouyang
 * @date 2023-11-20
 */
public interface IBizAssessTaskEvaluateRecordService {
    /**
     * 查询考核评议记录
     *
     * @param evaluateId 考核评议记录ID
     * @return 考核评议记录
     */
    public BizAssessTaskEvaluateRecord selectBizAssessTaskEvaluateRecordById(String evaluateId);

    /**
     * 查询考核评议记录列表
     *
     * @param bizAssessTaskEvaluateRecord 考核评议记录
     * @return 考核评议记录集合
     */
    public List<BizAssessTaskEvaluateRecord> selectBizAssessTaskEvaluateRecordList(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord);

    /**
     * 新增考核评议记录
     *
     * @param bizAssessTaskEvaluateRecord 考核评议记录
     * @return 结果
     */
    public int insertBizAssessTaskEvaluateRecord(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord);


    public int batchInsertBizAssessTaskEvaluateRecord(List<BizAssessTaskEvaluateRecord> list);

    /**
     * 修改考核评议记录
     *
     * @param bizAssessTaskEvaluateRecord 考核评议记录
     * @return 结果
     */
    public int updateBizAssessTaskEvaluateRecord(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord);

    /**
     * 批量删除考核评议记录
     *
     * @param evaluateIds 需要删除的考核评议记录ID
     * @return 结果
     */
    public int deleteBizAssessTaskEvaluateRecordByIds(String[] evaluateIds);

    /**
     * 删除考核评议记录信息
     *
     * @param evaluateId 考核评议记录ID
     * @return 结果
     */
    public int deleteBizAssessTaskEvaluateRecordById(String evaluateId);


    public int deleteEvaluateRecord(List<String> evaluateTagIds);
}
