package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorProDuty;

/**
 * 被考核部门Service接口
 *
 * @author ouyang
 * @date 2023-11-01
 */
public interface IBizAssessIndicatorProDutyService {


    public int batchInsertProDuty(List<BizAssessIndicatorProDuty> list);
    /**
     * 批量删除被考核部门
     *
     * @param proIds 需要删除的被考核部门ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProDutyByIds(String[] proIds);

    /**
     * 删除被考核部门信息
     *
     * @param proId 被考核部门ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProDutyById(String proId);
}
