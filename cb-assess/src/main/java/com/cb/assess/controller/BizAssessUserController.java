package com.cb.assess.controller;

import com.cb.assess.assessUserQuery.AssessUserQueryContext;
import com.cb.assess.assessUserQuery.CommonUserStrategy;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这个控制器用于查询指定的用户
 */
@RestController
@RequestMapping("/assess/user")
public class BizAssessUserController {
    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService sysDeptService;
    /**
     * 获取 二级巡视员、总师、督查员、主要负责人
     * type 1为事业单位，2为公务员
     * @return
     */
    @GetMapping("/getMainUserList")
    public AjaxResult getMainUserList(String type){
        String[] workCodes = {"121", "212"};
        ArrayList<String> strings = new ArrayList<>();
        if (type.equals("1")){
            strings.add("2");
            strings.add("1");
        }else if (type.equals("2")){
            strings.add("3");
        }else {
            strings.add("2");
            strings.add("1");
            strings.add("3");
        }
        AssessUserQueryContext queryContext = new AssessUserQueryContext()
                .setIdentityTypes(strings)
                .setPositive(true)
                .setIncludeMainLeader(true)
                .setIncludeHostWork(true)
                .setWorkCodes(Arrays.asList(workCodes))
                .setStrategy(new CommonUserStrategy());
        List<SysUser> sysUsers = queryContext.queryAssessedUserNoDept();
        List<SysUser> collect = sysUsers.stream().filter(o -> !o.getDeptId().equals(135)).collect(Collectors.toList());
        return AjaxResult.success(collect);
    }

    /**
     * 局领导人员
     * @return
     */
    @GetMapping("/getLeaderList")
    public AjaxResult getLeaderList(){
        String[] workCodes = {"111", "112"};
        ArrayList<String> strings = new ArrayList<>();
        strings.add("2");
        strings.add("1");
        strings.add("3");

        AssessUserQueryContext queryContext = new AssessUserQueryContext()
                .setIdentityTypes(strings)
                .setPositive(true)
                .setIncludeMainLeader(false)
                .setIncludeHostWork(false)
                .setWorkCodes(Arrays.asList(workCodes))
                .setStrategy(new CommonUserStrategy());
        List<SysUser> sysUsers = queryContext.queryAssessedUserNoDept();
        return AjaxResult.success(sysUsers);
    }

    /**
     * 根据所传的用户id拿这个人的当前部门的所有用户
     * @return
     */
    @GetMapping("/getUserListBySelf")
    public AjaxResult getUserListBySelf(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        SysUser queryUser = new SysUser();
        queryUser.setDeptId(user.getDeptId());
        List<SysUser> sysUsersList = userService.selectUserList(queryUser);
        List<String> workCodes = Arrays.asList("121","122");
        List<SysUser> collect = sysUsersList.stream()
                .filter(e -> {
                    return workCodes.contains(e.getWorkTitleCode()) || workCodes.contains(e.getWorkPostCode()) || "1".equals(e.getIsHostingWork());
                })
                .collect(Collectors.toList());
        List<SysUser> collect2 = sysUsersList.stream()
                .filter(e -> {
                    return !(workCodes.contains(e.getWorkTitleCode()) || workCodes.contains(e.getWorkPostCode()) || "1".equals(e.getIsHostingWork()));
                })
                .collect(Collectors.toList());
        //公务员
        ArrayList<String> civilServant = new ArrayList<>();
        civilServant.add("2");
        civilServant.add("1");
        //事业单位
        ArrayList<String> institution = new ArrayList<>();
        institution.add("3");
        List<SysUser> civilServantUser = sysUsersList.stream().filter(e -> civilServant.contains(e.getIdentityType())).collect(Collectors.toList());
        List<SysUser> institutionUser = sysUsersList.stream().filter(e -> institution.contains(e.getIdentityType())).collect(Collectors.toList());
        HashMap<String, List<SysUser>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("leader",collect);
        stringListHashMap.put("other",collect2);
        //公务员
        stringListHashMap.put("civilServantUser",civilServantUser);
        //事业单位
        stringListHashMap.put("institutionUser",institutionUser);
        return AjaxResult.success(stringListHashMap);
    }
}
