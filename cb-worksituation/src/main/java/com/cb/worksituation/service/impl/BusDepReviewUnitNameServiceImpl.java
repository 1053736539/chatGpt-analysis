package com.cb.worksituation.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.cb.common.utils.DateUtils;
import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.domain.BusDepReviewMemberUnit;
import com.cb.worksituation.domain.BusDepReviewUnitName;
import com.cb.worksituation.mapper.BusDepReviewDataMapper;
import com.cb.worksituation.mapper.BusDepReviewMapper;
import com.cb.worksituation.mapper.BusDepReviewUnitNameMapper;
import com.cb.worksituation.service.IBusDepReviewUnitNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import org.springframework.util.CollectionUtils;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-15
 */
@Service
public class BusDepReviewUnitNameServiceImpl implements IBusDepReviewUnitNameService {
    @Autowired
    private BusDepReviewUnitNameMapper busDepReviewUnitNameMapper;

    @Autowired
    private BusDepReviewDataMapper busDepReviewDataMapper;

    @Override
    public List<BusDepReviewUnitName> getBusinessCollaborationUnit(String busDepReviewId) {
        // 查询党建业务协作单元
        List<BusDepReviewUnitName> busDepReviewUnitNames = busDepReviewUnitNameMapper.getBusinessCollaborationUnit();
        if (!CollectionUtils.isEmpty(busDepReviewUnitNames)) {
            List<String> evaluatTargets = busDepReviewUnitNames.stream().flatMap(busDepReviewUnitName -> busDepReviewUnitName.getBusDepReviewMemberUnits().stream()).map(busDepReviewUnitName -> busDepReviewUnitName.getMemberUnit()).collect(Collectors.toList());
            // 获取填报数据
            List<BusDepReviewData> busDepReviewDataList = busDepReviewDataMapper.selectBusDepReviewMemberUnits(busDepReviewId, evaluatTargets);
            for (BusDepReviewUnitName busDepReviewUnitName : busDepReviewUnitNames) {
                List<BusDepReviewMemberUnit> busDepReviewMemberUnits = busDepReviewUnitName.getBusDepReviewMemberUnits();
                // 求和
                BigDecimal total = new BigDecimal(0);
                // 平均分
                BigDecimal avgScore = new BigDecimal(0);
                for (BusDepReviewMemberUnit busDepReviewMemberUnit : busDepReviewMemberUnits) {
                    List<BusDepReviewData> busDepReviewDatas = busDepReviewDataList.stream().filter(busDepReviewData -> busDepReviewData.getEvaluatTarget().equals(busDepReviewMemberUnit.getMemberUnit())).collect(Collectors.toList());
                    BigDecimal reviewScore = busDepReviewDatas.get(0).getReviewScore();
                    busDepReviewMemberUnit.setScore(reviewScore);
                    total = total.add(reviewScore);
                }
                avgScore = total.divide(new BigDecimal(busDepReviewMemberUnits.size()), 2, BigDecimal.ROUND_HALF_UP);
                busDepReviewUnitName.setTotal(total);
                busDepReviewUnitName.setAvgScore(avgScore);
            }
            // 计算平均分排行榜 ->党建业务协作单元加分 取第一名和第二名
            busDepReviewUnitNames.sort(((o1, o2) -> o1.getAvgScore().compareTo(o2.getAvgScore())));
            // 获取评价对象信息
            List<String> firstEvaluatTarget = new ArrayList<>();
            List<String> twoEvaluatTarget = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                BusDepReviewUnitName busDepReviewUnitName = busDepReviewUnitNames.get(i);
                if (i == 0) {
                    firstEvaluatTarget.addAll(busDepReviewUnitName.getBusDepReviewMemberUnits().stream().map(busDepReviewMemberUnit -> busDepReviewMemberUnit.getMemberUnit()).collect(Collectors.toList()));
                } else if (i == 1) {
                    twoEvaluatTarget.addAll(busDepReviewUnitName.getBusDepReviewMemberUnits().stream().map(busDepReviewMemberUnit -> busDepReviewMemberUnit.getMemberUnit()).collect(Collectors.toList()));
                }
            }
            for (BusDepReviewData busDepReviewData : busDepReviewDataList) {
                BusDepReviewData updatedBusDepReviewData = new BusDepReviewData();
                updatedBusDepReviewData.setId(busDepReviewData.getId());
                String evaluatTarget = busDepReviewData.getEvaluatTarget();
                if (firstEvaluatTarget.contains(evaluatTarget)) {
                    updatedBusDepReviewData.setBusUnitScore(new BigDecimal("5"));
                } else if (twoEvaluatTarget.contains(evaluatTarget)) {
                    updatedBusDepReviewData.setBusUnitScore(new BigDecimal("3"));
                } else {
                    updatedBusDepReviewData.setBusUnitScore(new BigDecimal("0"));
                }
                busDepReviewDataMapper.updateBusDepReviewData(updatedBusDepReviewData);
            }

        }


        return busDepReviewUnitNames;
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BusDepReviewUnitName selectBusDepReviewUnitNameById(String id) {
        return busDepReviewUnitNameMapper.selectBusDepReviewUnitNameById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BusDepReviewUnitName> selectBusDepReviewUnitNameList(BusDepReviewUnitName busDepReviewUnitName) {
        return busDepReviewUnitNameMapper.selectBusDepReviewUnitNameList(busDepReviewUnitName);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBusDepReviewUnitName(BusDepReviewUnitName busDepReviewUnitName) {
        if (StringUtils.isBlank(busDepReviewUnitName.getId())) {
            busDepReviewUnitName.setId(IdUtils.randomUUID());
        }
        return busDepReviewUnitNameMapper.insertBusDepReviewUnitName(busDepReviewUnitName);
    }


    /**
     * 批量新增【请填写功能名称】
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepReviewUnitName> entities) {
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
            totalInserted += busDepReviewUnitNameMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改【请填写功能名称】
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBusDepReviewUnitName(BusDepReviewUnitName busDepReviewUnitName) {
        return busDepReviewUnitNameMapper.updateBusDepReviewUnitName(busDepReviewUnitName);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewUnitNameByIds(String[] ids) {
        return busDepReviewUnitNameMapper.deleteBusDepReviewUnitNameByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewUnitNameById(String id) {
        return busDepReviewUnitNameMapper.deleteBusDepReviewUnitNameById(id);
    }
}
