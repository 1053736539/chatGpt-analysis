package com.cb.exam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: hu
 * @date: 2023/11/08
 */
public interface ExamUserAnswerDetailDto {

    interface Add {
        @Accessors(chain = true)
        @Data
        class Input {
            private Integer examinationPaperQuestionsId;
            private String questionsType;
            private String userAnswer;
            private String questionsAnswer;
            private Boolean isCorrect;
        }
    }

}
