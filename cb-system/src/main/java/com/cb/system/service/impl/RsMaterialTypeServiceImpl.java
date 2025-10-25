package com.cb.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.common.core.domain.TreeSelect;
import com.cb.common.core.domain.entity.RsMaterialType;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.RsMaterialTypeMapper;
import com.cb.system.service.IRsMaterialTypeService;

/**
 * 材料类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@Service
public class RsMaterialTypeServiceImpl implements IRsMaterialTypeService 
{
    @Autowired
    private RsMaterialTypeMapper rsMaterialTypeMapper;

    /**
     * 查询材料类型
     * 
     * @param id 材料类型ID
     * @return 材料类型
     */
    @Override
    public RsMaterialType selectRsMaterialTypeById(Integer id)
    {
        return rsMaterialTypeMapper.selectRsMaterialTypeById(id);
    }

    /**
     * 查询材料类型列表
     * 
     * @param rsMaterialType 材料类型
     * @return 材料类型
     */
    @Override
    public List<RsMaterialType> selectRsMaterialTypeList(RsMaterialType rsMaterialType)
    {
        return rsMaterialTypeMapper.selectRsMaterialTypeList(rsMaterialType);
    }

    /**
     * 新增材料类型
     * 
     * @param rsMaterialType 材料类型
     * @return 结果
     */
    @Override
    public int insertRsMaterialType(RsMaterialType rsMaterialType)
    {
        rsMaterialType.setCreateTime(DateUtils.getNowDate());
        rsMaterialType.setCreateBy(SecurityUtils.getUsername());
        return rsMaterialTypeMapper.insertRsMaterialType(rsMaterialType);
    }

    /**
     * 修改材料类型
     * 
     * @param rsMaterialType 材料类型
     * @return 结果
     */
    @Override
    public int updateRsMaterialType(RsMaterialType rsMaterialType)
    {
        rsMaterialType.setUpdateTime(DateUtils.getNowDate());
        rsMaterialType.setUpdateBy(SecurityUtils.getUsername());
        return rsMaterialTypeMapper.updateRsMaterialType(rsMaterialType);
    }

    /**
     * 批量删除材料类型
     * 
     * @param ids 需要删除的材料类型ID
     * @return 结果
     */
    @Override
    public int deleteRsMaterialTypeByIds(Integer[] ids)
    {
        return rsMaterialTypeMapper.deleteRsMaterialTypeByIds(ids);
    }

    /**
     * 删除材料类型信息
     * 
     * @param id 材料类型ID
     * @return 结果
     */
    @Override
    public int deleteRsMaterialTypeById(Integer id)
    {
        return rsMaterialTypeMapper.deleteRsMaterialTypeById(id);
    }

    @Override
    public List<RsMaterialType> selectRsMaterialTypeParentList(RsMaterialType rsMaterialType) {
        return rsMaterialTypeMapper.selectRsMaterialTypeParentList(rsMaterialType);
    }

    @Override
    public List<TreeSelect> buildMaterialTypeTreeSelect(List<RsMaterialType> rsMaterialTypes) {
        List<RsMaterialType> materialTypeTrees = buildMaterialTypeTree(rsMaterialTypes);
        return materialTypeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要树结构
     *
     * @param materialTypes 档案材料类别列表
     * @return 树结构列表
     */
    @Override
    public List<RsMaterialType> buildMaterialTypeTree(List<RsMaterialType> materialTypes)
    {
        List<RsMaterialType> returnList = new ArrayList<RsMaterialType>();
        List<Long> tempList = new ArrayList<Long>();
        for (RsMaterialType materialType : materialTypes)
        {
            tempList.add(Long.valueOf(materialType.getType()));
        }
        for (Iterator<RsMaterialType> iterator = materialTypes.iterator(); iterator.hasNext();)
        {
            RsMaterialType materialType = (RsMaterialType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(materialType.getParentId()))
            {
                recursionFn(materialTypes, materialType);
                returnList.add(materialType);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = materialTypes;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<RsMaterialType> list, RsMaterialType t)
    {
        // 得到子节点列表
        List<RsMaterialType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (RsMaterialType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<RsMaterialType> getChildList(List<RsMaterialType> list, RsMaterialType t)
    {
        List<RsMaterialType> tlist = new ArrayList<RsMaterialType>();
        Iterator<RsMaterialType> it = list.iterator();
        while (it.hasNext())
        {
            RsMaterialType n = (RsMaterialType) it.next();
            if (StringUtils.isNotNull(n.getParentType()) && n.getParentType().longValue() == t.getType().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<RsMaterialType> list, RsMaterialType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
