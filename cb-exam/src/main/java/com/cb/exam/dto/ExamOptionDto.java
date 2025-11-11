package com.cb.exam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @version V1.0
 * @Package com.cb.exam.dto
 * @ClassName ExamOptionsDto
 * @Description TODO
 * @Author hu
 * @date 2023/11/07 17:03
 **/
public interface ExamOptionDto {

    interface Add {
        @Accessors(chain = true)
        @Data
        class Input {
            private String name;
            private String option;
        }
    }

}
