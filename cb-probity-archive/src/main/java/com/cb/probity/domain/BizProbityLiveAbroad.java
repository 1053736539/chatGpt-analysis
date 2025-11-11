package com.cb.probity.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况对象 biz_probity_live_abroad
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public class BizProbityLiveAbroad extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * probity_id
     */
    @Excel(name = "probity_id")
    private String probityId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 所在国家（地区）
     */
    @Excel(name = "所在国家", readConverterExp = "地=区")
    private String countryRegion;

    /**
     * 现工作、居住城市
     */
    @Excel(name = "现工作、居住城市")
    private String liveCity;

    /**
     * 起始时间
     */
    @Excel(name = "起始时间")
    private String beginTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProbityId(String probityId) {
        this.probityId = probityId;
    }

    public String getProbityId() {
        return probityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity;
    }

    public String getLiveCity() {
        return liveCity;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("probityId", getProbityId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("name", getName())
                .append("countryRegion", getCountryRegion())
                .append("liveCity", getLiveCity())
                .append("beginTime", getBeginTime())
                .toString();
    }
}
