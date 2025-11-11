package com.cb.oa.mapper;

import com.cb.oa.domain.SysUserOut;
import com.cb.oa.domain.vo.SysUserOutVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * oa用户Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
public interface SysUserOutMapper 
{
    /**
     * 查询oa用户
     * 
     * @param id oa用户ID
     * @return oa用户
     */
    public SysUserOut selectSysUserOutById(String id);
    public SysUserOut selectSysUserOutByParam(@Param("id") String id,@Param("userId") Long userId);

    public SysUserOut selectSysUserOutByOaUserName(String oaUserName);

    /**
     * 查询oa用户列表
     * 
     * @param sysUserOut oa用户
     * @return oa用户集合
     */
    public List<SysUserOutVo> selectSysUserOutVoList(SysUserOutVo sysUserOut);
    public List<SysUserOut> selectSysUserOutList(SysUserOut sysUserOut);

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
     * 删除oa用户
     * 
     * @param id oa用户ID
     * @return 结果
     */
    public int deleteSysUserOutById(String id);

    /**
     * 批量删除oa用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserOutByIds(String[] ids);
}
