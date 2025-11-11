package com.cb.conductInfo.service.impl;

import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.conductInfo.domain.ConductInfoType;
import com.cb.conductInfo.mapper.ConductInfoTypeMapper;
import com.cb.conductInfo.service.IConductInfoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 普查员宣传资料库-类型Service业务层处理
 * 
 * @author yangxin
 * @date 2023-10-20
 */
@Service
public class ConductInfoTypeServiceImpl implements IConductInfoTypeService 
{
    @Autowired
    private ConductInfoTypeMapper conductInfoTypeMapper;

    /**
     * 查询普查员宣传资料库-类型
     * 
     * @param id 普查员宣传资料库-类型ID
     * @return 普查员宣传资料库-类型
     */
    @Override
    public ConductInfoType selectConductInfoTypeById(String id)
    {
        return conductInfoTypeMapper.selectConductInfoTypeById(id);
    }

    /**
     * 查询普查员宣传资料库-类型列表
     * 
     * @param conductInfoType 普查员宣传资料库-类型
     * @return 普查员宣传资料库-类型
     */
    @Override
    public List<ConductInfoType> selectConductInfoTypeList(ConductInfoType conductInfoType)
    {
        return conductInfoTypeMapper.selectConductInfoTypeList(conductInfoType);
    }

    /**
     * 新增普查员宣传资料库-类型
     * 
     * @param conductInfoType 普查员宣传资料库-类型
     * @return 结果
     */
    @Override
    public int insertConductInfoType(ConductInfoType conductInfoType)
    {
        if(StringUtils.isBlank(conductInfoType.getId())){
            conductInfoType.setId(IdUtils.randomUUID());
        }
        conductInfoType.setDelFlag(0);
        return conductInfoTypeMapper.insertConductInfoType(conductInfoType);
    }

    /**
     * 修改普查员宣传资料库-类型
     * 
     * @param conductInfoType 普查员宣传资料库-类型
     * @return 结果
     */
    @Override
    public int updateConductInfoType(ConductInfoType conductInfoType)
    {
        return conductInfoTypeMapper.updateConductInfoType(conductInfoType);
    }

    /**
     * 批量删除普查员宣传资料库-类型
     * 
     * @param ids 需要删除的普查员宣传资料库-类型ID
     * @return 结果
     */
    @Override
    public int deleteConductInfoTypeByIds(String[] ids)
    {
        return conductInfoTypeMapper.deleteConductInfoTypeByIds(ids);
    }

    /**
     * 删除普查员宣传资料库-类型信息
     * 
     * @param id 普查员宣传资料库-类型ID
     * @return 结果
     */
    @Override
    public int deleteConductInfoTypeById(String id)
    {
        return conductInfoTypeMapper.deleteConductInfoTypeById(id);
    }
}
