package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityOperate;

/**
 * 廉政档案-19.本人或本人子女操办婚丧嫁娶情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityOperateService {
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
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityOperate> entities);

    /**
     * 修改廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param bizProbityOperate 廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     * @return 结果
     */
    public int updateBizProbityOperate(BizProbityOperate bizProbityOperate);

    /**
     * 批量删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况
     *
     * @param ids 需要删除的廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 结果
     */
    public int deleteBizProbityOperateByIds(String[] ids);

    /**
     * 删除廉政档案-19.本人或本人子女操办婚丧嫁娶情况信息
     *
     * @param id 廉政档案-19.本人或本人子女操办婚丧嫁娶情况ID
     * @return 结果
     */
    public int deleteBizProbityOperateById(String id);
}
