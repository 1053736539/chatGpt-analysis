package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityBasic;

/**
 * 廉政档案-1.报告人基本情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityBasicService {
    /**
     * 查询廉政档案-1.报告人基本情况
     *
     * @param id 廉政档案-1.报告人基本情况ID
     * @return 廉政档案-1.报告人基本情况
     */
    public BizProbityBasic selectBizProbityBasicById(String id);

    /**
     * 查询廉政档案-1.报告人基本情况列表
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 廉政档案-1.报告人基本情况集合
     */
    public List<BizProbityBasic> selectBizProbityBasicList(BizProbityBasic bizProbityBasic);

    /**
     * 新增廉政档案-1.报告人基本情况
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 结果
     */
    public int insertBizProbityBasic(BizProbityBasic bizProbityBasic);

    /**
     * 批量新增廉政档案-1.报告人基本情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityBasic> entities);

    /**
     * 修改廉政档案-1.报告人基本情况
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 结果
     */
    public int updateBizProbityBasic(BizProbityBasic bizProbityBasic);

    /**
     * 批量删除廉政档案-1.报告人基本情况
     *
     * @param ids 需要删除的廉政档案-1.报告人基本情况ID
     * @return 结果
     */
    public int deleteBizProbityBasicByIds(String[] ids);

    /**
     * 删除廉政档案-1.报告人基本情况信息
     *
     * @param id 廉政档案-1.报告人基本情况ID
     * @return 结果
     */
    public int deleteBizProbityBasicById(String id);
}
