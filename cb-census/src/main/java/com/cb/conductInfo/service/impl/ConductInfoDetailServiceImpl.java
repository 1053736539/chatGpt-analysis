package com.cb.conductInfo.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.conductInfo.domain.ConductInfoDetail;
import com.cb.conductInfo.mapper.ConductInfoDetailMapper;
import com.cb.conductInfo.service.IConductInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 普查员宣传资料库-详情Service业务层处理
 * 
 * @author yangxin
 * @date 2023-10-20
 */
@Service
public class ConductInfoDetailServiceImpl implements IConductInfoDetailService 
{
    @Autowired
    private ConductInfoDetailMapper conductInfoDetailMapper;

    /**
     * 查询普查员宣传资料库-详情
     * 
     * @param id 普查员宣传资料库-详情ID
     * @return 普查员宣传资料库-详情
     */
    @Override
    public ConductInfoDetail selectConductInfoDetailById(String id)
    {
        return conductInfoDetailMapper.selectConductInfoDetailById(id);
    }

    /**
     * 查询普查员宣传资料库-详情列表
     * 
     * @param conductInfoDetail 普查员宣传资料库-详情
     * @return 普查员宣传资料库-详情
     */
    @Override
    public List<ConductInfoDetail> selectConductInfoDetailList(ConductInfoDetail conductInfoDetail)
    {
        return conductInfoDetailMapper.selectConductInfoDetailList(conductInfoDetail);
    }

    /**
     * 新增普查员宣传资料库-详情
     * 
     * @param conductInfoDetail 普查员宣传资料库-详情
     * @return 结果
     */
    @Override
    public int insertConductInfoDetail(ConductInfoDetail conductInfoDetail)
    {
        if(StringUtils.isBlank(conductInfoDetail.getId())){
            conductInfoDetail.setId(IdUtils.randomUUID());
        }
        conductInfoDetail.setCreateTime(DateUtils.getNowDate());
        try{
            conductInfoDetail.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){

        }
        return conductInfoDetailMapper.insertConductInfoDetail(conductInfoDetail);
    }

    /**
     * 修改普查员宣传资料库-详情
     * 
     * @param conductInfoDetail 普查员宣传资料库-详情
     * @return 结果
     */
    @Override
    public int updateConductInfoDetail(ConductInfoDetail conductInfoDetail)
    {
        conductInfoDetail.setUpdateTime(DateUtils.getNowDate());
        try{
            conductInfoDetail.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){

        }
        return conductInfoDetailMapper.updateConductInfoDetail(conductInfoDetail);
    }

    /**
     * 批量删除普查员宣传资料库-详情
     * 
     * @param ids 需要删除的普查员宣传资料库-详情ID
     * @return 结果
     */
    @Override
    public int deleteConductInfoDetailByIds(String[] ids)
    {
        return conductInfoDetailMapper.deleteConductInfoDetailByIds(ids);
    }

    /**
     * 删除普查员宣传资料库-详情信息
     * 
     * @param id 普查员宣传资料库-详情ID
     * @return 结果
     */
    @Override
    public int deleteConductInfoDetailById(String id)
    {
        return conductInfoDetailMapper.deleteConductInfoDetailById(id);
    }
}
