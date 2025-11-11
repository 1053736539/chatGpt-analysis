package com.cb.assess.controller;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.cb.assess.domain.BizAssessDeptExcellentEvaluation;
import com.cb.assess.domain.BizAssessDeptExcellentEvaluationResult;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationResultVo;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.service.IBizAccessQuotaAllocateInfoService;
import com.cb.assess.service.IBizAssessDeptExcellentEvaluationResultService;
import com.cb.assess.service.IBizAssessDeptExcellentEvaluationService;
import com.cb.assess.utils.CycleUtil;
import com.cb.assess.utils.GradeUtil;
import com.cb.assess.vo.QuotaAllocateInfoVo;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.easyexcel.TemplateExcelExpUtil;
import com.cb.system.service.ISysUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * 年度处室（单位）年度考核优秀评议Controller
 *
 * @author ruoyi
 * @date 2023-12-09
 */
@RestController
@RequestMapping("/assess/evaluation")
public class BizAssessDeptExcellentEvaluationController extends BaseController {
    @Autowired
    private IBizAssessDeptExcellentEvaluationService bizAssessDeptExcellentEvaluationService;
    @Autowired
    private IBizAssessDeptExcellentEvaluationResultService bizAssessDeptExcellentEvaluationResultService;
    @Autowired
    private IBizAccessQuotaAllocateInfoService bizAccessQuotaAllocateInfoService;

    @Resource
    private ISysUserService userService;
    /**
     * 查询自己的评议别人的，别人评议我的，接口
     */
//    @PreAuthorize("@ss.hasPermi('system:evaluation:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        startPage();
        if (StringUtils.isBlank(bizAssessDeptExcellentEvaluation.getDataType())) {
            bizAssessDeptExcellentEvaluation.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            bizAssessDeptExcellentEvaluation.setDiscussantUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        } else if (bizAssessDeptExcellentEvaluation.getDataType().equals("1")) {
            bizAssessDeptExcellentEvaluation.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        } else if (bizAssessDeptExcellentEvaluation.getDataType().equals("2")) {
            bizAssessDeptExcellentEvaluation.setDiscussantUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationList(bizAssessDeptExcellentEvaluation);
        return getDataTable(list);
    }

    /**
     * 全部填写详情
     *
     * @param bizAssessDeptExcellentEvaluation
     * @return
     */
    @GetMapping("/listAll")
    public TableDataInfo listAll(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        startPage();
        //接口排斥,有部门id参数就放弃部门名称
        if (null != bizAssessDeptExcellentEvaluation.getDeptId()) {
            bizAssessDeptExcellentEvaluation.setDeptName("");
        }
        List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationList(bizAssessDeptExcellentEvaluation);
        return getDataTable(list);
    }

    //    @PreAuthorize("@ss.hasPermi('system:evaluation:listDept')")
    @GetMapping("/listDept")
    public TableDataInfo listDept(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        startPage();
        bizAssessDeptExcellentEvaluation.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationList(bizAssessDeptExcellentEvaluation);
        return getDataTable(list);
    }

    @GetMapping("/listByMyCreate")
    public TableDataInfo listByMyCreate(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        startPage();
//        bizAssessDeptExcellentEvaluation.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        bizAssessDeptExcellentEvaluation.setDeptId(deptId);
        List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationList(bizAssessDeptExcellentEvaluation);
        List<String> collect = list.stream().map(BaseEntity::getCreateBy).distinct().collect(Collectors.toList());
        List<SysUser> sysUsers = userService.selectUserListByNames(collect);
        Map<String, SysUser> collect1 = sysUsers.stream().collect(Collectors.toMap(SysUser::getUserName, e -> e, (k1, k2) -> k1));
        for (BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluationVo : list) {
            SysUser sysUser = collect1.get(bizAssessDeptExcellentEvaluationVo.getCreateBy());
            if (null != sysUser) {
                bizAssessDeptExcellentEvaluationVo.setCreateBy(sysUser.getName());
            }
        }
        return getDataTable(list);
    }

