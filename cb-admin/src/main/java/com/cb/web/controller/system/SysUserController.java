package com.cb.web.controller.system;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.Log;
import com.cb.common.annotation.RepeatSubmit;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.constant.Constants;
import com.cb.common.constant.UserConstants;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.*;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.domain.vo.*;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.ServletUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.framework.web.service.TokenService;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.service.ISysUserOutService;
import com.cb.system.domain.SysUserInfoChangeApply;
import com.cb.system.domain.SysUserInfoChangeLog;
import com.cb.system.domain.SysUserRole;
import com.cb.system.mapper.SysUserRoleMapper;
import com.cb.system.service.*;
import com.cb.system.util.WordImportContextHolder;
import com.cb.system.util.WordImportUtil;
import com.cb.system.vo.WordUserVo;
import org.apache.commons.io.IOUtils;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysCommonConfigService commonConfigService;
    @Autowired
    private ISysUserDeptPostService userDeptPostService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserOutService sysUserOutService;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private ILeaveBalancesService leaveBalancesService;

    @Autowired
    private ISysUserInfoChangeLogService sysUserInfoChangeLogService;

    @Autowired
    private ISysUserInfoChangeApplyService sysUserInfoChangeApplyService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        setEduInfo(list);
        return getDataTable(list);
    }

    /**
     * 设置教育学历信息
     * @param list
     */
    private void setEduInfo(List<SysUser> list) {
        //设置学历信息
        List<Long> userIds = list.stream().map(o -> o.getUserId()).collect(Collectors.toList());
        Map<Long, List<SysUserEducationAndDegreeInfo>> userIdEduMap = userService.selectEducationAndDegreeInfoByUserIds(userIds)
                .stream().collect(Collectors.groupingBy(SysUserEducationAndDegreeInfo::getUserId));
        list.forEach(o -> {
            List<SysUserEducationAndDegreeInfo> eduList = userIdEduMap.get(o.getUserId());
            if (null == eduList) {
                o.setEducationAndDegreeInfos(Collections.emptyList());
            } else {
                eduList.forEach(i -> {
                    if (null == i.getCompletionDate()) {
                        i.setCompletionDate("");
                    }
                });
                eduList = eduList.stream().sorted(Comparator.comparing(SysUserEducationAndDegreeInfo::getCompletionDate).reversed())
                        .collect(Collectors.toList());
                o.setEducationAndDegreeInfos(eduList);
            }
        });
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/listByAdmin")
    public TableDataInfo listByAdmin(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserListByAdmin(user);
        setEduInfo(list);
        return getDataTable(list);
    }

    /**
     * 获取已删除的用户列表（未彻底删除的）
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/deleteListByAdmin")
    public TableDataInfo deleteListByAdmin(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectDeleteUserListByAdmin(user);
        setEduInfo(list);
        return getDataTable(list);
    }

    /**
     * 获取联络员用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:liaisonInfoList')")
    @GetMapping("/liaisonInfoList")
    public TableDataInfo liaisonInfoList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectLiaisonInfoList(user);
        return getDataTable(list);
    }

    /**
     * 移除指定用户的联络员角色
     * @param userIds
     * @return
     */
    @GetMapping("/removeLiaisonRole")
    public AjaxResult removeLiaisonRole(String userIds) {
        if (StringUtils.isBlank(userIds)) {
            return AjaxResult.error("参数不能为空");
        }
        try {
            String roleKey = "liaison_role";
            SysRole role = roleService.selectRoleByKey(roleKey);
            String[] split = userIds.split(",");
            Long[] userIdArr = new Long[split.length];
            for (int i = 0; i < split.length; i++) {
                userIdArr[i] = Long.valueOf(split[i]);
            }
            userRoleMapper.deleteUserRoleInfos(role.getRoleId(), userIdArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AjaxResult.success();
    }

    /**
     * 添加指定用户的联络员角色
     * @param userIds
     * @return
     */
    @GetMapping("/addLiaisonRole")
    public AjaxResult addLiaisonRole(String userIds) {
        if (StringUtils.isBlank(userIds)) {
            return AjaxResult.error("参数不能为空");
        }
        try {
            String roleKey = "liaison_role";
            SysRole role = roleService.selectRoleByKey(roleKey);
            List<SysUserRole> userRoles = Arrays.stream(userIds.split(",")).map(userId -> {
                SysUserRole add = new SysUserRole();
                add.setUserId(Long.valueOf(userId));
                add.setRoleId(role.getRoleId());
                return add;
            }).collect(Collectors.toList());

            userRoleMapper.batchUserRole(userRoles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AjaxResult.success();
    }

    /**
     * 查询干部
     * @param user
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/listCadre")
    public TableDataInfo listCadre(SysUser user) {
        startPage();
        List<SysUser> list = userService.listCadre(user);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/selectUserListByIdentityType")
    public TableDataInfo selectUserListByIdentityType(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserListByIdentityType(user);
        return getDataTable(list);
    }

    /**
     * 获取退休人员管理列表
     * @param user
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:retireeManagement:list')")
    @GetMapping("/retireeManagementList")
    public TableDataInfo retireeManagementList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectRetireesUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/exportLrmx")
    public void exportLrmx(String ids, HttpServletResponse response) throws IOException {
        String[] idList = ids.split(",");
        List<Long> userIds = new ArrayList<>();
        for (String id : idList) {
            userIds.add(Long.parseLong(id));
        }
        List<SysUser> list = userService.selectUserListByUserIds(userIds);
        byte[] data = userService.exportLrmxFile(list);

        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("人事任免表", "utf-8") + ".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ImportUserVo> util = new ExcelUtil<ImportUserVo>(ImportUserVo.class);
        List<ImportUserVo> userList = util.importExcel(file.getInputStream()).stream()
                .filter(u->StringUtils.isNotBlank(u.getName()))
                .collect(Collectors.toList());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser2(userList, updateSupport, operName);
        return AjaxResult.success();
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData2")
    public AjaxResult importData2(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }
    //事业人员导入模版
    @GetMapping("/importTemplateShiYe")
    public AjaxResult importTemplateShiYe() {
        ExcelUtil<SysUserShiYeVo> util = new ExcelUtil<SysUserShiYeVo>(SysUserShiYeVo.class);
        return util.importTemplateExcel("事业人员名册");
    }
    //合同工人员导入模版
    @GetMapping("/importTemplateHeTong")
    public AjaxResult importTemplateHeTong() {
        ExcelUtil<SysUserHeTongVo> util = new ExcelUtil<SysUserHeTongVo>(SysUserHeTongVo.class);
        return util.importTemplateExcel("合同制人员名册");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importDataShiYe")
    public AjaxResult importDataShiYe(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUserShiYeVo> util = new ExcelUtil<SysUserShiYeVo>(SysUserShiYeVo.class);
        List<SysUserShiYeVo> userList = util.importExcel(file.getInputStream()).stream()
                .filter(u->StringUtils.isNotBlank(u.getNickName()))
                .collect(Collectors.toList());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser3(userList, updateSupport, operName);
        return AjaxResult.success();
    }
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importDataHeTong")
    public AjaxResult importDataHeTong(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUserHeTongVo> util = new ExcelUtil<SysUserHeTongVo>(SysUserHeTongVo.class);
        List<SysUserHeTongVo> userList = util.importExcel(file.getInputStream()).stream()
                .filter(u->StringUtils.isNotBlank(u.getNickName()))
                .collect(Collectors.toList());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser4(userList, updateSupport, operName);
        return AjaxResult.success();
    }
    /**
     * 根据用户编号获取详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        long startTime = System.currentTimeMillis();
        List<SysRole> roles = roleService.selectRoleAll();
        if (commonConfigService.selectThreeMemberSwitch()) {
            if (SysUser.isAdmin(userId) || SysUser.isSecAdmin(userId) || SysUser.isAudAdmin(userId)) {
                ajax.put("roles", roles.stream().filter(r -> r.isAdmin() || r.isSecAdmin() || r.isAudAdmin()).collect(Collectors.toList()));
            } else {
                ajax.put("roles", roles.stream().filter(r -> !r.isAdmin() && !r.isSecAdmin() && !r.isAudAdmin()).collect(Collectors.toList()));
            }
        } else {
            ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        }
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            List<UserDeptPost> userDeptPostList = userDeptPostService.selectByUserId(userId);
            SysUser sysUser = userService.selectUserById(userId);
            sysUser.setUserDeptPostList(userDeptPostList);
            ajax.put(AjaxResult.DATA_TAG, sysUser);
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
            ajax.put("userDeptPostList", userDeptPostList);
            ajax.put("positionInfos", userService.selectTechnicalPositionInfoByUserId(userId));
            ajax.put("educationAndDegreeInfos", userService.selectEducationAndDegreeInfoByUserId(userId));
            ajax.put("rewardsInfos", userService.selectRewardsAndPenaltiesInfoByUserId(userId));
            ajax.put("sysUserOtherInfoList", userService.selectOtherInfoByUserId(userId));
            ajax.put("appraisalInfos", userService.selectAnnualAppraisalInfoByUserId(userId));
            ajax.put("familyInfos", userService.selectFamilyMemberSocialInfoByUserId(userId));
            ajax.put("sysUserResumeInfoList", userService.selectResumeInfoByUserId(userId));
            ajax.put("sysUserWorkUnitAndPositionInfoList", userService.selectWorkUnitAndPositionInfoByUserId(userId));
            ajax.put("currentPostInfos", userService.selectCurrentPostInfoByUserId(userId));
            ajax.put("grassrootsWorkInfos", userService.selectGrassrootsWorkInfoByUserId(userId));
            ajax.put("secondmentWorkInfos", userService.selectSecondmentWorkInfoByUserId(userId));
            ajax.put("sysUserInfoChangeLog", sysUserInfoChangeLogService.selectSysUserInfoChangeLogById(userId));
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("代码执行时间：" + elapsedTime + "毫秒");
        return ajax;
    }

    //   根据用户id获取用户信息
    @GetMapping("/getUserInfoByUserId/{userId}")
    public AjaxResult getUserInfoByUserId(@PathVariable("userId") Long userId) {
        SysUser user = userService.selectUserByUserId(userId);
        return AjaxResult.success(user);
    }

    //    根据用户id修改用户信息，接收的参数是：userId,positionShort
    @PutMapping("/updateUserInfoByUserId")
    public AjaxResult updateUserInfoByUserId(Long userId, String positionShort) {
        if (StringUtils.isNotEmpty(positionShort)) {
            SysUser sysUser = userService.selectUserById(userId);
//            判断 sysUser 是否为空
            if (sysUser == null) {
                return AjaxResult.error("用户不存在");
            }
            userService.updateUserByUserId(userId, positionShort);
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }

    }


    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkIdcardUnique(user.getIdcard(), null))) {
            return AjaxResult.error("新增人员'" + user.getName() + "'失败，身份证号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增人员'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        if (StringUtils.isNotEmpty(user.getBirthday())) {
            user.setBirthday(DateUtils.dateStdFormat(user.getBirthday(), true, "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(user.getWorkTitleTime())) {
            user.setBirthday(DateUtils.dateStdFormat(user.getWorkTitleTime(), true, "yyyy-MM-dd"));
        }

        if (StringUtils.isNotEmpty(user.getPartyJoinTime())) {
            user.setPartyJoinTime(DateUtils.dateStdFormat(user.getPartyJoinTime(), true, "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(user.getStartWorkTime())) {
            user.setStartWorkTime(DateUtils.dateStdFormat(user.getStartWorkTime(), true, "yyyy-MM-dd"));
        }
        //设置多部门及职务
        List<UserDeptPost> userDeptPostList = user.getUserDeptPostList();
        if (userDeptPostList == null || userDeptPostList.size() < 1) {
            UserDeptPost userDeptPost = new UserDeptPost();
            userDeptPost.setDeptId(user.getDeptId());
            user.setUserDeptPostList(Collections.singletonList(userDeptPost));
        }

        int rst = userService.insertUser(user);
        if (rst > 0) {
            // 成功添加用户后，根据工作年限添加年休假
            try {
                leaveBalancesService.addAnnualLeaveForUser(user);
            } catch (Exception e) {
                return AjaxResult.error("新增用户成功，但年休假添加失败：" + e.getMessage());
            }
            Map<String, Object> params = user.getParams();
            if (params != null && params.get("oaUserId") != null) {
                String oaUserId = (String) params.get("oaUserId");
                SysUserOut oaUser = sysUserOutService.selectSysUserOutById(oaUserId);
                if (oaUser != null) {
                    oaUser.setUserId(user.getUserId());
                    sysUserOutService.updateSysUserOut(oaUser);
                }
            }
        }
        return toAjax(rst);
    }

    /**
     * 修改用户
     */
    //@PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        //userService.checkUserAllowed(user);
        /*if (UserConstants.NOT_UNIQUE.equals(userService.checkIdcardUnique(user.getIdcard(), user.getUserId()))) {
            return AjaxResult.error("修改人员'" + user.getName() + "'失败，身份证号已存在");
        } else */
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改人员'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        // 更新用户信息
        if (StringUtils.isNotEmpty(user.getBirthday())) {
            user.setBirthday(DateUtils.dateStdFormat(user.getBirthday(), true, "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(user.getPartyJoinTime())) {
            user.setPartyJoinTime(DateUtils.dateStdFormat(user.getPartyJoinTime(), true, "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(user.getStartWorkTime())) {
            user.setStartWorkTime(DateUtils.dateStdFormat(user.getStartWorkTime(), true, "yyyy-MM-dd"));
        }
        int result = userService.updateUser(user);
        if (result > 0) {
            // 在用户信息更新成功后调用假期额度更新方法
            leaveBalancesService.addAnnualLeaveForUser(user);
        }

        return toAjax(result);
    }

    /**
     * 用户自己修改信息，要记录修改之前的信息和之后的信息
     */
    //@PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("selfInfoModify")
    public AjaxResult selfInfoModify(@Validated @RequestBody SysUser user) {
        //userService.checkUserAllowed(user);
        /*if (UserConstants.NOT_UNIQUE.equals(userService.checkIdcardUnique(user.getIdcard(), user.getUserId()))) {
            return AjaxResult.error("修改人员'" + user.getName() + "'失败，身份证号已存在");
        } else */
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改人员'" + user.getUserName() + "'失败，手机号码已存在");
        }
//        //修改之前的用户信息
//        Long userId = user.getUserId();
//        SysUserInfoChangeLog infoChangeLog = sysUserInfoChangeLogService.selectSysUserInfoChangeLogById(userId);
//        boolean logExist = true;
//        if(null == infoChangeLog){
//            infoChangeLog = new SysUserInfoChangeLog();
//            infoChangeLog.setUserId(userId);
//            logExist = false;
//        }
//        if(StringUtils.isBlank(infoChangeLog.getInitData())){
//            List<UserDeptPost> userDeptPostList = userDeptPostService.selectByUserId(userId);
//            SysUser sysUser = userService.selectUserById(userId);
//            sysUser.setUserDeptPostList(userDeptPostList);
//            infoChangeLog.setInitData(JSON.toJSONString(sysUser));
//        }
//
//        user.setUpdateBy(SecurityUtils.getUsername());
//        // 更新用户信息
//        int result = userService.updateUser(user);
//
//        //修改之后保存用户信息
//        List<UserDeptPost> userDeptPostList = userDeptPostService.selectByUserId(userId);
//        SysUser sysUser = userService.selectUserById(userId);
//        sysUser.setUserDeptPostList(userDeptPostList);
//        infoChangeLog.setLastData(JSON.toJSONString(sysUser));
//        //日志是否已存在，存在则更新，不存在则新增
//        if(logExist){
//            sysUserInfoChangeLogService.updateSysUserInfoChangeLog(infoChangeLog);
//        } else {
//            sysUserInfoChangeLogService.insertSysUserInfoChangeLog(infoChangeLog);
//        }
//
//        if (result > 0) {
//            // 在用户信息更新成功后调用假期额度更新方法
//            leaveBalancesService.addAnnualLeaveForUser(user);
//        }
//
//        return toAjax(result);

        List<SysUserInfoChangeApply> existNoPassList = sysUserInfoChangeApplyService.selectUserNoPassList(user.getUserId());
        if (ObjectUtil.isNotEmpty(existNoPassList)) {
            return AjaxResult.error("您尚有待审批或被审批驳回的信息修改记录，不允许发起新的申请!");
        }
        Long userId = user.getUserId();
        SysUser beforeData = userService.selectUserById(userId);
        // 修改记录标识
        boolean updateFlag = getUpdateFlag(user, beforeData);
      // 对比原来的内容只修改了家属成员信息，则不需要走审核直接修改成功
        if (updateFlag) {
            // 改为先保存到修改申请里，审核通过后修改
            List<UserDeptPost> userDeptPostList = userDeptPostService.selectByUserId(userId);
            beforeData.setUserDeptPostList(userDeptPostList);
            SysUserInfoChangeApply infoChangeApply = new SysUserInfoChangeApply();
            infoChangeApply.setUserId(userId);
            infoChangeApply.setBeforeData(JSON.toJSONString(beforeData));
            infoChangeApply.setAfterData(JSON.toJSONString(user));
            sysUserInfoChangeApplyService.insertSysUserInfoChangeApply(infoChangeApply);
            return AjaxResult.success();
        } else {
            userService.updateUser(user);
            return AjaxResult.success();
        }
    }

    private boolean getUpdateFlag(SysUser user, SysUser beforeData) {
        boolean updateFlag = false;
        if (user != null && beforeData != null) {
            if (!Objects.equals(user.getIdentityType(), beforeData.getIdentityType())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getIsMainLeader(), beforeData.getIsMainLeader())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getIsHostingWork(), beforeData.getIsHostingWork())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getName(), beforeData.getName())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getSex(), beforeData.getSex())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getBirthday(), beforeData.getBirthday())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getAvatar(), beforeData.getAvatar())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getNation(), beforeData.getNation())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getNativePlace(), beforeData.getNativePlace())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getBirthPlace(), beforeData.getBirthPlace())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getPartyJoinTime(), beforeData.getPartyJoinTime())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getStartWorkTime(), beforeData.getStartWorkTime())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getHealthCondition(), beforeData.getHealthCondition())) {
                updateFlag = true;
            }
            if (!Objects.equals(user.getProfessionalDuty(), beforeData.getProfessionalDuty())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getSpeciality(), beforeData.getSpeciality())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getFullTimeEducationLevel(), beforeData.getFullTimeEducationLevel())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getFullTimeEducationSchoolAndMajor(), beforeData.getFullTimeEducationSchoolAndMajor())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getOnJobEducationLevel(), beforeData.getOnJobEducationLevel())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getOnJobEducationSchoolAndMajor(), beforeData.getOnJobEducationSchoolAndMajor())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getCurrentPosition(), beforeData.getCurrentPosition())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getProposedAppointmentPosition(), beforeData.getProposedAppointmentPosition())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getProposedRemovalPosition(), beforeData.getProposedRemovalPosition())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getResumeJsonArray(), beforeData.getResumeJsonArray())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getRewardAndPunishment(), beforeData.getRewardAndPunishment())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getAnnualAssessment(), beforeData.getAnnualAssessment())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getReasonForAppointmentOrRemoval(), beforeData.getReasonForAppointmentOrRemoval())) {
                updateFlag = true;
            }

            if (!Objects.equals(user.getDeptId(), beforeData.getDeptId())) {
                updateFlag = true;
            }

            if (user.getPostIds() == beforeData.getPostIds()) {
                return true;
            }

            if (!areLongArraysEqual(user.getPostIds(), beforeData.getPostIds())) {
                updateFlag = true;
            }
        }
        return updateFlag;
    }

    public static boolean areLongArraysEqual(Long[] arr1, Long[] arr2) {

        // 如果两个引用相同
        if (arr1 == arr2) {
            return true;
        }

        if ( (arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)){
            return true;
        }

        // 如果有一个为 null
        if (arr1 == null || arr2 == null) {
            return false;
        }

        // 如果长度不同
        if (arr1.length != arr2.length) {
            return false;
        }

        // 逐个比较元素
        for (int i = 0; i < arr1.length; i++) {
            if (!Objects.equals(arr1[i], arr2[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }


    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        int i = userService.resetPwd(user);
        user = userService.selectUserById(user.getUserId());
        if (i > 0) {
            redisCache.deleteObject(Constants.LOGIN_LOCK_KEY + user.getUserName());
        }
        return toAjax(i);
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:changeStatus')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 在职状态修改
     */
    //@PreAuthorize("@ss.hasPermi('system:user:changePersonnelStatus')")
    @Log(title = "在职状态修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changePersonnelStatus")
    public AjaxResult changePersonnelStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 储备干部设置、职务职级设置
     */
    @Log(title = "储备干部设置、职务职级设置", businessType = BusinessType.UPDATE)
    @PutMapping("/reserveUserSetting")
    public AjaxResult reserveUserSetting(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateReserveUser(user));
    }

    /**
     * 用户权限设置
     */
    @PreAuthorize("@ss.hasPermi('system:user:permissionSet')")
    @Log(title = "用户权限设置", businessType = BusinessType.UPDATE)
    @PutMapping("/permissionSet")
    public AjaxResult permissionSet(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.permissionSet(user));
    }

    /**
     * 获取用户列表 用户选择器使用
     */
    @GetMapping("/userSelectorList")
    public AjaxResult userSelectorList(SysUser user) {
        List<SysUser> list = userService.userSelectorList(user);
        return AjaxResult.success("成功", list);
    }

    /**
     * @Auther: yangcd
     * @Date: 2023/11/2 17:50
     * @Description: 查询用户信息
     */
    @GetMapping("/selectorUserList")
    public AjaxResult selectorUserList(SysUser user) {
        List<SysUser> list = userService.selectorUserList(user);
        return AjaxResult.success("成功", list);
    }

    /**
     * @Auther: yangcd
     * @Date: 2023/11/2 18:53
     * @param user
     * @Description: 档案管理需要用户信息
     */
    @GetMapping("/archivesUserList")
    public TableDataInfo archivesUserList(SysUser user) {
        startPage();
        List<SysUser> list = userService.archivesUserList(user);
        return getDataTable(list);
    }

    @GetMapping("/info/{userId}")
    public AjaxResult getUserInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        if (commonConfigService.selectThreeMemberSwitch()) {
            if (SysUser.isAdmin(userId) || SysUser.isSecAdmin(userId) || SysUser.isAudAdmin(userId)) {
                ajax.put("roles", roles.stream().filter(r -> r.isAdmin() || r.isSecAdmin() || r.isAudAdmin()).collect(Collectors.toList()));
            } else {
                ajax.put("roles", roles.stream().filter(r -> !r.isAdmin() && !r.isSecAdmin() && !r.isAudAdmin()).collect(Collectors.toList()));
            }
        } else {
            ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        }
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
            ajax.put("userDeptPostList", userDeptPostService.selectByUserId(userId));
            ajax.put("positionInfos", userService.selectTechnicalPositionInfoByUserId(userId));
            ajax.put("educationAndDegreeInfos", userService.selectEducationAndDegreeInfoByUserId(userId));
            ajax.put("rewardsInfos", userService.selectRewardsAndPenaltiesInfoByUserId(userId));
            ajax.put("sysUserOtherInfoList", userService.selectOtherInfoByUserId(userId));
            ajax.put("appraisalInfos", userService.selectAnnualAppraisalInfoByUserId(userId));
            ajax.put("familyInfos", userService.selectFamilyMemberSocialInfoByUserId(userId));
            ajax.put("sysUserResumeInfoList", userService.selectResumeInfoByUserId(userId));
            ajax.put("sysUserWorkUnitAndPositionInfoList", userService.selectWorkUnitAndPositionInfoByUserId(userId));
            ajax.put("secondmentWorkInfos", userService.selectSecondmentWorkInfoByUserId(userId));
        }
        return ajax;
    }
    @GetMapping("/info/selectRewardsAndPenaltiesInfoByUserId")
    public AjaxResult selectRewardsAndPenaltiesInfoByUserId(Long userId) {
        return AjaxResult.success(userService.selectRewardsAndPenaltiesInfoByUserId(userId));
    }
    /**
     * 通用上传请求
     */
    @PostMapping("/uploadLrmx")
    public AjaxResult uploadLrmxFile(MultipartFile file) throws Exception
    {
        try
        {
            LrmxPerson data = userService.readLrmxFile(file.getInputStream());
            /*String idcard = data.getIdcard();
            if(StringUtils.isEmpty(idcard)) {
                return AjaxResult.error("身份证号码不能为空");
            }
            SysUser sysUser = userService.selectUserByIdcard(idcard);
            if(sysUser != null) {
                return AjaxResult.error("人员已经存在");
            }*/
            List<LrmxPerson> personList = new ArrayList<>();
            personList.add(data);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("data", personList);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/importFromLrmx")
    public AjaxResult importFromLrmx(@RequestBody List<LrmxPerson> personList) throws Exception
    {
        try
        {
            userService.importFromLrmx(personList);
            AjaxResult ajax = AjaxResult.success();
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @PostMapping("/listByCondition")
    public TableDataInfo listByCondition(@RequestBody JSONObject params) {
        JSONArray expList = params.getJSONArray("expList");
        JSONArray deptIdList = params.getJSONArray("deptIdList");
        startPage();
        StringBuffer sb = new StringBuffer();
        for (int i = 0, len = expList.size(); i < len; i++) {
            JSONObject exp = expList.getJSONObject(i);
            if (exp.containsKey("l")) {
                sb.append(exp.getString("l"));
            }
            String column = exp.getString("c");
            sb.append("u2.").append(column);
            String op = exp.getString("o");
            String strVal = "";
            Object value = exp.get("v");
            if (value != null) {
                if (value instanceof String) {
                    strVal = (String) value;
                } else if (value instanceof List) {
                    List valList = (List) value;
                    for (Object o : valList) {
                        if (strVal.length() > 0) {
                            strVal += ",";
                        }
                        strVal += "'" + (String) o + "'";
                    }
                }
            }
            op = op.replaceAll("\\{c\\}", column);
            op = op.replaceAll("\\{v\\}", strVal);
            sb.append(op);
            if (exp.containsKey("r")) {
                sb.append(exp.getString("r"));
            }
            String lo = exp.getString("lo");
            if (StringUtils.isNotEmpty(lo)) {
                sb.append(lo);
            }
        }
        List<Long> deptIds = new ArrayList<>();
        if (deptIdList != null && deptIdList.size() > 0) {
            for (int i = 0, len = deptIdList.size(); i < len; i++) {
                deptIds.add(deptIdList.getLongValue(i));
            }
        }
        List<SysUser> list = userService.selectUserListByCondition(sb.toString(), deptIds);
        return getDataTable(list);
    }

    @PostMapping("/encryptMobile")
    public AjaxResult encryptMobile(SysUser user) throws Exception {
        try {
            List<SysUser> list = userService.selectUserList(user);
            for (SysUser sysUser : list) {
                userService.encryptMobile(sysUser);
            }
            AjaxResult ajax = AjaxResult.success();
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/synchronousDept")
    public AjaxResult synchronousDept() throws Exception {
        try {
            List<SysUser> list = userService.selectUserList(new SysUser());
            for (SysUser user : list) {
                userService.synchronousDept(user);
            }
            AjaxResult ajax = AjaxResult.success();
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 获取考核确认用户数据
     * @param user
     * @return
     */
    @GetMapping("/vSysUserList")
    public AjaxResult vSysUserList(VSysUser user) {
        List<VSysUser> list = userService.selectVSysUserList(user);
        return AjaxResult.success("成功", list);
    }

    /**
     * @Auther: yangcd
     * @Date: 2024/9/12 17:55
     * @param user
     * @Description: 根据用户角色筛选数据
     */
    @GetMapping("/vSysUserListByRoles")
    public AjaxResult vSysUserListByRoles(VSysUser user) {
        List<VSysUser> list = userService.selectVSysUserListByRoles(user);
        return AjaxResult.success("成功", list);
    }

    /**
     * 下载word模板
     * @param request
     * @param response
     */
    @GetMapping("/downloadWordTpl")
    public void downloadWordTpl(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/word/kj-word-user-tpl.docx");
            InputStream is = classPathResource.getInputStream();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = "干部任免审批表导入模板.docx";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * word导入
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/importUserByWord")
    public AjaxResult importUserByWord(HttpServletRequest request, HttpServletResponse response, Long deptId) {
        if (request instanceof MultipartHttpServletRequest) {
            String path = RuoYiConfig.getUploadPath() + "/importZip/";
            File folder = new File(path);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> multipartFileList = multipartRequest.getFiles("file");
            List<File> fileList = new LinkedList<>();
            try {
                for (int i = 0; i < multipartFileList.size(); i++) {
                    MultipartFile multipartFile = multipartFileList.get(i);
                    if (null != multipartFile && !multipartFile.isEmpty()) {
                        String fileName = multipartFile.getOriginalFilename();
                        // 创建临时文件
                        File tempFile = new File(folder, DateUtils.dateTimeNow() + File.separator + fileName);
                        File parentFile = tempFile.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        // 使用 transferTo 方法将 MultipartFile 内容传输到临时文件
                        multipartFile.transferTo(tempFile);
                        fileList.add(tempFile);
                    }

                }
            } catch (Exception e) {
                return AjaxResult.error(e.getMessage());
            }

            final String importId = IdUtils.randomUUID();
            final String userName = SecurityUtils.getUsername();
            redisCache.setCacheMapValue(importId, "totalRecord", fileList.size());
            CompletableFuture.runAsync(() -> {
                try {
                    WordImportContextHolder.setImportId(importId);
                    WordImportContextHolder.setUserName(userName);
                    long total = 0;
                    Map<String, String> errFileMap = new LinkedHashMap<>();
                    for (int i = 0; i < fileList.size(); i++) {
                        File file = fileList.get(i);
                        String fileName = file.getName();
                        try {
                            WordUserVo wordUserVo = WordImportUtil.readUserByWord(file);
                            wordUserVo.setDeptId(deptId);
                            int num = userService.importWordUser(wordUserVo);
                            total += num;
                        } catch (Exception e) {
                            errFileMap.put(fileName, e.getMessage());
                        } finally {
                            if (null != file && file.exists()) {
                                file.delete();
                            }
                        }

                    }
                    if (errFileMap.isEmpty()) {
                        redisCache.setCacheMapValue(importId, "success", String.format("操作成功。共导入 %d 条数据", total));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("部分导入成功！共导入 %d 条数据。<br/>", total));
                        sb.append("以下文件导入失败!<br/>");
                        Iterator<Map.Entry<String, String>> it = errFileMap.entrySet().iterator();
                        int i = 1;
                        while (it.hasNext()) {
                            Map.Entry<String, String> entry = it.next();
                            sb.append(String.format("%d.文件名：%s。失败原因：%s<br/>", i, entry.getKey(), entry.getValue()));
                            i++;
                        }
                        redisCache.setCacheMapValue(importId, "error", sb.toString());
                    }
                } finally {
                    WordImportContextHolder.clear();
                }
            });
            return AjaxResult.success("操作成功。后台导入中...", importId);

        } else {
            return AjaxResult.error("未检测到文件，请选择文件！");
        }
    }

    /**
     * 导出为word
     * @param response
     * @param userIds
     */
    @GetMapping("/exportUserWord")
    public void exportUserWord(HttpServletRequest request, HttpServletResponse response, Long[] userIds) {
        if (ObjectUtil.isEmpty(userIds)) {
            throw new IllegalArgumentException("请选择要导出的用户");
        }
        ServletOutputStream outputStream;
        try {
            outputStream = response.getOutputStream();
            List<SysUser> userList = userService.selectUserListByUserIds(Arrays.asList(userIds));
            if (ObjectUtil.isEmpty(userList)) {
                throw new IllegalArgumentException("未获取到用户信息！");
            }
            if (userList.size() == 1) {
                SysUser user = userList.get(0);
                Long userId = user.getUserId();
                String name = user.getName();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition","attachment; filename=\"" +
                        URLEncoder.encode(String.format("%s_%s", userId,name),"utf-8") +".docx\"");
                WordImportUtil.writeUserToWord(user, outputStream);
            } else {
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("用户任免审批表导出", "utf-8") + ".zip\"");
                ZipOutputStream zos = new ZipOutputStream(outputStream);
                for (int i = 0; i < userList.size(); i++) {
                    SysUser user = userList.get(i);
                    Long userId = user.getUserId();
                    String name = user.getName();
                    String deptName = user.getDept().getDeptName();
                    String fileName = String.format("%s/%s_%s.docx", deptName, userId, name);
                    zos.putNextEntry(new ZipEntry(fileName));
                    WordImportUtil.writeUserToWord(user, zos);
                    zos.flush();
                    zos.closeEntry();
                }
                zos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("用户信息导出为word异常！" + e.getMessage());
        }
    }

    /**
     * 批量修改用户所在部门
     */
//    @PreAuthorize("@ss.hasPermi('system:user:updateDeptByUserIds')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateDeptByUserIds")
    public AjaxResult updateDeptByUserIds(@RequestBody SysUser user) {
        return toAjax(userService.updateDeptByUserIds(user));
    }

    /**
     * 查询干部标签用户列表
     */
    @GetMapping("/selectUserListByAbilityLabel")
    public TableDataInfo selectUserByAbilityLabel(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserByAbilityLabel(user);
        setEduInfo(list);
        return getDataTable(list);
    }

    /**
     * 恢复用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:recover')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/recover/{userIds}")
    public AjaxResult recover(@PathVariable Long[] userIds) {
        return toAjax(userService.recoverUserByIds(userIds));
    }

    /**
     * 彻底删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{userIds}")
    public AjaxResult delete(@PathVariable Long[] userIds) {
        return toAjax(userService.completelyDeleteUserByIds(userIds));
    }

    /**
     * 还原所有删除的用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:recover')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/recoverAll")
    public AjaxResult recover() {
        return toAjax(userService.recoverAllUserByIds());
    }
    /**
     * 彻底删除已经被删除的用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteAll")
    public AjaxResult delete() {
        return toAjax(userService.completelyDeleteAllUserByIds());
    }
}
