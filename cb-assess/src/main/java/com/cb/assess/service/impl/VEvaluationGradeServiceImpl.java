package com.cb.assess.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cb.assess.domain.BizAssessEvaluationGrade;
import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.VEvaluationGrade;
import com.cb.assess.domain.vo.AssessRegularVo;
import com.cb.assess.enums.*;
import com.cb.assess.mapper.BizAssessRegularInfoMapper;
import com.cb.assess.mapper.VEvaluationGradeMapper;
import com.cb.assess.service.IBizAssessEvaluationGradeService;
import com.cb.assess.service.IBizAssessPromulgateService;
import com.cb.assess.service.VEvaluationGradeService;
import com.cb.assess.utils.CycleUtil;
import com.cb.assess.utils.PoiTlUtil;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.constant.NoticeBizUrlTpl;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.easyexcel.TemplateExcelExpUtil;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.domain.SysNotice;
import com.cb.system.mapper.SysUserMapper;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysNoticeService;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class VEvaluationGradeServiceImpl implements VEvaluationGradeService {

    @Autowired
    private VEvaluationGradeMapper gradeMapper;
    @Autowired
    private BizAssessRegularInfoMapper regularMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private IBizAssessEvaluationGradeService evaluationGradeService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private IBizAssessPromulgateService promulgateService;
    @Autowired
    private ISysNoticeService noticeService;

    @Override
    public List<VEvaluationGrade> selectVEvaluationGradeList(VEvaluationGrade evaluationGrade) {
        List<VEvaluationGrade> list = gradeMapper.selectVEvaluationGradeList(evaluationGrade);
        this.buildEvaluationGrades(list);
        return list;
    }


    @Override
    public List<VEvaluationGrade.Grade> selectGradeList(VEvaluationGrade.Grade grade) {
        return gradeMapper.selectGradeList(grade);
    }

    @Override
    public List<VEvaluationGrade.ExportLevelGrade> selectExportGradeList(VEvaluationGrade.ExportLevelGrade grade) {
        List<VEvaluationGrade.ExportLevelGrade> list = gradeMapper.selectExportLevelGradeList(grade);
        list.stream().forEach(item -> {
            BigDecimal score = item.getDeptScore().add(item.getLeaderScore()).add(item.getDisciplineScore()).add(item.getUnusualScore());
            item.setScore(score);
            // 设置季度
            if (Special.NORMAL.getCode().equals(grade.getSpecial())) {
                String quarterText = CycleUtil.showQuarterText(item.getCycleDesc());
                item.setQuarter(quarterText);
            } else {
                item.setQuarter("专项考核");
            }
        });
        return list;
    }

    @Override
    public BizAssessRegularInfo selectRegularInfo(String taskId, Long userId) {
        return regularMapper.selectBizAssessRegularInfoByTaskId(taskId, userId);
    }

    @Override
    public List<VEvaluationGrade.Composite> selectCompositeList(VEvaluationGrade.Composite composite) {
        String special = composite.getSpecial();
        List<VEvaluationGrade.Composite> composites = gradeMapper.selectCompositeList(composite);
        List<VEvaluationGrade.Composite> collect = composites.stream().map(o -> {
                    if (Special.NORMAL.getCode().equals(special)) {
                        BigDecimal deptScore = o.getDeptScore();
                        BigDecimal leaderScore = o.getLeaderScore();
                        BigDecimal subtotal = deptScore.add(leaderScore);
                        o.setSubtotalScore(subtotal);
                    } else {
                        o.setSubtotalScore(o.getUnusualScore());
                    }
                    BigDecimal Composite = o.getSubtotalScore().add(o.getDiscipline());
                    o.setCompositeScore(Composite);
                    String grade = Grade.getGradeDescByCode(Composite.intValue());
                    if (StringUtils.isBlank(o.getOrganGrade())) {
                        o.setOrganGrade(grade);
                    }
                    return o;
                })
                // 收集为LinkedHashMap
                .collect(Collectors.groupingBy(VEvaluationGrade.Composite::getDeptId, LinkedHashMap::new, Collectors.toList()))
                .entrySet()
                .stream()
                // 对键进行排序
                .sorted(Map.Entry.comparingByKey())
                // 对值进行排序
                .map(entry -> {
                    List<VEvaluationGrade.Composite> value = entry.getValue();
                    return value.stream().map(c->{
                        String scoringJson = c.getScoringJson();
                        BigDecimal average = this.calculateDeptAverageScore(scoringJson);
                        c.setDeptOriginScore(average);
                        return c;
                    }).sorted(Comparator.comparing(VEvaluationGrade.Composite::getCompositeScore).reversed())
                            .collect(Collectors.toList());
                })
                // 扁平化数据
                .flatMap(List::stream).collect(Collectors.toList());
        return collect;
    }

    @Override
    public VEvaluationGrade.PersonalScore selectVoterDetailList(VEvaluationGrade.Voter voter) {
        List<SysUser> users = userMapper.selectAll();
        Map<Long, SysUser> userMap = users.stream().collect(Collectors.toMap(SysUser::getUserId, SysUser -> SysUser));
//        List<VEvaluationGrade.Voter> voters = gradeMapper.selectVoterListByUserId(voter);
        List<VEvaluationGrade.Voter> voters = selectJSON2VoterList(voter.getId());
        List<VEvaluationGrade.Voter> collect = voters.stream().map(item -> {
            item.setVoterName(userMap.get(item.getVoter()).getName());
            return item;
        }).sorted(Comparator.comparing(VEvaluationGrade.Voter::getScore).reversed()).collect(Collectors.toList());
        VEvaluationGrade.PersonalScore personalScore = new VEvaluationGrade.PersonalScore();
        personalScore.setUserName(userMap.get(voter.getUserId()).getName());

        Map<String, BigDecimal> groupSumMap = voters.stream().collect(Collectors.groupingBy(VEvaluationGrade.Voter::getVoteType,
                Collectors.mapping(item -> {
                    Integer weight = item.getWeight();
                    float v = weight / 100F;
                    BigDecimal score = item.getScore();
                    return score.multiply(BigDecimal.valueOf(v));
                }, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Map<String, Long> statusMap = voters.stream().filter(o -> "1".equals(o.getStatus())).collect(Collectors.groupingBy(VEvaluationGrade.Voter::getVoteType,
                Collectors.mapping(VEvaluationGrade.Voter::getStatus, Collectors.counting())));
        BigDecimal zero = BigDecimal.ZERO;
        Iterator<Map.Entry<String, BigDecimal>> iterator = groupSumMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, BigDecimal> entry = iterator.next();
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            /*Long aLong = statusMap.get(key) != null ? statusMap.get(key) : 0L;
            zero = zero.add(value.divide(BigDecimal.valueOf(aLong), BigDecimal.ROUND_CEILING));*/
            Long aLong = statusMap.get(key);
            if(null != aLong && aLong != 0){
                zero = zero.add(value.divide(BigDecimal.valueOf(aLong), BigDecimal.ROUND_CEILING));
            }
        }
        personalScore.setWeightAverageScore(zero.setScale(2, BigDecimal.ROUND_DOWN));
        personalScore.setVoterList(collect);
        return personalScore;
    }


    public List<VEvaluationGrade.Voter> selectJSON2VoterList(String id) {
        BizAssessEvaluationGrade evaluationGrade = evaluationGradeService.selectBizAssessEvaluationGradeById(id);
        String scoringMirror = evaluationGrade.getScoringMirror();
        JSONArray jsonArray = JSON.parseArray(scoringMirror);
        List<VEvaluationGrade.Voter> voters = jsonArray.toJavaList(VEvaluationGrade.Voter.class);
        return voters;
    }


    @Override
    public int saveComprehensive(VEvaluationGrade.ScoreLevelTempParam param) {
        List<BizAssessEvaluationGrade> levelTemps = param.getLevelTemps();
        if (CollectionUtil.isEmpty(levelTemps)) throw new IllegalArgumentException("参数异常!");
        levelTemps.forEach(o -> {
            String id = o.getId();
            //党组会等次为空的话用人事处的赋值
            String rscOpinion = o.getRscOpinion();
            String dzhOpinion = o.getDzhOpinion();
            if(StringUtils.isBlank(dzhOpinion)){
                o.setDzhOpinion(rscOpinion);
            }
            BizAssessEvaluationGrade evaluationGrade = evaluationGradeService.checkUnique(id);
            if (StringUtils.isNull(evaluationGrade)) {
                o.setId(IdUtils.randomUUID());
                o.setCreateBy(SecurityUtils.getUsername());
                o.setCreateTime(DateUtils.getNowDate());
                evaluationGradeService.insertBizAssessEvaluationGrade(o);
            } else {
                o.setId(evaluationGrade.getId());
                evaluationGradeService.updateBizAssessEvaluationGrade(o);
            }
        });
        return 1;
    }

    @Override
    public List<VEvaluationGrade> selectDisciplineVEvaluationGradeList(VEvaluationGrade evaluationGrade) {
        List<VEvaluationGrade> list = gradeMapper.selectDisciplineVEvaluationGradeList(evaluationGrade);
        this.buildEvaluationGrades(list);
        return list;
    }

    @Override
    public List<VEvaluationGrade.Grade> selectToBeEvaluatedList(VEvaluationGrade.Grade grade, Boolean isScoring) {
        List<VEvaluationGrade.Grade> list = gradeMapper.selectToBeEvaluatedList(grade, isScoring);
        this.calculateDisciplineScore(list,isScoring);
        return list;
    }


    /**
     * 计算旷工天数
     * 考勤分数包含在纪委5分评分项中 也是对处长、主任。考勤扣分规整旷工一天扣 1分，以此类推。最后高扣5分？
     */
    private void calculateDisciplineScore(List<VEvaluationGrade.Grade> list, Boolean isScoring) {
        if (!isScoring) {
            list.forEach(g -> {
                Integer attendanceNum = g.getAttendanceNum();
                Integer attendance = attendanceNum > 5 ? 5 : attendanceNum;
                BigDecimal disciplineScore = g.getDisciplineScore();
                g.setAttendanceScore(new BigDecimal(attendance)); //设置考勤扣分项
                BigDecimal deduction = new BigDecimal(-attendance);
                g.setDisciplineScore(disciplineScore.add(deduction)); // 计算纪委得分
            });
        }
    }

    @Override
    public List<VEvaluationGrade> selectMainChargeVEvaluationGradeList(VEvaluationGrade evaluationGrade) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysDept> chargeDeptList = deptService.selectChargeDeptList(user.getUserId());
        List<Long> deptIds = chargeDeptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(deptIds)) return CollectionUtil.newArrayList();
        LinkedHashSet<String> types = new LinkedHashSet<>();
        types.add(PersonnelType.CIVIL_SERVANT_SPECIAL.getCode());
        types.add(PersonnelType.INSTITUTION_SPECIAL.getCode());

        List<VEvaluationGrade> vEvaluationGrades = gradeMapper.selectMainChargeVEvaluationGradeList(evaluationGrade, deptIds, String.join(",", types));
        this.buildEvaluationGrades(vEvaluationGrades);
        return vEvaluationGrades;
    }

    @Override
    public List<VEvaluationGrade.Grade> toBeOrderGradeList(VEvaluationGrade.Grade grade, Boolean isScoring) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysDept> chargeDeptList = deptService.selectChargeDeptList(user.getUserId());
        List<Long> deptIds = chargeDeptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(deptIds)) return CollectionUtil.newArrayList();
        LinkedHashSet<String> types = new LinkedHashSet<>();
        types.add(PersonnelType.CIVIL_SERVANT_SPECIAL.getCode());
        types.add(PersonnelType.INSTITUTION_SPECIAL.getCode());
        List<VEvaluationGrade.Grade> grades = gradeMapper.toBeOrderGradeList(grade, deptIds, String.join(",", types), isScoring);
        List<VEvaluationGrade.Grade> sorted = grades.stream().map(item->{
            if (!isScoring) {
                item.setGrade(Grade.BETTER.getDesc());
            }
            item.setTotalScore(item.getScore().add(item.getDisciplineScore()));
            return item;
        }).sorted(Comparator.comparing(VEvaluationGrade.Grade::getTotalScore).reversed()).collect(Collectors.toList());
        return sorted;
    }

    @Override
    public List<VEvaluationGrade> selectOrganVEvaluationGradeList(VEvaluationGrade evaluationGrade) {
        List<VEvaluationGrade> list = gradeMapper.selectOrganVEvaluationGradeList(evaluationGrade);
        this.buildEvaluationGrades(list);
        return list;
    }

    @Override
    public List<VEvaluationGrade.Grade> selectToBeOrganGradeList(VEvaluationGrade.Grade grade, Boolean isScoring) {
        List<VEvaluationGrade.Grade> grades = gradeMapper.selectToBeOrganGradeList(grade, isScoring);
        grades.forEach(item -> {
            BigDecimal finalScore = item.getScore().add(item.getDisciplineScore());
            item.setCompositeScore(finalScore);
            if (StringUtils.isBlank(item.getRscOpinion())) {
//                String gradeDescByCode = Grade.getGradeDescByCode(finalScore.intValue());
//                item.setRscOpinion(gradeDescByCode);
                item.setRscOpinion(item.getGrade());
            }
        });
        return grades;
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
    public void exportRegular2Word(String taskId, Long userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AssessRegularVo vo = regularMapper.exportRegular2Word(taskId, userId);

        String identityType = vo.getUser().getIdentityType();
        String quarter = CycleUtil.parseCycle(vo.getCycle(), vo.getCycleDesc());
        vo.setQuarter(quarter);
        vo.setAuditDateTime(DateUtil.format(vo.getAuditTime(), "yyyy年MM月dd日"));
        InputStream is = null;
        if ("1".equals(identityType) || "2".equals(identityType)) {
            ClassPathResource classPathResource = new ClassPathResource("template/ordinaryAssess/ServantRegistrationForm.docx");
            is = classPathResource.getInputStream();
        } else if ("3".equals(identityType)) {
            ClassPathResource classPathResource = new ClassPathResource("template/ordinaryAssess/InstitutionsRegistrationForm.docx");
            is = classPathResource.getInputStream();
        }
        response.setContentType("application/octet-stream");
        Map<String, Object> map = BeanUtil.beanToMap(vo);
        OutputStream out = response.getOutputStream();
        List<String> list = Arrays.asList("personalSummary");

        map.putIfAbsent("othersRemark", "");
        Object organSeal = map.get("organSeal");
        if(null != organSeal){
            String  organSealStr = (String) organSeal;
            if(StringUtils.isNotBlank(organSealStr)){
                String root = RuoYiConfig.getUploadPath();
                String path = root + organSealStr.replace("/profile/upload","");
                map.put("organSeal", Pictures.ofLocal(path).size(160, 160).create());
            }
        }
        PoiTlUtil.export2Word(is, out, map, list, true);
    }

    @Override
    public void exportEvaluationCompositeLevel(VEvaluationGrade.Composite composite, HttpServletResponse response) {
        VEvaluationGrade.ExportCompositeLevel exportCompositeLevel = this.selectExportEvaluationCompositeList(composite);
        String special = composite.getSpecial();
        String personnelType = composite.getPersonnelType();
        OutputStream outputStream;
        try {
            TemplateExcelExpUtil templateExcelExpUtil;
            outputStream = response.getOutputStream();
            if (Special.NORMAL.getCode().equals(special)) {
                if (PersonnelType.CIVIL_SERVANT_SPECIAL.getCode().equals(personnelType) ||
                        PersonnelType.INSTITUTION_SPECIAL.getCode().equals(personnelType)) {
                    templateExcelExpUtil = TemplateExcelExpUtil.setClassPath("template/ordinaryAssess/CompositeLevelA.xls");
                } else {
                    templateExcelExpUtil = TemplateExcelExpUtil.setClassPath("template/ordinaryAssess/CompositeLevelB.xls");
                }
            } else {
                templateExcelExpUtil = TemplateExcelExpUtil.setClassPath("template/ordinaryAssess/CompositeLevelC.xls");
            }
            templateExcelExpUtil.export(outputStream, exportCompositeLevel, exportCompositeLevel.getList(), "list");
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public VEvaluationGrade.ExportCompositeLevel selectExportEvaluationCompositeList(VEvaluationGrade.Composite composite) {
        VEvaluationGrade.ExportCompositeLevel exportCompositeLevel = new VEvaluationGrade.ExportCompositeLevel();
        String cycle = composite.getCycle();
        String cycleDesc = composite.getCycleDesc();
        String personnelType = composite.getPersonnelType();
        exportCompositeLevel.setBelongYear(composite.getBelongYear());
        String deptName = SecurityUtils.getLoginUser().getUser().getDept().getDeptName();
        exportCompositeLevel.setFillingDeptName(deptName);
        String personnelDesc;
        String special = composite.getSpecial();
        if (Special.NORMAL.getCode().equals(special)) {
            String quarter = CycleUtil.parseCycle(cycle, cycleDesc);
            exportCompositeLevel.setQuarter(quarter);
            try {
                personnelDesc = PersonnelType.ofCode(personnelType).getDesc();
            } catch (Exception e) {
                personnelDesc = "其他考核对象";
            }
            exportCompositeLevel.setPersonnelDesc(personnelDesc);
        }
        List<VEvaluationGrade.ExportEvaluationComposite> list = gradeMapper.selectExportEvaluationCompositeList(composite);

        List<VEvaluationGrade.ExportEvaluationComposite> collect = list.stream().map(o -> {
                    if (Special.NORMAL.getCode().equals(special)) {
                        BigDecimal deptScore = o.getDeptScore();
                        BigDecimal leaderScore = o.getLeaderScore();
                        BigDecimal subtotal = deptScore.add(leaderScore);
                        o.setSubtotalScore(subtotal);
                    } else {
                        o.setSubtotalScore(o.getUnusualScore());
                    }
                    BigDecimal compositeScore = o.getSubtotalScore().add(o.getDisciplineScore());
                    o.setCompositeScore(compositeScore);

                    // 计算部门内评分平均分
                    String scoringJson = o.getScoringJson();
                    BigDecimal average = calculateDeptAverageScore(scoringJson);
                    o.setDeptAverageScore(average);
                    return o;
                }).sorted(Comparator.comparing(VEvaluationGrade.ExportEvaluationComposite::getCompositeScore).reversed())
                .collect(Collectors.toList());
        exportCompositeLevel.setList(collect);
        return exportCompositeLevel;
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

    @Override
    public int saveDzhOpinion(BizAssessEvaluationGrade grade) {
        grade.setStep("4");
        return evaluationGradeService.updateBizAssessEvaluationGrade(grade);
    }

    @Override
    public int batchSaveDzhOpinion(List<BizAssessEvaluationGrade> gradeList) {
        gradeList.forEach(item->{
            this.saveDzhOpinion(item);
        });
        return 1;
    }

    @Override
    @Transactional
    public int publicity(BizAssessPromulgate promulgate) {
        checkPublicity(promulgate);
        promulgate.setPublicityTime(DateUtils.getNowDate());
        String taskId = promulgate.getTaskId();
        String quarter = promulgate.getQuarter();
        String special = promulgate.getSpecial();
        String title;
        String bizUrl;
        if(Special.NORMAL.getCode().equals(special)){
            title = StringUtils.format("{}"+ NoticeBizUrlTpl.ORDINARY_ASSESSMENT.getDesc(), CycleUtil.parseCycle("2",quarter));
            bizUrl = NoticeBizUrlTpl.ORDINARY_ASSESSMENT.build(special,quarter);
        }else{
            title = StringUtils.format("{}"+ "专项考核",promulgate.getBelongYear());
            bizUrl =  NoticeBizUrlTpl.ORDINARY_ASSESSMENT.build(special,taskId);
        }
        SysNotice notice = new SysNotice();
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        notice.setBizUrl(bizUrl);
        noticeService.insertNotice(notice);
        return promulgateService.insertBizAssessPromulgate(promulgate);
    }

    private void checkPublicity(BizAssessPromulgate promulgate){
        Boolean exist = promulgateService.checkExist(promulgate);
        if(exist){
            throw new RuntimeException("该季度考核已公示");
        }
        // TODO 查找是否存在未上报以及办理完整的考评数据
        List<VEvaluationGrade.Grade> gradeList = gradeMapper.selectGradeListByPromulgate(promulgate);
        // 过滤出未办完的
        List<VEvaluationGrade.Grade> filterList = gradeList.stream().filter(o -> !EvaluateStep.GM_SUGGESTS.getCode().equals(o.getStep())).collect(Collectors.toList());
        Map<String, List<VEvaluationGrade.Grade>> map = filterList.stream().collect(Collectors.groupingBy(VEvaluationGrade.Grade::getStep));
        if (map.size() > 0){
            List<String> strList = map.entrySet().stream().map(o -> {
                String key = o.getKey();
                String desc = EvaluateStep.getDescByCode(key);
                List<VEvaluationGrade.Grade> value = o.getValue();
                List<String> nameList = value.stream().map(g -> g.getUserName()).collect(Collectors.toList());
                String names = StringUtils.join(nameList, ",");
                String s = String.format("【%s：%s】", desc, names);
                return s;
            }).collect(Collectors.toList());
            String tipStr = "存在未办理完成数据："+StringUtils.join(strList, ";") +",禁止公示！";
            throw new RuntimeException(tipStr);
        }

        int count = gradeMapper.countDzhOpinionNull(promulgate);
        if(count > 0){
            throw new RuntimeException("存在党组会建议等次未设置，请检查");
        }
    }

    @Override
    public List<VEvaluationGrade.Publicity> selectPublicityList(VEvaluationGrade.Publicity publicity) {
        return gradeMapper.selectPublicityList(publicity);
    }

    @Override
    public  VEvaluationGrade.PromulgateResult selectPromulgateResult(VEvaluationGrade.Publicity publicity) {
        VEvaluationGrade.PromulgateResult result= new VEvaluationGrade.PromulgateResult();
        List<VEvaluationGrade.Publicity> list = gradeMapper.selectPublicityList(publicity);
        String promulgateId = list.stream().map(VEvaluationGrade.Publicity::getPromulgateId)
                .findFirst().orElseThrow(() -> new RuntimeException("未匹配到公示信息"));
        BizAssessPromulgate promulgate = promulgateService.selectBizAssessPromulgateById(promulgateId);
        result.setPromulgate(promulgate);
        result.setPublicityList(list);
        return result;
    }

    @Override
    public void exportInstructionWord(HttpServletResponse response, VEvaluationGrade.Composite composite) {
        String cycleDesc = composite.getCycleDesc();
        DateTime dateTime = DateUtil.parseDate(cycleDesc);
        int year = DateUtil.year(dateTime);
        int quarter = DateUtil.quarter(dateTime);

        //二级巡视员、总师、督查员、主要负责人(公务员)
        List<VEvaluationGrade.Composite> civilServantSpecialList = getCompositeListByPersonnelType(composite,"civil_servant_special");
        //其他考核对象(公务员)
        List<VEvaluationGrade.Composite> civilServantOtherList = getCompositeListByPersonnelType(composite,"civil_servant_other");
        //主要负责人(事业单位)
        List<VEvaluationGrade.Composite> institutionSpecialList = getCompositeListByPersonnelType(composite,"institution_special");
        //其他考核对象 (事业单位)
        List<VEvaluationGrade.Composite> institutionOtherList = getCompositeListByPersonnelType(composite,"institution_other");

        List<VEvaluationGrade.Composite> allList = new LinkedList<>();
        allList.addAll(civilServantSpecialList);
        allList.addAll(civilServantOtherList);
        allList.addAll(institutionSpecialList);
        allList.addAll(institutionOtherList);

        int totalNum = civilServantSpecialList.size() + civilServantOtherList.size() +institutionSpecialList.size() + institutionOtherList.size();
        long goodNum =   allList.stream().filter(item->"好".equals(item.getPartyWillGrade())).count();
        long betterNum =   allList.stream().filter(item->"较好".equals(item.getPartyWillGrade())).count();
        List<VEvaluationGrade.Composite> confusionList = allList.stream().filter(item -> "不定等次".equals(item.getPartyWillGrade())).collect(Collectors.toList());
        int confusionNum =  confusionList.size();
        boolean hasConfusion = confusionNum > 0;
        String confusionNames = confusionList.stream().map(item->item.getName()).collect(Collectors.joining(","));

        long goodPercent = totalNum == 0 ? 0 : 100 * goodNum / totalNum;
        long betterPercent = totalNum == 0 ? 0 : 100 * betterNum / totalNum;
        long confusionPercent = totalNum == 0 ? 0 : 100 * confusionNum / totalNum;

        //总师、督查员以及机关和参公单位主要负责人参加考核人数
        long civilServantTotalNum = civilServantSpecialList.size();
        long civilServantGoodNum = civilServantSpecialList.stream().filter(item->"好".equals(item.getPartyWillGrade())).count();

        //事业单位主要负责人参加考核人数
        long institutionTotalNum = institutionSpecialList.size();
        long institutionGoodNum = institutionSpecialList.stream().filter(item->"好".equals(item.getPartyWillGrade())).count();

        //二级巡视员、总师、处室（单位）主要负责人、督查员层级
        Map<String,Object> special = new HashMap<>();
        special.put("totalNum",civilServantSpecialList.size() + institutionSpecialList.size());
        Map<String,Object> special_good = new HashMap<>();
        Map<String,Object> special_good_civilServant = new HashMap<>();
        List<VEvaluationGrade.Composite> special_civilServant_goodList = civilServantSpecialList.stream()
                .filter(item -> "好".equals(item.getPartyWillGrade())).collect(Collectors.toList());
        special_good_civilServant.put("num",special_civilServant_goodList.size());
        special_good_civilServant.put("has",special_civilServant_goodList.size() > 0);
        special_good_civilServant.put("names",special_civilServant_goodList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        Map<String,Object> special_good_institution = new HashMap<>();
        List<VEvaluationGrade.Composite> special_institution_goodList = institutionSpecialList.stream()
                .filter(item -> "好".equals(item.getPartyWillGrade())).collect(Collectors.toList());
        special_good_institution.put("num",special_institution_goodList.size());
        special_good_institution.put("has",special_institution_goodList.size() > 0);
        special_good_institution.put("names",special_institution_goodList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        special_good.put("civilServant",special_good_civilServant);
        special_good.put("institution",special_good_institution);
        special.put("good",special_good);

        Map<String,Object> special_better = new HashMap<>();
        Map<String,Object> special_better_civilServant = new HashMap<>();
        List<VEvaluationGrade.Composite> special_civilServant_betterList = civilServantSpecialList.stream()
                .filter(item -> "较好".equals(item.getPartyWillGrade())).collect(Collectors.toList());
        special_better_civilServant.put("num",special_civilServant_betterList.size());
        special_better_civilServant.put("has",special_civilServant_betterList.size() > 0);
        special_better_civilServant.put("names",special_civilServant_betterList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        Map<String,Object> special_better_institution = new HashMap<>();
        List<VEvaluationGrade.Composite> special_institution_betterList = institutionSpecialList.stream()
                .filter(item -> "较好".equals(item.getPartyWillGrade())).collect(Collectors.toList());
        special_better_institution.put("num",special_institution_betterList.size());
        special_better_institution.put("has",special_institution_betterList.size() > 0);
        special_better_institution.put("names",special_institution_betterList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        special_better.put("civilServant",special_better_civilServant);
        special_better.put("institution",special_better_institution);
        special.put("better",special_better);

        //驻村工作队需要过滤出来
        SysUser query = new SysUser();
        query.setStatus("0");
        query.setDelFlag("0");
        query.setPersonnelStatus("7");//驻村
        List<Long> confusionUserIds = userMapper.selectUserList(query).stream().map(SysUser::getUserId).collect(Collectors.toList());

        //其他考核对象层级
        Map<String,Object> other = new HashMap<>();
        other.put("totalNum",civilServantOtherList.size() + institutionOtherList.size());
        Map<String,Object> other_good = new HashMap<>();
        Map<String,Object> other_good_civilServant = new HashMap<>();
        List<VEvaluationGrade.Composite> other_civilServant_goodList = civilServantOtherList.stream()
                .filter(item -> "好".equals(item.getPartyWillGrade()))
                .filter(item -> !confusionUserIds.contains(item.getUserId()))
                .collect(Collectors.toList());
        other_good_civilServant.put("num",other_civilServant_goodList.size());
        other_good_civilServant.put("has",other_civilServant_goodList.size() > 0);
        other_good_civilServant.put("names",other_civilServant_goodList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        other_good.put("civilServant",other_good_civilServant);
        Map<String,Object> other_good_institution = new HashMap<>();
        List<VEvaluationGrade.Composite> other_institution_goodList = institutionOtherList.stream()
                .filter(item -> "好".equals(item.getPartyWillGrade()))
                .filter(item -> !confusionUserIds.contains(item.getUserId()))
                .collect(Collectors.toList());
        other_good_institution.put("num",other_institution_goodList.size());
        other_good_institution.put("has",other_institution_goodList.size() > 0);
        other_good_institution.put("names",other_institution_goodList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        other_good.put("institution",other_good_institution);
        Map<String,Object> other_good_villageWork = new HashMap<>();
        List<VEvaluationGrade.Composite> other_villageWork_goodList = institutionOtherList.stream()
                .filter(item -> "好".equals(item.getPartyWillGrade()))
                .filter(item -> confusionUserIds.contains(item.getUserId()))
                .collect(Collectors.toList());
        other_good_villageWork.put("num",other_villageWork_goodList.size());
        other_good_villageWork.put("has",other_villageWork_goodList.size() > 0);
        other_good_villageWork.put("names",other_villageWork_goodList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        other_good.put("villageWork",other_good_villageWork);
        other.put("good",other_good);

        Map<String,Object> other_better = new HashMap<>();
        Map<String,Object> other_better_civilServant = new HashMap<>();
        List<VEvaluationGrade.Composite> other_civilServant_betterList = civilServantOtherList.stream()
                .filter(item -> "较好".equals(item.getPartyWillGrade()))
                .filter(item -> !confusionUserIds.contains(item.getUserId()))
                .collect(Collectors.toList());
        other_better_civilServant.put("num",other_civilServant_betterList.size());
        other_better_civilServant.put("has",other_civilServant_betterList.size() > 0);
        other_better_civilServant.put("names",other_civilServant_betterList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        other_better.put("civilServant",other_better_civilServant);
        Map<String,Object> other_better_institution = new HashMap<>();
        List<VEvaluationGrade.Composite> other_institution_betterList = institutionOtherList.stream()
                .filter(item -> "较好".equals(item.getPartyWillGrade()))
                .filter(item -> !confusionUserIds.contains(item.getUserId()))
                .collect(Collectors.toList());
        other_better_institution.put("num",other_institution_betterList.size());
        other_better_institution.put("has",other_institution_betterList.size() > 0);
        other_better_institution.put("names",other_institution_betterList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        other_better.put("institution",other_better_institution);
        Map<String,Object> other_better_villageWork = new HashMap<>();
        List<VEvaluationGrade.Composite> other_villageWork_betterList = institutionOtherList.stream()
                .filter(item -> "较好".equals(item.getPartyWillGrade()))
                .filter(item -> confusionUserIds.contains(item.getUserId()))
                .collect(Collectors.toList());
        other_better_villageWork.put("num",other_villageWork_betterList.size());
        other_better_villageWork.put("has",other_villageWork_betterList.size() > 0);
        other_better_villageWork.put("names",other_villageWork_betterList.stream().map(VEvaluationGrade.Composite::getName).collect(Collectors.joining(",")));
        other_better.put("villageWork",other_better_villageWork);
        other.put("better",other_better);

        Map<String,Object> data = new HashMap<>();
        data.put("year",year);
        data.put("quarter",quarter);
        data.put("totalNum",totalNum);
        data.put("goodNum",goodNum);
        data.put("goodPercent",goodPercent);
        data.put("betterNum",betterNum);
        data.put("betterPercent",betterPercent);
        data.put("confusionNum",confusionNum);
        data.put("confusionPercent",confusionPercent);
        data.put("hasConfusion",hasConfusion);
        data.put("confusionNames",confusionNames);
        data.put("civilServantTotalNum",civilServantTotalNum);
        data.put("civilServantGoodNum",civilServantGoodNum);
        IntStream.range(0, civilServantSpecialList.size()).forEach(index -> {
            civilServantSpecialList.get(index).setRank(index + 1);
        });
        data.put("civilServantSpecialList",civilServantSpecialList);
        data.put("institutionTotalNum",institutionTotalNum);
        data.put("institutionGoodNum",institutionGoodNum);
        IntStream.range(0, institutionSpecialList.size()).forEach(index -> {
            institutionSpecialList.get(index).setRank(index + 1);
        });
        data.put("institutionSpecialList",institutionSpecialList);
        data.put("special",special);
        data.put("other",other);

        List<VEvaluationGrade.Composite> otherList = new LinkedList<>();
        List<VEvaluationGrade.Composite> civilServantNotVillage = civilServantOtherList.stream()
                .filter(item -> !confusionUserIds.contains(item.getUserId())).collect(Collectors.toList());
        List<VEvaluationGrade.Composite> civilServantVillage = civilServantOtherList.stream()
                .filter(item -> confusionUserIds.contains(item.getUserId())).collect(Collectors.toList());
        List<VEvaluationGrade.Composite> institutionNotVillage = institutionOtherList.stream()
                .filter(item -> !confusionUserIds.contains(item.getUserId())).collect(Collectors.toList());
        List<VEvaluationGrade.Composite> institutionVillage = institutionOtherList.stream()
                .filter(item -> confusionUserIds.contains(item.getUserId())).collect(Collectors.toList());

        List<VEvaluationGrade.Composite> notVillageList = new LinkedList();
        notVillageList.addAll(civilServantNotVillage);
        notVillageList.addAll(institutionNotVillage);
        notVillageList = notVillageList.stream().sorted(Comparator.comparing(VEvaluationGrade.Composite::getCompositeScore).reversed()).collect(Collectors.toList());
        List<VEvaluationGrade.Composite> villageList = new LinkedList();
        villageList.addAll(civilServantVillage);
        villageList.addAll(institutionVillage);
        villageList = villageList.stream().sorted(Comparator.comparing(VEvaluationGrade.Composite::getCompositeScore).reversed()).collect(Collectors.toList());

        otherList.addAll(notVillageList);
        otherList.addAll(villageList);

        IntStream.range(0, otherList.size()).forEach(index -> {
            VEvaluationGrade.Composite item = otherList.get(index);
            item.setRank(index + 1);
            if(confusionUserIds.contains(item.getUserId())){
                item.setDeptName("驻村队员");
            }
        });
        data.put("otherList",otherList);

        InputStream is = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/word/关于审定XXXX年X季度平时考核等次人员的请示模版.docx");
            is = classPathResource.getInputStream();

            ConfigureBuilder configureBuilder = Configure.builder().useSpringEL()
                    .bind("civilServantSpecialList", new HackLoopTableRenderPolicy())
                    .bind("institutionSpecialList", new HackLoopTableRenderPolicy())
                    .bind("otherList", new HackLoopTableRenderPolicy())
                    ;
            Configure config = configureBuilder.build();

            XWPFTemplate template = XWPFTemplate.compile(is,config).render(data);
            String fileName = "关于审定" + year + "年" + quarter +"季度平时考核等次人员的请示";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            template.writeAndClose(response.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
            throw new CustomException("民主评测导出失败!");
        } finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private List<VEvaluationGrade.Composite> getCompositeListByPersonnelType(VEvaluationGrade.Composite composite,String personnelType){
        composite.setPersonnelType(personnelType);
        return selectCompositeList(composite).stream()
                .sorted(Comparator.comparing(VEvaluationGrade.Composite::getCompositeScore).reversed())
                .collect(Collectors.toList());
    }

}
