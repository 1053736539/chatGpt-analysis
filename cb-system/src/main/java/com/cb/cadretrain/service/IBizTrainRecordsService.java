package com.cb.cadretrain.service;

import com.cb.cadretrain.domain.BizTrainRecords;
import com.cb.cadretrain.domain.BizTrainRecordsResult;
import com.cb.cadretrain.domain.TrainDurationResult;

import java.util.List;

/**
 * 培训记录Service接口
 * 
 * @author yangcd
 * @date 2023-10-30
 */
public interface IBizTrainRecordsService 
{
    /**
     * 查询培训记录
     * 
     * @param id 培训记录ID
     * @return 培训记录
     */
    public BizTrainRecords selectBizTrainRecordsById(String id);

    /**
     * 查询培训记录列表
     * 
     * @param bizTrainRecords 培训记录
     * @return 培训记录集合
     */
    public List<BizTrainRecords> selectBizTrainRecordsList(BizTrainRecords bizTrainRecords);

    /*根据用户ID 查询个人培训记录 用户档案信息使用*/
    public List<BizTrainRecords> selectUserInfoBizTrainRecordsList(BizTrainRecords bizTrainRecords);

    List<BizTrainRecords> selectAllList(BizTrainRecords bizTrainRecords);

    public List<BizTrainRecords> selectBizTrainRecordsAllList(BizTrainRecords bizTrainRecords);

    public List<BizTrainRecords> selectBizTrainRecordsByPersonnelDept(BizTrainRecords bizTrainRecords);

    public List<BizTrainRecords> selectBizTrainRecordsByDept(BizTrainRecords bizTrainRecords);

    public List<BizTrainRecords> listByDeptMgr(BizTrainRecords bizTrainRecords);

    /**
     * 新增培训记录
     * 
     * @param bizTrainRecords 培训记录
     * @return 结果
     */
    public int insertBizTrainRecords(BizTrainRecords bizTrainRecords);

    /**
     * 修改培训记录
     * 
     * @param bizTrainRecords 培训记录
     * @return 结果
     */
    public int updateBizTrainRecords(BizTrainRecords bizTrainRecords);
    public int updateBizTrainRecordsByAudit(BizTrainRecords bizTrainRecords);

    /**
     * 批量删除培训记录
     * 
     * @param ids 需要删除的培训记录ID
     * @return 结果
     */
    public int deleteBizTrainRecordsByIds(String[] ids);

    /**
     * 删除培训记录信息
     * 
     * @param id 培训记录ID
     * @return 结果
     */
    public int deleteBizTrainRecordsById(String id);

    // 查询特定年份和季度的培训总时长
    List<TrainDurationResult> getTrainDurationByYearAndQuarter1(
            String trainYear,
            Integer quarter, // 季度是可选参数
            String traineeName,
            String approvalStatus
    );
    List<BizTrainRecordsResult> getTrainDurationByYearAndQuarter(
            String trainYear,
            BizTrainRecords bizTrainRecords, // 季度是可选参数
            String traineeName
    );
}
