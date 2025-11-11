package com.cb.assess.domain.vo;

import com.cb.assess.domain.BizAssessCadrePoliticalQuality;
import com.cb.common.annotation.Excel;

public class BizAssessCadrePoliticalQualityVo extends BizAssessCadrePoliticalQuality {
    private String name;
    //列表类型，1为我要填别人的，2是别人填我的
    private String type;
    private String deptName;
    private String workPost;
    private Integer total;
    private String reviewerUserName;

    /**
     * 以下是导出的时候对应导出模板的字段
     * @return
     */
    /** 政治忠诚，这些指标暂时不定，可存字典可定死 */
    //@Excel(name = "政治忠诚，这些指标暂时不定，可存字典可定死")
    private String loyalty1;
    private String loyalty2;
    private String loyalty3;
    private String loyalty4;

    /** 政治定力 */
    //@Excel(name = "政治定力")
    private String abilityConcentrate1;
    private String abilityConcentrate2;
    private String abilityConcentrate3;
    private String abilityConcentrate4;

    /** 政治担当 */
    //@Excel(name = "政治担当")
    private String assume1;
    private String assume2;
    private String assume3;
    private String assume4;

    /** 政治能力 */
    //@Excel(name = "政治能力")
    private String capacity1;
    private String capacity2;
    private String capacity3;
    private String capacity4;

    /** 政治自律 */
    //@Excel(name = "政治自律")
    private String selfDiscipline1;
    private String selfDiscipline2;
    private String selfDiscipline3;
    private String selfDiscipline4;

    /** 忠诚不足：“两个维护”不坚决，“四个意识”树得不牢，政治学习不认真、对习近平新时代中国特色社会主义思想理解不深不透，拉帮结派搞“小圈子”，当面一套、背后一套 */
    //@Excel(name = "忠诚不足：“两个维护”不坚决，“四个意识”树得不牢，政治学习不认真、对习近平新时代中国特色社会主义思想理解不深不透，拉帮结派搞“小圈子”，当面一套、背后一套")
    private String lackLoyalty1;
    private String lackLoyalty2;
    private String lackLoyalty3;
    private String lackLoyalty4;

    /** 定力不足：“四个自信”缺乏，妄评乱议、对错误言论不抵制不斗争不坚持原则，不信马列信鬼神，思想消极颓废，精神状态差，心思不在工作上，整天忙于个人进退留转。 */
    //@Excel(name = "定力不足：“四个自信”缺乏，妄评乱议、对错误言论不抵制不斗争不坚持原则，不信马列信鬼神，思想消极颓废，精神状态差，心思不在工作上，整天忙于个人进退留转。")
    private String lackAbilityConcentrate1;
    private String lackAbilityConcentrate2;
    private String lackAbilityConcentrate3;
    private String lackAbilityConcentrate4;

    /** 担当不足：选择性贯彻执行上级工作要求，面对突发事件畏首畏尾，关键时刻冲不上，有“船到码头车到站”思想，心中无数、空喊口号、不推不动、揽功诿过、找借口、躺平。 */
    //@Excel(name = "担当不足：选择性贯彻执行上级工作要求，面对突发事件畏首畏尾，关键时刻冲不上，有“船到码头车到站”思想，心中无数、空喊口号、不推不动、揽功诿过、找借口、躺平。")
    private String lackAssume1;
    private String lackAssume2;
    private String lackAssume3;
    private String lackAssume4;

    /** 品行不端：参加组织生活不正常，参与违规吃请，优亲厚友、私心重，“八小时之外”不讲公德、不注重公众形象，违背社会公德、职业道德及家庭伦理道德。 */
    //@Excel(name = "品行不端：参加组织生活不正常，参与违规吃请，优亲厚友、私心重，“八小时之外”不讲公德、不注重公众形象，违背社会公德、职业道德及家庭伦理道德。")
    private String lackMisconduct1;
    private String lackMisconduct2;
    private String lackMisconduct3;
    private String lackMisconduct4;

    /** 违反组织：不严格执行重大事项请示报告、个人有关事项报告等制度，无正当理由拒不执行党组织的分配、调动、交流等决定。 */
    //@Excel(name = "违反组织：不严格执行重大事项请示报告、个人有关事项报告等制度，无正当理由拒不执行党组织的分配、调动、交流等决定。")
    private String violationOrganization1;
    private String violationOrganization2;
    private String violationOrganization3;
    private String violationOrganization4;

    /** 能力不足：政治站位不高、工作只讲业务，政治敏锐性不强、缺乏处置风险能力，不讲大局、只顾个人“一亩三分地”，做样子、搞花架子，有法不依、盲干胡来。 */
    //@Excel(name = "能力不足：政治站位不高、工作只讲业务，政治敏锐性不强、缺乏处置风险能力，不讲大局、只顾个人“一亩三分地”，做样子、搞花架子，有法不依、盲干胡来。")
    private String lackCapacity1;
    private String lackCapacity2;
    private String lackCapacity3;
    private String lackCapacity4;

