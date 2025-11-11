package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbity;

/**
 * 廉政档案记录Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-13
 */
public interface BizProbityMapper {
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
    public int insertBizProbity(BizProbity bizProbity);


    /**
     * 批量新增廉政档案记录
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbity> entities);

    /**
     * 修改廉政档案记录
     *
     * @param bizProbity 廉政档案记录
     * @return 结果
     */
    public int updateBizProbity(BizProbity bizProbity);

    /**
     * 删除廉政档案记录
     *
     * @param id 廉政档案记录ID
     * @return 结果
     */
    public int deleteBizProbityById(String id);

    /**
     * 批量删除廉政档案记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityByIds(String[] ids);

    void deleteInfoByProbityId(@Param("probityId") String probityId);

    int verifyBizProbity(String[] ids);

    int updateConfirmStatus(@Param("id") String id,@Param("confirmStatus") String confirmStatus);
}
