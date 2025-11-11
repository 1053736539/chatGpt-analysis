package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityMigrateMapper;
import com.cb.probity.domain.BizProbityMigrate;
import com.cb.probity.service.IBizProbityMigrateService;

/**
 * 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityMigrateServiceImpl implements IBizProbityMigrateService 
{
    @Autowired
    private BizProbityMigrateMapper bizProbityMigrateMapper;

    /**
     * 查询廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * 
     * @param id 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况ID
     * @return 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     */
    @Override
    public BizProbityMigrate selectBizProbityMigrateById(String id)
    {
        return bizProbityMigrateMapper.selectBizProbityMigrateById(id);
    }

    /**
     * 查询廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况列表
     * 
     * @param bizProbityMigrate 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * @return 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     */
    @Override
    public List<BizProbityMigrate> selectBizProbityMigrateList(BizProbityMigrate bizProbityMigrate)
    {
        return bizProbityMigrateMapper.selectBizProbityMigrateList(bizProbityMigrate);
    }

    /**
     * 新增廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * 
     * @param bizProbityMigrate 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * @return 结果
     */
    @Override
    public int insertBizProbityMigrate(BizProbityMigrate bizProbityMigrate)
    {
        if(StringUtils.isBlank(bizProbityMigrate.getId())) {
            bizProbityMigrate.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityMigrate.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityMigrate.setCreateTime(DateUtils.getNowDate());
        return bizProbityMigrateMapper.insertBizProbityMigrate(bizProbityMigrate);
    }


    /**
     * 批量新增廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityMigrate> entities) {
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
            totalInserted +=  bizProbityMigrateMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * 
     * @param bizProbityMigrate 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * @return 结果
     */
    @Override
    public int updateBizProbityMigrate(BizProbityMigrate bizProbityMigrate)
    {
        try{
            bizProbityMigrate.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityMigrate.setUpdateTime(DateUtils.getNowDate());
        return bizProbityMigrateMapper.updateBizProbityMigrate(bizProbityMigrate);
    }

    /**
     * 批量删除廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * 
     * @param ids 需要删除的廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityMigrateByIds(String[] ids)
    {
        return bizProbityMigrateMapper.deleteBizProbityMigrateByIds(ids);
    }

    /**
     * 删除廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况信息
     * 
     * @param id 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityMigrateById(String id)
    {
        return bizProbityMigrateMapper.deleteBizProbityMigrateById(id);
    }
}
