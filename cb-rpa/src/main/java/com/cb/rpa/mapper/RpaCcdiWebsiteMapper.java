package com.cb.rpa.mapper;

import com.cb.rpa.domain.RpaCcdiWebsite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 中纪委Mapper接口
 * 
 * @author ouyang
 * @date 2024-11-13
 */
public interface RpaCcdiWebsiteMapper 
{
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
     * 删除中纪委
     * 
     * @param id 中纪委ID
     * @return 结果
     */
    public int deleteRpaCcdiWebsiteById(String id);

    /**
     * 批量删除中纪委
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRpaCcdiWebsiteByIds(String[] ids);

    public RpaCcdiWebsite existence(String title);
}
