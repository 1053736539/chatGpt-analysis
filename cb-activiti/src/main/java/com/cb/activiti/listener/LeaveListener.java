package com.cb.activiti.listener;

import cn.hutool.core.date.DateUtil;
import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.enums.UserLevelEnum;
import com.cb.activiti.service.IBizLeaveService;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.message.service.MessageService;
import com.cb.system.domain.SysUserSupervisor;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;
import com.cb.system.service.ISysUserSupervisorService;
import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.service.IWorkSituationService;
import lombok.AllArgsConstructor;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务节点监听器
 */
@Component
@AllArgsConstructor
public class LeaveListener implements TaskListener {

    private IdentityService identityService;
    private TaskService taskService;
    private HistoryService historyService;
    private RuntimeService runtimeService;
    private ISysUserService userService;
    private ISysDeptService deptService;
    private IBizLeaveService leaveService;
    private ILeaveBalancesService  leaveBalancesService;
    private ISysUserSupervisorService userSupervisorService;
    private IWorkSituationService workSituationService;
    private MessageService messageService;

    @Override
    public void notify(DelegateTask delegateTask) {
        String definitionKey = delegateTask.getTaskDefinitionKey();
        String processInstanceId = delegateTask.getProcessInstanceId();
        Map<String, Object> variables = delegateTask.getVariables();
        // 获取特定的流程变量
        if("_organization_dept".equals(definitionKey)){
        //组织部节点2
           handleUserLeader(delegateTask,processInstanceId,variables);
        } else if("_inspection_team_head".equals(definitionKey)){
        // 巡察组组长节点
            String createUserLevel = (String) variables.get("createUserLevel");
            if(UserLevelEnum.LEVEL_5.getCode().equals(createUserLevel)){
                handleUserLeader(delegateTask,processInstanceId,variables);
            }
        }
        else if("_apply_user_two".equals(delegateTask)){
        //返回至申请人节点
            handleApplyuser(delegateTask,processInstanceId,variables);
        }
        else if("_dept_leader".equals(definitionKey)){
            //部门领导节点
            String createUserLevel = (String) variables.get("createUserLevel");
            if(UserLevelEnum.OTHER.getCode().equals(createUserLevel)){
                handleUserLeader(delegateTask,processInstanceId,variables);
            }
        }
        else if("_committee_member".equals(definitionKey)){
            //领衔常委(委员)节点
//            handlerCheckAssignee(delegateTask,processInstanceId,variables);
//            handleCommitteeMember(delegateTask);
            handleUserLeader(delegateTask,processInstanceId,variables);
        }  else if("_leader_charge".equals(definitionKey)){
            //市纪委市监委分管领导节点
            handleLeaderCharge(delegateTask);
        } else if("_deputy_secretary".equals(definitionKey)){
            //分管副书记节点
            String createUserLevel = (String) variables.get("createUserLevel");
//            handleDeputySecretary(delegateTask);
            handleUserLeader(delegateTask,processInstanceId,variables);
        } else if("_main_leader".equals(definitionKey)){
            //书记节点
            String createUserLevel = (String) variables.get("createUserLevel");
            handleUserLeader(delegateTask,processInstanceId,variables);
        }
        else if("_destruction_leave".equals(definitionKey)){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()//创建流程实例查询
                    .processInstanceId(processInstanceId)//使用流程实例ID查询
                    .singleResult();
            String businessKey = processInstance.getBusinessKey();
            Long leaveId = Long.valueOf(businessKey);
            BizLeave bizLeave = leaveService.selectBizLeaveById(leaveId);
            if(EVENTNAME_CREATE.equals(delegateTask.getEventName())){
                bizLeave.setStatus("1");
                leaveService.updateBizLeave(bizLeave);
                //请假审批通过
                // TODO 扣除假期额度逻辑
                SysUser sysUser = userService.selectUserByUserName(bizLeave.getApplyUserId());
                if (sysUser==null){
                    throw new RuntimeException("用户不存在: " + bizLeave.getApplyUserId());
                }
                //病假通过时发送通知给机关党委
                Long type = bizLeave.getType();
                if(Long.valueOf(2L).equals(type)){
                    String roleKey = "jgdw_user";
                    List<SysUser> jgdwUserList = userService.listUserByRoleKey(roleKey);
                    if(!jgdwUserList.isEmpty()){
                        String title = "病假审批通过";
                        String beginDateStr = DateUtil.formatDate(bizLeave.getLeaveStartTime());
                        String endDateStr = DateUtil.formatDate(bizLeave.getLeaveEndTime());
                        String message = String.format("%s的(病假)审批通过。请假时间：%s至%s，共%.1f天。",sysUser.getNickName(),beginDateStr,endDateStr,bizLeave.getLeaveNum()) ;
                        List<Long> userIds = jgdwUserList.stream().map(SysUser::getUserId).collect(Collectors.toList());
                        messageService.sendMessage2User(title,message,"admin",userIds);
                    }
                }

                try {
                    if(Long.valueOf(1L).equals(type)){
                        //销假时扣除公休假天数
                        leaveBalancesService.updateLeaveBalancesAfterApproval(sysUser.getUserId(),bizLeave.getType(),bizLeave.getLeaveNum(), Integer.valueOf(bizLeave.getHolidayYear()));
                    }
                    WorkSituation workSituation = new WorkSituation();
                    workSituation.setUserId(sysUser.getUserId());
                    workSituation.setLeaveTypeId(bizLeave.getType());
                    workSituation.setLeaveDay(bizLeave.getLeaveNum());//请假天数
                    workSituation.setSituationYear(bizLeave.getHolidayYear());//哪一年请假
                    // 对 totalTime 进行 null 检查
                    if (bizLeave.getTotalTime() != null) {
                        workSituation.setTotalTime(bizLeave.getTotalTime());
                    }
                    workSituation.setRealityStartTime(bizLeave.getLeaveStartTime());
                    workSituation.setRealityEndTime(bizLeave.getLeaveEndTime());
                    //  将请假记录新增到考勤表中
                    workSituationService.addWorkSituation(workSituation);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
            else if(EVENTNAME_COMPLETE.equals(delegateTask.getEventName())){
                // TODO 销假逻辑
                bizLeave.setDestructionDate(bizLeave.getLeaveEndTime());
                leaveService.updateBizLeave(bizLeave);
            }

        }
    }

    private void handleApplyuser(DelegateTask delegateTask,String processInstanceId, Map<String,Object> variables) {
        String applyUserName = delegateTask.getVariable("applyUserId", String.class);
        if(!applyUserName.isEmpty()){
            delegateTask.setAssignee(applyUserName);
        }

    }

    private void handlerInspectionTeamHead(DelegateTask delegateTask,String processInstanceId, Map<String,Object> variables) {
        //获取上面的多个任务
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricTaskInstanceEndTime().desc().list();
        String nextUserName = (String) variables.get("nextUserName");
        if(StringUtils.isNotNull(nextUserName)&&StringUtils.isNotEmpty(nextUserName)){
            delegateTask.setAssignee(nextUserName);
        }

    }

    private void handlerCheckAssignee(DelegateTask delegateTask,String processInstanceId, Map<String,Object> variables) {
        String nextUserName = (String) variables.get("nextUserName");
        if(StringUtils.isNotNull(nextUserName)){
            delegateTask.setAssignee(nextUserName);
        }
    }

    private void handleUserLeader(DelegateTask delegateTask, String processInstanceId, Map<String,Object> variables){
        //获取上面的多个任务
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricTaskInstanceEndTime().desc().list();
        String nextUserName = (String) variables.get("nextUserName");
        if(StringUtils.isNotNull(nextUserName)){
            delegateTask.setAssignee(nextUserName);
        }
        //获取最后面一个任务，即本任务的上一个任务
//        HistoricTaskInstance lastTask=taskList.get(0);
        //获取上一个任务的执行人
//        String lastTaskAssignee = lastTask.getAssignee();
//        if(lastTaskAssignee != null ){
//            SysUser user = userService.selectUserByUserName(lastTaskAssignee);
//            List<SysUser> leaders = userService.selectMainLeaderByDeptId(user.getDeptId());
//            if(!leaders.isEmpty()){
//                delegateTask.setAssignee(leaders.get(0).getUserName());
//            }
//        }
    }


        private void handleCommitteeMember(DelegateTask delegateTask){
        //领衔常委(委员)
        String applyUserName = delegateTask.getVariable("applyUserId", String.class);
        SysUser user = userService.selectUserByUserName(applyUserName);
        SysUser leaderCharge = userService.selectDeptLeaderCharge(user.getDeptId(),Arrays.asList(33L,34L),false);
        if(null != leaderCharge){
            delegateTask.setAssignee(leaderCharge.getUserName());
        }
    }

    private void handleLeaderCharge(DelegateTask delegateTask){
        // 市纪委市监委分管领导
        String applyUserName = delegateTask.getVariable("applyUserId", String.class);
        SysUser user = userService.selectUserByUserName(applyUserName);
        SysUser leaderCharge = userService.selectDeptLeaderCharge(user.getDeptId(),null,false);
        if(null != leaderCharge){
            delegateTask.setAssignee(leaderCharge.getUserName());
        }
    }

    private void handleDeputySecretary(DelegateTask delegateTask){
        String processInstanceId = delegateTask.getProcessInstanceId();
        List<HistoricTaskInstance> taskList=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceEndTime().desc().list();
        //获取最后面一个任务，即本任务的上一个任务
        HistoricTaskInstance lastTask=taskList.get(0);
        //获取上一个任务的执行人
        String lastTaskAssignee = lastTask.getAssignee();
        SysUser lastTaskAssigneeUser = userService.selectUserByUserName(lastTaskAssignee);
        SysUserSupervisor query = new SysUserSupervisor();
        query.setUserId(lastTaskAssigneeUser.getUserId().intValue());
        List<SysUserSupervisor> supervisorList = userSupervisorService.selectSysUserSupervisorList(query);
        if(!supervisorList.isEmpty()){
            Integer supervisorId = supervisorList.get(0).getSupervisorId();
            SysUser supervisor = userService.selectUserById(supervisorId.longValue());
            delegateTask.setAssignee(supervisor.getUserName());
        }
    }
}
