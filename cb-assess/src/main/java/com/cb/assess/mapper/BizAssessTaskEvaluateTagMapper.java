package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessTaskEvaluateTag;
import org.apache.ibatis.annotations.Param;

/**
 * 任务用户互评标记Mapper接口
 *
 * @author ouyang
 * @date 2023-11-17
 */
public interface BizAssessTaskEvaluateTagMapper {
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
     * 删除任务用户互评标记
     *
     * @param id 任务用户互评标记ID
     * @return 结果
     */
    public int deleteBizAssessTaskEvaluateTagById(String id);

    /**
     * 批量删除任务用户互评标记
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessTaskEvaluateTagByIds(String[] ids);


    public int changeEvaluateTagStatus(@Param("id") String id, @Param("status") String status);

    public int batchChangeEvaluateTagStatus(@Param("ids") List<String> ids, @Param("status") String status);

    public boolean checkEvaluateComplete(@Param("taskId") String taskId,@Param("deptId") Long deptId);

    public List<BizAssessTaskEvaluateTag> selectTaskEvaluateTagListByTaskId(String taskId);

    public int deleteEvaluateTagByTaskId(String taskId);
}
