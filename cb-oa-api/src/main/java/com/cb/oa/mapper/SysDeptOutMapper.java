package com.cb.oa.mapper;

import com.cb.oa.domain.SysDeptOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * oa系统的部门列Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
public interface SysDeptOutMapper 
{
    /**
     * 查询oa系统的部门列
     * 
     * @param id oa系统的部门列ID
     * @return oa系统的部门列
     */
    public SysDeptOut selectSysDeptOutById(String id);
    public SysDeptOut selectSysDeptOutByParam(@Param("deptId") String deptId, @Param("id")String id);

    /**
     * 查询oa系统的部门列列表
     * 
     * @param sysDeptOut oa系统的部门列
     * @return oa系统的部门列集合
     */
    public List<SysDeptOut> selectSysDeptOutList(SysDeptOut sysDeptOut);

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
     * 删除oa系统的部门列
     * 
     * @param id oa系统的部门列ID
     * @return 结果
     */
    public int deleteSysDeptOutById(String id);

    /**
     * 批量删除oa系统的部门列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDeptOutByIds(String[] ids);
}
