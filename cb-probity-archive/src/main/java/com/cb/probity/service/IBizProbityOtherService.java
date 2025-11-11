package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityOther;

/**
 * 廉政档案-20.个人认为需要报告的其他事项Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityOtherService {
    /**
     * 查询廉政档案-20.个人认为需要报告的其他事项
     *
     * @param id 廉政档案-20.个人认为需要报告的其他事项ID
     * @return 廉政档案-20.个人认为需要报告的其他事项
     */
    public BizProbityOther selectBizProbityOtherById(String id);

    /**
     * 查询廉政档案-20.个人认为需要报告的其他事项列表
     *
     * @param bizProbityOther 廉政档案-20.个人认为需要报告的其他事项
     * @return 廉政档案-20.个人认为需要报告的其他事项集合
     */
    public List<BizProbityOther> selectBizProbityOtherList(BizProbityOther bizProbityOther);

    /**
     * 新增廉政档案-20.个人认为需要报告的其他事项
     *
     * @param bizProbityOther 廉政档案-20.个人认为需要报告的其他事项
     * @return 结果
     */
    public int insertBizProbityOther(BizProbityOther bizProbityOther);

    /**
     * 批量新增廉政档案-20.个人认为需要报告的其他事项
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityOther> entities);

    /**
     * 修改廉政档案-20.个人认为需要报告的其他事项
     *
     * @param bizProbityOther 廉政档案-20.个人认为需要报告的其他事项
     * @return 结果
     */
    public int updateBizProbityOther(BizProbityOther bizProbityOther);

    /**
     * 批量删除廉政档案-20.个人认为需要报告的其他事项
     *
     * @param ids 需要删除的廉政档案-20.个人认为需要报告的其他事项ID
     * @return 结果
     */
    public int deleteBizProbityOtherByIds(String[] ids);

    /**
     * 删除廉政档案-20.个人认为需要报告的其他事项信息
     *
     * @param id 廉政档案-20.个人认为需要报告的其他事项ID
     * @return 结果
     */
    public int deleteBizProbityOtherById(String id);
}
