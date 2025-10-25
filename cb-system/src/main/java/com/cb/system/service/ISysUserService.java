package com.cb.system.service;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.DataScope;
import com.cb.common.core.domain.entity.*;
import com.cb.common.core.domain.vo.*;
import com.cb.system.vo.WordUserVo;

import java.io.InputStream;
import java.util.List;

/**
 * 用户 业务层
 *
 * @author ruoyi
 */
public interface ISysUserService
{
    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);
    public List<SysUser> selectAllUserList();

    /**
     * 根据条件分页查询用户列表(管理员)
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserListByAdmin(SysUser user);

    /**
     * 获取联络员用户列表
     * @param user
     * @return
     */
    public List<SysUser> selectLiaisonInfoList(SysUser user);

    /**
     *
     * @param user
     * @return
     */
    public List<SysUser> listCadre(SysUser user);

    public List<SysUser> selectRetireesUserList(SysUser user);

    List<SysUser> selectUserListNoScpoe(SysUser user);

    @DataScope(deptAlias = "d", userAlias = "u")
    List<SysUser> selectUserListByIdentityType(SysUser user);

    public List<SysUser> archivesUserList(SysUser user);

    /**
     * 根据登录用户名拼接字符串获取用于显示的用户名拼接字符串
     * @param loginNames
     * @return
     */
    String getNameStrByLoginNames(String loginNames);

    String getNameStrByLoginNameList(List<String> loginNameList);

    String getUserIdStrByLoginNameList(List<String> loginNameList);

    String getNameStrByUserIdList(List<Long> userIdList);
    List<SysUser> selectUserListByUserIds(List<Long> userIdList);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过用户名及部门查询用户
     *
     * @param userName 用户名
     * @param deptName 部门
     * @return 用户对象信息
     */
    public SysUser selectUserByUserNameAndDeptName(String userName,String deptName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);
    public SysUser selectUserByUserId(Long userId);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectUserRoleGroup(String userName);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectUserPostGroup(String userName);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public String checkUserNameUnique(String userName, Long userId);

    /**
     * 校验身份证号码是否唯一
     *
     * @param idcard 身份证号码
     * @return 结果
     */
    public String checkIdcardUnique(String idcard, Long userId);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(SysUser user);

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
    public int updateUserByUserId(Long userId, String positionShort);

    public int encryptMobile(SysUser user);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserStatus(SysUser user);

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserProfile(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateUserAvatar(String userName, String avatar);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    public int resetPwd(SysUser user);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(String userName, String password);

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
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);

    public String importUser2(List<ImportUserVo> userList, Boolean isUpdateSupport, String operName);
    public String importUser3(List<SysUserShiYeVo> userList, Boolean isUpdateSupport, String operName);
     public String importUser4(List<SysUserHeTongVo> userList, Boolean updateSupport, String operName);

    public int permissionSet(SysUser user);

    public SysUser selectUserByUserNameAndPassword(String userName,String password);


    public int updateDeptId(Long userId,Long deptId);

    public List<SysUser> userSelectorList(SysUser user);
    public List<SysUser> selectorUserList(SysUser user);

    public List<VSysUser> selectVSysUserList(VSysUser user);
    public List<VSysUser> selectAllVSysUserList();
    public List<VSysUser> selectVSysUserListByRoles(VSysUser user);

    public List<SysUserTechnicalPositionInfo> selectTechnicalPositionInfoByUserId(Long userId);

    public List<SysUserEducationAndDegreeInfo> selectEducationAndDegreeInfoByUserId(Long userId);

    public List<SysUserEducationAndDegreeInfo> selectEducationAndDegreeInfoByUserIds(List<Long> userIds);

    public List<SysUserRewardsAndPenaltiesInfo> selectRewardsAndPenaltiesInfoByUserId(Long userId);

    public List<SysUserOtherInfo> selectOtherInfoByUserId(Long userId);

    public List<SysUserAnnualAppraisalInfo> selectAnnualAppraisalInfoByUserId(Long userId);

    public List<SysUserFamilyMemberSocialInfo> selectFamilyMemberSocialInfoByUserId(Long userId);

    public List<SysUserResumeInfo> selectResumeInfoByUserId(Long userId);

    public List<SysUserWorkUnitAndPositionInfo> selectWorkUnitAndPositionInfoByUserId(Long userId);

    public List<SysUserSecondmentWorkInfo> selectSecondmentWorkInfoByUserId(Long userId);

    public LrmxPerson readLrmxFile(InputStream inputStream);

    public byte[] exportLrmxFile(List<SysUser> sysUsers);

    public JSONObject importFromLrmx(List<LrmxPerson> personList);

    /**
     * 通过身份证查询用户
     *
     * @param idcard 身份证
     * @return 用户对象信息
     */
    @Deprecated
    public SysUser selectUserByIdcard(String idcard);

    public SysUser selectUserByMobile(String mobile);

    public List<SysUserCurrentPostInfo> selectCurrentPostInfoByUserId(Long userId);

    public List<SysUserGrassrootsWorkInfo> selectGrassrootsWorkInfoByUserId(Long userId);

    public List<Long> selectUserIdsByIdentityTypeAndPost(List<Long> deptIds,List<String> types);

    public List<SysUser> selectUserListByCondition(String condition, List<Long> deptIds);

    public Boolean isLeaderInCharge();


    public int synchronousDept(SysUser user);

    /**
     * 根据姓名查询用户列表
     * @param user
     * @return
     */
    public List<SysUser> selectUserListByName(SysUser user);

    List<SysUser> selectUserListByNames(List<String> userNames);

    /**
     * 查询部门的主要负责人
     * @param deptId
     * @return
     */
    List<SysUser> selectMainLeaderByDeptId(Long deptId);

    /**
     * 查询部门的分管领导
     * @param deptId
     * @return
     */
    SysUser selectDeptLeaderCharge(Long deptId, List<Long> roleIds, boolean startTop);

    /**
     * 根据有指定角色key的用户列表
     * @param roleKey
     * @return
     */
    List<SysUser> listUserByRoleKey(String roleKey);

    /**
     * 导入word用户
     * @param wordUserVo
     * @return
     */
    int importWordUser(WordUserVo wordUserVo);

    /**
     * 批量修改用户部门
     * @param user
     * @return
     */
    public int updateDeptByUserIds(SysUser user);

    /**
     * 根据用户标签ID查询用户列表
     * @param user
     * @return
     */
    List<SysUser> selectUserByAbilityLabel(SysUser user);

    /**
     * 根据用户姓名查询用户信息
     * @param nickName
     * @return
     */
    List<SysUser>  selectUserByNickName(String nickName);


    List<SysUser> selectUserByNickNameAndDeptId(String nickName, Long deptId);

    List<SysUser> selectMainLeaderList(SysUser user);

    List<SysUser> selectUserListByAdminBalance(SysUser user);

    List<SysUser> selectDeleteUserListByAdmin(SysUser user);

    /**
     * 恢复用户信息
     * @param userIds
     * @return
     */
    public int recoverUserByIds(Long[] userIds);

    /**
     * 彻底删除用户数据
     * @param userIds
     * @return
     */
    public int completelyDeleteUserByIds(Long[] userIds);

    /**
     * 恢复所有删除的数据
     * @param user
     * @return
     */
    public int recoverAllUserByIds();

    /**
     * 彻底删除所有用户数据
     * @param user
     * @return
     */
    public  int completelyDeleteAllUserByIds();

    /**
     * 储备干部设置
     * @param user
     * @return
     */
    public int updateReserveUser(SysUser user);

    /**
     * 修改申请记录通过后更新用户信息
     * @param user
     */
    public int updateByUserInfoApply(SysUser user);

}
