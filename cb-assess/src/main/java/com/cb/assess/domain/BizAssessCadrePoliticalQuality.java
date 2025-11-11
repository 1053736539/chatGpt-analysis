package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * xxxx年处级领导干部政治素质年度考察测评对象 biz_assess_cadre_political_quality
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
public class BizAssessCadrePoliticalQuality extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 评测对象， */
    @Excel(name = "评测对象，")
    private Long userId;
    /**
     * 评测人
     */
    private Long reviewerUserId;
    private Long deptId;

    /** 政治忠诚，这些指标暂时不定，可存字典可定死 */
    @Excel(name = "政治忠诚，这些指标暂时不定，可存字典可定死")
    private String loyalty;

    /** 政治定力 */
    @Excel(name = "政治定力")
    private String abilityConcentrate;

    /** 政治担当 */
    @Excel(name = "政治担当")
    private String assume;

    /** 政治能力 */
    @Excel(name = "政治能力")
    private String capacity;

    /** 政治自律 */
    @Excel(name = "政治自律")
    private String selfDiscipline;

    /** 忠诚不足：“两个维护”不坚决，“四个意识”树得不牢，政治学习不认真、对习近平新时代中国特色社会主义思想理解不深不透，拉帮结派搞“小圈子”，当面一套、背后一套 */
    @Excel(name = "忠诚不足：“两个维护”不坚决，“四个意识”树得不牢，政治学习不认真、对习近平新时代中国特色社会主义思想理解不深不透，拉帮结派搞“小圈子”，当面一套、背后一套")
    private String lackLoyalty;

    /** 定力不足：“四个自信”缺乏，妄评乱议、对错误言论不抵制不斗争不坚持原则，不信马列信鬼神，思想消极颓废，精神状态差，心思不在工作上，整天忙于个人进退留转。 */
    @Excel(name = "定力不足：“四个自信”缺乏，妄评乱议、对错误言论不抵制不斗争不坚持原则，不信马列信鬼神，思想消极颓废，精神状态差，心思不在工作上，整天忙于个人进退留转。")
    private String lackAbilityConcentrate;

    /** 担当不足：选择性贯彻执行上级工作要求，面对突发事件畏首畏尾，关键时刻冲不上，有“船到码头车到站”思想，心中无数、空喊口号、不推不动、揽功诿过、找借口、躺平。 */
    @Excel(name = "担当不足：选择性贯彻执行上级工作要求，面对突发事件畏首畏尾，关键时刻冲不上，有“船到码头车到站”思想，心中无数、空喊口号、不推不动、揽功诿过、找借口、躺平。")
    private String lackAssume;

    /** 品行不端：参加组织生活不正常，参与违规吃请，优亲厚友、私心重，“八小时之外”不讲公德、不注重公众形象，违背社会公德、职业道德及家庭伦理道德。 */
    @Excel(name = "品行不端：参加组织生活不正常，参与违规吃请，优亲厚友、私心重，“八小时之外”不讲公德、不注重公众形象，违背社会公德、职业道德及家庭伦理道德。")
    private String lackMisconduct;

    /** 违反组织：不严格执行重大事项请示报告、个人有关事项报告等制度，无正当理由拒不执行党组织的分配、调动、交流等决定。 */
    @Excel(name = "违反组织：不严格执行重大事项请示报告、个人有关事项报告等制度，无正当理由拒不执行党组织的分配、调动、交流等决定。")
    private String violationOrganization;

    /** 能力不足：政治站位不高、工作只讲业务，政治敏锐性不强、缺乏处置风险能力，不讲大局、只顾个人“一亩三分地”，做样子、搞花架子，有法不依、盲干胡来。 */
    @Excel(name = "能力不足：政治站位不高、工作只讲业务，政治敏锐性不强、缺乏处置风险能力，不讲大局、只顾个人“一亩三分地”，做样子、搞花架子，有法不依、盲干胡来。")
    private String lackCapacity;

    /** 总体评价， */
    @Excel(name = "总体评价，")
    private String overallEvaluation;
    /**
     * 状态：
     */
    private String status;

    /** $column.columnComment */
    private String delFlag;

    /** 考核年度 */
    @Excel(name = "考核年度")
    @NotBlank(message = "评测年度不能为空")
    private String assessmentYear;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(Long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setLoyalty(String loyalty) 
    {
        this.loyalty = loyalty;
    }

    public String getLoyalty() 
    {
        return loyalty;
    }
    public void setAbilityConcentrate(String abilityConcentrate) 
    {
        this.abilityConcentrate = abilityConcentrate;
    }

    public String getAbilityConcentrate() 
    {
        return abilityConcentrate;
    }
    public void setAssume(String assume) 
    {
        this.assume = assume;
    }

    public String getAssume() 
    {
        return assume;
    }
    public void setCapacity(String capacity) 
    {
        this.capacity = capacity;
    }

    public String getCapacity() 
    {
        return capacity;
    }
    public void setSelfDiscipline(String selfDiscipline) 
    {
        this.selfDiscipline = selfDiscipline;
    }

    public String getSelfDiscipline() 
    {
        return selfDiscipline;
    }
    public void setLackLoyalty(String lackLoyalty) 
    {
        this.lackLoyalty = lackLoyalty;
    }

    public String getLackLoyalty() 
    {
        return lackLoyalty;
    }
    public void setLackAbilityConcentrate(String lackAbilityConcentrate) 
    {
        this.lackAbilityConcentrate = lackAbilityConcentrate;
    }

    public String getLackAbilityConcentrate() 
    {
        return lackAbilityConcentrate;
    }
    public void setLackAssume(String lackAssume) 
    {
        this.lackAssume = lackAssume;
    }

    public String getLackAssume() 
    {
        return lackAssume;
    }
    public void setLackMisconduct(String lackMisconduct) 
    {
        this.lackMisconduct = lackMisconduct;
    }

    public String getLackMisconduct() 
    {
        return lackMisconduct;
    }
    public void setViolationOrganization(String violationOrganization) 
    {
        this.violationOrganization = violationOrganization;
    }

    public String getViolationOrganization() 
    {
        return violationOrganization;
    }
    public void setLackCapacity(String lackCapacity) 
    {
        this.lackCapacity = lackCapacity;
    }

    public String getLackCapacity() 
    {
        return lackCapacity;
    }
    public void setOverallEvaluation(String overallEvaluation) 
    {
        this.overallEvaluation = overallEvaluation;
    }

    public String getOverallEvaluation() 
    {
        return overallEvaluation;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setAssessmentYear(String assessmentYear) 
    {
        this.assessmentYear = assessmentYear;
    }

    public String getAssessmentYear() 
    {
        return assessmentYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("loyalty", getLoyalty())
            .append("abilityConcentrate", getAbilityConcentrate())
            .append("assume", getAssume())
            .append("capacity", getCapacity())
            .append("selfDiscipline", getSelfDiscipline())
            .append("lackLoyalty", getLackLoyalty())
            .append("lackAbilityConcentrate", getLackAbilityConcentrate())
            .append("lackAssume", getLackAssume())
            .append("lackMisconduct", getLackMisconduct())
            .append("violationOrganization", getViolationOrganization())
            .append("lackCapacity", getLackCapacity())
            .append("overallEvaluation", getOverallEvaluation())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("assessmentYear", getAssessmentYear())
            .toString();
    }
}
