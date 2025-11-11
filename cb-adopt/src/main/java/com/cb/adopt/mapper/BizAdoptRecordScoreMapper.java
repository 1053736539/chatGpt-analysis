package com.cb.adopt.mapper;

import java.util.Date;
import java.util.List;

import com.cb.adopt.vo.AdoptVo;
import org.apache.ibatis.annotations.Param;
import com.cb.adopt.domain.BizAdoptRecordScore;

/**
 * 信息采用记录的相关单位及分值记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface BizAdoptRecordScoreMapper 
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
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizAdoptRecordScore> entities);

    /**
     * 修改信息采用记录的相关单位及分值记录
     * 
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 结果
     */
    public int updateBizAdoptRecordScore(BizAdoptRecordScore bizAdoptRecordScore);

    /**
     * 删除信息采用记录的相关单位及分值记录
     * 
     * @param id 信息采用记录的相关单位及分值记录ID
     * @return 结果
     */
    public int deleteBizAdoptRecordScoreById(String id);

    /**
     * 批量删除信息采用记录的相关单位及分值记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAdoptRecordScoreByIds(String[] ids);

    /**
     * 根据deptType 和 时间范围进行统计
     * @param deptType
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<AdoptVo.StatisticItem> statisticByDeptTypeAndDateRange(@Param("deptType") Integer deptType,
                                                                       @Param("beginDate") Date beginDate,
                                                                       @Param("endDate") Date endDate);

    /**
     * 获取载体相关的采用条目记录
     * @param level
     * @param carrier
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<AdoptVo.ListCarrierRecordScoreItem> listCarrierRecordScore(@Param("level") String level,
                                                              @Param("carrier") String carrier,
                                                              @Param("beginDate") Date beginDate,
                                                              @Param("endDate") Date endDate);

    List<BizAdoptRecordScore> selectScoreListByRecordId(String recordId);

    public int deleteRecordScoreNotIds(@Param("recordId")String recordId,@Param("ids") List<String> ids);
}
