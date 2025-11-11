package com.cb.assess.mapper;

import com.cb.assess.domain.VAssessIndicator;

import java.util.List;

/**
 * 考核指标体系Mapper接口
 *
 * @author ouyang
 * @date 2023-10-26
 */
public interface VAssessIndicatorMapper {
    public List<VAssessIndicator> selectAssessIndicatorSelectorList(VAssessIndicator assessIndicator);

    public List<VAssessIndicator> selectAllAssessIndicatorSelectorList(VAssessIndicator assessIndicator);
}
