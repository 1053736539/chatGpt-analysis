package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorProRatGroup;

/**
 * 考核方案关联评分组Mapper接口
 *
 * @author ouyang
 * @date 2023-11-01
 */
public interface BizAssessIndicatorProRatGroupMapper {

    public List<BizAssessIndicatorProRatGroup> selectListByProId(String proId);

    public List<BizAssessIndicatorProRatGroup> selectListByProIds(List<String> list);

    public int batchInsertProRatGroup(List<BizAssessIndicatorProRatGroup> list);

    /**
     * 删除考核方案关联评分组
     *
     * @param id 考核方案关联评分组ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProRatGroupById(String proId);

    /**
     * 批量删除考核方案关联评分组
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProRatGroupByIds(String[] proIds);
}
