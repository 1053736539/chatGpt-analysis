package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityCertificateMapper;
import com.cb.probity.domain.BizProbityCertificate;
import com.cb.probity.service.IBizProbityCertificateService;

/**
 * 廉政档案-6.1本人持有护照、往来港澳台证件情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityCertificateServiceImpl implements IBizProbityCertificateService 
{
    @Autowired
    private BizProbityCertificateMapper bizProbityCertificateMapper;

    /**
     * 查询廉政档案-6.1本人持有护照、往来港澳台证件情况
     * 
     * @param id 廉政档案-6.1本人持有护照、往来港澳台证件情况ID
     * @return 廉政档案-6.1本人持有护照、往来港澳台证件情况
     */
    @Override
    public BizProbityCertificate selectBizProbityCertificateById(String id)
    {
        return bizProbityCertificateMapper.selectBizProbityCertificateById(id);
    }

    /**
     * 查询廉政档案-6.1本人持有护照、往来港澳台证件情况列表
     * 
     * @param bizProbityCertificate 廉政档案-6.1本人持有护照、往来港澳台证件情况
     * @return 廉政档案-6.1本人持有护照、往来港澳台证件情况
     */
    @Override
    public List<BizProbityCertificate> selectBizProbityCertificateList(BizProbityCertificate bizProbityCertificate)
    {
        return bizProbityCertificateMapper.selectBizProbityCertificateList(bizProbityCertificate);
    }

    /**
     * 新增廉政档案-6.1本人持有护照、往来港澳台证件情况
     * 
     * @param bizProbityCertificate 廉政档案-6.1本人持有护照、往来港澳台证件情况
     * @return 结果
     */
    @Override
    public int insertBizProbityCertificate(BizProbityCertificate bizProbityCertificate)
    {
        if(StringUtils.isBlank(bizProbityCertificate.getId())) {
            bizProbityCertificate.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityCertificate.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityCertificate.setCreateTime(DateUtils.getNowDate());
        return bizProbityCertificateMapper.insertBizProbityCertificate(bizProbityCertificate);
    }


    /**
     * 批量新增廉政档案-6.1本人持有护照、往来港澳台证件情况
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityCertificate> entities) {
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
            totalInserted +=  bizProbityCertificateMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-6.1本人持有护照、往来港澳台证件情况
     * 
     * @param bizProbityCertificate 廉政档案-6.1本人持有护照、往来港澳台证件情况
     * @return 结果
     */
    @Override
    public int updateBizProbityCertificate(BizProbityCertificate bizProbityCertificate)
    {
        try{
            bizProbityCertificate.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityCertificate.setUpdateTime(DateUtils.getNowDate());
        return bizProbityCertificateMapper.updateBizProbityCertificate(bizProbityCertificate);
    }

    /**
     * 批量删除廉政档案-6.1本人持有护照、往来港澳台证件情况
     * 
     * @param ids 需要删除的廉政档案-6.1本人持有护照、往来港澳台证件情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityCertificateByIds(String[] ids)
    {
        return bizProbityCertificateMapper.deleteBizProbityCertificateByIds(ids);
    }

    /**
     * 删除廉政档案-6.1本人持有护照、往来港澳台证件情况信息
     * 
     * @param id 廉政档案-6.1本人持有护照、往来港澳台证件情况ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityCertificateById(String id)
    {
        return bizProbityCertificateMapper.deleteBizProbityCertificateById(id);
    }
}
