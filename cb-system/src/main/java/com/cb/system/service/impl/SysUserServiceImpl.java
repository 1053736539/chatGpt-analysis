package com.cb.system.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.DataScope;
import com.cb.common.annotation.EncryptMethod;
import com.cb.common.constant.UserConstants;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.*;
import com.cb.common.core.domain.vo.*;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.encipher.service.IEncipherService;
import com.cb.common.enums.EODType;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.bean.BeanUtils;
import com.cb.common.utils.spring.SpringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.domain.*;
import com.cb.system.mapper.*;
import com.cb.system.service.*;
import com.cb.system.util.WordImportContextHolder;
import com.cb.system.util.WordImportUtil;
import com.cb.system.vo.WordUserVo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISysUserPwdModifyService pwdModifyService;

    @Autowired
    private SysUserDeptPostMapper userDeptMapper;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysDictTypeService sysDictTypeService;
    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserInfoChangeLogService sysUserInfoChangeLogService;

    @Autowired
    private ISysUserDeptPostService userDeptPostService;


    private IEncipherService encipherService = SpringUtils.getBean(IEncipherService.class);


    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectUserList(SysUser user) {
        String phonenumber = user.getPhonenumber();
        if (StringUtils.isNotBlank(phonenumber)) {
            user.setPhonenumber(encipherService.sm4EncryptEcb(phonenumber));
        }
        String str = "";
        if(user.getAbilityLabelIds()!=null && user.getAbilityLabelIds().length>0){
            for (String AbilityLabel1 : user.getAbilityLabelIds()) {
                str = str + AbilityLabel1 + ',';
            }
            user.setAbilityLabel(str.substring(0, str.length()-1));
        }
        if(StringUtils.isNotBlank(user.getName())){
            user.setDeptId(null);
        }
        return userMapper.selectUserList(user);
    }

    @Override
    public List<SysUser> selectAllUserList() {
        return userMapper.selectAllUserList();
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectUserListByAdmin(SysUser user) {
        String phonenumber = user.getPhonenumber();
        if (StringUtils.isNotBlank(phonenumber)) {
            user.setPhonenumber(encipherService.sm4EncryptEcb( phonenumber));
        }
//        if(StringUtils.isNotBlank(user.getName())){
//            user.setDeptId(null);
//        }
        prepareNameSearch(user);
        return userMapper.selectUserListByAdmin(user);
    }


    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectDeleteUserListByAdmin(SysUser user) {
        String phonenumber = user.getPhonenumber();
        if (StringUtils.isNotBlank(phonenumber)) {
            user.setPhonenumber(encipherService.sm4EncryptEcb( phonenumber));
        }
//        if(StringUtils.isNotBlank(user.getName())){
//            user.setDeptId(null);
//        }
        prepareNameSearch(user);
        return userMapper.selectDeleteUserListByAdmin(user);
    }


    /**
     * 准备姓名检索条件，支持汉字和拼音首字母模糊查询。
     *
     * @param user 用户查询参数
     */
    private void prepareNameSearch(SysUser user) {
        if (StringUtils.isBlank(user.getName())) {
            return;
        }
        user.setDeptId(null);
        Map<String, Object> params = user.getParams();
        String compactName = user.getName().replaceAll("\\s+", "");
        if (StringUtils.isBlank(compactName)) {
            return;
        }
        String pinyin = containsChinese(compactName) ? StringUtils.getPingYin(compactName) : compactName;
        pinyin = pinyin.toLowerCase(Locale.ROOT);
        params.put("namePinyin", pinyin);
        params.put("namePinyinFuzzy", buildSequentialLike(pinyin));
    }

    private String buildSequentialLike(String value) {
        StringBuilder pattern = new StringBuilder("%");
        for (char c : value.toCharArray()) {
            pattern.append(c).append('%');
        }
        return pattern.toString();
    }

    private boolean containsChinese(String value) {
        return value.codePoints().anyMatch(codePoint -> codePoint >= 0x4E00 && codePoint <= 0x9FA5);
    }



    @Override
    public int recoverUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
        return userMapper.recoverUserByIds(userIds);
    }

    @Override
    public int completelyDeleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
        // 删除用户与角色关联
        userRoleMapper.deleteUserRole(userIds);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPost(userIds);
        // 删除用户多部门关联
        userDeptMapper.deleteByUserIds(userIds);
        // 删除用户专业技术职务信息
        userMapper.deleteSysUserTechnicalPositionInfoByUserIds(userIds);
        // 删除用户学历学位信息
        userMapper.deleteSysUserEducationAndDegreeInfoByUserIds(userIds);
        // 删除用户奖惩信息
        userMapper.deleteSysUserRewardsAndPenaltiesInfoByUserIds(userIds);
        // 删除用户其他信息
        userMapper.deleteSysUserOtherInfoByUserIds(userIds);
        // 删除用户年度考核信息
        userMapper.deleteSysUserAnnualAppraisalInfoByUserIds(userIds);
        // 删除用户家庭成员及主要社会关系信息
        userMapper.deleteSysUserFamilyMemberSocialInfoByUserIds(userIds);
        // 删除用户简历信息
        userMapper.deleteSysUserResumeInfoByUserIds(userIds);
        // 删除用户工作单位及职务信息
        userMapper.deleteSysUserWorkUnitAndPositionInfoByUserIds(userIds);
        // 删除用户现职级信息
        userMapper.deleteSysUserCurrentPostInfoByUserIds(userIds);
        // 删除用户基层工作经历信息
        userMapper.deleteSysUserGrassrootsWorkInfoByUserIds(userIds);
        // 删除借调工作人员信息
        userMapper.deleteSysUserSecondmentWorkInfoByUserIds(userIds);

        return userMapper.completelyDeleteUserByIds(userIds);
    }

    @Override
    public int recoverAllUserByIds() {
        return userMapper.recoverAllUserByIds();
    }

    @Override
    public int completelyDeleteAllUserByIds() {
        SysUser user =new SysUser();
        List<SysUser> list= userMapper.selectDeleteUserListByAdmin(user);
        Long userId;
        for (SysUser user1:list){
             userId= user1.getUserId();
            checkUserAllowed(new SysUser(userId));
            // 删除用户与角色关联
            userRoleMapper.deleteUserRoleByUserId(userId);
            // 删除用户与岗位表
            userPostMapper.deleteUserPostByUserId(userId);
            // 删除多部门信息关联表
            userDeptMapper.deleteByUserId(userId);
            // 通过用户ID删除用户专业技术职务信息
            userMapper.deleteSysUserTechnicalPositionInfoByUserId(userId);
            // 通过用户ID删除学历学位信息
            userMapper.deleteSysUserEducationAndDegreeInfoByUserId(userId);
            // 通过用户ID删除奖惩信息
            userMapper.deleteSysUserRewardsAndPenaltiesInfoByUserId(userId);
            // 通过用户ID删除用户其他信息
            userMapper.deleteSysUserOtherInfoByUserId(userId);
            // 通过用户ID删除年度考核信息
            userMapper.deleteSysUserAnnualAppraisalInfoByUserId(userId);
            // 通过用户ID删除家庭成员及主要社会关系信息
            userMapper.deleteSysUserFamilyMemberSocialInfoByUserId(userId);
            // 通过用户ID删除简历信息Id
            userMapper.deleteSysUserResumeInfoByUserId(userId);
            // 通过用户ID删除工作单位及职务信息
            userMapper.deleteSysUserWorkUnitAndPositionInfoByUserId(userId);
            // 通过用户ID删除现职级信息
            userMapper.deleteSysUserCurrentPostInfoByUserId(userId);
            // 通过用户ID删除基层工作经历信息
            userMapper.deleteSysUserGrassrootsWorkInfoByUserId(userId);
            // 通过用户ID删除借调工作人员信息
            userMapper.deleteSysUserSecondmentWorkInfoByUserId(userId);
        }
        return userMapper.completelyDeleteAllUser();
    }


    @Override
