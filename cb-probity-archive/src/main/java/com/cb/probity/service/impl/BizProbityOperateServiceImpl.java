package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityOperateMapper;
import com.cb.probity.domain.BizProbityOperate;
import com.cb.probity.service.IBizProbityOperateService;

/**
 * 廉政档案-19.本人或本人子女操办婚丧嫁娶情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityOperateServiceImpl implements IBizProbityOperateService 
{
    @Autowired
    private BizProbityOperateMapper bizProbityOperateMapper;

    /**
     * 查询廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * 
     * @param id 廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     */
    @Override
    public BizProbityOperate selectBizProbityOperateById(String id)
    {
        return bizProbityOperateMapper.selectBizProbityOperateById(id);
    }

    /**
     * 查询廉政档案-19.本人或本人子女操办婚丧嫁娶情况列表
     * 
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     */
    @Override
    public List<BizProbityOperate> selectBizProbityOperateList(BizProbityOperate bizProbityOperate)
    {
        return bizProbityOperateMapper.selectBizProbityOperateList(bizProbityOperate);
    }

    /**
     * 新增廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * 
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 结果
     */
    @Override
    public int insertBizProbityOperate(BizProbityOperate bizProbityOperate)
    {
        if(StringUtils.isBlank(bizProbityOperate.getId())) {
            bizProbityOperate.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityOperate.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityOperate.setCreateTime(DateUtils.getNowDate());
        return bizProbityOperateMapper.insertBizProbityOperate(bizProbityOperate);
    }


    /**
     * 批量新增廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityOperate> entities) {
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
            totalInserted +=  bizProbityOperateMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * 
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 结果
     */
    @Override
    public int updateBizProbityOperate(BizProbityOperate bizProbityOperate)
    {
        try{
            bizProbityOperate.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityOperate.setUpdateTime(DateUtils.getNowDate());
        return bizProbityOperateMapper.updateBizProbityOperate(bizProbityOperate);
    }

    /**
     * 批量删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * 
     * @param ids 需要删除的廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityOperateByIds(String[] ids)
    {
        return bizProbityOperateMapper.deleteBizProbityOperateByIds(ids);
    }

    /**
     * 删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况信息
     * 
     * @param id 廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityOperateById(String id)
    {
        return bizProbityOperateMapper.deleteBizProbityOperateById(id);
    }
}
