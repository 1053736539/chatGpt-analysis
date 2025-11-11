package com.cb.task.scheduler;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.StringUtils;
import com.cb.message.service.MessageService;
import com.cb.system.service.ISysUserService;
import com.cb.task.domain.BizTaskHandle;
import com.cb.task.service.IBizTaskHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 任务定时器处理
 * @Author ARPHS
 * @Date 2023/11/24 15:15
 */
@Component
public class TaskScheduler {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IBizTaskHandleService handleService;

    @Autowired
    private MessageService messageService;




    /**
     * 即将过期任务 提醒
     * @param day 提前天数
     */
    public void noticeExpireBeforeByDay(Integer day){
        if(null == day || day <= 0){
            day = 5;
        }
        String now = DateUtil.now();
        String endTime = DateUtil.formatDateTime(DateUtil.offsetDay(new Date(), day));
        List<BizTaskHandle> handleList = handleService.list4ExpireNotice(now, endTime);

        Map<String, List<BizTaskHandle>> taskIdMap = handleList.stream().collect(Collectors.groupingBy(BizTaskHandle::getTaskId));

        Iterator<Map.Entry<String, List<BizTaskHandle>>> it = taskIdMap.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<String, List<BizTaskHandle>> item = it.next();
            List<BizTaskHandle> list = item.getValue();
            if(!list.isEmpty()){
                String taskName = list.get(0).getTaskName();
                DateTime currTime = DateUtil.date();
                DateTime taskEndTime = DateUtil.parseDateTime(list.get(0).getTaskEndTime());
                long surplus = DateUtil.betweenDay(currTime, taskEndTime, true);
                String title = String.format("您经办的【%s】任务尚未完成，离任务结束时间还有%d天，请及时处理。", taskName, surplus);
                if(currTime.after(taskEndTime)){
                    title = String.format("您经办的【%s】任务尚未完成，任务已超时%d天，请及时处理。", taskName, surplus);
                }
                String message = String.format("您经办的【%s】任务尚未完成，任务结束时间为%s，请及时处理。", taskName, taskEndTime);
                sendMessage(title,message,null,list);
            }
        }

    }

    private void sendMessage(String title, String message,String sender,List<BizTaskHandle> handleList){
        try{
            List<String> userNameList = handleList.stream().filter(item->{
                return null != item && StringUtils.isNotBlank(item.getHandleUser());
            }).filter(item -> "1".equals(item.getHandleStatus()))
                    .map(BizTaskHandle::getHandleUser).collect(Collectors.toList());
            String userIds = userService.getUserIdStrByLoginNameList(userNameList);
            if(StringUtils.isNotBlank(userIds)){
                messageService.sendMessage2User(title,message,sender,userIds);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

/*    public void findAllTodoTasks() {
        //查询所有流程节点是销假的流程
        Taskvo taskVo= new TaskVo();
        List<Map> tasks = taskMapper.findAllTodoList(taskVo);
        List<TaskVo> taskVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> {
                //销假开始时间计算三个工作日内
                String   startTime = task.get("CREATE_TIME_").toString();
                String   username = task.get("ASSIGNEE_").toString();
                int  diffDays = calendarService.calcWorkDays(DateUtil.format(LocalDateTime.parse(startTime),"yyyy-MM-dd"),DateUtil.format(new Date(),"yyyy-MM-dd"),null);
                if(diffDays >= 3) {
                    //超过三个工作日系统发送销假提醒
                    SysUser sysUser = userService.selectUserByUserName(username);
                    if(sysUser !=null){
                        String nickName = userService.selectUserByUserName(username).getNickName();
                        Long userId = userService.selectUserByUserName(username).getUserId();
                        String title = String.format("销假提醒");
                        String message = String.format("您好，%s，您发起的请假审批流程已通过审批，请及时销假。", nickName);
                        messageService.sendMessage2User(title, message, "系统", userId + "");
                    }
                }
            });
        }
    }*/

}
