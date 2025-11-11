package com.cb.ai.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.ai.domain.AiSkillModel;
import com.cb.ai.service.IAiSkillModelService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * AI 技能模型Controller
 *
 * @author ouyang
 * @date 2024-11-02
 */
@RestController
@RequestMapping("/ai/aiModel")
public class AiSkillModelController extends BaseController {
    @Autowired
    private IAiSkillModelService aiSkillModelService;

    /**
     * 查询AI 技能模型列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AiSkillModel aiSkillModel) {
        startPage();
        List<AiSkillModel> list = aiSkillModelService.selectAiSkillModelList(aiSkillModel);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public AjaxResult listAll(AiSkillModel model) {
        List<AiSkillModel> list = aiSkillModelService.selectAiSkillModelList(model);
        return AjaxResult.success(list);
    }


   /* @GetMapping("/listAll")
    public AjaxResult listAll() {
        List<AiSkillModel> list = aiSkillModelService.selectAllAiSkillModelList();
        return AjaxResult.success(list);
    }*/

    /**
     * 导出AI 技能模型列表
     */
    @Log(title = "AI 技能模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AiSkillModel aiSkillModel) {
        List<AiSkillModel> list = aiSkillModelService.selectAiSkillModelList(aiSkillModel);
        ExcelUtil<AiSkillModel> util = new ExcelUtil<AiSkillModel>(AiSkillModel.class);
        return util.exportExcel(list, "aiModel");
    }

    /**
     * 获取AI 技能模型详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(aiSkillModelService.selectAiSkillModelById(id));
    }

    /**
     * 新增AI 技能模型
     */
    @Log(title = "AI 技能模型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiSkillModel aiSkillModel) {
        return toAjax(aiSkillModelService.insertAiSkillModel(aiSkillModel));
    }

    /**
     * 修改AI 技能模型
     */
    @Log(title = "AI 技能模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiSkillModel aiSkillModel) {
        return toAjax(aiSkillModelService.updateAiSkillModel(aiSkillModel));
    }

    /**
     * 删除AI 技能模型
     */
    @Log(title = "AI 技能模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(aiSkillModelService.deleteAiSkillModelByIds(ids));
    }

    @Log(title = "AI 技能模型测试-文本", businessType = BusinessType.OTHER)
    @PostMapping("/textTest")
    public AjaxResult textTest(@RequestBody AiSkillModel model) {
        return aiSkillModelService.textTest(model);
    }

    @Log(title = "AI 技能模型测试-文件", businessType = BusinessType.OTHER)
    @PostMapping("/fileTest")
    public AjaxResult fileTest(HttpServletRequest request,
                               @RequestParam(value = "modelId", required = true) String modelId,
                               @RequestParam(value = "tipWords", required = true) String tipWords
    ) {
        // 转换请求
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        if (!resolver.isMultipart(request)) {
            return AjaxResult.error("Request is not multipart, unable to extract file");
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null && file.isEmpty()) {
            return AjaxResult.error("No file found in the request");
        }
        AiSkillModel model = aiSkillModelService.selectAiSkillModelById(modelId);
        model.setTipWords(tipWords);
        return aiSkillModelService.fileTest(file, model);
    }

    @PostMapping("/runFileModel")
    public AjaxResult runFileModel(HttpServletRequest request,
                                   @RequestParam(value = "modelId", required = true) String modelId){
        // 转换请求
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        if (!resolver.isMultipart(request)) {
            return AjaxResult.error("请上传文件！");
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null && file.isEmpty()) {
            return AjaxResult.error("请上传文件！");
        }
        return aiSkillModelService.runFileModel(file, modelId);
    }
}
