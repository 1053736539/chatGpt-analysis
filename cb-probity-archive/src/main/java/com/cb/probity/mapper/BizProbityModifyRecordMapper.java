package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityModifyRecord;

/**
 * 修改廉政档案记录Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-26
 */
public interface BizProbityModifyRecordMapper {
    /**
     * 查询修改廉政档案记录
     *
     * @param id 修改廉政档案记录ID
     * @return 修改廉政档案记录
     */
    public BizProbityModifyRecord selectBizProbityModifyRecordById(String id);

    /**
     * 查询修改廉政档案记录列表
     *
     * @param bizProbityModifyRecord 修改廉政档案记录
     * @return 修改廉政档案记录集合
     */
    public List<BizProbityModifyRecord> selectBizProbityModifyRecordList(BizProbityModifyRecord bizProbityModifyRecord);

    /**
     * 新增修改廉政档案记录
     *
     * @param bizProbityModifyRecord 修改廉政档案记录
     * @return 结果
     */
    public int insertBizProbityModifyRecord(BizProbityModifyRecord bizProbityModifyRecord);


    /**
     * 批量新增修改廉政档案记录
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityModifyRecord> entities);

    /**
     * 修改修改廉政档案记录
     *
     * @param bizProbityModifyRecord 修改廉政档案记录
     * @return 结果
     */
    public int updateBizProbityModifyRecord(BizProbityModifyRecord bizProbityModifyRecord);

    /**
     * 删除修改廉政档案记录
     *
     * @param id 修改廉政档案记录ID
     * @return 结果
     */
    public int deleteBizProbityModifyRecordById(String id);

    /**
     * 批量删除修改廉政档案记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityModifyRecordByIds(String[] ids);

    List<BizProbityModifyRecord> searchPrevRecorde(@Param("userId") Long userId, @Param("probityId") String probityId);

    int deleteByProbityId(@Param("probityId") String probityId);
}
