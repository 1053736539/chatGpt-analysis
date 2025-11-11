package com.cb.assess.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cb.assess.domain.*;
import com.cb.assess.domain.vo.BizAssessTaskVo;
import com.cb.assess.domain.vo.EvaluationGradeVo;
import com.cb.assess.enums.Grade;
import com.cb.assess.enums.PersonnelType;
import com.cb.assess.enums.RatGroup;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.*;
import com.cb.assess.service.VEvaluateScoreService;
import com.cb.assess.utils.CycleUtil;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.easyexcel.TemplateExcelExpUtil;
import com.cb.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VEvaluateScoreServiceImpl implements VEvaluateScoreService {
    @Autowired
    private VEvaluateScoreMapper evaluationScoreMapper;
    @Autowired
    private BizAssessTaskManageMapper taskManageMapper;
    @Autowired
    private BizAssessIndicatorProRatGroupMapper ratGroupMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private VEvaluationGradeMapper evaluationGradeMapper;

    @Override
    public List<BizAssessTaskVo> selectTaskList(BizAssessTaskVo vo) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        Set<BizAssessTaskVo> set = new LinkedHashSet<>();
        // 部门负责人
        if (SecurityUtils.hasForceRole("dept_master_leader")) {
            vo.setType("_other");
            // return evaluationScoreMapper.selectTaskList(vo, deptId);
            List<BizAssessTaskVo> deptMasters = evaluationScoreMapper.selectTaskList(vo, deptId);
            set.addAll(deptMasters);
        }
        //机关党委（人事处）或者系统管理员
        if (SecurityUtils.hasForceRole("organ") || SecurityUtils.hasForceRole("admin")) {
            //return evaluationScoreMapper.selectTaskList(vo, null);
            List<BizAssessTaskVo> alls = evaluationScoreMapper.selectTaskList(vo, null);
            set.addAll(alls);
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<VEvaluateScore> selectEvaluateScoreList(String taskId) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
        // 获取任务和相关的方案信息
        BizAssessTaskManage task = taskManageMapper.selectBizAssessTaskManageById(taskId);
        String proId = task.getProId();
        // 获取考核评分权重
        List<BizAssessIndicatorProRatGroup> ratGroupList = ratGroupMapper.selectListByProId(proId);
        Map<String, Integer> ratGroupMap = ratGroupList.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
        List<String> ratGroupNames = ratGroupList.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
        // 获取考核打分信息
        List<VEvaluateScore.VoterDetail> list = this.selectEvaluateVoterList(taskId, deptId);
        Map<Long, List<VEvaluateScore.VoterDetail>> map = list.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId));

        Iterator<Map.Entry<Long, List<VEvaluateScore.VoterDetail>>> iterator = map.entrySet().iterator();
        List<VEvaluateScore> vEvaluateScoreList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<VEvaluateScore.VoterDetail>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            VEvaluateScore vEvaluateScore = new VEvaluateScore();
            vEvaluateScore.setUserId(key);
            vEvaluateScore.setDeptName(userMap.get(key).getDept().getDeptName());
            vEvaluateScore.setName(name);
            List<VEvaluateScore.VoterDetail> value = entry.getValue();
            List<VEvaluateScore.VoteScore> calculation = calculation(task, ratGroupNames, ratGroupMap, value);
            BigDecimal finaScore = calculation.stream().map(VEvaluateScore.VoteScore::getWeightedScore).reduce(BigDecimal.ZERO, BigDecimal::add);
            vEvaluateScore.setFinalScore(finaScore);
            vEvaluateScore.setVoteScoreList(calculation);
            vEvaluateScoreList.add(vEvaluateScore);
        }

        return vEvaluateScoreList;
    }

    @Override
    public List<EvaluationGradeVo> selectPreviewEvaluateScore(EvaluationGradeVo vo) {
        String taskId = vo.getTaskId();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
        // 获取任务和相关的方案信息
        BizAssessTaskManage task = taskManageMapper.selectBizAssessTaskManageById(taskId);
        String proId = task.getProId();
        // 获取考核评分权重
        List<BizAssessIndicatorProRatGroup> ratGroupList = ratGroupMapper.selectListByProId(proId);
        Map<String, Integer> ratGroupMap = ratGroupList.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
        List<String> ratGroupNames = ratGroupList.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
        // 获取考核打分信息
        List<VEvaluateScore.VoterDetail> list = this.selectEvaluateVoterList(taskId, deptId);
        Map<Long, List<VEvaluateScore.VoterDetail>> map = list.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId));

        Iterator<Map.Entry<Long, List<VEvaluateScore.VoterDetail>>> iterator = map.entrySet().iterator();
        List<EvaluationGradeVo> gradeVoList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<VEvaluateScore.VoterDetail>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            EvaluationGradeVo gradeVo = new EvaluationGradeVo();
            gradeVo.setTaskId(taskId);
            gradeVo.setUserId(key);
            gradeVo.setDeptName(userMap.get(key).getDept().getDeptName());
            gradeVo.setName(name);
            List<VEvaluateScore.VoterDetail> value = entry.getValue();
            List<VEvaluateScore.VoteScore> calculation = calculation(task, ratGroupNames, ratGroupMap, value);
            BigDecimal finaScore = calculation.stream().map(VEvaluateScore.VoteScore::getWeightedScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
            gradeVo.setEvaluationScore(finaScore);
            String gradeDescByCode = Grade.getGradeDescByCode(finaScore.intValue());
            gradeVo.setGrade(gradeDescByCode);
            gradeVoList.add(gradeVo);
        }
        return gradeVoList;
    }

    @Override
    public List<Map<String, Object>> selectAggregateEvaluate(String taskId) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
        // 获取任务和相关的方案信息
        BizAssessTaskManage task = taskManageMapper.selectBizAssessTaskManageById(taskId);
        String proId = task.getProId();
        // 获取考核评分权重
        List<BizAssessIndicatorProRatGroup> ratGroupList = ratGroupMapper.selectListByProId(proId);
        Map<String, Integer> ratGroupMap = ratGroupList.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
        List<String> ratGroupNames = ratGroupList.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
        // 获取考核打分信息
        List<VEvaluateScore.VoterDetail> list = this.selectEvaluateVoterList(taskId, deptId);
        Map<Long, List<VEvaluateScore.VoterDetail>> map = list.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId));

        Iterator<Map.Entry<Long, List<VEvaluateScore.VoterDetail>>> iterator = map.entrySet().iterator();

        String special = task.getSpecial();
        String personnelType = task.getPro().getPersonnelType();
        List<Map<String, Object>> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<VEvaluateScore.VoterDetail>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            Map<String, Object> resultMap = new LinkedHashMap<>(); // 有序链表
            resultMap.put("处室、部门", userMap.get(key).getDept().getDeptName());
            resultMap.put("姓名", name);
            List<VEvaluateScore.VoterDetail> value = entry.getValue();
            List<VEvaluateScore.VoteScore> calculation = calculation(task, ratGroupNames, ratGroupMap, value);
            calculation.stream().forEach(item -> {
                // 非民主测评才塞入数据
                if (!RatGroup.RATE_EACH.getCode().equals(item.getVoteType())) {
                    item.getVoterDetailList().stream().forEach(d -> {
                        resultMap.put(d.getVoterName(), d.getScore().setScale(2, BigDecimal.ROUND_DOWN));
                    });
                }
                resultMap.put(item.getDescribe(), item.getAverageScore().setScale(2, BigDecimal.ROUND_DOWN));
                Float v = item.getWeight() * 100;
                resultMap.put(item.getDescribe() + "(" + v.intValue() + "%)", item.getWeightedScore().setScale(2, BigDecimal.ROUND_DOWN));
            });
            BigDecimal finaScore = calculation.stream().map(VEvaluateScore.VoteScore::getWeightedScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
            resultMap.put("得分", finaScore);
            if (Special.NORMAL.getCode().equals(special) && personnelType.indexOf("_other") > -1) {
                String gradeDescByCode = Grade.getGradeDescByCode(finaScore.intValue());
                resultMap.put("建议等次", gradeDescByCode);
            }
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public List<VEvaluateScore.Comprehensive> selectComprehensiveEvaluate(String taskId) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
        // 获取任务和相关的方案信息
        BizAssessTaskManage task = taskManageMapper.selectBizAssessTaskManageById(taskId);
        String proId = task.getProId();
        // 获取考核评分权重
        List<BizAssessIndicatorProRatGroup> ratGroupList = ratGroupMapper.selectListByProId(proId);
        Map<String, Integer> ratGroupMap = ratGroupList.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
        List<String> ratGroupNames = ratGroupList.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
        // 获取考核打分信息
        List<VEvaluateScore.VoterDetail> list = this.selectEvaluateVoterList(taskId, deptId);
        Map<Long, List<VEvaluateScore.VoterDetail>> map = list.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId));

        Iterator<Map.Entry<Long, List<VEvaluateScore.VoterDetail>>> iterator = map.entrySet().iterator();

        List<VEvaluateScore.Comprehensive> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<VEvaluateScore.VoterDetail>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            VEvaluateScore.Comprehensive comprehensive = new VEvaluateScore.Comprehensive();
            comprehensive.setName(name);
            comprehensive.setDeptName(userMap.get(key).getDept().getDeptName());
            List<VEvaluateScore.VoterDetail> value = entry.getValue();
            List<VEvaluateScore.VoteScore> calculation = calculation(task, ratGroupNames, ratGroupMap, value);
            calculation.stream().forEach(item -> {
                String voteType = item.getVoteType();
                BigDecimal weightedScore = item.getWeightedScore().setScale(2, BigDecimal.ROUND_DOWN);
                if (RatGroup.RATE_EACH.getCode().equals(voteType)) {
                    comprehensive.setRateEach(weightedScore);
                }
                if (RatGroup.RATE_DIRECTOR.getCode().equals(voteType)) {
                    comprehensive.setRateDirector(weightedScore);
                }
                if (RatGroup.RATE_CHARGE_LEADER.getCode().equals(voteType)) {
                    comprehensive.setRateChargeLeader(weightedScore);
                }
                if (RatGroup.RATE_MAIN_LEADER.getCode().equals(voteType)) {
                    comprehensive.setRateMainLeader(weightedScore);
                }
                if (RatGroup.RATE_SPECIAL.getCode().equals(voteType)) {
                    comprehensive.setRateSpecial(weightedScore);
                }
            });
            BigDecimal subtotalScore = comprehensive.getRateEach().add(comprehensive.getRateDirector()).add(comprehensive.getRateChargeLeader())
                    .add(comprehensive.getRateMainLeader()).add(comprehensive.getRateSpecial()).setScale(2, BigDecimal.ROUND_DOWN);
            comprehensive.setSubtotalScore(subtotalScore);
            BigDecimal comprehensiveScore = calculation.stream().map(VEvaluateScore.VoteScore::getWeightedScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
            comprehensive.setComprehensiveScore(comprehensiveScore);
            resultList.add(comprehensive);
        }
        List<VEvaluateScore.Comprehensive> collect = resultList.stream()
                .sorted(Comparator.comparing(VEvaluateScore.Comprehensive::getComprehensiveScore).reversed()).collect(Collectors.toList());
        Stream.iterate(0, i -> i + 1).limit(collect.size()).forEach(i -> {
            VEvaluateScore.Comprehensive item = collect.get(i);
            item.setRank(i + 1); // 设置排名
        });
        return collect;
    }

    private List<VEvaluateScore.VoterDetail> selectEvaluateVoterList(String taskId, Long deptId) {
        LinkedHashSet<VEvaluateScore.VoterDetail> set = new LinkedHashSet<>();
        // 部门负责人
        if (SecurityUtils.hasForceRole("dept_master_leader")) {
            //return evaluationScoreMapper.selectEvaluateVoterList(taskId, deptId);
            List<VEvaluateScore.VoterDetail> deptMasters = evaluationScoreMapper.selectEvaluateVoterList(taskId, deptId);
            set.addAll(deptMasters);
        }
        //机关党委（人事处）或者系统管理员
        if (SecurityUtils.hasForceRole("organ") || SecurityUtils.hasForceRole("admin")) {
            //return evaluationScoreMapper.selectEvaluateVoterList(taskId, null);
            List<VEvaluateScore.VoterDetail> alls = evaluationScoreMapper.selectEvaluateVoterList(taskId, null);
            set.addAll(alls);
        }
        return new ArrayList<>(set);
    }

    private List<VEvaluateScore.VoteScore> calculation(BizAssessTaskManage task, List<String> ratGroupNames
            , Map<String, Integer> ratGroupMap, List<VEvaluateScore.VoterDetail> list) {
        List<VEvaluateScore.VoteScore> voteScoreList = new ArrayList<>();
        String special = task.getSpecial();

        if (Special.NORMAL.getCode().equals(special)) {
            // 民主测评
            if (ratGroupNames.contains(RatGroup.RATE_EACH.getCode())) {
                List<VEvaluateScore.VoterDetail> democraticVoters = list.stream().filter(item -> RatGroup.RATE_EACH.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = democraticVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());

                Map<Long, BigDecimal> democraticMap = democraticVoters.stream().collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId,
                        Collectors.mapping(VEvaluateScore.VoterDetail::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_EACH.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues(democraticVoters, count, democraticMap, weight, RatGroup.RATE_EACH);
                voteScoreList.addAll(voteScores);
            }

            if (ratGroupNames.contains(RatGroup.RATE_DIRECTOR.getCode())) {
                // 主要负责人评价
                List<VEvaluateScore.VoterDetail> deptChargeVoters = list.stream().filter(item -> RatGroup.RATE_DIRECTOR.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = deptChargeVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> deptChargeMap = deptChargeVoters.stream().collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId,
                        Collectors.mapping(VEvaluateScore.VoterDetail::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_DIRECTOR.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues(deptChargeVoters, count, deptChargeMap, weight, RatGroup.RATE_DIRECTOR);
                voteScoreList.addAll(voteScores);
            }
            if (ratGroupNames.contains(RatGroup.RATE_CHARGE_LEADER.getCode())) {
                // 分管领导评分
                List<VEvaluateScore.VoterDetail> chargeLeaderVoters = list.stream().filter(item -> RatGroup.RATE_CHARGE_LEADER.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = chargeLeaderVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> chargeLeaderMap = chargeLeaderVoters.stream().collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId,
                        Collectors.mapping(VEvaluateScore.VoterDetail::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_CHARGE_LEADER.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues(chargeLeaderVoters, count, chargeLeaderMap, weight, RatGroup.RATE_CHARGE_LEADER);
                voteScoreList.addAll(voteScores);
            }

            if (ratGroupNames.contains(RatGroup.RATE_MAIN_LEADER.getCode())) {
                // 局主要领导评分
                List<VEvaluateScore.VoterDetail> mainLeaderVoters = list.stream().filter(item -> RatGroup.RATE_MAIN_LEADER.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = mainLeaderVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> mainLeaderMap = mainLeaderVoters.stream().collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId,
                        Collectors.mapping(VEvaluateScore.VoterDetail::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

                Float weight = ratGroupMap.get(RatGroup.RATE_MAIN_LEADER.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues(mainLeaderVoters, count, mainLeaderMap, weight, RatGroup.RATE_MAIN_LEADER);
                voteScoreList.addAll(voteScores);
            }
        } else {
            if (ratGroupNames.contains(RatGroup.RATE_SPECIAL.getCode())) {
                // 专项考核
                List<VEvaluateScore.VoterDetail> specialVoters = list.stream().filter(item -> RatGroup.RATE_SPECIAL.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = specialVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> specialMap = specialVoters.stream().collect(Collectors.groupingBy(VEvaluateScore.VoterDetail::getUserId,
                        Collectors.mapping(VEvaluateScore.VoterDetail::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_SPECIAL.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues(specialVoters, count, specialMap, weight, RatGroup.RATE_SPECIAL);
                voteScoreList.addAll(voteScores);
            }
        }
        return voteScoreList;
    }

    /**
     * @param source         过滤后没有计算的源集合
     * @param count          获取已评数量
     * @param calculationMap 计算后的值
     * @param weight         打分权重
     * @param group          enum
     */
    private List<VEvaluateScore.VoteScore> settingValues(List<VEvaluateScore.VoterDetail> source, Long count,
                                                         Map<Long, BigDecimal> calculationMap, Float weight, RatGroup group) {

        Iterator<Map.Entry<Long, BigDecimal>> iterator = calculationMap.entrySet().iterator();
        List<VEvaluateScore.VoteScore> voteScores = new ArrayList<>();
        if (source.size() > 0) {
            while (iterator.hasNext()) {
                Map.Entry<Long, BigDecimal> entry = iterator.next();
                BigDecimal value = entry.getValue();
                VEvaluateScore.VoteScore voteScore = new VEvaluateScore.VoteScore();
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
                voteScore.setVoterDetailList(source);
                voteScores.add(voteScore);
            }
        } else {
            // 防止source的size 为0 时，获取不到数据，导致前端显示异常
            VEvaluateScore.VoteScore voteScore = new VEvaluateScore.VoteScore();
            voteScore.setDescribe(group.getDesc());
            voteScore.setVoteType(group.getCode());
            voteScore.setWeight(weight);
            voteScore.setTotalScore(BigDecimal.ZERO);
            voteScore.setWeightedScore(BigDecimal.ZERO);
            voteScore.setAverageScore(BigDecimal.ZERO);
            voteScore.setVoterDetailList(source);
            voteScores.add(voteScore);
        }
        return voteScores;
    }


    @Override
    public List<VEvaluationGrade> selectEscalationList(VEvaluationGrade evaluationGrade) {
        List<VEvaluationGrade> list = evaluationGradeMapper.selectVEvaluationGradeList(evaluationGrade);
        this.buildEvaluationGrades(list);
        return list;
    }
    private void buildEvaluationGrades(List<VEvaluationGrade> list) {
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
    public List<BizAssessTaskVo.EvaluateLevelTempResp> selectEvaluateLevelTempList(BizAssessTaskVo.EvaluateLevelTempReq req) {
        List<BizAssessTaskVo.EvaluateLevelTempResp> list = evaluationScoreMapper.selectEvaluateLevelTempList(req);
        List<BizAssessTaskVo.EvaluateLevelTempResp> collect = list.stream().map(item -> {
            item.setFinalScore(item.getDeptScore().add(item.getLeaderScore()).add(item.getUnusualScore()).add(item.getDisciplineScore()));
            return item;
        }).sorted(Comparator.comparing(BizAssessTaskVo.EvaluateLevelTempResp::getFinalScore).reversed()).collect(Collectors.toList());
        Stream.iterate(0, i -> i + 1).limit(collect.size()).forEach(i -> {
            BizAssessTaskVo.EvaluateLevelTempResp resp = collect.get(i);
            resp.setRank(i + 1); // 设置排名
        });
        return collect;
    }

    @Override
    public List<Map<String, Object>> selectTempEvaluateScoreList(BizAssessTaskVo.EvaluateLevelTempReq req) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
        // 获取考核打分信息
        List<BizAssessTaskVo.EvaluateLevelTempResp> evaluateLevelTempResps = evaluationScoreMapper.selectTempEvaluateScoreList(req);
        Map<Long, List<VEvaluationGrade.Voter>> evaluateLevelTempMap = evaluateLevelTempResps.stream()
                .collect(Collectors.toMap(BizAssessTaskVo.EvaluateLevelTempResp::getUserId,
                        o -> this.selectJSON2VoterList(o.getScoringMirror()))
                );
        Iterator<Map.Entry<Long, List<VEvaluationGrade.Voter>>> iterator = evaluateLevelTempMap.entrySet().iterator();
        List<Map<String, Object>> resultList = new ArrayList<>();
        String special = req.getSpecial();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<VEvaluationGrade.Voter>> entry = iterator.next();
            Long key = entry.getKey();
            String userName = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            Map<String, Object> resultMap = new LinkedHashMap<>(); // 有序链表
            resultMap.put("处室、部门", userMap.get(key).getDept().getDeptName());
            resultMap.put("姓名", userName);
            List<VEvaluationGrade.Voter> value = entry.getValue();

            String porId = value.stream().map(VEvaluationGrade.Voter::getProId).findFirst().orElse(null);
            if (StringUtils.isNull(porId)) {
                continue;
            }
            List<VEvaluationGrade.Voter> collect = value.stream().map(item -> {
                item.setVoterName(userMap.get(item.getVoter()).getName());
                return item;
            }).collect(Collectors.toList());
            List<BizAssessIndicatorProRatGroup> ratGroups = ratGroupMapper.selectListByProId(porId);
            // 转成键值对方便获取值
            List<String> groupNames = ratGroups.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
            Map<String, Integer> groupMap = ratGroups.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
            List<VEvaluateScore.VoteScore> calculation = calculation(special, groupNames, groupMap, collect);
            calculation.stream().forEach(item -> {
                // 非民主测评才塞入数据
                if (!RatGroup.RATE_EACH.getCode().equals(item.getVoteType())) {
                    item.getVoterDetailList().stream().forEach(d -> {
                        resultMap.put(d.getVoterName(), d.getScore().setScale(2, BigDecimal.ROUND_DOWN));
                    });
                }
                resultMap.put(item.getDescribe(), item.getAverageScore().setScale(2, BigDecimal.ROUND_DOWN));
                Float v = item.getWeight() * 100;
                resultMap.put(item.getDescribe() + "(" + v.intValue() + "%)", item.getWeightedScore().setScale(2, BigDecimal.ROUND_DOWN));
            });
            BigDecimal finaScore = calculation.stream().map(VEvaluateScore.VoteScore::getWeightedScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
            resultMap.put("得分", finaScore);
            /*if (Special.NORMAL.getCode().equals(special) && personnelType.indexOf("_other") > -1) {
                String gradeDescByCode = Grade.getGradeDescByCode(finaScore.intValue());
                resultMap.put("建议等次", gradeDescByCode);
            }*/
            resultList.add(resultMap);
        }
        // 排序（降序）
        List<Map<String, Object>> list = resultList.stream().sorted(Comparator.comparingDouble(e ->
                -Double.parseDouble(e.get("得分").toString())
        )).collect(Collectors.toList());
        return list;
    }


    private List<VEvaluateScore.VoteScore> calculation(String special, List<String> ratGroupNames, Map<String, Integer> ratGroupMap,
                                                       List<VEvaluationGrade.Voter> list) {
        List<VEvaluateScore.VoteScore> voteScoreList = new ArrayList<>();
        if (Special.NORMAL.getCode().equals(special)) {
            // 民主测评
            if (ratGroupNames.contains(RatGroup.RATE_EACH.getCode())) {
                List<VEvaluationGrade.Voter> democraticVoters = list.stream().filter(item -> RatGroup.RATE_EACH.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = democraticVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> democraticMap = democraticVoters.stream().collect(Collectors.groupingBy(VEvaluationGrade.Voter::getUserId,
                        Collectors.mapping(VEvaluationGrade.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_EACH.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues2(democraticVoters, count, democraticMap, weight, RatGroup.RATE_EACH);
                voteScoreList.addAll(voteScores);
            }

            if (ratGroupNames.contains(RatGroup.RATE_DIRECTOR.getCode())) {
                // 主要负责人评价
                List<VEvaluationGrade.Voter> deptChargeVoters = list.stream().filter(item -> RatGroup.RATE_DIRECTOR.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = deptChargeVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> deptChargeMap = deptChargeVoters.stream().collect(Collectors.groupingBy(VEvaluationGrade.Voter::getUserId,
                        Collectors.mapping(VEvaluationGrade.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_DIRECTOR.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues2(deptChargeVoters, count, deptChargeMap, weight, RatGroup.RATE_DIRECTOR);
                voteScoreList.addAll(voteScores);
            }
            if (ratGroupNames.contains(RatGroup.RATE_CHARGE_LEADER.getCode())) {
                // 分管领导评分
                List<VEvaluationGrade.Voter> chargeLeaderVoters = list.stream().filter(item -> RatGroup.RATE_CHARGE_LEADER.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = chargeLeaderVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> chargeLeaderMap = chargeLeaderVoters.stream().collect(Collectors.groupingBy(VEvaluationGrade.Voter::getUserId,
                        Collectors.mapping(VEvaluationGrade.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_CHARGE_LEADER.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues2(chargeLeaderVoters, count, chargeLeaderMap, weight, RatGroup.RATE_CHARGE_LEADER);
                voteScoreList.addAll(voteScores);
            }

            if (ratGroupNames.contains(RatGroup.RATE_MAIN_LEADER.getCode())) {
                // 局主要领导评分
                List<VEvaluationGrade.Voter> mainLeaderVoters = list.stream().filter(item -> RatGroup.RATE_MAIN_LEADER.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = mainLeaderVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> mainLeaderMap = mainLeaderVoters.stream().collect(Collectors.groupingBy(VEvaluationGrade.Voter::getUserId,
                        Collectors.mapping(VEvaluationGrade.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_MAIN_LEADER.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues2(mainLeaderVoters, count, mainLeaderMap, weight, RatGroup.RATE_MAIN_LEADER);
                voteScoreList.addAll(voteScores);
            }
        } else {
            if (ratGroupNames.contains(RatGroup.RATE_SPECIAL.getCode())) {
                // 专项考核
                List<VEvaluationGrade.Voter> specialVoters = list.stream().filter(item -> RatGroup.RATE_SPECIAL.getCode().equals(item.getVoteType()))
                        .collect(Collectors.toList());
                Long count = specialVoters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.counting());
                Map<Long, BigDecimal> specialMap = specialVoters.stream().collect(Collectors.groupingBy(VEvaluationGrade.Voter::getUserId,
                        Collectors.mapping(VEvaluationGrade.Voter::getScore, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
                Float weight = ratGroupMap.get(RatGroup.RATE_SPECIAL.getCode()) / 100F;
                List<VEvaluateScore.VoteScore> voteScores = settingValues2(specialVoters, count, specialMap, weight, RatGroup.RATE_SPECIAL);
                voteScoreList.addAll(voteScores);
            }
        }
        return voteScoreList;
    }

    private List<VEvaluateScore.VoteScore> settingValues2(List<VEvaluationGrade.Voter> source, Long count,
                                                          Map<Long, BigDecimal> calculationMap, Float weight, RatGroup group) {

        Iterator<Map.Entry<Long, BigDecimal>> iterator = calculationMap.entrySet().iterator();

        List<VEvaluateScore.VoterDetail> collect = source.stream().map(o -> {
            VEvaluateScore.VoterDetail voterDetail = new VEvaluateScore.VoterDetail();
            voterDetail.setTaskId(o.getTaskId());
            voterDetail.setEvaluateTagId(o.getEvaluateTagId());
            voterDetail.setVoter(o.getVoter());
            voterDetail.setVoterName(o.getVoterName());
            voterDetail.setUserId(o.getUserId());
            voterDetail.setVoteType(o.getVoteType());
            voterDetail.setWeight(o.getWeight());
            voterDetail.setStatus(o.getStatus());
            voterDetail.setScore(o.getScore());
            return voterDetail;
        }).collect(Collectors.toList());

        List<VEvaluateScore.VoteScore> voteScores = new ArrayList<>();
        if (source.size() > 0) {
            while (iterator.hasNext()) {
                Map.Entry<Long, BigDecimal> entry = iterator.next();
                BigDecimal value = entry.getValue();
                VEvaluateScore.VoteScore voteScore = new VEvaluateScore.VoteScore();
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
                voteScore.setVoterDetailList(collect);
                voteScores.add(voteScore);
            }
        } else {
            // 防止source的size 为0 时，获取不到数据，导致前端显示异常
            VEvaluateScore.VoteScore voteScore = new VEvaluateScore.VoteScore();
            voteScore.setDescribe(group.getDesc());
            voteScore.setVoteType(group.getCode());
            voteScore.setWeight(weight);
            voteScore.setTotalScore(BigDecimal.ZERO);
            voteScore.setWeightedScore(BigDecimal.ZERO);
            voteScore.setAverageScore(BigDecimal.ZERO);
            voteScore.setVoterDetailList(new ArrayList<>());
            voteScores.add(voteScore);
        }
        return voteScores;
    }

    public List<VEvaluationGrade.Voter> selectJSON2VoterList(String json) {
        try {
            JSONArray jsonArray = JSON.parseArray(json);
            List<VEvaluationGrade.Voter> voters = jsonArray.toJavaList(VEvaluationGrade.Voter.class);
            return voters;
        } catch (Exception e) {
            e.printStackTrace();
            return CollectionUtil.newArrayList();
        }

    }

    @Override
    public List<VEvaluateScore.Comprehensive> listTempCompositeEvaluate(BizAssessTaskVo.EvaluateLevelTempReq req) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
        // 获取考核打分信息
        List<BizAssessTaskVo.EvaluateLevelTempResp> evaluateLevelTempResps = evaluationScoreMapper.selectTempEvaluateScoreList(req);
        Map<Long, List<VEvaluationGrade.Voter>> evaluateLevelTempMap = evaluateLevelTempResps.stream()
                .collect(Collectors.toMap(BizAssessTaskVo.EvaluateLevelTempResp::getUserId,
                        o -> this.selectJSON2VoterList(o.getScoringMirror()))
                );
        Iterator<Map.Entry<Long, List<VEvaluationGrade.Voter>>> iterator = evaluateLevelTempMap.entrySet().iterator();
        List<VEvaluateScore.Comprehensive> resultList = new ArrayList<>();
        String special = req.getSpecial();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<VEvaluationGrade.Voter>> entry = iterator.next();
            Long key = entry.getKey();
            String name = StringUtils.isNotBlank(userMap.get(key).getName()) ? userMap.get(key).getName() : userMap.get(key).getNickName();
            VEvaluateScore.Comprehensive comprehensive = new VEvaluateScore.Comprehensive();
            comprehensive.setName(name);
            comprehensive.setDeptName(userMap.get(key).getDept().getDeptName());
            List<VEvaluationGrade.Voter> value = entry.getValue();
            String porId = value.stream().map(VEvaluationGrade.Voter::getProId).findFirst().orElse(null);
            if (StringUtils.isNull(porId)) {
                continue;
            }
            List<VEvaluationGrade.Voter> collect = value.stream().map(item -> {
                item.setVoterName(userMap.get(item.getVoter()).getName());
                return item;
            }).collect(Collectors.toList());
            List<BizAssessIndicatorProRatGroup> ratGroups = ratGroupMapper.selectListByProId(porId);
            // 转成键值对方便获取值
            List<String> groupNames = ratGroups.stream().map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
            Map<String, Integer> groupMap = ratGroups.stream().collect(Collectors.toMap(BizAssessIndicatorProRatGroup::getName, BizAssessIndicatorProRatGroup::getWeight));
            List<VEvaluateScore.VoteScore> calculation = calculation(special, groupNames, groupMap, collect);
            calculation.stream().forEach(item -> {
                String voteType = item.getVoteType();
                BigDecimal weightedScore = item.getWeightedScore().setScale(2, BigDecimal.ROUND_DOWN);
                if (RatGroup.RATE_EACH.getCode().equals(voteType)) {
                    comprehensive.setRateEach(weightedScore);
                }
                if (RatGroup.RATE_DIRECTOR.getCode().equals(voteType)) {
                    comprehensive.setRateDirector(weightedScore);
                }
                if (RatGroup.RATE_CHARGE_LEADER.getCode().equals(voteType)) {
                    comprehensive.setRateChargeLeader(weightedScore);
                }
                if (RatGroup.RATE_MAIN_LEADER.getCode().equals(voteType)) {
                    comprehensive.setRateMainLeader(weightedScore);
                }
                if (RatGroup.RATE_SPECIAL.getCode().equals(voteType)) {
                    comprehensive.setRateSpecial(weightedScore);
                }
            });
            BigDecimal subtotalScore = comprehensive.getRateEach().add(comprehensive.getRateDirector()).add(comprehensive.getRateChargeLeader())
                    .add(comprehensive.getRateMainLeader()).add(comprehensive.getRateSpecial()).setScale(2, BigDecimal.ROUND_DOWN);
            comprehensive.setSubtotalScore(subtotalScore);
            BigDecimal comprehensiveScore = calculation.stream().map(VEvaluateScore.VoteScore::getWeightedScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
            comprehensive.setComprehensiveScore(comprehensiveScore);
            resultList.add(comprehensive);
        }
        List<VEvaluateScore.Comprehensive> collect = resultList.stream()
                .sorted(Comparator.comparing(VEvaluateScore.Comprehensive::getComprehensiveScore).reversed()).collect(Collectors.toList());
        Stream.iterate(0, i -> i + 1).limit(collect.size()).forEach(i -> {
            VEvaluateScore.Comprehensive item = collect.get(i);
            item.setRank(i + 1); // 设置排名
        });
        return collect;
    }

    @Override
    public void exportComposite(BizAssessTaskVo.EvaluateLevelTempReq req, HttpServletResponse response) {
        BizAssessTaskVo.ExportComposite composite = this.selectExportCompositeList(req);
        String special = req.getSpecial();
        String personnelType = req.getPersonnelType();
        OutputStream outputStream;
        try {
            TemplateExcelExpUtil templateExcelExpUtil;
             outputStream = response.getOutputStream();
            if(Special.NORMAL.getCode().equals(special)){
                if(PersonnelType.CIVIL_SERVANT_SPECIAL.getCode().equals(personnelType) ||
                        PersonnelType.INSTITUTION_SPECIAL.getCode().equals(personnelType)){
                    templateExcelExpUtil =TemplateExcelExpUtil.setClassPath("template/ordinaryAssess/CompositeA.xls");
                }else{
                    templateExcelExpUtil =TemplateExcelExpUtil.setClassPath("template/ordinaryAssess/CompositeB.xls");
                }
            }else{
                templateExcelExpUtil =TemplateExcelExpUtil.setClassPath("template/ordinaryAssess/CompositeC.xls");
            }
            templateExcelExpUtil.export(outputStream, composite,composite.getList(),"list");
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public BizAssessTaskVo.ExportComposite selectExportCompositeList(BizAssessTaskVo.EvaluateLevelTempReq req) {
        BizAssessTaskVo.ExportComposite exportComposite = new BizAssessTaskVo.ExportComposite();
        String cycle = req.getCycle();
        String cycleDesc = req.getCycleDesc();
        String personnelType = req.getPersonnelType();
        exportComposite.setBelongYear(req.getBelongYear());
        String deptName = SecurityUtils.getLoginUser().getUser().getDept().getDeptName();
        exportComposite.setFillingDeptName(deptName);
        String personnelDesc;
        String special = req.getSpecial();
        if(Special.NORMAL.getCode().equals(special)){
            String quarter = CycleUtil.parseCycle(cycle, cycleDesc);
            exportComposite.setQuarter(quarter);
            try {
                personnelDesc= PersonnelType.ofCode(personnelType).getDesc();
            }catch (Exception e){
                personnelDesc = "其他考核对象";
            }
            exportComposite.setPersonnelDesc(personnelDesc);
        }

        List<BizAssessTaskVo.ExportEvaluateComposite> list = evaluationScoreMapper.selectExportCompositeList(req);
        List<BizAssessTaskVo.ExportEvaluateComposite> collect = list.stream().map(item -> {
                    if (Special.NORMAL.getCode().equals(special)) {
                        BigDecimal subtotal = item.getDeptScore().add(item.getLeaderScore());
                        item.setSubtotalScore(subtotal);
                    } else {
                        item.setSubtotalScore(item.getUnusualScore());
                    }
                    item.setCompositeScore(item.getSubtotalScore().add(item.getDisciplineScore()));
                    String scoringJson = item.getScoringJson();
                    BigDecimal averageScore = this.calculateDeptAverageScore(scoringJson);
                    item.setDeptAverageScore(averageScore);
                    return item;
                }).sorted(Comparator.comparing(BizAssessTaskVo.ExportEvaluateComposite::getCompositeScore).reversed())
                .collect(Collectors.toList());
        Stream.iterate(0, i -> i + 1).limit(collect.size()).forEach(i -> {
            BizAssessTaskVo.ExportEvaluateComposite composite = collect.get(i);
            composite.setRank(i + 1); // 设置排名
        });
        exportComposite.setTotalNum(collect.size());
        Long excellentNum = collect.stream().filter(o -> "好".equals(o.getGrade())).count();
        exportComposite.setExcellentNum(excellentNum.intValue());
        exportComposite.setList(collect);
        return exportComposite;
    }
    /**
     * 计算部门内评分平均值
     * @param scoringJson
     * @return
     */
    private BigDecimal calculateDeptAverageScore(String scoringJson) {
        if(StringUtils.isBlank(scoringJson)){
            return BigDecimal.ZERO;
        }
        JSONArray jsonArray = JSON.parseArray(scoringJson);
        List<VEvaluationGrade.Voter> voters = jsonArray.toJavaList(VEvaluationGrade.Voter.class);
        List<BigDecimal> scoreList = voters.stream()
                .filter(v -> RatGroup.RATE_EACH.getCode().equals(v.getVoteType()) && "1".equals(v.getStatus()))
                .map(x -> Optional.ofNullable(x.getScore()).orElse(BigDecimal.ZERO))
                .collect(Collectors.toList());
        BigDecimal sum = scoreList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        long count = scoreList.stream().count();
        if(count > 0){
            return sum.divide( BigDecimal.valueOf(count),2, BigDecimal.ROUND_CEILING);
        }
        return BigDecimal.ZERO;
    }
}
