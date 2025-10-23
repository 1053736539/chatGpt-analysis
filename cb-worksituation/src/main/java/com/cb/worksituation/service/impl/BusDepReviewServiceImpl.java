package com.cb.worksituation.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.worksituation.domain.BusDepReview;
import com.cb.worksituation.mapper.BusDepReviewMapper;
import com.cb.worksituation.service.IBusDepReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 部门评分Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Service
public class BusDepReviewServiceImpl implements IBusDepReviewService
{
    @Autowired
    private BusDepReviewMapper busDepReviewMapper;

    /**
     * 查询部门评分
     *
     * @param id 部门评分ID
     * @return 部门评分
     */
    @Override
    public BusDepReview selectBusDepReviewById(String id)
    {
        return busDepReviewMapper.selectBusDepReviewById(id);
    }

    /**
     * 查询部门评分列表
     *
     * @param busDepReview 部门评分
     * @return 部门评分
     */
    @Override
    public List<BusDepReview> selectBusDepReviewList(BusDepReview busDepReview)
    {
        return busDepReviewMapper.selectBusDepReviewList(busDepReview);
    }

    @Override
    public BusDepReview getDisplayHeaderList(String id) {
        return busDepReviewMapper.getDisplayHeaderList(id);
    }

    /**
     * 新增部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    @Override
    public int insertBusDepReview(BusDepReview busDepReview)
    {
        if(StringUtils.isBlank(busDepReview.getId())) {
            busDepReview.setId(IdUtils.randomUUID());
        }
        try{
            busDepReview.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        busDepReview.setCreateTime(DateUtils.getNowDate());
        return busDepReviewMapper.insertBusDepReview(busDepReview);
    }


    /**
     * 批量新增部门评分
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepReview> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
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
            totalInserted +=  busDepReviewMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    @Override
    public int updateBusDepReview(BusDepReview busDepReview)
    {
        try{
            busDepReview.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        busDepReview.setUpdateTime(DateUtils.getNowDate());
        return busDepReviewMapper.updateBusDepReview(busDepReview);
    }

    /**
     * 批量删除部门评分
     *
     * @param ids 需要删除的部门评分ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewByIds(String[] ids)
    {
        return busDepReviewMapper.deleteBusDepReviewByIds(ids);
    }

    /**
     * 删除部门评分信息
     *
     * @param id 部门评分ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewById(String id)
    {
        return busDepReviewMapper.deleteBusDepReviewById(id);
    }
}