    //部门内统计
//    @PreAuthorize("@ss.hasPermi('system:evaluation:statisticsByDept')")
    @GetMapping("/selectStatisticsByDept")
    public AjaxResult selectStatisticsByDept(BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult = new BizAssessDeptExcellentEvaluationResultVo();
        bizAssessDeptExcellentEvaluationResult.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        List<BizAssessDeptExcellentEvaluationResultVo> bizAssessDeptExcellentEvaluationResults = bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultList(bizAssessDeptExcellentEvaluationResult);
        if (bizAssessDeptExcellentEvaluationResults.isEmpty()) {
            bizAssessDeptExcellentEvaluation.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
            List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectStatisticsByDept(bizAssessDeptExcellentEvaluation);
            return AjaxResult.success(list);
        }
        //        startPage();
        return AjaxResult.success(bizAssessDeptExcellentEvaluationResults);
    }

    //查询所有年
    @GetMapping("/selectYears")
    public AjaxResult selectYears() {
        List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectYears();
        return AjaxResult.success(list);
    }

    @GetMapping("/exportByDept")
    public void exportByDept(HttpServletResponse response, BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        /**
         * 获取列表的数据
         */
        // 取出模板
        try {
            BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult = new BizAssessDeptExcellentEvaluationResultVo();
            bizAssessDeptExcellentEvaluationResult.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
            List<BizAssessDeptExcellentEvaluationResultVo> results = bizAssessDeptExcellentEvaluationResultService.selectBizAssessDeptExcellentEvaluationResultList(bizAssessDeptExcellentEvaluationResult);
            if (results.isEmpty()) {
                bizAssessDeptExcellentEvaluation.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
                List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectStatisticsByDept(bizAssessDeptExcellentEvaluation);
                for (int i = 0; i < list.size(); i++) {
                    BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluationVo = list.get(i);
                    bizAssessDeptExcellentEvaluationVo.setIndex(String.valueOf(i + 1));
                    List<RegularAssessmentVo> regularAssessmentVos = bizAssessDeptExcellentEvaluationVo.getRegularAssessmentVos();
                    if (null != regularAssessmentVos && !regularAssessmentVos.isEmpty()) {
                        for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                            String s = CycleUtil.parseCycle(regularAssessmentVo.getCycle(), regularAssessmentVo.getCycleDesc());
                            if (null != s && s.contains("第一")) {
                                bizAssessDeptExcellentEvaluationVo.setQuarter1(regularAssessmentVo.getGrade());
                            }
                            if (null != s && s.contains("第二")) {
                                bizAssessDeptExcellentEvaluationVo.setQuarter2(regularAssessmentVo.getGrade());
                            }
                            if (null != s && s.contains("第三")) {
                                bizAssessDeptExcellentEvaluationVo.setQuarter3(regularAssessmentVo.getGrade());
                            }
                            if (null != s && s.contains("第四")) {
                                bizAssessDeptExcellentEvaluationVo.setQuarter4(regularAssessmentVo.getGrade());
                            }
                        }
                    }
                    String type = "1";
                    if ("3".equals(bizAssessDeptExcellentEvaluationVo.getIdentityType()) ||
                            "4".equals(bizAssessDeptExcellentEvaluationVo.getIdentityType()))
                        type = "2";
                    //把登记标签转成中文的
                    String grade = GradeUtil.getGrade(type,
                            bizAssessDeptExcellentEvaluationVo.getRecommendGrade());
                    bizAssessDeptExcellentEvaluationVo.setRecommendGrade(grade);
                }
                OutputStream outputStream = response.getOutputStream();
                TemplateExcelExpUtil.setClassPath("template/evaluationExportTemplate.xls").export(outputStream, bizAssessDeptExcellentEvaluation, list, "list");
                outputStream.flush();
            } else {
                for (int i = 0; i < results.size(); i++) {
                    BizAssessDeptExcellentEvaluationResultVo o = results.get(i);
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
                TemplateExcelExpUtil.setClassPath("template/evaluationExportTemplate.xls").export(outputStream, bizAssessDeptExcellentEvaluation, results, "list");
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return AjaxResult.success(null);
    }

    /**
     * 导出年度处室（单位）年度考核优秀评议列表
     */
//    @PreAuthorize("@ss.hasPermi('system:evaluation:export')")
//    @Log(title = "年度处室（单位）年度考核优秀评议", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public void export(HttpServletResponse response, BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) throws IOException {
//        if (StringUtils.isBlank(bizAssessDeptExcellentEvaluation.getDataType())) {
//            bizAssessDeptExcellentEvaluation.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//            bizAssessDeptExcellentEvaluation.setDiscussantUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//        } else if (bizAssessDeptExcellentEvaluation.getDataType().equals("1")) {
//            bizAssessDeptExcellentEvaluation.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//        } else if (bizAssessDeptExcellentEvaluation.getDataType().equals("2")) {
//            bizAssessDeptExcellentEvaluation.setDiscussantUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//        }
//        List<BizAssessDeptExcellentEvaluationVo> list = bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationList(bizAssessDeptExcellentEvaluation);
//        //创建一个excel表格
//        HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象
//        HSSFSheet sheet = wb.createSheet(bizAssessDeptExcellentEvaluation.getAssessmentYear() + "年度处室（单位）年度考核优秀评议表");//建立sheet对象
//        /**
//         * 创建样式
//         */
//        //设置样式
//        CellStyle blackStyle = wb.createCellStyle();
//        CellStyle centerStyle = wb.createCellStyle();
//        //自动换行*重要*
//        blackStyle.setWrapText(true);
//        //水平居中
//        centerStyle.setAlignment(HorizontalAlignment.CENTER);
//        //垂直居中
//        blackStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        //边框样式及颜色
//        blackStyle.setBorderRight(BorderStyle.THIN);
//        blackStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
//        blackStyle.setBorderLeft(BorderStyle.THIN);
//        blackStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
//        blackStyle.setBorderTop(BorderStyle.THIN);
//        blackStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
//        blackStyle.setBorderBottom(BorderStyle.THIN);
//        blackStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
//
//        //在sheet里创建第一行，参数为行索引
//        HSSFRow row1 = sheet.createRow(0);
//        //创建单元格
//        HSSFCell cell = row1.createCell(0);
//        //设置单元格内容
//        cell.setCellValue(bizAssessDeptExcellentEvaluation.getAssessmentYear() + "年度处室（单位）年度考核优秀评议表");
////        for (int i = 1; i < 8; i++) {
////            //创建单元格
////            HSSFCell cellTitle = row1.createCell(i);
////        }
//        //自动列宽
//        sheet.autoSizeColumn(1, true);
//        sheet.autoSizeColumn(2, true);
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//标题合并
//        //创建单元格并设置单元格内容
//        //在sheet里创建第二行
//        HSSFRow row2 = sheet.createRow(1);
//        row2.createCell(0).setCellValue("序号");
//        row2.createCell(1).setCellValue("处室");
//        row2.createCell(2).setCellValue("姓名");
//        row2.createCell(3).setCellValue("平时考核等次（季度）");
//        row2.createCell(4).setCellValue("");
//        row2.createCell(5).setCellValue("");
//        row2.createCell(6).setCellValue("");
//        row2.createCell(7).setCellValue("年度考核“优秀”人选");
//        //在sheet里创建第三行
//        HSSFRow row3 = sheet.createRow(2);
//        row3.createCell(0).setCellValue("");
//        row3.createCell(1).setCellValue("");
//        row3.createCell(2).setCellValue("");
//        row3.createCell(3).setCellValue("");
//        row3.createCell(3).setCellValue("1");
//        row3.createCell(4).setCellValue("2");
//        row3.createCell(5).setCellValue("3");
//        row3.createCell(6).setCellValue("4");
//        row3.createCell(7).setCellValue("");
//        //设置表头样式
//        for (int i = 1; i < 3; i++) {
//            HSSFRow row = sheet.getRow(i);
//            for (int j = 0; j < 8; j++) {
//                row.getCell(j).setCellStyle(blackStyle);
//            }
//        }
//        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));//序号
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));//处室
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));//姓名
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 6));//平时考核
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));//优秀人选
//        Integer rowNum = 3;
//        for (int i = 0; i < list.size(); i++) {
//            BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluationVo = list.get(i);
//            //行号从第四行开始
//            HSSFRow row = sheet.createRow(rowNum);
//            HSSFCell cell1 = row.createCell(0);
//            cell1.setCellValue(i + 1);
//            cell1.setCellStyle(blackStyle);
//            row.createCell(1).setCellValue(bizAssessDeptExcellentEvaluationVo.getDeptName() == null ? "" : bizAssessDeptExcellentEvaluationVo.getDeptName());
//            row.createCell(2).setCellValue(bizAssessDeptExcellentEvaluationVo.getName() == null ? "" : bizAssessDeptExcellentEvaluationVo.getName());
//            row.createCell(3).setCellValue("1");
//            row.createCell(4).setCellValue("2");
//            row.createCell(5).setCellValue("3");
//            row.createCell(6).setCellValue("4");
//            row.createCell(7).setCellValue("1".equals(bizAssessDeptExcellentEvaluationVo.getRecommendGrade()) ? "优秀" : "");
//            rowNum++;
//        }
//        //设置数据样式
//        for (int i = 3; i < rowNum; i++) {
//            HSSFRow row = sheet.getRow(i);
//            for (int j = 0; j < 8; j++) {
//                row.getCell(j).setCellStyle(blackStyle);
//            }
//        }
//        HSSFRow rowLast = sheet.createRow(rowNum);
//        QuotaAllocateInfoVo.TableItemInfo deptAllocateInfo = bizAccessQuotaAllocateInfoService.getDeptAllocateInfo(SecurityUtils.getLoginUser().getUser().getDeptId(), bizAssessDeptExcellentEvaluation.getAssessmentYear());
//        String deptName = deptAllocateInfo == null ? "" : deptAllocateInfo.getDeptName();
//        Integer AllocateNum = deptAllocateInfo == null ? 0 : deptAllocateInfo.getAllocateNum();
//        String str1 = "1.按《" + bizAssessDeptExcellentEvaluation.getAssessmentYear() + "年度机关事业单位考核人数及优秀等次名额分配表》" + deptName +
//                "优秀名额为" + AllocateNum + "名";
//        String str2 = "2.按考核方案要求，年度确定优秀等次当年平时考核结果“好”等次累计次数要达到一半以上，且无一般、较差等次；多填无效。";
//        String str3 = "3.请在年度考核推荐等次栏内划“√”";
//        for (int i = 0; i < 8; i++) {
//            HSSFCell cell1 = rowLast.createCell(i);
//            if (i == 0) {
//                cell1.setCellValue("注：" + str1 + "\n" + str2 + "\n" + str3);
//            }
//            cell1.setCellStyle(blackStyle);
//        }
//
////        cell1.setCellStyle(blackStyle);
//
//        rowLast.setHeight((short) 1000);
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 7));//
////        sheet.getRow(rowNum).getCell(0).setCellStyle(blackStyle);
////        File file = new File("E:\\a.xlsx");
////        OutputStream outputStream1 = Files.newOutputStream(file.toPath());
//        //写出到下载
//        OutputStream outputStream = response.getOutputStream();
//        wb.write(outputStream);
////        wb.write(outputStream1);
////        outputStream1.close();
//        wb.close();
//        outputStream.flush();
////        outputStream.close();
////        return success();
//    }

    /**
     * 获取年度处室（单位）年度考核优秀评议详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:evaluation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationById(id));
    }

    /**
     * 新增年度处室（单位）年度考核优秀评议
     */
