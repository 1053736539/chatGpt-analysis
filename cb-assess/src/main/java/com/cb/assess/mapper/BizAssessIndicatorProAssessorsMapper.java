package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessIndicatorProAssessors;

import java.util.List;

public interface BizAssessIndicatorProAssessorsMapper {

    public int batchInsertProAssessors(List<BizAssessIndicatorProAssessors> list);

    public int deleteProAssessorsByProId(String proId);

    public int deleteProAssessorsByProIds(String [] proIds);

}
