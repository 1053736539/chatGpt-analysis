package com.cb.task.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.message.service.MessageService;
import com.cb.oa.service.ISysUserOutService;
import com.cb.system.service.ISysUserService;
import com.cb.task.domain.BizTaskComment;
import com.cb.task.domain.BizTaskDocType;
import com.cb.task.domain.BizTaskHandle;
import com.cb.task.domain.BizTaskInfo;
import com.cb.task.mapper.BizTaskInfoMapper;
import com.cb.task.service.IBizTaskCommentService;
import com.cb.task.service.IBizTaskDocTypeService;
import com.cb.task.service.IBizTaskHandleService;
import com.cb.task.service.IBizTaskInfoService;
import com.cb.task.vo.TaskInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 任务信息Service业务层处理
 * 
 * @author yangxin
 * @date 2023-10-30
 */
@Service
public class BizTaskInfoServiceImpl implements IBizTaskInfoService 
{
    private static final Logger log = LoggerFactory.getLogger(BizTaskInfoServiceImpl.class);

    @Autowired
    private BizTaskInfoMapper bizTaskInfoMapper;

    @Autowired
    private IBizTaskHandleService handleService;

    @Autowired
    private IBizTaskDocTypeService docTypeService;

    @Autowired
    private IBizTaskCommentService commentService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ISysUserOutService oaUserService;

    /**
     * 查询任务信息
     * 
     * @param id 任务信息ID
     * @return 任务信息
     */
    @Override
    public BizTaskInfo selectBizTaskInfoById(String id)
    {
        BizTaskInfo bizTaskInfo = bizTaskInfoMapper.selectBizTaskInfoById(id);
        //添加经办人、协办人、知会人列表
        addUserListInfo(bizTaskInfo);

        return bizTaskInfo;
    }

    private void addUserListInfo(BizTaskInfo bizTaskInfo){
        String id = bizTaskInfo.getId();
        CompletableFuture[] futureArr = new CompletableFuture[2];

        CompletableFuture handleFuture = CompletableFuture.runAsync(()->{
            //附加经办人列表
            BizTaskHandle query = new BizTaskHandle();
            query.setTaskId(id);
            //TODO 不作过滤，所有处理人都查询
//            query.setClaimStatus("1");
//            query.setClaimType("1");
            List<BizTaskHandle> list = handleService.selectBizTaskHandleList(query);
            bizTaskInfo.setHandleList(list);
        });
        futureArr[0] = handleFuture;

        CompletableFuture commentFuture = CompletableFuture.runAsync(()->{
            //附加评论列表
            BizTaskComment query = new BizTaskComment();
            query.setTaskId(id);
            List<BizTaskComment> list = commentService.selectBizTaskCommentList(query);
            bizTaskInfo.setCommentList(list);
        });
        futureArr[1] = commentFuture;

        CompletableFuture.allOf(futureArr).join();
    }

    /**
     * 查询任务信息列表
     * 
     * @param bizTaskInfo 任务信息
     * @return 任务信息
     */
    @Override
    public List<BizTaskInfo> selectBizTaskInfoList(BizTaskInfo bizTaskInfo)
    {
        return bizTaskInfoMapper.selectBizTaskInfoList(bizTaskInfo);
    }

    /**
     * 查询部门的任务（待办/超时/已完结）
     * @param bizTaskInfo 任务信息
     * @param expireQuery 是否查询已超时任务
     * @return
     */
    @Override
    public List<BizTaskInfo> listByDept(BizTaskInfo bizTaskInfo, boolean expireQuery) {
        if(expireQuery){
            bizTaskInfo.getParams().put("expireQuery", true);
            bizTaskInfo.getParams().put("now", DateUtil.formatDateTime(DateUtils.getNowDate()));
        }
        return bizTaskInfoMapper.listByDept(bizTaskInfo);
    }

    @Override
    public List<BizTaskInfo> listMyAudit(BizTaskInfo bizTaskInfo, Long leaderDeptId) {
        return bizTaskInfoMapper.listMyAudit(bizTaskInfo,leaderDeptId);
    }

