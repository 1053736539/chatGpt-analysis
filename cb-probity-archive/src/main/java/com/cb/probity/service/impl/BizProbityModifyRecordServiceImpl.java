package com.cb.probity.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Date;

import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityModifyRecordMapper;
import com.cb.probity.domain.BizProbityModifyRecord;
import com.cb.probity.service.IBizProbityModifyRecordService;

/**
 * 修改廉政档案记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-26
 */
@Service
public class BizProbityModifyRecordServiceImpl implements IBizProbityModifyRecordService {
    @Autowired
    private BizProbityModifyRecordMapper bizProbityModifyRecordMapper;

    /**
     * 查询修改廉政档案记录
     *
     * @param id 修改廉政档案记录ID
     * @return 修改廉政档案记录
     */
    @Override
    public BizProbityModifyRecord selectBizProbityModifyRecordById(String id) {
        return bizProbityModifyRecordMapper.selectBizProbityModifyRecordById(id);
    }

    /**
     * 查询修改廉政档案记录列表
     *
     * @param bizProbityModifyRecord 修改廉政档案记录
     * @return 修改廉政档案记录
     */
    @Override
    public List<BizProbityModifyRecord> selectBizProbityModifyRecordList(BizProbityModifyRecord bizProbityModifyRecord) {
        return bizProbityModifyRecordMapper.selectBizProbityModifyRecordList(bizProbityModifyRecord);
    }

    /**
     * 新增修改廉政档案记录
     *
     * @param bizProbityModifyRecord 修改廉政档案记录
     * @return 结果
     */
    @Override
    public int insertBizProbityModifyRecord(BizProbityModifyRecord bizProbityModifyRecord) {
        if (StringUtils.isBlank(bizProbityModifyRecord.getId())) {
            bizProbityModifyRecord.setId(IdUtils.randomUUID());
        }
        try {
            bizProbityModifyRecord.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        bizProbityModifyRecord.setCreateTime(DateUtils.getNowDate());
        return bizProbityModifyRecordMapper.insertBizProbityModifyRecord(bizProbityModifyRecord);
    }


    /**
     * 批量新增修改廉政档案记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityModifyRecord> entities) {
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
            totalInserted += bizProbityModifyRecordMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改修改廉政档案记录
     *
     * @param bizProbityModifyRecord 修改廉政档案记录
     * @return 结果
     */
    @Override
    public int updateBizProbityModifyRecord(BizProbityModifyRecord bizProbityModifyRecord) {
        try {
            bizProbityModifyRecord.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        bizProbityModifyRecord.setUpdateTime(DateUtils.getNowDate());
        return bizProbityModifyRecordMapper.updateBizProbityModifyRecord(bizProbityModifyRecord);
    }

    /**
     * 批量删除修改廉政档案记录
     *
     * @param ids 需要删除的修改廉政档案记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityModifyRecordByIds(String[] ids) {
        return bizProbityModifyRecordMapper.deleteBizProbityModifyRecordByIds(ids);
    }

    /**
     * 删除修改廉政档案记录信息
     *
     * @param id 修改廉政档案记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityModifyRecordById(String id) {
        return bizProbityModifyRecordMapper.deleteBizProbityModifyRecordById(id);
    }

    @Override
    public List<BizProbityModifyRecord> searchPrevRecorde(Long userId, String probityId) {
        return bizProbityModifyRecordMapper.searchPrevRecorde(userId, probityId);
    }
}
