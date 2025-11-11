package com.cb.task.service.impl;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.message.service.MessageService;
import com.cb.system.service.ISysUserService;
import com.cb.task.domain.BizTaskComment;
import com.cb.task.domain.BizTaskHandle;
import com.cb.task.domain.BizTaskInfo;
import com.cb.task.mapper.BizTaskHandleMapper;
import com.cb.task.service.IBizTaskCommentService;
import com.cb.task.service.IBizTaskHandleService;
import com.cb.task.service.IBizTaskInfoService;
import com.cb.task.vo.TaskHandleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务分配的执行人/部门Service业务层处理
 * 
 * @author yangxin
 * @date 2023-10-30
 */
@Service
public class BizTaskHandleServiceImpl implements IBizTaskHandleService 
{
    @Autowired
    private BizTaskHandleMapper bizTaskHandleMapper;

    @Autowired
    private IBizTaskInfoService taskInfoService;

    @Autowired
    private IBizTaskCommentService commentService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private MessageService messageService;

    /**
     * 查询任务分配的执行人/部门
     * 
     * @param id 任务分配的执行人/部门ID
     * @return 任务分配的执行人/部门
     */
    @Override
    public BizTaskHandle selectBizTaskHandleById(String id)
    {
        BizTaskHandle handle = bizTaskHandleMapper.selectBizTaskHandleById(id);
        addTaskInfo(handle);
        return handle;
    }

    private void addTaskInfo(BizTaskHandle bizTaskHandle){
        String taskId = bizTaskHandle.getTaskId();
        BizTaskInfo taskInfo = taskInfoService.selectBizTaskInfoById(taskId);
        bizTaskHandle.setTaskInfo(taskInfo);
    }

    /**
     * 查询任务分配的执行人/部门列表
     * 
     * @param bizTaskHandle 任务分配的执行人/部门
     * @return 任务分配的执行人/部门
     */
    @Override
    public List<BizTaskHandle> selectBizTaskHandleList(BizTaskHandle bizTaskHandle)
    {
        return bizTaskHandleMapper.selectBizTaskHandleList(bizTaskHandle);
    }

