package com.cb.assess.service.impl;

import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessEvaluationGradeMapper;
import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.service.IBizAssessEvaluationGradeService;

/**
 * 考核评定等次存储Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-24
 */
@Service
public class BizAssessEvaluationGradeServiceImpl implements IBizAssessEvaluationGradeService {
    @Autowired
    private BizAssessEvaluationGradeMapper bizAssessEvaluationGradeMapper;

    /**
     * 查询考核评定等次存储
     *
     * @param id 考核评定等次存储ID
     * @return 考核评定等次存储
     */
    @Override
    public BizAssessEvaluationGrade selectBizAssessEvaluationGradeById(String id) {
        return bizAssessEvaluationGradeMapper.selectBizAssessEvaluationGradeById(id);
    }

    /**
     * 查询考核评定等次存储列表
     *
     * @param bizAssessEvaluationGrade 考核评定等次存储
     * @return 考核评定等次存储
     */
    @Override
    public List<BizAssessEvaluationGrade> selectBizAssessEvaluationGradeList(BizAssessEvaluationGrade bizAssessEvaluationGrade) {
        return bizAssessEvaluationGradeMapper.selectBizAssessEvaluationGradeList(bizAssessEvaluationGrade);
    }

    /**
     * 新增考核评定等次存储
     *
     * @param bizAssessEvaluationGrade 考核评定等次存储
     * @return 结果
     */
    @Override
    public int insertBizAssessEvaluationGrade(BizAssessEvaluationGrade bizAssessEvaluationGrade) {
        bizAssessEvaluationGrade.setId(IdUtils.randomUUID());
        bizAssessEvaluationGrade.setCreateBy(SecurityUtils.getUsername());
        bizAssessEvaluationGrade.setCreateTime(DateUtils.getNowDate());
        return bizAssessEvaluationGradeMapper.insertBizAssessEvaluationGrade(bizAssessEvaluationGrade);
    }

    /**
     * 修改考核评定等次存储
     *
     * @param bizAssessEvaluationGrade 考核评定等次存储
     * @return 结果
     */
    @Override
    public int updateBizAssessEvaluationGrade(BizAssessEvaluationGrade bizAssessEvaluationGrade) {
        bizAssessEvaluationGrade.setUpdateBy(SecurityUtils.getUsername());
        bizAssessEvaluationGrade.setUpdateTime(DateUtils.getNowDate());
        return bizAssessEvaluationGradeMapper.updateBizAssessEvaluationGrade(bizAssessEvaluationGrade);
    }

    @Override
    public BizAssessEvaluationGrade selectBizAssessEvaluationGradeByParam(String taskId, Long userId, Long deptId) {
        return bizAssessEvaluationGradeMapper.selectBizAssessEvaluationGradeByParam(taskId, userId, deptId);
    }

    @Override
    public BizAssessEvaluationGrade checkUnique(String id) {
        return bizAssessEvaluationGradeMapper.checkUnique(id);
    }
}
