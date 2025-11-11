package com.cb.activiti.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.domain.HistoricActivity;
import com.cb.activiti.domain.InstanceBusiness;
import com.cb.activiti.enums.UserLevelEnum;
import com.cb.activiti.mapper.BizLeaveMapper;
import com.cb.activiti.mapper.TaskMapper;
import com.cb.activiti.domain.TaskVo;
import com.cb.activiti.service.IBizLeaveService;
import com.cb.common.constant.HttpStatus;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.activiti.service.IProcessService;
import com.cb.leave.calendar.domain.Calendar;
import com.cb.leave.calendar.service.ICalendarService;
import com.cb.message.service.MessageService;
import com.cb.system.mapper.SysUserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.omg.CORBA.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 一只闲鹿
 */
@Service
@Transactional
@AllArgsConstructor
public class ProcessServiceImpl implements IProcessService {

    protected final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

    private IdentityService identityService;
    private TaskService taskService;
    private HistoryService historyService;
    private RuntimeService runtimeService;
    private SysUserMapper userMapper;
    private TaskMapper taskMapper;
    private BizLeaveMapper bizLeaveMapper;
    private MessageService messageService;
    private ICalendarService calendarService;


    /**
     * 提交申请
     */
    @Override
    public <T> void submitApply(T entity, String key) throws Exception {
        this.submitApply(entity, key, null);
    }

