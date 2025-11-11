package com.cb.assess.service.impl;

import com.cb.assess.domain.VAssessIndicator;
import com.cb.assess.mapper.VAssessIndicatorMapper;
import com.cb.assess.service.IVAssessIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考核指标体系Service业务层处理
 *
 * @author ouyang
 * @date 2023-10-26
 */
@Service
public class VAssessIndicatorServiceImpl implements IVAssessIndicatorService {
    @Autowired
    private VAssessIndicatorMapper vAssessIndicatorMapper;

    @Override
    public List<VAssessIndicator> selectAssessIndicatorSelectorList(VAssessIndicator assessIndicator) {
        return vAssessIndicatorMapper.selectAssessIndicatorSelectorList(assessIndicator);
    }

    @Override
    public List<VAssessIndicator> selectAllAssessIndicatorSelectorList(VAssessIndicator assessIndicator) {
        return vAssessIndicatorMapper.selectAllAssessIndicatorSelectorList(assessIndicator);
    }
}
