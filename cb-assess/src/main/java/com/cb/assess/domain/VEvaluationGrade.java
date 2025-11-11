package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VEvaluationGrade {
    private String taskId;
    private String title;

    private String belongYear;

    private String special;

    private String cycle;

    private String cycleDesc;
    @Builder.Default
    private Boolean isScoring = false;

    private String publicityStatus;

    @Data
    public static class VoteScore {
        private String taskId;
        //用户姓名或者其他描述
        private String describe;

        /**
         * {@link com.cb.assess.enums.RatGroup}
         */
        private String voteType;
        // 权重 （换算成小数了）
        private Float weight;

        // 总得分
        private BigDecimal totalScore;

        // 平均分
        private BigDecimal averageScore;

        // 加权平均分
        private BigDecimal weightedScore;

        //打分记录
        private List<VEvaluationGrade.Voter> voterList;
    }

    @Data
    public static class PersonalScore {
        private String userName;
        private BigDecimal weightAverageScore;
        //打分记录
        private List<VEvaluationGrade.Voter> voterList;
    }

    @Data
    public static class Voter {
        private String id;
        private String taskId;

        private String evaluateTagId;

        private Long voter;

        private String voterName;

        private Long userId;

        private String voteType;

        private Integer weight;

        private String status;

        private BigDecimal score;

        private String special;

        private String cycle;

        private String cycleDesc;

        private String proId;

        private String personnelType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Grade {
        private String id;
        @Excel(name = "任务ID")
        private String taskId;

        private String taskName;

        private String proId;

        private String personnelType;

        private String cycle;

        private String belongYear;

        private String special;

        @Excel(name = "用户ID")
        private Long userId;

        /**
         * 用户编制类型
         */
        private String identityType;

        @Excel(name = "季度")
        private String cycleDesc;

        @Excel(name = "用户姓名")
        private String userName;

        private Long deptId;

        @Excel(name = "部门名称")
        private String deptName;

        @Excel(name = "民主评价-处室打分")
        @Builder.Default
        private BigDecimal deptScore = BigDecimal.ZERO;

        @Excel(name = "民主评价-领导打分")
        @Builder.Default
        private BigDecimal leaderScore = BigDecimal.ZERO;

        //        @Excel(name = "专项考核得分")
        @Builder.Default
        private BigDecimal unusualScore = BigDecimal.ZERO;
        @Excel(name = "作风纪律检查、系统考核和信访举报情况")
        @Builder.Default
        private BigDecimal disciplineScore = BigDecimal.ZERO;

        @Excel(name = "考勤-矿工天数")
        @Builder.Default
        private Integer attendanceNum =0;

        @Excel(name = "考勤得分")
        @Builder.Default
        private BigDecimal attendanceScore = BigDecimal.ZERO;


        @Excel(name = "评价得分")
        @Builder.Default
        private BigDecimal score = BigDecimal.ZERO;

        @Excel(name = "总分")
        private BigDecimal totalScore = BigDecimal.ZERO;

        // 综合得分
        @Transient
        @Builder.Default
        private BigDecimal compositeScore = BigDecimal.ZERO;
        private String modified;
        @Excel(name = "最终评定等次")
        private String grade;
        @Excel(name = "机关党委（人事处）审定意见")
        private String rscOpinion;
        @Excel(name = "党组会建议等次")
        private String dzhOpinion;
        // 是否填写了个人总结
        private Boolean isFillRegular;
        // 0 上报 1 纪委评分 2.主要负责人建议等次 3.机关党委审定等次 4.党组会建议等次
        private String step;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Composite {
        private String id;
        private String taskId;
        private String personnelType;

        private String cycle;
        private String cycleDesc;

        private String belongYear;

        private String special;

        private Long userId;
        // 用户姓名
        private String name;
        private Long deptId;
        // 部门名称
        private String deptName;
        /**
         * 打分Json数组
         */
        private String  scoringJson;

        //部门原始打分
        private BigDecimal deptOriginScore = BigDecimal.ZERO;

        @Excel(name = "民主评价-处室打分")
        @Builder.Default
        private BigDecimal deptScore = BigDecimal.ZERO;

        @Excel(name = "民主评价-领导打分")
        @Builder.Default
        private BigDecimal leaderScore = BigDecimal.ZERO;

        @Excel(name = "专项考核得分")
        @Builder.Default
        private BigDecimal unusualScore = BigDecimal.ZERO;
        // 以上小计
        @Builder.Default
        private BigDecimal subtotalScore = BigDecimal.ZERO;
        //作风纪律检查、系统考核和信访举报情况
        @Builder.Default
        private BigDecimal discipline = BigDecimal.ZERO;

        @Excel(name = "考勤-矿工天数")
        @Builder.Default
        private Integer attendanceNum =0;

        @Excel(name = "考勤得分")
        @Builder.Default
        private BigDecimal attendanceScore = BigDecimal.ZERO;

        // 综合得分
        @Builder.Default
        private BigDecimal compositeScore = BigDecimal.ZERO;
        // 排名
        private Integer rank;

        private String sourceGrade;
        private String organGrade;

        private String partyWillGrade;
    }

    @Data
    public static class ScoreLevelTempParam {
        private String personnelType;
        private List<BizAssessEvaluationGrade> levelTemps;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExportLevelGrade {
//        @Excel(name = "任务ID")
        private String taskId;
        private String taskName;
//        @Excel(name = "用户ID")
        private Long userId;
        private String personnelType;
        private String special;
        private String belongYear;
//        @Excel(name = "季度")
        private String cycleDesc;
        @Excel(name = "季度")
        private String quarter;

        private Long deptId;
        @Excel(name = "用户姓名")
        private String userName;

        @Excel(name = "部门名称")
        private String deptName;
        @Excel(name = "编制", readConverterExp = "1=行政,2=参公,3=事业,4=企业")
        private String identityType;

        @Builder.Default
        private BigDecimal deptScore=BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal leaderScore=BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal unusualScore=BigDecimal.ZERO;
        @Builder.Default
        private BigDecimal disciplineScore=BigDecimal.ZERO;

        @Excel(name = "最终得分")
        @Builder.Default
        private BigDecimal score=BigDecimal.ZERO;
        @Excel(name = "最终评定等次")
        private String grade;
    }



    @Data
    public static class ExportCompositeLevel{
        private String belongYear;
        private String quarter;
        // 填报部门
        private String fillingDeptName;
        // 人员描述
        private String personnelDesc;

        public List<ExportEvaluationComposite> list;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExportEvaluationComposite{

        private String deptName;

        private String userName;

        /**
         * 打分Json数组
         */
        private String  scoringJson;
        /**
         * 所在处室打分平局分
         */
        @Builder.Default
        private BigDecimal deptAverageScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal deptScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal leaderScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal unusualScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal subtotalScore = BigDecimal.ZERO;

        @Builder.Default
        private Integer attendanceNum =0;

        @Builder.Default
        private BigDecimal attendanceScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal disciplineScore = BigDecimal.ZERO;

        @Builder.Default
        private BigDecimal compositeScore= BigDecimal.ZERO;
        // 建议等次（主要负责人或分管领导建议）

        private String grade;
        //机关党委审定建议等次
        private String organGrade;
        // 党组会建议
        private String partyWillGrade;
    }


    @Data
    public static class PromulgateResult{
        private BizAssessPromulgate promulgate;
        private List<Publicity> publicityList;
    }

    @Data
    public static  class Publicity{
        private String promulgateId;
        private String taskId;
        private String special;
        private String belongYear;
        private String quarter;

        private String deptName;
        private String userName;
        private String finalOpinion;
    }
}
