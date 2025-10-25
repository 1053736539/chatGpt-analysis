package com.cb.common.constant;

import com.cb.common.utils.StringUtils;

/**
 * @Description: 主要是为了统一管理，方便查找,注意，这里配的是前端路由地址
 * @Author ARPHS
 * @Date 2023/12/12 11:48
 */
public enum NoticeBizUrlTpl {
    ALLOCATE_INFO("allocateInfo/noticeDetail/{}","机关事业单位考核人数及优秀等次名额分配表"),
    PERSONAL_SUMMARY("personalSummary/personalSummaryYear/{}","个人总结公示"),
    EVALUATION_PUBLICITY("assess/evaluation/publicityView/{}/{}","云南省统计局年度考核优秀汇总表"),
    ORDINARY_ASSESSMENT("assess/ordinary/publicity/{}/{}","平时考核等次公示"),
    WORK_SITUATION("/assess/workSituation/publicityWorkSituation?months={}","考勤结果公示"),
    ANNUAL_REVIEW_VIEW("/assess/annualReview/publicityView/{}","年度机关事业单位考核优秀名单公示")
    ;

    private String tpl;
    private String desc;

    NoticeBizUrlTpl(String tpl, String desc) {
        this.tpl = tpl;
        this.desc = desc;
    }

    /**
     * 当前模板根据传入参数生成url
     * @param args
     * @return
     */
    public String build(Object... args){
        return StringUtils.format(this.tpl,args);
    }

    public String getTpl() {
        return tpl;
    }
    public void setTpl(String tpl) {
        this.tpl = tpl;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
