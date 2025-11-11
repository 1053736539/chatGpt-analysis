package com.cb.knowledge.base.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.cb.common.annotation.Log;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.file.FileUploadUtils;
import com.cb.knowledge.base.domain.entity.KnowledgeBase;
import com.cb.knowledge.base.domain.vo.KnowledgeBaseVo;
import com.cb.knowledge.base.service.IKnowledgeBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/15 16:40
 * @Copyright (c) 2025
 * @Description 知识库控制器
 */
@RestController
@RequestMapping("/knowledgeBase")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KnowledgeBaseController extends BaseController {

    private final IKnowledgeBaseService knowledgeBaseService;

    /**
     * 知识库查询
     *
     * @param file
     * @return
     * @throws Exception
     */
    @GetMapping("/page/list")
    public TableDataInfo pageList(KnowledgeBase knowledgeBase) {
        startPage();
        List<KnowledgeBase> knowledgeBases = knowledgeBaseService.selectDataList(knowledgeBase);
        return getDataTable(knowledgeBases);
    }

    /**
     * 获取历史标签
     *
     * @param fileType
     * @return
     * @throws Exception
     */
    @GetMapping("/getTags")
    public AjaxResult getTags(@RequestParam(required = false) String fileType) {
        AjaxResult result = AjaxResult.success();
        result.put(AjaxResult.DATA_TAG, knowledgeBaseService.getFileTags(fileType));
        return result;
    }

    /**
     * 知识库新增
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "知识库新增", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult saveBase(@RequestBody List<KnowledgeBaseVo> knowledgeBases) {
        if (knowledgeBaseService.saveData(knowledgeBases)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 知识库修改
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "知识库修改", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult updateBase(@RequestBody KnowledgeBaseVo knowledgeBase) {
        if (knowledgeBaseService.updateData(knowledgeBase)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 知识库删除
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "知识库删除", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult deleteBase(@RequestBody List<Integer> ids) {
        if (knowledgeBaseService.deleteData(ids)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 知识库文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "知识库文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = String.join(File.separator, RuoYiConfig.getUploadPath(), "knowledgeBase");
            Map<String, String> map = FileUploadUtils.originalUpload(filePath, file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", map.get("oldName"));
            ajax.put("url", map.get("path"));
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 知识库文件下载
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response,
                             @RequestParam(required = false) String rowUrl,
                             @RequestParam(required = false) Integer fileId,
                             @RequestParam(required = false) String fileName
    ) {
        if (StrUtil.isNotBlank(rowUrl)) {
            fileName = rowUrl.substring(rowUrl.lastIndexOf("/") + 1);
        } else if (fileId != null) {
            KnowledgeBase knowledgeBase = knowledgeBaseService.selectData(fileId);
            fileName = knowledgeBase.getFileName() + knowledgeBase.getFileSuffix();
            rowUrl = knowledgeBase.getFilePath();
        } else {
            throw new NullPointerException("下载文件地址或文件id不能为空");
        }
        // 设置响应内容类型为ZIP文件
        response.setContentType("application/octet-stream");
        // 设置响应头，指定文件名
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        // 读取文件内容并写入响应
        if (rowUrl.startsWith("/profile/upload")) {
            rowUrl = RuoYiConfig.getUploadPath() + rowUrl.replaceFirst("/profile/upload", "");
        } else if (rowUrl.startsWith("/profile/avatar")) {
            rowUrl = RuoYiConfig.getAvatarPath() + rowUrl.replaceFirst("/profile/avatar", "");
        } else {
            rowUrl = RuoYiConfig.getOtherAvatarPath() + rowUrl.replaceFirst("/profile/otherAvatar", "");
        }
        try (
            FileInputStream fis = new FileInputStream(rowUrl);
            OutputStream outs = response.getOutputStream()
        ) {
            IoUtil.copy(fis, outs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
