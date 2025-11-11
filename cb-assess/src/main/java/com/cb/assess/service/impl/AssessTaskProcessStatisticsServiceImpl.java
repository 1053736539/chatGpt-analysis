package com.cb.assess.service.impl;

import com.cb.assess.domain.*;
import com.cb.assess.domain.vo.AssessTaskProcessStatisticsVo;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.AssessTaskProcessStatisticsMapper;
import com.cb.assess.mapper.BizAssessIndicatorProMapper;
import com.cb.assess.mapper.BizAssessRegularInfoMapper;
import com.cb.assess.mapper.BizAssessTaskAssessUserConfirmMapper;
import com.cb.assess.service.AssessTaskProcessStatisticsService;
import com.cb.assess.service.IBizAssessTaskAssessUserConfirmService;
import com.cb.assess.service.IBizAssessTaskManageService;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AssessTaskProcessStatisticsServiceImpl implements AssessTaskProcessStatisticsService {
    @Autowired
    private AssessTaskProcessStatisticsMapper statisticsMapper;
    @Autowired
    private ISysUserService  userService;
    @Autowired
    private BizAssessIndicatorProMapper proMapper;
    @Autowired
    private IBizAssessTaskAssessUserConfirmService confirmService;
    @Autowired
    private BizAssessRegularInfoMapper regularInfoMapper;


    @Override
    public List<AssessTaskProcessStatisticsVo> selectAssessTaskProcessCount(AssessTaskProcessStatisticsVo vo) {
        return statisticsMapper.selectAssessTaskProcessCount(vo);
    }

    @Override
    public List<BizAssessTaskAssessUserConfirm> selectUserConfirmList(AssessTaskProcessStatisticsVo vo) {
        String taskId = vo.getTaskId();
        String proId = vo.getProId();
        String special = vo.getSpecial();

        BizAssessIndicatorPro pro = proMapper.selectBizAssessIndicatorProById(proId);
        List<BizAssessIndicatorProDuty> dutyList = pro.getDutyList();
        List<BizAssessTaskAssessUserConfirm> result = new ArrayList<>();
        if(Special.NORMAL.getCode().equals(special)) {
            // 获取确认的用户
            List<BizAssessTaskAssessUserConfirm> confirms = confirmService.selectTaskAssessUserConfirmByTaskId(taskId);
            Map<Long, BizAssessTaskAssessUserConfirm> map = confirms.stream().collect(Collectors.toMap(BizAssessTaskAssessUserConfirm::getDeptId, confirm -> confirm));
            dutyList.stream().forEach(o -> {
                Long deptId = o.getDeptId();
                BizAssessTaskAssessUserConfirm confirm = map.get(deptId);
                if (null != confirm) {
                    confirm.setDeptName(o.getDeptName());
                    result.add(confirm);
                } else {
                    BizAssessTaskAssessUserConfirm defaultConfirm = new BizAssessTaskAssessUserConfirm();
                    defaultConfirm.setTaskId(taskId);
                    defaultConfirm.setDeptId(deptId);
                    defaultConfirm.setDeptName(o.getDeptName());
                    defaultConfirm.setUsersJson("");
                    defaultConfirm.setStatus("0");
                    result.add(defaultConfirm);
                }
            });
        }else{
            List<SysUser> users = userService.selectAllUserList();
            Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, u -> u));
            dutyList.stream().forEach(o->{
                Long userId = o.getUserId();
                SysUser user = userMap.get(userId);
                Long deptId = user.getDeptId();
                String deptName = user.getDept().getDeptName();
                BizAssessTaskAssessUserConfirm defaultConfirm = new BizAssessTaskAssessUserConfirm();
                defaultConfirm.setTaskId(taskId);
                defaultConfirm.setDeptId(deptId);
                defaultConfirm.setDeptName(deptName);
                defaultConfirm.setUsersJson(String.valueOf(userId));
                defaultConfirm.setStatus("1");
                result.add(defaultConfirm);
            });
        }
        List<BizAssessTaskAssessUserConfirm> sortedList = result.stream()
                .sorted(Comparator.comparing(BizAssessTaskAssessUserConfirm::getDeptId)).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<VRegularFillInfo> selectVRegularFillInfoList(VRegularFillInfo info) {
        return regularInfoMapper.selectVRegularFillInfoList(info);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.EvaluateReport> selectEvaluateReportList(AssessTaskProcessStatisticsVo.EvaluateReport report) {
        return statisticsMapper.selectEvaluateReportList(report);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.EvaluateReportAudit> selectEvaluateReportAuditList(AssessTaskProcessStatisticsVo.EvaluateReportAudit audit) {
        return statisticsMapper.selectEvaluateReportAuditList(audit);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.Discipline> selectDisciplineList(AssessTaskProcessStatisticsVo.Discipline discipline) {
        return statisticsMapper.selectDisciplineList(discipline);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.MainLevelGrade> selectMainLevelGradeList(AssessTaskProcessStatisticsVo.MainLevelGrade grade) {
        return statisticsMapper.selectMainLevelGradeList(grade);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.Grade> selectRscOpinionList(AssessTaskProcessStatisticsVo.Grade grade) {
        return statisticsMapper.selectRscOpinionList(grade);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.Grade> selectDzhOpinionList(AssessTaskProcessStatisticsVo.Grade grade) {
        return statisticsMapper.selectDzhOpinionList(grade);
    }

    @Override
    public List<AssessTaskProcessStatisticsVo.Promulgate> selectPromulgateList(AssessTaskProcessStatisticsVo.Promulgate promulgate) {
        return statisticsMapper.selectPromulgateList(promulgate);
    }
}
