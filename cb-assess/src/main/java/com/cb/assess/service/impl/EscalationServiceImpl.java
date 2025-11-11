package com.cb.assess.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cb.assess.domain.*;
import com.cb.assess.domain.vo.EscalationVo;
import com.cb.assess.enums.Grade;
import com.cb.assess.enums.PersonnelType;
import com.cb.assess.enums.RatGroup;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.*;
import com.cb.assess.service.EscalationService;
import com.cb.assess.service.IBizAssessEvaluationGradeSaveMarkService;
import com.cb.assess.service.IBizAssessEvaluationGradeService;
import com.cb.assess.utils.CycleUtil;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EscalationServiceImpl implements EscalationService {
    @Autowired
    private EscalationMapper escalationMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private BizAssessIndicatorProRatGroupMapper ratGroupMapper;

    @Autowired
    private IBizAssessEvaluationGradeService evaluationGradeService;

    @Autowired
    private IBizAssessEvaluationGradeSaveMarkService gradeSaveMarkService;
    @Autowired
    private BizAssessEvaluationGradeMapper bizAssessEvaluationGradeMapper;

    @Autowired
    private BizAssessEvaluationGradeSaveMarkMapper saveMarkMapper;
    @Autowired
    private BizAssessRegularInfoMapper regularInfoMapper;

    @Override
    public List<EscalationVo> selectEscalationList(EscalationVo vo) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<EscalationVo> list = escalationMapper.selectEscalationList(vo, user.getDeptId());
        this.buildEscalationVo(list);
        return list;
    }

    @Override
    public List<EscalationVo> selectExamineEscalationList(EscalationVo vo) {
        List<EscalationVo> list = escalationMapper.selectExamineEscalationList(vo,null);
        this.buildEscalationVo(list);
        return list;
    }

    private void buildEscalationVo(List<EscalationVo> list) {
        list.stream().forEach(item -> {
            String special = item.getSpecial();
            if(Special.NORMAL.getCode().equals(special)){
                String cycle = item.getCycle();
                String cycleDesc = item.getCycleDesc();
                if(StringUtils.isNotBlank(cycle) || StringUtils.isNotBlank(cycleDesc)){
                    String title = String.format("【%s】%s", CycleUtil.parseCycle(cycle, cycleDesc), Special.ofCode(special).getDesc());
                    item.setTitle(title);
                }
            }
        });
    }
    @Override
    public List<EscalationVo.ReportedEvaluate> selectCensusList(EscalationVo.Voter voter) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));

        List<EscalationVo.Voter> voters = escalationMapper.selectVoterList(voter, deptId);
        // 收集使用的方案分值配置权重信息
        List<String> proIds = voters.stream().map(EscalationVo.Voter::getProId).distinct().collect(Collectors.toList());
        if (CollectionUtil.isEmpty(proIds)) {
            return CollectionUtil.newArrayList();
        }
        List<BizAssessIndicatorProRatGroup> ratGroups = ratGroupMapper.selectListByProIds(proIds);
        // 转成键值对方便获取值
        Map<String, List<BizAssessIndicatorProRatGroup>> ratGroupMap = ratGroups.stream().collect(Collectors.groupingBy(BizAssessIndicatorProRatGroup::getProId));

        Map<Long, List<EscalationVo.Voter>> map = voters.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).collect(Collectors.groupingBy(EscalationVo.Voter::getUserId, LinkedHashMap::new, Collectors.toList()));

        Iterator<Map.Entry<Long, List<EscalationVo.Voter>>> iterator = map.entrySet().iterator();

        List<EscalationVo.ReportedEvaluate> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<EscalationVo.Voter>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            EscalationVo.ReportedEvaluate reportedEvaluate = new EscalationVo.ReportedEvaluate();
            reportedEvaluate.setUserId(key);
            reportedEvaluate.setName(name);
            reportedEvaluate.setDeptId(userMap.get(key).getDept().getDeptId());
            reportedEvaluate.setDeptName(userMap.get(key).getDept().getDeptName());
            List<EscalationVo.Voter> value = entry.getValue();

            String proId = value.stream().map(EscalationVo.Voter::getProId).distinct().findFirst().orElseThrow(() -> new NullPointerException("收集ProId异常"));
            List<BizAssessIndicatorProRatGroup> groups = ratGroupMap.get(proId);
            List<String> groupNames = groups.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
            Map<String, Integer> groupMap = groups.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
            List<EscalationVo.VoteScore> calculation = calculation(voter.getSpecial(), groupNames, groupMap, value);

            String taskId = calculation.stream().map(EscalationVo.VoteScore::getTaskId).distinct().findFirst().orElseThrow(() -> new NullPointerException("收集TaskId异常"));
            reportedEvaluate.setTaskId(taskId);
            String personnelType = calculation.stream().map(EscalationVo.VoteScore::getPersonnelType).distinct().findFirst().orElseThrow(() -> new NullPointerException("收集PersonnelType异常"));
            reportedEvaluate.setPersonnelType(personnelType);
            calculation.forEach(item->{
                String voteType = item.getVoteType();
                BigDecimal weightedScore = item.getWeightedScore().setScale(2, BigDecimal.ROUND_DOWN);
                if (RatGroup.RATE_EACH.getCode().equals(voteType)) {
                    reportedEvaluate.setDeptScore(weightedScore);
                }
                if (RatGroup.RATE_DIRECTOR.getCode().equals(voteType)) {
                    reportedEvaluate.setLeaderScore(reportedEvaluate.getLeaderScore().add(weightedScore));
                }
                if (RatGroup.RATE_CHARGE_LEADER.getCode().equals(voteType)) {
                    reportedEvaluate.setLeaderScore(reportedEvaluate.getLeaderScore().add(weightedScore));
                }
                if (RatGroup.RATE_MAIN_LEADER.getCode().equals(voteType)) {
                    reportedEvaluate.setLeaderScore(reportedEvaluate.getLeaderScore().add(weightedScore));
                }
                if (RatGroup.RATE_SPECIAL.getCode().equals(voteType)) {
                    reportedEvaluate.setUnusualScore(weightedScore);
                }
            });
            BigDecimal evaluationScore = calculation.stream().map(EscalationVo.VoteScore::getWeightedScore).reduce(BigDecimal.ZERO, BigDecimal::add);
            reportedEvaluate.setEvaluationScore(evaluationScore.setScale(2, BigDecimal.ROUND_DOWN));
            resultList.add(reportedEvaluate);
        }

        List<EscalationVo.ReportedEvaluate> collect = resultList.stream()
                .sorted(Comparator.comparing(EscalationVo.ReportedEvaluate::getEvaluationScore).reversed()).collect(Collectors.toList());
        List<String> taskIds = collect.stream().map(EscalationVo.ReportedEvaluate::getTaskId).distinct().collect(Collectors.toList());
        List<VRegularFillInfo> fillInfos = regularInfoMapper.selectVRegularFillInfoListByTskIds(taskIds);
        Stream.iterate(0, i -> i + 1).limit(collect.size()).forEach(i -> {
            EscalationVo.ReportedEvaluate item = collect.get(i);
            String personnelType = item.getPersonnelType();
            item.setRank(i + 1); // 设置排名
            if (PersonnelType.CIVIL_SERVANT_GENERAL.getCode().equals(personnelType)
                    || PersonnelType.INSTITUTION_GENERAL.getCode().equals(personnelType)) {
//                BigDecimal finalScore = item.getEvaluationScore();
//                String grade = Grade.getGradeDescByCode(finalScore.intValue());
//                item.setGrade(grade);
                // TODO 考核上报功能，联络员上报“民主评价得分计建议等次等次表”建议评定等次默认为“较好”。不用和评价得分进行关联。
                item.setGrade(Grade.BETTER.getDesc());
            } else {
                item.setGrade(null);
            }
            isEvaluationCompleted(voters, item);
            isSummarizeCompleted(fillInfos, item);
        });
        return collect;
    }

    @Override
    public List<EscalationVo.ReportedEvaluate> selectVoterListByKeys(String special, List<String> taskIds, List<Long> userIds) {
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));

        List<EscalationVo.Voter> voters = escalationMapper.selectVoterListByKeys(taskIds, userIds);
        // 收集使用的方案分值配置权重信息
        List<String> proIds = voters.stream().map(EscalationVo.Voter::getProId).distinct().collect(Collectors.toList());
        if (CollectionUtil.isEmpty(proIds)) {
            return CollectionUtil.newArrayList();
        }
        List<BizAssessIndicatorProRatGroup> ratGroups = ratGroupMapper.selectListByProIds(proIds);
        // 转成键值对方便获取值
        Map<String, List<BizAssessIndicatorProRatGroup>> ratGroupMap = ratGroups.stream().collect(Collectors.groupingBy(BizAssessIndicatorProRatGroup::getProId));

        Map<Long, List<EscalationVo.Voter>> map = voters.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).collect(Collectors.groupingBy(EscalationVo.Voter::getUserId, LinkedHashMap::new, Collectors.toList()));

        Iterator<Map.Entry<Long, List<EscalationVo.Voter>>> iterator = map.entrySet().iterator();

        List<EscalationVo.ReportedEvaluate> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<EscalationVo.Voter>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            EscalationVo.ReportedEvaluate reportedEvaluate = new EscalationVo.ReportedEvaluate();
            reportedEvaluate.setUserId(key);
            reportedEvaluate.setName(name);
            reportedEvaluate.setDeptId(userMap.get(key).getDept().getDeptId());
            reportedEvaluate.setDeptName(userMap.get(key).getDept().getDeptName());
            List<EscalationVo.Voter> value = entry.getValue();

            String proId = value.stream().map(EscalationVo.Voter::getProId).distinct().findFirst().orElseThrow(() -> new NullPointerException("收集ProId异常"));
            List<BizAssessIndicatorProRatGroup> groups = ratGroupMap.get(proId);
            List<String> groupNames = groups.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
            Map<String, Integer> groupMap = groups.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
            List<EscalationVo.VoteScore> calculation = calculation(special, groupNames, groupMap, value);

            String taskId = calculation.stream().map(EscalationVo.VoteScore::getTaskId).distinct().findFirst().orElseThrow(() -> new NullPointerException("收集TaskId异常"));
            reportedEvaluate.setTaskId(taskId);
            String personnelType = calculation.stream().map(EscalationVo.VoteScore::getPersonnelType).distinct().findFirst().orElseThrow(() -> new NullPointerException("收集PersonnelType异常"));
            reportedEvaluate.setPersonnelType(personnelType);
            calculation.forEach(item->{
                String voteType = item.getVoteType();
                BigDecimal weightedScore = item.getWeightedScore().setScale(2, BigDecimal.ROUND_DOWN);
                if (RatGroup.RATE_EACH.getCode().equals(voteType)) {
                    reportedEvaluate.setDeptScore(weightedScore);
                }
                if (RatGroup.RATE_DIRECTOR.getCode().equals(voteType)) {
                    reportedEvaluate.setLeaderScore(reportedEvaluate.getLeaderScore().add(weightedScore));
                }
                if (RatGroup.RATE_CHARGE_LEADER.getCode().equals(voteType)) {
                    reportedEvaluate.setLeaderScore(reportedEvaluate.getLeaderScore().add(weightedScore));
                }
                if (RatGroup.RATE_MAIN_LEADER.getCode().equals(voteType)) {
                    reportedEvaluate.setLeaderScore(reportedEvaluate.getLeaderScore().add(weightedScore));
                }
                if (RatGroup.RATE_SPECIAL.getCode().equals(voteType)) {
                    reportedEvaluate.setUnusualScore(weightedScore);
                }
            });
            BigDecimal evaluationScore = calculation.stream().map(EscalationVo.VoteScore::getWeightedScore).reduce(BigDecimal.ZERO, BigDecimal::add);
            reportedEvaluate.setEvaluationScore(evaluationScore.setScale(2, BigDecimal.ROUND_DOWN));
            resultList.add(reportedEvaluate);
        }

        return resultList.stream()
                .sorted(Comparator.comparing(EscalationVo.ReportedEvaluate::getEvaluationScore).reversed())
                .collect(Collectors.toList());
    }

    /**
     * 处理人员是否评价以及评分情况
     * @param voters
     * @param item
     */
    private void isEvaluationCompleted(List<EscalationVo.Voter> voters, EscalationVo.ReportedEvaluate item) {
        List<EscalationVo.Voter> collect = voters.stream().filter(voter -> item.getUserId().equals(voter.getUserId())).collect(Collectors.toList());
        item.setVoters(collect);
        long count = collect.stream().filter(voter -> "0".equals(voter.getStatus())).count();
        Boolean completed = count == 0 ? true : false;
        item.setEvaluationCompleted(completed);
    }

    /**
     * 判断是否填报了个人总结以及状态等
     * @param infos
     * @param item
     */
    private void isSummarizeCompleted(List<VRegularFillInfo> infos, EscalationVo.ReportedEvaluate item) {
        String userName = item.getName();
        Long userId = item.getUserId();
        List<VRegularFillInfo> list = infos.stream().filter(info -> userId.equals(info.getUserId())).collect(Collectors.toList());
        long mustFillCount = list.stream().count();
        if (mustFillCount > 0) {
            item.setMustFillSummarize(true);
            VRegularFillInfo fillInfo = list.stream().findFirst().orElseThrow(() -> new NullPointerException("未匹配到【"+userName+"】个人总结"));
            String status = fillInfo.getStatus();
            Integer step = fillInfo.getStep();
            item.setSummarizeStep(step);
            if ("1".equals(status)) {
                item.setSummarizeStep(0);
                if("1".equals(fillInfo.getAuditStatus())){
                    item.setSummarizeStep(1);
                    item.setSummarizeCompleted(true);
                }
            }
            if(Integer.valueOf(2).equals(step)){
                item.setSummarizeStep(2);
            }

        }
    }


    private List<EscalationVo.VoteScore> calculation(String special, List<String> ratGroupNames, Map<String, Integer> ratGroupMap,
                                                     List<EscalationVo.Voter> list) {
        String taskId = list.stream().map(EscalationVo.Voter::getTaskId).distinct().findFirst().orElse(null);
        String personnelType = null;
        if (Special.NORMAL.getCode().equals(special)) {
            personnelType = list.stream().map(EscalationVo.Voter::getPersonnelType).distinct().findFirst().orElse(null);
        } else {
            personnelType = "4";
        }
        List<EscalationVo.VoteScore> voteScoreList = new ArrayList<>();
        if (Special.NORMAL.getCode().equals(special)) {
            // 民主测评
            if (ratGroupNames.contains(RatGroup.RATE_EACH.getCode())) {
                List<EscalationVo.Voter> democraticVoters = list.stream().filter(item -> RatGroup.RATE_EACH.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = democraticVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> democraticMap = democraticVoters.stream().collect(Collectors.groupingBy(EscalationVo.Voter::getUserId,
                        Collectors.mapping(EscalationVo.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_EACH.getCode()) / 100F;
                List<EscalationVo.VoteScore> voteScores = settingValues(taskId, personnelType, democraticVoters, count, democraticMap, weight, RatGroup.RATE_EACH);
                voteScoreList.addAll(voteScores);
            }

            if (ratGroupNames.contains(RatGroup.RATE_DIRECTOR.getCode())) {
                // 主要负责人评价
                List<EscalationVo.Voter> deptChargeVoters = list.stream().filter(item -> RatGroup.RATE_DIRECTOR.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = deptChargeVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> deptChargeMap = deptChargeVoters.stream().collect(Collectors.groupingBy(EscalationVo.Voter::getUserId,
                        Collectors.mapping(EscalationVo.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_DIRECTOR.getCode()) / 100F;
                List<EscalationVo.VoteScore> voteScores = settingValues(taskId, personnelType, deptChargeVoters, count, deptChargeMap, weight, RatGroup.RATE_DIRECTOR);
                voteScoreList.addAll(voteScores);
            }
            if (ratGroupNames.contains(RatGroup.RATE_CHARGE_LEADER.getCode())) {
                // 分管领导评分
                List<EscalationVo.Voter> chargeLeaderVoters = list.stream().filter(item -> RatGroup.RATE_CHARGE_LEADER.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = chargeLeaderVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> chargeLeaderMap = chargeLeaderVoters.stream().collect(Collectors.groupingBy(EscalationVo.Voter::getUserId,
                        Collectors.mapping(EscalationVo.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_CHARGE_LEADER.getCode()) / 100F;
                List<EscalationVo.VoteScore> voteScores = settingValues(taskId, personnelType, chargeLeaderVoters, count, chargeLeaderMap, weight, RatGroup.RATE_CHARGE_LEADER);
                voteScoreList.addAll(voteScores);
            }

            if (ratGroupNames.contains(RatGroup.RATE_MAIN_LEADER.getCode())) {
                // 局主要领导评分
                List<EscalationVo.Voter> mainLeaderVoters = list.stream().filter(item -> RatGroup.RATE_MAIN_LEADER.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = mainLeaderVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> mainLeaderMap = mainLeaderVoters.stream().collect(Collectors.groupingBy(EscalationVo.Voter::getUserId,
                        Collectors.mapping(EscalationVo.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_MAIN_LEADER.getCode()) / 100F;
                List<EscalationVo.VoteScore> voteScores = settingValues(taskId, personnelType, mainLeaderVoters, count, mainLeaderMap, weight, RatGroup.RATE_MAIN_LEADER);
                voteScoreList.addAll(voteScores);
            }
        } else {
            if (ratGroupNames.contains(RatGroup.RATE_SPECIAL.getCode())) {
                // 专项考核
                List<EscalationVo.Voter> specialVoters = list.stream().filter(item -> RatGroup.RATE_SPECIAL.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = specialVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> specialMap = specialVoters.stream().collect(Collectors.groupingBy(EscalationVo.Voter::getUserId,
                        Collectors.mapping(EscalationVo.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_SPECIAL.getCode()) / 100F;
                List<EscalationVo.VoteScore> voteScores = settingValues(taskId, personnelType, specialVoters, count, specialMap, weight, RatGroup.RATE_SPECIAL);
                voteScoreList.addAll(voteScores);
            }
        }
        return voteScoreList;
    }

    private List<EscalationVo.VoteScore> settingValues(String taskId, String personnelType, List<EscalationVo.Voter> source, Long count,
                                                       Map<Long, BigDecimal> calculationMap, Float weight, RatGroup group) {

        Iterator<Map.Entry<Long, BigDecimal>> iterator = calculationMap.entrySet().iterator();
        List<EscalationVo.VoteScore> voteScores = new ArrayList<>();
        if (source.size() > 0) {
            while (iterator.hasNext()) {
                Map.Entry<Long, BigDecimal> entry = iterator.next();
                BigDecimal value = entry.getValue();
                EscalationVo.VoteScore voteScore = new EscalationVo.VoteScore();
                voteScore.setTaskId(taskId);
                voteScore.setDescribe(group.getDesc());
                voteScore.setVoteType(group.getCode());
                voteScore.setWeight(weight);
                voteScore.setTotalScore(value);
                if (count == 0) {
                    voteScore.setWeightedScore(BigDecimal.ZERO);
                    voteScore.setAverageScore(BigDecimal.ZERO);
                } else {
                    BigDecimal average = value.divide(BigDecimal.valueOf(count), BigDecimal.ROUND_CEILING);

                    voteScore.setAverageScore(average);
                    BigDecimal weightedScore = value.multiply(BigDecimal.valueOf(weight))
                            .divide(BigDecimal.valueOf(count), BigDecimal.ROUND_CEILING);
                    voteScore.setWeightedScore(weightedScore);
                }
                voteScore.setVoterList(source);
                voteScore.setPersonnelType(personnelType);
                voteScores.add(voteScore);
            }
        } else {
            // 防止source的size 为0 时，获取不到数据，导致前端显示异常
            EscalationVo.VoteScore voteScore = new EscalationVo.VoteScore();
            voteScore.setTaskId(taskId);
            voteScore.setDescribe(group.getDesc());
            voteScore.setVoteType(group.getCode());
            voteScore.setWeight(weight);
            voteScore.setTotalScore(BigDecimal.ZERO);
            voteScore.setWeightedScore(BigDecimal.ZERO);
            voteScore.setAverageScore(BigDecimal.ZERO);
            voteScore.setVoterList(source);
            voteScore.setPersonnelType(personnelType);
            voteScores.add(voteScore);
        }
        return voteScores;
    }

    @Override
    public Boolean checkReported(EscalationVo.EvaluateCensus evaluateCensus) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        return escalationMapper.checkReported(evaluateCensus, user.getDeptId());
    }

    @Override
    public int saveEvaluateCensus(EscalationVo.EvaluateCensus evaluateCensus) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        List<BizAssessEvaluationGrade> evaluationGrades = evaluateCensus.getEvaluationGrades();
        if (CollectionUtil.isEmpty(evaluationGrades)) throw new CustomException("没有任何上报数据!");
        List<String> taskIds = evaluationGrades.stream().map(BizAssessEvaluationGrade::getTaskId).distinct().collect(Collectors.toList());
        List<Long> userIds = evaluationGrades.stream().map(BizAssessEvaluationGrade::getUserId).collect(Collectors.toList());
        List<VEvaluationGrade.Voter> mirrors = CollectionUtil.newArrayList();
        if (CollectionUtil.isNotEmpty(taskIds) && CollectionUtil.isNotEmpty(userIds)) {
            mirrors.addAll(escalationMapper.selectVEvaluateScoreList(taskIds, userIds));
        }
        BizAssessEvaluationGradeSaveMark mak = new BizAssessEvaluationGradeSaveMark();
        mak.setDeptId(deptId);
        mak.setTaskId(evaluateCensus.getTaskId());
        mak.setYear(evaluateCensus.getYear());
        mak.setCycleDesc(evaluateCensus.getCycleDesc());
        BizAssessEvaluationGradeSaveMark one = gradeSaveMarkService.selectOne(mak);

        String uuid = null;
        // 需处理保存标记
        if (StringUtils.isNotNull(one)) {
            uuid = one.getId();
            one.setTaskId(evaluateCensus.getTaskId());
            one.setEscalationFlag(evaluateCensus.getEscalationFlag());
            gradeSaveMarkService.updateBizAssessEvaluationGradeSaveMark(one);
        } else {
            uuid = IdUtils.randomUUID();
            mak.setId(uuid);
            mak.setTaskId(evaluateCensus.getTaskId());
            mak.setEscalationFlag(evaluateCensus.getEscalationFlag());
            gradeSaveMarkService.insertBizAssessEvaluationGradeSaveMark(mak);
        }
        Map<Long, List<VEvaluationGrade.Voter>> mirrorsMap = mirrors.stream()
                .collect(Collectors.groupingBy(VEvaluationGrade.Voter::getUserId));

        for (BizAssessEvaluationGrade evaluationGrade : evaluationGrades) {
            evaluationGrade.setEscalationId(uuid);
            Long userId = evaluationGrade.getUserId();
            if (CollectionUtil.isNotEmpty(mirrors)) {
                List<VEvaluationGrade.Voter> voters = mirrorsMap.get(userId);
                String mirror = JSON.toJSONString(voters);
                evaluationGrade.setScoringMirror(mirror);
            } else {
                evaluationGrade.setScoringMirror(null);
            }
            if (StringUtils.isNotBlank(evaluationGrade.getId())) {
                evaluationGradeService.updateBizAssessEvaluationGrade(evaluationGrade);
            } else {
                evaluationGradeService.insertBizAssessEvaluationGrade(evaluationGrade);
            }
        }
        return 1;
    }

    @Override
    public List<EscalationVo.ReportedEvaluate> selectReportedList(EscalationVo.ReportedEvaluate reported) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        reported.setDeptId(deptId);
        List<EscalationVo.ReportedEvaluate> list = escalationMapper.selectReportedList(reported);
        if (CollectionUtil.isNotEmpty(list)) {
            List<SysUser> users = userMapper.selectAll();
            Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
            List<String> taskIds = list.stream().map(EscalationVo.ReportedEvaluate::getTaskId).collect(Collectors.toList());
            List<VRegularFillInfo> fillInfos = regularInfoMapper.selectVRegularFillInfoListByTskIds(taskIds);
            list.forEach(item -> {
                List<EscalationVo.Voter> voters = selectJSON2VoterList(item, userMap);
                item.setVoters(voters);
                isEvaluationCompleted(voters, item);
                isSummarizeCompleted(fillInfos, item);
            });
        }
        return list;
    }

    /**
     * 获取评分数据
     * @param reported
     * @param map
     * @return
     */
    private List<EscalationVo.Voter> selectJSON2VoterList(EscalationVo.ReportedEvaluate reported,Map<Long, SysUser> map){
        String scoringMirror = reported.getScoringMirror();
        JSONArray jsonArray = JSON.parseArray(scoringMirror);
        List<EscalationVo.Voter> voters = jsonArray.toJavaList(EscalationVo.Voter.class);

        return voters.stream().map(o->{
            Long voter = o.getVoter();
            SysUser user = map.get(voter);
            o.setVoterName(user.getName());
            return o;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BizAssessEvaluationGradeSaveMark> selectEvaluationGradeSaveMark(EscalationVo.EvaluateCensus evaluateCensus) {
        return escalationMapper.selectEvaluationGradeSaveMark(evaluateCensus);
    }

    @Override
    public List<BizAssessEvaluationGrade> selectBizAssessEvaluationGrade(String escalationId) {
        return bizAssessEvaluationGradeMapper.selectAssessEvaluationGradeList(escalationId);
    }

    @Override
    public int evaluationGradeAudit(BizAssessEvaluationGradeSaveMark mark) {
        mark.setUpdateBy(SecurityUtils.getUsername());
        mark.setUpdateTime(DateUtils.getNowDate());
        return saveMarkMapper.updateBizAssessEvaluationGradeSaveMark(mark);
    }

}
