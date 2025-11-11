package com.cb.vo;

import com.cb.domain.TransferringStudentSupervise;

public class StudentSuperviseVo extends TransferringStudentSupervise {
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
