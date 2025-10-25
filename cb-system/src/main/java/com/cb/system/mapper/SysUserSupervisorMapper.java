package com.cb.system.mapper;

import java.util.List;
import com.cb.system.domain.SysUserSupervisor;

/**
 * 用户负责人关系Mapper接口
 * 
 * @author yangcd
 * @date 2024-09-12
 */
public interface SysUserSupervisorMapper 
{
    /**
     * 查询用户负责人关系
     * 
     * @param id 用户负责人关系ID
     * @return 用户负责人关系
     */
    public SysUserSupervisor selectSysUserSupervisorById(Integer id);

    /**
     * 查询用户负责人关系列表
     * 
     * @param sysUserSupervisor 用户负责人关系
     * @return 用户负责人关系集合
     */
    public List<SysUserSupervisor> selectSysUserSupervisorList(SysUserSupervisor sysUserSupervisor);

    /**
     * 新增用户负责人关系
     * 
     * @param sysUserSupervisor 用户负责人关系
     * @return 结果
     */
    public int insertSysUserSupervisor(SysUserSupervisor sysUserSupervisor);

    /**
     * 修改用户负责人关系
     * 
     * @param sysUserSupervisor 用户负责人关系
     * @return 结果
     */
    public int updateSysUserSupervisor(SysUserSupervisor sysUserSupervisor);

    /**
     * 删除用户负责人关系
     * 
     * @param id 用户负责人关系ID
     * @return 结果
     */
    public int deleteSysUserSupervisorById(Integer id);

    /**
     * 批量删除用户负责人关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserSupervisorByIds(Integer[] ids);

    public SysUserSupervisor selectSupervisorByUserId(Integer userId);

    public int deleteSupervisorByUserId(Integer id);
}
