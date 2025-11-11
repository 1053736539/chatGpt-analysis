package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessAssessmentGrade;
import com.cb.assess.domain.vo.BizAssessAssessmentGradeVo;

import java.util.List;

/**
 * 机关参公和事业单位等次Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-25
 */
public interface BizAssessAssessmentGradeMapper 
{
    /**
     * 查询机关参公和事业单位等次
     * 
     * @param id 机关参公和事业单位等次ID
     * @return 机关参公和事业单位等次
     */
    public BizAssessAssessmentGrade selectBizAssessAssessmentGradeById(String id);

    /**
     * 查询机关参公和事业单位等次列表
     * 
     * @param bizAssessAssessmentGrade 机关参公和事业单位等次
     * @return 机关参公和事业单位等次集合
     */
    public List<BizAssessAssessmentGradeVo> selectBizAssessAssessmentGradeList(BizAssessAssessmentGradeVo bizAssessAssessmentGrade);

    /**
     * 新增机关参公和事业单位等次
     * 
     * @param bizAssessAssessmentGrade 机关参公和事业单位等次
     * @return 结果
     */
    public int insertBizAssessAssessmentGrade(BizAssessAssessmentGrade bizAssessAssessmentGrade);

    /**
     * 修改机关参公和事业单位等次
     * 
     * @param bizAssessAssessmentGrade 机关参公和事业单位等次
     * @return 结果
     */
    public int updateBizAssessAssessmentGrade(BizAssessAssessmentGrade bizAssessAssessmentGrade);

    /**
     * 删除机关参公和事业单位等次
     * 
     * @param id 机关参公和事业单位等次ID
     * @return 结果
     */
    public int deleteBizAssessAssessmentGradeById(String id);

    /**
     * 批量删除机关参公和事业单位等次
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessAssessmentGradeByIds(String[] ids);
}