    /**
     * 新增任务信息
     * 
     * @param bizTaskInfo 任务信息
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int insertBizTaskInfo(BizTaskInfo bizTaskInfo)
    {
        if(StringUtils.isBlank(bizTaskInfo.getId())){
            String taskId = IdUtils.randomUUID();
            bizTaskInfo.setId(taskId);
            if(StringUtils.isBlank(bizTaskInfo.getTopId())){
                //默认设置当前任务为顶级任务
                bizTaskInfo.setTopId(taskId);
                //有父级id时，取父级的topId
                if(StringUtils.isNotBlank(bizTaskInfo.getParentId())){
                    BizTaskInfo parent = bizTaskInfoMapper.selectBizTaskInfoById(bizTaskInfo.getParentId());
                    if(null != parent){
                        bizTaskInfo.setTopId(parent.getTopId());
                    }
                }
            }
        }
        try{
            if(StringUtils.isBlank(bizTaskInfo.getCreateBy())){
                bizTaskInfo.setCreateBy(SecurityUtils.getUsername());
            }
            if(null == bizTaskInfo.getCreateDept()){
                bizTaskInfo.setCreateDept(SecurityUtils.getOnlineDept().getDeptId());
            }

        } catch (Exception e){}
        bizTaskInfo.setCreateTime(DateUtils.getNowDate());
        return bizTaskInfoMapper.insertBizTaskInfo(bizTaskInfo);
    }

    /**
     * 修改任务信息
     * 
     * @param bizTaskInfo 任务信息
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int updateBizTaskInfo(BizTaskInfo bizTaskInfo)
    {
        // 计算任务总分
        //系统自建-取分类分数    oa创建-取紧急程度*难度
        BizTaskDocType docType = docTypeService.selectBizTaskDocTypeByCode(bizTaskInfo.getDocType());
        if("3".equals(bizTaskInfo.getCausation())){
            long docTypeScore = docType.getScore();
            bizTaskInfo.setPlanScore(docTypeScore);
        } else {
            long urgency = Long.parseLong(bizTaskInfo.getUrgency());
            long diffcult = Long.parseLong(bizTaskInfo.getDifficult());
            bizTaskInfo.setPlanScore(urgency * diffcult);
        }
        //更新为已完成时设置进度为100
        if("2".equals(bizTaskInfo.getStatus())){
            bizTaskInfo.setProgress(100);
        }
        try{
            bizTaskInfo.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizTaskInfo.setUpdateTime(DateUtils.getNowDate());
        int i = bizTaskInfoMapper.updateBizTaskInfo(bizTaskInfo);

        //完成任务经办记录
        completeTask(bizTaskInfo);

        return i;
    }

    //完成任务相关经办记录
    private void completeTask(BizTaskInfo bizTaskInfo){
        if(StringUtils.isNotBlank(bizTaskInfo.getId())){
            if("2".equals(bizTaskInfo.getStatus()) || bizTaskInfo.getProgress().equals(100)){
                //未完成的处理记录此时也要设置为已完成
                BizTaskHandle query = new BizTaskHandle();
                query.setTaskId(bizTaskInfo.getId());
                query.setAssignStatus("1");
                query.setHandleStatus("1");
                query.setClaimStatus("1");
                query.setClaimType("1");
                List<BizTaskHandle> executingList = handleService.selectBizTaskHandleList(query);
                executingList.forEach(item->{
                    item.setCompleteTime(DateUtils.getTime());
                    item.setHandleStatus("2");
                    handleService.updateBizTaskHandle(item);
                });
            }
        }
    }

    /**
     * 批量删除任务信息
     * 
     * @param ids 需要删除的任务信息ID
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteBizTaskInfoByIds(String[] ids)
    {

        Arrays.stream(ids).forEach(id->{
            deleteBizTaskInfoById(id);
        });

        return ids.length;
    }

    /**
     * 删除任务信息信息
     * 
     * @param id 任务信息ID
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteBizTaskInfoById(String id)
    {
        handleService.deleteBizTaskHandleByTaskId(id);
        commentService.deleteBizTaskCommentByTaskId(id);
        return bizTaskInfoMapper.deleteBizTaskInfoById(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void fromOA(TaskInfoVo.FormOAReq req) {
        log.info("OA交办数据\n{}",JSON.toJSONString(req));

        String currHandleUserId = req.getCurrHandleUserId();
//        SysUser sysUser = oaUserService.selectSysUserByOaUserId(currHandleUserId);
        SysUser sysUser = oaUserService.selectSysUserByOaUserName(currHandleUserId);
        if(null == sysUser){
            //TODO mock数据 待移除
//            sysUser = mockSysUser();
            throw new CustomException("当前OA用户尚未设置用户关联信息，请联系统计考核评价管理系统管理员设置后再试");
        }
        String userName = sysUser.getUserName();
        Long deptId = sysUser.getDeptId();

        //TODO 重复先删除已有的（bussinessId和createBy作为唯一约束）
        String msgBizId = req.getBusinessId() + userName;
        BizTaskInfo query = new BizTaskInfo();
        query.setBusinessId(req.getBusinessId());
        query.setCreateBy(userName);
        List<String> existIdList = bizTaskInfoMapper.selectBizTaskInfoList(query).stream().map(BizTaskInfo::getId).collect(Collectors.toList());
        //存在就删除
        deleteBizTaskInfoByIds(existIdList.toArray(new String[existIdList.size()]));
        //删除消息记录
        messageService.deleteMsgByBizId(msgBizId);


        //处长不分派的时候,经办人为空
        boolean emptyFlag = null == req.getAssignUserIdList() || req.getAssignUserIdList().isEmpty();

        String taskId = IdUtils.randomUUID();
        BizTaskInfo taskInfo = ofInfoByOAReq(req);
        taskInfo.setId(taskId);
        taskInfo.setTopId(taskId);
        taskInfo.setCreateBy(sysUser.getUserName());
        taskInfo.setCreateDept(deptId);
        //info.setCreateDept(null);

        //没有经办人时设置为已完成
        if(emptyFlag){
            taskInfo.setProgress(100);
            taskInfo.setStatus("2");
        }

        insertBizTaskInfo(taskInfo);

        //没有经办人时，使用当前处理人为经办人
        if(emptyFlag){
            req.setAssignUserIdList(Collections.singletonList(currHandleUserId));
        }
        List<BizTaskHandle> handleList = ofHandleListByOAReq(req);

        handleList.forEach(item->{
            item.setTaskId(taskId);
            item.setCreateBy(userName);
            //没有经办人时设置为已完成
            if(emptyFlag){
                item.setProgress(100);
                item.setHandleStatus("2");
                item.setCompleteTime(DateUtils.getTime());
                item.setClaimStatus("1");//已认领
                item.setClaimType("1");//办件
            } else {
                item.setClaimStatus("0");//待认领
                item.setScorePercent(0L);//先不给分，认领时给分
            }
            handleService.insertBizTaskHandle(item);
        });

        //不需审核的直接发送提醒消息
        if("3".equals(taskInfo.getAuditStatus()) && !emptyFlag){
            String sender = sysUser.getUserName();
            String title = String.format("您有一个新的待认领任务【%s】，请注意查看。",taskInfo.getTitle());
            String name = sysUser.getName();
            String message = String.format("%s发布了一个新的待认领任务【%s】，请注意查看",name,taskInfo.getTitle());
            sendMessage(title,message,sender,handleList,msgBizId);
        }

    }

    /**
     * 模拟数据
     * @return
     */
    private SysUser mockSysUser(){
        Long[] scope = {70L, 71L, 72L, 73L, 74L, 75L, 76L, 77L, 78L, 79L, 80L};
        int index = RandomUtil.randomInt(0,scope.length);
        return userService.selectUserById(scope[index]);
    }