//    @PreAuthorize("@ss.hasPermi('system:evaluation:add')")
    @Log(title = "年度处室（单位）年度考核优秀评议", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessDeptExcellentEvaluationVo bizAssessDeptExcellentEvaluation) {
        //先拿到当前年度所有数据
        BizAssessDeptExcellentEvaluationVo query = new BizAssessDeptExcellentEvaluationVo();
        query.setAssessmentYear(bizAssessDeptExcellentEvaluation.getAssessmentYear());
        List<BizAssessDeptExcellentEvaluationVo> bizAssessDeptExcellentEvaluationVos = bizAssessDeptExcellentEvaluationService.selectBizAssessDeptExcellentEvaluationList(query);
        Map<String, BizAssessDeptExcellentEvaluationVo> collect = bizAssessDeptExcellentEvaluationVos.stream().collect(Collectors.toMap(e -> e.getUserId() + "-" + e.getDiscussantUserId(), o -> o));
        if (StringUtils.isBlank(bizAssessDeptExcellentEvaluation.getUserIds()) || StringUtils.isBlank(bizAssessDeptExcellentEvaluation.getDiscussantUserIds())) {
            return error("评议对象或被评议对象为空");
        }
        //前端拿到用户列表，再传过来，然后再拿他的那些身份信息
        Map<Long, SysUser> sysUserHashMap = new HashMap<>();
        if (null != bizAssessDeptExcellentEvaluation.getSysUsers()) {
            sysUserHashMap = bizAssessDeptExcellentEvaluation.getSysUsers().stream().collect(Collectors.toMap(SysUser::getUserId, o -> o));
        }
        String[] userIds = bizAssessDeptExcellentEvaluation.getUserIds().split(",");
        String[] disUserIds = bizAssessDeptExcellentEvaluation.getDiscussantUserIds().split(",");
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        for (String userId : userIds) {
            for (String disUserId : disUserIds) {
                //因为他们自己也要评自己，所以这里先注释掉
//                if (!userId.equals(disUserId))
                //没有的才创建
                if (null == collect.get(userId + "-" + disUserId)) {
                    BizAssessDeptExcellentEvaluation save = new BizAssessDeptExcellentEvaluation();
                    BeanUtils.copyProperties(bizAssessDeptExcellentEvaluation, save);
                    save.setUserId(Long.valueOf(userId));
                    save.setDiscussantUserId(Long.valueOf(disUserId));
                    save.setDeptId(deptId);
                    SysUser sysUser = sysUserHashMap.get(Long.valueOf(userId));
                    if (null != sysUser) {
                        save.setIdentityType(sysUser.getIdentityType());
                    }
                    bizAssessDeptExcellentEvaluationService.insertBizAssessDeptExcellentEvaluation(save);
                }
            }
        }
        return success("成功！");
    }

    /**
     * 修改年度处室（单位）年度考核优秀评议
     */