    /**
     * 新增任务分配的执行人/部门
     * 
     * @param bizTaskHandle 任务分配的执行人/部门
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int insertBizTaskHandle(BizTaskHandle bizTaskHandle)
    {
        if(StringUtils.isBlank(bizTaskHandle.getId())){
            String id = IdUtils.randomUUID();
            bizTaskHandle.setId(id);
        }
        if("1".equals(bizTaskHandle.getHandleType())){
            bizTaskHandle.setAssignStatus("1");//分配给人时默认已指派
        } else {
            bizTaskHandle.setAssignStatus("0");//分配给部门时默认未指派
        }
        try{
            if(StringUtils.isBlank(bizTaskHandle.getCreateBy())){
                bizTaskHandle.setCreateBy(SecurityUtils.getUsername());
            }
        } catch (Exception e){}
        //新增处理记录时执行时间没有则默认当前时间
        if(StringUtils.isBlank(bizTaskHandle.getReceiveTime())){
            bizTaskHandle.setReceiveTime(DateUtils.getTime());
        }
        bizTaskHandle.setCreateTime(DateUtils.getNowDate());
        return bizTaskHandleMapper.insertBizTaskHandle(bizTaskHandle);
    }

    /**
     * 修改任务分配的执行人/部门
     * 
     * @param bizTaskHandle 任务分配的执行人/部门
     * @return 结果
     */
    @Override
    public int updateBizTaskHandle(BizTaskHandle bizTaskHandle)
    {
        try{
            bizTaskHandle.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizTaskHandle.setUpdateTime(DateUtils.getNowDate());
        return bizTaskHandleMapper.updateBizTaskHandle(bizTaskHandle);
    }

    /**
     * 批量删除任务分配的执行人/部门
     * 
     * @param ids 需要删除的任务分配的执行人/部门ID
     * @return 结果
     */
    @Override
    public int deleteBizTaskHandleByIds(String[] ids)
    {
        return bizTaskHandleMapper.deleteBizTaskHandleByIds(ids);
    }

    /**
     * 删除任务分配的执行人/部门信息
     * 
     * @param id 任务分配的执行人/部门ID
     * @return 结果
     */
    @Override
    public int deleteBizTaskHandleById(String id)
    {
        return bizTaskHandleMapper.deleteBizTaskHandleById(id);
    }

    @Override
    public int deleteBizTaskHandleByTaskId(String taskId) {
        return bizTaskHandleMapper.deleteBizTaskHandleByTaskId(taskId);
    }

    @Override
    public List<BizTaskHandle> listMyUnClaimList(BizTaskHandle bizTaskHandle, String userName) {
        return bizTaskHandleMapper.listMyUnClaimList(bizTaskHandle, userName);
    }

    @Override
    public List<BizTaskHandle> listMyReadList(BizTaskHandle bizTaskHandle, String userName) {
        return bizTaskHandleMapper.listMyReadList(bizTaskHandle, userName);
    }

    @Override
    public List<BizTaskHandle> listMyTodoList(BizTaskHandle bizTaskHandle,String userName,Long leaderDeptId) {
        return bizTaskHandleMapper.listMyTodoList(bizTaskHandle,userName,leaderDeptId);
    }

    @Override
    public List<BizTaskHandle> listMyCompleteList(BizTaskHandle bizTaskHandle, String userName, Long leaderDeptId) {
        return bizTaskHandleMapper.listMyCompleteList(bizTaskHandle,userName,leaderDeptId);
    }

    @Override
    public List<BizTaskHandle> listDeptOrUserList4Statistics(BizTaskHandle bizTaskHandle,Long userId, String userName, Long deptId) {
        return bizTaskHandleMapper.listDeptOrUserList4Statistics(bizTaskHandle, userId, userName, deptId);
    }

    @Override
    public List<BizTaskHandle> listMyExpireList(BizTaskHandle bizTaskHandle, String userName) {
        String currDateTime = DateUtils.getTime();
        return bizTaskHandleMapper.listMyExpireList(bizTaskHandle,userName,currDateTime);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void assign(TaskHandleVo.AssignReq req) {
        BizTaskHandle bizTaskHandle = bizTaskHandleMapper.selectBizTaskHandleById(req.getHandleId());
        if(null == bizTaskHandle){
            throw new CustomException("指派失败！未查询到该任务信息");
        }
        String username = SecurityUtils.getUsername();
        SysDept onlineDept = SecurityUtils.getOnlineDept();
        Long deptId = onlineDept.getDeptId();
        String leader = onlineDept.getLeader();
        //非本部门处理的任务或当前用户不是部门负责人 无权进行指派
        if(!bizTaskHandle.getHandleDept().equals(deptId) || !username.equals(leader)){
            throw new CustomException("指派失败！对该任务无指派权限");
        }
        if(!bizTaskHandle.getHandleType().equals("2")){
            throw new CustomException("指派失败！任务处理类型不是部门处理，不允许指派");
        }
        if(!bizTaskHandle.getAssignStatus().equals("0")){
            throw new CustomException("指派失败！任务已被指派，不能重复指派");
        }
        bizTaskHandle.setAssignStatus("1");
        bizTaskHandle.setHandleUser(req.getUserName());
        bizTaskHandleMapper.updateBizTaskHandle(bizTaskHandle);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void confirm(String handleId) {
        BizTaskHandle bizTaskHandle = bizTaskHandleMapper.selectBizTaskHandleById(handleId);
        if(null == bizTaskHandle){
            throw new CustomException("任务确认失败！未查询到该任务信息");
        }
        if(!"0".equals(bizTaskHandle.getHandleStatus()) || !"1".equals(bizTaskHandle.getAssignStatus())){
            throw new CustomException("任务确认失败！该任务目前不需确认操作");
        }
        String username = SecurityUtils.getUsername();
        if(!username.equals(bizTaskHandle.getHandleUser())){
            throw new CustomException("任务确认失败！您不是该任务的执行人，不能进行确认");
        }
        //若任务待确定则修改为执行中
        BizTaskInfo taskInfo = taskInfoService.selectBizTaskInfoById(bizTaskHandle.getTaskId());
        if("0".equals(taskInfo.getStatus())){
            taskInfo.setStatus("1");
            taskInfoService.updateBizTaskInfo(taskInfo);
        }

        bizTaskHandle.setHandleStatus("1");
        bizTaskHandle.setReceiveTime(DateUtils.getTime());
        bizTaskHandleMapper.updateBizTaskHandle(bizTaskHandle);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void transfer(TaskHandleVo.TransferReq req) {
        BizTaskHandle parent = bizTaskHandleMapper.selectBizTaskHandleById(req.getHandleId());
        if(!"1".equals(parent.getHandleStatus())){
            throw new CustomException("该处理记录已完成或已作废，不能进行转办！");
        }
        String username = SecurityUtils.getUsername();
        if(!parent.getHandleUser().equals(username)){
            throw new CustomException("您无权转办该处理记录！");
        }
        List<TaskHandleVo.HandleTransferItem> handleList = req.getHandleList();
        Long transferAllPercent = handleList.stream().map(i -> i.getScorePercent()).reduce(0L, (a, b) -> a + b);
        Long oldPercent = parent.getScorePercent();
        if(transferAllPercent > oldPercent){
            throw new CustomException("转办的工作量占比合计超出了原记录工作量占比，请调整！");
        }
        if(parent.getMasterFlag()){
            if(transferAllPercent >= oldPercent){
                throw new CustomException("您是主要负责人，不能将全部工作量占比转出给他人！");
            }
        }

        parent.setHandleStatus("2");
        parent.setScorePercent(oldPercent - transferAllPercent);
        bizTaskHandleMapper.updateBizTaskHandle(parent);

        List<BizTaskHandle> addList = ofHandleList(req.getHandleList(), req.getHandleId(), parent.getHandleUser(), req.getTransferRemark());
        addList.forEach(item->{
            item.setTaskId(parent.getTaskId());
            item.setClaimStatus("1");
            item.setClaimType("1");
            insertBizTaskHandle(item);
        });

        //添加转办操作日志
        BizTaskComment comment = new BizTaskComment();
        comment.setId(IdUtils.randomUUID());
        comment.setTaskId(parent.getTaskId());
        List<String> userNameList = addList.stream().map(BizTaskHandle::getHandleUser).collect(Collectors.toList());
        String userNamesStr = sysUserService.getNameStrByLoginNameList(userNameList);

        String str = String.format("%s 转办给了 %s", parent.getHandleUserName(), userNamesStr);
        comment.setComment(str);
        commentService.insertBizTaskComment(comment);

        //发送消息通知
        String title = String.format("%s 将任务【%s】转办给了你,请注意查看", parent.getHandleUserName(), parent.getTaskName());
        sendMessage(title,title,parent.getHandleUser(),addList);
    }

    public List<BizTaskHandle> ofHandleList(List<TaskHandleVo.HandleTransferItem> list,String parentId,String transferUser,String transferRemark){
        List<BizTaskHandle> handleList = list.stream().map(item -> {
            BizTaskHandle handle = new BizTaskHandle();
            handle.setHandleType(item.getHandleType());
            handle.setHandleDept(item.getHandleDept());
            handle.setHandleUser(item.getHandleUser());
            handle.setScorePercent(item.getScorePercent());
            handle.setProgress(item.getProgress());
            //个人处理时不需要指派了
            if ("1".equals(handle.getHandleType())) {
                handle.setAssignStatus("1");
            } else {
                handle.setAssignStatus("0");
            }
            handle.setReceiveTime(item.getReceiveTime());
            handle.setCompleteTime(item.getCompleteTime());
            handle.setHandleRole(item.getHandleRole());
            handle.setRemark(item.getRemark());
            //根据处理进度设置处理状态
            Integer progress = handle.getProgress();
            if(progress >= 100){
                handle.setHandleStatus("2");
            } else {
                handle.setHandleStatus("1");
            }

            handle.setParentId(parentId);
            handle.setTransferUser(transferUser);
            handle.setTransferRemark(transferRemark);

            return handle;
        }).collect(Collectors.toList());

        return handleList;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void progess(TaskHandleVo.ProgressReq req) {
        BizTaskHandle bizTaskHandle = bizTaskHandleMapper.selectBizTaskHandleById(req.getHandleId());
        if(null == bizTaskHandle){
            throw new CustomException("更新进度失败！未查询到该任务信息");
        }
        if("0".equals(bizTaskHandle.getHandleStatus())){
            throw new CustomException("更新进度失败！该任务尚未被确认");
        }
        String username = SecurityUtils.getUsername();
        if(!username.equals(bizTaskHandle.getHandleUser())){
            throw new CustomException("更新进度失败！您不是该任务的执行人，不能进行更新");
        }

        bizTaskHandle.setProgress(req.getProgress());
        bizTaskHandle.setCompleteSituation(req.getCompleteSituation());
        bizTaskHandle.setResultAttach(req.getResultAttach());
        bizTaskHandleMapper.updateBizTaskHandle(bizTaskHandle);

        //添加转办操作日志
        BizTaskComment comment = new BizTaskComment();
        comment.setId(IdUtils.randomUUID());
        comment.setTaskId(bizTaskHandle.getTaskId());
        String str = String.format("%s 更新处理进度。处理情况：%s", bizTaskHandle.getHandleUserName(), req.getCompleteSituation());
        comment.setComment(str);
        commentService.insertBizTaskComment(comment);

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void complete(TaskHandleVo.CompleteReq req) {
        BizTaskHandle bizTaskHandle = bizTaskHandleMapper.selectBizTaskHandleById(req.getHandleId());
        if(null == bizTaskHandle){
            throw new CustomException("处理完成失败！未查询到该任务信息");
        }
        String username = SecurityUtils.getUsername();
        if(!username.equals(bizTaskHandle.getHandleUser())){
            throw new CustomException("处理完成失败！您不是该任务的执行人，不能处理");
        }

        bizTaskHandle.setProgress(100);
        bizTaskHandle.setCompleteSituation(req.getCompleteSituation());
        bizTaskHandle.setResultAttach(req.getResultAttach());
        bizTaskHandle.setCompleteTime(DateUtils.getTime());
        bizTaskHandle.setHandleStatus("2");
        bizTaskHandleMapper.updateBizTaskHandle(bizTaskHandle);

        BizTaskInfo taskInfo = taskInfoService.selectBizTaskInfoById(bizTaskHandle.getTaskId());

        if(null != taskInfo && !"2".equals(taskInfo.getStatus())){
            //更新taskInfo的状态
            BizTaskHandle query = new BizTaskHandle();
            query.setTaskId(bizTaskHandle.getTaskId());
            List<BizTaskHandle> handleList = bizTaskHandleMapper.selectBizTaskHandleList(query);
            boolean allCompleteFlag = handleList.stream().allMatch(item -> "2".equals(item.getHandleStatus()));
            if(allCompleteFlag){
                taskInfo.setProgress(100);
                taskInfo.setStatus("2");
                taskInfoService.updateBizTaskInfo(taskInfo);
            }
        }

        //添加转办操作日志
        BizTaskComment comment = new BizTaskComment();
        comment.setId(IdUtils.randomUUID());
        comment.setTaskId(bizTaskHandle.getTaskId());
        String str = String.format("%s 处理完成。完成情况：%s", bizTaskHandle.getHandleUserName(), req.getCompleteSituation());
        comment.setComment(str);
        commentService.insertBizTaskComment(comment);

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void claim(String id) {
        BizTaskHandle handle = bizTaskHandleMapper.selectBizTaskHandleById(id);
        handle.setClaimStatus("1");
        handle.setClaimType("1");
        updateBizTaskHandle(handle);

        //重新分配分数
        reScorePercent(handle.getTaskId());
    }

    /**
     * 重新分配分数
     * @param taskId
     */
    private void reScorePercent(String taskId){
        BizTaskHandle query = new BizTaskHandle();
        query.setTaskId(taskId);
        query.setClaimStatus("1");
        query.setClaimType("1");
        //已认领为办件的记录
        List<BizTaskHandle> handleList = selectBizTaskHandleList(query);
        Map<String, BizTaskHandle> idMap = handleList.stream().collect(Collectors.toMap(BizTaskHandle::getId, item -> item, (a, b) -> b));
        //已转办给他人的记录id
        Set<String> transferIds = handleList.stream().filter(item -> StringUtils.isNotBlank(item.getParentId())).map(BizTaskHandle::getParentId).collect(Collectors.toSet());
        //需要重新算分的记录
        List<BizTaskHandle> reScoreList = handleList.stream().filter(item -> !transferIds.contains(item.getId())).collect(Collectors.toList());

        long total= 100L;
        // 先排除已转办的记录上保留的占比
        for (String transferId : transferIds) {
            BizTaskHandle handle = idMap.get(transferId);
            if (null != handle) {
                total = total - handle.getScorePercent();
            }
        }
        //剩下的由所有办理人来分
        int len = handleList.size() - transferIds.size();
        long average = total / len;

        for (int i = 0; i < reScoreList.size(); i++) {
            BizTaskHandle handle = reScoreList.get(i);
            if (i == reScoreList.size() - 1) {
                handle.setScorePercent(total);
            } else {
                handle.setScorePercent(average);
                total = total - average;
            }
            updateBizTaskHandle(handle);
        }

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void read(String id) {
        BizTaskHandle handle = bizTaskHandleMapper.selectBizTaskHandleById(id);
        handle.setClaimStatus("1");
        handle.setClaimType("2");
        updateBizTaskHandle(handle);
    }

    @Override
    public void urge(String id,String msg) {
        BizTaskHandle handle = bizTaskHandleMapper.selectBizTaskHandleById(id);
        if(null == handle || StringUtils.isBlank(handle.getTaskId())) {
            throw new CustomException("未获取到任务处理信息");
        }
        String taskId = handle.getTaskId();
        BizTaskInfo taskInfo = taskInfoService.selectBizTaskInfoById(taskId);
        if(null == taskInfo){
            throw new CustomException("未获取到任务信息");
        }
        String taskName = taskInfo.getTitle();
        String title = String.format("您收到了关于任务【%s】的催办，请及时处理。",taskName);
        String sender = SecurityUtils.getUsername();
        String userIds = sysUserService.getUserIdStrByLoginNameList(Collections.singletonList(handle.getHandleUser()));

        //待认领
        if(!"1".equals(handle.getClaimStatus())){
            String message;
            if(StringUtils.isNotBlank(msg)){
                message = String.format("您有一个工作任务【%s】，尚处于待认领状态，请及时处理。催办备注：%s",taskName,msg);
            } else {
                message = String.format("您有一个工作任务【%s】，尚处于待认领状态，请及时处理。",taskName);
            }
            messageService.sendMessage2User(title,message,sender,userIds);
        } else if("1".equals(handle.getClaimType()) && "1".equals(handle.getHandleStatus())){
            //办件，且是执行中
            String message;
            if(StringUtils.isNotBlank(msg)){
                message = String.format("您有一个工作任务【%s】，尚未完成，请及时处理。催办备注：%s",taskName,msg);
            } else {
                message = String.format("您有一个工作任务【%s】，尚未完成，请及时处理。",taskName);
            }
            messageService.sendMessage2User(title,message,sender,userIds);
        }

        //添加催办操作日志
        BizTaskComment comment = new BizTaskComment();
        comment.setId(IdUtils.randomUUID());
        comment.setTaskId(taskId);
        String operatorName = SecurityUtils.getLoginUser().getUser().getNickName();
        String handleUserName = handle.getHandleUserName();
        String str;
        if(StringUtils.isNotBlank(msg)){
            str = String.format("%s 对 %s 进行了催办操作。催办备注：%s", operatorName, handleUserName,msg);
        } else {
            str = String.format("%s 对 %s 进行了催办操作。", operatorName, handleUserName);
        }
        comment.setComment(str);
        commentService.insertBizTaskComment(comment);

    }

    @Override
    public List<BizTaskHandle> list4ExpireNotice(String now, String endTime) {
        return bizTaskHandleMapper.list4ExpireNotice(now,endTime);
    }

    /**
     * 查询用于年度/季度考核时一键引用的任务经办信息
     * @return
     */
    @Override
    public List<TaskHandleVo.List4AssessResp> list4Assess(TaskHandleVo.List4AssessReq req) {
        return bizTaskHandleMapper.list4Assess(req);
    }

    private void sendMessage(String title, String message, String sender, List<BizTaskHandle> handleList){
        List<String> userNameList = handleList.stream().filter(item->{
            return null != item && StringUtils.isNotBlank(item.getHandleUser());
        }).filter(item -> "1".equals(item.getHandleStatus()))
                .map(BizTaskHandle::getHandleUser).collect(Collectors.toList());
        String userIds = sysUserService.getUserIdStrByLoginNameList(userNameList);
        if(StringUtils.isNotBlank(userIds)){
            messageService.sendMessage2User(title,message,sender,userIds);
        }

    }
}
