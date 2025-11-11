package com.cb.ai.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-11-15 10:31
 * @Version: 1.0
 **/
public interface OneKEVo {

    @Data
    class Req{
        private String instruction;//任务描述，以自然语言指定模型扮演的角色以及需要完成的任务
        private JSONArray schema;//是一份需提取的标签列表，明确指出了待抽取信息的关键字段，反应用户的需求，是动态可变的
        //由于example示例通常较长, 模型训练最大长度有限, 过多的example可能导致模型的效果反而不好, 因此我们建议给2个example, 一个正例, 一个反例, 并且限制schema的数量在1个。
        private JSONArray example;// 定制化example示例，可选
        private String input;//是用于信息抽取的源文本。
    }

}