    @Override
    public <T> void submitApply(T entity, String key, Map<String, Object> variables) throws Exception {
        Class clazz = entity.getClass();

        Method getId = clazz.getDeclaredMethod("getId");
        Long id = (Long) getId.invoke(entity);

        Method setApplyUserId = clazz.getDeclaredMethod("setApplyUserId", String.class);
        Method setApplyUserName = clazz.getDeclaredMethod("setApplyUserName", String.class);
        Method setApplyTime = clazz.getDeclaredMethod("setApplyTime", Date.class);
        Method setProcessKey = clazz.getDeclaredMethod("setProcessKey", String.class);

        Method setUpdateBy = clazz.getSuperclass().getSuperclass().getDeclaredMethod("setUpdateBy", String.class);
        Method setUpdateTime = clazz.getSuperclass().getSuperclass().getDeclaredMethod("setUpdateTime", Date.class);

        Method setInstanceId = clazz.getDeclaredMethod("setInstanceId", String.class);

        String username = SecurityUtils.getUsername();
        String nickName = SecurityUtils.getLoginUser().getUser().getNickName();
        Date now = new Date();

        // 更新流程通用字段
        setApplyUserId.invoke(entity, username);
        setApplyUserName.invoke(entity, nickName);
        setApplyTime.invoke(entity, now);
        setProcessKey.invoke(entity, key);
        setUpdateBy.invoke(entity, username);
        setUpdateTime.invoke(entity, now);

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(username);

        Map<String, Object> formMap = BeanUtil.beanToMap(entity);
        if (null == variables) {
            variables = formMap;
        } else {
            //合并，重复的key以variables的值为主
            Set<Map.Entry<String, Object>> entrySet = formMap.entrySet();
            Iterator<Map.Entry<String, Object>> it = entrySet.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                if (null != entry.getValue()) {
                    variables.putIfAbsent(entry.getKey(), entry.getValue());
                }
            }
        }
        // 启动流程时设置业务 key
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key, id + "", variables);

        // 更新业务表流程实例id字段
        setInstanceId.invoke(entity, instance.getId());

        // 记录流程实例业务关系
        InstanceBusiness ib = new InstanceBusiness();
        ib.setInstanceId(instance.getId());
        ib.setBusinessKey(id + "");
        ib.setModule(humpToLine(entity.getClass().getSimpleName()).substring(1));
        taskMapper.insertInstanceBusiness(ib);

        // TODO 开始流程后自动完成申请人填写的节点，这里只是针对性的对请假流程进行处理
        Task task = taskService.createTaskQuery().active().processInstanceId(instance.getId()).taskAssignee(username).singleResult();
        taskService.complete(task.getId());
    }

    /**
     * 驼峰转下划线
     */
    private String humpToLine(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Pattern linePattern = Pattern.compile("_(\\w)");
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 填充流程相关字段
     */
    @Override
    public <T> void richProcessField(T entity) throws Exception {
        Class clazz = entity.getClass();

        Method getInstanceId = clazz.getDeclaredMethod("getInstanceId");
        String instanceId = (String) getInstanceId.invoke(entity);
        Method getStatus = clazz.getDeclaredMethod("getStatus");
        String status = (String) getStatus.invoke(entity);

        Method setTaskId = clazz.getSuperclass().getDeclaredMethod("setTaskId", String.class);
        Method setTaskName = clazz.getSuperclass().getDeclaredMethod("setTaskName", String.class);
        Method setSuspendState = clazz.getSuperclass().getDeclaredMethod("setSuspendState", String.class);
        Method setSuspendStateName = clazz.getSuperclass().getDeclaredMethod("setSuspendStateName", String.class);
        Method setAssignee =  clazz.getSuperclass().getDeclaredMethod("setAssignee", String.class);
        Method setAssigneeName =  clazz.getSuperclass().getDeclaredMethod("setAssigneeName", String.class);

        // 当前环节
        if (StringUtils.isNotBlank(instanceId)) {
            List<Task> taskList = taskService.createTaskQuery()
                    .processInstanceId(instanceId)
                    .list();    // 例如请假会签，会同时拥有多个任务
            if (!CollectionUtils.isEmpty(taskList)) {
                TaskEntityImpl task = (TaskEntityImpl) taskList.get(0);
                setTaskId.invoke(entity, task.getId());
                if(!task.getAssignee().isEmpty()&&task.getAssignee() !=null){
                    setAssignee.invoke(entity,task.getAssignee());
                    setAssigneeName.invoke(entity,userMapper.selectUserByUserName(task.getAssignee()).getNickName());
                }else{
                    setAssigneeName.invoke(entity,"-");
                }
                if (task.getSuspensionState() == 2) {
                    setTaskName.invoke(entity, "已挂起");
                    setSuspendState.invoke(entity, "2");
                    setSuspendStateName.invoke(entity, "已挂起");
                } else {
                    setTaskName.invoke(entity, task.getName());
                    setSuspendState.invoke(entity, "1");
                    setSuspendStateName.invoke(entity, "已激活");
                }
            } else {
                // 已办结或者已撤销
                List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                        .processInstanceId(instanceId)
                        .orderByTaskCreateTime()
                        .desc()
                        .list();
                if (!CollectionUtils.isEmpty(list)) {
                    HistoricTaskInstance lastTask = list.get(0); // 该流程实例最后一个任务
                    if (StringUtils.isNotBlank(lastTask.getDeleteReason())) {
                        setTaskName.invoke(entity, "已撤销");
                    } else {
                        if ("2".equals(status)) {
                            setTaskName.invoke(entity, "未通过");
                        } else if ("3".equals(status)) {
                            setTaskName.invoke(entity, "审核中");
                        } else {
                            setTaskName.invoke(entity, "已结束");
                        }
                    }
                    setTaskId.invoke(entity, "-1"); // 已撤销或已结束，任务id不妨设置成-1
                    setAssigneeName.invoke(entity,"-");
                } else {
                    // 这种情况是流程表被删除，业务表的instanceId找不到对应记录
                    setTaskName.invoke(entity, "流程已删除");
                    setTaskId.invoke(entity, "-2"); // 流程已删除，前端不能查看审批历史和进度
                    setAssigneeName.invoke(entity,"-");
                }
            }
        } else {
            if ("1".equals(status)) {
                setTaskName.invoke(entity, "已结束");
                setAssigneeName.invoke(entity,"-");
            } else if ("2".equals(status)) {
                setTaskName.invoke(entity, "未通过");
                setAssigneeName.invoke(entity,"-");
            } else if ("3".equals(status)) {
                setTaskName.invoke(entity, "审核中");
            }else {
                setTaskName.invoke(entity, "未启动");
                setAssigneeName.invoke(entity,"-");
            }
        }
    }

    @Override
    public TableDataInfo findTodoTasks(TaskVo taskVo) {
        taskVo.setUserId(SecurityUtils.getUsername());
        taskVo.setOffset((taskVo.getPageNum() - 1) * taskVo.getPageSize());
        List<Map> tasks = taskMapper.findTodoList(taskVo);
        Integer count = taskMapper.findTodoCount(taskVo);

        List<TaskVo> taskVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> {
                TaskVo newTaskVo = new TaskVo();
                newTaskVo.setType("todo");
                newTaskVo.setUserId(SecurityUtils.getUsername());
                newTaskVo.setTaskId(task.get("ID_").toString());
                newTaskVo.setTaskName(task.get("NAME_").toString());
                newTaskVo.setInstanceId(task.get("PROC_INST_ID_").toString());
                newTaskVo.setSuspendState(task.get("SUSPENSION_STATE_").toString());
                newTaskVo.setCreateTime((Date) task.get("CREATE_TIME_"));
                newTaskVo.setTaskDefKey(task.get("TASK_DEF_KEY_").toString());

                if ("2".equals(newTaskVo.getSuspendState())) {
                    newTaskVo.setSuspendStateName("已挂起");
                } else {
                    newTaskVo.setSuspendStateName("已激活");
                }
                newTaskVo.setAssigneeName(userMapper.selectUserByUserName(newTaskVo.getUserId()).getNickName());

                // 查询业务表单数据，放入 map 中
                Map ibMap = taskMapper.selectInstanceBusinessByInstanceId(task.get("PROC_INST_ID_").toString());
                if (!CollectionUtils.isEmpty(ibMap)) {
                    Map<String, Object> formData = taskMapper.selectBusinessByBusinessKeyAndModule(ibMap.get("business_key").toString(), ibMap.get("module").toString());
                    if (!CollectionUtils.isEmpty(formData)) {
                        newTaskVo.setFormData(getLine2HumpMap(formData));
                    }
                }

                // 通过流程实例ID获取流程实例
                HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(newTaskVo.getInstanceId())
                        .singleResult();
                String instanceName = processInstance.getProcessDefinitionName();
                newTaskVo.setInstanceName(instanceName);

                String startUserId = processInstance.getStartUserId();
                SysUser applyUser = userMapper.selectUserByUserName(startUserId);
                newTaskVo.setApplyUser(applyUser);
                taskVos.add(newTaskVo);
            });
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(taskVos);
        rspData.setTotal(count);

        return rspData;
    }

    private Map<String, Object> getLine2HumpMap(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // key 格式转换，如 apply_user_id 转换成 applyUserId
            key = lineToHump(key).substring(0, 1).toLowerCase() + lineToHump(key).substring(1);
            newMap.put(key, value);
        }
        return newMap;
    }

    @Override
    public TableDataInfo findDoneTasks(TaskVo taskVo) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())  && !SecurityUtils.hasRole("organization_admin")) {
            taskVo.setUserId(SecurityUtils.getUsername());
        }
        taskVo.setOffset((taskVo.getPageNum() - 1) * taskVo.getPageSize());
        List<Map> tasks = taskMapper.findDoneList(taskVo);
        Integer count = taskMapper.findDoneCount(taskVo);

        List<TaskVo> taskVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> {
                TaskVo newTaskVo = new TaskVo();
                newTaskVo.setType("done");
                newTaskVo.setUserId(SecurityUtils.getUsername());
                newTaskVo.setTaskId(task.get("ID_").toString());
                newTaskVo.setTaskName(task.get("NAME_").toString());
                newTaskVo.setInstanceId(task.get("PROC_INST_ID_").toString());
                newTaskVo.setAssignee(task.get("ASSIGNEE_").toString());
                newTaskVo.setStartTime((Date) task.get("START_TIME_"));
                newTaskVo.setEndTime((Date) task.get("END_TIME_"));
                newTaskVo.setAssigneeName(userMapper.selectUserByUserName(newTaskVo.getAssignee()).getNickName());

                // 查询业务表单数据，放入 map 中
                Map ibMap = taskMapper.selectInstanceBusinessByInstanceId(task.get("PROC_INST_ID_").toString());
                if (!CollectionUtils.isEmpty(ibMap)) {
                    Map<String, Object> formData = taskMapper.selectBusinessByBusinessKeyAndModule(ibMap.get("business_key").toString(), ibMap.get("module").toString());
                    if (!CollectionUtils.isEmpty(formData)) {
                        newTaskVo.setFormData(getLine2HumpMap(formData));
                    }
                }
                // 通过流程实例ID获取流程实例
                HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(newTaskVo.getInstanceId())
                        .singleResult();
                String instanceName = processInstance.getProcessDefinitionName();
                newTaskVo.setInstanceName(instanceName);
                String startUserId = processInstance.getStartUserId();
                SysUser applyUser = userMapper.selectUserByUserName(startUserId);
                newTaskVo.setApplyUser(applyUser);
                taskVos.add(newTaskVo);
            });
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(taskVos);
        rspData.setTotal(count);

        return rspData;
    }

    @Override
    public void complete(String taskId, String instanceId, String variablesStr) {
        System.out.println("variables: " + variablesStr);
        Map<String, Object> variables = (Map<String, Object>) JSON.parse(variablesStr);
        Object passObj = variables.get("pass");
        Map<String, Object> formDates = (Map<String, Object>) variables.get("formData");
        Object commentObj = variables.get("comment");
        String comment = null == commentObj ? "" : commentObj.toString();
        String pass = null == passObj ? "" : passObj.toString();
        try {

            variables.put("pass", "true".equals(pass));
            // 被委派人处理完成任务
            // p.s. 被委托的流程需要先 resolved 这个任务再提交。
            // 所以在 complete 之前需要先 resolved

            // 判断该任务是否是委托任务（转办）
            TaskEntityImpl task = (TaskEntityImpl) taskService.createTaskQuery()
                    .taskId(taskId)
                    .singleResult();
            String definitionKey = task.getTaskDefinitionKey();
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()//创建流程实例查询
                    .processInstanceId(processInstanceId)//使用流程实例ID查询
                    .singleResult();
            String businessKey = processInstance.getBusinessKey();
            List<String> handleProcess = Arrays.asList("_dept_leader", "_committee_member", "_deputy_secretary", "_main_leader", "_destruction_leave",
                    "_organization_dept", "_inspection_team_head");
            if (handleProcess.contains(definitionKey) && StringUtils.isNotBlank(comment)) {
                BizLeave leave = new BizLeave();
                Long leaveId = Long.valueOf(businessKey);
                leave.setId(leaveId);
                switch (definitionKey) {
                    case "_dept_leader":
                        leave.setDepartmentOpinions(comment + "\n" + "$" + SecurityUtils.getUserNickname() + "  " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", DateUtils.getNowDate()));
                        break;
                    case "_committee_member":
                        leave.setStandingCommitteeOpinions(comment + "\n" + "$" + SecurityUtils.getUserNickname() + "  " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", DateUtils.getNowDate()));
                        break;
                    case "_deputy_secretary":
                        leave.setDeputySecretaryOpinions(comment + "\n" + "$" + SecurityUtils.getUserNickname() + "  " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", DateUtils.getNowDate()));
                        break;
                    case "_main_leader":
                        leave.setMainLeaderOpinions(comment + "\n" + "$" + SecurityUtils.getUserNickname() + "  " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", DateUtils.getNowDate()));
                        break;
                    case "_organization_dept":
                        leave.setOrganizationOpinions(comment + "\n" + "$" + SecurityUtils.getUserNickname() + "  " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", DateUtils.getNowDate()));
                        break;
                    case "_inspection_team_head":
                        leave.setInspectionTeamHeadOpinions(comment + "\n" + "$" + SecurityUtils.getUserNickname() + "  " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", DateUtils.getNowDate()));
                        break;
                    case "_destruction_leave":
                        leave.setDestructionDate(new Date());
                        break;
                }

                //组织部总申请人 level= 3,4,5,返回至申请人
                if (("_organization_dept".equals(definitionKey) && "true".equals(passObj) &&
                        ("level_3".equals(variables.get("createUserLevel")) ||
                                "level_4".equals(variables.get("createUserLevel")) ||
                                "level_5".equals(variables.get("createUserLevel"))))) {
                    String applyUserName = formDates.get("applyUserId").toString();
                    leave.setNextUserName(applyUserName);
                } else if (("true".equals(passObj) && ("_dept_leader".equals(definitionKey) || "_inspection_team_head".equals(definitionKey)
                        || "_apply_user_two".equals(definitionKey) || "_committee_member".equals(definitionKey))) ||
                        ("_deputy_secretary".equals(definitionKey) && "true".equals(passObj)
                                && (("level_1".equals(variables.get("createUserLevel")) || "level_3".equals(variables.get("createUserLevel")))))) {
                    //部门负责人、监察组组长、申请人送书记、常委、副书记，常委送副书记、书记，副书记送书记
                    String nextUserName = variables.get("nextUserName").toString();
                    leave.setNextUserName(nextUserName);
                }
                if ("false".equals(passObj)) {
                    //审核未通过
                    leave.setStatus("2");
                }
                bizLeaveMapper.updateBizLeave(leave);
            }

            // DELEGATION_ 为 PENDING 表示该任务是转办任务
            if (task.getDelegationState() != null && task.getDelegationState().equals(DelegationState.PENDING)) {
                taskService.resolveTask(taskId, variables);
                // 批注说明是转办
                String delegateUserName = userMapper.selectUserByUserName(SecurityUtils.getUsername()).getNickName();
                comment += "【由" + delegateUserName + "转办】";

                // 如果是 OWNER_ 为 null 的转办任务（候选组的待办），暂且用转办人来签收该任务
                if (StringUtils.isBlank(task.getOwner())) {
                    taskService.claim(taskId, SecurityUtils.getUsername());
                }
            } else {
                // 只有签收任务，act_hi_taskinst 表的 assignee 字段才不为 null
                taskService.claim(taskId, SecurityUtils.getUsername());
            }

            if (StringUtils.isNotEmpty(comment)) {
                identityService.setAuthenticatedUserId(SecurityUtils.getUsername());
                taskService.addComment(taskId, instanceId, comment);
            }

            // 动态设置办理人的节点，历史记录中办理人为null的话进行更新
            bizLeaveMapper.DynamicAssigneeRefreshActHis(taskId, SecurityUtils.getUsername());

            taskService.complete(taskId, variables);

        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskId, variables, e});
        }
    }


    @Override
    public List<HistoricActivity> selectHistoryList(HistoricActivity historicActivity) {
        // 说明：以下实现方案是手动封装 开始节点 和 结束节点 的数据，因此不考虑分页功能
//        PageDomain pageDomain = TableSupport.buildPageRequest();
//        Integer pageNum = pageDomain.getPageNum();
//        Integer pageSize = pageDomain.getPageSize();
        List<HistoricActivity> activityList = new ArrayList<>();
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        if (StringUtils.isNotBlank(historicActivity.getAssignee())) {
            query.taskAssignee(historicActivity.getAssignee());
        }
        if (StringUtils.isNotBlank(historicActivity.getActivityName())) {
            query.taskName(historicActivity.getActivityName());
        }
        List<HistoricTaskInstance> list = query.processInstanceId(historicActivity.getProcessInstanceId())
                .finished()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .list();

        activityList = list.stream().map(instance -> {
            HistoricActivity activity = new HistoricActivity();
            BeanUtils.copyProperties(instance, activity);
            String taskId = instance.getId();
            activity.setActivityName(instance.getName());
            List<Comment> comment = taskService.getTaskComments(taskId, "comment");
            if (!CollectionUtils.isEmpty(comment)) {
                activity.setComment(comment.get(0).getFullMessage());
            }
            // 如果是撤销（deleteReason 不为 null），写入审批意见栏
            if (StringUtils.isNotBlank(activity.getDeleteReason())) {
                activity.setComment(activity.getDeleteReason());
            }
            SysUser sysUser = userMapper.selectUserByUserName(instance.getAssignee());
            if (sysUser != null) {
                activity.setAssigneeName(sysUser.getNickName());
            }
            return activity;
        })
                .collect(Collectors.toList());


        HistoricActivityInstanceQuery query1 = historyService.createHistoricActivityInstanceQuery();
        List<HistoricActivityInstance> list1 = query1.processInstanceId(historicActivity.getProcessInstanceId())
                .finished()
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        HistoricActivityInstance startActivityInstance = list1.stream().filter(o -> "startEvent".equals(o.getActivityType())).findFirst().orElse(null);
        // 以下手动封装发起人节点的数据
        HistoricActivity startActivity = new HistoricActivity();
        BeanUtils.copyProperties(startActivityInstance, startActivity);
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(historicActivity.getProcessInstanceId())
                .singleResult();
        startActivity.setAssignee(historicProcessInstance.getStartUserId());
        SysUser sysUser = userMapper.selectUserByUserName(historicProcessInstance.getStartUserId());
        if (sysUser != null) {
            startActivity.setAssigneeName(sysUser.getNickName());
        }
        startActivity.setActivityName("开始");
        startActivity.setComment("流程启动");
        activityList.add(0, startActivity);


        HistoricActivityInstance endActivityInstance = null;
        for (int i = list1.size() - 1; i >= 0; i--) {
            HistoricActivityInstance instance = list1.get(i);
            if ("endEvent".equals(instance.getActivityType())) {
                endActivityInstance = instance;
                break;
            }
        }
        if (null != endActivityInstance) {
            int index = list1.indexOf(endActivityInstance);
            if (index > 0 && "_destruction_leave".equals(list1.get(index - 1).getActivityId())) {
                HistoricActivity endActivity = new HistoricActivity();
                BeanUtils.copyProperties(endActivityInstance, endActivity);
                endActivity.setActivityName("结束");
                endActivityInstance.getAssignee();
                endActivity.setAssigneeName("系统");
                endActivity.setComment("流程结束");
                activityList.add(endActivity);
            }
        }

        return activityList;
    }

    @Override
    public void delegate(String taskId, String fromUser, String delegateToUser) {
        taskService.delegateTask(taskId, delegateToUser);
    }

    @Override
    public void cancelApply(String instanceId, String deleteReason) {
        // 执行此方法后未审批的任务 act_ru_task 会被删除，流程历史 act_hi_taskinst 不会被删除，并且流程历史的状态为finished完成
        runtimeService.deleteProcessInstance(instanceId, deleteReason);
    }

    @Override
    public void suspendOrActiveApply(String instanceId, String suspendState) {
        if ("1".equals(suspendState)) {
            // 当流程实例被挂起时，无法通过下一个节点对应的任务id来继续这个流程实例。
            // 通过挂起某一特定的流程实例，可以终止当前的流程实例，而不影响到该流程定义的其他流程实例。
            // 激活之后可以继续该流程实例，不会对后续任务造成影响。
            // 直观变化：act_ru_task 的 SUSPENSION_STATE_ 为 2
            runtimeService.suspendProcessInstanceById(instanceId);
        } else if ("2".equals(suspendState)) {
            runtimeService.activateProcessInstanceById(instanceId);
        }
    }

    @Scheduled(cron = "0 20 7 ? * MON-FRI") // 周一到周五早上7点20
    public void findAllTodoTasks() {
        //查询所有流程节点是销假的流程
        String senderName;
        try{
            senderName = SecurityUtils.getUsername();
        } catch (Exception e){
            senderName = "admin";
        }
        TaskVo taskVo= new TaskVo();
        List<Map> tasks = taskMapper.findAllTodoList(taskVo);
        List<TaskVo> taskVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tasks)) {
            String finalSenderName = senderName;
            tasks.forEach(task -> {
                //销假开始时间计算三个工作日内
               String   createTime = task.get("CREATE_TIME_").toString();
               DateTime startTime = DateUtil.parse(createTime, "yyyy-MM-dd");
               String   username = task.get("ASSIGNEE_").toString();
                int  diffDays = calendarService.calcWorkDays(DateUtil.format(startTime,"yyyy-MM-dd"),DateUtil.format(new Date(),"yyyy-MM-dd"),null);
                if(diffDays >= 3) {
                    //超过三个工作日系统发送销假提醒
                    SysUser sysUser = userMapper.selectUserByUserName(username);
                    if(sysUser !=null){
                        String nickName = userMapper.selectUserByUserName(username).getNickName();
                        Long userId = userMapper.selectUserByUserName(username).getUserId();
                        String title = String.format("销假提醒");
                        String message = String.format("尊敬的%s，您好，您发起的请销假审批流程已通过审批，请及时销假！", nickName);
                        messageService.sendMessage2User(title, message, finalSenderName, userId + "");
                    }
                }
            });
        }
    }

}
