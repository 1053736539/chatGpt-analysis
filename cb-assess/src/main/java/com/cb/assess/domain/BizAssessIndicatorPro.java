package com.cb.assess.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import com.cb.filemanage.domain.BizAttach;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * 考核方案对象 biz_assess_indicator_pro
 *
 * @author ouyang
 * @date 2023-11-01
 */
@Data
public class BizAssessIndicatorPro extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 方案ID
     */
    private String proId;

    /**
     * 方案名称
     */
    @Excel(name = "方案名称")
    private String proName;

    /**
     * 责任单位
     */
    @Excel(name = "责任单位")
    private Integer dutyUnit;

    /**
     * 考核体系ID
     */
    @Excel(name = "考核体系ID")
    private String proIsmId;
    /**
     * 是否是专项考核 0 否 1 是
     */
    private String special;
    /**
     * 考核对象类型（special 为0 时有效）
     */
    private String personnelType;

    /**
     * 考核周期
     */
    @Excel(name = "考核周期")
    private String proCycle;

    /**
     * 考核年份（字段弃用）
     */
    @Excel(name = "考核年份")
    private String proYear;

    /**
     * 总分数
     */
    @Excel(name = "总分数")
    private Integer score;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String proDesc;

    /**
     * 0 未审核 ，1 通过 ，2不通过 3 发布
     */
    @Excel(name = "0 未审核 ，1 通过 ，2不通过 3 发布")
    private String status;

    /**
     * 状态为1，2 时的意见
     */
    private String auditOption;

    /**
     * 字典参数
     */
    private String delFlag;

    private String proFileId;

    private String proWorkTipFileId;//工作提示附件id

    @Transient
    private List<BizAttach> proWorkTipFile;//工作提示附件信息

    private BizAssessIndicatorIsm ism;

    private List<BizAssessIndicatorProDuty> dutyList;

    private List<BizAssessIndicatorProRatGroup> ratGroupList;

    private List<BizAssessIndicatorProProcedure> procedureList;

    private List<BizAssessIndicatorProAssessors> assessors;
}
