package com.cb.system.mapper;

import com.cb.common.core.domain.entity.*;
import com.cb.common.core.domain.vo.VSysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户表 数据层
 *
 * @author ruoyi
 */
public interface SysUserMapper
{
    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);
    public List<SysUser> selectAllUserList();

    /**
     * 根据条件分页查询用户列表(管理员)
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserListByAdmin(SysUser sysUser);

    /**
     * 获取联络员用户列表
     * @param user
     * @return
     */
    public List<SysUser> selectLiaisonInfoList(SysUser user);

    /**
     * 查询领导
     * @param sysUser
     * @return
     */
    public List<SysUser> listCadre(SysUser sysUser);

    public List<SysUser> selectUserListByIdentityType(SysUser sysUser);
    public List<SysUser> archivesUserList(SysUser sysUser);
    public List<SysUser> selectRetireesUserList(SysUser sysUser);

    public List<SysUser> selectUserListByLoginNames(@Param("loginNames") String[] loginNames);

    public List<SysUser> selectUserListByUserIds(@Param("userIds") List<Long> userIds);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);
    @Deprecated
    public SysUser selectUserByIdcard(String idcard);

    public SysUser selectUserByMobile(String mobile);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);
    public SysUser selectUserByUserId(Long userId);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public int checkUserNameUnique(@Param("userName") String userName, @Param("userId") Long userId);

    /**
     * 校验身份证号码是否唯一
     *
     * @param idcard 身份证号码
     * @return 结果
     */
    public int checkIdcardUnique(@Param("idcard") String idcard, @Param("userId") Long userId);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(@Param("phonenumber") String phonenumber, @Param("userId") Long userId);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);


    public SysUser selectUserByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);

    List<SysUser> selectUserListByRoleKey(String roleKey);

    public int updateDeptId(@Param("userId") Long userId,@Param("deptId") Long deptId);

    public List<SysUser> userSelectorList(SysUser sysUser);
    public List<SysUser> selectorUserList(SysUser sysUser);

    public List<VSysUser> selectVSysUserList(VSysUser user);
    public List<VSysUser> selectAllVSysUserList();
    public List<VSysUser> selectVSysUserListByRoles(VSysUser user);

    /**
     * 批量删除专业技术职务信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserTechnicalPositionInfoByUserIds(Long[] userIds);

    /**
     * 批量新增专业技术职务信息
     *
     * @param sysUserTechnicalPositionInfoList 专业技术职务信息列表
     * @return 结果
     */
    public int batchSysUserTechnicalPositionInfo(List<SysUserTechnicalPositionInfo> sysUserTechnicalPositionInfoList);


    /**
     * 通过用户信息ID删除专业技术职务信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserTechnicalPositionInfoByUserId(Long userId);

    public List<SysUserTechnicalPositionInfo> selectTechnicalPositionInfoByUserId(Long userId);

    /**
     * 批量删除学历学位信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserEducationAndDegreeInfoByUserIds(Long[] userIds);

    /**
     * 批量新增学历学位信息
     *
     * @param sysUserEducationAndDegreeInfoList 学历学位信息列表
     * @return 结果
     */
    public int batchSysUserEducationAndDegreeInfo(List<SysUserEducationAndDegreeInfo> sysUserEducationAndDegreeInfoList);


    /**
     * 通过用户信息ID删除学历学位信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserEducationAndDegreeInfoByUserId(Long userId);

    /**
     * 批量删除奖惩信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserRewardsAndPenaltiesInfoByUserIds(Long[] userIds);

    /**
     * 批量新增奖惩信息
     *
     * @param sysUserRewardsAndPenaltiesInfoList 奖惩信息列表
     * @return 结果
     */
    public int batchSysUserRewardsAndPenaltiesInfo(List<SysUserRewardsAndPenaltiesInfo> sysUserRewardsAndPenaltiesInfoList);


    /**
     * 通过用户信息ID删除奖惩信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserRewardsAndPenaltiesInfoByUserId(Long userId);

    /**
     * 批量删除用户其他信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserOtherInfoByUserIds(Long[] userIds);

    /**
     * 批量新增用户其他信息
     *
     * @param sysUserOtherInfoList 用户其他信息列表
     * @return 结果
     */
    public int batchSysUserOtherInfo(List<SysUserOtherInfo> sysUserOtherInfoList);


    /**
     * 通过用户信息ID删除用户其他信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserOtherInfoByUserId(Long userId);

    /**
     * 批量删除用户年度考核信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserAnnualAppraisalInfoByUserIds(Long[] userIds);

    /**
     * 批量新增用户年度考核信息
     *
     * @param sysUserAnnualAppraisalInfoList 用户年度考核信息列表
     * @return 结果
     */
    public int batchSysUserAnnualAppraisalInfo(List<SysUserAnnualAppraisalInfo> sysUserAnnualAppraisalInfoList);


    /**
     * 通过用户信息ID删除用户年度考核信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserAnnualAppraisalInfoByUserId(Long userId);

    /**
     * 批量删除家庭成员及主要社会关系信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserFamilyMemberSocialInfoByUserIds(Long[] userIds);

    /**
     * 批量新增家庭成员及主要社会关系信息
     *
     * @param sysUserFamilyMemberSocialInfoList 家庭成员及主要社会关系信息列表
     * @return 结果
     */
    public int batchSysUserFamilyMemberSocialInfo(List<SysUserFamilyMemberSocialInfo> sysUserFamilyMemberSocialInfoList);


    /**
     * 通过用户信息ID删除家庭成员及主要社会关系信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserFamilyMemberSocialInfoByUserId(Long userId);

    /**
     * 批量删除用户简历信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserResumeInfoByUserIds(Long[] userIds);

    /**
     * 批量新增用户简历信息
     *
     * @param sysUserResumeInfoList 用户简历信息列表
     * @return 结果
     */
    public int batchSysUserResumeInfo(List<SysUserResumeInfo> sysUserResumeInfoList);


    /**
     * 通过用户信息ID删除用户简历信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserResumeInfoByUserId(Long userId);

    /**
     * 批量删除工作单位及职务信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserWorkUnitAndPositionInfoByUserIds(Long[] userIds);

    /**
     * 批量新增工作单位及职务信息
     *
     * @param sysUserWorkUnitAndPositionInfoList 工作单位及职务信息列表
     * @return 结果
     */
    public int batchSysUserWorkUnitAndPositionInfo(List<SysUserWorkUnitAndPositionInfo> sysUserWorkUnitAndPositionInfoList);


    /**
     * 通过用户信息ID删除工作单位及职务信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserWorkUnitAndPositionInfoByUserId(Long userId);

    /**
     * 批量删除用户现职级信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserCurrentPostInfoByUserIds(Long[] userIds);

    /**
     * 批量新增用户现职级信息
     *
     * @param sysUserCurrentPostInfoList 用户现职级信息列表
     * @return 结果
     */
    public int batchSysUserCurrentPostInfo(List<SysUserCurrentPostInfo> sysUserCurrentPostInfoList);


    /**
     * 通过用户信息ID删除用户现职级信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserCurrentPostInfoByUserId(Long userId);

    /**
     * 批量删除用户基层工作经历信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserGrassrootsWorkInfoByUserIds(Long[] userIds);

    /**
     * 批量新增用户基层工作经历信息
     *
     * @param sysUserGrassrootsWorkInfoList 用户基层工作经历信息列表
     * @return 结果
     */
    public int batchSysUserGrassrootsWorkInfo(List<SysUserGrassrootsWorkInfo> sysUserGrassrootsWorkInfoList);


    /**
     * 通过用户信息ID删除用户基层工作经历信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserGrassrootsWorkInfoByUserId(Long userId);

    public List<SysUserEducationAndDegreeInfo> selectEducationAndDegreeInfoByUserId(Long userId);

    public List<SysUserEducationAndDegreeInfo> selectEducationAndDegreeInfoByUserIds(@Param("userIds") List<Long> userIds);

    public List<SysUserRewardsAndPenaltiesInfo> selectRewardsAndPenaltiesInfoByUserId(Long userId);

    public List<SysUserOtherInfo> selectOtherInfoByUserId(Long userId);

    public List<SysUserAnnualAppraisalInfo> selectAnnualAppraisalInfoByUserId(Long userId);

    public List<SysUserFamilyMemberSocialInfo> selectFamilyMemberSocialInfoByUserId(Long userId);

    public List<SysUserResumeInfo> selectResumeInfoByUserId(Long userId);

    public List<SysUserWorkUnitAndPositionInfo> selectWorkUnitAndPositionInfoByUserId(Long userId);

    public List<SysUserCurrentPostInfo> selectCurrentPostInfoByUserId(Long userId);

    public List<SysUserGrassrootsWorkInfo> selectGrassrootsWorkInfoByUserId(Long userId);


    public List<Long> selectUserIdsByIdentityTypeAndPost(@Param("deptIds")List<Long> deptIds,@Param("types") List<String> types);

    public List<SysUser> selectUserListByCondition(@Param("condition")String condition, @Param("deptIds")List<Long> deptIds);

    public List<SysUser> selectAll();

    /**
     * 批量删除人员借调工作信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserSecondmentWorkInfoByUserIds(Long[] userIds);

    /**
     * 批量新增人员借调工作信息
     *
     * @param sysUserSecondmentWorkInfoList 人员借调工作信息列表
     * @return 结果
     */
    public int batchSysUserSecondmentWorkInfo(List<SysUserSecondmentWorkInfo> sysUserSecondmentWorkInfoList);


    /**
     * 通过用户信息ID删除人员借调工作信息信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSysUserSecondmentWorkInfoByUserId(Long userId);

    public List<SysUserSecondmentWorkInfo> selectSecondmentWorkInfoByUserId(Long userId);

    /**
     * 根据姓名查询用户列表
     * @param user
     * @return
     */
    public List<SysUser> selectUserListByName(SysUser user);

    /**
     * 根据姓名列表查询用户列表
     * @param userNames
     * @return
     */
    public List<SysUser> selectUserListByNames(@Param("userNames") List<String> userNames);

    /**
     * 查询部门的主要负责人
     * @param deptId
     * @return
     */
    public List<SysUser> selectMainLeaderByDeptId(@Param("deptId") Long deptId);

    /**
     * 查询部门下的用户
     * @param deptId
     * @return
     */
    public List<SysUser> selectUserListByDeptId(@Param("deptId") Long deptId);

    /**
     * 查询部门分管领导的userId
     * @param deptId
     * @return
     */
    public Long selectDeptLeaderChargeUserId(@Param("deptId") Long deptId, @Param("roleIds") List<Long> roleIds, @Param("startTop") boolean startTop);

    /**
     * 根据角色key查询用户列表
     * @param roleKey
     * @return
     */
    public List<SysUser> listUserByRoleKey(@Param("roleKey") String roleKey);

    /**
     * 批量修改用户部门
     * @param userIds
     * @param deptId
     * @return
     */
    public int updateDeptByUserIds(SysUser user);

    /**
     * 根据用户登录名和部门查询用户信息
     * @param userName
     * @param deptName
     * @return
     */
    SysUser selectUserByUserNameAndDeptName(@Param("userName") String userName, @Param("deptName") String deptName);

    /**
     * 查询干部标签用户信息列表
     * @param user
     * @return
     */
    List<SysUser> selectUserByAbilityLabel(SysUser user);

    List<SysUser> selectUserByNickName(@Param("nickName") String nickName);

    List<SysUser> selectUserByNickNameAndDeptId(@Param("nickName")String nickName, @Param("deptId") Long deptId);

    List<SysUser> selectMainLeaderList(SysUser user);

    public int updateUserByUserId(@Param("userId") Long userId, @Param("positionShort") String positionShort);

    //定时生成公休假天数查询人员信息
    List<SysUser> selectUserListByAdminBalance(SysUser user);

    List<SysUser> exportUserInfoData(Map<String, Object> params);

    /**
     * 查询人员头像
     * @param params
     * @return
     */
    List<String> selectUserAvatars();

    /**
     * 查询已删除的用户列表
     * @param user
     * @return
     */
    List<SysUser> selectDeleteUserListByAdmin(SysUser user);

    /**
     * 恢复用户信息
     * @param userIds
     * @return
     */
    public int recoverUserByIds(Long[] userIds);

    /**
     * 彻底删除用户信息
     * @param userIds
     * @return
     */
    public int completelyDeleteUserByIds(Long[] userIds);

    /**
     * 还原所有删除的用户数据
     * @param user
     * @return
     */
    public int recoverAllUserByIds();

    /**
     * 彻底删除所有用户数据
     * @param user
     * @return
     */
    public  int completelyDeleteAllUser();
}
