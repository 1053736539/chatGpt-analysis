package com.cb.assess.service;

import com.cb.assess.domain.BizAssessIndicator;
import com.cb.assess.domain.VAssessIndicator;

import java.util.List;

/**
 * 考核指标体系Service接口
 *
 * @author ouyang
 * @date 2023-10-26
 */
public interface IVAssessIndicatorService {
    public List<VAssessIndicator> selectAssessIndicatorSelectorList(VAssessIndicator assessIndicator);

    public List<VAssessIndicator> selectAllAssessIndicatorSelectorList(VAssessIndicator assessIndicator);
}
