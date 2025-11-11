package com.cb.adopt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cb.adopt.domain.BizAdoptRecord;

/**
 * 信息采用记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface BizAdoptRecordMapper 
{
    /**
     * 查询信息采用记录
     * 
     * @param id 信息采用记录ID
     * @return 信息采用记录
     */
    public BizAdoptRecord selectBizAdoptRecordById(String id);

    /**
     * 查询信息采用记录列表
     * 
     * @param bizAdoptRecord 信息采用记录
     * @return 信息采用记录集合
     */
    public List<BizAdoptRecord> selectBizAdoptRecordList(BizAdoptRecord bizAdoptRecord);

    /**
     * 新增信息采用记录
     * 
     * @param bizAdoptRecord 信息采用记录
     * @return 结果
     */
    public int insertBizAdoptRecord(BizAdoptRecord bizAdoptRecord);


    /**
     * 批量新增信息采用记录
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizAdoptRecord> entities);

    /**
     * 修改信息采用记录
     * 
     * @param bizAdoptRecord 信息采用记录
     * @return 结果
     */
    public int updateBizAdoptRecord(BizAdoptRecord bizAdoptRecord);

    /**
     * 删除信息采用记录
     * 
     * @param id 信息采用记录ID
     * @return 结果
     */
    public int deleteBizAdoptRecordById(String id);

    /**
     * 批量删除信息采用记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAdoptRecordByIds(String[] ids);
}
