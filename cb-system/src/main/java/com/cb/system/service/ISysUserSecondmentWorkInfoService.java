package com.cb.system.service;

import com.cb.common.core.domain.entity.SysUserSecondmentWorkInfo;
import com.cb.common.core.domain.vo.SysUserSecondmentWorkInfoVo;

import java.util.List;
/**
 * 人员借调工作信息Service接口
 *
 * @author huhaojie
 * @date 2023-12-21
 */
public interface ISysUserSecondmentWorkInfoService {
    /**
     * 查询人员借调工作信息
     *
     * @param secondmentWorkInfoId 人员借调工作信息ID
     * @return 人员借调工作信息
     */
    public SysUserSecondmentWorkInfo selectSysUserSecondmentWorkInfoById(String secondmentWorkInfoId);

    /**
     * 查询人员借调工作信息列表
     *
     * @param sysUserSecondmentWorkInfo 人员借调工作信息
     * @return 人员借调工作信息集合
     */
    public List<SysUserSecondmentWorkInfo> selectSysUserSecondmentWorkInfoList(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo);

    public List<SysUserSecondmentWorkInfoVo> selectSysUserSecondmentWorkInfoVoList(SysUserSecondmentWorkInfoVo sysUserSecondmentWorkInfoVo);

    /**
     * 新增人员借调工作信息
     *
     * @param sysUserSecondmentWorkInfo 人员借调工作信息
     * @return 结果
     */
    public int insertSysUserSecondmentWorkInfo(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo);

    /**
     * 修改人员借调工作信息
     *
     * @param sysUserSecondmentWorkInfo 人员借调工作信息
     * @return 结果
     */
    public int updateSysUserSecondmentWorkInfo(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo);

    /**
     * 批量删除人员借调工作信息
     *
     * @param secondmentWorkInfoIds 需要删除的人员借调工作信息ID
     * @return 结果
     */
    public int deleteSysUserSecondmentWorkInfoByIds(String[] secondmentWorkInfoIds);

    /**
     * 删除人员借调工作信息信息
     *
     * @param secondmentWorkInfoId 人员借调工作信息ID
     * @return 结果
     */
    public int deleteSysUserSecondmentWorkInfoById(String secondmentWorkInfoId);

}
