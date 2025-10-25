package com.cb.system.mapper;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.vo.SysDeptBasicInfoVo;
import com.cb.common.core.domain.vo.SysDeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门管理 数据层
 * 
 * @author ruoyi
 */
public interface SysDeptMapper
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    public List<SysDept> selectAllDeptList();

    /**
     * 根据角色ID查询部门树信息
     * 
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    public List<Integer> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDeptVo selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门
     * 
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改所在部门的父级部门状态
     * 
     * @param dept 部门
     */
    public void updateDeptStatus(SysDept dept);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    public List<SysDept> selectChargeDeptList(Long userId);

    /**
     * 获取部门基础信息用于考核方案
     * @return
     */
    List<SysDeptBasicInfoVo> getDeptBasicInfoList4PersonnelType();



    List<SysDept> selectDeptListByAncestors(String ancestors);

    List<SysDept> selectDeptListLeftLikeAncestors(String ancestors);

    List<SysDept> selectDeptListLeftLikeAncestorsAndDeptId(@Param("ancestors") String ancestors, @Param("deptId") Long deptId);

    List<SysDept> selectDeptListLeftBydeptId(SysDept sysDept);

    SysDept selectDeptByDeptName(String deptName);

    /**
     * 根据数据权限的role_keys查询对应的权限部门
     * @param roleKeys
     * @return
     */
    List<SysDept> getDeptListByRoleKeys(@Param("roleKeys") List<String> roleKeys);

    List<SysDept> selectDeptListleaderCharge(SysDept dept);

    /**
     * 平板导出部门信息需求接口
     * @param params
     * @return
     */
    List<SysDept> exportDeptInfoData(Map<String, Object> params);
}
