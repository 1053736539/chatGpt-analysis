package com.cb.exam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @version V1.0
 * @Package com.cb.exam.dto
 * @ClassName ExamPaperDto
 * @Description TODO
 * @Author hu
 * @date 2023/11/08
 **/
public interface ExamPaperDto {
    interface Add {
        @Accessors(chain = true)
        @Data
        class Input {
            private Long id;
            private List<ExamPaperDetailDto.Add.Input> list;
            private Boolean isIssue;
            private Integer goodScore;
            private Integer passingScore;
            private Integer questionCount;
            private Integer totalPoint;
            private Integer duration;
            private String name;
            private String createUser;
            private Long selectScore;
            private Long selectsScore;
            private Long judgeScore;
            private Long answerScore;
        }
    }
}
