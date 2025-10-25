package com.cb.system.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-01-16 13:57
 * @Version: 1.0
 **/
@Data
public class WordUserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;//姓名
    private String gender;//性别
    private String birthDate;//出生年月(岁)
    private String nation;//民族
    private String nativePlace;//籍贯
    private String birthPlace;//出生地
    private String joinPartyDate;//入党时间
    private String beginWorkDate;//参加工作时间
    private String healthStatus;//健康状况
    private String professionalDuty;//专业技术职务
    private String familiarProfession;//熟悉专业有何专长
    private String fullTimeEducationLevel;//全日制教育
    private String fullTimeEducationSchoolAndMajor;//全日制教育-毕业院校系及专业
    private String onJobEducationLevel;//在职教育
    private String onJobEducationSchoolAndMajor;//在职教育-毕业院校系及专业
    private String currentPosition;//现任职务
    private String proposedAppointmentPosition;//拟任职务
    private String proposedRemovalPosition;//拟免职务
    private List<ResumeItem> resumeItemList;//简历项集合
    private String rewardAndPunishment;//奖惩情况
    private String annualAssessment;//年度考核结果
    private String reasonForAppointmentOrRemoval;//任免理由

    private BufferedImage headImage;//头像
    private String headImageExtension;//头像类型，后缀名
    private List<FamilyMemberInfo> familyMemberInfoList;//家庭成员集合

    private Long deptId;//导入时指定的部门id

    public static class ResumeItem implements Serializable {
        private static final long serialVersionUID = 1L;
        private String beginDate;
        private String endDate;
        private String content;

        public ResumeItem(String beginDate, String endDate, String content) {
            this.beginDate = beginDate;
            this.endDate = endDate;
            this.content = content;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class FamilyMemberInfo implements Serializable{
        private static final long serialVersionUID = 1L;

        private String relation;//称谓
        private String name;//姓名
        private String age;//格式可能是birthDate
        private String politicStatus;//政治面貌
        private String workDeptAndPosition;//工作单位及职务

        public FamilyMemberInfo(String relation, String name, String age, String politicStatus, String workDeptAndPosition) {
            this.relation = relation;
            this.name = name;
            this.age = age;
            this.politicStatus = politicStatus;
            this.workDeptAndPosition = workDeptAndPosition;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPoliticStatus() {
            return politicStatus;
        }

        public void setPoliticStatus(String politicStatus) {
            this.politicStatus = politicStatus;
        }

        public String getWorkDeptAndPosition() {
            return workDeptAndPosition;
        }

        public void setWorkDeptAndPosition(String workDeptAndPosition) {
            this.workDeptAndPosition = workDeptAndPosition;
        }
    }

    public static class AnnualAssessment implements Serializable {
        private static final long serialVersionUID = 1L;
        private String year;
        private String content;

        public AnnualAssessment(String year, String content) {
            this.year = year;
            this.content = content;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * 读取docx文件的第一个表格对应的数据
     * fieldName:label:rowIndex:colIndex
     */
    //学历学位使用双行
    public static String[] firstTableFieldPosition_edu_double_row = new String[]{
            "name:姓名:0:1","gender:性别:0:3","birthDate:出生年月(岁):0:5",
            "nation:民族:1:1","nativePlace:籍贯:1:3","birthPlace:出生地:1:5",
            "joinPartyDate:入党时间:2:1","beginWorkDate:参加工作时间:2:3","healthStatus:健康状况:2:5",
            "professionalDuty:专业技术职务:3:1","familiarProfession:熟悉专业有何专长:3:3",
            "fullTimeEducationLevel:全日制教育:4:2","fullTimeEducationSchoolAndMajor:全日制教育-毕业院校系及专业:4:4",
            "onJobEducationLevel:在职教育:6:2","onJobEducationSchoolAndMajor:在职教育-毕业院校系及专业:6:4",
            "currentPosition:现任职务:8:1",
            "proposedAppointmentPosition:拟任职务:9:1",
            "proposedRemovalPosition:拟免职务:10:1",
            "resumeItemList:简历:11:1"
    };

    //学历学位使用单行
    public static String[] firstTableFieldPosition_edu_single_row = new String[]{
            "name:姓名:0:1","gender:性别:0:3","birthDate:出生年月(岁):0:5",
            "nation:民族:1:1","nativePlace:籍贯:1:3","birthPlace:出生地:1:5",
            "joinPartyDate:入党时间:2:1","beginWorkDate:参加工作时间:2:3","healthStatus:健康状况:2:5",
            "professionalDuty:专业技术职务:3:1","familiarProfession:熟悉专业有何专长:3:3",
            "fullTimeEducationLevel:全日制教育:4:2","fullTimeEducationSchoolAndMajor:全日制教育-毕业院校系及专业:4:4",
            "onJobEducationLevel:在职教育:5:2","onJobEducationSchoolAndMajor:在职教育-毕业院校系及专业:5:4",
            "currentPosition:现任职务:6:1",
            "proposedAppointmentPosition:拟任职务:7:1",
            "proposedRemovalPosition:拟免职务:8:1",
            "resumeItemList:简历:9:1"
    };

    /**
     * 读取docx文件的第二个表格对应的数据
     * fieldName:label:rowIndex:colIndex
     */
    public static String[] secondTableFieldPosition = new String[]{
            "rewardAndPunishment:奖惩情况:0:1",
            "annualAssessment:年度考核结果:1:1",
            "reasonForAppointmentOrRemoval:任免理由:2:1"
    };

}
