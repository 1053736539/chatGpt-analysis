package com.cb.ai.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.ai.enums.OneKEOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-25 10:58
 * @Version: 1.0
 **/
public interface AIClient {

    /**
     * 通用数据清洗模型
     * @param input
     * @param outValueScheme
     * @return
     */
    JSONObject generalDataCleanByText(String input,String outValueScheme);

    /**
     * 上传文件和提示词，返回结果
     * @param file 文件
     * @param prompt 提示词
     * @return
     */
    String uploadFileAndPrompt(File file, String prompt);

    String uploadFileAndPrompt(MultipartFile file, String prompt);

    /**
     * 调用OnekE解析文本
     * @param operation 操作类型
     * @param text 源文本
     * @param schema 定义描述集合 参考示例{@link /resources/OneKE示例.json}
     * @param example 示例集合
     * @return
     */
    JSONObject textByOneKE(OneKEOperation operation, String text, JSONArray schema, JSONArray example);

    JSONObject textByOneKE(String instruction, String text, JSONArray schema, JSONArray example);

    /**
     * 从文本中解析实体及实体之间的关系（知识图谱）
     * @param text
     * @return
     */
    JSONObject parseRelationGraphByText(String text);

    /**
     * 根据问题从知识库中检索结果，然后提取实体及实体之间的关系（知识图谱）
     * @param question
     * @return
     */
    JSONObject parseRelationGraphByFileLib(String question);


    /**
     * 上传文件返回 file_path
     * @param file
     * @return
     */
    String uploadFileGetUrl(MultipartFile file);

    /**
     * 上传文件返回 file_path
     * @param file
     * @return
     */
    String uploadFileGetUrl(File file);

    /**
     * 上传内容到知识库
     * @param title 标题
     * @param content 内容
     * @param knowledgeId 知识库ID
     * @return
     */
    JSONObject uploadContentToKnowledge(String title, String content, Integer knowledgeId);

}
