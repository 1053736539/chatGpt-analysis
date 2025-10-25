package com.cb.system.service;

import com.cb.system.domain.SysUserInfoChangeApply;
import com.cb.system.vo.SysUserInfoChangeApplyVo;

import java.util.List;

/**
 * 用户信息修改申请Service接口
 *
 * @author ruoyi
 * @date 2025-05-15
 */
public interface ISysUserInfoChangeApplyService
{
    /**
     * 查询用户信息修改申请
     *
     * @param id 用户信息修改申请ID
     * @return 用户信息修改申请
     */
    public SysUserInfoChangeApply selectSysUserInfoChangeApplyById(String id);

    /**
     * 查询用户信息修改申请列表
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 用户信息修改申请集合
     */
    public List<SysUserInfoChangeApply> selectSysUserInfoChangeApplyList(SysUserInfoChangeApply sysUserInfoChangeApply);

    /**
     * 新增用户信息修改申请
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 结果
     */
    public int insertSysUserInfoChangeApply(SysUserInfoChangeApply sysUserInfoChangeApply);

    /**
     * 批量新增用户信息修改申请
     * @param entities
     * @return
     */
    public int insertBatch(List<SysUserInfoChangeApply> entities);

    /**
     * 修改用户信息修改申请
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 结果
     */
    public int updateSysUserInfoChangeApply(SysUserInfoChangeApply sysUserInfoChangeApply);

    /**
     * 批量删除用户信息修改申请
     *
     * @param ids 需要删除的用户信息修改申请ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeApplyByIds(String[] ids);

    /**
     * 批量删除用户信息修改申请(排除掉通过的)
     * @param ids
     * @return
     */
    public int deleteSysUserInfoChangeApplyByIdsExcludePass(String[] ids);

    /**
     * 删除用户信息修改申请信息
     *
     * @param id 用户信息修改申请ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeApplyById(String id);

    /**
     * 根据id集合查询
     * @param ids
     * @return
     */
    public List<SysUserInfoChangeApply> selectByIds(List<String> ids);

    /**
     * 审批
     * @param req
     */
    public void approval(SysUserInfoChangeApplyVo.ApprovalReq req);

    /**
     * 重新申请
     * @param req
     */
    public void reApply(SysUserInfoChangeApplyVo.ReApplyReq req);

    /**
     * 查询用户 未通过（待审核、审批驳回） 的记录
     * @param userId
     */
    public List<SysUserInfoChangeApply> selectUserNoPassList(Long userId);

    /**
     * 统计待办审批数量
     * @return
     */
    public long countPending();

}
