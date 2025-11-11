package com.cb.adopt.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 得分规则对象 biz_adopt_score_rule
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BizAdoptScoreRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 采用级别 */
    @Excel(name = "采用级别")
    private String level;

    /** 采用载体 */
    @Excel(name = "采用载体")
    private String carrier;

    /** 主报单位分值 */
    @Excel(name = "主报单位分值")
    private Integer mainScore;

    /** 采用单位分值 */
    @Excel(name = "采用单位分值")
    private Integer adoptScore;

    /** 其它单位分值 */
    @Excel(name = "其它单位分值")
    private Integer otherScore;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCarrier(String carrier) 
    {
        this.carrier = carrier;
    }

    public String getCarrier() 
    {
        return carrier;
    }
    public void setMainScore(Integer mainScore) 
    {
        this.mainScore = mainScore;
    }

    public Integer getMainScore() 
    {
        return mainScore;
    }

    public Integer getAdoptScore() {
        return adoptScore;
    }

    public void setAdoptScore(Integer adoptScore) {
        this.adoptScore = adoptScore;
    }

    public void setOtherScore(Integer otherScore)
    {
        this.otherScore = otherScore;
    }

    public Integer getOtherScore() 
    {
        return otherScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("level", getLevel())
            .append("carrier", getCarrier())
            .append("mainScore", getMainScore())
            .append("otherScore", getOtherScore())
            .toString();
    }
}
