package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessAnnualReviewTypeResult;
import com.cb.assess.domain.vo.BizAssessAnnualReviewStatisticsVo;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessAnnualReviewStatisticsMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.service.IBizAccessQuotaAllocateInfoService;
import com.cb.assess.service.IBizAssessAnnualReviewTypeResultService;
import com.cb.assess.utils.CycleUtil;
import com.cb.assess.vo.QuotaAllocateInfoVo;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.easyexcel.TemplateExcelExpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类Controller
 *
 * @author ruoyi
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/assess/annualReviewStatistics")
public class BizAssessAnnualReviewStatisticsController  extends BaseController {

    @Resource
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    @Autowired
    private IBizAssessAnnualReviewTypeResultService bizAssessAnnualReviewTypeResultService;

    @Resource
    private IBizAccessQuotaAllocateInfoService bizAccessQuotaAllocateInfoService;
    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewStatisticsVo)
    {
//        startPage();
        List<BizAssessAnnualReviewTypeResultVo> list = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(bizAssessAnnualReviewStatisticsVo);
        if (list.isEmpty()){
             list = bizAssessAnnualReviewStatisticsService.selectStatistics(bizAssessAnnualReviewStatisticsVo);
        }
        return getDataTable(list);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response, BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewStatisticsVo) throws Exception {
//        startPage();
        /**
         * 公务员
         */
        BizAssessAnnualReviewTypeResultVo query1 = new BizAssessAnnualReviewTypeResultVo();
        BeanUtils.copyProperties(bizAssessAnnualReviewStatisticsVo,query1);
        query1.setType("1");
        List<BizAssessAnnualReviewTypeResultVo> list1 = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(query1);
        if (list1.isEmpty()){
            list1 = bizAssessAnnualReviewStatisticsService.selectStatistics(query1);
        }
        /**
         * 事业单位
         */
        BizAssessAnnualReviewTypeResultVo query2 = new BizAssessAnnualReviewTypeResultVo();
        BeanUtils.copyProperties(bizAssessAnnualReviewStatisticsVo,query2);
        query2.setType("2");
        List<BizAssessAnnualReviewTypeResultVo> list2 = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(query2);
        if (list2.isEmpty()){
            list2 = bizAssessAnnualReviewStatisticsService.selectStatistics(query2);
        }
        /**
         * 这里导出A类表，所以，我们只管A类表的优秀票数，因为结果表只存优秀统计的票数，后面如果要改成所有结果都统计，则上面的数据查询要改，查全部统计，不止统计优秀
         */
        /**
         * 先处理序号
         */
        bizAssessAnnualReviewTypeResultService.setIndex(list1);
        bizAssessAnnualReviewTypeResultService.setIndex(list2);
        List<BizAssessAnnualReviewTypeResultVo> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        for (BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResultVo : list) {
            if (bizAssessAnnualReviewStatisticsVo.getExportType().equals("1")){
                bizAssessAnnualReviewTypeResultVo.setRecommendGrade1(bizAssessAnnualReviewTypeResultVo.getaTypeVotes()!=null?
                        bizAssessAnnualReviewTypeResultVo.getaTypeVotes().toString():"0");
            }else {
                bizAssessAnnualReviewTypeResultVo.setRecommendGrade1(bizAssessAnnualReviewTypeResultVo.getbTypeVotes()!=null?
                        bizAssessAnnualReviewTypeResultVo.getaTypeVotes().toString():"0");
            }
        }
        /**
         * 设置平时考核
         */
        bizAssessAnnualReviewTypeResultService.setRegularAssessment(list);
        /**
         * 根据条件拿一下优秀名额
         */
        QuotaAllocateInfoVo.TableItemInfo organ = bizAccessQuotaAllocateInfoService.getNoDeptAllocateInfo(bizAssessAnnualReviewStatisticsVo.getAssessmentYear(), "机关");
        QuotaAllocateInfoVo.TableItemInfo cause = bizAccessQuotaAllocateInfoService.getNoDeptAllocateInfo(bizAssessAnnualReviewStatisticsVo.getAssessmentYear(), "事业");

        ServletOutputStream outputStream = response.getOutputStream();
        HashMap<String, String> data = new HashMap<>();
        //机关优秀名额
        data.put("userNum1", null!=organ?String.valueOf(organ.getUserIds().size()):"0");
        data.put("allocateNum1", null!=organ?String.valueOf(organ.getAllocateNum()):"0");
        //事业优秀名额
        data.put("userNum2", null!=cause?String.valueOf(cause.getUserIds().size()):"0");
        data.put("allocateNum2", null!=cause?String.valueOf(cause.getAllocateNum()):"0");
        //年度
        data.put("year", bizAssessAnnualReviewStatisticsVo.getAssessmentYear());

        HashMap<String, List<?>> listMap = new HashMap<>();
        listMap.put("list1",list1);
        listMap.put("list2",list2);
        String path="";
        if (bizAssessAnnualReviewStatisticsVo.getExportType().equals("1")){
            path="template/Atype.xls";
        }else {
            path="template/Btype.xls";
        }
        TemplateExcelExpUtil.setClassPath(path).export(outputStream,data,listMap);
        outputStream.flush();
    }

    @GetMapping("/exportBySummary")
    public void exportBySummary(HttpServletResponse response, BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewStatisticsVo) throws Exception {
        /**
         * 公务员
         */
        BizAssessAnnualReviewTypeResultVo query1 = new BizAssessAnnualReviewTypeResultVo();
        BeanUtils.copyProperties(bizAssessAnnualReviewStatisticsVo,query1);
        query1.setType("1");
        List<BizAssessAnnualReviewTypeResultVo> list1 = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(query1);
        if (list1.isEmpty()){
            list1 = bizAssessAnnualReviewStatisticsService.selectStatistics(query1);
        }
        /**
         * 事业单位
         */
        BizAssessAnnualReviewTypeResultVo query2 = new BizAssessAnnualReviewTypeResultVo();
        BeanUtils.copyProperties(bizAssessAnnualReviewStatisticsVo,query2);
        query2.setType("2");
        List<BizAssessAnnualReviewTypeResultVo> list2 = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(query2);
        if (list2.isEmpty()){
            list2 = bizAssessAnnualReviewStatisticsService.selectStatistics(query2);
        }
        /**
         * 这里导出A类表，所以，我们只管A类表的优秀票数，因为结果表只存优秀统计的票数，后面如果要改成所有结果都统计，则上面的数据查询要改，查全部统计，不止统计优秀
         */
        /**
         * 先处理序号
         */
        bizAssessAnnualReviewTypeResultService.setIndex(list1);
        bizAssessAnnualReviewTypeResultService.setIndex(list2);
        /**
         * 设置平时考核
         */
        bizAssessAnnualReviewTypeResultService.setRegularAssessment(list1);
        bizAssessAnnualReviewTypeResultService.setRegularAssessment(list2);
        /**
         * 根据条件拿一下优秀名额
         */
        QuotaAllocateInfoVo.TableItemInfo organ = bizAccessQuotaAllocateInfoService.getNoDeptAllocateInfo(bizAssessAnnualReviewStatisticsVo.getAssessmentYear(), "机关");
        QuotaAllocateInfoVo.TableItemInfo cause = bizAccessQuotaAllocateInfoService.getNoDeptAllocateInfo(bizAssessAnnualReviewStatisticsVo.getAssessmentYear(), "事业");

        ServletOutputStream outputStream = response.getOutputStream();
        HashMap<String, String> data = new HashMap<>();
        //机关优秀名额
        data.put("userNum1", null!=organ?String.valueOf(organ.getUserIds().size()):"0");
        data.put("allocateNum1", null!=organ?String.valueOf(organ.getAllocateNum()):"0");
        //事业优秀名额
        data.put("userNum2", null!=cause?String.valueOf(cause.getUserIds().size()):"0");
        data.put("allocateNum2", null!=cause?String.valueOf(cause.getAllocateNum()):"0");
        //年度
        data.put("year", bizAssessAnnualReviewStatisticsVo.getAssessmentYear());

        HashMap<String, List<?>> listMap = new HashMap<>();
        listMap.put("list1",list1);
        listMap.put("list2",list2);
        String path="";
        path="template/summaryABtype.xls";
        TemplateExcelExpUtil.setClassPath(path).export(outputStream,data,listMap);
        outputStream.flush();
    }
    //结果公示数据
    @GetMapping("/listByPublicityView")
    public TableDataInfo listByPublicityView(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewStatisticsVo)
    {
//        startPage();
        List<BizAssessAnnualReviewTypeResultVo> list = bizAssessAnnualReviewTypeResultService.selectBizAssessAnnualReviewTypeResultList(bizAssessAnnualReviewStatisticsVo);
        return getDataTable(list);
    }
    //小哈讯所有年份
    @GetMapping("/selectYears")
    public AjaxResult selectYears(){
        return AjaxResult.success(bizAssessAnnualReviewStatisticsService.selectYears());
    }
    //根据用户id和年份查询平时考核
    @GetMapping("/regularAssessment")
    public AjaxResult regularAssessment(Long userId,String year){
        ArrayList<Long> longs = new ArrayList<>();
        longs.add(userId);
        return AjaxResult.success(bizAssessAnnualReviewStatisticsService.regularAssessment(longs,year));
    }
}
