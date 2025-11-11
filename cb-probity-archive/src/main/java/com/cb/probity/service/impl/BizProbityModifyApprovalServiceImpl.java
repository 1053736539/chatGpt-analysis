package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;

import com.cb.common.utils.DateUtils;
import com.cb.probity.mapper.BizProbityMapper;
import com.cb.probity.service.IBizProbityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityModifyApprovalMapper;
import com.cb.probity.domain.BizProbityModifyApproval;
import com.cb.probity.service.IBizProbityModifyApprovalService;

import javax.annotation.Resource;

/**
 * 申请修改廉政档案的记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityModifyApprovalServiceImpl implements IBizProbityModifyApprovalService {
    @Resource
    private BizProbityModifyApprovalMapper bizProbityModifyApprovalMapper;
    @Resource
    private IBizProbityService bizProbityService;

    /**
     * 查询申请修改廉政档案的记录
     *
     * @param id 申请修改廉政档案的记录ID
     * @return 申请修改廉政档案的记录
     */
    @Override
    public BizProbityModifyApproval selectBizProbityModifyApprovalById(String id) {
        return bizProbityModifyApprovalMapper.selectBizProbityModifyApprovalById(id);
    }

    /**
     * 查询申请修改廉政档案的记录列表
     *
     * @param bizProbityModifyApproval 申请修改廉政档案的记录
     * @return 申请修改廉政档案的记录
     */
    @Override
    public List<BizProbityModifyApproval> selectBizProbityModifyApprovalList(BizProbityModifyApproval bizProbityModifyApproval) {
        return bizProbityModifyApprovalMapper.selectBizProbityModifyApprovalList(bizProbityModifyApproval);
    }

    /**
     * 新增申请修改廉政档案的记录
     *
     * @param bizProbityModifyApproval 申请修改廉政档案的记录
     * @return 结果
     */
    @Override
    public int insertBizProbityModifyApproval(BizProbityModifyApproval bizProbityModifyApproval) {
        if (StringUtils.isBlank(bizProbityModifyApproval.getId())) {
            bizProbityModifyApproval.setId(IdUtils.randomUUID());
        }
        try {
            bizProbityModifyApproval.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        bizProbityModifyApproval.setCreateTime(DateUtils.getNowDate());
        return bizProbityModifyApprovalMapper.insertBizProbityModifyApproval(bizProbityModifyApproval);
    }


    /**
     * 批量新增申请修改廉政档案的记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityModifyApproval> entities) {
        if (null == entities || entities.isEmpty()) {
            return 0;
        }
        String userName = null;
        try {
            userName = SecurityUtils.getUsername();
        } catch (Exception e) {
        }
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted += bizProbityModifyApprovalMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改申请修改廉政档案的记录
     *
     * @param bizProbityModifyApproval 申请修改廉政档案的记录
     * @return 结果
     */
    @Override
    public int updateBizProbityModifyApproval(BizProbityModifyApproval bizProbityModifyApproval) {
        try {
            bizProbityModifyApproval.setUpdateBy(SecurityUtils.getUsername());
            bizProbityModifyApproval.setApprovalTime(DateUtils.getNowDate());
        } catch (Exception e) {
        }
        bizProbityModifyApproval.setUpdateTime(DateUtils.getNowDate());
        //根据审批状态修改档案状态
        if (bizProbityModifyApproval.getStatus() == 2) {
            bizProbityService.updateConfirmStatus(bizProbityModifyApproval.getProbityId(), "3");
        } else if (bizProbityModifyApproval.getStatus() == 3) {
            bizProbityService.updateConfirmStatus(bizProbityModifyApproval.getProbityId(), "2");
        }
        return bizProbityModifyApprovalMapper.updateBizProbityModifyApproval(bizProbityModifyApproval);
    }

    /**
     * 批量删除申请修改廉政档案的记录
     *
     * @param ids 需要删除的申请修改廉政档案的记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityModifyApprovalByIds(String[] ids) {
        return bizProbityModifyApprovalMapper.deleteBizProbityModifyApprovalByIds(ids);
    }

    /**
     * 删除申请修改廉政档案的记录信息
     *
     * @param id 申请修改廉政档案的记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityModifyApprovalById(String id) {
        return bizProbityModifyApprovalMapper.deleteBizProbityModifyApprovalById(id);
    }
}
