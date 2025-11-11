package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityEnterpriseMapper;
import com.cb.probity.domain.BizProbityEnterprise;
import com.cb.probity.service.IBizProbityEnterpriseService;

/**
 * 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityEnterpriseServiceImpl implements IBizProbityEnterpriseService 
{
    @Autowired
    private BizProbityEnterpriseMapper bizProbityEnterpriseMapper;

    /**
     * 查询廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * 
     * @param id 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况ID
     * @return 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     */
    @Override
    public BizProbityEnterprise selectBizProbityEnterpriseById(String id)
    {
        return bizProbityEnterpriseMapper.selectBizProbityEnterpriseById(id);
    }

    /**
     * 查询廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况列表
     * 
     * @param bizProbityEnterprise 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * @return 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     */
    @Override
    public List<BizProbityEnterprise> selectBizProbityEnterpriseList(BizProbityEnterprise bizProbityEnterprise)
    {
        return bizProbityEnterpriseMapper.selectBizProbityEnterpriseList(bizProbityEnterprise);
    }

    /**
     * 新增廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * 
     * @param bizProbityEnterprise 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * @return 结果
     */
    @Override
    public int insertBizProbityEnterprise(BizProbityEnterprise bizProbityEnterprise)
    {
        if(StringUtils.isBlank(bizProbityEnterprise.getId())) {
            bizProbityEnterprise.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityEnterprise.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityEnterprise.setCreateTime(DateUtils.getNowDate());
        return bizProbityEnterpriseMapper.insertBizProbityEnterprise(bizProbityEnterprise);
    }


    /**
     * 批量新增廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityEnterprise> entities) {
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
            totalInserted +=  bizProbityEnterpriseMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * 
     * @param bizProbityEnterprise 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * @return 结果
     */
    @Override
    public int updateBizProbityEnterprise(BizProbityEnterprise bizProbityEnterprise)
    {
        try{
            bizProbityEnterprise.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityEnterprise.setUpdateTime(DateUtils.getNowDate());
        return bizProbityEnterpriseMapper.updateBizProbityEnterprise(bizProbityEnterprise);
    }

    /**
     * 批量删除廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况
     * 
     * @param ids 需要删除的廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityEnterpriseByIds(String[] ids)
    {
        return bizProbityEnterpriseMapper.deleteBizProbityEnterpriseByIds(ids);
    }

    /**
     * 删除廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况信息
     * 
     * @param id 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityEnterpriseById(String id)
    {
        return bizProbityEnterpriseMapper.deleteBizProbityEnterpriseById(id);
    }
}