    public BizTaskInfo ofInfoByOAReq(TaskInfoVo.FormOAReq req){
        BizTaskInfo info = new BizTaskInfo();
        info.setCausation("1");
        info.setBusinessId(req.getBusinessId());
        info.setTitle(req.getTitle());
        String handleOpinion = req.getHandleOpinion();
        //数据库非空，所以给空格
        handleOpinion = StringUtils.isBlank(handleOpinion) ? " " : "处理意见：" + handleOpinion;
        info.setDesc(handleOpinion);
        String assignTime = req.getAssignTime();
        if(StringUtils.isBlank(assignTime)){
            assignTime = DateUtil.formatDateTime(new Date());
        }
        info.setBeginTime(assignTime);
        //默认5天后为完成时间
        String endTime = DateUtil.formatDateTime(DateUtil.offsetDay(DateUtil.parseDateTime(assignTime), 5));
        info.setEndTime(endTime);
        //默认紧急程度和难易程度为一般
//        info.setUrgency(req.getUrgency());
        if(StringUtils.isBlank(req.getUrgency())){
            info.setUrgency("1");
        } else {
            // oa来的参数为 一般 紧急 特急
            // 我们的字典为 1-一般 2-重要 3-紧急 4-非常紧急
            String urgency = req.getUrgency();
            if("一般".equals(urgency)){
                info.setUrgency("1");
            } else if("紧急".equals(urgency)){
                info.setUrgency("3");
            } else if("特急".equals(urgency)){
                info.setUrgency("4");
            } else {
                info.setUrgency("1");
            }
        }
        info.setDifficult("2");
        long urgency = Long.parseLong(info.getUrgency());
        long diffcult = Long.parseLong(info.getDifficult());
        info.setPlanScore(urgency * diffcult);
        info.setProgress(0);
        info.setStatus("1");
        //oa来源时
        info.setAuditStatus("3");
        info.setDocType("1");//默认待办件
        info.setRemark(req.getRemark());

        // 额外信息
        JSONObject extInfo = new JSONObject();
        extInfo.put("docNum",req.getDocNum());
        extInfo.put("registerTime",req.getRegisterTime());
        extInfo.put("transferList",req.getTransferList());
        extInfo.put("fileList",req.getFileList());

        info.setExtInfo(extInfo.toJSONString());
        return info;
    }

