package com.cb.ai.controller;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.ai.enums.OneKEOperation;
import com.cb.ai.vo.APIVo;
import com.cb.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-25 17:12
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIClient aiClient;

    /**
     * 从文本中解析关系图谱
     * @param
     * @return
     */
    @PostMapping("parseRelationGraph")
    public AjaxResult parseRelationGraph(@RequestBody APIVo.ParseRelationGraphReq req){
        JSONObject jsonObject = aiClient.parseRelationGraphByText(req.getText());
        return AjaxResult.success(jsonObject);
    }

    /**
     * 根据问题从知识库中检索结果，然后提取实体及实体之间的关系（知识图谱）
     * @param req
     * @return
     */
    @PostMapping("parseRelationGraphByFileLib")
    public AjaxResult parseRelationGraphByFileLib(@RequestBody APIVo.ParseRelationGraphReq req){
        JSONObject jsonObject = aiClient.parseRelationGraphByFileLib(req.getText());
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("file2md")
    public AjaxResult file2md(HttpServletRequest request, @RequestParam(value = "question", required = true) String question){
        // 转换请求
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");

            if (file != null && !file.isEmpty()) {
                String text = aiClient.uploadFileAndPrompt(file, question);
                return AjaxResult.success(text);
            } else {
                return AjaxResult.error("No file found in the request");
            }
        } else {
            return AjaxResult.error("Request is not multipart, unable to extract file");
        }
    }

    @GetMapping("test")
    public AjaxResult test(HttpServletRequest request,@RequestBody APIVo.ParseRelationGraphReq req){
        OneKEOperation oneKEOperation = OneKEOperation.of(req.getOperation());
        JSONObject jsonObject;
        if(null != oneKEOperation){
            jsonObject = aiClient.textByOneKE(oneKEOperation, req.getText(), req.getSchema(), null);
        } else {
            jsonObject = aiClient.textByOneKE(req.getOperation(), req.getText(), req.getSchema(), null);
        }
        return AjaxResult.success(jsonObject);
    }
}
