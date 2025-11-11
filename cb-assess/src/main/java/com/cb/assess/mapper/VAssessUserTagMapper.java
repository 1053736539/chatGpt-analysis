package com.cb.assess.mapper;

import com.cb.assess.domain.VAssessUserTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VAssessUserTagMapper {

    public List<VAssessUserTag> selectVAssessUserTagList(VAssessUserTag tag);


    public List<Map<String,Object>> selectVoteStatusList(@Param("taskId") String taskId, @Param("userId")Long userId);
}