//    @DataScope(deptAlias = "d", userAlias = "u")
//    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectUserListByAdminBalance(SysUser user) {
        return userMapper.selectUserListByAdminBalance(user);
    }

    /**
     * 获取联络员用户列表
     *
     * @param user
     * @return
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectLiaisonInfoList(SysUser user) {
        String phonenumber = user.getPhonenumber();
        if (StringUtils.isNotBlank(phonenumber)) {
            user.setPhonenumber(encipherService.sm4EncryptEcb(phonenumber));
        }
        return userMapper.selectLiaisonInfoList(user);
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> listCadre(SysUser user) {
        return userMapper.listCadre(user);
    }

    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectRetireesUserList(SysUser user) {
        return userMapper.selectRetireesUserList(user);
    }

    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectUserListNoScpoe(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectUserListByIdentityType(SysUser user) {
        return userMapper.selectUserListByIdentityType(user);
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> archivesUserList(SysUser user) {
        return userMapper.archivesUserList(user);
    }

    @Override
    public String getNameStrByLoginNames(String loginNames) {
        String[] loginNameArr = loginNames.split(",");
        return getNameStrByLoginNameArr(loginNameArr);
    }

    @Override
    public String getNameStrByLoginNameList(List<String> loginNameList) {
        String[] loginNameArr = loginNameList.stream().toArray(String[]::new);
        return getNameStrByLoginNameArr(loginNameArr);
    }

    private String getNameStrByLoginNameArr(String[] loginNameArr) {
        if (loginNameArr.length < 1) {
            return "";
        }
        List<SysUser> userList = userMapper.selectUserListByLoginNames(loginNameArr);
        return userList.stream().map(SysUser::getName).collect(Collectors.joining(","));
    }

    @Override
    public String getUserIdStrByLoginNameList(List<String> loginNameList) {
        String[] loginNameArr = loginNameList.stream().toArray(String[]::new);
        if (loginNameArr.length < 1) {
            return "";
        }
        List<SysUser> userList = userMapper.selectUserListByLoginNames(loginNameArr);
        return userList.stream().map(i -> i.getUserId().toString()).collect(Collectors.joining(","));
    }

    @Override
    public String getNameStrByUserIdList(List<Long> userIdList) {
        if (null == userIdList || userIdList.isEmpty()) {
            return "";
        }
        Map<Long, String> map = userMapper.selectUserListByUserIds(userIdList).stream().collect(Collectors.toMap(SysUser::getUserId, SysUser::getName));
        String names = userIdList.stream().map(userId -> {
            String name = map.get(userId);
            return name;
        }).filter(Objects::nonNull).collect(Collectors.joining("、"));
        return names;
    }

    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<SysUser> selectUserListByUserIds(List<Long> userIdList) {
        return userMapper.selectUserListByUserIds(userIdList);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public SysUser selectUserByUserNameAndDeptName(String userName, String deptName) {
        return userMapper.selectUserByUserNameAndDeptName(userName,deptName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }
    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public SysUser selectUserByUserId(Long userId) {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<SysRole> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName) {
        List<SysPost> list = postMapper.selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list) {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName, Long userId) {
        int count = userMapper.checkUserNameUnique(userName, userId);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkIdcardUnique(String idcard, Long userId) {
        int count = userMapper.checkIdcardUnique(idcard, userId);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber(), userId);
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
            throw new CustomException("不允许操作系统管理员用户");
        }
        if (StringUtils.isNotNull(user.getUserId()) && user.isSecAdmin()) {
            throw new CustomException("不允许操作安全管理员用户");
        }
        if (StringUtils.isNotNull(user.getUserId()) && user.isAudAdmin()) {
            throw new CustomException("不允许操作安全审计员用户");
        }
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    @EncryptMethod(businessType = EODType.ENCRYPT)
    public int insertUser(SysUser user) {
        String namePy = StringUtils.getPingYin(user.getName());
        String userName = namePy;
        int idx = 1;
        while (UserConstants.NOT_UNIQUE.equals(checkUserNameUnique(userName, null))) {
            userName = namePy + idx;
            idx++;
        }
        user.setUserName(userName);
        user.setNickName(user.getName());
        user.setUserType("00");
        user.setStatus("0");
        user.setDelFlag("0");
        if(StringUtils.isBlank(user.getCreateBy())){
            try {
                user.setCreateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = configService.selectConfigByKey("sys.user.initPassword");
        }
        user.setPassword(SecurityUtils.encryptPassword(password));
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        // 新增多部门信息
        insertUserDeptPost(user);
        // 新增专业技术职务信息
        insertSysUserTechnicalPositionInfo(user);
        // 新增学历学位信息
        insertSysUserEducationAndDegreeInfo(user);
        // 新增奖惩信息
        insertSysUserRewardsAndPenaltiesInfo(user);
        // 新增用户其他信息
        insertSysUserOtherInfo(user);
        // 新增年度考核信息
        insertSysUserAnnualAppraisalInfo(user);
        // 新增家庭成员及主要社会关系信息
        insertSysUserFamilyMemberSocialInfo(user);
        // 新增简历信息
        insertSysUserResumeInfo(user);
        // 新增工作单位及职务信息
        insertSysUserWorkUnitAndPositionInfo(user);
        // 新增现职级信息
        insertSysUserCurrentPostInfo(user);
        // 新增基层工作经历信息
        insertSysUserGrassrootsWorkInfo(user);
        // 新增借调工作人员信息
        insertSysUserSecondmentWorkInfo(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    @EncryptMethod(businessType = EODType.ENCRYPT)
    public int updateUser(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户与角色关联
//        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
//        insertUserRole(user);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);
        // 删除用户多部门关联
        userDeptMapper.deleteByUserId(userId);
        // 新增用户多部门关联
        insertUserDeptPost(user);

        // 通过用户ID删除专业技术职务信息
        userMapper.deleteSysUserTechnicalPositionInfoByUserId(userId);
        // 新增用户专业技术职务信息
        insertSysUserTechnicalPositionInfo(user);
        // 通过用户ID删除学历学位信息
        userMapper.deleteSysUserEducationAndDegreeInfoByUserId(userId);
        // 新增学历学位信息
        insertSysUserEducationAndDegreeInfo(user);
        // 通过用户ID删除奖惩信息
        userMapper.deleteSysUserRewardsAndPenaltiesInfoByUserId(userId);
        // 新增奖惩信息
        insertSysUserRewardsAndPenaltiesInfo(user);
        // 通过用户ID删除用户其他信息
        userMapper.deleteSysUserOtherInfoByUserId(userId);
        // 新增用户其他信息
        insertSysUserOtherInfo(user);
        // 通过用户ID删除年度考核信息
        userMapper.deleteSysUserAnnualAppraisalInfoByUserId(userId);
        // 新增年度考核信息
        insertSysUserAnnualAppraisalInfo(user);
        // 通过用户ID删除家庭成员及主要社会关系信息
        userMapper.deleteSysUserFamilyMemberSocialInfoByUserId(userId);
        // 新增家庭成员及主要社会关系信息
        insertSysUserFamilyMemberSocialInfo(user);
        // 通过用户ID删除简历信息
        userMapper.deleteSysUserResumeInfoByUserId(userId);
        // 新增简历信息
        insertSysUserResumeInfo(user);
        // 通过用户ID删除工作单位及职务信息
        userMapper.deleteSysUserWorkUnitAndPositionInfoByUserId(userId);
        // 新增工作单位及职务信息
        insertSysUserWorkUnitAndPositionInfo(user);
        // 通过用户ID删除现职级信息
        userMapper.deleteSysUserCurrentPostInfoByUserId(userId);
        // 新增现职级信息
        insertSysUserCurrentPostInfo(user);
        // 通过用户ID删除基层工作经历信息
        userMapper.deleteSysUserGrassrootsWorkInfoByUserId(userId);
        // 新增基层工作经历信息
        insertSysUserGrassrootsWorkInfo(user);
        // 通过用户ID删除借调工作人员信息
        userMapper.deleteSysUserSecondmentWorkInfoByUserId(userId);
        // 新增借调工作人员信息
        insertSysUserSecondmentWorkInfo(user);

        return userMapper.updateUser(user);
    }

    @Override
    @EncryptMethod(businessType = EODType.ENCRYPT)
    public int encryptMobile(SysUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 设置储备干部
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateReserveUser(SysUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return userMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int resetPwd(SysUser user) {
        int i = userMapper.updateUser(user);        // 设置修改密码记录
        SysUserPwdModify pwdModify = new SysUserPwdModify();
        pwdModify.setUserId(user.getUserId());
        pwdModify.setNewPassword(user.getPassword());
        pwdModify.setModifyTime(DateUtils.getNowDate());
        pwdModify.setCreateBy(user.getUpdateBy());
        pwdModifyService.insertSysUserPwdModify(pwdModify);
        return i;
    }

//    public int updateUserByUserId(Long userId, String positionShort);
    @Override
    public int updateUserByUserId(Long userId, String positionShort) {
        return userMapper.updateUserByUserId(userId, positionShort);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password) {
        return userMapper.resetUserPwd(userName, password);
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                try {
                    userRoleMapper.batchUserRole(list);
                } catch (Exception e) {
                    log.error("用户设置角色异常！", e);
                }
            }
        } else {
            //添加默认角色 通用权限角色 task_man_role
            SysRole role = roleMapper.selectRoleByKey("task_man_role");
            if (role != null) {
                List<SysUserRole> list = new ArrayList<SysUserRole>();
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(role.getRoleId());
                list.add(ur);
                try {
                    userRoleMapper.batchUserRole(list);
                } catch (Exception e) {
                    log.error("用户设置角色异常！", e);
                }
            }
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user) {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts)) {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts) {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0) {
                userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        // 删除多部门信息关联表
        userDeptMapper.deleteByUserId(userId);
        // 通过用户ID删除用户专业技术职务信息
        userMapper.deleteSysUserTechnicalPositionInfoByUserId(userId);
        // 通过用户ID删除学历学位信息
        userMapper.deleteSysUserEducationAndDegreeInfoByUserId(userId);
        // 通过用户ID删除奖惩信息
        userMapper.deleteSysUserRewardsAndPenaltiesInfoByUserId(userId);
        // 通过用户ID删除用户其他信息
        userMapper.deleteSysUserOtherInfoByUserId(userId);
        // 通过用户ID删除年度考核信息
        userMapper.deleteSysUserAnnualAppraisalInfoByUserId(userId);
        // 通过用户ID删除家庭成员及主要社会关系信息
        userMapper.deleteSysUserFamilyMemberSocialInfoByUserId(userId);
        // 通过用户ID删除简历信息Id
        userMapper.deleteSysUserResumeInfoByUserId(userId);
        // 通过用户ID删除工作单位及职务信息
        userMapper.deleteSysUserWorkUnitAndPositionInfoByUserId(userId);
        // 通过用户ID删除现职级信息
        userMapper.deleteSysUserCurrentPostInfoByUserId(userId);
        // 通过用户ID删除基层工作经历信息
        userMapper.deleteSysUserGrassrootsWorkInfoByUserId(userId);
        // 通过用户ID删除借调工作人员信息
        userMapper.deleteSysUserSecondmentWorkInfoByUserId(userId);

        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息（修改用户表删除状态 = 2）
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
//        // 删除用户与角色关联
//        userRoleMapper.deleteUserRole(userIds);
//        // 删除用户与岗位关联
//        userPostMapper.deleteUserPost(userIds);
//        // 删除用户多部门关联
//        userDeptMapper.deleteByUserIds(userIds);
//        // 删除用户专业技术职务信息
//        userMapper.deleteSysUserTechnicalPositionInfoByUserIds(userIds);
//        // 删除用户学历学位信息
//        userMapper.deleteSysUserEducationAndDegreeInfoByUserIds(userIds);
//        // 删除用户奖惩信息
//        userMapper.deleteSysUserRewardsAndPenaltiesInfoByUserIds(userIds);
//        // 删除用户其他信息
//        userMapper.deleteSysUserOtherInfoByUserIds(userIds);
//        // 删除用户年度考核信息
//        userMapper.deleteSysUserAnnualAppraisalInfoByUserIds(userIds);
//        // 删除用户家庭成员及主要社会关系信息
//        userMapper.deleteSysUserFamilyMemberSocialInfoByUserIds(userIds);
//        // 删除用户简历信息
//        userMapper.deleteSysUserResumeInfoByUserIds(userIds);
//        // 删除用户工作单位及职务信息
//        userMapper.deleteSysUserWorkUnitAndPositionInfoByUserIds(userIds);
//        // 删除用户现职级信息
//        userMapper.deleteSysUserCurrentPostInfoByUserIds(userIds);
//        // 删除用户基层工作经历信息
//        userMapper.deleteSysUserGrassrootsWorkInfoByUserIds(userIds);
//        // 删除借调工作人员信息
//        userMapper.deleteSysUserSecondmentWorkInfoByUserIds(userIds);

        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SysUser user : userList) {
            try {
                // 验证是否存在这个用户
                SysUser u = userMapper.selectUserByUserName(user.getUserName());
                if (StringUtils.isNull(u)) {
                    user.setPassword(SecurityUtils.encryptPassword(password));
                    user.setCreateBy(operName);
//                    this.insertUser(user);
                    this.importUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String importUser2(List<ImportUserVo> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        SysUser user;
        Map<String, SysDept> deptMap = sysDeptService.getDeptMapByCode();
        Map<String, SysDictData> identityTypeDataMap = sysDictTypeService.selectDictMapByLabel("preparation_type");
        SysDictData dictData;
        List<UserDeptPost> deptPostList;
        List<SysUserTechnicalPositionInfo> positionInfos;
        UserDeptPost userDeptPost;
        List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos;
        SysUserEducationAndDegreeInfo educationInfo;
        List<SysUserCurrentPostInfo> sysUserCurrentPostInfoList;
        SysUserCurrentPostInfo currentPostInfo;
        String deptCode, val;
        SysDept sysDept;
        SysRole defaultRole = roleMapper.checkRoleKeyUnique("task_man_role");
        for (ImportUserVo userVo : userList) {
            user = new SysUser();
            try {
                BeanUtils.copyBeanProp(user, userVo);
                if (StringUtils.isEmpty(user.getIsMainLeader())) {
                    user.setIsMainLeader("0");
                }
                if (StringUtils.isEmpty(user.getIsVeterans())) {
                    user.setIsVeterans("0");
                }
                if (StringUtils.isEmpty(user.getHealthCondition())) {
                    user.setHealthCondition("健康");
                }
                if (StringUtils.isEmpty(user.getIsEnrollment())) {
                    user.setIsEnrollment("1");
                }
                //政治面貌
                val = user.getPoliticalIdentity();
                if ("中共".equals(val)) {
                    val = "中共党员";
                    user.setPoliticalIdentity(val);
                }
                //民族
//                val = user.getNation();
//                if (StringUtils.isNotEmpty(val) && !val.contains("族")) {
//                    user.setNation(val + "族");
//                }
                //编制类型
                val = user.getIdentityType();
                if (identityTypeDataMap.containsKey(val)) {
                    dictData = identityTypeDataMap.get(val);
                    user.setIdentityType(dictData.getDictValue());
                }

                //部门信息
                deptCode = userVo.getDeptCode();
                sysDept = deptMap.get(deptCode);
                if (sysDept != null) {
                    deptPostList = new ArrayList<>();
                    userDeptPost = new UserDeptPost();
                    userDeptPost.setDeptId(sysDept.getDeptId());
                    deptPostList.add(userDeptPost);
                    user.setUserDeptPostList(deptPostList);
                    user.setDeptId(sysDept.getDeptId());
                }

                //专业技术职务
//                val = userVo.getPosition();
//                if (StringUtils.isNotEmpty(val)) {
//                    positionInfos = new ArrayList<>();
//                    SysUserTechnicalPositionInfo positionInfo = new SysUserTechnicalPositionInfo();
//                    positionInfo.setTechnicalQualificationName(val);
//                    positionInfo.setQualificationDate(userVo.getPositionTime());
//                    positionInfos.add(positionInfo);
//                    user.setPositionInfos(positionInfos);
//                }

                //学历，学位
                String degree = "";
                educationAndDegreeInfos = new ArrayList<>();
                val = userVo.getEducation();
                if (StringUtils.isNotEmpty(val)) {
                    val = val.replaceAll("\n", "");
                    if (val.contains("硕士")) {
                        degree = "硕士";
                    } else if (val.contains("博士")) {
                        degree = "博士";
                    } else if (val.contains("大学")) {
                        degree = "学士";
                    }
                    if (val.contains("硕士") || val.contains("研究生")) {
                        val = "研究生";
                    }
                    educationInfo = new SysUserEducationAndDegreeInfo();
                    educationInfo.setEducationCategory("1");
                    educationInfo.setEducation(val);
                    educationInfo.setEducationalName(val);
                    educationInfo.setDegree(degree);
                    educationInfo.setDegreeName(degree);
                    educationInfo.setSchoolAndDepartment(userVo.getSchool());
                    educationInfo.setProfessionalName(userVo.getMajor());
                    educationInfo.setCompletionDate(userVo.getGraduationTime());
                    educationAndDegreeInfos.add(educationInfo);
                }
                val = userVo.getEducation1();
                if (StringUtils.isNotEmpty(val)) {
                    val = val.replaceAll("\n", "");
                    degree = "";
                    if (val.contains("硕士")) {
                        degree = "硕士";
                    } else if (val.contains("博士")) {
                        degree = "博士";
                    } else if (val.contains("大学")) {
                        degree = "学士";
                    }
                    if (val.contains("硕士") || val.contains("研究生")) {
                        val = "研究生";
                    }
                    educationInfo = new SysUserEducationAndDegreeInfo();
                    educationInfo.setEducationCategory("1");
                    educationInfo.setEducation(val);
                    educationInfo.setEducationalName(val);
                    educationInfo.setDegree(degree);
                    educationInfo.setDegreeName(degree);
                    educationInfo.setSchoolAndDepartment(userVo.getSchool1());
                    educationInfo.setProfessionalName(userVo.getMajor1());
                    educationAndDegreeInfos.add(educationInfo);
                }
                user.setEducationAndDegreeInfos(educationAndDegreeInfos);
                //职级信息
                val = userVo.getWorkTitle();
                if (StringUtils.isNotEmpty(val)) {
                    sysUserCurrentPostInfoList = new ArrayList<>();
                    currentPostInfo = new SysUserCurrentPostInfo();
                    currentPostInfo.setApprovalDate(userVo.getWorkTitleTime());
                    currentPostInfo.setRank(userVo.getWorkTitleCode());
                    currentPostInfo.setPostLevelStatus("1");
                    currentPostInfo.setPeerLevel(userVo.getSameWorkTitleTime());
                    sysUserCurrentPostInfoList.add(currentPostInfo);
                    user.setCurrentPostInfos(sysUserCurrentPostInfoList);
                }
                // 验证是否存在这个用户
                SysUser u = null;
                String mobile = user.getPhonenumber();
                if (StringUtils.isNotEmpty(mobile)) {
                    //兼容数据库里面没有加密的历史记录
                    u = selectUserByMobile(mobile);
                    mobile = encipherService.sm4EncryptEcb(mobile);
                    if (u == null) {
                        u = selectUserByMobile(mobile);
                    }
                }
                user.setPhonenumber(mobile);
                if (StringUtils.isNull(u)) {
                    user.setIsHostingWork("0");
                    user.setPersonnelStatus("1");
                    user.setPassword(password);
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    setDefaultRole(user, defaultRole);
//                    this.importUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + user.getName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUserId(u.getUserId());
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + user.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 " + user.getName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、姓名 " + user.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String importUser3(List<SysUserShiYeVo> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        SysUser user;
        Map<String, SysDept> deptMap = sysDeptService.getDeptMapByCode();
        Map<String, SysDictData> identityTypeDataMap = sysDictTypeService.selectDictMapByLabel("preparation_type");
        SysDictData dictData;
        List<UserDeptPost> deptPostList;
        List<SysUserTechnicalPositionInfo> positionInfos;
        UserDeptPost userDeptPost;
        List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos;
        SysUserEducationAndDegreeInfo educationInfo;
        List<SysUserCurrentPostInfo> sysUserCurrentPostInfoList;
        SysUserCurrentPostInfo currentPostInfo;
        String deptCode, val;
        SysDept sysDept;
        SysRole defaultRole = roleMapper.checkRoleKeyUnique("task_man_role");
        for (SysUserShiYeVo userVo : userList) {
            user = new SysUser();
            try {
                BeanUtils.copyBeanProp(user, userVo);
                if (StringUtils.isEmpty(user.getIsMainLeader())) {
                    user.setIsMainLeader("0");
                }
                if (StringUtils.isEmpty(user.getIsVeterans())) {
                    user.setIsVeterans("0");
                }
                if (StringUtils.isEmpty(user.getHealthCondition())) {
                    user.setHealthCondition("健康");
                }
                if (StringUtils.isEmpty(user.getIsEnrollment())) {
                    user.setIsEnrollment("1");
                }
                //政治面貌
//                user.setPoliticalIdentity( "中共党员");

                //民族
                val = userVo.getNation();
                if (StringUtils.isNotEmpty(val) && !val.contains("族")) {
                    user.setNation(val+"族");
                }else {
                    user.setNation(val);
                }
                //编制类型 :3-事业
//                    user.setIdentityType("3");
                //编制类型
                val = userVo.getIdentityType().trim();
                if (StringUtils.isNotEmpty(val)) {
                    if(identityTypeDataMap.containsKey(val)) {
                        dictData = identityTypeDataMap.get(val);
                        user.setIdentityType(dictData.getDictValue());
                    }
                }

                //部门信息
                val = userVo.getDeptName().trim();
                SysDept dept = sysDeptService.selectDeptByDeptName(val);
                if (dept != null) {
                    user.setDeptId(dept.getDeptId());
                    deptPostList = new ArrayList<>();
                    userDeptPost = new UserDeptPost();
                    userDeptPost.setDeptId(dept.getDeptId());
                    deptPostList.add(userDeptPost);
                    user.setUserDeptPostList(deptPostList);
                    user.setDeptId(dept.getDeptId());
                }
                //学历，学位
                val = userVo.getFullTimeEducationLevel();
                if(StringUtils.isNotEmpty(val)){
                    user.setFullTimeEducationLevel(val);
                }
                val = userVo.getFullTimeEducationSchoolAndMajor();
                if(StringUtils.isNotEmpty(val)){
                    user.setFullTimeEducationSchoolAndMajor(val);
                }
                //在职学历
                val = userVo.getOnJobEducationLevel();
                if(StringUtils.isNotEmpty(val)){
                    user.setOnJobEducationLevel(val);
                }
                val = userVo.getOnJobEducationSchoolAndMajor();
                if(StringUtils.isNotEmpty(val)){
                    user.setOnJobEducationSchoolAndMajor(val);
                }
                    //职级信息
                val = userVo.getWorkTitle();
                if (StringUtils.isNotEmpty(val)) {
                    user.setWorkTitle(val);
                    user.setCurrentPosition(val);
                }
                //聘用时间=职级时间
                val = userVo.getWorkTitleTime().trim();
                if(StringUtils.isNotEmpty(val)){
                    user.setWorkTitleTime(DateUtils.dateStdFormat(val,true,"yyyy-MM-dd"));
                }
                //性别
                val = userVo.getSex();
                if(StringUtils.isNotEmpty(val)){
                    user.setSex(val);
                }
                //出生年月
                val = userVo.getBirthday().trim();
                if(StringUtils.isNotEmpty(val)){
                    user.setBirthday(DateUtils.dateStdFormat(val,true,"yyyy-MM-dd"));
                }
                //入党年月
                val = userVo.getPartyJoinTime().trim();
                if(StringUtils.isNotEmpty(val)){
                    user.setPartyJoinTime(DateUtils.dateStdFormat(val,true,"yyyy-MM-dd"));
                }
                //工作年月
                val = userVo.getStartWorkTime().trim();
                if(StringUtils.isNotEmpty(val)){
                    user.setStartWorkTime(DateUtils.dateStdFormat(val,true,"yyyy-MM-dd"));
                }

                // 验证是否存在这个用户
                SysUser u = null;
                String nickName = userVo.getNickName();
                if (StringUtils.isNotEmpty(nickName)) {
                    user.setNickName(nickName);
                    StringUtils.getPingYin(nickName);
                    String userName = StringUtils.getPingYin(nickName);;
                    user.setUserName(userName);
                    u = selectUserByUserName(userName);
                }
                if (StringUtils.isNull(u)) {
                    user.setIsHostingWork("0");
                    user.setPersonnelStatus("1");
                    user.setPassword(password);
                    user.setCreateBy(operName);
                    user.setName(nickName);
                    this.insertUser(user);
                    setDefaultRole(user, defaultRole);
//                    this.importUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + userVo.getNickName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUserId(u.getUserId());
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + userVo.getNickName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 " + userVo.getNickName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、姓名 " + userVo.getNickName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String importUser4(List<SysUserHeTongVo> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        SysUser user;
        Map<String, SysDept> deptMap = sysDeptService.getDeptMapByCode();
        Map<String, SysDictData> identityTypeDataMap = sysDictTypeService.selectDictMapByLabel("preparation_type");
        SysDictData dictData;
        List<UserDeptPost> deptPostList;
        List<SysUserTechnicalPositionInfo> positionInfos;
        UserDeptPost userDeptPost;
        List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos;
        SysUserEducationAndDegreeInfo educationInfo;
        List<SysUserCurrentPostInfo> sysUserCurrentPostInfoList;
        SysUserCurrentPostInfo currentPostInfo;
        String deptCode, val;
        SysDept sysDept;
        SysRole defaultRole = roleMapper.checkRoleKeyUnique("task_man_role");
        for (SysUserHeTongVo userVo : userList) {
            user = new SysUser();
            try {
                BeanUtils.copyBeanProp(user, userVo);
                if (StringUtils.isEmpty(user.getIsMainLeader())) {
                    user.setIsMainLeader("0");
                }
                if (StringUtils.isEmpty(user.getIsVeterans())) {
                    user.setIsVeterans("0");
                }
                if (StringUtils.isEmpty(user.getHealthCondition())) {
                    user.setHealthCondition("健康");
                }
                if (StringUtils.isEmpty(user.getIsEnrollment())) {
                    user.setIsEnrollment("1");
                }
                //政治面貌
                val = userVo.getPoliticalIdentity();
                if(StringUtils.isNotEmpty(val)){
                    user.setPoliticalIdentity(val);
                }
                //民族
                val = user.getNation();
                if (StringUtils.isNotEmpty(val) && !val.contains("族")) {
                    user.setNation(val+"族");
                }
                //编制类型 :5-合同
//                user.setIdentityType("5");
                val = userVo.getIdentityType().trim();
                if (identityTypeDataMap.containsKey(val)) {
                    dictData = identityTypeDataMap.get(val);
                    user.setIdentityType(dictData.getDictValue());
                }
                //部门信息
                val = userVo.getDeptName();
                SysDept dept = sysDeptService.selectDeptByDeptName(val);
                if (dept != null) {
                    user.setDeptId(dept.getDeptId());
                    deptPostList = new ArrayList<>();
                    userDeptPost = new UserDeptPost();
                    userDeptPost.setDeptId(dept.getDeptId());
                    deptPostList.add(userDeptPost);
                    user.setUserDeptPostList(deptPostList);
                    user.setDeptId(dept.getDeptId());
                }
                //学历，学位
                val = userVo.getFullTimeEducationLevel();
                if(StringUtils.isNotEmpty(val)){
                    user.setFullTimeEducationLevel(val);
                }
                //学校
                String school = userVo.getFullTimeEducationSchool();
                //专业
                String major= userVo.getMajor();
                if(StringUtils.isNotEmpty(school)&&StringUtils.isNotEmpty(major)){
                    user.setFullTimeEducationSchoolAndMajor(school+"（"+major+"）");
                }
                //性别
                val = userVo.getSex();
                if(StringUtils.isNotEmpty(val)){
                    user.setSex(val);
                }
                //出生年月
                val = userVo.getBirthday().trim();
                if(StringUtils.isNotEmpty(val)){
                        user.setBirthday(DateUtils.dateStdFormat(val,true,"yyyy-MM-dd"));
                    }
                //工作年月
                val = userVo.getStartWorkTime().trim();
                if(StringUtils.isNotEmpty(val)){
                        user.setStartWorkTime(DateUtils.dateStdFormat(val,true,"yyyy-MM-dd"));
                    }
                val = userVo.getRemarkHeTong();
                if(StringUtils.isNotEmpty(val)){
                    user.setRemark(val);
                }
                // 验证是否存在这个用户
                SysUser u = null;
                String mobile = userVo.getPhonenumber();
                if (StringUtils.isNotEmpty(mobile)) {
                    //兼容数据库里面没有加密的历史记录
                    u = selectUserByMobile(mobile);
                    mobile = encipherService.sm4EncryptEcb(mobile);
                    if (u == null) {
                        u = selectUserByMobile(mobile);;
                    }
                    user.setPhonenumber(mobile);
                }
                String nickName = userVo.getNickName();
                if (StringUtils.isNotEmpty(nickName)) {
                    user.setNickName(nickName);
                    StringUtils.getPingYin(nickName);
                    String userName = StringUtils.getPingYin(nickName);;
                    user.setUserName(userName);
                    u = selectUserByUserName(userName);
                }
                if (StringUtils.isNull(u)) {
                    user.setIsHostingWork("0");
                    user.setPersonnelStatus("1");
                    user.setPassword(password);
                    user.setCreateBy(operName);
                    user.setName(nickName);
                    this.insertUser(user);
                    setDefaultRole(user, defaultRole);
//                    this.importUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + userVo.getNickName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUserId(u.getUserId());
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + userVo.getNickName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 " + userVo.getNickName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、姓名 " + userVo.getNickName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    private void setDefaultRole(SysUser sysUser, SysRole role) {
        try {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(role.getRoleId());
            List<SysUserRole> userRoles = new ArrayList<>();
            userRoles.add(userRole);
            userRoleMapper.batchUserRole(userRoles);
        } catch (Exception e) {
            log.error("用户设置角色异常！", e);
        }

    }

    /**
     * 用户权限设置
     *
     * @param user
     * @return
     */
    @Override
    public int permissionSet(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        return 1;
    }


    @Override
    public SysUser selectUserByUserNameAndPassword(String userName, String password) {
        return userMapper.selectUserByUserNameAndPassword(userName, password);
    }

    @Override
    public int updateDeptId(Long userId, Long deptId) {
        return userMapper.updateDeptId(userId, deptId);
    }


    /**
     * 新增用户多部门信息
     *
     * @param user 用户对象
     */
    public void insertUserDeptPost(SysUser user) {
        List<UserDeptPost> deptPostList = user.getUserDeptPostList();
        if (!CollectionUtils.isEmpty(deptPostList)) {
            // 新增用户与岗位管理
            List<SysUserDeptPost> list = new ArrayList<>();
            for (UserDeptPost item : deptPostList) {
                SysUserDeptPost userDept = new SysUserDeptPost();
                userDept.setUserId(user.getUserId());
                userDept.setDeptId(item.getDeptId());
                userDept.setPostId(item.getPostId());
                list.add(userDept);
            }
            if (list.size() > 0) {
                userDeptMapper.batchDeptPost(list);
            }
        }
    }

    @Transactional
    public int importUser(SysUser user) {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增多部门信息
        SysUserDeptPost entity = new SysUserDeptPost();
        entity.setUserId(user.getUserId());
        entity.setDeptId(user.getDeptId());
        userDeptMapper.insertDeptPost(entity);
        return rows;
    }

    @Override
    public List<SysUser> userSelectorList(SysUser user) {
        return userMapper.userSelectorList(user);
    }

    @Override
    public List<SysUser> selectorUserList(SysUser user) {
        return userMapper.selectorUserList(user);
    }

    @Override
    public List<VSysUser> selectVSysUserList(VSysUser user) {
        return userMapper.selectVSysUserList(user);
    }

    @Override
    public List<VSysUser> selectAllVSysUserList() {
        return userMapper.selectAllVSysUserList();
    }

    @Override
    public List<VSysUser> selectVSysUserListByRoles(VSysUser user) {
        return userMapper.selectVSysUserListByRoles(user);
    }

    @Override
    public List<SysUserTechnicalPositionInfo> selectTechnicalPositionInfoByUserId(Long userId) {
        return userMapper.selectTechnicalPositionInfoByUserId(userId);
    }

    @Override
    public List<SysUserEducationAndDegreeInfo> selectEducationAndDegreeInfoByUserId(Long userId) {
        return userMapper.selectEducationAndDegreeInfoByUserId(userId);
    }

    @Override
    public List<SysUserEducationAndDegreeInfo> selectEducationAndDegreeInfoByUserIds(List<Long> userIds) {
        if (null == userIds || userIds.isEmpty()) {
            return Collections.emptyList();
        }
        return userMapper.selectEducationAndDegreeInfoByUserIds(userIds);
    }

    @Override
    public List<SysUserRewardsAndPenaltiesInfo> selectRewardsAndPenaltiesInfoByUserId(Long userId) {
        return userMapper.selectRewardsAndPenaltiesInfoByUserId(userId);
    }

    @Override
    public List<SysUserOtherInfo> selectOtherInfoByUserId(Long userId) {
        return userMapper.selectOtherInfoByUserId(userId);
    }

    @Override
    public List<SysUserAnnualAppraisalInfo> selectAnnualAppraisalInfoByUserId(Long userId) {
        return userMapper.selectAnnualAppraisalInfoByUserId(userId);
    }

    @Override
    public List<SysUserFamilyMemberSocialInfo> selectFamilyMemberSocialInfoByUserId(Long userId) {
        return userMapper.selectFamilyMemberSocialInfoByUserId(userId);
    }

    @Override
    public List<SysUserResumeInfo> selectResumeInfoByUserId(Long userId) {
        return userMapper.selectResumeInfoByUserId(userId);
    }

    @Override
    public List<SysUserWorkUnitAndPositionInfo> selectWorkUnitAndPositionInfoByUserId(Long userId) {
        return userMapper.selectWorkUnitAndPositionInfoByUserId(userId);
    }

    @Override
    public List<SysUserSecondmentWorkInfo> selectSecondmentWorkInfoByUserId(Long userId) {
        return userMapper.selectSecondmentWorkInfoByUserId(userId);
    }

    @Override
    public List<SysUserCurrentPostInfo> selectCurrentPostInfoByUserId(Long userId) {
        return userMapper.selectCurrentPostInfoByUserId(userId);
    }

    @Override
    public List<SysUserGrassrootsWorkInfo> selectGrassrootsWorkInfoByUserId(Long userId) {
        return userMapper.selectGrassrootsWorkInfoByUserId(userId);
    }

    /**
     * 新增专业技术职务信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserTechnicalPositionInfo(SysUser sysUser) {
        List<SysUserTechnicalPositionInfo> sysUserTechnicalPositionInfoList = sysUser.getPositionInfos();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserTechnicalPositionInfoList)) {
            List<SysUserTechnicalPositionInfo> list = new ArrayList<SysUserTechnicalPositionInfo>();
            for (SysUserTechnicalPositionInfo sysUserTechnicalPositionInfo : sysUserTechnicalPositionInfoList) {
                sysUserTechnicalPositionInfo.setTechnicalPositionInfoId(IdUtils.randomUUID());
                sysUserTechnicalPositionInfo.setUserId(userId);
                list.add(sysUserTechnicalPositionInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserTechnicalPositionInfo(list);
            }
        }
    }

    /**
     * 新增学历学位信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserEducationAndDegreeInfo(SysUser sysUser) {
        List<SysUserEducationAndDegreeInfo> sysUserEducationAndDegreeInfoList = sysUser.getEducationAndDegreeInfos();
        Long userId = sysUser.getUserId();
        String education = "", degree = "";
        if (StringUtils.isNotNull(sysUserEducationAndDegreeInfoList)) {
            int eduSort = Integer.MAX_VALUE, degreeSort = Integer.MAX_VALUE;
            Map<String, SysDictData> eduDictMap = sysDictTypeService.selectDictMapByLabel("education");
            Map<String, SysDictData> degreeDictMap = sysDictTypeService.selectDictMapByLabel("degree");
            SysDictData dictData;
            String tempVal;
            List<SysUserEducationAndDegreeInfo> list = new ArrayList<SysUserEducationAndDegreeInfo>();
            for (SysUserEducationAndDegreeInfo sysUserEducationAndDegreeInfo : sysUserEducationAndDegreeInfoList) {
                sysUserEducationAndDegreeInfo.setEducationAndDegreeInfoId(IdUtils.randomUUID());
                sysUserEducationAndDegreeInfo.setUserId(userId);
                list.add(sysUserEducationAndDegreeInfo);
                tempVal = sysUserEducationAndDegreeInfo.getEducation();
                if (StringUtils.isNotEmpty(tempVal) && eduDictMap.containsKey(tempVal)) {
                    dictData = eduDictMap.get(tempVal);
                    if (dictData.getDictSort() < eduSort) {
                        education = tempVal;
                        eduSort = dictData.getDictSort().intValue();
                    }
                }
                tempVal = sysUserEducationAndDegreeInfo.getDegree();
                if (StringUtils.isNotEmpty(tempVal) && degreeDictMap.containsKey(tempVal)) {
                    dictData = degreeDictMap.get(tempVal);
                    if (dictData.getDictSort() < degreeSort) {
                        degree = tempVal;
                        degreeSort = dictData.getDictSort().intValue();
                    }
                }
            }
            if (list.size() > 0) {
                userMapper.batchSysUserEducationAndDegreeInfo(list);
            }
        }
        sysUser.setEducation(education);
        sysUser.setDegree(degree);
    }

    /**
     * 新增奖惩信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserRewardsAndPenaltiesInfo(SysUser sysUser) {
        List<SysUserRewardsAndPenaltiesInfo> sysUserRewardsAndPenaltiesInfoList = sysUser.getRewardsInfos();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserRewardsAndPenaltiesInfoList)) {
            List<SysUserRewardsAndPenaltiesInfo> list = new ArrayList<SysUserRewardsAndPenaltiesInfo>();
            for (SysUserRewardsAndPenaltiesInfo sysUserRewardsAndPenaltiesInfo : sysUserRewardsAndPenaltiesInfoList) {
                sysUserRewardsAndPenaltiesInfo.setRewardsAndPenaltiesInfoId(IdUtils.randomUUID());
                sysUserRewardsAndPenaltiesInfo.setUserId(userId);
                list.add(sysUserRewardsAndPenaltiesInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserRewardsAndPenaltiesInfo(list);
            }
        }
    }


    /**
     * 新增用户其他信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserOtherInfo(SysUser sysUser) {
        /*List<SysUserOtherInfo> sysUserOtherInfoList = sysUser.getSysUserOtherInfoList();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserOtherInfoList)) {
            List<SysUserOtherInfo> list = new ArrayList<SysUserOtherInfo>();
            for (SysUserOtherInfo sysUserOtherInfo : sysUserOtherInfoList) {
                sysUserOtherInfo.setOtherInfoId(IdUtils.randomUUID());
                sysUserOtherInfo.setUserId(userId);
                list.add(sysUserOtherInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserOtherInfo(list);
            }
        }*/
    }

    /**
     * 新增用户年度考核信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserAnnualAppraisalInfo(SysUser sysUser) {
        List<SysUserAnnualAppraisalInfo> sysUserAnnualAppraisalInfoList = sysUser.getAppraisalInfos();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserAnnualAppraisalInfoList)) {
            List<SysUserAnnualAppraisalInfo> list = new ArrayList<SysUserAnnualAppraisalInfo>();
            for (SysUserAnnualAppraisalInfo sysUserAnnualAppraisalInfo : sysUserAnnualAppraisalInfoList) {
                sysUserAnnualAppraisalInfo.setAnnualAppraisalInfoId(IdUtils.randomUUID());
                sysUserAnnualAppraisalInfo.setUserId(userId);
                list.add(sysUserAnnualAppraisalInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserAnnualAppraisalInfo(list);
            }
        }
    }


    /**
     * 新增家庭成员及主要社会关系信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserFamilyMemberSocialInfo(SysUser sysUser) {
        Long userId = sysUser.getUserId();
        List<SysUserFamilyMemberSocialInfo> sysUserFamilyMemberSocialInfoList = sysUser.getFamilyInfos();
        if (StringUtils.isNotNull(sysUserFamilyMemberSocialInfoList)) {
            List<SysUserFamilyMemberSocialInfo> list = new ArrayList<SysUserFamilyMemberSocialInfo>();
            for (SysUserFamilyMemberSocialInfo sysUserFamilyMemberSocialInfo : sysUserFamilyMemberSocialInfoList) {
                sysUserFamilyMemberSocialInfo.setFamilyMemberSocialInfoId(IdUtils.randomUUID());
                sysUserFamilyMemberSocialInfo.setUserId(userId);
                list.add(sysUserFamilyMemberSocialInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserFamilyMemberSocialInfo(list);
            }
        }
    }

    /**
     * 新增用户简历信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserResumeInfo(SysUser sysUser) {
        List<SysUserResumeInfo> sysUserResumeInfoList = sysUser.getSysUserResumeInfoList();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserResumeInfoList)) {
            List<SysUserResumeInfo> list = new ArrayList<SysUserResumeInfo>();
            for (SysUserResumeInfo sysUserResumeInfo : sysUserResumeInfoList) {
                sysUserResumeInfo.setResumeInfoId(IdUtils.randomUUID());
                sysUserResumeInfo.setUserId(userId);
                list.add(sysUserResumeInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserResumeInfo(list);
            }
        }
    }


    /**
     * 新增工作单位及职务信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserWorkUnitAndPositionInfo(SysUser sysUser) {
        List<SysUserWorkUnitAndPositionInfo> sysUserWorkUnitAndPositionInfoList = sysUser.getSysUserWorkUnitAndPositionInfoList();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserWorkUnitAndPositionInfoList)) {
            List<SysUserWorkUnitAndPositionInfo> list = new ArrayList<SysUserWorkUnitAndPositionInfo>();
            for (SysUserWorkUnitAndPositionInfo sysUserWorkUnitAndPositionInfo : sysUserWorkUnitAndPositionInfoList) {
                sysUserWorkUnitAndPositionInfo.setWorkUnitAndPositionInfoId(IdUtils.randomUUID());
                sysUserWorkUnitAndPositionInfo.setUserId(userId);
                list.add(sysUserWorkUnitAndPositionInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserWorkUnitAndPositionInfo(list);
            }
        }
    }

    /**
     * 新增用户现职级信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserCurrentPostInfo(SysUser sysUser) {
        List<SysUserCurrentPostInfo> sysUserCurrentPostInfoList = sysUser.getCurrentPostInfos();
        Long userId = sysUser.getUserId();
        SysUserCurrentPostInfo currentPostInfo = null;
        if (StringUtils.isNotNull(sysUserCurrentPostInfoList)) {
            List<SysUserCurrentPostInfo> list = new ArrayList<SysUserCurrentPostInfo>();
            for (SysUserCurrentPostInfo sysUserCurrentPostInfo : sysUserCurrentPostInfoList) {
                sysUserCurrentPostInfo.setCurrentPostId(IdUtils.randomUUID());
                sysUserCurrentPostInfo.setUserId(userId);
                list.add(sysUserCurrentPostInfo);
                if (sysUserCurrentPostInfo.getPostLevelStatus().equals("1")) {
                    currentPostInfo = sysUserCurrentPostInfo;
                }
            }
            if (list.size() > 0) {
                userMapper.batchSysUserCurrentPostInfo(list);
            }
        }
        if (currentPostInfo != null) {
            sysUser.setWorkTitleCode(currentPostInfo.getRank());
            sysUser.setWorkTitleTime(currentPostInfo.getApprovalDate());
            sysUser.setSameWorkTitleTime(currentPostInfo.getPeerLevel());
            String dictType = "3".equals(sysUser.getIdentityType()) ? "work_title_syb" : "work_title_gwy";
            sysUser.setWorkTitle(sysDictDataService.selectDictLabel(dictType, currentPostInfo.getRank()));
        } else {
            sysUser.setWorkTitleCode("");
            sysUser.setWorkTitleTime("");
            sysUser.setSameWorkTitleTime("");
            sysUser.setWorkTitle("");
        }
    }

    /**
     * 新增用户基层工作经历信息信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserGrassrootsWorkInfo(SysUser sysUser) {
        List<SysUserGrassrootsWorkInfo> sysUserGrassrootsWorkInfoList = sysUser.getGrassrootsWorkInfos();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserGrassrootsWorkInfoList)) {
            int totalWorkTime = 0;
            List<SysUserGrassrootsWorkInfo> list = new ArrayList<SysUserGrassrootsWorkInfo>();
            Date startDate, endDate;
            for (SysUserGrassrootsWorkInfo sysUserGrassrootsWorkInfo : sysUserGrassrootsWorkInfoList) {
                sysUserGrassrootsWorkInfo.setGrassrootsWorkId(IdUtils.randomUUID());
                sysUserGrassrootsWorkInfo.setUserId(userId);
                list.add(sysUserGrassrootsWorkInfo);
                try {
                    startDate = DateUtils.parseDate(sysUserGrassrootsWorkInfo.getStartDate(), DateUtils.YYYY_MM_DD);
                    endDate = DateUtils.parseDate(sysUserGrassrootsWorkInfo.getEndDate(), DateUtils.YYYY_MM_DD);
                    totalWorkTime += DateUtils.dateDiff('M', endDate, startDate);
                } catch (Exception e) {
                }
            }
            if (list.size() > 0) {
                userMapper.batchSysUserGrassrootsWorkInfo(list);
            }
            sysUser.setGrassrootsWorkTime(Double.toString(Math.round(totalWorkTime * 10 / 12) / 10.0));
        }
    }

    /**
     * 新增借调工作人员信息
     *
     * @param sysUser 用户信息对象
     */
    public void insertSysUserSecondmentWorkInfo(SysUser sysUser) {
        List<SysUserSecondmentWorkInfo> sysUserSecondmentWorkInfoList = sysUser.getSecondmentWorkInfos();
        Long userId = sysUser.getUserId();
        if (StringUtils.isNotNull(sysUserSecondmentWorkInfoList)) {
            List<SysUserSecondmentWorkInfo> list = new ArrayList<SysUserSecondmentWorkInfo>();
            for (SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo : sysUserSecondmentWorkInfoList) {
                sysUserSecondmentWorkInfo.setSecondmentWorkInfoId(IdUtils.randomUUID());
                sysUserSecondmentWorkInfo.setUserId(userId);
                sysUserSecondmentWorkInfo.setCreateBy(SecurityUtils.getUsername());
                sysUserSecondmentWorkInfo.setCreateTime(DateUtils.getNowDate());
                list.add(sysUserSecondmentWorkInfo);
            }
            if (list.size() > 0) {
                userMapper.batchSysUserSecondmentWorkInfo(list);
            }
        }

    }


    @Override
    public LrmxPerson readLrmxFile(InputStream inputStream) {
        String xmlStr = IoUtil.readUtf8(inputStream);
        Map data = XmlUtil.xmlToMap(xmlStr);
        JSONObject json = new JSONObject(data);
        LrmxPerson person = JSONObject.toJavaObject(json, LrmxPerson.class);
        return person;
    }

    @Override
    public byte[] exportLrmxFile(List<SysUser> sysUsers) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        if (sysUsers != null) {
            for (SysUser sysUser : sysUsers) {
                exportLrmx(sysUser, zip);
            }
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public SysUser selectUserByIdcard(String idcard) {
        return userMapper.selectUserByIdcard(idcard);
    }

    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public SysUser selectUserByMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return null;
        }
        return userMapper.selectUserByMobile(mobile);
    }

    public JSONObject importFromLrmx(List<LrmxPerson> personList) {
        if (personList != null && !personList.isEmpty()) {
            SysUser sysUser;
            String val;
            List<UserDeptPost> deptPostList;
            List<SysUserFamilyMemberSocialInfo> familyMemberList;
            LrmxPerson.FamilyMember familyMember;
            List<SysUserTechnicalPositionInfo> positionInfos;
            SysUserFamilyMemberSocialInfo memberSocialInfo;
            UserDeptPost userDeptPost;
            List<SysUserResumeInfo> sysUserResumeInfoList;
            SysUserResumeInfo resumeInfo;
            List<SysUserRewardsAndPenaltiesInfo> rewardsInfos;
            SysUserRewardsAndPenaltiesInfo rewardsInfo;
            List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos;
            SysUserEducationAndDegreeInfo educationInfo;
            for (LrmxPerson person : personList) {
                sysUser = new SysUser();
                deptPostList = new ArrayList<>();
                sysUser.setIdcard(person.getIdcard());
                sysUser.setName(person.getName());
                val = person.getGender();
                sysUser.setSex(StringUtils.isEmpty(val) ? "2" : ("男".equals(val) ? "0" : "1"));
                sysUser.setBirthday(person.getBirthday());
                sysUser.setNation(person.getNation());
                sysUser.setNativePlace(person.getNativePlace());
                sysUser.setBirthPlace(person.getBirthPlace());
                sysUser.setStartWorkTime(person.getStartWorkTime());
                sysUser.setHealthCondition(person.getHealthCondition());
                sysUser.setSpeciality(person.getSpeciality());
                sysUser.setWorkPost(person.getCurPosition());
                sysUser.setIsHostingWork("0");
                sysUser.setPersonnelStatus("1");

                //部门信息
                val = person.getDeptId();
                userDeptPost = new UserDeptPost();
                userDeptPost.setDeptId(Long.parseLong(val));
                deptPostList.add(userDeptPost);
                sysUser.setUserDeptPostList(deptPostList);
                sysUser.setDeptId(Long.parseLong(val));

                //家庭成员
                familyMember = person.getFamilyMember();
                if (familyMember != null) {
                    familyMemberList = new ArrayList<>();
                    List<LrmxPerson.FamilyMemberInfo> memberInfos = familyMember.getMemberList();
                    for (LrmxPerson.FamilyMemberInfo memberInfo : memberInfos) {
                        memberSocialInfo = new SysUserFamilyMemberSocialInfo();
                        memberSocialInfo.setBirthDate(memberInfo.getBirthday());
                        memberSocialInfo.setFamilyMembersName(memberInfo.getName());
                        memberSocialInfo.setPoliticalOutlook(memberInfo.getPoliticalId());
                        memberSocialInfo.setAppellation(memberInfo.getTitle());
                        memberSocialInfo.setWorkUnitAndPosition(memberInfo.getWork());
                        familyMemberList.add(memberSocialInfo);
                    }
                    sysUser.setFamilyInfos(familyMemberList);
                }

                //专业技术职务
                val = person.getPosition();
                if (StringUtils.isNotEmpty(val)) {
                    positionInfos = new ArrayList<>();
                    SysUserTechnicalPositionInfo positionInfo = new SysUserTechnicalPositionInfo();
                    positionInfo.setTechnicalQualificationName(val);
                    positionInfos.add(positionInfo);
                    sysUser.setPositionInfos(positionInfos);
                }

                //简历信息
                val = person.getJianLi();
                if (StringUtils.isNotEmpty(val)) {
                    sysUserResumeInfoList = new ArrayList<>();
                    String[] resumes = val.split("\\n");
                    for (String resume : resumes) {
                        String[] items = resume.split("  ");
                        String[] dates = items[0].split("--");
                        resumeInfo = new SysUserResumeInfo();
                        resumeInfo.setStartDate(dates[0]);
                        resumeInfo.setEndDate(dates[1]);
                        resumeInfo.setWorkJobName(items[1]);
                        sysUserResumeInfoList.add(resumeInfo);
                    }
                    sysUser.setSysUserResumeInfoList(sysUserResumeInfoList);
                }

                //奖惩信息
                val = person.getRewards();
                if (StringUtils.isNotEmpty(val)) {
                    rewardsInfos = new ArrayList<>();
                    String[] rewards = val.split("；");
                    for (String reward : rewards) {
                        rewardsInfo = new SysUserRewardsAndPenaltiesInfo();
                        String[] items = reward.split("，");
                        rewardsInfo.setApprovalDate(items[0]);
                        rewardsInfo.setRewardsAndPenaltiesName(items[2]);
                        rewardsInfos.add(rewardsInfo);
                    }
                    sysUser.setRewardsInfos(rewardsInfos);
                }

                //学历，学位
                educationAndDegreeInfos = new ArrayList<>();
                val = person.getXueLi1();
                if (StringUtils.isNotEmpty(val)) {
                    educationInfo = new SysUserEducationAndDegreeInfo();
                    educationInfo.setEducationCategory("1");
                    educationInfo.setEducation(val);
                    educationInfo.setEducationalName(val);
                    educationInfo.setDegree(person.getXueWei1());
                    educationInfo.setDegreeName(person.getXueWei1());
                    educationInfo.setSchoolAndDepartment(person.getSchool1());
                    educationInfo.setProfessionalName(person.getSchool1());
                    educationAndDegreeInfos.add(educationInfo);
                }
                val = person.getXueLi2();
                if (StringUtils.isNotEmpty(val)) {
                    educationInfo = new SysUserEducationAndDegreeInfo();
                    educationInfo.setEducationCategory("2");
                    educationInfo.setEducation(val);
                    educationInfo.setEducationalName(val);
                    educationInfo.setDegree(person.getXueWei2());
                    educationInfo.setDegreeName(person.getXueWei2());
                    educationInfo.setSchoolAndDepartment(person.getSchool2());
                    educationInfo.setProfessionalName(person.getSchool2());
                    educationAndDegreeInfos.add(educationInfo);
                }
                sysUser.setEducationAndDegreeInfos(educationAndDegreeInfos);

                insertUser(sysUser);
            }
        }
        return null;
    }

    private void exportLrmx(SysUser sysUser, ZipOutputStream zip) {
        Long sysUserId = sysUser.getUserId();
        LrmxPerson person = new LrmxPerson();
        person.setIdcard(sysUser.getIdcard());
        person.setName(sysUser.getName());
        String val = sysUser.getSex();
        person.setGender(StringUtils.isEmpty(val) ? "" : ("0".equals(val) ? "男" : "女"));
        val = sysUser.getBirthday();
        if (StringUtils.isNotEmpty(val)) {
            person.setBirthday(val.replaceAll("-", ""));
        }
        person.setNation(sysUser.getNation());
        person.setNativePlace(sysUser.getNativePlace());
        person.setBirthPlace(sysUser.getBirthPlace());
        val = sysUser.getStartWorkTime();
        if (StringUtils.isNotEmpty(val)) {
            person.setStartWorkTime(val.replaceAll("-", ""));
        }
        person.setHealthCondition(sysUser.getHealthCondition());
        person.setSpeciality(sysUser.getSpeciality());
        person.setCurPosition(sysUser.getWorkPost());
        val = "";
        if (StringUtils.isNotEmpty(sysUser.getPoliticalIdentity())) {
            val += sysUser.getPoliticalIdentity();
        }
        if (StringUtils.isNotEmpty(sysUser.getPartyJoinTime())) {
            val += "(" + sysUser.getPartyJoinTime().replaceAll("-", ".") + ")";
        }
        person.setPartyJoinTime(val);
        //家庭成员
        LrmxPerson.FamilyMember familyMember = new LrmxPerson.FamilyMember();
        List<LrmxPerson.FamilyMemberInfo> memberInfos = new ArrayList<>();
        List<SysUserFamilyMemberSocialInfo> familyMemberList = userMapper.selectFamilyMemberSocialInfoByUserId(sysUserId);
        if (familyMemberList != null) {
            LrmxPerson.FamilyMemberInfo memberInfo = null;
            for (SysUserFamilyMemberSocialInfo memberSocialInfo : familyMemberList) {
                memberInfo = new LrmxPerson.FamilyMemberInfo();
                val = memberSocialInfo.getBirthDate();
                if (StringUtils.isNotEmpty(val)) {
                    memberInfo.setBirthday(val.replaceAll("-", ""));
                }
                memberInfo.setName(memberSocialInfo.getFamilyMembersName());
                memberInfo.setPoliticalId(memberSocialInfo.getPoliticalOutlook());
                memberInfo.setTitle(memberSocialInfo.getAppellation());
                memberInfo.setWork(memberSocialInfo.getWorkUnitAndPosition());
                memberInfos.add(memberInfo);
            }
        }
        familyMember.setMemberList(memberInfos);
        person.setFamilyMember(familyMember);

        //专业技术职务
        List<SysUserTechnicalPositionInfo> positionInfos = userMapper.selectTechnicalPositionInfoByUserId(sysUserId);
        if (positionInfos != null && positionInfos.size() > 0) {
            person.setPosition(positionInfos.get(0).getTechnicalQualificationName());
        }

        //简历信息
        List<SysUserResumeInfo> sysUserResumeInfoList = userMapper.selectResumeInfoByUserId(sysUserId);
        if (sysUserResumeInfoList != null) {
            val = "";
            for (SysUserResumeInfo resumeInfo : sysUserResumeInfoList) {
                if (val.length() > 0) {
                    val += "\n";
                }
                val += resumeInfo.getStartDate() + "--" + resumeInfo.getEndDate() + "  " + resumeInfo.getWorkJobName();
            }
            person.setJianLi(val);
        }

        //奖惩信息
        List<SysUserRewardsAndPenaltiesInfo> rewardsInfos = userMapper.selectRewardsAndPenaltiesInfoByUserId(sysUserId);
        if (rewardsInfos != null) {
            val = "";
            for (SysUserRewardsAndPenaltiesInfo rewardsInfo : rewardsInfos) {
                if (val.length() > 0) {
                    val += "；";
                }
                val += rewardsInfo.getApprovalDate() + "，" + "经批准，" + rewardsInfo.getRewardsAndPenaltiesName() + "。";
            }
            person.setRewards(val);
        }

        //学历，学位
        List<SysUserEducationAndDegreeInfo> educationAndDegreeInfos = userMapper.selectEducationAndDegreeInfoByUserId(sysUserId);
        if (educationAndDegreeInfos != null) {
            String education = sysUser.getEducation();
            String degree = sysUser.getDegree();
            for (SysUserEducationAndDegreeInfo educationInfo : educationAndDegreeInfos) {
                if (educationInfo.getDegree().equals(degree)) {
                    person.setXueLi1(educationInfo.getEducationalName());
                    person.setXueWei1(educationInfo.getDegreeName());
                    val = "";
                    if (StringUtils.isNotEmpty(educationInfo.getSchoolAndDepartment())) {
                        val += educationInfo.getSchoolAndDepartment();
                    }
                    if (StringUtils.isNotEmpty(educationInfo.getProfessionalName())) {
                        val += educationInfo.getProfessionalName();
                    }
                    person.setSchool1(val);
                } else {
                    person.setXueLi2(educationInfo.getEducationalName());
                    person.setXueWei2(educationInfo.getDegreeName());
                    val = "";
                    if (StringUtils.isNotEmpty(educationInfo.getSchoolAndDepartment())) {
                        val += educationInfo.getSchoolAndDepartment();
                    }
                    if (StringUtils.isNotEmpty(educationInfo.getProfessionalName())) {
                        val += educationInfo.getProfessionalName();
                    }
                    person.setSchool2(val);
                }
            }
        }

        Document document = XmlUtil.mapToXml((JSONObject) JSONObject.toJSON(person), "Person");
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transFormer = null;
        try {
            // 添加到zip
            zip.putNextEntry(new ZipEntry(sysUser.getName() + ".lrmx"));
            transFormer = transFactory.newTransformer();
            transFormer.setOutputProperty(OutputKeys.INDENT, "yes");
            transFormer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transFormer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource domSource = new DOMSource(document);
            StreamResult xmlResult = new StreamResult(zip);
            transFormer.transform(domSource, xmlResult);

            zip.flush();
            zip.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Long> selectUserIdsByIdentityTypeAndPost(List<Long> deptIds, List<String> types) {
        return userMapper.selectUserIdsByIdentityTypeAndPost(deptIds, types);
    }

    @Override
    public List<SysUser> selectUserListByCondition(String condition, List<Long> deptIds) {
        return userMapper.selectUserListByCondition(condition, deptIds);
    }

    @Override
    public Boolean isLeaderInCharge() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        List<SysDept> list = deptMapper.selectChargeDeptList(userId);
        return list.size() > 0 ? true : false;
    }

    @Override
    @Transactional
    public int synchronousDept(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户多部门关联
        userDeptMapper.deleteByUserId(userId);
        SysUserDeptPost deptPost = new SysUserDeptPost();
        deptPost.setDeptId(user.getDeptId());
        deptPost.setUserId(userId);
        // 新增用户多部门关联
        return userDeptMapper.insertDeptPost(deptPost);
    }

    @Override
    public List<SysUser> selectUserListByName(SysUser user) {
        return userMapper.selectUserListByName(user);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userNames
     * @return
     */
    @Override
    public List<SysUser> selectUserListByNames(List<String> userNames) {
        if (userNames == null || userNames.size() == 0) {
            return new ArrayList<>();
        }
        return userMapper.selectUserListByNames(userNames);
    }

    @Override
    public List<SysUser> selectMainLeaderByDeptId(Long deptId) {
        return userMapper.selectMainLeaderByDeptId(deptId);
    }

    @Override
    public SysUser selectDeptLeaderCharge(Long deptId, List<Long> roleIds, boolean startTop) {
        Long chargeUserId = userMapper.selectDeptLeaderChargeUserId(deptId, roleIds, startTop);
        if (chargeUserId != null) {
            return selectUserById(chargeUserId);
        }
        return null;
    }

    @Override
    public List<SysUser> listUserByRoleKey(String roleKey) {
        if(StringUtils.isBlank(roleKey)){
            return Collections.emptyList();
        }
        return userMapper.listUserByRoleKey(roleKey);
    }

    @Override
    public int importWordUser(WordUserVo wordUserVo) {
        if(StringUtils.isBlank(wordUserVo.getName())){
            throw new RuntimeException("未获取到用户名称");
        }
        String importId = WordImportContextHolder.getImportId();
        String threadUserName = WordImportContextHolder.getUserName();
        String userName = null == threadUserName ? SecurityUtils.getUsername() : threadUserName;
        SysUser user = WordImportUtil.convertWordUserVo(wordUserVo);
        user.setCreateBy(userName);
        int i = insertUser(user);
        if(null != importId){
            int iNum = i;
            Integer cacheNum = redisCache.getCacheMapValue(importId, "totalInserted");
            if(null != cacheNum ){
                iNum = cacheNum + iNum;
            }
            redisCache.setCacheMapValue(importId, "totalInserted", iNum);
        }
        return i;
    }

    @Override
    public int updateDeptByUserIds(SysUser user) {
        for (Long userId : user.getUserIds()) {
            checkUserAllowed(new SysUser(userId));
        }
        return userMapper.updateDeptByUserIds(user);
    }

    @Override
    public List<SysUser> selectUserByAbilityLabel(SysUser user) {
        return userMapper.selectUserByAbilityLabel(user);
    }

    @Override
    public  List<SysUser> selectUserByNickName(String nickName) {
        return userMapper.selectUserByNickName(nickName);
    }

    @Override
    public List<SysUser> selectUserByNickNameAndDeptId(String nickName, Long deptId) {
        return userMapper.selectUserByNickNameAndDeptId(nickName,deptId);
    }

    @Override
    public List<SysUser> selectMainLeaderList(SysUser user) {
        return userMapper.selectMainLeaderList(user);
    }

    @Override
    public int updateByUserInfoApply(SysUser user) {
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(checkPhoneUnique(user))) {
            throw new CustomException("修改人员'" + user.getUserName() + "'失败，手机号码已存在");
        }
        //修改之前的用户信息
        Long userId = user.getUserId();
        SysUserInfoChangeLog infoChangeLog = sysUserInfoChangeLogService.selectSysUserInfoChangeLogById(userId);
        boolean logExist = true;
        if(null == infoChangeLog){
            infoChangeLog = new SysUserInfoChangeLog();
            infoChangeLog.setUserId(userId);
            logExist = false;
        }
        if(StringUtils.isBlank(infoChangeLog.getInitData())){
            List<UserDeptPost> userDeptPostList = userDeptPostService.selectByUserId(userId);
            SysUser sysUser = selectUserById(userId);
            sysUser.setUserDeptPostList(userDeptPostList);
            infoChangeLog.setInitData(JSON.toJSONString(sysUser));
        }

        user.setUpdateBy(SecurityUtils.getUsername());
        // 更新用户信息
        int result = updateUser(user);

        //修改之后保存用户信息
        List<UserDeptPost> userDeptPostList = userDeptPostService.selectByUserId(userId);
        SysUser sysUser = selectUserById(userId);
        sysUser.setUserDeptPostList(userDeptPostList);
        infoChangeLog.setLastData(JSON.toJSONString(sysUser));
        //日志是否已存在，存在则更新，不存在则新增
        if(logExist){
            sysUserInfoChangeLogService.updateSysUserInfoChangeLog(infoChangeLog);
        } else {
            sysUserInfoChangeLogService.insertSysUserInfoChangeLog(infoChangeLog);
        }
        return result;
    }
}
