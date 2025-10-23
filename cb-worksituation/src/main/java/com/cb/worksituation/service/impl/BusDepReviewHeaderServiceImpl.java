package com.cb.worksituation.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.worksituation.domain.BusDepReviewHeader;
import com.cb.worksituation.mapper.BusDepReviewHeaderMapper;
import com.cb.worksituation.service.IBusDepReviewHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 部门评分-头Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Service
public class BusDepReviewHeaderServiceImpl implements IBusDepReviewHeaderService
{
    @Autowired
    private BusDepReviewHeaderMapper busDepReviewHeaderMapper;

    /**
     * 查询部门评分-头
     *
     * @param id 部门评分-头ID
     * @return 部门评分-头
     */
    @Override
    public BusDepReviewHeader selectBusDepReviewHeaderById(String id)
    {
        return busDepReviewHeaderMapper.selectBusDepReviewHeaderById(id);
    }

    /**
     * 查询部门评分-头列表
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 部门评分-头
     */
    @Override
    public List<BusDepReviewHeader> selectBusDepReviewHeaderList(BusDepReviewHeader busDepReviewHeader)
    {
        return busDepReviewHeaderMapper.selectBusDepReviewHeaderList(busDepReviewHeader);
    }

    /**
     * 新增部门评分-头
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 结果
     */
    @Override
    public int insertBusDepReviewHeader(BusDepReviewHeader busDepReviewHeader)
    {
        if(StringUtils.isBlank(busDepReviewHeader.getId())) {
            busDepReviewHeader.setId(IdUtils.randomUUID());
        }
        try{
            busDepReviewHeader.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        busDepReviewHeader.setCreateTime(DateUtils.getNowDate());
        return busDepReviewHeaderMapper.insertBusDepReviewHeader(busDepReviewHeader);
    }


    /**
     * 批量新增部门评分-头
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepReviewHeader> entities) {
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
            totalInserted +=  busDepReviewHeaderMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改部门评分-头
     *
     * @param busDepReviewHeader 部门评分-头
     * @return 结果
     */
    @Override
    public int updateBusDepReviewHeader(BusDepReviewHeader busDepReviewHeader)
    {
        try{
            busDepReviewHeader.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        busDepReviewHeader.setUpdateTime(DateUtils.getNowDate());
        return busDepReviewHeaderMapper.updateBusDepReviewHeader(busDepReviewHeader);
    }

    /**
     * 批量删除部门评分-头
     *
     * @param ids 需要删除的部门评分-头ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewHeaderByIds(String[] ids)
    {
        return busDepReviewHeaderMapper.deleteBusDepReviewHeaderByIds(ids);
    }

    /**
     * 删除部门评分-头信息
     *
     * @param id 部门评分-头ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewHeaderById(String id)
    {
        return busDepReviewHeaderMapper.deleteBusDepReviewHeaderById(id);
    }
}
