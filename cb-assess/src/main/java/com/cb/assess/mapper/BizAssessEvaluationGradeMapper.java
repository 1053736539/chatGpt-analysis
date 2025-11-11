package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessEvaluationGrade;
import org.apache.ibatis.annotations.Param;

/**
 * 考核评定等次存储Mapper接口
 *
 * @author ouyang
 * @date 2023-11-24
 */
public interface BizAssessEvaluationGradeMapper {
    /**
     * 查询考核评定等次存储
     *
     * @param id 考核评定等次存储ID
     * @return 考核评定等次存储
     */
    public BizAssessEvaluationGrade selectBizAssessEvaluationGradeById(String id);

    /**
     * 查询考核评定等次存储列表
     *
     * @param bizAssessEvaluationGrade 考核评定等次存储
     * @return 考核评定等次存储集合
     */
    public List<BizAssessEvaluationGrade> selectBizAssessEvaluationGradeList(BizAssessEvaluationGrade bizAssessEvaluationGrade);

    public List<BizAssessEvaluationGrade> selectAssessEvaluationGradeList(String escalationId);
    /**
     * 新增考核评定等次存储
     *
     * @param bizAssessEvaluationGrade 考核评定等次存储
     * @return 结果
     */
    public int insertBizAssessEvaluationGrade(BizAssessEvaluationGrade bizAssessEvaluationGrade);

    /**
     * 修改考核评定等次存储
     *
     * @param bizAssessEvaluationGrade 考核评定等次存储
     * @return 结果
     */
    public int updateBizAssessEvaluationGrade(BizAssessEvaluationGrade bizAssessEvaluationGrade);



    public BizAssessEvaluationGrade selectBizAssessEvaluationGradeByParam(@Param("taskId") String taskId,
                                                                          @Param("userId") Long userId,
                                                                          @Param("deptId") Long deptId);

    public BizAssessEvaluationGrade checkUnique(String id);

}
