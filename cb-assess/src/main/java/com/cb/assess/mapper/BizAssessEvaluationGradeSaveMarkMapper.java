package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessEvaluationGradeSaveMark;
import org.apache.ibatis.annotations.Param;

/**
 * 考核上报数据保存标记Mapper接口
 *
 * @author ouyang
 * @date 2023-12-07
 */
public interface BizAssessEvaluationGradeSaveMarkMapper {
    /**
     * 查询考核上报数据保存标记
     *
     * @param taskId 考核上报数据保存标记ID
     * @return 考核上报数据保存标记
     */
    public BizAssessEvaluationGradeSaveMark selectBizAssessEvaluationGradeSaveMarkById(String taskId);

    /**
     * 查询考核上报数据保存标记列表
     *
     * @param bizAssessEvaluationGradeSaveMark 考核上报数据保存标记
     * @return 考核上报数据保存标记集合
     */
    public List<BizAssessEvaluationGradeSaveMark> selectBizAssessEvaluationGradeSaveMarkList(BizAssessEvaluationGradeSaveMark bizAssessEvaluationGradeSaveMark);

    /**
     * 新增考核上报数据保存标记
     *
     * @param bizAssessEvaluationGradeSaveMark 考核上报数据保存标记
     * @return 结果
     */
    public int insertBizAssessEvaluationGradeSaveMark(BizAssessEvaluationGradeSaveMark bizAssessEvaluationGradeSaveMark);

    /**
     * 修改考核上报数据保存标记
     *
     * @param bizAssessEvaluationGradeSaveMark 考核上报数据保存标记
     * @return 结果
     */
    public int updateBizAssessEvaluationGradeSaveMark(BizAssessEvaluationGradeSaveMark bizAssessEvaluationGradeSaveMark);

    /**
     * 删除考核上报数据保存标记
     *
     * @param taskId 考核上报数据保存标记ID
     * @return 结果
     */
    public int deleteBizAssessEvaluationGradeSaveMarkById(String taskId);

    /**
     * 批量删除考核上报数据保存标记
     *
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessEvaluationGradeSaveMarkByIds(String[] taskIds);

    public boolean checkExist(BizAssessEvaluationGradeSaveMark mark);
    public BizAssessEvaluationGradeSaveMark selectOne(BizAssessEvaluationGradeSaveMark mark);

}
