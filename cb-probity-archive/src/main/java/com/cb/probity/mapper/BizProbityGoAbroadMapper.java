package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityGoAbroad;

/**
 * 廉政档案-6.2本人因私出国（境）及往来港澳台情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityGoAbroadMapper {
    /**
     * 查询廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @param id 廉政档案-6.2本人因私出国（境）及往来港澳台情况ID
     * @return 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     */
    public BizProbityGoAbroad selectBizProbityGoAbroadById(String id);

    /**
     * 查询廉政档案-6.2本人因私出国（境）及往来港澳台情况列表
     *
     * @param bizProbityGoAbroad 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * @return 廉政档案-6.2本人因私出国（境）及往来港澳台情况集合
     */
    public List<BizProbityGoAbroad> selectBizProbityGoAbroadList(BizProbityGoAbroad bizProbityGoAbroad);

    /**
     * 新增廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @param bizProbityGoAbroad 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * @return 结果
     */
    public int insertBizProbityGoAbroad(BizProbityGoAbroad bizProbityGoAbroad);


    /**
     * 批量新增廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityGoAbroad> entities);

    /**
     * 修改廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @param bizProbityGoAbroad 廉政档案-6.2本人因私出国（境）及往来港澳台情况
     * @return 结果
     */
    public int updateBizProbityGoAbroad(BizProbityGoAbroad bizProbityGoAbroad);

    /**
     * 删除廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @param id 廉政档案-6.2本人因私出国（境）及往来港澳台情况ID
     * @return 结果
     */
    public int deleteBizProbityGoAbroadById(String id);

    /**
     * 批量删除廉政档案-6.2本人因私出国（境）及往来港澳台情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityGoAbroadByIds(String[] ids);
}
