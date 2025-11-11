package com.cb.assess.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.cb.assess.domain.*;
import com.cb.assess.domain.vo.ExaminerVo;
import com.cb.assess.enums.AssessType;
import com.cb.assess.enums.Special;
import com.cb.assess.enums.TaskStatus;
import com.cb.assess.mapper.BizAssessIndicatorProMapper;
import com.cb.assess.mapper.BizAssessTaskManageMapper;
import com.cb.assess.mapper.BizAssessUserMapper;
import com.cb.assess.service.*;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.message.service.MessageService;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考核任务管理Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-04
 */
@Service
public class BizAssessTaskManageServiceImpl implements IBizAssessTaskManageService {
    @Autowired
    private BizAssessTaskManageMapper bizAssessTaskManageMapper;
    @Autowired
    private IBizAssessTaskUserTagService userTagService;
    @Autowired
    private IBizAssessUserService assessUserService;
    @Autowired
    private IBizAssessTaskEvaluateTagService evaluateTagService;
    @Autowired
    private BizAssessIndicatorProMapper proMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private IBizAssessRegularInfoService regularInfoService;
    @Autowired
    private IBizAssessTaskEvaluateRecordService evaluateRecordService;
    @Autowired
    private IBizAssessTaskAssessUserConfirmService confirmService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private BizAssessUserMapper assessUserMapper;
    @Autowired
    private IBizAssessTaskAssessUserConfirmService userConfirmService;


    /**
     * 查询考核任务管理
     *
     * @param taskId 考核任务管理ID
     * @return 考核任务管理
     */
    @Override
    public BizAssessTaskManage selectBizAssessTaskManageById(String taskId) {
        return bizAssessTaskManageMapper.selectBizAssessTaskManageById(taskId);
    }

    /**
     * 查询考核任务管理列表
     *
     * @param bizAssessTaskManage 考核任务管理
     * @return 考核任务管理
     */
    @Override
    public List<BizAssessTaskManage> selectBizAssessTaskManageList(BizAssessTaskManage bizAssessTaskManage) {
        Date now = new Date();
        List<BizAssessTaskManage> list = bizAssessTaskManageMapper.selectBizAssessTaskManageList(bizAssessTaskManage);
        List<BizAssessIndicatorPro> pros = proMapper.selectAllProList();

        list.forEach(o->{
            String proId = o.getProId();
            BizAssessIndicatorPro pro = pros.stream().filter(p -> proId.equals(p.getProId())).findFirst().orElse(null);
            o.setPro(pro);
            Date endTime = o.getEndTime();
            int e = endTime.compareTo(now);
            if(e < 0) {
                o.setEnableRevoke(false);
            }else{
                o.setEnableRevoke(true);
            }
        });
        return list;
    }

    /**
     * 新增考核任务管理
     *
     * @param bizAssessTaskManage 考核任务管理
     * @return 结果
     */
    @Override
    public int insertBizAssessTaskManage(BizAssessTaskManage bizAssessTaskManage) {
        bizAssessTaskManage.setTaskId(IdUtils.randomUUID());
        bizAssessTaskManage.setCreateBy(SecurityUtils.getUsername());
        bizAssessTaskManage.setCreateTime(DateUtils.getNowDate());
        bizAssessTaskManage.setStatus(TaskStatus.DRAFT.getCode());
        bizAssessTaskManage.setDelFlag("1");
        return bizAssessTaskManageMapper.insertBizAssessTaskManage(bizAssessTaskManage);
    }

    /**
     * 修改考核任务管理
     *
     * @param bizAssessTaskManage 考核任务管理
     * @return 结果
     */
    @Override
    public int updateBizAssessTaskManage(BizAssessTaskManage bizAssessTaskManage) {
        bizAssessTaskManage.setUpdateBy(SecurityUtils.getUsername());
        bizAssessTaskManage.setUpdateTime(DateUtils.getNowDate());
        return bizAssessTaskManageMapper.updateBizAssessTaskManage(bizAssessTaskManage);
    }

