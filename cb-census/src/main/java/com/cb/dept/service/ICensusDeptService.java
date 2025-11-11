package com.cb.dept.service;

import java.util.List;

import com.cb.common.core.domain.TreeSelect;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.dept.domain.CensusDept;
import com.cb.dept.domain.CensusTreeSelect;

/**
 * 普查人员部门Service接口
 * 
 * @author yangcd
 * @date 2023-11-11
 */
public interface ICensusDeptService 
{
    /**
     * 查询普查人员部门
     * 
     * @param deptId 普查人员部门ID
     * @return 普查人员部门
     */
    public CensusDept selectCensusDeptById(Long deptId);

    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 查询普查人员部门列表
     * 
     * @param censusDept 普查人员部门
     * @return 普查人员部门集合
     */
    public List<CensusDept> selectCensusDeptList(CensusDept censusDept);

    /**
     * 新增普查人员部门
     * 
     * @param censusDept 普查人员部门
     * @return 结果
     */
    public int insertCensusDept(CensusDept censusDept);

    /**
     * 修改普查人员部门
     * 
     * @param censusDept 普查人员部门
     * @return 结果
     */
    public int updateCensusDept(CensusDept censusDept);

    /**
     * 批量删除普查人员部门
     * 
     * @param deptIds 需要删除的普查人员部门ID
     * @return 结果
     */
    public int deleteCensusDeptByIds(Integer[] deptIds);

    /**
     * 删除普查人员部门信息
     * 
     * @param deptId 普查人员部门ID
     * @return 结果
     */
    public int deleteCensusDeptById(Long deptId);

    public List<CensusTreeSelect> buildDeptTreeSelect(List<CensusDept> depts);

    public List<CensusDept> buildDeptTree(List<CensusDept> depts);

    public String checkDeptNameUnique(CensusDept dept);
    public boolean hasChildByDeptId(Long deptId);
    public boolean checkDeptExistUser(Long deptId);

    public String importCensusDepts(List<CensusDept> censusDeptList) throws Exception;
}
