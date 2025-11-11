package com.cb.ai.constants;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-25 11:41
 * @Version: 1.0
 **/
public enum BishengApi {
    ASSISTANT("api/v2/assistant/chat/completions","毕昇助手api"),
    SKILL("api/v1/process","毕昇技能api"),
    UPLOAD_FILE_GET_URL("api/v1/knowledge/upload","上传文件获取url"),
    UPLOAD_FILE("api/v2/filelib/file","上传文件到知识库内");

    private String url;
    private String desc;

    BishengApi(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }
}
