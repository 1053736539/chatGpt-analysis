package com.cb.system.service.impl;

import java.util.List;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.SysUserInfoChangeLogMapper;
import com.cb.system.domain.SysUserInfoChangeLog;
import com.cb.system.service.ISysUserInfoChangeLogService;

/**
 * 干部任免信息修改日志Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-11
 */
@Service
public class SysUserInfoChangeLogServiceImpl implements ISysUserInfoChangeLogService
{
    @Autowired
    private SysUserInfoChangeLogMapper sysUserInfoChangeLogMapper;

    /**
     * 查询干部任免信息修改日志
     *
     * @param userId 干部任免信息修改日志ID
     * @return 干部任免信息修改日志
     */
    @Override
    public SysUserInfoChangeLog selectSysUserInfoChangeLogById(Long userId)
    {
        return sysUserInfoChangeLogMapper.selectSysUserInfoChangeLogById(userId);
    }

    /**
     * 查询干部任免信息修改日志列表
     *
     * @param sysUserInfoChangeLog 干部任免信息修改日志
     * @return 干部任免信息修改日志
     */
    @Override
    public List<SysUserInfoChangeLog> selectSysUserInfoChangeLogList(SysUserInfoChangeLog sysUserInfoChangeLog)
    {
        return sysUserInfoChangeLogMapper.selectSysUserInfoChangeLogList(sysUserInfoChangeLog);
    }

    /**
     * 新增干部任免信息修改日志
     *
     * @param sysUserInfoChangeLog 干部任免信息修改日志
     * @return 结果
     */
    @Override
    public int insertSysUserInfoChangeLog(SysUserInfoChangeLog sysUserInfoChangeLog)
    {
        try {
            sysUserInfoChangeLog.setCreateBy(SecurityUtils.getUsername());
            sysUserInfoChangeLog.setCreateTime(DateUtils.getNowDate());
        } catch (Exception e){}
        return sysUserInfoChangeLogMapper.insertSysUserInfoChangeLog(sysUserInfoChangeLog);
    }

    /**
     * 修改干部任免信息修改日志
     *
     * @param sysUserInfoChangeLog 干部任免信息修改日志
     * @return 结果
     */
    @Override
    public int updateSysUserInfoChangeLog(SysUserInfoChangeLog sysUserInfoChangeLog)
    {
        try {
            sysUserInfoChangeLog.setUpdateBy(SecurityUtils.getUsername());
            sysUserInfoChangeLog.setUpdateTime(DateUtils.getNowDate());
        } catch (Exception e){}

        return sysUserInfoChangeLogMapper.updateSysUserInfoChangeLog(sysUserInfoChangeLog);
    }

    /**
     * 批量删除干部任免信息修改日志
     *
     * @param userIds 需要删除的干部任免信息修改日志ID
     * @return 结果
     */
    @Override
    public int deleteSysUserInfoChangeLogByIds(Long[] userIds)
    {
        return sysUserInfoChangeLogMapper.deleteSysUserInfoChangeLogByIds(userIds);
    }

    /**
     * 删除干部任免信息修改日志信息
     *
     * @param userId 干部任免信息修改日志ID
     * @return 结果
     */
    @Override
    public int deleteSysUserInfoChangeLogById(Long userId)
    {
        return sysUserInfoChangeLogMapper.deleteSysUserInfoChangeLogById(userId);
    }
}