package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbity;
import com.cb.probity.vo.ProbityInfoVo;

/**
 * 廉政档案记录Service接口
 *
 * @author ruoyi
 * @date 2025-03-13
 */
public interface IBizProbityService {
    /**
     * 查询廉政档案记录
     *
     * @param id 廉政档案记录ID
     * @return 廉政档案记录
     */
    public BizProbity selectBizProbityById(String id);

    /**
     * 查询廉政档案记录列表
     *
     * @param bizProbity 廉政档案记录
     * @return 廉政档案记录集合
     */
    public List<BizProbity> selectBizProbityList(BizProbity bizProbity);

    /**
     * 新增廉政档案记录
     *
     * @param bizProbity 廉政档案记录
     * @return 结果
     */
    public String insertBizProbity(BizProbity bizProbity);

    /**
     * 批量新增廉政档案记录
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbity> entities);

    /**
     * 修改廉政档案记录
     *
     * @param bizProbity 廉政档案记录
     * @return 结果
     */
    public int updateBizProbity(BizProbity bizProbity);

    /**
     * 批量删除廉政档案记录
     *
     * @param ids 需要删除的廉政档案记录ID
     * @return 结果
     */
    public int deleteBizProbityByIds(String[] ids);

    /**
     * 删除廉政档案记录信息
     *
     * @param id 廉政档案记录ID
     * @return 结果
     */
    public int deleteBizProbityById(String id);

    ProbityInfoVo searchInfoById(String id);

    String updateProbityInfo(ProbityInfoVo vo);

    int verifyBizProbity(String[] ids);

    int applyModifyProbity(String[] ids);

    int updateConfirmStatus(String probityId, String number);
}
