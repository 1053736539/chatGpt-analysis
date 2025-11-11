package com.cb.exam.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author hu
 * @date 2023/11/08
 * 这个是保存用户考试数据用的
 */
public interface ExamUserAnswerDto {

    interface Add {
        @Accessors(chain = true)
        @Data
        class Input {
            private String examinationPaperId;
            private String startTime;
            private String endTime;
            private Integer totalPoints;
            private Integer taskId;
            private Long userId;
            private String createUser;
            private List<ExamUserAnswerDetailDto.Add.Input> optionList;
        }
    }

}
