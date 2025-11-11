package com.cb.rpa.service;

import com.cb.rpa.domain.RpaCcdiWebsite;

import java.util.List;

/**
 * 中纪委Service接口
 *
 * @author ouyang
 * @date 2024-11-13
 */
public interface IRpaCcdiWebsiteService {
    /**
     * 查询中纪委
     *
     * @param id 中纪委ID
     * @return 中纪委
     */
    public RpaCcdiWebsite selectRpaCcdiWebsiteById(String id);

    /**
     * 查询中纪委列表
     *
     * @param rpaCcdiWebsite 中纪委
     * @return 中纪委集合
     */
    public List<RpaCcdiWebsite> selectRpaCcdiWebsiteList(RpaCcdiWebsite rpaCcdiWebsite);

    /**
     * 新增中纪委
     *
     * @param rpaCcdiWebsite 中纪委
     * @return 结果
     */
    public int insertRpaCcdiWebsite(RpaCcdiWebsite rpaCcdiWebsite);

    /**
     * 修改中纪委
     *
     * @param rpaCcdiWebsite 中纪委
     * @return 结果
     */
    public int updateRpaCcdiWebsite(RpaCcdiWebsite rpaCcdiWebsite);

    /**
     * 批量删除中纪委
     *
     * @param ids 需要删除的中纪委ID
     * @return 结果
     */
    public int deleteRpaCcdiWebsiteByIds(String[] ids);

    /**
     * 删除中纪委信息
     *
     * @param id 中纪委ID
     * @return 结果
     */
    public int deleteRpaCcdiWebsiteById(String id);


    /**
     * rpa 采集信息
     * @param ccdiWebsite
     * @return
     */
    public int collectWebsiteInfo(RpaCcdiWebsite ccdiWebsite);

    public int push2AiKnowledge(RpaCcdiWebsite ccdiWebsite);

}
