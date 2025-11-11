package com.cb.assess.service.impl;

import java.util.Date;
import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessTaskUserTagMapper;
import com.cb.assess.domain.BizAssessTaskUserTag;
import com.cb.assess.service.IBizAssessTaskUserTagService;

/**
 * 任务与用户关联Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-08
 */
@Service
public class BizAssessTaskUserTagServiceImpl implements IBizAssessTaskUserTagService {
    @Autowired
    private BizAssessTaskUserTagMapper bizAssessTaskUserTagMapper;

    /**
     * 查询任务与用户关联
     *
     * @param id 任务与用户关联ID
     * @return 任务与用户关联
     */
    @Override
    public BizAssessTaskUserTag selectBizAssessTaskUserTagById(String id) {
        return bizAssessTaskUserTagMapper.selectBizAssessTaskUserTagById(id);
    }

    /**
     * 查询任务与用户关联列表
     *
     * @param bizAssessTaskUserTag 任务与用户关联
     * @return 任务与用户关联
     */
    @Override
    public List<BizAssessTaskUserTag> selectBizAssessTaskUserTagList(BizAssessTaskUserTag bizAssessTaskUserTag) {
        return bizAssessTaskUserTagMapper.selectBizAssessTaskUserTagList(bizAssessTaskUserTag);
    }

    /**
     * 新增任务与用户关联
     *
     * @param bizAssessTaskUserTag 任务与用户关联
     * @return 结果
     */
    @Override
    public int insertBizAssessTaskUserTag(BizAssessTaskUserTag bizAssessTaskUserTag) {
        bizAssessTaskUserTag.setId(IdUtils.randomUUID());
        bizAssessTaskUserTag.setCreateBy(SecurityUtils.getUsername());
        bizAssessTaskUserTag.setCreateTime(DateUtils.getNowDate());
        return bizAssessTaskUserTagMapper.insertBizAssessTaskUserTag(bizAssessTaskUserTag);
    }

    @Override
    public int batchInsertBizAssessTaskUserTag(List<BizAssessTaskUserTag> list) {
        return bizAssessTaskUserTagMapper.batchInsertBizAssessTaskUserTag(list);
    }

    /**
     * 修改任务与用户关联
     *
     * @param bizAssessTaskUserTag 任务与用户关联
     * @return 结果
     */
    @Override
    public int updateBizAssessTaskUserTag(BizAssessTaskUserTag bizAssessTaskUserTag) {
        bizAssessTaskUserTag.setUpdateBy(SecurityUtils.getUsername());
        bizAssessTaskUserTag.setUpdateTime(DateUtils.getNowDate());
        return bizAssessTaskUserTagMapper.updateBizAssessTaskUserTag(bizAssessTaskUserTag);
    }

    /**
     * 批量删除任务与用户关联
     *
     * @param ids 需要删除的任务与用户关联ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskUserTagByIds(String[] ids) {
        return bizAssessTaskUserTagMapper.deleteBizAssessTaskUserTagByIds(ids);
    }

    /**
     * 删除任务与用户关联信息
     *
     * @param id 任务与用户关联ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskUserTagById(String id) {
        return bizAssessTaskUserTagMapper.deleteBizAssessTaskUserTagById(id);
    }


    @Override
    public List<BizAssessTaskUserTag> selectTaskUserTagListByTaskId(String taskId) {
        return bizAssessTaskUserTagMapper.selectTaskUserTagListByTaskId(taskId);
    }

    @Override
    public int deleteEntityByTaskId(String taskId) {
        return bizAssessTaskUserTagMapper.deleteEntityByTaskId(taskId);
    }
}
