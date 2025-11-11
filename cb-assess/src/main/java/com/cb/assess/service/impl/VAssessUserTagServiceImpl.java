package com.cb.assess.service.impl;

import com.cb.assess.domain.BizAssessTaskAssessUserConfirm;
import com.cb.assess.domain.VAssessUserTag;
import com.cb.assess.mapper.VAssessUserTagMapper;
import com.cb.assess.service.VAssessUserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VAssessUserTagServiceImpl implements VAssessUserTagService {
    @Autowired
    private VAssessUserTagMapper userTagMapper;

    @Override
    public List<VAssessUserTag> selectVAssessUserTagList(VAssessUserTag tag) {
        List<VAssessUserTag> list = userTagMapper.selectVAssessUserTagList(tag);
        list.forEach(o->{
            String taskId = o.getTaskId();
            Long userId = o.getUserId();
            List<Map<String, Object>> maps = userTagMapper.selectVoteStatusList(taskId, userId);
            List<Map<String, Object>> sorted = maps.stream()
                    .sorted(Comparator.comparing(m -> Integer.parseInt(m.get("status").toString())))
                    .collect(Collectors.toList());
            o.setVoteStatusList(sorted);
        });
        return list;
    }

    @Override
    public List<Map<String, Object>> selectVoteStatusList(String taskId, Long userId) {
        return userTagMapper.selectVoteStatusList(taskId,userId);
    }
}
