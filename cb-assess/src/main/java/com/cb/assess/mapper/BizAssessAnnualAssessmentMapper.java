package com.cb.assess.mapper;

import java.util.List;
import com.cb.assess.domain.BizAssessAnnualAssessment;
import com.cb.assess.domain.vo.BizAssessAnnualAssessmentVo;

/**
 * 年度考核Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-17
 */
public interface BizAssessAnnualAssessmentMapper 
{
    /**
     * 查询年度考核
     * 
     * @param id 年度考核ID
     * @return 年度考核
     */
    public BizAssessAnnualAssessmentVo selectBizAssessAnnualAssessmentById(String id);

    /**
     * 查询年度考核列表
     * 
     * @param bizAssessAnnualAssessment 年度考核
     * @return 年度考核集合
     */
    public List<BizAssessAnnualAssessmentVo> selectBizAssessAnnualAssessmentList(BizAssessAnnualAssessmentVo bizAssessAnnualAssessment);

    public List<BizAssessAnnualAssessmentVo> selectYears();

    /**
     * 新增年度考核
     * 
     * @param bizAssessAnnualAssessment 年度考核
     * @return 结果
     */
    public int insertBizAssessAnnualAssessment(BizAssessAnnualAssessment bizAssessAnnualAssessment);

    /**
     * 修改年度考核
     * 
     * @param bizAssessAnnualAssessment 年度考核
     * @return 结果
     */
    public int updateBizAssessAnnualAssessment(BizAssessAnnualAssessment bizAssessAnnualAssessment);

    /**
     * 删除年度考核
     * 
     * @param id 年度考核ID
     * @return 结果
     */
    public int deleteBizAssessAnnualAssessmentById(String id);

    /**
     * 批量删除年度考核
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessAnnualAssessmentByIds(String[] ids);
}
