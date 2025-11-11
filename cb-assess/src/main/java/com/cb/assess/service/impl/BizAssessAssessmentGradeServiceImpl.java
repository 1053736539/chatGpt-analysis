package com.cb.assess.service.impl;

import java.util.List;

import com.cb.assess.domain.BizAssessAssessmentGrade;
import com.cb.assess.domain.vo.BizAssessAssessmentGradeVo;
import com.cb.assess.mapper.BizAssessAssessmentGradeMapper;
import com.cb.assess.service.IBizAssessAssessmentGradeService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 机关参公和事业单位等次Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-25
 */
@Service
public class BizAssessAssessmentGradeServiceImpl implements IBizAssessAssessmentGradeService
{
    @Autowired
    private BizAssessAssessmentGradeMapper bizAssessAssessmentGradeMapper;

    /**
     * 查询机关参公和事业单位等次
     * 
     * @param id 机关参公和事业单位等次ID
     * @return 机关参公和事业单位等次
     */
    @Override
    public BizAssessAssessmentGrade selectBizAssessAssessmentGradeById(String id)
    {
        return bizAssessAssessmentGradeMapper.selectBizAssessAssessmentGradeById(id);
    }

    /**
     * 查询机关参公和事业单位等次列表
     * 
     * @param bizAssessAssessmentGrade 机关参公和事业单位等次
     * @return 机关参公和事业单位等次
     */
    @Override
    public List<BizAssessAssessmentGradeVo> selectBizAssessAssessmentGradeList(BizAssessAssessmentGradeVo bizAssessAssessmentGrade)
    {
        return bizAssessAssessmentGradeMapper.selectBizAssessAssessmentGradeList(bizAssessAssessmentGrade);
    }

    /**
     * 新增机关参公和事业单位等次
     * 
     * @param bizAssessAssessmentGrade 机关参公和事业单位等次
     * @return 结果
     */
    @Override
    public int insertBizAssessAssessmentGrade(BizAssessAssessmentGrade bizAssessAssessmentGrade)
    {
        bizAssessAssessmentGrade.setId(UUID.randomUUID().toString());
        bizAssessAssessmentGrade.setCreateBy(SecurityUtils.getUsername());
        bizAssessAssessmentGrade.setCreateTime(DateUtils.getNowDate());
        bizAssessAssessmentGrade.setStatus("1");
        bizAssessAssessmentGrade.setDelFlag("0");
        return bizAssessAssessmentGradeMapper.insertBizAssessAssessmentGrade(bizAssessAssessmentGrade);
    }

    /**
     * 修改机关参公和事业单位等次
     * 
     * @param bizAssessAssessmentGrade 机关参公和事业单位等次
     * @return 结果
     */
    @Override
    public int updateBizAssessAssessmentGrade(BizAssessAssessmentGrade bizAssessAssessmentGrade)
    {
        bizAssessAssessmentGrade.setUpdateTime(DateUtils.getNowDate());
        bizAssessAssessmentGrade.setUpdateBy(SecurityUtils.getUsername());
        return bizAssessAssessmentGradeMapper.updateBizAssessAssessmentGrade(bizAssessAssessmentGrade);
    }

    /**
     * 批量删除机关参公和事业单位等次
     * 
     * @param ids 需要删除的机关参公和事业单位等次ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAssessmentGradeByIds(String[] ids)
    {
        return bizAssessAssessmentGradeMapper.deleteBizAssessAssessmentGradeByIds(ids);
    }

    /**
     * 删除机关参公和事业单位等次信息
     * 
     * @param id 机关参公和事业单位等次ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAssessmentGradeById(String id)
    {
        return bizAssessAssessmentGradeMapper.deleteBizAssessAssessmentGradeById(id);
    }
}
