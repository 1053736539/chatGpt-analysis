package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityLiveAbroad;

/**
 * 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityLiveAbroadMapper {
    /**
     * 查询廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param id 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     */
    public BizProbityLiveAbroad selectBizProbityLiveAbroadById(String id);

    /**
     * 查询廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况列表
     *
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况集合
     */
    public List<BizProbityLiveAbroad> selectBizProbityLiveAbroadList(BizProbityLiveAbroad bizProbityLiveAbroad);

    /**
     * 新增廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 结果
     */
    public int insertBizProbityLiveAbroad(BizProbityLiveAbroad bizProbityLiveAbroad);


    /**
     * 批量新增廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityLiveAbroad> entities);

    /**
     * 修改廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 结果
     */
    public int updateBizProbityLiveAbroad(BizProbityLiveAbroad bizProbityLiveAbroad);

    /**
     * 删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param id 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 结果
     */
    public int deleteBizProbityLiveAbroadById(String id);

    /**
     * 批量删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityLiveAbroadByIds(String[] ids);
}
