package com.cb.rpa.domain.vo;

import lombok.Data;

import java.util.Date;
@Data
public class WebsiteVo {

    private String id;

    private String title;

    private String labels;

    private String content;

    private String originate;

    private String editor;

    private Date releaseTime;

    private String linkUrl;
}
