package com.cb.oa.service;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.oa.domain.SysDeptOut;

import java.util.List;

/**
 * oa系统的部门列Service接口
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
public interface ISysDeptOutService 
{
    /**
     * 查询oa系统的部门列
     * 
     * @param id oa系统的部门列ID
     * @return oa系统的部门列
     */
    public SysDeptOut selectSysDeptOutById(String id);

    SysDeptOut selectOaDeptIdByDeptId(String deptId);

    SysDept selectDeptIdByOaDeptId(String oaDeptId);

    /**
     * 查询oa系统的部门列列表
     * 
     * @param sysDeptOut oa系统的部门列
     * @return oa系统的部门列集合
     */
    public List<SysDeptOut> selectSysDeptOutList(SysDeptOut sysDeptOut);

    Integer syncSysDeptOutList();

    /**
     * 新增oa系统的部门列
     * 
     * @param sysDeptOut oa系统的部门列
     * @return 结果
     */
    public int insertSysDeptOut(SysDeptOut sysDeptOut);

    /**
     * 修改oa系统的部门列
     * 
     * @param sysDeptOut oa系统的部门列
     * @return 结果
     */
    public int updateSysDeptOut(SysDeptOut sysDeptOut);

    /**
     * 批量删除oa系统的部门列
     * 
     * @param ids 需要删除的oa系统的部门列ID
     * @return 结果
     */
    public int deleteSysDeptOutByIds(String[] ids);

    /**
     * 删除oa系统的部门列信息
     * 
     * @param id oa系统的部门列ID
     * @return 结果
     */
    public int deleteSysDeptOutById(String id);
}
