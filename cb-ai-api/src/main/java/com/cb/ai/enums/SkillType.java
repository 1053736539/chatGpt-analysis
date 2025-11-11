package com.cb.ai.enums;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-28 11:10
 * @Version: 1.0
 **/
public enum SkillType {
    TEXT_BY_ONE_KE("textByOneKE","调用OneKE处理文本"),
    PARSE_RELATION_GRAPH_BY_TEXT("parseRelationGraphByText","从文本中解析实体及实体之间的关系"),
    UPLOAD_FILE_AND_PROMPT("uploadFileAndPrompt","上传文件和提示词，并指定结果类型返回结果"),
    GENERATE_CLEAN("generalClean","通用数据清洗模型"),
    PARSE_RELATION_GRAPH_BY_FILE_LIB("parseRelationGraphByFileLib","从知识库内提取实体及实体之间的关系")
    ;
    private String key;
    private String desc;

    private SkillType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

}
