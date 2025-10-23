package com.cb.worksituation.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.worksituation.domain.BusDepReviewMemberUnit;
import com.cb.worksituation.mapper.BusDepReviewMemberUnitMapper;
import com.cb.worksituation.service.IBusDepReviewMemberUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-15
 */
@Service
public class BusDepReviewMemberUnitServiceImpl implements IBusDepReviewMemberUnitService {
    @Autowired
    private BusDepReviewMemberUnitMapper busDepReviewMemberUnitMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BusDepReviewMemberUnit selectBusDepReviewMemberUnitById(String id) {
        return busDepReviewMemberUnitMapper.selectBusDepReviewMemberUnitById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busDepReviewMemberUnit 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BusDepReviewMemberUnit> selectBusDepReviewMemberUnitList(BusDepReviewMemberUnit busDepReviewMemberUnit) {
        return busDepReviewMemberUnitMapper.selectBusDepReviewMemberUnitList(busDepReviewMemberUnit);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param busDepReviewMemberUnit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBusDepReviewMemberUnit(BusDepReviewMemberUnit busDepReviewMemberUnit) {
        if (StringUtils.isBlank(busDepReviewMemberUnit.getId())) {
            busDepReviewMemberUnit.setId(IdUtils.randomUUID());
        }
        return busDepReviewMemberUnitMapper.insertBusDepReviewMemberUnit(busDepReviewMemberUnit);
    }


    /**
     * 批量新增【请填写功能名称】
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepReviewMemberUnit> entities) {
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
            totalInserted += busDepReviewMemberUnitMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改【请填写功能名称】
     *
     * @param busDepReviewMemberUnit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBusDepReviewMemberUnit(BusDepReviewMemberUnit busDepReviewMemberUnit) {
        return busDepReviewMemberUnitMapper.updateBusDepReviewMemberUnit(busDepReviewMemberUnit);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewMemberUnitByIds(String[] ids) {
        return busDepReviewMemberUnitMapper.deleteBusDepReviewMemberUnitByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewMemberUnitById(String id) {
        return busDepReviewMemberUnitMapper.deleteBusDepReviewMemberUnitById(id);
    }
}
