package com.cb.exam.enums;

/**
 * 题目类型
 *
 * @author hu
 */
public enum QuestionTypeEnum {

    /**
     * 单选题
     */
    SELECT("select","单选题"),

    /**
     * 多选题
     */
    SELECTS("selects","多选题"),

    /**
     * 判断题
     */
    VERDICT("verdict","判断题"),

    /**
     * 问答题
     */
    TEXT("answer","问答题");

    QuestionTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
