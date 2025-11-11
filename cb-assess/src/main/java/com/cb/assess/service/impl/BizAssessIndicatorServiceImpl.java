package com.cb.assess.service.impl;

import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessIndicatorMapper;
import com.cb.assess.domain.BizAssessIndicator;
import com.cb.assess.service.IBizAssessIndicatorService;

/**
 * 考核指标体系Service业务层处理
 *
 * @author ouyang
 * @date 2023-10-26
 */
@Service
public class BizAssessIndicatorServiceImpl implements IBizAssessIndicatorService {
    @Autowired
    private BizAssessIndicatorMapper bizAssessIndicatorMapper;

    /**
     * 查询考核指标体系
     *
     * @param indId 考核指标体系ID
     * @return 考核指标体系
     */
    @Override
    public BizAssessIndicator selectBizAssessIndicatorById(String indId) {
        return bizAssessIndicatorMapper.selectBizAssessIndicatorById(indId);
    }

    /**
     * 查询考核指标体系列表
     *
     * @param bizAssessIndicator 考核指标体系
     * @return 考核指标体系
     */
    @Override
    public List<BizAssessIndicator> selectBizAssessIndicatorList(BizAssessIndicator bizAssessIndicator) {
        return bizAssessIndicatorMapper.selectBizAssessIndicatorList(bizAssessIndicator);
    }


    /**
     * 新增考核指标体系
     *
     * @param bizAssessIndicator 考核指标体系
     * @return 结果
     */
    @Override
    public int insertBizAssessIndicator(BizAssessIndicator bizAssessIndicator) {
        bizAssessIndicator.setIndId(IdUtils.randomUUID());
        bizAssessIndicator.setCreateBy(SecurityUtils.getUsername());
        bizAssessIndicator.setCreateTime(DateUtils.getNowDate());
        int row = bizAssessIndicatorMapper.insertBizAssessIndicator(bizAssessIndicator);
        return row;
    }

    /**
     * 修改考核指标体系
     *
     * @param bizAssessIndicator 考核指标体系
     * @return 结果
     */
    @Override
    public int updateBizAssessIndicator(BizAssessIndicator bizAssessIndicator) {
        bizAssessIndicator.setUpdateBy(SecurityUtils.getUsername());
        bizAssessIndicator.setUpdateTime(DateUtils.getNowDate());
        int row = bizAssessIndicatorMapper.updateBizAssessIndicator(bizAssessIndicator);
        return row;
    }

    /**
     * 批量删除考核指标体系
     *
     * @param indIds 需要删除的考核指标体系ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorByIds(String[] indIds) {
        return bizAssessIndicatorMapper.deleteBizAssessIndicatorByIds(indIds);
    }

    /**
     * 删除考核指标体系信息
     *
     * @param indId 考核指标体系ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorById(String indId) {
        return bizAssessIndicatorMapper.deleteBizAssessIndicatorById(indId);
    }

    @Override
    public int changeStatus(BizAssessIndicator bizAssessIndicator) {
        return bizAssessIndicatorMapper.changeStatus(bizAssessIndicator);
    }

    @Override
    public int logicDeleteBizAssessIndicatorById(String indId) {
        return bizAssessIndicatorMapper.logicDeleteBizAssessIndicatorById(indId);
    }

    @Override
    public int logicDeleteBizAssessIndicatorByIds(String[] indIds) {
        return bizAssessIndicatorMapper.logicDeleteBizAssessIndicatorByIds(indIds);
    }
}
