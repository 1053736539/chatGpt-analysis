package com.cb.ai.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cb.ai.config.AIProperties;
import com.cb.ai.constants.BishengApi;
import com.cb.ai.enums.OneKEOperation;
import com.cb.ai.enums.OneKESchemaDefault;
import com.cb.ai.enums.SkillType;
import com.cb.ai.vo.OneKEVo;
import com.cb.ai.vo.RespVo;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Bisheng框架api调用
 * @Author: ARPHS
 * @Date: 2024-10-25 11:04
 * @Version: 1.0
 **/
@Component
@ConditionalOnProperty(prefix = "ai", name = "baseUrl")
public class BishengClient implements AIClient, InitializingBean {

    public static final Logger log = LoggerFactory.getLogger(BishengClient.class);

    @Resource
    private AIProperties aiProperties;

    @Override
    public JSONObject generalDataCleanByText(String input, String outValueScheme) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("文本不能为空");
        }
        String appId = aiProperties.getSkillAppIds().get(SkillType.GENERATE_CLEAN.getKey());
        JSONObject requestJson = getResJSON("request_json_tpl/generalClean.json");
        requestJson.getJSONObject("inputs").put("input", input);
        String url = String.format("%s/%s/%s", aiProperties.getBaseUrl(), BishengApi.SKILL.getUrl(), appId);
        String respStr = HttpUtil.post(url, JSONObject.toJSONString(requestJson));
        RespVo.BaseVo<RespVo.DataVo> baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo<RespVo.DataVo>>() {
        });
        if (baseVo.isOk()) {
            return JSONObject.parseObject(baseVo.getDataText());
        }
        String errorMsg = String.format("调用AI接口-从文本中解析出地址信息(%s)失败！错误信息：%s", outValueScheme, baseVo.getStatusMessage());
        throw new RuntimeException(errorMsg);
    }

    @Override
    public String uploadFileAndPrompt(File file, String prompt) {
        if (!FileUtil.isFile(file) || FileUtil.isEmpty(file)) {
            throw new IllegalArgumentException("请选择文件！");
        }
        if (StringUtils.isBlank(prompt)) {
            throw new IllegalArgumentException("提示词不能为空！");
        }
        String filePath = this.uploadFileGetUrl(file);
        return this.doUploadFileAndPrompt(filePath, prompt);
    }

    @Override
    public String uploadFileAndPrompt(MultipartFile file, String prompt) {
        if (null == file) {
            throw new IllegalArgumentException("请选择文件！");
        }
        if (StringUtils.isBlank(prompt)) {
            throw new IllegalArgumentException("提示词不能为空！");
        }
        String filePath = this.uploadFileGetUrl(file);
        return this.doUploadFileAndPrompt(filePath, prompt);
    }

    private String doUploadFileAndPrompt(String filePath, String prompt) {
        String appId = aiProperties.getSkillAppIds().get(SkillType.UPLOAD_FILE_AND_PROMPT.getKey());
        JSONObject requestJson = getResJSON("request_json_tpl/uploadFileAndPrompt.json");
        requestJson.getJSONObject("inputs").put("question", prompt);
        requestJson.getJSONObject("tweaks").getJSONObject("InputFileNode-db8ca").put("file_path", filePath);
        String url = String.format("%s/%s/%s", aiProperties.getBaseUrl(), BishengApi.SKILL.getUrl(), appId);
        String respStr = HttpUtil.post(url, JSONObject.toJSONString(requestJson));
        RespVo.BaseVo<RespVo.DataVo> baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo<RespVo.DataVo>>() {
        });
        if (baseVo.isOk()) {
            return baseVo.getDataText();

        }
        throw new RuntimeException("调用AI接口-上传文件并给出提示词和返回结果类型接口失败！错误信息：" + baseVo.getStatusMessage());
    }

    @Override
    public JSONObject textByOneKE(OneKEOperation operation, String text, JSONArray schema, JSONArray example) {
        if (null == operation || StringUtils.isBlank(text) ) {
            throw new IllegalArgumentException("参数不能为空！操作类型、源文本不能为空");
        }
        if( null == schema || schema.isEmpty()){
            schema = OneKESchemaDefault.of(operation.getCode()).getJsonArray();
        }
        return textByOneKE(operation.getInstruction(), text, schema, example);
    }

    @Override
    public JSONObject textByOneKE(String instruction, String text, JSONArray schema, JSONArray example) {
        if (StringUtils.isBlank(instruction) || StringUtils.isBlank(text) || null == schema || schema.isEmpty()) {
            throw new IllegalArgumentException("参数不能为空！任务描述、源文本、定义描述不能为空");
        }
        OneKEVo.Req req = new OneKEVo.Req();
        req.setInstruction(instruction);
        req.setInput(text);
        req.setSchema(schema);
        req.setExample(example);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(req);
        if (null == example || example.isEmpty()) {
            jsonObject.remove("example");
        }
        String appId = aiProperties.getSkillAppIds().get(SkillType.TEXT_BY_ONE_KE.getKey());
        JSONObject requestJson = getResJSON("request_json_tpl/textByOneKE.json");
        JSONObject inputs = requestJson.getJSONObject("inputs");
        inputs.put("input", jsonObject.toJSONString());
        log.info("OneKE请求内容：{}", jsonObject.toJSONString());
        String url = String.format("%s/%s/%s", aiProperties.getBaseUrl(), BishengApi.SKILL.getUrl(), appId);
        String respStr = HttpUtil.post(url, JSONObject.toJSONString(requestJson));
        log.info("OneKE返回原始文本：{}", respStr);
        RespVo.BaseVo<RespVo.DataVo> baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo<RespVo.DataVo>>() {
        });
        if (baseVo.isOk()) {
            try {
                String dataText = baseVo.getDataText();
                log.info("OneKE返回的数据文本：{}", dataText);
                return JSONObject.parseObject(dataText);
            } catch (Exception e) {
                throw new RuntimeException("AI接口-调用OneKE处理文本，响应解析失败！", e);
            }

        }
        throw new RuntimeException("调用AI接口-上调用OneKE处理文本接口失败！错误信息：" + baseVo.getStatusMessage());
    }

    @Override
    public JSONObject parseRelationGraphByText(String text) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("文本不能为空");
        }
        String appId = aiProperties.getSkillAppIds().get(SkillType.PARSE_RELATION_GRAPH_BY_TEXT.getKey());
        JSONObject requestJson = getResJSON("request_json_tpl/parseRelationGraph.json");
        requestJson.getJSONObject("inputs").put("input", text);
        String url = String.format("%s/%s/%s", aiProperties.getBaseUrl(), BishengApi.SKILL.getUrl(), appId);
        String respStr = HttpUtil.post(url, JSONObject.toJSONString(requestJson));
        RespVo.BaseVo<RespVo.DataVo> baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo<RespVo.DataVo>>() {
        });
        if (baseVo.isOk()) {
            return JSONObject.parseObject(baseVo.getDataText());
        }

        throw new RuntimeException("调用AI接口-从文本中解析实体及实体之间的关系（知识图谱）失败！错误信息：" + baseVo.getStatusMessage());
    }

    @Override
    public JSONObject parseRelationGraphByFileLib(String question) {
        if (StringUtils.isBlank(question)) {
            throw new IllegalArgumentException("问题不能为空");
        }
        String appId = aiProperties.getSkillAppIds().get(SkillType.PARSE_RELATION_GRAPH_BY_FILE_LIB.getKey());
        JSONObject requestJson = getResJSON("request_json_tpl/parseRelationGraphByFileLib.json");
        requestJson.getJSONObject("inputs").put("input", question);
        String url = String.format("%s/%s/%s", aiProperties.getBaseUrl(), BishengApi.SKILL.getUrl(), appId);
        String respStr = HttpUtil.post(url, JSONObject.toJSONString(requestJson));
        RespVo.BaseVo<RespVo.DataVo> baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo<RespVo.DataVo>>() {
        });
        if (baseVo.isOk()) {
            try {
                return JSONObject.parseObject(baseVo.getDataText());
            } catch (Exception e) {
                throw new RuntimeException(baseVo.getDataText());
            }
        }

        throw new RuntimeException("调用AI接口-根据问题从知识库中检索结果，然后提取实体及实体之间的关系（知识图谱）失败！错误信息：" + baseVo.getStatusMessage());
    }

    @Override
    public String uploadFileGetUrl(MultipartFile multipartFile) {
        if (null == multipartFile || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("请选择要上传的文件！");
        }
        File tempFile = null;
        try {
            tempFile = File.createTempFile(String.format("temp_%s", IdUtils.fastSimpleUUID()), "." + FileUtil.getSuffix(multipartFile.getOriginalFilename()));
            multipartFile.transferTo(tempFile);
            return this.uploadFileGetUrl(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != tempFile) {
                tempFile.delete();
            }
        }

    }

    @Override
    public String uploadFileGetUrl(File file) {
        if (!FileUtil.isFile(file) || FileUtil.isEmpty(file)) {
            throw new IllegalArgumentException("请选择文件！");
        }
        String url = String.format("%s/%s", aiProperties.getBaseUrl(), BishengApi.UPLOAD_FILE_GET_URL.getUrl());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("file", file);
        String respStr = HttpUtil.post(url, paramMap);
        RespVo.BaseVo<RespVo.UploadFileGetUrlRes> baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo<RespVo.UploadFileGetUrlRes>>() {
        });
        if (baseVo.isOk()) {
            return baseVo.getData().getFilePath();
        }
        throw new RuntimeException("调用AI接口-上传文件获取file_path失败！错误信息：" + baseVo.getStatusMessage());
    }

    private JSONObject getResJSON(String jsonPath) {
        try {
            // 读取resources目录下的JSON文件
            ClassPathResource resource = new ClassPathResource(jsonPath);
            InputStream inputStream = resource.getInputStream();
            // 使用BufferedReader读取输入流内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            // 将字符串解析为JSONObject
            JSONObject rst = JSON.parseObject(content.toString());
            return rst;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject uploadContentToKnowledge(String title, String content, Integer knowledgeId) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("文档标题不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw new IllegalArgumentException("文档内容不能为空");
        }
        String url = String.format("%s/%s/%s", aiProperties.getBaseUrl(), BishengApi.UPLOAD_FILE.getUrl(), knowledgeId);
        File file = null;
        JSONObject rst = new JSONObject();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            file = new File(FileUtil.getTmpDir(), title + ".txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            IoUtil.writeUtf8(fileOutputStream, true, content);
            paramMap.put("file", file);
            String respStr = HttpUtil.post(url, paramMap);
            RespVo.BaseVo baseVo = JSONObject.parseObject(respStr, new TypeReference<RespVo.BaseVo>() {
            });
            rst.put("status_code", baseVo.getStatusCode());
            rst.put("status_message", baseVo.getStatusMessage());
        } catch (Exception e) {
            rst.put("status_code", 500);
            rst.put("status_message", e.getMessage());
        } finally {
            if (file != null) {
                FileUtil.del(file);
            }
        }
        return rst;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == aiProperties || StringUtils.isBlank(aiProperties.getBaseUrl())) {
            throw new IllegalArgumentException("ai接口配置缺失！");
        }
    }
}
