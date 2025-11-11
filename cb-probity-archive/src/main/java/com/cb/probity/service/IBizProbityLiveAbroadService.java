package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityLiveAbroad;

/**
 * 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityLiveAbroadService {
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
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityLiveAbroad> entities);

    /**
     * 修改廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param bizProbityLiveAbroad 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     * @return 结果
     */
    public int updateBizProbityLiveAbroad(BizProbityLiveAbroad bizProbityLiveAbroad);

    /**
     * 批量删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况
     *
     * @param ids 需要删除的廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 结果
     */
    public int deleteBizProbityLiveAbroadByIds(String[] ids);

    /**
     * 删除廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况信息
     *
     * @param id 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况ID
     * @return 结果
     */
    public int deleteBizProbityLiveAbroadById(String id);
}
