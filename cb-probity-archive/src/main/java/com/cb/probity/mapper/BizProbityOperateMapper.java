package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityOperate;

/**
 * 廉政档案-19.本人或本人子女操办婚丧嫁娶情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityOperateMapper {
    /**
     * 查询廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param id 廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     */
    public BizProbityOperate selectBizProbityOperateById(String id);

    /**
     * 查询廉政档案-19.本人或本人子女操办婚丧嫁娶情况列表
     *
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 廉政档案-19.本人或本人子女操办婚丧嫁娶情况集合
     */
    public List<BizProbityOperate> selectBizProbityOperateList(BizProbityOperate bizProbityOperate);

    /**
     * 新增廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 结果
     */
    public int insertBizProbityOperate(BizProbityOperate bizProbityOperate);


    /**
     * 批量新增廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityOperate> entities);

    /**
     * 修改廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 结果
     */
    public int updateBizProbityOperate(BizProbityOperate bizProbityOperate);

    /**
     * 删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param id 廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 结果
     */
    public int deleteBizProbityOperateById(String id);

    /**
     * 批量删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityOperateByIds(String[] ids);
}
