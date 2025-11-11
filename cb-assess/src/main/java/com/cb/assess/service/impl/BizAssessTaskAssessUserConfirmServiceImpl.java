package com.cb.assess.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.domain.VOrdinaryAssessTask;
import com.cb.assess.domain.vo.VTaskAssessConfirmUser;
import com.cb.assess.mapper.BizAssessIndicatorProMapper;
import com.cb.assess.service.IBizAssessUserService;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessTaskAssessUserConfirmMapper;
import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.service.IBizAssessTaskAssessUserConfirmService;

/**
 * 考核任务下发确认Service业务层处理
 *
 * @author ouyang
 * @date 2024-05-31
 */
@Service
public class BizAssessTaskAssessUserConfirmServiceImpl implements IBizAssessTaskAssessUserConfirmService {
    @Autowired
    private BizAssessTaskAssessUserConfirmMapper confirmMapper;


    @Autowired
    private IBizAssessUserService assessUserService;


    @Autowired
    private BizAssessIndicatorProMapper proMapper;

    @Override
    public List<BizAssessTaskAssessUserConfirm> selectTaskAssessUserConfirmByTaskId(String taskId) {
        return confirmMapper.selectTaskAssessUserConfirmByTaskId(taskId);
    }

    @Override
    public List<VOrdinaryAssessTask> selectTaskManageNeedConfirmList(VOrdinaryAssessTask vOrdinaryAssessTask) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        return confirmMapper.selectTaskManageNeedConfirmList(vOrdinaryAssessTask,deptId);
    }
    @Override
    public BizAssessTaskAssessUserConfirm selectAssessUser(BizAssessTaskManage manage) {
        SysUser currUser = SecurityUtils.getLoginUser().getUser();
        Long deptId = currUser.getDeptId();
        BizAssessTaskAssessUserConfirm userConfirm = confirmMapper.selectTaskAssessUserConfirm(manage.getTaskId(), deptId);
        if (userConfirm != null) {
            return userConfirm;
        }
        BizAssessTaskAssessUserConfirm defaultUserConfirm = new BizAssessTaskAssessUserConfirm();
        defaultUserConfirm.setDeptId(deptId);
        defaultUserConfirm.setTaskId(manage.getTaskId());
        String proId = manage.getProId();
        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        manage.setPro(pro);
        List<SysUser> users = assessUserService.selectAssessedUserByTaskManage(manage);
        Map<Long, List<SysUser>> map = users.stream().collect(Collectors.groupingBy(SysUser::getDeptId));
        List<SysUser> deptUsers = map.get(deptId);
        String jsonResult;
        if(CollectionUtil.isEmpty(deptUsers)){
            jsonResult = "";
        }else{
            List<Long> userIds = deptUsers.stream().map(SysUser::getUserId).collect(Collectors.toList());
             jsonResult = StringUtils.join( userIds,",");
        }
        defaultUserConfirm.setStatus("1");
        defaultUserConfirm.setUsersJson(jsonResult);
        return defaultUserConfirm;
    }

    @Override
    public BizAssessTaskAssessUserConfirm selectChargeAssessUser(String taskId, String proId, Long deptId) {
        BizAssessTaskAssessUserConfirm userConfirm = confirmMapper.selectTaskAssessUserConfirm(taskId, deptId);
        if (userConfirm != null) {
            return userConfirm;
        }
        BizAssessTaskAssessUserConfirm defaultUserConfirm = new BizAssessTaskAssessUserConfirm();
        defaultUserConfirm.setDeptId(deptId);
        defaultUserConfirm.setTaskId(taskId);
        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        BizAssessTaskManage manage = new BizAssessTaskManage();
        manage.setTaskId(taskId);
        manage.setProId(proId);
        manage.setPro(pro);
        List<SysUser> users = assessUserService.selectAssessedUserByTaskManage(manage);
        Map<Long, List<SysUser>> map = users.stream().collect(Collectors.groupingBy(SysUser::getDeptId));
        List<SysUser> deptUsers = map.get(deptId);
        String jsonResult;
        if(CollectionUtil.isEmpty(deptUsers)){
            jsonResult = "";
        }else{
            List<Long> userIds = deptUsers.stream().map(SysUser::getUserId).collect(Collectors.toList());
            jsonResult = StringUtils.join( userIds,",");
        }
        defaultUserConfirm.setStatus("1");
        defaultUserConfirm.setUsersJson(jsonResult);
        return defaultUserConfirm;
    }

    /**
     * 驳回
     * @param confirm
     * @return
     */
    public int rejection(BizAssessTaskAssessUserConfirm confirm) {
        if(StringUtils.isBlank(confirm.getId())){
            throw new RuntimeException("参数异常");
        }
        String status = confirm.getStatus();
        String rejection = confirm.getRejection();
        if(StringUtils.isBlank(rejection)){
            throw new RuntimeException("请输入驳回理由");
        }
        if(StringUtils.isBlank(status)){
            confirm.setStatus("2");
        }
        return confirmMapper.updateBizAssessTaskAssessUserConfirmById(confirm);
    }

    /**
     * 新增考核任务下发确认
     *
     * @param confirm 考核任务下发确认
     * @return 结果
     */
    @Override
    public int saveOrUpdate(BizAssessTaskAssessUserConfirm confirm) {

        BizAssessTaskAssessUserConfirm userConfirm = confirmMapper.selectTaskAssessUserConfirm(confirm.getTaskId(), confirm.getDeptId());

        String usersJson = confirm.getUsersJson();
        if(StringUtils.isBlank(usersJson)){
            throw new RuntimeException("未选参加考核的用户！");
        }

        List<Long> confirmUsers = Arrays.stream(usersJson.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        String cycleDesc = confirm.getCycleDesc();
        String confirmId = userConfirm != null?userConfirm.getId():null;
        List<VTaskAssessConfirmUser> confirmedUsers = confirmMapper.selectConfirmUser(cycleDesc,confirmId);

        List<VTaskAssessConfirmUser> repetitiveList = confirmedUsers.stream()
                .filter(o -> confirmUsers.indexOf(o.getUserId()) > -1).collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(repetitiveList)){
            List<String> strList = repetitiveList.stream().map(o -> {
                String userName = o.getUserName();
                String deptName = o.getDeptName();

                String format = String.format("【%s，参加考核部门:%s】", userName, deptName);
                return format;
            }).collect(Collectors.toList());
            throw new RuntimeException("存在已经确认的人员："+ StringUtils.join(strList, ';') +"，请检查！");
        }
        int result;
//        confirm.setStatus("1");
        if (userConfirm == null) {
            confirm.setId(IdUtils.randomUUID());
            confirm.setCreateBy(SecurityUtils.getUsername());
            confirm.setCreateTime(DateUtils.getNowDate());
            result = confirmMapper.insertBizAssessTaskAssessUserConfirm(confirm);
        } else {
            confirm.setUpdateBy(SecurityUtils.getUsername());
            confirm.setUpdateTime(DateUtils.getNowDate());
            result = confirmMapper.updateBizAssessTaskAssessUserConfirm(confirm);
        }
        return result;
    }

    @Override
    public int deleteTaskAssessUserConfirmByTaskId(String taskId) {
        return confirmMapper.deleteBizAssessTaskAssessUserConfirmById(taskId);
    }

    @Override
    public BizAssessTaskAssessUserConfirm selectConfirmInfoById(String id) {
        return confirmMapper.selectConfirmInfoById(id);
    }

    @Override
    public int updateConfirmInfo(BizAssessTaskAssessUserConfirm confirm) {
        return confirmMapper.updateBizAssessTaskAssessUserConfirmById(confirm);
    }
}
