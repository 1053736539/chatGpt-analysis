package com.cb.adopt.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.cb.adopt.domain.BizAdoptRecordScore;
import com.cb.adopt.service.IBizAdoptRecordScoreService;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.util.ExcelUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.cb.adopt.domain.BizAdoptRecord;
import com.cb.adopt.service.IBizAdoptRecordService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

/**
 * 信息采用记录Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/adopt/adoptRecord")
public class BizAdoptRecordController extends BaseController
{
    @Autowired
    private IBizAdoptRecordService bizAdoptRecordService;
    @Autowired
    private IBizAdoptRecordScoreService bizAdoptRecordScoreService;
    /**
     * 查询信息采用记录列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAdoptRecord bizAdoptRecord)
    {
        startPage();
        List<BizAdoptRecord> list = bizAdoptRecordService.selectBizAdoptRecordList(bizAdoptRecord);
        return getDataTable(list);
    }

    /**
     * 导出信息采用记录列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecord:export')")
    @Log(title = "信息采用记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAdoptRecord bizAdoptRecord)
    {
        List<BizAdoptRecord> list = bizAdoptRecordService.selectBizAdoptRecordList(bizAdoptRecord);
        ExcelUtil<BizAdoptRecord> util = new ExcelUtil<BizAdoptRecord>(BizAdoptRecord.class);
        return util.exportExcel(list, "adoptRecord");
    }

    /**
     * 获取信息采用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAdoptRecordService.selectBizAdoptRecordById(id));
    }

    /**
     * 新增信息采用记录
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecord:add')")
    @Log(title = "信息采用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAdoptRecord bizAdoptRecord)
    {
       return  toAjax(bizAdoptRecordService.insertBizAdoptRecord(bizAdoptRecord));
    }

    /**
     * 修改信息采用记录
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecord:edit')")
    @Log(title = "信息采用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAdoptRecord bizAdoptRecord)
    {
        return toAjax(bizAdoptRecordService.updateBizAdoptRecord(bizAdoptRecord));
    }

    /**
     * 删除信息采用记录
     */
    @PreAuthorize("@ss.hasPermi('adopt:adoptRecord:remove')")
    @Log(title = "信息采用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAdoptRecordService.deleteBizAdoptRecordByIds(ids));
    }

    /**
     * 下载请假单导入excel模板
     * @param request
     * @param response
     */
    @GetMapping("/downloadExcelTpl")
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response){
        try{
            ClassPathResource classPathResource = new ClassPathResource("tpl/excel/导入模板.xlsx");
            InputStream is = classPathResource.getInputStream();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = "导入模板.xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(fileName,"utf-8") +"\"");
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 信息导入（Excel 使用客户提供的模板导入）
     */
    static final String FLAGS = "flags";
    static final String CONTENT = "content";
    @PostMapping(value = "/adoptRecordImport", consumes = "multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Map<String, Object> leaveListImport(@PathParam("file") MultipartFile file, HttpServletRequest request) throws IOException, Throwable {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer status = 1;
        String content = "导入成功!";
        InputStream fileIn = file.getInputStream();
        // 根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = ExcelUtils.getWorkbook(file);
        //Workbook wb0 = new HSSFWorkbook(fileIn);
        // 获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        List<BizAdoptRecord> bizAdoptRecords = new ArrayList<BizAdoptRecord>();
        List<BizAdoptRecordScore> bizAdoptRecordScores= new ArrayList<BizAdoptRecordScore>();
        int i = 0;
        List<String> errors = new ArrayList<>();
        int rowIndex = 0;
        for (Row row : sht0) {
            ++rowIndex;
            if (rowIndex > 1) {
                String str = ExcelUtils.getCellValue(row, 0);
                BizAdoptRecord adoptRecord = new BizAdoptRecord();
                if (StringUtils.isNotNull(str) && StringUtils.isNotEmpty(str)) {
                    //用户信息获取
                    adoptRecord.setIssueNo(ExcelUtils.getCellValue(row, 0));//期号
                    adoptRecord.setTitle(ExcelUtils.getCellValue(row, 1));// 标题
                    adoptRecord.setLevel(ExcelUtils.getCellValue(row, 2));//级别
                    adoptRecord.setCarrier(ExcelUtils.getCellValue(row, 3));//载体
                    adoptRecord.setAdoptDate(DateUtil.parseDate(ExcelUtils.getCellValue(row, 4)));//采用时间
                    adoptRecord.setRemark(ExcelUtils.getCellValue(row, 5));//备注
                    adoptRecord.setCreateBy(SecurityUtils.getUsername());
                    adoptRecord.setCreateTime(DateUtils.getNowDate());
                    adoptRecord.setId(IdUtils.randomUUID());
                    bizAdoptRecords.add(adoptRecord);
                    //  子表数据
                    //   主报单位
                    String str1 = ExcelUtils.getCellValue(row, 6);
                    if (StringUtils.isNotBlank(str1)){
                        String[] mainDepts = str1.split("[,，、/]");
                        for (String mainDept : mainDepts) {
                            System.out.println(mainDept.trim());
                            BizAdoptRecordScore bizAdoptRecordScore = new BizAdoptRecordScore();
                            bizAdoptRecordScore.setRecordId(adoptRecord.getId());
                            bizAdoptRecordScore.setDept(mainDept.trim());
                            bizAdoptRecordScore.setScore(Integer.valueOf(ExcelUtils.getCellValue(row, 7)));
                            bizAdoptRecordScore.setMainFlag(1);
                            bizAdoptRecordScores.add(bizAdoptRecordScore);
                        }
                    }
                    //  采用单位
                    String str2 = ExcelUtils.getCellValue(row, 8);
                    if (StringUtils.isNotBlank(str2)){
                        String[] otherDepts = str2.split("[,，/]");
                        for (String otherDept : otherDepts) {
                            System.out.println(otherDept.trim());
                            BizAdoptRecordScore bizAdoptRecordScore = new BizAdoptRecordScore();
                            bizAdoptRecordScore.setRecordId(adoptRecord.getId());
                            bizAdoptRecordScore.setDept(otherDept.trim());
                            bizAdoptRecordScore.setScore(Integer.valueOf(ExcelUtils.getCellValue(row, 9)));
                            bizAdoptRecordScore.setMainFlag(2);
                            bizAdoptRecordScores.add(bizAdoptRecordScore);
                        }
                    }
                    //  其他单位
                    String str3 = ExcelUtils.getCellValue(row, 10);
                    if (StringUtils.isNotBlank(str3)){
                        String[] otherDepts = str3.split("[,，/]");
                        for (String otherDept : otherDepts) {
                            System.out.println(otherDept.trim());
                            BizAdoptRecordScore bizAdoptRecordScore = new BizAdoptRecordScore();
                            bizAdoptRecordScore.setRecordId(adoptRecord.getId());
                            bizAdoptRecordScore.setDept(otherDept.trim());
                            bizAdoptRecordScore.setScore(Integer.valueOf(ExcelUtils.getCellValue(row, 11)));
                            bizAdoptRecordScore.setMainFlag(0);
                            bizAdoptRecordScores.add(bizAdoptRecordScore);
                        }
                    }
                } else {
                        break;
                    }
            }
        }
        if (StringUtils.isEmpty(bizAdoptRecords)) {
            status = 0;
            content = "导入数据不能为空";
        }
        if (status != 0) {
            if(bizAdoptRecords.size()>0){
                    try {
                        bizAdoptRecordService.insertBatchByExcel(bizAdoptRecords);
                        if(bizAdoptRecordScores.size()>0){
                            // 子表数据
                            bizAdoptRecordScoreService.insertBatch(bizAdoptRecordScores);
                        }
                        content="成功导入"+bizAdoptRecords.size()+"条数据";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                status = 0;
                content = "不能导入空数据";
            }
       }
        map.put(FLAGS, status);
        map.put(CONTENT, content);
        return map;
    }


}
