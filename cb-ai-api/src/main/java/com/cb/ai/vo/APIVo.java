package com.cb.ai.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-31 11:22
 * @Version: 1.0
 **/
public interface APIVo {

    /**
     * 请求关系图谱的请求参数
     */
    @Data
    class ParseRelationGraphReq {
        String operation;
        String text;
        JSONArray schema;
    }

    /**
     * 文件转md的请求参数
     */
    @Data
    class GenReviewReportTextReq{
        @NotBlank(message = "请填写提示词!")
        String question;
    }

}
