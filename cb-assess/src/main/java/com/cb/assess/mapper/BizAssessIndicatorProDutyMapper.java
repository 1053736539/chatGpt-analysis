package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorProDuty;

/**
 * 被考核部门Mapper接口
 *
 * @author ouyang
 * @date 2023-11-01
 */
public interface BizAssessIndicatorProDutyMapper {

    public int batchInsertProDuty(List<BizAssessIndicatorProDuty> list);
    /**
     * 删除被考核部门
     *
     * @param proId 被考核部门ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProDutyById(String proId);

    /**
     * 批量删除被考核部门
     *
     * @param proIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProDutyByIds(String[] proIds);
}
