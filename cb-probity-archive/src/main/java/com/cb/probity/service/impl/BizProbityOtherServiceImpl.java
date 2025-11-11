package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityOtherMapper;
import com.cb.probity.domain.BizProbityOther;
import com.cb.probity.service.IBizProbityOtherService;

/**
 * 廉政档案-20.个人认为需要报告的其他事项Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityOtherServiceImpl implements IBizProbityOtherService 
{
    @Autowired
    private BizProbityOtherMapper bizProbityOtherMapper;

    /**
     * 查询廉政档案-20.个人认为需要报告的其他事项
     * 
     * @param id 廉政档案-20.个人认为需要报告的其他事项ID
     * @return 廉政档案-20.个人认为需要报告的其他事项
     */
    @Override
    public BizProbityOther selectBizProbityOtherById(String id)
    {
        return bizProbityOtherMapper.selectBizProbityOtherById(id);
    }

    /**
     * 查询廉政档案-20.个人认为需要报告的其他事项列表
     * 
     * @param bizProbityOther 廉政档案-20.个人认为需要报告的其他事项
     * @return 廉政档案-20.个人认为需要报告的其他事项
     */
    @Override
    public List<BizProbityOther> selectBizProbityOtherList(BizProbityOther bizProbityOther)
    {
        return bizProbityOtherMapper.selectBizProbityOtherList(bizProbityOther);
    }

    /**
     * 新增廉政档案-20.个人认为需要报告的其他事项
     * 
     * @param bizProbityOther 廉政档案-20.个人认为需要报告的其他事项
     * @return 结果
     */
    @Override
    public int insertBizProbityOther(BizProbityOther bizProbityOther)
    {
        if(StringUtils.isBlank(bizProbityOther.getId())) {
            bizProbityOther.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityOther.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityOther.setCreateTime(DateUtils.getNowDate());
        return bizProbityOtherMapper.insertBizProbityOther(bizProbityOther);
    }


    /**
     * 批量新增廉政档案-20.个人认为需要报告的其他事项
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityOther> entities) {
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
            totalInserted +=  bizProbityOtherMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-20.个人认为需要报告的其他事项
     * 
     * @param bizProbityOther 廉政档案-20.个人认为需要报告的其他事项
     * @return 结果
     */
    @Override
    public int updateBizProbityOther(BizProbityOther bizProbityOther)
    {
        try{
            bizProbityOther.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityOther.setUpdateTime(DateUtils.getNowDate());
        return bizProbityOtherMapper.updateBizProbityOther(bizProbityOther);
    }

    /**
     * 批量删除廉政档案-20.个人认为需要报告的其他事项
     * 
     * @param ids 需要删除的廉政档案-20.个人认为需要报告的其他事项ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityOtherByIds(String[] ids)
    {
        return bizProbityOtherMapper.deleteBizProbityOtherByIds(ids);
    }

    /**
     * 删除廉政档案-20.个人认为需要报告的其他事项信息
     * 
     * @param id 廉政档案-20.个人认为需要报告的其他事项ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityOtherById(String id)
    {
        return bizProbityOtherMapper.deleteBizProbityOtherById(id);
    }
}
