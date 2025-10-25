package com.cb.system.service;

import com.cb.system.domain.SysUserPwdModify;

import java.util.List;

/**
 * 密码修改记录Service接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface ISysUserPwdModifyService 
{
    /**
     * 查询密码修改记录
     * 
     * @param id 密码修改记录ID
     * @return 密码修改记录
     */
    public SysUserPwdModify selectSysUserPwdModifyById(Long id);

    /**
     * 查询密码修改记录列表
     * 
     * @param sysUserPwdModify 密码修改记录
     * @return 密码修改记录集合
     */
    public List<SysUserPwdModify> selectSysUserPwdModifyList(SysUserPwdModify sysUserPwdModify);

    /**
     * 新增密码修改记录
     * 
     * @param sysUserPwdModify 密码修改记录
     * @return 结果
     */
    public int insertSysUserPwdModify(SysUserPwdModify sysUserPwdModify);

    /**
     * 修改密码修改记录
     * 
     * @param sysUserPwdModify 密码修改记录
     * @return 结果
     */
    public int updateSysUserPwdModify(SysUserPwdModify sysUserPwdModify);

    /**
     * 批量删除密码修改记录
     * 
     * @param ids 需要删除的密码修改记录ID
     * @return 结果
     */
    public int deleteSysUserPwdModifyByIds(Long[] ids);

    /**
     * 删除密码修改记录信息
     * 
     * @param id 密码修改记录ID
     * @return 结果
     */
    public int deleteSysUserPwdModifyById(Long id);

    public SysUserPwdModify selectSysUserPwdModifyByUserId(Long userId);
}
