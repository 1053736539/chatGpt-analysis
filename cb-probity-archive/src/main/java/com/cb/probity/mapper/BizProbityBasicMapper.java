package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityBasic;

/**
 * 廉政档案-1.报告人基本情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityBasicMapper {
    /**
     * 查询廉政档案-1.报告人基本情况
     *
     * @param id 廉政档案-1.报告人基本情况ID
     * @return 廉政档案-1.报告人基本情况
     */
    public BizProbityBasic selectBizProbityBasicById(String id);

    /**
     * 查询廉政档案-1.报告人基本情况列表
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 廉政档案-1.报告人基本情况集合
     */
    public List<BizProbityBasic> selectBizProbityBasicList(BizProbityBasic bizProbityBasic);

    /**
     * 新增廉政档案-1.报告人基本情况
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 结果
     */
    public int insertBizProbityBasic(BizProbityBasic bizProbityBasic);


    /**
     * 批量新增廉政档案-1.报告人基本情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityBasic> entities);

    /**
     * 修改廉政档案-1.报告人基本情况
     *
     * @param bizProbityBasic 廉政档案-1.报告人基本情况
     * @return 结果
     */
    public int updateBizProbityBasic(BizProbityBasic bizProbityBasic);

    /**
     * 删除廉政档案-1.报告人基本情况
     *
     * @param id 廉政档案-1.报告人基本情况ID
     * @return 结果
     */
    public int deleteBizProbityBasicById(String id);

    /**
     * 批量删除廉政档案-1.报告人基本情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityBasicByIds(String[] ids);
}
