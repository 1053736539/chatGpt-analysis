package com.cb.assess.service;

import com.cb.assess.domain.VAssessUserTag;

import java.util.List;
import java.util.Map;

public interface VAssessUserTagService {
    public List<VAssessUserTag> selectVAssessUserTagList(VAssessUserTag tag);

    public List<Map<String,Object>> selectVoteStatusList(String taskId, Long userId);
}
