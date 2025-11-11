package com.cb.task.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.task.domain.BizFeedback;
import com.cb.task.mapper.BizFeedbackMapper;
import com.cb.task.service.IBizFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 使用反馈Service业务层处理
 * 
 * @author yangxin
 * @date 2024-01-09
 */
@Service
public class BizFeedbackServiceImpl implements IBizFeedbackService 
{
    @Autowired
    private BizFeedbackMapper bizFeedbackMapper;

    /**
     * 查询使用反馈
     * 
     * @param id 使用反馈ID
     * @return 使用反馈
     */
    @Override
    public BizFeedback selectBizFeedbackById(Integer id)
    {
        return bizFeedbackMapper.selectBizFeedbackById(id);
    }

    /**
     * 查询使用反馈列表
     * 
     * @param bizFeedback 使用反馈
     * @return 使用反馈
     */
    @Override
    public List<BizFeedback> selectBizFeedbackList(BizFeedback bizFeedback)
    {
        return bizFeedbackMapper.selectBizFeedbackList(bizFeedback);
    }

    /**
     * 新增使用反馈
     * 
     * @param bizFeedback 使用反馈
     * @return 结果
     */
    @Override
    public int insertBizFeedback(BizFeedback bizFeedback)
    {
        bizFeedback.setCreateTime(DateUtils.getNowDate());
        try{
            bizFeedback.setCreateBy(SecurityUtils.getUsername());
        }catch (Exception e){}
        return bizFeedbackMapper.insertBizFeedback(bizFeedback);
    }

    /**
     * 修改使用反馈
     * 
     * @param bizFeedback 使用反馈
     * @return 结果
     */
    @Override
    public int updateBizFeedback(BizFeedback bizFeedback)
    {
        bizFeedback.setUpdateTime(DateUtils.getNowDate());
        try{
            bizFeedback.setUpdateBy(SecurityUtils.getUsername());
        }catch (Exception e){}
        return bizFeedbackMapper.updateBizFeedback(bizFeedback);
    }

    /**
     * 批量删除使用反馈
     * 
     * @param ids 需要删除的使用反馈ID
     * @return 结果
     */
    @Override
    public int deleteBizFeedbackByIds(Integer[] ids)
    {
        return bizFeedbackMapper.deleteBizFeedbackByIds(ids);
    }

    /**
     * 删除使用反馈信息
     * 
     * @param id 使用反馈ID
     * @return 结果
     */
    @Override
    public int deleteBizFeedbackById(Integer id)
    {
        return bizFeedbackMapper.deleteBizFeedbackById(id);
    }
}
