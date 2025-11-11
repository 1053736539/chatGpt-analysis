package com.cb.adopt.service;

import java.util.List;
import com.cb.adopt.domain.BizAdoptRecordScore;
import com.cb.adopt.vo.AdoptVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 信息采用记录的相关单位及分值记录Service接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface IBizAdoptRecordScoreService 
{
    /**
     * 查询信息采用记录的相关单位及分值记录
     * 
     * @param id 信息采用记录的相关单位及分值记录ID
     * @return 信息采用记录的相关单位及分值记录
     */
    public BizAdoptRecordScore selectBizAdoptRecordScoreById(String id);

    /**
     * 查询信息采用记录的相关单位及分值记录列表
     * 
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 信息采用记录的相关单位及分值记录集合
     */
    public List<BizAdoptRecordScore> selectBizAdoptRecordScoreList(BizAdoptRecordScore bizAdoptRecordScore);

    /**
     * 新增信息采用记录的相关单位及分值记录
     * 
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 结果
     */
    public int insertBizAdoptRecordScore(BizAdoptRecordScore bizAdoptRecordScore);

    /**
     * 批量新增信息采用记录的相关单位及分值记录
     * @param entities
     * @return
     */
    public int insertBatch(List<BizAdoptRecordScore> entities);

    /**
     * 修改信息采用记录的相关单位及分值记录
     * 
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 结果
     */
    public int updateBizAdoptRecordScore(BizAdoptRecordScore bizAdoptRecordScore);

    /**
     * 批量删除信息采用记录的相关单位及分值记录
     * 
     * @param ids 需要删除的信息采用记录的相关单位及分值记录ID
     * @return 结果
     */
    public int deleteBizAdoptRecordScoreByIds(String[] ids);

    /**
     * 删除信息采用记录的相关单位及分值记录信息
     * 
     * @param id 信息采用记录的相关单位及分值记录ID
     * @return 结果
     */
    public int deleteBizAdoptRecordScoreById(String id);

    /**
     * 导出信息采用情况的篇数和分数的统计情况word
     * @param req
     */
    public void exportStatisticWord(HttpServletResponse response, AdoptVo.StatisticReq req);

    /**
     * 导出信息采用情况的记录word
     * @param response
     * @param req
     */
    public void exportCarrierRecordWord(HttpServletResponse response, AdoptVo.StatisticReq req);

}
