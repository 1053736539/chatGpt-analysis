package com.cb.assess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessIndicatorProDutyMapper;
import com.cb.assess.domain.BizAssessIndicatorProDuty;
import com.cb.assess.service.IBizAssessIndicatorProDutyService;

/**
 * 被考核部门Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-01
 */
@Service
public class BizAssessIndicatorProDutyServiceImpl implements IBizAssessIndicatorProDutyService {
    @Autowired
    private BizAssessIndicatorProDutyMapper bizAssessIndicatorProDutyMapper;


    @Override
    public int batchInsertProDuty(List<BizAssessIndicatorProDuty> list) {
        return bizAssessIndicatorProDutyMapper.batchInsertProDuty(list);
    }

    /**
     * 批量删除被考核部门
     *
     * @param proIds 需要删除的被考核部门ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorProDutyByIds(String[] proIds) {
        return bizAssessIndicatorProDutyMapper.deleteBizAssessIndicatorProDutyByIds(proIds);
    }

    /**
     * 删除被考核部门信息
     *
     * @param proId 被考核部门ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorProDutyById(String proId) {
        return bizAssessIndicatorProDutyMapper.deleteBizAssessIndicatorProDutyById(proId);
    }
}
