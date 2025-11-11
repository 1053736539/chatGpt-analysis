package com.cb.vo;

import java.io.Serializable;

public class StudentVisitUserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String workPost;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }
}
