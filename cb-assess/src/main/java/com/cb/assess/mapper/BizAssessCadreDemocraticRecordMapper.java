package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessCadreDemocraticRecord;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 年度处级领导干部民主测评Mapper接口
 * 
 * @author yangxin
 * @date 2023-11-25
 */
public interface BizAssessCadreDemocraticRecordMapper 
{
    /**
     * 查询年度处级领导干部民主测评
     * 
     * @param id 年度处级领导干部民主测评ID
     * @return 年度处级领导干部民主测评
     */
    public BizAssessCadreDemocraticRecord selectBizAssessCadreDemocraticRecordById(String id);

    /**
     * 查询年度处级领导干部民主测评列表
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 年度处级领导干部民主测评集合
     */
    public List<BizAssessCadreDemocraticRecord> selectBizAssessCadreDemocraticRecordList(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord);

    /**
     * 统计自己的待填
     * @param voteUserId
     * @return
     */
    public HashMap<String,Long> selectCountMyDemocratic(Long voteUserId);

    /**
     * 新增年度处级领导干部民主测评
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 结果
     */
    public int insertBizAssessCadreDemocraticRecord(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord);

    /**
     * 批量新增
     * @param recordList
     * @return
     */
    public int batchInsert(@Param("recordList") List<BizAssessCadreDemocraticRecord> recordList);

    /**
     * 修改年度处级领导干部民主测评
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 结果
     */
    public int updateBizAssessCadreDemocraticRecord(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord);

    /**
     * 删除年度处级领导干部民主测评
     * 
     * @param id 年度处级领导干部民主测评ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticRecordById(String id);

    /**
     * 批量删除年度处级领导干部民主测评
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticRecordByIds(String[] ids);

    /**
     * 根据taskId删除记录
     * @param taskIds
     * @return
     */
    public int deleteByTaskIds(String[] taskIds);

}
