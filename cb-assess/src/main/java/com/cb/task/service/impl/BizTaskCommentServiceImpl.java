package com.cb.task.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.task.domain.BizTaskComment;
import com.cb.task.mapper.BizTaskCommentMapper;
import com.cb.task.service.IBizTaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务评论Service业务层处理
 * 
 * @author yangxin
 * @date 2023-11-11
 */
@Service
public class BizTaskCommentServiceImpl implements IBizTaskCommentService 
{
    @Autowired
    private BizTaskCommentMapper bizTaskCommentMapper;

    /**
     * 查询任务评论
     * 
     * @param id 任务评论ID
     * @return 任务评论
     */
    @Override
    public BizTaskComment selectBizTaskCommentById(String id)
    {
        return bizTaskCommentMapper.selectBizTaskCommentById(id);
    }

    /**
     * 查询任务评论列表
     * 
     * @param bizTaskComment 任务评论
     * @return 任务评论
     */
    @Override
    public List<BizTaskComment> selectBizTaskCommentList(BizTaskComment bizTaskComment)
    {
        return bizTaskCommentMapper.selectBizTaskCommentList(bizTaskComment);
    }

    /**
     * 新增任务评论
     * 
     * @param bizTaskComment 任务评论
     * @return 结果
     */
    @Override
    public int insertBizTaskComment(BizTaskComment bizTaskComment)
    {
        if(StringUtils.isBlank(bizTaskComment.getId())){
            bizTaskComment.setId(IdUtils.randomUUID());
        }
        bizTaskComment.setCreateTime(DateUtils.getNowDate());
        try{
            bizTaskComment.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        return bizTaskCommentMapper.insertBizTaskComment(bizTaskComment);
    }

    /**
     * 修改任务评论
     * 
     * @param bizTaskComment 任务评论
     * @return 结果
     */
    @Override
    public int updateBizTaskComment(BizTaskComment bizTaskComment)
    {
        return bizTaskCommentMapper.updateBizTaskComment(bizTaskComment);
    }

    /**
     * 批量删除任务评论
     * 
     * @param ids 需要删除的任务评论ID
     * @return 结果
     */
    @Override
    public int deleteBizTaskCommentByIds(String[] ids)
    {
        return bizTaskCommentMapper.deleteBizTaskCommentByIds(ids);
    }

    /**
     * 删除任务评论信息
     * 
     * @param id 任务评论ID
     * @return 结果
     */
    @Override
    public int deleteBizTaskCommentById(String id)
    {
        return bizTaskCommentMapper.deleteBizTaskCommentById(id);
    }

    @Override
    public int deleteBizTaskCommentByTaskId(String taskId) {
        return bizTaskCommentMapper.deleteBizTaskCommentByTaskId(taskId);
    }

    @Override
    public List<BizTaskComment> list4Evaluation(String taskId, String userName, String beginTime, String endTime) {
        return bizTaskCommentMapper.list4Evaluation(taskId, userName, beginTime, endTime);
    }
}
