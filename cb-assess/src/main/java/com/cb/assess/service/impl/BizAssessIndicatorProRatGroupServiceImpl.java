package com.cb.assess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessIndicatorProRatGroupMapper;
import com.cb.assess.domain.BizAssessIndicatorProRatGroup;
import com.cb.assess.service.IBizAssessIndicatorProRatGroupService;

/**
 * 考核方案关联评分组Service业务层处理
 * 
 * @author ouyang
 * @date 2023-11-01
 */
@Service
public class BizAssessIndicatorProRatGroupServiceImpl implements IBizAssessIndicatorProRatGroupService 
{
    @Autowired
    private BizAssessIndicatorProRatGroupMapper bizAssessIndicatorProRatGroupMapper;

}
