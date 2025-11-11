package com.cb.exam.vo;

import com.cb.exam.domain.ExamUserAnswer;

/**
 * @author: hu
 * @date: 22023/11/08
 * @description:
 */
public class ExamUserAnswerVo extends ExamUserAnswer {

    private String task_name;

    private String paper_name;

    @Override
    public String toString() {
        return "ExamUserAnswerVo{" +
                "task_name='" + task_name + '\'' +
                ", paper_name='" + paper_name + '\'' +
                '}';
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }
}
