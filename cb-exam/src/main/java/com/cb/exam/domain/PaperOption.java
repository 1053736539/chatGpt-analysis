package com.cb.exam.domain;

import lombok.Data;

/**
 * @version V1.0
 * @Package com.cb.exam.domain
 * @ClassName PaperOption
 * @Description 查询的试卷的题目的选项类
 * @Author hu
 * @date 22023/11/08
 **/
@Data
public class PaperOption {

    private Long questionsId;
    private Long optionId;
    private String serialNumber;
    private String optionContent;

}
