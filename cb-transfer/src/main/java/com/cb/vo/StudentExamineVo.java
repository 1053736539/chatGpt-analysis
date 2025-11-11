package com.cb.vo;

import com.cb.domain.TransferringStudentExamine;

public class StudentExamineVo extends TransferringStudentExamine {
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
