package com.cb.assess.service.impl;

import com.cb.assess.domain.*;
import com.cb.assess.domain.vo.EscalationVo;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.*;
import com.cb.assess.service.EscalationService;
import com.cb.assess.service.VAssessmentProcessService;
import com.cb.assess.utils.CycleUtil;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VAssessmentProcessServiceImpl implements VAssessmentProcessService {
    @Autowired
    private VAssessmentProcessMapper processMapper;
    @Autowired
    private BizAssessTaskAssessUserConfirmMapper confirmMapper;
    @Autowired
    private BizIndividualAssessmentTaskMapper individualTaskMapper;
    @Autowired
    private BizAssessRegularInfoMapper regularInfoMapper;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private VEvaluationGradeMapper gradeMapper;
    @Autowired
    private EscalationService escalationService;


    @Override
    public List<VAssessmentProcess> selectProcessList(VAssessmentProcess.ProcessVo processVo) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        Long deptId = user.getDeptId();
        processVo.setUserId(userId);
        processVo.setDeptId(deptId);
        List<SysRole> roleList = user.getRoles();
        String roleKeys = roleList.stream().map(SysRole::getRoleKey).distinct().collect(Collectors.joining(","));

        Integer handleStatus = processVo.getHandleStatus();
        if(null == handleStatus) processVo.setHandleStatus(0);
        List<VAssessmentProcess> list = processMapper.selectProcessList(processVo,roleKeys);
        this.buildVAssessmentProcessName(list);
        return list;
    }

    private void buildVAssessmentProcessName(List<VAssessmentProcess> list) {
        list.stream().forEach(item -> {
            String special = item.getSpecial();
            if (Special.NORMAL.getCode().equals(special)) {
                String cycle = item.getCycle();
                String cycleDesc = item.getCycleDesc();
                if (StringUtils.isNotBlank(cycle) || StringUtils.isNotBlank(cycleDesc)) {
                    String name = String.format("【%s】%s", CycleUtil.parseCycle(cycle, cycleDesc), Special.ofCode(special).getDesc());
                    item.setName(name);
                }
            }
        });
    }

    @Override
    public List<VOrdinaryAssessTask> selectNeedConfirmList(VOrdinaryAssessTask vOrdinaryAssessTask) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        return confirmMapper.selectTaskManageNeedConfirmList(vOrdinaryAssessTask, deptId);
    }

    @Override
    public List<BizIndividualAssessmentTask> evaluateToDoList(BizIndividualAssessmentTask task) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<BizIndividualAssessmentTask> tasks = individualTaskMapper.selectAllToDoTaskList(userId, task);
        tasks.forEach(o->{
            VRegularFillInfo info = individualTaskMapper.selectVRegularFillInfoList(o.getTaskId(), userId);
            if(info != null && "2".equals(info.getAuditStatus())) o.setSummarizeAuditFailed(true);
            o.setRegular(info);
        });
        return tasks;
    }

    @Override
    public List<BizAssessRegularInfo> selectNeedApprovalSummaryList(VAssessmentProcess.ProcessVo processVo) {
        String[] jobLevels = {"111", "112", "211"}; //获取数据时排除(111:正厅级，112:副厅级，211 一级巡视员)
        List<BizAssessRegularInfo> list =
                regularInfoMapper.selectNeedApprovalSummaryList(processVo, Arrays.asList(jobLevels), processVo.getHandleStatus() == 0 ? 1 : 2);
        return list;
    }

    @Override
    public List<BizAssessRegularInfo> selectNeedAuditSummaryList(VAssessmentProcess.ProcessVo processVo) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        Long userId = user.getUserId();
        //  部门主要负责人，主任
        if (SecurityUtils.hasForceRole("dept_master_leader")) {
            // 获取数据时排除(111:正厅级，112:副厅级，121:正处级，212:二级巡视员)
            String[] jobLevels = {"111", "112", "121", "212"};
            List<Long> deptIds = new ArrayList<>();
            deptIds.add(deptId);
            if(!CollectionUtils.isEmpty(deptIds)){
                return regularInfoMapper.selectNeedAuditSummaryList(processVo,Arrays.asList(jobLevels), deptIds,processVo.getHandleStatus(), true);
            }
        }
        // 分管领导
        if (SecurityUtils.hasForceRole("leader_charge")) {
            List<SysDept> chargeDeptList = deptService.selectChargeDeptList(userId);
            //  获取 121:正处级，212:二级巡视员
            String[] jobLevels = {"121", "212"};
            List<Long> deptIds = chargeDeptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(deptIds)){
                return regularInfoMapper.selectNeedAuditSummaryList(processVo,Arrays.asList(jobLevels), deptIds, processVo.getHandleStatus(), false);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<VAssessmentProcess.EvaluationScoreResp> selectEvaluationScore(VAssessmentProcess.EvaluationScoreReq req) {
        String special = req.getSpecial();
        List<String> taskIds = req.getTaskIds().stream().distinct().collect(Collectors.toList());
        List<Long> userIds = req.getUserIds();
        if(CollectionUtils.isEmpty(taskIds) || CollectionUtils.isEmpty(userIds)){
            return Collections.emptyList();
        }
        List<EscalationVo.ReportedEvaluate> evaluates = escalationService.selectVoterListByKeys(special, taskIds, userIds);
        ArrayList<VAssessmentProcess.EvaluationScoreResp> list = new ArrayList<>();
        evaluates.stream().forEach(item->{
            VAssessmentProcess.EvaluationScoreResp resp = new VAssessmentProcess.EvaluationScoreResp();
            resp.setUserId(item.getUserId());
            resp.setUserName(item.getName());
            resp.setDeptName(item.getDeptName());
            resp.setScore(item.getEvaluationScore());
            list.add(resp);
        });
        return list;
    }

    @Override
    public List<VEvaluationGrade.Grade> selectDzhGradeList(VAssessmentProcess.ProcessVo processVo) {
        return gradeMapper.selectDzhGradeList(processVo);
    }
}
