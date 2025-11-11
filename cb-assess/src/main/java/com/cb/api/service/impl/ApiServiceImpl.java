package com.cb.api.service.impl;

import com.cb.api.service.ApiService;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityVo;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationVo;
import com.cb.assess.mapper.*;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.utils.SecurityUtils;
import com.cb.task.domain.BizTaskHandle;
import com.cb.task.service.IBizTaskHandleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private BizIndividualAssessmentTaskMapper individualTaskMapper;
    @Autowired
    private VEvaluationGradeMapper vEvaluationGradeMapper;

    @Resource
    private BizAssessCadrePoliticalQualityMapper bizAssessCadrePoliticalQualityMapper;
    @Resource
    private BizAssessDeptExcellentEvaluationMapper bizAssessDeptExcellentEvaluationMapper;
    @Resource
    private BizAssessCadreDemocraticRecordMapper bizAssessCadreDemocraticRecordMapper;
    @Resource
    private BizAssessAnnualReviewTypeAMapper bizAssessAnnualReviewTypeAMapper;
    @Resource
    private BizAssessAnnualReviewTypeBMapper bizAssessAnnualReviewTypeBMapper;


    @Autowired
    private IBizTaskHandleService bizTaskHandleService;

    @Override
    public int countAssessToDoNum() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return individualTaskMapper.countAssessToDoNum(userId);
    }

    @Override
    public int countDisciplineScoringNum() {
        return vEvaluationGradeMapper.countDisciplineScoringNum();
    }

    @Override
    public int countPartyCommitteeNum() {
        return vEvaluationGradeMapper.countPartyCommitteeNum();
    }

    @Override
    public int countTaskMyTodoNum() {
        SysDept dept = SecurityUtils.getOnlineDept();
        Long leaderDeptId = null;
        String username = SecurityUtils.getUsername();
        if (username.equals(dept.getLeader())) {
            leaderDeptId = dept.getDeptId();
        }
        BizTaskHandle bizTaskHandle = new BizTaskHandle();

        PageHelper.startPage(1, 10);
        List<BizTaskHandle> list = bizTaskHandleService.listMyTodoList(bizTaskHandle, username, leaderDeptId);
        long total = new PageInfo(list).getTotal();
        return Long.valueOf(total).intValue();
    }

    @Override
    public int countTaskMyUnClaimNum(){
        String username = SecurityUtils.getUsername();
        BizTaskHandle bizTaskHandle = new BizTaskHandle();
        PageHelper.startPage(1, 10);
        List<BizTaskHandle> list = bizTaskHandleService.listMyUnClaimList(bizTaskHandle, username);
        long total = new PageInfo(list).getTotal();
        return Long.valueOf(total).intValue();
    }

    @Override
    public int countTaskMyExpireNum() {
        String username = SecurityUtils.getUsername();
        BizTaskHandle bizTaskHandle = new BizTaskHandle();
        PageHelper.startPage(1, 10);
        List<BizTaskHandle> list = bizTaskHandleService.listMyExpireList(bizTaskHandle,username);
        long total = new PageInfo(list).getTotal();
        return Long.valueOf(total).intValue();
    }

    @Override
    public int selectQualityCountMyTask() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQualityVo = bizAssessCadrePoliticalQualityMapper.selectCountMyTask(userId);
        return bizAssessCadrePoliticalQualityVo.getTotal();
    }

    @Override
    public int selectMyExcellentEvaluationCount() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluationVo = bizAssessDeptExcellentEvaluationMapper.selectMyExcellentEvaluationCount(userId);
        return bizAssessDeptExcellentEvaluationVo.getCountTotal();
    }
    @Override
    public int selectCountMyDemocratic(){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        HashMap<String, Long> stringStringHashMap = bizAssessCadreDemocraticRecordMapper.selectCountMyDemocratic(userId);
        Long s = stringStringHashMap.get("countDemocratic");
        return s==null?0:Integer.parseInt(String.valueOf(s));
    }
    @Override
    public int selectAnnualReviewTypeACount(){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo = bizAssessAnnualReviewTypeAMapper.selectAnnualReviewTypeACount(userId);

        return bizAssessAnnualReviewTypeVo.getAnnualReviewTypeCount();
    }
    @Override
    public int selectAnnualReviewTypeBCount(){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo = bizAssessAnnualReviewTypeBMapper.selectAnnualReviewTypeBCount(userId);
        return bizAssessAnnualReviewTypeVo.getAnnualReviewTypeCount();
    }
}