//    @PreAuthorize("@ss.hasPermi('system:evaluation:edit')")
    @Log(title = "年度处室（单位）年度考核优秀评议", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessDeptExcellentEvaluation bizAssessDeptExcellentEvaluation) {
        return toAjax(bizAssessDeptExcellentEvaluationService.updateBizAssessDeptExcellentEvaluation(bizAssessDeptExcellentEvaluation));
    }

    //批量评议
//    @PreAuthorize("@ss.hasPermi('assess:evaluation:saveBatch')")
    @PutMapping("/batchEvaluate")
    public AjaxResult batchEvaluate(@RequestBody List<BizAssessDeptExcellentEvaluation> bizAssessDeptExcellentEvaluations) {
        for (BizAssessDeptExcellentEvaluation bizAssessDeptExcellentEvaluation : bizAssessDeptExcellentEvaluations) {
            bizAssessDeptExcellentEvaluationService.updateBizAssessDeptExcellentEvaluation(bizAssessDeptExcellentEvaluation);
        }
        return success();
    }

    /**
     * 删除年度处室（单位）年度考核优秀评议
     */
//    @PreAuthorize("@ss.hasPermi('system:evaluation:remove')")
    @Log(title = "年度处室（单位）年度考核优秀评议", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessDeptExcellentEvaluationService.deleteBizAssessDeptExcellentEvaluationByIds(ids));
    }
}
