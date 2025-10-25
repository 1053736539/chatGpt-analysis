package com.cb.cadretrain.mapper;

import com.cb.cadretrain.domain.BizTrainRecords;
import com.cb.cadretrain.domain.BizTrainRecordsResult;
import com.cb.cadretrain.domain.TrainDurationResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 培训记录Mapper接口
 * 
 * @author yangcd
 * @date 2023-10-30
 */
public interface BizTrainRecordsMapper 
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
    public List<BizTrainRecords> selectBizTrainRecordsAllList1(BizTrainRecords bizTrainRecords);
    public List<BizTrainRecords> selectBizTrainRecordsAllList(BizTrainRecords bizTrainRecords);

    /**
     * 查询部门内的所有培训记录
     * @param bizTrainRecords
     * @return
     */
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

    /**
     * 删除培训记录
     * 
     * @param id 培训记录ID
     * @return 结果
     */
    public int deleteBizTrainRecordsById(String id);

    /**
     * 批量删除培训记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizTrainRecordsByIds(String[] ids);

    // 查询特定年份和季度的培训总时长
    List<TrainDurationResult> sumTrainDurationByYearAndQuarter1(
            @Param("trainYear") String trainYear,
            @Param("quarter") Integer quarter, // 季度是可选参数
            @Param("traineeName") String traineeName,
            @Param("approvalStatus") String approvalStatus
    );
    List<BizTrainRecordsResult> sumTrainDurationByYearAndQuarter(
            @Param("trainYear") String trainYear,
            @Param("biz") BizTrainRecords biz, // 季度是可选参数
            @Param("traineeName") String traineeName
    );
}
