package com.cb.assess.mapper;

import com.cb.assess.domain.VAssessmentProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VAssessmentProcessMapper {

    List<VAssessmentProcess> selectProcessList(@Param("vo") VAssessmentProcess.ProcessVo processVo,@Param("roleKeys") String roleKeys);
}
