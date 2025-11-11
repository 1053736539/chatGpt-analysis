package com.cb.adopt.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface AdoptVo {

    @Data
    class StatisticReq {
        @NotBlank
        private String beginDate;//起始日期 yyyy-MM-dd
        @NotBlank
        private String endDate;//截止日期 yyyy-MM-dd
    }

    /**
     * 统计的单条数据
     */
    @Data
    class StatisticItem{
        /** 部门名称 */
        private String dept;

        private Integer c_j_c;      // 市纪委监委 计数
        private Integer c_j_s;      // 市纪委监委 总分

        private Integer c_o_c;      // 市委办公室 计数
        private Integer c_o_s;      // 市委办公室 总分

        private Integer p_j_c;      // 省纪委监委 计数
        private Integer p_j_s;      // 省纪委监委 总分

        private Integer p_o_c;      // 省委办公厅 计数
        private Integer p_o_s;      // 省委办公厅 总分

        private Integer ce_j_c;     // 中央纪委国家监委 计数
        private Integer ce_j_s;     // 中央纪委国家监委 总分

        private Integer li_c;       // 领导批示 计数
        private Integer li_s;       // 领导批示 总分

        private Integer a_c;        // 累计数量（总数）
        private Integer a_s;        // 累计分数（总分）

        public static StatisticItem emptyEntity(String dept){
            StatisticItem rst = new StatisticItem();
            rst.setDept(dept);
            rst.setC_j_c(0);
            rst.setC_j_s(0);
            rst.setC_o_c(0);
            rst.setC_o_s(0);
            rst.setP_j_c(0);
            rst.setP_j_s(0);
            rst.setP_o_c(0);
            rst.setP_o_s(0);
            rst.setCe_j_c(0);
            rst.setCe_j_s(0);
            rst.setLi_c(0);
            rst.setLi_s(0);
            rst.setA_c(0);
            rst.setA_s(0);
            return rst;
        }

    }

    /**
     * 查询载体采用记录的单条数据
     */
    @Data
    class ListCarrierRecordScoreItem{
        private String dept;//部门名称
        private Integer deptType;//部门类型
        private Integer deptOrderNo;//部门排序
        private String score;//分数
        private Integer mainFlag;//是否主报单位
        private String recordId;//记录id
        private String level;//采用级别
        private String carrier;//采用载体
        private String title;//标题
        private String issueNo;//期号
    }

    /**
     * 用于导出word时的循环渲染
     */
    @Data
    class CarrierRenderItem{
        private String level;
        private String carrier;
        private List<CarrierRecordItem> recordList;
    }

    /**
     * 用于导出载体采用记录的数据单项
     */
    @Data
    class CarrierRecordItem{
        private Integer indexNo;
        private String issueNo;
        private String title;
        private String mainDept;
        private String adoptDept;
        private String otherDept;
        private String fullDept;
    }

}
