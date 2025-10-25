package com.cb.system.service.impl;

import java.util.List;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.SysUserSupervisorMapper;
import com.cb.system.domain.SysUserSupervisor;
import com.cb.system.service.ISysUserSupervisorService;

/**
 * 用户负责人关系Service业务层处理
 * 
 * @author yangcd
 * @date 2024-09-12
 */
@Service
public class SysUserSupervisorServiceImpl implements ISysUserSupervisorService 
{
    @Autowired
    private SysUserSupervisorMapper sysUserSupervisorMapper;

    /**
     * 查询用户负责人关系
     * 
     * @param id 用户负责人关系ID
     * @return 用户负责人关系
     */
    @Override
    public SysUserSupervisor selectSysUserSupervisorById(Integer id)
    {
        return sysUserSupervisorMapper.selectSysUserSupervisorById(id);
    }

    /**
     * 查询用户负责人关系列表
     * 
     * @param sysUserSupervisor 用户负责人关系
     * @return 用户负责人关系
     */
    @Override
    public List<SysUserSupervisor> selectSysUserSupervisorList(SysUserSupervisor sysUserSupervisor)
    {
        return sysUserSupervisorMapper.selectSysUserSupervisorList(sysUserSupervisor);
    }

    /**
     * 新增用户负责人关系
     * 
     * @param sysUserSupervisor 用户负责人关系
     * @return 结果
     */
    @Override
    public int insertSysUserSupervisor(SysUserSupervisor sysUserSupervisor)
    {
        if(null == sysUserSupervisor.getSupervisorId()){
            //清空负责人
            int num = sysUserSupervisorMapper.deleteSupervisorByUserId(sysUserSupervisor.getUserId());
            return Math.max(num, 1);
        } else {
            // 首先检查该用户是否已经有负责人记录
            SysUserSupervisor existingRecord = sysUserSupervisorMapper.selectSupervisorByUserId(sysUserSupervisor.getUserId());

            // 如果存在记录，先删除旧的记录
            if (existingRecord != null) {
                sysUserSupervisorMapper.deleteSupervisorByUserId(sysUserSupervisor.getUserId());
            }

            // 插入新的负责人记录
            String username = SecurityUtils.getUsername();
            sysUserSupervisor.setCreateBy(username);
            sysUserSupervisor.setCreateTime(DateUtils.getNowDate());
            return sysUserSupervisorMapper.insertSysUserSupervisor(sysUserSupervisor);
        }
    }

    /**
     * 修改用户负责人关系
     * 
     * @param sysUserSupervisor 用户负责人关系
     * @return 结果
     */
    @Override
    public int updateSysUserSupervisor(SysUserSupervisor sysUserSupervisor)
    {
        sysUserSupervisor.setUpdateTime(DateUtils.getNowDate());
        return sysUserSupervisorMapper.updateSysUserSupervisor(sysUserSupervisor);
    }

    /**
     * 批量删除用户负责人关系
     * 
     * @param ids 需要删除的用户负责人关系ID
     * @return 结果
     */
    @Override
    public int deleteSysUserSupervisorByIds(Integer[] ids)
    {
        return sysUserSupervisorMapper.deleteSysUserSupervisorByIds(ids);
    }

    /**
     * 删除用户负责人关系信息
     * 
     * @param id 用户负责人关系ID
     * @return 结果
     */
    @Override
    public int deleteSysUserSupervisorById(Integer id)
    {
        return sysUserSupervisorMapper.deleteSysUserSupervisorById(id);
    }
}
