package com.cb.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentVisit;
import com.cb.service.ITransferringStudentVisitService;
import com.cb.vo.StudentVisitUserVo;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 监督管理-走访基层Controller
 * 
 * @author yangxin
 * @date 2024-08-27
 */
@RestController
@RequestMapping("/transfer/visit")
public class TransferringStudentVisitController extends BaseController
{
    @Autowired
    private ITransferringStudentVisitService transferringStudentVisitService;

    /**
     * 查询监督管理-走访基层列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TransferringStudentVisit transferringStudentVisit)
    {
        Map<String, Object> params = transferringStudentVisit.getParams();
        if(null != params){
            params.entrySet().forEach(entry -> {
                if("null".equals(entry.getValue())){
                    entry.setValue(null);
                }
            });
        }
        startPage();
        List<TransferringStudentVisit> list = transferringStudentVisitService.selectTransferringStudentVisitList(transferringStudentVisit);
        return getDataTable(list);
    }

    /**
     * 导出监督管理-走访基层列表
     */
    @Log(title = "监督管理-走访基层", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransferringStudentVisit transferringStudentVisit)
    {
        List<TransferringStudentVisit> list = transferringStudentVisitService.selectTransferringStudentVisitList(transferringStudentVisit);
        ExcelUtil<TransferringStudentVisit> util = new ExcelUtil<TransferringStudentVisit>(TransferringStudentVisit.class);
        return util.exportExcel(list, "visit");
    }

    /**
     * 获取监督管理-走访基层详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentVisitService.selectTransferringStudentVisitById(id));
    }

    /**
     * 新增监督管理-走访基层
     */
    @Log(title = "监督管理-走访基层", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentVisit transferringStudentVisit)
    {
        return toAjax(transferringStudentVisitService.insertTransferringStudentVisit(transferringStudentVisit));
    }

    /**
     * 修改监督管理-走访基层
     */
    @Log(title = "监督管理-走访基层", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentVisit transferringStudentVisit)
    {
        return toAjax(transferringStudentVisitService.updateTransferringStudentVisit(transferringStudentVisit));
    }

    /**
     * 删除监督管理-走访基层
     */
    @Log(title = "监督管理-走访基层", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentVisitService.deleteTransferringStudentVisitByIds(ids));
    }

    /**
     * 导出单条记录word
     * @param request
     * @param response
     * @param id
     */
    @GetMapping("/exportWord/{id}")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        try{
            TransferringStudentVisit transferringStudentVisit = transferringStudentVisitService.selectTransferringStudentVisitById(id);
            ClassPathResource classPathResource = new ClassPathResource("template/transferring_student_visit.docx");
            InputStream is = classPathResource.getInputStream();
            HackLoopTableRenderPolicy tablePolicy = new HackLoopTableRenderPolicy();
            Configure configure = Configure.builder()
                    .bind("visitObj", tablePolicy)
                    .bind("visitUser", tablePolicy)
                    .build();

            String visitTime = transferringStudentVisit.getVisitTime();
            visitTime = DateUtil.format(DateUtil.parse(visitTime,"yyyy-MM-dd"),"yyyy年M月d日");
            transferringStudentVisit.setVisitTime(visitTime);
            Map<String, Object> data = BeanUtil.beanToMap(transferringStudentVisit);
            String visitObjJson = transferringStudentVisit.getVisitObjJson();
            String visitUserJson = transferringStudentVisit.getVisitUserJson();
            if(StringUtils.isNotBlank(visitObjJson)){
                data.put("visitObj", JSON.parseArray(visitObjJson, StudentVisitUserVo.class));
                data.remove("visitObjJson");
            } else {
                data.put("visitObj", Collections.emptyList());
            }
            if(StringUtils.isNotBlank(visitUserJson)){
                data.put("visitUser", JSON.parseArray(visitUserJson, StudentVisitUserVo.class));
                data.remove("visitUserJson");
            } else {
                data.put("visitUser", Collections.emptyList());
            }
            XWPFTemplate template = XWPFTemplate.compile(is, configure).render(data);
            template.writeAndClose(response.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
            throw new CustomException("导出word失败!");
        }
    }
}
