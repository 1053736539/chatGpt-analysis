package com.cb.assess.service.impl;

import java.security.Security;
import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessTaskEvaluateTagMapper;
import com.cb.assess.domain.BizAssessTaskEvaluateTag;
import com.cb.assess.service.IBizAssessTaskEvaluateTagService;

/**
 * 任务用户互评标记Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-17
 */
@Service
public class BizAssessTaskEvaluateTagServiceImpl implements IBizAssessTaskEvaluateTagService {
    @Autowired
    private BizAssessTaskEvaluateTagMapper bizAssessTaskEvaluateTagMapper;

    /**
     * 查询任务用户互评标记
     *
     * @param id 任务用户互评标记ID
     * @return 任务用户互评标记
     */
    @Override
    public BizAssessTaskEvaluateTag selectBizAssessTaskEvaluateTagById(String id) {
        return bizAssessTaskEvaluateTagMapper.selectBizAssessTaskEvaluateTagById(id);
    }

    /**
     * 查询任务用户互评标记列表
     *
     * @param bizAssessTaskEvaluateTag 任务用户互评标记
     * @return 任务用户互评标记
     */
    @Override
    public List<BizAssessTaskEvaluateTag> selectBizAssessTaskEvaluateTagList(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        return bizAssessTaskEvaluateTagMapper.selectBizAssessTaskEvaluateTagList(bizAssessTaskEvaluateTag);
    }

    /**
     * 新增任务用户互评标记
     *
     * @param bizAssessTaskEvaluateTag 任务用户互评标记
     * @return 结果
     */
    @Override
    public int insertBizAssessTaskEvaluateTag(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        bizAssessTaskEvaluateTag.setId(IdUtils.randomUUID());
        bizAssessTaskEvaluateTag.setCreateBy(SecurityUtils.getUsername());
        bizAssessTaskEvaluateTag.setCreateTime(DateUtils.getNowDate());
        return bizAssessTaskEvaluateTagMapper.insertBizAssessTaskEvaluateTag(bizAssessTaskEvaluateTag);
    }

    @Override
    public int batchInsertBizAssessTaskEvaluateTag(List<BizAssessTaskEvaluateTag> list) {
        return bizAssessTaskEvaluateTagMapper.batchInsertBizAssessTaskEvaluateTag(list);
    }

    /**
     * 修改任务用户互评标记
     *
     * @param bizAssessTaskEvaluateTag 任务用户互评标记
     * @return 结果
     */
    @Override
    public int updateBizAssessTaskEvaluateTag(BizAssessTaskEvaluateTag bizAssessTaskEvaluateTag) {
        bizAssessTaskEvaluateTag.setUpdateBy(SecurityUtils.getUsername());
        bizAssessTaskEvaluateTag.setUpdateTime(DateUtils.getNowDate());
        return bizAssessTaskEvaluateTagMapper.updateBizAssessTaskEvaluateTag(bizAssessTaskEvaluateTag);
    }

    /**
     * 批量删除任务用户互评标记
     *
     * @param ids 需要删除的任务用户互评标记ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskEvaluateTagByIds(String[] ids) {
        return bizAssessTaskEvaluateTagMapper.deleteBizAssessTaskEvaluateTagByIds(ids);
    }

    /**
     * 删除任务用户互评标记信息
     *
     * @param id 任务用户互评标记ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskEvaluateTagById(String id) {
        return bizAssessTaskEvaluateTagMapper.deleteBizAssessTaskEvaluateTagById(id);
    }

    @Override
    public List<BizAssessTaskEvaluateTag> selectTaskEvaluateTagListByTaskId(String taskId) {
        return bizAssessTaskEvaluateTagMapper.selectTaskEvaluateTagListByTaskId(taskId);
    }

    @Override
    public int deleteEvaluateTagByTaskId(String taskId) {
        return bizAssessTaskEvaluateTagMapper.deleteEvaluateTagByTaskId(taskId);
    }
}
