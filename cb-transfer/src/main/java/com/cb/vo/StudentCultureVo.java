package com.cb.vo;

import com.cb.domain.TransferringStudentCulture;


public class StudentCultureVo extends TransferringStudentCulture {
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 用户名
     */
    private String nickName;
}
