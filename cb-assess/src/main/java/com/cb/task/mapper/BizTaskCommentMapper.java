package com.cb.task.mapper;

import com.cb.task.domain.BizTaskComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务评论Mapper接口
 * 
 * @author yangxin
 * @date 2023-11-11
 */
public interface BizTaskCommentMapper 
{
    /**
     * 查询任务评论
     * 
     * @param id 任务评论ID
     * @return 任务评论
     */
    public BizTaskComment selectBizTaskCommentById(String id);

    /**
     * 查询任务评论列表
     * 
     * @param bizTaskComment 任务评论
     * @return 任务评论集合
     */
    public List<BizTaskComment> selectBizTaskCommentList(BizTaskComment bizTaskComment);

    /**
     * 新增任务评论
     * 
     * @param bizTaskComment 任务评论
     * @return 结果
     */
    public int insertBizTaskComment(BizTaskComment bizTaskComment);

    /**
     * 修改任务评论
     * 
     * @param bizTaskComment 任务评论
     * @return 结果
     */
    public int updateBizTaskComment(BizTaskComment bizTaskComment);

    /**
     * 删除任务评论
     * 
     * @param id 任务评论ID
     * @return 结果
     */
    public int deleteBizTaskCommentById(String id);

    /**
     * 根据任务id删除评论
     * @param taskId
     * @return
     */
    public int deleteBizTaskCommentByTaskId(String taskId);

    /**
     * 批量删除任务评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizTaskCommentByIds(String[] ids);


    public List<BizTaskComment> list4Evaluation(@Param("taskId")String taskId,
                                                @Param("userName")String userName,
                                                @Param("beginTime") String beginTime,
                                                @Param("endTime") String endTime);

}
