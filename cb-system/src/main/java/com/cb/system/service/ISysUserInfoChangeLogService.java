package com.cb.system.service;

import java.util.List;
import com.cb.system.domain.SysUserInfoChangeLog;

/**
 * 干部任免信息修改日志Service接口
 *
 * @author ruoyi
 * @date 2025-03-11
 */
public interface ISysUserInfoChangeLogService
{
    /**
     * 查询干部任免信息修改日志
     *
     * @param userId 干部任免信息修改日志ID
     * @return 干部任免信息修改日志
     */
    public SysUserInfoChangeLog selectSysUserInfoChangeLogById(Long userId);

    /**
     * 查询干部任免信息修改日志列表
     *
     * @param sysUserInfoChangeLog 干部任免信息修改日志
     * @return 干部任免信息修改日志集合
     */
    public List<SysUserInfoChangeLog> selectSysUserInfoChangeLogList(SysUserInfoChangeLog sysUserInfoChangeLog);

    /**
     * 新增干部任免信息修改日志
     *
     * @param sysUserInfoChangeLog 干部任免信息修改日志
     * @return 结果
     */
    public int insertSysUserInfoChangeLog(SysUserInfoChangeLog sysUserInfoChangeLog);

    /**
     * 修改干部任免信息修改日志
     *
     * @param sysUserInfoChangeLog 干部任免信息修改日志
     * @return 结果
     */
    public int updateSysUserInfoChangeLog(SysUserInfoChangeLog sysUserInfoChangeLog);

    /**
     * 批量删除干部任免信息修改日志
     *
     * @param userIds 需要删除的干部任免信息修改日志ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeLogByIds(Long[] userIds);

    /**
     * 删除干部任免信息修改日志信息
     *
     * @param userId 干部任免信息修改日志ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeLogById(Long userId);
}