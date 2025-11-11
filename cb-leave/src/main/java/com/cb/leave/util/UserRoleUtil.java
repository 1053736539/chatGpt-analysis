package com.cb.leave.util;

import cn.hutool.core.util.ObjectUtil;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.core.domain.entity.SysUser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-03-25 10:57
 * @Version: 1.0
 **/
public class UserRoleUtil {

    /**
     * 是否Admin
     * @param user
     * @return
     */
    public static boolean nameIsAdmin(SysUser user){
        return "admin".equals(user.getUserName());
    }

    /**
     * 是否主要负责人
     * @param user
     * @return
     */
    public static boolean isMainLeader(SysUser user){
        return "1".equals(user.getIsMainLeader());
    }

    /**
     * 是否主持工作
     * @param user
     * @return
     */
    public static boolean isHostWork(SysUser user){
        return "1".equals(user.getIsHostingWork());
    }

    /**
     * 所属部门是委领导
     * @param user
     * @return
     */
    public static boolean orgIsWeilingdao(SysUser user){
        SysDept dept = user.getDept();
        if(null == dept){
            return false;
        }
        return "委领导".equals(dept.getDeptName());
    }

    /**
     * 是否是书记
     * @param user
     * @return
     */
    public static boolean hasRole_shuji(SysUser user){
        return hasRole(user,"shuji");
    }

    /**
     * 是否是副书记
     * @param user
     * @return
     */
    public static boolean hasRole_fushuji(SysUser user){
        return hasRole(user,"fushuji");
    }

    /**
     * 是否是常委
     * @param user
     * @return
     */
    public static boolean hasRole_changwei(SysUser user){
        return hasRole(user,"changwei");
    }

    /**
     * 是否是组织部管理员
     * @param user
     * @return
     */
    public static boolean hasRole_organization_admin(SysUser user){
        return hasRole(user, "organization_admin");
    }

    /**
     * 是否含有部门负责人的角色
     * @param user
     * @return
     */
    public static boolean hasRole_dept_master_leader(SysUser user){
        return hasRole(user, "dept_master_leader");
    }

    /**
     * 获取 分管xxx部门的角色的key
     * @param user
     * @return
     */
    public static List<String> getFgRoleKeys(SysUser user){
        List<SysRole> roles = user.getRoles();
        if(ObjectUtil.isEmpty(roles)){
            return Collections.emptyList();
        }
        return roles.stream().map(SysRole::getRoleKey)
                .filter(o->o.startsWith("fg_"))
                .collect(Collectors.toList());
    }

    public static boolean hasRole(SysUser user, String roleKey){
        List<SysRole> roles = user.getRoles();
        if(ObjectUtil.isEmpty(roles)){
            return false;
        }
        return roles.stream().anyMatch(role -> role.getRoleKey().equals(roleKey));
    }

}
