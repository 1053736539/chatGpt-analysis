package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessTaskEvaluateTag;

/**
 * 任务用户互评标记Service接口
 *
 * @author ouyang
 * @date 2023-11-17
 */
public interface IBizAssessTaskEvaluateTagService {
    /**
     * 查询任务用户互评标记
     *
     * @param id 任务用户互评标记ID
     * @return 任务用户互评标记
     */
    public BizAssessTaskEvaluateTag selectBizAssessTaskEvaluateTagById(String id);

    /**
     * 查询任务用户互评标记列表
     *
     * @param bizAssessTaskEvaluateTag 任务用户互评标记
     * @return 任务用户互评标记集合
     */
    public List<BizAssessTaskEvaluateTag> selectBizAssessTaskEvaluateTagList(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag);

    /**
     * 新增任务用户互评标记
     *
     * @param bizAssessTaskEvaluateTag 任务用户互评标记
     * @return 结果
     */
    public int insertBizAssessTaskEvaluateTag(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag);

    public int batchInsertBizAssessTaskEvaluateTag(List<BizAssessTaskEvaluateTag> list);

    /**
     * 修改任务用户互评标记
     *
     * @param bizAssessTaskEvaluateTag 任务用户互评标记
     * @return 结果
     */
    public int updateBizAssessTaskEvaluateTag(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag);

    /**
     * 批量删除任务用户互评标记
     *
     * @param ids 需要删除的任务用户互评标记ID
     * @return 结果
     */
    public int deleteBizAssessTaskEvaluateTagByIds(String[] ids);

    /**
     * 删除任务用户互评标记信息
     *
     * @param id 任务用户互评标记ID
     * @return 结果
     */
    public int deleteBizAssessTaskEvaluateTagById(String id);


    public List<BizAssessTaskEvaluateTag> selectTaskEvaluateTagListByTaskId(String taskId);


    public int deleteEvaluateTagByTaskId(String taskId);
}
