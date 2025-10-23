package com.cb.worksituation.mapper;

import java.util.List;

import com.cb.worksituation.domain.BusDepReviewUnitName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2025-10-15
 */
@Mapper
public interface BusDepReviewUnitNameMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public BusDepReviewUnitName selectBusDepReviewUnitNameById(String id);

    /**
     * 查询【请填写功能名称】列表
     * @return 【请填写功能名称】集合
     */
    public List<BusDepReviewUnitName> getBusinessCollaborationUnit();

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BusDepReviewUnitName> selectBusDepReviewUnitNameList(BusDepReviewUnitName busDepReviewUnitName);

    /**
     * 新增【请填写功能名称】
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 结果
     */
    public int insertBusDepReviewUnitName(BusDepReviewUnitName busDepReviewUnitName);


    /**
     * 批量新增【请填写功能名称】
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BusDepReviewUnitName> entities);

    /**
     * 修改【请填写功能名称】
     *
     * @param busDepReviewUnitName 【请填写功能名称】
     * @return 结果
     */
    public int updateBusDepReviewUnitName(BusDepReviewUnitName busDepReviewUnitName);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusDepReviewUnitNameById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusDepReviewUnitNameByIds(String[] ids);
}
