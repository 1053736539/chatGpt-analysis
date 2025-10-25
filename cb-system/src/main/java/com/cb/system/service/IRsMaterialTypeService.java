package com.cb.system.service;

import java.util.List;

import com.cb.common.core.domain.TreeSelect;
import com.cb.common.core.domain.entity.RsMaterialType;
import com.cb.common.core.domain.entity.SysDept;

/**
 * 材料类型Service接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface IRsMaterialTypeService 
{
    /**
     * 查询材料类型
     * 
     * @param id 材料类型ID
     * @return 材料类型
     */
    public RsMaterialType selectRsMaterialTypeById(Integer id);

    /**
     * 查询材料类型列表
     * 
     * @param rsMaterialType 材料类型
     * @return 材料类型集合
     */
    public List<RsMaterialType> selectRsMaterialTypeList(RsMaterialType rsMaterialType);

    /**
     * 新增材料类型
     * 
     * @param rsMaterialType 材料类型
     * @return 结果
     */
    public int insertRsMaterialType(RsMaterialType rsMaterialType);

    /**
     * 修改材料类型
     * 
     * @param rsMaterialType 材料类型
     * @return 结果
     */
    public int updateRsMaterialType(RsMaterialType rsMaterialType);

    /**
     * 批量删除材料类型
     * 
     * @param ids 需要删除的材料类型ID
     * @return 结果
     */
    public int deleteRsMaterialTypeByIds(Integer[] ids);

    /**
     * 删除材料类型信息
     * 
     * @param id 材料类型ID
     * @return 结果
     */
    public int deleteRsMaterialTypeById(Integer id);

    /**
     * 查询档案材料类型列表
     * @param rsMaterialType
     * @return
     */
     List<RsMaterialType> selectRsMaterialTypeParentList(RsMaterialType rsMaterialType);

    /**
     * 前端树形结构
     * @param rsMaterialTypes
     * @return
     */
     List<TreeSelect> buildMaterialTypeTreeSelect(List<RsMaterialType> rsMaterialTypes);

    /**
     * 构建前端所需要树结构
     *
     * @param rsMaterialTypes
     * @return 树结构列表
     */
    public List<RsMaterialType> buildMaterialTypeTree(List<RsMaterialType> rsMaterialTypes);
}
