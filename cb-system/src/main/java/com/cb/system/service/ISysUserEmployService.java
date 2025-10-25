package com.cb.system.service;

import com.cb.common.core.domain.entity.SysUserEmploy;

import java.util.List;


/**
 * 任免和聘用信息Service接口
 *
 * @author ruoyi
 * @date 2023-11-09
 */
public interface ISysUserEmployService
{
    /**
     * 查询任免和聘用信息
     *
     * @param id 任免和聘用信息ID
     * @return 任免和聘用信息
     */
    public SysUserEmploy selectSysUserEmployById(String id);

    /**
     * 查询任免和聘用信息列表
     *
     * @param sysUserEmploy 任免和聘用信息
     * @return 任免和聘用信息集合
     */
    public List<SysUserEmploy> selectSysUserEmployList(SysUserEmploy sysUserEmploy);

    /**
     * 新增任免和聘用信息
     *
     * @param sysUserEmploy 任免和聘用信息
     * @return 结果
     */
    public int insertSysUserEmploy(SysUserEmploy sysUserEmploy);

    /**
     * 修改任免和聘用信息
     *
     * @param sysUserEmploy 任免和聘用信息
     * @return 结果
     */
    public int updateSysUserEmploy(SysUserEmploy sysUserEmploy);

    /**
     * 批量删除任免和聘用信息
     *
     * @param ids 需要删除的任免和聘用信息ID
     * @return 结果
     */
    public int deleteSysUserEmployByIds(String[] ids);

    /**
     * 删除任免和聘用信息信息
     *
     * @param id 任免和聘用信息ID
     * @return 结果
     */
    public int deleteSysUserEmployById(String id);

    public int disableEmployById(String id);
}
