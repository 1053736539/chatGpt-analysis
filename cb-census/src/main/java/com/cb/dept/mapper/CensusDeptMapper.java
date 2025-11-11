package com.cb.dept.mapper;

import java.util.List;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.dept.domain.CensusDept;
import org.apache.ibatis.annotations.Param;

/**
 * 普查人员部门Mapper接口
 * 
 * @author yangcd
 * @date 2023-11-11
 */
public interface CensusDeptMapper 
{
    /**
     * 查询普查人员部门
     * 
     * @param deptId 普查人员部门ID
     * @return 普查人员部门
     */
    public CensusDept selectCensusDeptById(Long deptId);

    public int selectNormalChildrenDeptById(Long deptId);
    public int updateDept(CensusDept dept);

    public void updateDeptStatus(CensusDept dept);

    public List<CensusDept> selectChildrenDeptById(Long deptId);
    public int updateDeptChildren(@Param("depts") List<CensusDept> depts);
    public int hasChildByDeptId(Long deptId);

    public int checkDeptExistUser(Long deptId);

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
     * 删除普查人员部门
     * 
     * @param deptId 普查人员部门ID
     * @return 结果
     */
    public int deleteCensusDeptById(Long deptId);

    /**
     * 批量删除普查人员部门
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCensusDeptByIds(Integer[] deptIds);

    public CensusDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    void deleteAllCensusDepts();

    /**
     * 根据部门代码精确查询部门ID
     *
     * @param deptCode 部门代码
     * @return 部门ID
     */
    Long getCensusDeptIdByCode(@Param("deptCode") Long deptCode);

    /**
     * 根据部门代码模糊查询部门ID
     *
     * @param deptCode 部门代码
     * @return 部门ID
     */
    Long getCensusDeptIdByCodeFuzzy(@Param("deptCode") Long deptCode);
}
