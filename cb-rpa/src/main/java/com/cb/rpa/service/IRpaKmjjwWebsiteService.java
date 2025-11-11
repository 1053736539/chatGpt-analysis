package com.cb.rpa.service;

import java.util.List;

import com.cb.rpa.domain.RpaKmjjwWebsite;

/**
 * 昆明市纪监委网站Service接口
 *
 * @author ouyang
 * @date 2024-10-22
 */
public interface IRpaKmjjwWebsiteService {
    /**
     * 查询昆明市纪监委网站
     *
     * @param id 昆明市纪监委网站ID
     * @return 昆明市纪监委网站
     */
    public RpaKmjjwWebsite selectRpaKmjjwWebsiteById(String id);

    /**
     * 查询昆明市纪监委网站列表
     *
     * @param rpaKmjjwWebsite 昆明市纪监委网站
     * @return 昆明市纪监委网站集合
     */
    public List<RpaKmjjwWebsite> selectRpaKmjjwWebsiteList(RpaKmjjwWebsite rpaKmjjwWebsite);

    /**
     * 新增昆明市纪监委网站
     *
     * @param rpaKmjjwWebsite 昆明市纪监委网站
     * @return 结果
     */
    public int insertRpaKmjjwWebsite(RpaKmjjwWebsite rpaKmjjwWebsite);

    /**
     * 修改昆明市纪监委网站
     *
     * @param rpaKmjjwWebsite 昆明市纪监委网站
     * @return 结果
     */
    public int updateRpaKmjjwWebsite(RpaKmjjwWebsite rpaKmjjwWebsite);

    /**
     * 批量删除昆明市纪监委网站
     *
     * @param ids 需要删除的昆明市纪监委网站ID
     * @return 结果
     */
    public int deleteRpaKmjjwWebsiteByIds(String[] ids);

    /**
     * 删除昆明市纪监委网站信息
     *
     * @param id 昆明市纪监委网站ID
     * @return 结果
     */
    public int deleteRpaKmjjwWebsiteById(String id);

    /**
     * rpa 采集信息
     * @param rpaKmjjwWebsite
     * @return
     */
    public int collectWebsiteInfo(RpaKmjjwWebsite rpaKmjjwWebsite);

    public int push2AiKnowledge(RpaKmjjwWebsite website);
}
