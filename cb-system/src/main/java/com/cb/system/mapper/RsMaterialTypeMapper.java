package com.cb.system.mapper;

import com.cb.common.core.domain.entity.RsMaterialType;

import java.util.List;
import java.util.Map;

/**
 * 材料类型Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
public interface RsMaterialTypeMapper 
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
     * 删除材料类型
     * 
     * @param id 材料类型ID
     * @return 结果
     */
    public int deleteRsMaterialTypeById(Integer id);

    /**
     * 批量删除材料类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRsMaterialTypeByIds(Integer[] ids);

    /**
     * 查询档案材料类型
     * @param rsMaterialType
     * @return
     */
    List<RsMaterialType> selectRsMaterialTypeParentList(RsMaterialType rsMaterialType);

    /**
     * 导出档案材料类型数据
     * @param params
     * @return
     */
    List<RsMaterialType> exportArchivesCatalogueOfTypesData(Map<String, Object> params);
}
