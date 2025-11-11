package com.cb.assess.service;

import com.cb.assess.domain.BizAssessCadreDemocraticRecord;
import com.cb.assess.vo.CadreDemocraticRecordVo;

import java.util.List;

/**
 * 年度处级领导干部民主测评Service接口
 * 
 * @author yangxin
 * @date 2023-11-25
 */
public interface IBizAssessCadreDemocraticRecordService 
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
     * 新增年度处级领导干部民主测评
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 结果
     */
    public int insertBizAssessCadreDemocraticRecord(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord);

    /**
     * 批量插入
     * @param recordList
     * @return
     */
    public int batchInsert(List<BizAssessCadreDemocraticRecord> recordList);

    /**
     * 修改年度处级领导干部民主测评
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 结果
     */
    public int updateBizAssessCadreDemocraticRecord(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord);

    /**
     * 批量删除年度处级领导干部民主测评
     * 
     * @param ids 需要删除的年度处级领导干部民主测评ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticRecordByIds(String[] ids);


    /**
     * 批量删除年度处级领导干部民主测评
     *
     * @param taskIds 需要删除的年度处级领导干部民主测评ID
     * @return 结果
     */
    public int deleteByTaskIds(String[] taskIds);

    /**
     * 删除年度处级领导干部民主测评信息
     * 
     * @param id 年度处级领导干部民主测评ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticRecordById(String id);

    /**
     * 提交评议
     * @param req
     */
    public void submit(CadreDemocraticRecordVo.SubmitReq req);

}
