package com.cb.oa.task;

import com.cb.common.annotation.Log;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.StringUtils;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.service.ISysDeptOutService;
import com.cb.oa.service.ISysUserOutService;
import com.cb.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("oaTask")
public class OaTask {
    @Autowired
    private ISysUserOutService sysUserOutService;
    @Resource
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptOutService sysDeptOutService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OaTask.class);
    public void syncSysUserOutList()
    {
        try {
            SysUserOut sysUserOut = new SysUserOut();
            List<SysUserOut> sysUserOuts = sysUserOutService.selectSysUserOutList(sysUserOut);
            Map<String, SysUserOut> sysUserOutMap = sysUserOuts.stream().filter(e -> StringUtils.isNoneBlank(e.getId())).collect(Collectors.toMap(SysUserOut::getId, o -> o));
            SysUser sysUser = new SysUser();
            List<SysUser> sysUsers = sysUserService.selectUserListNoScpoe(sysUser);
            //过滤掉系统内重名和电话重复的人
            Map<String, Long> sysUsersPhoneCount = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getPhonenumber())).collect(Collectors.groupingBy(SysUser::getPhonenumber, Collectors.counting()));
            Map<String, SysUser> phoneMap = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getPhonenumber())).filter(e->sysUsersPhoneCount.get(e.getPhonenumber())<2).collect(Collectors.toMap(SysUser::getPhonenumber, o -> o));
            Map<String, Long> nameCountMap = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getName())).collect(Collectors.groupingBy(SysUser::getName, Collectors.counting()));
            Map<String, SysUser> nameMap = sysUsers.stream().filter(e -> StringUtils.isNoneBlank(e.getName())).filter(e->nameCountMap.get(e.getName())<2).collect(Collectors.toMap(SysUser::getName, o -> o));
            Integer count=0;
            Integer page=1;
            Integer pageSize=50;
            Integer integer = sysUserOutService.syncSysUserOutList(count, page, pageSize, phoneMap, nameMap, sysUserOutMap);
            LOGGER.info("本次同步用户数据条数",integer);
        }catch (Exception e){
            LOGGER.error("同步用户数据发生错误",e.getMessage());
            e.printStackTrace();
        }

    }

    public void syncSysDeptOutList()
    {
        try {
            Integer integer = sysDeptOutService.syncSysDeptOutList();
            LOGGER.info("本次同步部门数据条数",integer);
        }catch (Exception e){
            LOGGER.error("同步部门数据发生错误",e.getMessage());
            e.printStackTrace();
        }
    }
}
