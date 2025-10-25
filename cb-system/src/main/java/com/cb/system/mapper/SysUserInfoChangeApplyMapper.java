package com.cb.system.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cb.system.domain.SysUserInfoChangeApply;

/**
 * 用户信息修改申请Mapper接口
 *
 * @author ruoyi
 * @date 2025-05-15
 */
public interface SysUserInfoChangeApplyMapper
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
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<SysUserInfoChangeApply> entities);

    /**
     * 修改用户信息修改申请
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 结果
     */
    public int updateSysUserInfoChangeApply(SysUserInfoChangeApply sysUserInfoChangeApply);

    /**
     * 删除用户信息修改申请
     *
     * @param id 用户信息修改申请ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeApplyById(String id);

    /**
     * 批量删除用户信息修改申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeApplyByIds(String[] ids);

    /**
     * 批量删除用户信息修改申请(排除掉通过的)
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserInfoChangeApplyByIdsExcludePass(String[] ids);

    /**
     * 根据ids列表查询记录
     * @param ids
     * @return
     */
    public List<SysUserInfoChangeApply> selectByIds(@Param("ids") List<String> ids);

    /**
     * 审批
     * @param ids
     * @param status
     * @param approvalOpinion
     * @param approvalBy
     * @return
     */
    public int approval(@Param("ids") List<String> ids,
                        @Param("status") String status,
                        @Param("approvalOpinion") String approvalOpinion,
                        @Param("approvalBy") String approvalBy,
                        @Param("updateTime") Date updateTime);

    /**
     * 重新申请
     * @param id
     * @param afterData
     * @return
     */
    public int reApply(@Param("id") String id,
                       @Param("afterData") String afterData,
                       @Param("updateTime") Date updateTime);

    /**
     * 查询用户 未通过（待审核、审批驳回） 的记录
     * @param userId
     * @return
     */
    public List<SysUserInfoChangeApply> selectUserNoPassList(@Param("userId") Long userId);

    /**
     * 查询待审批记录数
     * @return
     */
    public long countPending();

}