    /** 总体评价， */
    //@Excel(name = "总体评价，")
    private String overallEvaluation1;
    private String overallEvaluation2;
    private String overallEvaluation3;
    private String overallEvaluation4;

    public String getReviewerUserName() {
        return reviewerUserName;
    }

    public void setReviewerUserName(String reviewerUserName) {
        this.reviewerUserName = reviewerUserName;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public Integer getTotal() {
        return total;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoyalty1() {
        return loyalty1;
    }

    public void setLoyalty1(String loyalty1) {
        this.loyalty1 = loyalty1;
    }

    public String getLoyalty2() {
        return loyalty2;
    }

    public void setLoyalty2(String loyalty2) {
        this.loyalty2 = loyalty2;
    }

    public String getLoyalty3() {
        return loyalty3;
    }

    public void setLoyalty3(String loyalty3) {
        this.loyalty3 = loyalty3;
    }

    public String getLoyalty4() {
        return loyalty4;
    }

    public void setLoyalty4(String loyalty4) {
        this.loyalty4 = loyalty4;
    }

    public String getAbilityConcentrate1() {
        return abilityConcentrate1;
    }

    public void setAbilityConcentrate1(String abilityConcentrate1) {
        this.abilityConcentrate1 = abilityConcentrate1;
    }

    public String getAbilityConcentrate2() {
        return abilityConcentrate2;
    }

    public void setAbilityConcentrate2(String abilityConcentrate2) {
        this.abilityConcentrate2 = abilityConcentrate2;
    }

    public String getAbilityConcentrate3() {
        return abilityConcentrate3;
    }

    public void setAbilityConcentrate3(String abilityConcentrate3) {
        this.abilityConcentrate3 = abilityConcentrate3;
    }

    public String getAbilityConcentrate4() {
        return abilityConcentrate4;
    }

    public void setAbilityConcentrate4(String abilityConcentrate4) {
        this.abilityConcentrate4 = abilityConcentrate4;
    }

    public String getAssume1() {
        return assume1;
    }

    public void setAssume1(String assume1) {
        this.assume1 = assume1;
    }

    public String getAssume2() {
        return assume2;
    }

    public void setAssume2(String assume2) {
        this.assume2 = assume2;
    }

    public String getAssume3() {
        return assume3;
    }

    public void setAssume3(String assume3) {
        this.assume3 = assume3;
    }

    public String getAssume4() {
        return assume4;
    }

    public void setAssume4(String assume4) {
        this.assume4 = assume4;
    }

    public String getCapacity1() {
        return capacity1;
    }

    public void setCapacity1(String capacity1) {
        this.capacity1 = capacity1;
    }

    public String getCapacity2() {
        return capacity2;
    }

    public void setCapacity2(String capacity2) {
        this.capacity2 = capacity2;
    }

    public String getCapacity3() {
        return capacity3;
    }

    public void setCapacity3(String capacity3) {
        this.capacity3 = capacity3;
    }

    public String getCapacity4() {
        return capacity4;
    }

    public void setCapacity4(String capacity4) {
        this.capacity4 = capacity4;
    }

    public String getSelfDiscipline1() {
        return selfDiscipline1;
    }

    public void setSelfDiscipline1(String selfDiscipline1) {
        this.selfDiscipline1 = selfDiscipline1;
    }

    public String getSelfDiscipline2() {
        return selfDiscipline2;
    }

    public void setSelfDiscipline2(String selfDiscipline2) {
        this.selfDiscipline2 = selfDiscipline2;
    }

    public String getSelfDiscipline3() {
        return selfDiscipline3;
    }

    public void setSelfDiscipline3(String selfDiscipline3) {
        this.selfDiscipline3 = selfDiscipline3;
    }

    public String getSelfDiscipline4() {
        return selfDiscipline4;
    }

    public void setSelfDiscipline4(String selfDiscipline4) {
        this.selfDiscipline4 = selfDiscipline4;
    }

    public String getLackLoyalty1() {
        return lackLoyalty1;
    }

    public void setLackLoyalty1(String lackLoyalty1) {
        this.lackLoyalty1 = lackLoyalty1;
    }

    public String getLackLoyalty2() {
        return lackLoyalty2;
    }

    public void setLackLoyalty2(String lackLoyalty2) {
        this.lackLoyalty2 = lackLoyalty2;
    }

    public String getLackLoyalty3() {
        return lackLoyalty3;
    }

    public void setLackLoyalty3(String lackLoyalty3) {
        this.lackLoyalty3 = lackLoyalty3;
    }

    public String getLackLoyalty4() {
        return lackLoyalty4;
    }

    public void setLackLoyalty4(String lackLoyalty4) {
        this.lackLoyalty4 = lackLoyalty4;
    }

    public String getLackAbilityConcentrate1() {
        return lackAbilityConcentrate1;
    }

    public void setLackAbilityConcentrate1(String lackAbilityConcentrate1) {
        this.lackAbilityConcentrate1 = lackAbilityConcentrate1;
    }

    public String getLackAbilityConcentrate2() {
        return lackAbilityConcentrate2;
    }

    public void setLackAbilityConcentrate2(String lackAbilityConcentrate2) {
        this.lackAbilityConcentrate2 = lackAbilityConcentrate2;
    }

    public String getLackAbilityConcentrate3() {
        return lackAbilityConcentrate3;
    }

    public void setLackAbilityConcentrate3(String lackAbilityConcentrate3) {
        this.lackAbilityConcentrate3 = lackAbilityConcentrate3;
    }

    public String getLackAbilityConcentrate4() {
        return lackAbilityConcentrate4;
    }

    public void setLackAbilityConcentrate4(String lackAbilityConcentrate4) {
        this.lackAbilityConcentrate4 = lackAbilityConcentrate4;
    }

    public String getLackAssume1() {
        return lackAssume1;
    }

    public void setLackAssume1(String lackAssume1) {
        this.lackAssume1 = lackAssume1;
    }

    public String getLackAssume2() {
        return lackAssume2;
    }

    public void setLackAssume2(String lackAssume2) {
        this.lackAssume2 = lackAssume2;
    }

    public String getLackAssume3() {
        return lackAssume3;
    }

    public void setLackAssume3(String lackAssume3) {
        this.lackAssume3 = lackAssume3;
    }

    public String getLackAssume4() {
        return lackAssume4;
    }

    public void setLackAssume4(String lackAssume4) {
        this.lackAssume4 = lackAssume4;
    }

    public String getLackMisconduct1() {
        return lackMisconduct1;
    }

    public void setLackMisconduct1(String lackMisconduct1) {
        this.lackMisconduct1 = lackMisconduct1;
    }

    public String getLackMisconduct2() {
        return lackMisconduct2;
    }

    public void setLackMisconduct2(String lackMisconduct2) {
        this.lackMisconduct2 = lackMisconduct2;
    }

    public String getLackMisconduct3() {
        return lackMisconduct3;
    }

    public void setLackMisconduct3(String lackMisconduct3) {
        this.lackMisconduct3 = lackMisconduct3;
    }

    public String getLackMisconduct4() {
        return lackMisconduct4;
    }

    public void setLackMisconduct4(String lackMisconduct4) {
        this.lackMisconduct4 = lackMisconduct4;
    }

    public String getViolationOrganization1() {
        return violationOrganization1;
    }

    public void setViolationOrganization1(String violationOrganization1) {
        this.violationOrganization1 = violationOrganization1;
    }

    public String getViolationOrganization2() {
        return violationOrganization2;
    }

    public void setViolationOrganization2(String violationOrganization2) {
        this.violationOrganization2 = violationOrganization2;
    }

    public String getViolationOrganization3() {
        return violationOrganization3;
    }

    public void setViolationOrganization3(String violationOrganization3) {
        this.violationOrganization3 = violationOrganization3;
    }

    public String getViolationOrganization4() {
        return violationOrganization4;
    }

    public void setViolationOrganization4(String violationOrganization4) {
        this.violationOrganization4 = violationOrganization4;
    }

    public String getLackCapacity1() {
        return lackCapacity1;
    }

    public void setLackCapacity1(String lackCapacity1) {
        this.lackCapacity1 = lackCapacity1;
    }

    public String getLackCapacity2() {
        return lackCapacity2;
    }

    public void setLackCapacity2(String lackCapacity2) {
        this.lackCapacity2 = lackCapacity2;
    }

    public String getLackCapacity3() {
        return lackCapacity3;
    }

    public void setLackCapacity3(String lackCapacity3) {
        this.lackCapacity3 = lackCapacity3;
    }

    public String getLackCapacity4() {
        return lackCapacity4;
    }

    public void setLackCapacity4(String lackCapacity4) {
        this.lackCapacity4 = lackCapacity4;
    }

    public String getOverallEvaluation1() {
        return overallEvaluation1;
    }

    public void setOverallEvaluation1(String overallEvaluation1) {
        this.overallEvaluation1 = overallEvaluation1;
    }

    public String getOverallEvaluation2() {
        return overallEvaluation2;
    }

    public void setOverallEvaluation2(String overallEvaluation2) {
        this.overallEvaluation2 = overallEvaluation2;
    }

    public String getOverallEvaluation3() {
        return overallEvaluation3;
    }

    public void setOverallEvaluation3(String overallEvaluation3) {
        this.overallEvaluation3 = overallEvaluation3;
    }

    public String getOverallEvaluation4() {
        return overallEvaluation4;
    }

    public void setOverallEvaluation4(String overallEvaluation4) {
        this.overallEvaluation4 = overallEvaluation4;
    }
}
