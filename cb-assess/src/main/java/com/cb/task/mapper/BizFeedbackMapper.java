package com.cb.task.mapper;

import java.util.List;
import com.cb.task.domain.BizFeedback;

/**
 * 使用反馈Mapper接口
 * 
 * @author yangxin
 * @date 2024-01-09
 */
public interface BizFeedbackMapper 
{
    /**
     * 查询使用反馈
     * 
     * @param id 使用反馈ID
     * @return 使用反馈
     */
    public BizFeedback selectBizFeedbackById(Integer id);

    /**
     * 查询使用反馈列表
     * 
     * @param bizFeedback 使用反馈
     * @return 使用反馈集合
     */
    public List<BizFeedback> selectBizFeedbackList(BizFeedback bizFeedback);

    /**
     * 新增使用反馈
     * 
     * @param bizFeedback 使用反馈
     * @return 结果
     */
    public int insertBizFeedback(BizFeedback bizFeedback);

    /**
     * 修改使用反馈
     * 
     * @param bizFeedback 使用反馈
     * @return 结果
     */
    public int updateBizFeedback(BizFeedback bizFeedback);

    /**
     * 删除使用反馈
     * 
     * @param id 使用反馈ID
     * @return 结果
     */
    public int deleteBizFeedbackById(Integer id);

    /**
     * 批量删除使用反馈
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizFeedbackByIds(Integer[] ids);
}
