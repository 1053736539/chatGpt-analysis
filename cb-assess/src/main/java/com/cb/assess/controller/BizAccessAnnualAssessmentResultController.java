package com.cb.assess.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationResultVo;
import com.cb.assess.service.IBizAssessAnnualReviewTypeResultService;
import com.cb.assess.service.IBizAssessDeptExcellentEvaluationResultService;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assess/AnnualAssessmentResult")
public class BizAccessAnnualAssessmentResultController extends BaseController {


    @Resource
    private IBizAssessDeptExcellentEvaluationResultService bizAssessDeptExcellentEvaluationResultService;

    @Resource
    private IBizAssessAnnualReviewTypeResultService bizAssessAnnualReviewTypeResultService;
    /**
     * 获取自己的公示的考核结果,第一层次和第二层次在一起
     *
     * @return
     */
    @GetMapping("/getAssessmentResult")
    public AjaxResult getAssessmentResult(Long userId) {
        BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResultVo = new BizAssessDeptExcellentEvaluationResultVo();
        //bizAssessDeptExcellentEvaluationResultVo.setAssessmentYear(year);
        bizAssessDeptExcellentEvaluationResultVo.setUserId(userId);
        bizAssessDeptExcellentEvaluationResultVo.setIsPublicity("1");
        List<Map<String,Object>> maps = new ArrayList<>();
        /**
         * 第一层次
         */
        List<BizAssessDeptExcellentEvaluationResultVo> bizAssessDeptExcellentEvaluationResultVos = bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultList(bizAssessDeptExcellentEvaluationResultVo);
        for (BizAssessDeptExcellentEvaluationResultVo assessDeptExcellentEvaluationResultVo : bizAssessDeptExcellentEvaluationResultVos) {
            Map<String, Object> stringObjectMap = BeanUtil.beanToMap(assessDeptExcellentEvaluationResultVo);
            maps.add(stringObjectMap);
        }

        /**
         *第二层次
         */
        BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResultVo = new BizAssessAnnualReviewTypeResultVo();
        //bizAssessAnnualReviewTypeResultVo.setAssessmentYear(year);
        //bizAssessAnnualReviewTypeResultVo.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        bizAssessAnnualReviewTypeResultVo.setUserId(userId);
        bizAssessAnnualReviewTypeResultVo.setIsPublicity("1");
        List<BizAssessAnnualReviewTypeResultVo> bizAssessAnnualReviewTypeResultVos = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(bizAssessAnnualReviewTypeResultVo);
        for (BizAssessAnnualReviewTypeResultVo assessAnnualReviewTypeResultVo : bizAssessAnnualReviewTypeResultVos) {
            Map<String, Object> stringObjectMap = BeanUtil.beanToMap(assessAnnualReviewTypeResultVo);
            maps.add(stringObjectMap);
        }
        /**
         *
         */
        HashMap<String, List<?>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("all",maps);
        stringListHashMap.put("first",bizAssessAnnualReviewTypeResultVos);
        stringListHashMap.put("second",bizAssessDeptExcellentEvaluationResultVos);
        return AjaxResult.success(stringListHashMap);
    }
}
