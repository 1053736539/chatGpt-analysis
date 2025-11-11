package com.cb.assess.service.impl;

import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessEvaluationGradeSaveMarkMapper;
import com.cb.assess.domain.BizAssessEvaluationGradeSaveMark;
import com.cb.assess.service.IBizAssessEvaluationGradeSaveMarkService;

/**
 * 考核上报数据保存标记Service业务层处理
 *
 * @author ouyang
 * @date 2023-12-07
 */
@Service
public class BizAssessEvaluationGradeSaveMarkServiceImpl implements IBizAssessEvaluationGradeSaveMarkService {
    @Autowired
    private BizAssessEvaluationGradeSaveMarkMapper bizAssessEvaluationGradeSaveMarkMapper;

    /**
     * 查询考核上报数据保存标记
     *
     * @param taskId 考核上报数据保存标记ID
     * @return 考核上报数据保存标记
     */
    @Override
    public BizAssessEvaluationGradeSaveMark selectBizAssessEvaluationGradeSaveMarkById(String taskId) {
        return bizAssessEvaluationGradeSaveMarkMapper.selectBizAssessEvaluationGradeSaveMarkById(taskId);
    }

    /**
     * 查询考核上报数据保存标记列表
     *
     * @param bizAssessEvaluationGradeSaveMark 考核上报数据保存标记
     * @return 考核上报数据保存标记
     */
    @Override
    public List<BizAssessEvaluationGradeSaveMark> selectBizAssessEvaluationGradeSaveMarkList(BizAssessEvaluationGradeSaveMark bizAssessEvaluationGradeSaveMark) {
        return bizAssessEvaluationGradeSaveMarkMapper.selectBizAssessEvaluationGradeSaveMarkList(bizAssessEvaluationGradeSaveMark);
    }

    /**
     * 新增考核上报数据保存标记
     *
     * @param bizAssessEvaluationGradeSaveMark 考核上报数据保存标记
     * @return 结果
     */
    @Override
    public int insertBizAssessEvaluationGradeSaveMark(BizAssessEvaluationGradeSaveMark bizAssessEvaluationGradeSaveMark) {

        bizAssessEvaluationGradeSaveMark.setCreateBy(SecurityUtils.getUsername());
        bizAssessEvaluationGradeSaveMark.setCreateTime(DateUtils.getNowDate());
        return bizAssessEvaluationGradeSaveMarkMapper.insertBizAssessEvaluationGradeSaveMark(bizAssessEvaluationGradeSaveMark);
    }
    /**
     * 修改考核上报数据保存标记
     *
     * @param bizAssessEvaluationGradeSaveMark 考核上报数据保存标记
     * @return 结果
     */
    @Override
    public int updateBizAssessEvaluationGradeSaveMark(BizAssessEvaluationGradeSaveMark bizAssessEvaluationGradeSaveMark)
    {
        bizAssessEvaluationGradeSaveMark.setUpdateBy(SecurityUtils.getUsername());
        bizAssessEvaluationGradeSaveMark.setUpdateTime(DateUtils.getNowDate());
        return bizAssessEvaluationGradeSaveMarkMapper.updateBizAssessEvaluationGradeSaveMark(bizAssessEvaluationGradeSaveMark);
    }

    /**
     * 批量删除考核上报数据保存标记
     *
     * @param taskIds 需要删除的考核上报数据保存标记ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessEvaluationGradeSaveMarkByIds(String[] taskIds) {
        return bizAssessEvaluationGradeSaveMarkMapper.deleteBizAssessEvaluationGradeSaveMarkByIds(taskIds);
    }

    /**
     * 删除考核上报数据保存标记信息
     *
     * @param taskId 考核上报数据保存标记ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessEvaluationGradeSaveMarkById(String taskId) {
        return bizAssessEvaluationGradeSaveMarkMapper.deleteBizAssessEvaluationGradeSaveMarkById(taskId);
    }

    @Override
    public BizAssessEvaluationGradeSaveMark selectOne(BizAssessEvaluationGradeSaveMark mark) {
        return bizAssessEvaluationGradeSaveMarkMapper.selectOne(mark);
    }

}
