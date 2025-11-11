package com.cb.oa.service;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.domain.vo.SysUserOutVo;

import java.util.List;
import java.util.Map;

/**
 * oa用户Service接口
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
public interface ISysUserOutService 
{
    SysUser selectSysUserByOaUserId(String oaUserId);

    SysUser selectSysUserByOaUserName(String oaUserName);

    /**
     * 查询oa用户
     * 
     * @param id oa用户ID
     * @return oa用户
     */
    public SysUserOut selectSysUserOutById(String id);

    SysUserOut selectSysUserOutById(Long userId);

    /**
     * 查询oa用户列表
     * 
     * @param sysUserOut oa用户
     * @return oa用户集合
     */
    List<SysUserOutVo> selectSysUserOutVoList(SysUserOutVo sysUserOut);

    List<SysUserOut> selectSysUserOutList(SysUserOut sysUserOut);

    Integer syncSysUserOutList(Integer count, Integer page, Integer pageSize, Map<String, SysUser> phoneMap, Map<String, SysUser> nameMap, Map<String, SysUserOut> sysUserOutMap);

    /**
     * 新增oa用户
     * 
     * @param sysUserOut oa用户
     * @return 结果
     */
    public int insertSysUserOut(SysUserOut sysUserOut);

    /**
     * 修改oa用户
     * 
     * @param sysUserOut oa用户
     * @return 结果
     */
    public int updateSysUserOut(SysUserOut sysUserOut);

    /**
     * 批量删除oa用户
     * 
     * @param ids 需要删除的oa用户ID
     * @return 结果
     */
    public int deleteSysUserOutByIds(String[] ids);

    /**
     * 删除oa用户信息
     * 
     * @param id oa用户ID
     * @return 结果
     */
    public int deleteSysUserOutById(String id);
}
