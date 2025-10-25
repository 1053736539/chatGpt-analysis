package com.cb.system.domain.vo;
public class DeptPostVo {
    private Long deptId;

    private String deptName;

    private Long postId;

    private String postName;

    private Boolean online;
    // 现职务
    private String workPost;
    // 现职级
    private String workTitle;

    private String JobDeions;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getJobDeions() {
        return JobDeions;
    }

    public void setJobDeions(String jobDeions) {
        JobDeions = jobDeions;
    }
}
