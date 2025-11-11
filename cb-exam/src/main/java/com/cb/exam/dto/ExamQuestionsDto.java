package com.cb.exam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @version V1.0
 * @Package com.cb.exam.dto
 * @ClassName ExamQuestionsDto
 * @Description TODO
 * @Author hu
 * @date 2023/11/07 17:25
 **/
public interface ExamQuestionsDto {

    interface Add {
        @Accessors(chain = true)
        @Data
        class Input {
            private Long id;
            private String name;
            private List<Integer> labels;
            private List<Integer> banks;
            private String type;
            private String answer;
            private Integer judgeAnswer;
            private String selectAnswer;
            private List<String> selectAnswers;
            private String analysis;
            private Integer isEnable;
            private List<ExamOptionDto.Add.Input> list;
            private List<String> newLabelList;
            private String knowledgePoint;
            private String source;
            private String examPoint;
            private String keywords;
            private String difficulty;
        }

    }

    interface AddBatch {
        @Accessors(chain = true)
        @Data
        class Input {
            private List<Add.Input> list;
        }

    }

}
