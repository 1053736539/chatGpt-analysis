package com.cb.seal.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.seal.domain.BizSeal;
import com.cb.seal.mapper.BizSealMapper;
import com.cb.seal.service.IBizSealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公章信息Service业务层处理
 * 
 * @author yangxin
 * @date 2023-12-04
 */
@Service
public class BizSealServiceImpl implements IBizSealService 
{
    @Autowired
    private BizSealMapper bizSealMapper;

    /**
     * 查询公章信息
     * 
     * @param id 公章信息ID
     * @return 公章信息
     */
    @Override
    public BizSeal selectBizSealById(Long id)
    {
        return bizSealMapper.selectBizSealById(id);
    }

    /**
     * 查询公章信息列表
     * 
     * @param bizSeal 公章信息
     * @return 公章信息
     */
    @Override
    public List<BizSeal> selectBizSealList(BizSeal bizSeal)
    {
        return bizSealMapper.selectBizSealList(bizSeal);
    }

    /**
     * 新增公章信息
     * 
     * @param bizSeal 公章信息
     * @return 结果
     */
    @Override
    public int insertBizSeal(BizSeal bizSeal)
    {
        if(StringUtils.isBlank(bizSeal.getCreateBy())){
            try{
                bizSeal.setCreateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }
        bizSeal.setCreateTime(DateUtils.getNowDate());
        return bizSealMapper.insertBizSeal(bizSeal);
    }

    /**
     * 修改公章信息
     * 
     * @param bizSeal 公章信息
     * @return 结果
     */
    @Override
    public int updateBizSeal(BizSeal bizSeal)
    {
        if(StringUtils.isBlank(bizSeal.getUpdateBy())){
            try{
                bizSeal.setUpdateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }
        bizSeal.setUpdateTime(DateUtils.getNowDate());
        return bizSealMapper.updateBizSeal(bizSeal);
    }

    /**
     * 批量删除公章信息
     * 
     * @param ids 需要删除的公章信息ID
     * @return 结果
     */
    @Override
    public int deleteBizSealByIds(Long[] ids)
    {
        return bizSealMapper.deleteBizSealByIds(ids);
    }

    /**
     * 删除公章信息信息
     * 
     * @param id 公章信息ID
     * @return 结果
     */
    @Override
    public int deleteBizSealById(Long id)
    {
        return bizSealMapper.deleteBizSealById(id);
    }
}
