package com.cb.exam.vo;

import lombok.Data;

/**
 * @version V1.0
 * @Package com.cb.exam.vo
 * @ClassName PaperQuestionOptionVo
 * @Description TODO
 * @Author hu
 * @date 2023/11/08
 **/
@Data
public class PaperQuestionOptionVo {


    /**
     * 选项编号
     */
    private String serialNumber;

    /**
     * 选项内容
     */
    private String optionContent;

}