    private List<BizTaskHandle> ofHandleListByOAReq(TaskInfoVo.FormOAReq req){
        List<BizTaskHandle> handleList = new LinkedList<>();
        //当前处理领导
//        SysUserOut outQuery = new SysUserOut();
//        outQuery.setUsername(req.getCurrHandleUserId());
//        SysUserOut currHandleUser = oaUserService.selectSysUserOutList(outQuery)
//                .stream().findFirst()
//                .orElseThrow(()->new CustomException("当前OA用户尚未设置用户关联信息，请联系统计考核评价管理系统管理员设置后再试"));
//        String currHandleDeptId = currHandleUser.getMainDeptid();

        SysUser currHandleUser = oaUserService.selectSysUserByOaUserName(req.getCurrHandleUserId());
        if(null == currHandleUser){
            throw new CustomException("当前OA用户尚未设置用户关联信息，请联系统计考核评价管理系统管理员设置后再试");
        }
        Long currHandleDeptId = currHandleUser.getDeptId();
        //构建处理记录
        List<String> oaUserIdList = req.getAssignUserIdList();
        Long totalPercent = 100L;
        Long perPercent = totalPercent/ oaUserIdList.size();
        // 先检查是否存在映射，排除没映射的人
        List<SysUser> handleUserList = new LinkedList<>();
        for (int i = 0; i < oaUserIdList.size(); i++) {
            String oaUserId = oaUserIdList.get(i);
//            SysUserOut sysUserOut = oaUserService.selectSysUserOutById(oaUserId);
            SysUser sysUser = oaUserService.selectSysUserByOaUserId(oaUserId);
            if (null != currHandleDeptId && null != sysUser) {
                // TODO 与交办人相同部门的OA用户才取，存在一人多部门的问题尚未处理
                if (!currHandleDeptId.equals(sysUser.getDeptId())) {
                    continue;
                }
                if (null == sysUser) {
                    //TODO mock数据 待移除
//                sysUser = mockSysUser();
//                throw new CustomException("id为" + oaUserId + "的经办用户尚未设置用户关联信息，请联系统计考核评价管理系统管理员设置后再试");
                    log.error("id为" + oaUserId + "的经办用户尚未设置用户关联信息，请联系统计考核评价管理系统管理员设置");
                    continue;
                }
                handleUserList.add(sysUser);
            }
        }

        for (int i = 0; i < handleUserList.size(); i++) {
            SysUser sysUser = handleUserList.get(i);

            BizTaskHandle handle = new BizTaskHandle();
            handle.setHandleType("1");
             handle.setHandleUser(sysUser.getUserName());
            if(i == oaUserIdList.size() - 1){
                Long last = totalPercent - (perPercent * (oaUserIdList.size() - 1));
                handle.setScorePercent(last);
            } else {
                handle.setScorePercent(perPercent);
            }
            handle.setProgress(0);
            handle.setHandleStatus("1");
            //个人处理时不需要指派了
            handle.setAssignStatus("1");

            String assignTime = req.getAssignTime();
            if(StringUtils.isBlank(assignTime)){
                assignTime = DateUtil.formatDateTime(new Date());
            }
            handle.setReceiveTime(assignTime);
            handle.setMasterFlag(false);


            handleList.add(handle);
        }

        return handleList;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void create(TaskInfoVo.CreateReq req) {
        Long totalPercent = req.getHandleList().stream().map(item -> item.getScorePercent()).reduce(0L, (a, b) -> a + b);
        if(totalPercent != 100){
            throw new CustomException("所有经办人的工作量占比(%)总和不等于100,请调整后再试");
        }

        String taskId = IdUtils.randomUUID();
        BizTaskInfo taskInfo = of(req);
        taskInfo.setId(taskId);
        taskInfo.setTopId(taskId);
        //系统自建
//        if("3".equals(taskInfo.getCausation())){
//            //需要审核
//            taskInfo.setAuditStatus("1");
//        } else {
            //oa来源时
            taskInfo.setAuditStatus("3");
//        }

        // 算出任务总分
        //系统自建-取分类分数    oa创建-取紧急程度*难度
        BizTaskDocType docType = docTypeService.selectBizTaskDocTypeByCode(req.getDocType());
        if("3".equals(taskInfo.getCausation())){
            long docTypeScore = docType.getScore();
            taskInfo.setPlanScore(docTypeScore);
        } else {
            long urgency = Long.parseLong(taskInfo.getUrgency());
            long diffcult = Long.parseLong(taskInfo.getDifficult());
            taskInfo.setPlanScore(urgency * diffcult);
        }

        insertBizTaskInfo(taskInfo);
        List<BizTaskHandle> handleList = of(req.getHandleList());
        handleList.forEach(item->{
            item.setTaskId(taskId);
            item.setClaimStatus("1");//手工创建的默认已认领
            item.setClaimType("1");//手工创建的默认为办件
            handleService.insertBizTaskHandle(item);
        });

        //不需审核的直接发送提醒消息
        if("3".equals(taskInfo.getAuditStatus())){
            String sender = SecurityUtils.getUsername();
            String title = String.format("您有一个新的待办任务【%s】，请注意查看。",taskInfo.getTitle());
            String name = SecurityUtils.getLoginUser().getUser().getName();
            String message = String.format("%s指定了您为任务【%s】的经办人，请及时处理",name,taskInfo.getTitle());
            sendMessage(title,message,sender,handleList,null);
        }

    }

    private BizTaskInfo of(TaskInfoVo.CreateReq req){
        BizTaskInfo taskInfo = new BizTaskInfo();
        taskInfo.setCausation(req.getCausation());
        taskInfo.setBusinessId(req.getBusinessId());
        taskInfo.setBasis(req.getBasis());
        taskInfo.setTopId(req.getTopId());
        taskInfo.setParentId(req.getParentId());
        taskInfo.setTitle(req.getTitle());
        taskInfo.setDesc(req.getDesc());
        taskInfo.setBeginTime(req.getBeginTime());
        taskInfo.setEndTime(req.getEndTime());
        taskInfo.setUrgency(req.getUrgency());
        taskInfo.setPlanScore(req.getPlanScore());
        taskInfo.setDifficult(req.getDifficult());
        taskInfo.setCompleteSituation(req.getCompleteSituation());
        taskInfo.setProgress(req.getProgress());
        taskInfo.setStatus(req.getStatus());
        Integer progress = taskInfo.getProgress();
        if(progress >= 100){
            taskInfo.setStatus("2");
        } else{
            taskInfo.setStatus("1");
        }
        taskInfo.setAuditStatus("0");//默认草稿
        taskInfo.setAttach(req.getAttach());
        taskInfo.setDocType(req.getDocType());
        taskInfo.setRemark(req.getRemark());
        return taskInfo;
    }

    private List<BizTaskHandle> of(List<TaskInfoVo.HandleBuildItem> list){
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
            handle.setMasterFlag(item.getMasterFlag());
            handle.setRemark(item.getRemark());
            //根据处理进度设置处理状态
            Integer progress = handle.getProgress();
            if(progress >= 100){
                handle.setHandleStatus("2");
            } else {
                handle.setHandleStatus("1");
            }

            return handle;
        }).collect(Collectors.toList());

