package com.cb.ai.enums;

/**
 * @Description: OneKE操作
 * @Author: ARPHS
 * @Date: 2024-11-15 09:55
 * @Version: 1.0
 **/
public enum OneKEOperation {
    NER("NER", "entity_naming_recognition", "实体命名识别", "你是专门进行实体抽取的专家。请从input中抽取出符合schema定义的实体，不存在的实体类型返回空列表。请严格按照JSON字符串的格式返回。"),
    RE("RE", "relationship_recognition", "关系识别", "你是专门进行关系抽取的专家。请从input中抽取出符合schema定义的关系三元组，不存在的关系返回空列表。请严格按照JSON字符串的格式返回。"),
    KGC("KGC", "knowledge_graph_construction", "知识图谱构建", "你是一个图谱实体知识结构化专家。根据输入实体类型(entity type)的schema描述，从input中抽取出相应的实体实例和其属性信息，不存在的属性不输出, 属性存在多值就返回列表，请严格按照JSON字符串的格式返回。"),
    EE("EE", "event_extraction", "事件抽取", "你是专门进行事件提取的专家。请从input中抽取出符合schema定义的事件，不存在的事件返回空列表，不存在的论元返回NAN，如果论元存在多值请返回列表。请严格按照JSON字符串的格式返回。"),
    EET("EET", "event_extraction_trigger", "事件触发词识别", "你是专门进行事件提取的专家。请从input中抽取出符合schema定义的事件类型及事件触发词，不存在的事件返回空列表。请严格按照JSON字符串的格式返回。"),
    EEA("EEA", "event_extraction_argument", "事件论元抽取", "你是专门进行事件论元提取的专家。请从input中抽取出符合schema定义的事件论元及论元角色，不存在的论元返回NAN或空字典，如果论元存在多值请返回列表。请严格按照JSON字符串的格式返回。"),
    ;

    private String code;
    private String name;
    private String desc;
    private String instruction;

    OneKEOperation(String code, String name, String desc, String instruction) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.instruction = instruction;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getInstruction() {
        return instruction;
    }

    public static OneKEOperation of(String code) {
        for (OneKEOperation oneKEOperation : OneKEOperation.values()) {
            if (oneKEOperation.getCode().equalsIgnoreCase(code)) {
                return oneKEOperation;
            }
        }
        return null;
    }

}
