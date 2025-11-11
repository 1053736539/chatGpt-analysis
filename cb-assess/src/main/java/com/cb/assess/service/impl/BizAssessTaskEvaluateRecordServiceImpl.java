package com.cb.assess.service.impl;

import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessTaskEvaluateRecordMapper;
import com.cb.assess.domain.BizAssessTaskEvaluateRecord;
import com.cb.assess.service.IBizAssessTaskEvaluateRecordService;

/**
 * 考核评议记录Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-20
 */
@Service
public class BizAssessTaskEvaluateRecordServiceImpl implements IBizAssessTaskEvaluateRecordService  {
    @Autowired
    private BizAssessTaskEvaluateRecordMapper bizAssessTaskEvaluateRecordMapper;

    /**
     * 查询考核评议记录
     *
     * @param evaluateId 考核评议记录ID
     * @return 考核评议记录
     */
    @Override
    public BizAssessTaskEvaluateRecord selectBizAssessTaskEvaluateRecordById(String evaluateId) {
        return bizAssessTaskEvaluateRecordMapper.selectBizAssessTaskEvaluateRecordById(evaluateId);
    }

    /**
     * 查询考核评议记录列表
     *
     * @param bizAssessTaskEvaluateRecord 考核评议记录
     * @return 考核评议记录
     */
    @Override
    public List<BizAssessTaskEvaluateRecord> selectBizAssessTaskEvaluateRecordList(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        return bizAssessTaskEvaluateRecordMapper.selectBizAssessTaskEvaluateRecordList(bizAssessTaskEvaluateRecord);
    }

    /**
     * 新增考核评议记录
     *
     * @param bizAssessTaskEvaluateRecord 考核评议记录
     * @return 结果
     */
    @Override
    public int insertBizAssessTaskEvaluateRecord(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        bizAssessTaskEvaluateRecord.setEvaluateId(IdUtils.randomUUID());
        bizAssessTaskEvaluateRecord.setCreateBy(SecurityUtils.getUsername());
        bizAssessTaskEvaluateRecord.setCreateTime(DateUtils.getNowDate());
        return bizAssessTaskEvaluateRecordMapper.insertBizAssessTaskEvaluateRecord(bizAssessTaskEvaluateRecord);
    }

    @Override
    public int batchInsertBizAssessTaskEvaluateRecord(List<BizAssessTaskEvaluateRecord> list) {
        return bizAssessTaskEvaluateRecordMapper.batchInsertBizAssessTaskEvaluateRecord(list);
    }

    /**
     * 修改考核评议记录
     *
     * @param bizAssessTaskEvaluateRecord 考核评议记录
     * @return 结果
     */
    @Override
    public int updateBizAssessTaskEvaluateRecord(BizAssessTaskEvaluateRecord bizAssessTaskEvaluateRecord) {
        bizAssessTaskEvaluateRecord.setUpdateBy(SecurityUtils.getUsername());
        bizAssessTaskEvaluateRecord.setUpdateTime(DateUtils.getNowDate());
        return bizAssessTaskEvaluateRecordMapper.updateBizAssessTaskEvaluateRecord(bizAssessTaskEvaluateRecord);
    }

    /**
     * 批量删除考核评议记录
     *
     * @param evaluateIds 需要删除的考核评议记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskEvaluateRecordByIds(String[] evaluateIds) {
        return bizAssessTaskEvaluateRecordMapper.deleteBizAssessTaskEvaluateRecordByIds(evaluateIds);
    }

    /**
     * 删除考核评议记录信息
     *
     * @param evaluateId 考核评议记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskEvaluateRecordById(String evaluateId) {
        return bizAssessTaskEvaluateRecordMapper.deleteBizAssessTaskEvaluateRecordById(evaluateId);
    }

    @Override
    public int deleteEvaluateRecord(List<String> evaluateTagIds) {
        return bizAssessTaskEvaluateRecordMapper.deleteEvaluateRecord(evaluateTagIds);
    }
}
