package com.cb.assess.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hutool.json.JSONUtil;
import com.cb.assess.domain.BizAssessDeptExcellentEvaluationResult;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationResultVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.service.IBizAssessDeptExcellentEvaluationResultService;
import com.cb.assess.utils.CycleUtil;
import com.cb.assess.utils.GradeUtil;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.easyexcel.TemplateExcelExpUtil;
import com.cb.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 年度处室（单位）年度考核优秀评议(最终部门上报结果)Controller
 * 
 * @author ruoyi
 * @date 2023-12-11
 */
@RestController
@RequestMapping("/assess/evaluationResult")
public class BizAssessDeptExcellentEvaluationResultController extends BaseController
{
    @Autowired
    private IBizAssessDeptExcellentEvaluationResultService bizAssessDeptExcellentEvaluationResultService;

    @Resource
    private ISysUserService sysUserService;
    /**
     * 查询年度处室（单位）年度考核优秀评议(最终部门上报结果)列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult)
    {
        startPage();
        //参数排斥
        if (StringUtils.isNoneBlank(bizAssessDeptExcellentEvaluationResult.getIsExcellent())){
            bizAssessDeptExcellentEvaluationResult.setRecommendGrade("");
        }
        List<BizAssessDeptExcellentEvaluationResultVo> list = bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultList(bizAssessDeptExcellentEvaluationResult);
        return getDataTable(list);
    }
    @GetMapping("/selectYears")
    public AjaxResult selectYears( )
    {
        List<BizAssessDeptExcellentEvaluationResultVo> list = bizAssessDeptExcellentEvaluationResultService.selectYears();
        return AjaxResult.success(list);
    }
    /**
     * 导出年度处室（单位）年度考核优秀评议(最终部门上报结果)列表
     */
    @Log(title = "年度处室（单位）年度考核优秀评议(最终部门上报结果)", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult) throws Exception {
        List<BizAssessDeptExcellentEvaluationResultVo> list = bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultList(bizAssessDeptExcellentEvaluationResult);
        for (int i = 0; i < list.size(); i++) {
            BizAssessDeptExcellentEvaluationResultVo o = list.get(i);
            o.setIndex(String.valueOf(i + 1));
            List<RegularAssessmentVo> regularAssessmentVos = o.getRegularAssessmentVos();
            if (null != regularAssessmentVos && !regularAssessmentVos.isEmpty()) {
                for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                    String s = CycleUtil.parseCycle(regularAssessmentVo.getCycle(), regularAssessmentVo.getCycleDesc());
                    if (null != s && s.contains("第一")) {
                        o.setQuarter1(regularAssessmentVo.getGrade());
                    }
                    if (null != s && s.contains("第二")) {
                        o.setQuarter2(regularAssessmentVo.getGrade());
                    }
                    if (null != s && s.contains("第三")) {
                        o.setQuarter3(regularAssessmentVo.getGrade());
                    }
                    if (null != s && s.contains("第四")) {
                        o.setQuarter4(regularAssessmentVo.getGrade());
                    }
                }
            }
            String type = "1";
            if ("3".equals(o.getIdentityType()) ||
                    "4".equals(o.getIdentityType()))
                type = "2";
            //把登记标签转成中文的
            String grade = GradeUtil.getGrade(type,
                    o.getRecommendGrade());
            o.setRecommendGrade(grade);

        }
        OutputStream outputStream = response.getOutputStream();
        TemplateExcelExpUtil.setClassPath("template/evaluationExportTemplate.xls").export(outputStream, bizAssessDeptExcellentEvaluationResult, list, "list");
        outputStream.flush();
    }
    /**
     * 获取年度处室（单位）年度考核优秀评议(最终部门上报结果)详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultById(id));
    }
    @Log(title = "年度处室（单位）年度考核优秀评议(最终部门上报结果)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult)
    {
        //先检查有没有创建
        BizAssessDeptExcellentEvaluationResultVo query = new BizAssessDeptExcellentEvaluationResultVo();
        query.setAssessmentYear(bizAssessDeptExcellentEvaluationResult.getAssessmentYear());
        query.setUserId(bizAssessDeptExcellentEvaluationResult.getUserId());
        List<BizAssessDeptExcellentEvaluationResultVo> list = bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultList(query);
        if (!list.isEmpty())return error("错误，当前人当前年度已有考核数据！！！");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("quarter1",bizAssessDeptExcellentEvaluationResult.getQuarter1());
        stringStringHashMap.put("quarter2",bizAssessDeptExcellentEvaluationResult.getQuarter2());
        stringStringHashMap.put("quarter3",bizAssessDeptExcellentEvaluationResult.getQuarter3());
        stringStringHashMap.put("quarter4",bizAssessDeptExcellentEvaluationResult.getQuarter4());
        String jsonStr = JSONUtil.toJsonStr(stringStringHashMap);
        //设置平时考核
        bizAssessDeptExcellentEvaluationResult.setRegularAssessment(jsonStr);
        //标记此为手动录入
        bizAssessDeptExcellentEvaluationResult.setIsEntry("1");
        bizAssessDeptExcellentEvaluationResult.setEscalation("1");
        bizAssessDeptExcellentEvaluationResult.setIsPublicity("0");
        SysUser sysUser = sysUserService.selectUserById(bizAssessDeptExcellentEvaluationResult.getUserId());
        if (null!=sysUser)
            bizAssessDeptExcellentEvaluationResult.setDeptId(sysUser.getDeptId());
        return toAjax(bizAssessDeptExcellentEvaluationResultService.insertBizAssessDeptExcellentEvaluationResult(bizAssessDeptExcellentEvaluationResult));
    }
    /**
     * 批量上报
     */
    @Log(title = "年度处室（单位）年度考核优秀评议(最终部门上报结果)", businessType = BusinessType.UPDATE)
    @PostMapping("/report")
    public AjaxResult report(@RequestBody List<BizAssessDeptExcellentEvaluationResult>  list)
    {
        for (BizAssessDeptExcellentEvaluationResult bizAssessDeptExcellentEvaluationResult : list) {
            //设置上报状态
            bizAssessDeptExcellentEvaluationResult.setEscalation("1");
            bizAssessDeptExcellentEvaluationResult.setIsPublicity("0");
            if (StringUtils.isNotBlank(bizAssessDeptExcellentEvaluationResult.getId()))
            bizAssessDeptExcellentEvaluationResultService.updateBizAssessDeptExcellentEvaluationResult(bizAssessDeptExcellentEvaluationResult);
            else bizAssessDeptExcellentEvaluationResultService.insertBizAssessDeptExcellentEvaluationResult(bizAssessDeptExcellentEvaluationResult);
        }
        return success();
    }
    @PutMapping
    public AjaxResult update(@RequestBody BizAssessDeptExcellentEvaluationResult  bizAssessDeptExcellentEvaluationResult)
    {
        bizAssessDeptExcellentEvaluationResultService.updateBizAssessDeptExcellentEvaluationResult(bizAssessDeptExcellentEvaluationResult);
        return success();
    }

    /**
     * 推送到公示
     * @param bizAssessDeptExcellentEvaluationResult
     * @return
     */
    @PostMapping("/push2Publicity")
    AjaxResult push2Publicity(@RequestBody BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult){
        bizAssessDeptExcellentEvaluationResultService.push2Publicity(bizAssessDeptExcellentEvaluationResult.getAssessmentYear(),
                bizAssessDeptExcellentEvaluationResult.getIsExcellent());
        return success();
    }
    /**
     * 删除年度处室（单位）年度考核优秀评议(最终部门上报结果)
     */
    @PreAuthorize("@ss.hasPermi('system:evaluationResult:remove')")
    @Log(title = "年度处室（单位）年度考核优秀评议(最终部门上报结果)", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessDeptExcellentEvaluationResultService.deleteBizAssessDeptExcellentEvaluationResultByIds(ids));
    }
}
