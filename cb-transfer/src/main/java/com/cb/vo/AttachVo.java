package com.cb.vo;

import com.cb.domain.TransferringStudentAttach;
import com.cb.filemanage.domain.BizAttach;

import java.util.List;

public class AttachVo extends TransferringStudentAttach {

    /**
     * 用户名
     */
    private String name;
    private List<BizAttach> attachList;

    public List<BizAttach> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<BizAttach> attachList) {
        this.attachList = attachList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
