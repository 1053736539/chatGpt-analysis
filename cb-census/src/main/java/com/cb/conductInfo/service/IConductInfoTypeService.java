package com.cb.conductInfo.service;

import com.cb.conductInfo.domain.ConductInfoType;

import java.util.List;

/**
 * 普查员宣传资料库-类型Service接口
 * 
 * @author yangxin
 * @date 2023-10-20
 */
public interface IConductInfoTypeService 
{
    /**
     * 查询普查员宣传资料库-类型
     * 
     * @param id 普查员宣传资料库-类型ID
     * @return 普查员宣传资料库-类型
     */
    public ConductInfoType selectConductInfoTypeById(String id);

    /**
     * 查询普查员宣传资料库-类型列表
     * 
     * @param conductInfoType 普查员宣传资料库-类型
     * @return 普查员宣传资料库-类型集合
     */
    public List<ConductInfoType> selectConductInfoTypeList(ConductInfoType conductInfoType);

    /**
     * 新增普查员宣传资料库-类型
     * 
     * @param conductInfoType 普查员宣传资料库-类型
     * @return 结果
     */
    public int insertConductInfoType(ConductInfoType conductInfoType);

    /**
     * 修改普查员宣传资料库-类型
     * 
     * @param conductInfoType 普查员宣传资料库-类型
     * @return 结果
     */
    public int updateConductInfoType(ConductInfoType conductInfoType);

    /**
     * 批量删除普查员宣传资料库-类型
     * 
     * @param ids 需要删除的普查员宣传资料库-类型ID
     * @return 结果
     */
    public int deleteConductInfoTypeByIds(String[] ids);

    /**
     * 删除普查员宣传资料库-类型信息
     * 
     * @param id 普查员宣传资料库-类型ID
     * @return 结果
     */
    public int deleteConductInfoTypeById(String id);
}
