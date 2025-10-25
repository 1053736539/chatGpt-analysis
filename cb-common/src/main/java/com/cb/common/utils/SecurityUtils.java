package com.cb.common.utils;

import com.cb.common.constant.Constants;
import com.cb.common.constant.HttpStatus;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.encipher.encoder.EncipherPasswordEncoder;
import com.cb.common.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cb.common.core.domain.model.LoginUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 安全服务工具类
 * 
 * @author ruoyi
 */
public class SecurityUtils
{
    /**
     * 获取用户账户
     **/
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }
    /**
     * 获取用户账户 中文名
     **/
    public static String getUserNickname()
    {
        try
        {
            return getLoginUser().getUser().getNickName();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前用户在线部门
     * @return
     */
    public static SysDept getOnlineDept() {
        try {
            LoginUser loginUser = getLoginUser();
            return loginUser.getUser().getDept();
        } catch (Exception e) {
            throw new CustomException("获取在线部门信息异常", HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        PasswordEncoder passwordEncoder = new EncipherPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        PasswordEncoder passwordEncoder = new EncipherPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * 判断是否为安全管理员
     * @param userId
     * @return
     */
    public static boolean isSecAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 判断是否为安全审计员
     * @param userId
     * @return
     */
    public static boolean isAudAdmin(Long userId) {
        return userId != null && 3L == userId;
    }
    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public static boolean hasPermi(String permission)
    {
        return hasPermi(getLoginUser().getPermissions(), permission);
    }

    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public static boolean hasPermi(Collection<String> authorities, String permission)
    {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(x -> Constants.ALL_PERMISSION.equals(x) || PatternMatchUtils.simpleMatch(x, permission));
    }


    /**
     * 验证用户是否拥有某个角色
     *
     * @param role 角色标识
     * @return 用户是否具备某角色
     */
    public static boolean hasRole(String role) {
        List<SysRole> roleList = getLoginUser().getUser().getRoles();
        Collection<String> roles = roleList.stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
        return hasRole(roles, role);
    }

    /**
     * 验证用户是否拥有某个角色
     * @param role  角色标识
     * @return 用户是否具备某角色
     */
    public static Boolean hasForceRole(String role){
        List<SysRole> roleList = getLoginUser().getUser().getRoles();
        Collection<String> roles = roleList.stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
        return hasForceRole(roles, role);
    }

    /**
     * 判断是否包含角色
     *
     * @param roles 角色列表
     * @param role 角色
     * @return 用户是否具备某角色权限
     */
    public static boolean hasRole(Collection<String> roles, String role) {
        return roles.stream().filter(StringUtils::hasText)
                .anyMatch(x -> Constants.SUPER_ADMIN.equals(x) || PatternMatchUtils.simpleMatch(x, role));
    }

    public static boolean hasForceRole(Collection<String> roles, String role) {
        return roles.stream().filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(x, role));
    }
}
