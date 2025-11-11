package com.cb.assess.mapper;

import com.cb.assess.vo.VYearAssessHandleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VYearAssessHandleMapper {

    List<VYearAssessHandleVo.HandleInfo> selectList(@Param("req") VYearAssessHandleVo.Req req);

}