    /**
     * 批量删除考核任务管理
     *
     * @param taskIds 需要删除的考核任务管理ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskManageByIds(String[] taskIds) {
        return bizAssessTaskManageMapper.deleteBizAssessTaskManageByIds(taskIds);
    }

    /**
     * 删除考核任务管理信息
     *
     * @param taskId 考核任务管理ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessTaskManageById(String taskId) {
        return bizAssessTaskManageMapper.deleteBizAssessTaskManageById(taskId);
    }

    @Override
    public int distribute(BizAssessTaskManage manage) {
        if(StringUtils.isNotEmpty(manage.getStatus())){
            manage.setStatus(TaskStatus.ISSUANCE.getCode());
        }
        int result = bizAssessTaskManageMapper.changStatus(manage);
        return result;
    }

    @Override
    public List<BizAssessTaskAssessUserConfirm> confirmList(BizAssessTaskManage manage) {
        String taskId = manage.getTaskId();
        String proId = manage.getProId();
        // 获取确认的用户
        List<BizAssessTaskAssessUserConfirm> confirms = confirmService.selectTaskAssessUserConfirmByTaskId(taskId);
        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        List<BizAssessIndicatorProDuty> dutyList = pro.getDutyList();
        Map<Long, BizAssessTaskAssessUserConfirm> map = confirms.stream().collect(Collectors.toMap(BizAssessTaskAssessUserConfirm::getDeptId, confirm -> confirm));
        ArrayList<BizAssessTaskAssessUserConfirm> result = new ArrayList<>();
        dutyList.stream().forEach(o->{
            Long deptId = o.getDeptId();
            BizAssessTaskAssessUserConfirm confirm = map.get(deptId);
            if(null != confirm){
                confirm.setDeptName(o.getDeptName());
                result.add(confirm);
            }else{
                BizAssessTaskAssessUserConfirm defaultConfirm = new BizAssessTaskAssessUserConfirm();
                defaultConfirm.setTaskId(taskId);
                defaultConfirm.setDeptId(deptId);
                defaultConfirm.setDeptName(o.getDeptName());
                defaultConfirm.setUsersJson("");
                defaultConfirm.setStatus("0");
                result.add(defaultConfirm);
            }
        });
        return result;
    }

    @Override
    @Transactional
    public int release(BizAssessTaskManage manage) {
        String taskId = manage.getTaskId();
        boolean released = bizAssessTaskManageMapper.checkTaskReleased(taskId);
        if(released){
            throw new IllegalStateException("任务已发布，请勿重复操作！");
        }
        int result = bizAssessTaskManageMapper.changStatus(manage);
        if ("1".equals(manage.getStatus())) {
            this.batchProcessingData(manage);
        }
        return result;
    }

    @Override
    @Transactional
    public int revoke(BizAssessTaskManage manage) {
        Date now = new Date();

        Date endTime = manage.getEndTime();
        int e = endTime.compareTo(now);
        if(e < 0) throw new RuntimeException("任务已填报结束，禁止撤销");

        Date startTime = manage.getStartTime();
        int s = startTime.compareTo(now);
        if(s < 0) throw new RuntimeException("任务已开始填报，禁止撤销");

        int result = bizAssessTaskManageMapper.changStatus(manage);
        if("2".equals(manage.getStatus())){
            this.batchDeleteTaskCorrelationData(manage.getTaskId());
        }
        return result;
    }

    @Override
    public int logicDeleteBizAssessTaskManageById(String taskId) {
        return bizAssessTaskManageMapper.logicDeleteBizAssessTaskManageById(taskId);
    }

    @Override
    public int logicDeleteBizAssessTaskManageByIds(String[] taskIds) {
        return bizAssessTaskManageMapper.logicDeleteBizAssessTaskManageByIds(taskIds);
    }

//    @Override
//    public Boolean checkTaskExist(BizAssessTaskManage manage) {
//        String cycle = manage.getAssessCycle();
//        if (!"4".equals(cycle)) {
//            boolean b = bizAssessTaskManageMapper.checkTaskExist(manage);
//            return b;
//        } else {
//            return false;
//        }
//    }

    @Override
    public void checkTaskIsSameYear(BizAssessTaskManage manage) {
        String belongYear = manage.getBelongYear();
        String special = manage.getSpecial();
        String cycle = manage.getAssessCycle();
        if(Special.NORMAL.getCode().equals(special)){
            if(AssessType.MONTHLY.getCode().equals(cycle)){
                String taskMoth = manage.getTaskMoth();
                Date date = DateUtil.parse(taskMoth);
                String year = DateUtil.format(date, "yyyy");
                if(!belongYear.equals(year)){
                    throw new RuntimeException("考核年份和任务月度不匹配！");
                }
            }
            if(AssessType.QUARTERLY.getCode().equals(cycle)){
                String taskQuarter = manage.getTaskQuarter();
                Date date = DateUtil.parse(taskQuarter);
                String year = DateUtil.format(date, "yyyy");
                if(!belongYear.equals(year)){
                    throw new RuntimeException("考核年份和任务季度不匹配！");
                }
            }
            if(AssessType.YEARLY.getCode().equals(cycle)){
                String taskYear = manage.getTaskYear();
                if(!belongYear.equals(taskYear)){
                    throw new RuntimeException("考核年份和任务年度不匹配！");
                }
            }
        }
    }

    @Override
    public void checkTaskQuarter(BizAssessTaskManage manage) {
        String cycle = manage.getAssessCycle();
        if (!"4".equals(cycle)) {
            int i = bizAssessTaskManageMapper.checkTaskQuarter(manage);
            if (i > 0) {
                throw new RuntimeException("当前季度已存在考核任务");
            }
        }
    }


    private boolean checkStartTimeLtNow(BizAssessTaskManage manage) {
        Date startTime = manage.getStartTime();
        int i = startTime.compareTo(new Date());
        if(i < 0) return true;
        return false;
    }

    private List<SysUser> getAssessUser(String taskId,BizAssessIndicatorPro pro,List<BizAssessIndicatorProDuty> dutyList){
        List<BizAssessTaskAssessUserConfirm> confirms = confirmService.selectTaskAssessUserConfirmByTaskId(taskId);
        String special = pro.getSpecial();
        if (Special.NORMAL.getCode().equals(special)) {
            List<Long> proDuty = dutyList.stream().map(BizAssessIndicatorProDuty::getDeptId).collect(Collectors.toList());
            List<Long> confirmDept = confirms.stream().map(BizAssessTaskAssessUserConfirm::getDeptId).collect(Collectors.toList());
            // 获取未确认的部门
            List<Long> unConfirm = proDuty.stream().filter(o -> !confirmDept.contains(o)).collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(unConfirm)){
                List<SysDept> depts = deptService.selectAllDeptList();
                List<String> unConfirmDeptNames = depts.stream().filter(d -> unConfirm.contains(d.getDeptId())).map(SysDept::getDeptName).collect(Collectors.toList());

                String format = String.format("以下部门未确认考核信息【%s】，禁止发布!!", StringUtils.join(unConfirmDeptNames, "，"));
                throw new RuntimeException(format);
            }

            // 检查
            List<BizAssessTaskAssessUserConfirm> rejectionList = confirms.stream().filter(k -> "2".equals(k.getStatus())).collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(rejectionList)){
                List<SysDept> depts = deptService.selectAllDeptList();
                List<String> rejectionDeptNames = depts.stream().filter(d -> rejectionList.contains(d.getDeptId())).map(SysDept::getDeptName).collect(Collectors.toList());
                String format = String.format("以下部门驳回后未确认考核信息【%s】，禁止发布!!", StringUtils.join(rejectionDeptNames, "，"));
                throw new RuntimeException(format);
            }

            List<SysUser> allUser = userService.selectAllUserList();
            // 获取已确认的用户ID
            /*List<?> ids = confirms.stream().map(k -> {
                String usersJson = k.getUsersJson();
                if (StringUtils.isNotEmpty(usersJson)) {
                    String[] split = usersJson.split(",");
                    List<Long> longList = Arrays.stream(split)
                            .filter(o -> StringUtils.isNotEmpty(o))
                            .distinct()
                            .map(Long::parseLong) // 将每个字符串转换为Long类型
                            .collect(Collectors.toList());
                    return longList;
                }else{
                    return Collections.emptyList();
                }
            }).flatMap(List::stream).collect(Collectors.toList());
            List<SysUser> users = allUser.stream().filter(o -> ids.contains(o.getUserId())).collect(Collectors.toList());*/
            List<SysUser> result=new ArrayList<>();
            Map<Long, String> map = confirms.stream()
                    .filter(o->"1".equals(o.getStatus())) // 过滤出正常参加考核的
                    .collect(Collectors.toMap(BizAssessTaskAssessUserConfirm::getDeptId, e -> e.getUsersJson()));
            map.entrySet().stream().forEach(entry->{
                Long deptId = entry.getKey();
                String json = entry.getValue();
                if (StringUtils.isNotEmpty(json)) {
                    String[] split = json.split(",");
                    List<Long> userIds = Arrays.stream(split)
                            .filter(o -> StringUtils.isNotEmpty(o))
                            .distinct()
                            .map(Long::parseLong) // 将每个字符串转换为Long类型
                            .collect(Collectors.toList());
                    List<SysUser> userList = allUser.stream().filter(o -> userIds.contains(o.getUserId())).map(e -> {
                        // 设置为评议部门，防止打分时会获取考核对象现在所在部门人员
                        e.setDeptId(deptId);
                        return e;
                    }).collect(Collectors.toList());
                    result.addAll(userList);
                }
            });
            return  result;
        }else{
            List<Long> ids = dutyList.stream().filter(o -> "1".equals(o.getType()))
                    .map(item -> item.getUserId()).collect(Collectors.toList());
            List<SysUser> list = assessUserMapper.selectSysUserList(ids);
            return list;
        }
    }

    int batchProcessingData(BizAssessTaskManage manage) {
        String taskId = manage.getTaskId();
        String proId = manage.getProId();
        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        manage.setPro(pro);
        List<String> procedureList = pro.getProcedureList().stream().map(BizAssessIndicatorProProcedure::getName).collect(Collectors.toList());
        List<BizAssessIndicatorProDuty> dutyList = pro.getDutyList();
        // 获取被考核用户
        List<SysUser> users = getAssessUser(taskId, pro, dutyList);

        List<Long> userIds = users.stream().map(SysUser::getUserId).collect(Collectors.toList());
        List<BizAssessTaskEvaluateTag> finalEvaluateTagList = new ArrayList<>();
        List<BizAssessTaskUserTag> regularUserTagList = new ArrayList<>();
        if (users.size() > 0) {
            users.stream().forEach(item -> {
                if (procedureList.indexOf("0") > -1) {
                    BizAssessTaskUserTag tag = new BizAssessTaskUserTag();
                    tag.setId(IdUtils.randomUUID());
                    tag.setTaskId(taskId);
                    tag.setUserId(item.getUserId());
                    tag.setCreateBy(SecurityUtils.getUsername());
                    tag.setCreateTime(DateUtils.getNowDate());
                    regularUserTagList.add(tag);
                }
                List<ExaminerVo> examinerVos = assessUserService.selectExaminerVoListByTaskManage(manage, item);
                List<BizAssessTaskEvaluateTag> evaluateTagList = examinerVos.stream().map(vo -> {
                    Long userId = vo.getUserId();
                    userIds.add(userId);
                    BizAssessTaskEvaluateTag evaluateTag = new BizAssessTaskEvaluateTag();
                    evaluateTag.setId(IdUtils.randomUUID());
                    evaluateTag.setTaskId(taskId);
                    evaluateTag.setVoteUserId(userId);
                    evaluateTag.setAssessedUserId(item.getUserId());
                    evaluateTag.setVoteType(vo.getVoteType());
                    evaluateTag.setStatus("0");
                    evaluateTag.setCreateBy(SecurityUtils.getUsername());
                    evaluateTag.setCreateTime(DateUtils.getNowDate());
                    return evaluateTag;
                }).collect(Collectors.toList());
                finalEvaluateTagList.addAll(evaluateTagList);
            });
            if(regularUserTagList.size() > 0){
                userTagService.batchInsertBizAssessTaskUserTag(regularUserTagList);
            }
            if (finalEvaluateTagList.size() > 0) {
                evaluateTagService.batchInsertBizAssessTaskEvaluateTag(finalEvaluateTagList);
            }
            if (CollectionUtil.isNotEmpty(userIds)) {
                sendAssessMessage(taskId,userIds);
            }
            return 1;
        } else {
            throw new CustomException("您当前的配置未获取到任何的被考核对象，请检查指标方案的配置");
        }
    }

    void batchDeleteTaskCorrelationData(String taskId) {
        List<BizAssessTaskUserTag> userTags = userTagService.selectTaskUserTagListByTaskId(taskId);
        List<BizAssessTaskEvaluateTag> evaluateTags = evaluateTagService.selectTaskEvaluateTagListByTaskId(taskId);

        List<String> userTagIds = userTags.stream().map(o -> o.getId()).collect(Collectors.toList());
        List<String> evaluateTagIds = evaluateTags.stream().map(o -> o.getId()).collect(Collectors.toList());

        // 删除个人总结
        if(CollectionUtil.isNotEmpty(userTagIds))
            regularInfoService.deleteRegularInfo(userTagIds);
        //删除互评数据
        if(CollectionUtil.isNotEmpty(evaluateTagIds))
            evaluateRecordService.deleteEvaluateRecord(evaluateTagIds);

        // 删除需要填写总结标记
        userTagService.deleteEntityByTaskId(taskId);
        // 删除互评标记
        evaluateTagService.deleteEvaluateTagByTaskId(taskId);
        // 删除用户确认数据
        userConfirmService.deleteTaskAssessUserConfirmByTaskId(taskId);
    }

    private void sendAssessMessage(String taskId, List<Long> userIds) {
        BizAssessTaskManage manage = bizAssessTaskManageMapper.selectBizAssessTaskManageById(taskId);
        String title = "考核任务提醒";
        String start = DateUtil.format(manage.getStartTime(), "yyyy-MM-dd HH:mm");
        String end = DateUtil.format(manage.getEndTime(), "yyyy-MM-dd HH:mm");
        String message = String.format("您有新的考核任务【%s】，任务起止时间为:%s至%s，请及时处理。",
                manage.getTaskName(), start, end);
        String username = SecurityUtils.getUsername();
        messageService.sendMessage2User(title, message, username, userIds);
    }
}
