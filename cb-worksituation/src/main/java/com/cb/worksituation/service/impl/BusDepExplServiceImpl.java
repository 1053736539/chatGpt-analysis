package com.cb.worksituation.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.worksituation.domain.BusDepExpl;
import com.cb.worksituation.mapper.BusDepExplMapper;
import com.cb.worksituation.service.IBusDepExplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 评分说明Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Service
public class BusDepExplServiceImpl implements IBusDepExplService
{
    @Autowired
    private BusDepExplMapper busDepExplMapper;

    /**
     * 查询评分说明
     *
     * @param id 评分说明ID
     * @return 评分说明
     */
    @Override
    public BusDepExpl selectBusDepExplById(String id)
    {
        return busDepExplMapper.selectBusDepExplById(id);
    }

    /**
     * 查询评分说明列表
     *
     * @param busDepExpl 评分说明
     * @return 评分说明
     */
    @Override
    public List<BusDepExpl> selectBusDepExplList(BusDepExpl busDepExpl)
    {
        return busDepExplMapper.selectBusDepExplList(busDepExpl);
    }

    /**
     * 新增评分说明
     *
     * @param busDepExpl 评分说明
     * @return 结果
     */
    @Override
    public int insertBusDepExpl(BusDepExpl busDepExpl)
    {
        if(StringUtils.isBlank(busDepExpl.getId())) {
            busDepExpl.setId(IdUtils.randomUUID());
        }
        try{
            busDepExpl.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        busDepExpl.setCreateTime(DateUtils.getNowDate());
        return busDepExplMapper.insertBusDepExpl(busDepExpl);
    }


    /**
     * 批量新增评分说明
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepExpl> entities) {
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
            totalInserted +=  busDepExplMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改评分说明
     *
     * @param busDepExpl 评分说明
     * @return 结果
     */
    @Override
    public int updateBusDepExpl(BusDepExpl busDepExpl)
    {
        try{
            busDepExpl.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        busDepExpl.setUpdateTime(DateUtils.getNowDate());
        return busDepExplMapper.updateBusDepExpl(busDepExpl);
    }

    /**
     * 批量删除评分说明
     *
     * @param ids 需要删除的评分说明ID
     * @return 结果
     */
    @Override
    public int deleteBusDepExplByIds(String[] ids)
    {
        return busDepExplMapper.deleteBusDepExplByIds(ids);
    }

    /**
     * 删除评分说明信息
     *
     * @param id 评分说明ID
     * @return 结果
     */
    @Override
    public int deleteBusDepExplById(String id)
    {
        return busDepExplMapper.deleteBusDepExplById(id);
    }
}
