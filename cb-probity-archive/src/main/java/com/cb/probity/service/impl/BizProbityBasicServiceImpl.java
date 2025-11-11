package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;

import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityBasicMapper;
import com.cb.probity.domain.BizProbityBasic;
import com.cb.probity.service.IBizProbityBasicService;

/**
 * 廉政档案-1.报告人基本情况Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityBasicServiceImpl implements IBizProbityBasicService {
    @Autowired
    private BizProbityBasicMapper bizProbityBasicMapper;

    /**
     * 查询廉政档案-1.报告人基本情况
     *
     * @param id 廉政档案-1.报告人基本情况ID
     * @return 廉政档案-1.报告人基本情况
     */
    @Override
    public BizProbityBasic selectBizProbityBasicById(String id) {
        return bizProbityBasicMapper.selectBizProbityBasicById(id);
    }

    /**
     * 查询廉政档案-1.报告人基本情况列表
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 廉政档案-1.报告人基本情况
     */
    @Override
    public List<BizProbityBasic> selectBizProbityBasicList(BizProbityBasic bizProbityBasic) {
        return bizProbityBasicMapper.selectBizProbityBasicList(bizProbityBasic);
    }

    /**
     * 新增廉政档案-1.报告人基本情况
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 结果
     */
    @Override
    public int insertBizProbityBasic(BizProbityBasic bizProbityBasic) {
        if (StringUtils.isBlank(bizProbityBasic.getId())) {
            bizProbityBasic.setId(IdUtils.randomUUID());
        }
        try {
            bizProbityBasic.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        bizProbityBasic.setCreateTime(DateUtils.getNowDate());
        return bizProbityBasicMapper.insertBizProbityBasic(bizProbityBasic);
    }


    /**
     * 批量新增廉政档案-1.报告人基本情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityBasic> entities) {
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
            totalInserted += bizProbityBasicMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-1.报告人基本情况
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 结果
     */
    @Override
    public int updateBizProbityBasic(BizProbityBasic bizProbityBasic) {
        try {
            bizProbityBasic.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        bizProbityBasic.setUpdateTime(DateUtils.getNowDate());
        return bizProbityBasicMapper.updateBizProbityBasic(bizProbityBasic);
    }

    /**
     * 批量删除廉政档案-1.报告人基本情况
     *
     * @param ids 需要删除的廉政档案-1.报告人基本情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityBasicByIds(String[] ids) {
        return bizProbityBasicMapper.deleteBizProbityBasicByIds(ids);
    }

    /**
     * 删除廉政档案-1.报告人基本情况信息
     *
     * @param id 廉政档案-1.报告人基本情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityBasicById(String id) {
        return bizProbityBasicMapper.deleteBizProbityBasicById(id);
    }
}
