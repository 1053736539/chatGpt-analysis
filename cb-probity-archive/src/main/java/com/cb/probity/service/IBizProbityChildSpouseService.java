package com.cb.probity.service;

import java.util.List;

import com.cb.probity.domain.BizProbityChildSpouse;

/**
 * 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况Service接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface IBizProbityChildSpouseService {
    /**
     * 查询廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     *
     * @param id 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况ID
     * @return 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     */
    public BizProbityChildSpouse selectBizProbityChildSpouseById(String id);

    /**
     * 查询廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况列表
     *
     * @param bizProbityChildSpouse 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     * @return 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况集合
     */
    public List<BizProbityChildSpouse> selectBizProbityChildSpouseList(BizProbityChildSpouse bizProbityChildSpouse);

    /**
     * 新增廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     *
     * @param bizProbityChildSpouse 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     * @return 结果
     */
    public int insertBizProbityChildSpouse(BizProbityChildSpouse bizProbityChildSpouse);

    /**
     * 批量新增廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     *
     * @param entities
     * @return
     */
    public int insertBatch(List<BizProbityChildSpouse> entities);

    /**
     * 修改廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     *
     * @param bizProbityChildSpouse 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     * @return 结果
     */
    public int updateBizProbityChildSpouse(BizProbityChildSpouse bizProbityChildSpouse);

    /**
     * 批量删除廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况
     *
     * @param ids 需要删除的廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况ID
     * @return 结果
     */
    public int deleteBizProbityChildSpouseByIds(String[] ids);

    /**
     * 删除廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况信息
     *
     * @param id 廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况ID
     * @return 结果
     */
    public int deleteBizProbityChildSpouseById(String id);
}
