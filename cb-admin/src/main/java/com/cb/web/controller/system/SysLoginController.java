package com.cb.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.constant.Constants;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysMenu;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginBody;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.exception.user.DefaultPasswordException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.ServletUtils;
import com.cb.framework.web.service.SysLoginService;
import com.cb.framework.web.service.SysPermissionService;
import com.cb.framework.web.service.TokenService;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.service.ISysUserOutService;
import com.cb.system.domain.SysUserPwdModify;
import com.cb.system.domain.vo.DeptPostVo;
import com.cb.system.service.ISysMenuService;
import com.cb.system.service.ISysUserDeptPostService;
import com.cb.system.service.ISysUserPwdModifyService;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

import static com.cb.common.utils.SecurityUtils.encryptPassword;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserPwdModifyService pwdModifyService;

    @Autowired
    private ISysUserDeptPostService userDeptPostService;

    @Autowired
    private ISysUserOutService userOutService;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        try {
            AjaxResult ajax = AjaxResult.success();
            // 生成令牌
            String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                    loginBody.getUuid());
            ajax.put(Constants.TOKEN, token);
            return ajax;
        } catch (DefaultPasswordException e) {
            return AjaxResult.error(405, "DEFAULT_PASSWORD"); // 返回特定的标志
        }
    }

    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(String userName,String oldPassword, String newPassword) {
        SysUser sysUser = userService.selectUserByUserNameAndPassword(userName, encryptPassword(oldPassword));
        if (sysUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        String password = sysUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, encryptPassword(newPassword)) > 0)
        {
            // 设置修改密码记录
            String encryptPassword = encryptPassword(newPassword);
            SysUserPwdModify pwdModify = new SysUserPwdModify();
            pwdModify.setUserId(sysUser.getUserId());
            pwdModify.setNewPassword(encryptPassword);
            pwdModify.setModifyTime(DateUtils.getNowDate());
            pwdModify.setCreateBy(sysUser.getUserName());
            pwdModifyService.insertSysUserPwdModify(pwdModify);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        SysUserPwdModify modify = pwdModifyService.selectSysUserPwdModifyByUserId(user.getUserId());
        if (modify != null) {
            user.setLastPwdModifyTime(modify.getModifyTime());
        } else {
            user.setLastPwdModifyTime(user.getCreateTime());
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        List<DeptPostVo> deptPostVos = userDeptPostService.selectAssociationInfo(user.getUserId(), user.getDeptId());
        ajax.put("dopList", deptPostVos);
        SysUserOut sysUserOut = userOutService.selectSysUserOutById(user.getUserId());
        ajax.put("userOut", sysUserOut);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    // 部门切换
    @PostMapping("changeOnlineDept")
    public AjaxResult changeOnlineDept(@RequestBody DeptPostVo vo) {
        loginService.changeOnlineDept(vo);
        return AjaxResult.success();
    }

    @RequestMapping("/ssoLogin")
    public AjaxResult ssoLogin(@RequestBody JSONObject params) {
        String appId = params.getString("appId");
        String uid = params.getString("uid");
        String timestamp = params.getString("timestamp");
        String sign = params.getString("sign");
        AjaxResult ajax = AjaxResult.success();
        String token = loginService.ssoLogin(appId, uid, timestamp, sign);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @RequestMapping("/oaLogin")
    public void oaLogin(String userId, HttpServletResponse response) throws Exception {
        String url = "http://10.53.2.200/sso?userId=" + userId + "&timestamp=" + System.currentTimeMillis();
        response.sendRedirect(url);
    }
}
