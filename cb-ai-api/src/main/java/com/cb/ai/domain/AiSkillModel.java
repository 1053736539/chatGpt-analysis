package com.cb.ai.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AI 技能模型对象 ai_skill_model
 *
 * @author ouyang
 * @date 2024-11-02
 */
public class AiSkillModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 模型名称
     */
    @Excel(name = "模型名称")
    private String modelName;

    /**
     * 模型编码
     */
    @Excel(name = "模型编码")
    private String modelCode;

    /**
     * 是否系统内置（ 1是 0否）
     */
    @Excel(name = "是否系统内置", readConverterExp = "1=是,0=否")
    private String modelType;

    /**
     * 提示词语
     */
    @Excel(name = "提示词语")
    private String tipWords;

    /**
     * 返回数据
     */
    @Excel(name = "返回数据")
    private String outValueScheme;

    /** 输入类型 0文本，1文件 */
    @Excel(name = "输入类型 0文本，1文件")
    private String inputType;

    /** 输出类型 0 JSON ,1 文本 */
    @Excel(name = "输出类型 0 JSON ,1 文本")
    private String resultType;


    @Excel(name = "状态", readConverterExp = "0=正常,1=禁用")
    private String enabled;
    /** 测试用例 */
    @Excel(name = "测试用例")
    private String example;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelType() {
        return modelType;
    }

    public void setTipWords(String tipWords) {
        this.tipWords = tipWords;
    }

    public String getTipWords() {
        return tipWords;
    }

    public void setOutValueScheme(String outValueScheme) {
        this.outValueScheme = outValueScheme;
    }

    public String getOutValueScheme() {
        return outValueScheme;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
