package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessTaskUserTag;

/**
 * 任务与用户关联Mapper接口
 *
 * @author ouyang
 * @date 2023-11-08
 */
public interface BizAssessTaskUserTagMapper {
    /**
     * 查询任务与用户关联
     *
     * @param id 任务与用户关联ID
     * @return 任务与用户关联
     */
    public BizAssessTaskUserTag selectBizAssessTaskUserTagById(String id);

    /**
     * 查询任务与用户关联列表
     *
     * @param bizAssessTaskUserTag 任务与用户关联
     * @return 任务与用户关联集合
     */
    public List<BizAssessTaskUserTag> selectBizAssessTaskUserTagList(BizAssessTaskUserTag bizAssessTaskUserTag);

    /**
     * 新增任务与用户关联
     *
     * @param bizAssessTaskUserTag 任务与用户关联
     * @return 结果
     */
    public int insertBizAssessTaskUserTag(BizAssessTaskUserTag bizAssessTaskUserTag);

    public int batchInsertBizAssessTaskUserTag(List<BizAssessTaskUserTag> list);

    /**
     * 修改任务与用户关联
     *
     * @param bizAssessTaskUserTag 任务与用户关联
     * @return 结果
     */
    public int updateBizAssessTaskUserTag(BizAssessTaskUserTag bizAssessTaskUserTag);

    /**
     * 删除任务与用户关联
     *
     * @param id 任务与用户关联ID
     * @return 结果
     */
    public int deleteBizAssessTaskUserTagById(String id);

    /**
     * 批量删除任务与用户关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessTaskUserTagByIds(String[] ids);

    public List<BizAssessTaskUserTag> selectTaskUserTagListByTaskId(String taskId);

    public int deleteEntityByTaskId(String taskId);
}
