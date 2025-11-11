package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityModifyApproval;

/**
 * 申请修改廉政档案的记录Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityModifyApprovalMapper {
    /**
     * 查询申请修改廉政档案的记录
     *
     * @param id 申请修改廉政档案的记录ID
     * @return 申请修改廉政档案的记录
     */
    public BizProbityModifyApproval selectBizProbityModifyApprovalById(String id);

    /**
     * 查询申请修改廉政档案的记录列表
     *
     * @param bizProbityModifyApproval 申请修改廉政档案的记录
     * @return 申请修改廉政档案的记录集合
     */
    public List<BizProbityModifyApproval> selectBizProbityModifyApprovalList(BizProbityModifyApproval bizProbityModifyApproval);

    /**
     * 新增申请修改廉政档案的记录
     *
     * @param bizProbityModifyApproval 申请修改廉政档案的记录
     * @return 结果
     */
    public int insertBizProbityModifyApproval(BizProbityModifyApproval bizProbityModifyApproval);


    /**
     * 批量新增申请修改廉政档案的记录
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityModifyApproval> entities);

    /**
     * 修改申请修改廉政档案的记录
     *
     * @param bizProbityModifyApproval 申请修改廉政档案的记录
     * @return 结果
     */
    public int updateBizProbityModifyApproval(BizProbityModifyApproval bizProbityModifyApproval);

    /**
     * 删除申请修改廉政档案的记录
     *
     * @param id 申请修改廉政档案的记录ID
     * @return 结果
     */
    public int deleteBizProbityModifyApprovalById(String id);

    /**
     * 批量删除申请修改廉政档案的记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityModifyApprovalByIds(String[] ids);
}
