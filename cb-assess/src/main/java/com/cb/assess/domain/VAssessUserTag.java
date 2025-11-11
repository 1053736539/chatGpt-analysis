package com.cb.assess.domain;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;
import java.util.Map;

@Data
public class VAssessUserTag {
    private String taskId;
    private String taskName;
    private Long userId;
    private String userName;
    private String deptName;
    @Transient
    private BizIndividualAssessmentTask task;

    @Transient
    private SysUser user;

    @Transient
    private SysDept dept;

    List<Map<String, Object>> voteStatusList;

    /**
     * 是否必填总结
     */
    private Boolean mustFill;
    /**
     * 是否填写了总结
     */
    private Boolean isFill;
}
