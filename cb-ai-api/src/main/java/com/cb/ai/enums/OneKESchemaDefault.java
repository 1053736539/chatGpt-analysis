package com.cb.ai.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 实体类型枚举
 * @Author: ARPHS
 * @Date: 2024-10-25 14:03
 * @Version: 1.0
 **/
public enum OneKESchemaDefault {

    NER("NER", "NER默认Schema", "schema_json_tpl/NER_default.json"),
    RE("RE", "RE默认Schema", "schema_json_tpl/RE_default.json"),
    KGC("KGC", "KGC默认Schema", "schema_json_tpl/KGC_default.json"),
    EE("EE", "EE默认Schema", "schema_json_tpl/EE_default.json"),
    EET("EET", "EET默认Schema", "schema_json_tpl/EET_default.json"),
    EEA("EEA", "EEA默认Schema", "schema_json_tpl/EEA_default.json")
    ;

    static {
        ConcurrentHashMap<OneKESchemaDefault, JSONArray> collect = new ConcurrentHashMap<>();
        for (OneKESchemaDefault oneKESchemaDefault : OneKESchemaDefault.values()) {
            collect.put(oneKESchemaDefault, oneKESchemaDefault.getJsonObjectFromFile());
        }
        JSON_MAP = collect;
    }

    private String name;
    private String desc;
    private String jsonPath;

    public static final ConcurrentHashMap<OneKESchemaDefault, JSONArray> JSON_MAP;

    OneKESchemaDefault(String name, String desc, String jsonPath) {
        this.name = name;
        this.desc = desc;
        this.jsonPath = jsonPath;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public JSONArray getJsonArray() {
//        return JSON_MAP.get(this);
        return this.getJsonObjectFromFile();
    }

    public static OneKESchemaDefault of(String name) {
        for (OneKESchemaDefault oneKESchemaDefault : OneKESchemaDefault.values()) {
            if (oneKESchemaDefault.getName().equals(name)) {
                return oneKESchemaDefault;
            }
        }
        return null;
    }

    public JSONArray getJsonObjectFromFile() {
        try {
            // 读取resources目录下的JSON文件
            ClassPathResource resource = new ClassPathResource(this.getJsonPath());
            InputStream inputStream = resource.getInputStream();
            // 使用BufferedReader读取输入流内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            // 将字符串解析为JSONObject
            JSONArray rst = JSONArray.parseArray(content.toString());
            return rst;
        } catch (IOException e) {
            String msg = String.format("schema定义：%s 解析出错！", this.desc);
            throw new RuntimeException(msg,e);
        }
    }

}
