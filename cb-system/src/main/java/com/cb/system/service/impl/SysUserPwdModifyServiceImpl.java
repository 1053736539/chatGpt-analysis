package com.cb.system.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.system.domain.SysUserPwdModify;
import com.cb.system.mapper.SysUserPwdModifyMapper;
import com.cb.system.service.ISysUserPwdModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 密码修改记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class SysUserPwdModifyServiceImpl implements ISysUserPwdModifyService 
{
    @Autowired
    private SysUserPwdModifyMapper sysUserPwdModifyMapper;

    /**
     * 查询密码修改记录
     * 
     * @param id 密码修改记录ID
     * @return 密码修改记录
     */
    @Override
    public SysUserPwdModify selectSysUserPwdModifyById(Long id)
    {
        return sysUserPwdModifyMapper.selectSysUserPwdModifyById(id);
    }

    /**
     * 查询密码修改记录列表
     * 
     * @param sysUserPwdModify 密码修改记录
     * @return 密码修改记录
     */
    @Override
    public List<SysUserPwdModify> selectSysUserPwdModifyList(SysUserPwdModify sysUserPwdModify)
    {
        return sysUserPwdModifyMapper.selectSysUserPwdModifyList(sysUserPwdModify);
    }

    /**
     * 新增密码修改记录
     * 
     * @param sysUserPwdModify 密码修改记录
     * @return 结果
     */
    @Override
    public int insertSysUserPwdModify(SysUserPwdModify sysUserPwdModify)
    {
        sysUserPwdModify.setCreateTime(DateUtils.getNowDate());
        return sysUserPwdModifyMapper.insertSysUserPwdModify(sysUserPwdModify);
    }

    /**
     * 修改密码修改记录
     * 
     * @param sysUserPwdModify 密码修改记录
     * @return 结果
     */
    @Override
    public int updateSysUserPwdModify(SysUserPwdModify sysUserPwdModify)
    {
        sysUserPwdModify.setUpdateTime(DateUtils.getNowDate());
        return sysUserPwdModifyMapper.updateSysUserPwdModify(sysUserPwdModify);
    }

    /**
     * 批量删除密码修改记录
     * 
     * @param ids 需要删除的密码修改记录ID
     * @return 结果
     */
    @Override
    public int deleteSysUserPwdModifyByIds(Long[] ids)
    {
        return sysUserPwdModifyMapper.deleteSysUserPwdModifyByIds(ids);
    }

    /**
     * 删除密码修改记录信息
     * 
     * @param id 密码修改记录ID
     * @return 结果
     */
    @Override
    public int deleteSysUserPwdModifyById(Long id)
    {
        return sysUserPwdModifyMapper.deleteSysUserPwdModifyById(id);
    }

    @Override
    public SysUserPwdModify selectSysUserPwdModifyByUserId(Long userId) {
        return sysUserPwdModifyMapper.selectSysUserPwdModifyByUserId(userId);
    }
}