        return handleList;
    }

    @Override
    public void audit(TaskInfoVo.AuditReq req) {
        BizTaskInfo taskInfo = bizTaskInfoMapper.selectBizTaskInfoById(req.getId());
        if(null == taskInfo || !"1".equals(taskInfo.getAuditStatus())){
            throw new CustomException("未获取到任务或任务目前不需要审核");
        }
        taskInfo.setAuditStatus(req.getAuditStatus());
        taskInfo.setRejectReason(req.getRejectReason());
        bizTaskInfoMapper.updateBizTaskInfo(taskInfo);

        //不需审核的直接发送提醒消息
        if("3".equals(taskInfo.getAuditStatus())){
            //查任务处理的
            BizTaskHandle query = new BizTaskHandle();
            query.setTaskId(req.getId());
            query.setClaimStatus("1");
            query.setClaimType("1");
            List<BizTaskHandle> handleList = handleService.selectBizTaskHandleList(query);

            String sender = SecurityUtils.getUsername();
            String title = String.format("您有一个新的待办任务【%s】，请注意查看。",taskInfo.getTitle());
            String name = SecurityUtils.getLoginUser().getUser().getName();
            String message = String.format("%s指定了您为任务【%s】的经办人，请及时处理",name,taskInfo.getTitle());
            sendMessage(title,message,sender,handleList,null);
        }

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void submitComment(TaskInfoVo.SubmitCommentReq req) {
        String username = SecurityUtils.getUsername();

        BizTaskHandle query = new BizTaskHandle();
        query.setTaskId(req.getTaskId());
        query.setHandleUser(username);
        List<BizTaskHandle> handleList = handleService.selectBizTaskHandleList(query);

        //添加处理日志
        BizTaskComment comment = new BizTaskComment();
        comment.setId(IdUtils.randomUUID());
        comment.setTaskId(req.getTaskId());
        comment.setComment(req.getCompleteSituation());

        //当前用户是经办人，需要更新处理记录
        if(!handleList.isEmpty()){
            BizTaskHandle handle = handleList.get(0);
            if(null == req.getProgress()){
                throw  new CustomException("请选择进度");
            }
            handle.setProgress(req.getProgress());
            handle.setCompleteSituation(req.getCompleteSituation());
            handle.setTaskAttach(req.getResultAttach());
            handleService.updateBizTaskHandle(handle);

            //评论内容需要修改
            String str = String.format("更新进度至%s%%;处理情况为:%s",req.getProgress(),req.getCompleteSituation());
            comment.setComment(str);
        }

        commentService.insertBizTaskComment(comment);

    }

    @Override
    public void complete(String taskId) {
        BizTaskInfo taskInfo = bizTaskInfoMapper.selectBizTaskInfoById(taskId);
        taskInfo.setProgress(100);
        taskInfo.setStatus("2");
        updateBizTaskInfo(taskInfo);

        BizTaskHandle query = new BizTaskHandle();
        query.setTaskId(taskId);
        List<BizTaskHandle> handleList = handleService.selectBizTaskHandleList(query);
        handleList.forEach(bizTaskHandle->{
            bizTaskHandle.setProgress(100);
            bizTaskHandle.setCompleteTime(DateUtils.getTime());
            bizTaskHandle.setHandleStatus("2");
            handleService.updateBizTaskHandle(bizTaskHandle);
        });
    }

    @Override
    public TaskInfoVo.UserHandle4EvaluationResp getUserHandle4Evaluation(TaskInfoVo.UserHandle4EvaluationReq req) {
        SysUser user = userService.selectUserById(req.getUserId());
        boolean isDeptLeader = false;
//        if(null != user.getDept()){
//            isDeptLeader = user.getUserName().equals(user.getDept().getLeader());
//        }
        String userName = user.getUserName();
        // 任务要取任务时间与参数时间有交集的任务列表
        // 对应的任务处理信息要取参数时间段内的
        List<BizTaskHandle> handleList = bizTaskInfoMapper.listHandle4Evaluation(userName, req.getBeginTime(), req.getEndTime(),isDeptLeader);
        List<String> taskIds = handleList.stream().map(BizTaskHandle::getTaskId).collect(Collectors.toList());
        Map<String, BizTaskInfo> taskMap = new LinkedHashMap<>();
        if(!taskIds.isEmpty()){
            taskMap = bizTaskInfoMapper.selectBizTaskInfoByIdIn(taskIds).stream().collect(Collectors.toMap(BizTaskInfo::getId, item -> item, (value1, value2) -> value2));
        }

        BigDecimal totalScore = BigDecimal.ZERO;
        List<TaskInfoVo.UserHandleDetail> detailList = new LinkedList<>();
        Iterator<Map.Entry<String, BizTaskInfo>> it = taskMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, BizTaskInfo> item = it.next();
            String taskId = item.getKey();
            BizTaskInfo task = item.getValue();
            Long planScore = task.getPlanScore();
            //此任务下已完成的处理记录的工作量百分比累加获取
            long taskPercent = handleList.stream()
                    .filter(o -> taskId.equals(o.getTaskId()) && userName.equals(o.getHandleUser()) && "2".equals(o.getHandleStatus()))
                    .mapToLong(BizTaskHandle::getScorePercent).sum();
            //工作积分累加
            totalScore = totalScore.add(BigDecimal.valueOf(planScore).multiply(BigDecimal.valueOf(taskPercent)).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_UP));

            List<BizTaskComment> commentList = commentService.list4Evaluation(taskId, userName, req.getBeginTime(), req.getEndTime());

            TaskInfoVo.UserHandleDetail detail = new TaskInfoVo.UserHandleDetail();
            detail.setTaskId(taskId);
            detail.setTitle(task.getTitle());
            detail.setStatus(task.getStatus());
            detail.setCommentList(commentList);
            detailList.add(detail);
        }

        TaskInfoVo.UserHandle4EvaluationResp resp = new TaskInfoVo.UserHandle4EvaluationResp();
        resp.setScore(totalScore);
        resp.setDetailList(detailList);
        return resp;
    }

    private void sendMessage(String title, String message,String sender,List<BizTaskHandle> handleList,String bizId){
        List<String> userNameList = handleList.stream().filter(item->{
            return null != item && StringUtils.isNotBlank(item.getHandleUser());
        }).filter(item -> "1".equals(item.getHandleStatus()))
                .map(BizTaskHandle::getHandleUser).collect(Collectors.toList());
        String userIds = userService.getUserIdStrByLoginNameList(userNameList);
        if(StringUtils.isNotBlank(userIds)){
            messageService.sendMessage2User(title,message,sender,userIds,bizId);
        }
    }
}
