package com.cb.conductInfo.service;

import com.cb.conductInfo.domain.ConductInfoDetail;

import java.util.List;

/**
 * 普查员宣传资料库-详情Service接口
 * 
 * @author yangxin
 * @date 2023-10-20
 */
public interface IConductInfoDetailService 
{
    /**
     * 查询普查员宣传资料库-详情
     * 
     * @param id 普查员宣传资料库-详情ID
     * @return 普查员宣传资料库-详情
     */
    public ConductInfoDetail selectConductInfoDetailById(String id);

    /**
     * 查询普查员宣传资料库-详情列表
     * 
     * @param conductInfoDetail 普查员宣传资料库-详情
     * @return 普查员宣传资料库-详情集合
     */
    public List<ConductInfoDetail> selectConductInfoDetailList(ConductInfoDetail conductInfoDetail);

    /**
     * 新增普查员宣传资料库-详情
     * 
     * @param conductInfoDetail 普查员宣传资料库-详情
     * @return 结果
     */
    public int insertConductInfoDetail(ConductInfoDetail conductInfoDetail);

    /**
     * 修改普查员宣传资料库-详情
     * 
     * @param conductInfoDetail 普查员宣传资料库-详情
     * @return 结果
     */
    public int updateConductInfoDetail(ConductInfoDetail conductInfoDetail);

    /**
     * 批量删除普查员宣传资料库-详情
     * 
     * @param ids 需要删除的普查员宣传资料库-详情ID
     * @return 结果
     */
    public int deleteConductInfoDetailByIds(String[] ids);

    /**
     * 删除普查员宣传资料库-详情信息
     * 
     * @param id 普查员宣传资料库-详情ID
     * @return 结果
     */
    public int deleteConductInfoDetailById(String id);
}
