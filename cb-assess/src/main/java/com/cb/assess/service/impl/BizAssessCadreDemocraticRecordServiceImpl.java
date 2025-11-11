package com.cb.assess.service.impl;

import com.cb.assess.domain.BizAssessCadreDemocraticRecord;
import com.cb.assess.mapper.BizAssessCadreDemocraticRecordMapper;
import com.cb.assess.service.IBizAssessCadreDemocraticRecordService;
import com.cb.assess.vo.CadreDemocraticRecordVo;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 年度处级领导干部民主测评Service业务层处理
 * 
 * @author yangxin
 * @date 2023-11-25
 */
@Service
public class BizAssessCadreDemocraticRecordServiceImpl implements IBizAssessCadreDemocraticRecordService 
{
    @Autowired
    private BizAssessCadreDemocraticRecordMapper bizAssessCadreDemocraticRecordMapper;

    /**
     * 查询年度处级领导干部民主测评
     * 
     * @param id 年度处级领导干部民主测评ID
     * @return 年度处级领导干部民主测评
     */
    @Override
    public BizAssessCadreDemocraticRecord selectBizAssessCadreDemocraticRecordById(String id)
    {
        return bizAssessCadreDemocraticRecordMapper.selectBizAssessCadreDemocraticRecordById(id);
    }

    /**
     * 查询年度处级领导干部民主测评列表
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 年度处级领导干部民主测评
     */
    @Override
    public List<BizAssessCadreDemocraticRecord> selectBizAssessCadreDemocraticRecordList(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        return bizAssessCadreDemocraticRecordMapper.selectBizAssessCadreDemocraticRecordList(bizAssessCadreDemocraticRecord);
    }

    /**
     * 新增年度处级领导干部民主测评
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 结果
     */
    @Override
    public int insertBizAssessCadreDemocraticRecord(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        if(StringUtils.isBlank(bizAssessCadreDemocraticRecord.getId())){
            bizAssessCadreDemocraticRecord.setId(IdUtils.randomUUID());
        }
        try{
            bizAssessCadreDemocraticRecord.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizAssessCadreDemocraticRecord.setCreateTime(DateUtils.getNowDate());
        return bizAssessCadreDemocraticRecordMapper.insertBizAssessCadreDemocraticRecord(bizAssessCadreDemocraticRecord);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int batchInsert(List<BizAssessCadreDemocraticRecord> recordList) {
        if(null == recordList || recordList.isEmpty()){
            return 0;
        }
        recordList.forEach(item->{
            if(StringUtils.isBlank(item.getId())){
                item.setId(IdUtils.randomUUID());
            }
            try{
                item.setCreateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
            item.setCreateTime(DateUtils.getNowDate());
        });
        return bizAssessCadreDemocraticRecordMapper.batchInsert(recordList);
    }

    /**
     * 修改年度处级领导干部民主测评
     * 
     * @param bizAssessCadreDemocraticRecord 年度处级领导干部民主测评
     * @return 结果
     */
    @Override
    public int updateBizAssessCadreDemocraticRecord(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        try{
            bizAssessCadreDemocraticRecord.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizAssessCadreDemocraticRecord.setUpdateTime(DateUtils.getNowDate());
        return bizAssessCadreDemocraticRecordMapper.updateBizAssessCadreDemocraticRecord(bizAssessCadreDemocraticRecord);
    }

    /**
     * 批量删除年度处级领导干部民主测评
     * 
     * @param ids 需要删除的年度处级领导干部民主测评ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadreDemocraticRecordByIds(String[] ids)
    {
        return bizAssessCadreDemocraticRecordMapper.deleteBizAssessCadreDemocraticRecordByIds(ids);
    }

    @Override
    public int deleteByTaskIds(String[] taskIds) {
        return bizAssessCadreDemocraticRecordMapper.deleteByTaskIds(taskIds);
    }

    /**
     * 删除年度处级领导干部民主测评信息
     * 
     * @param id 年度处级领导干部民主测评ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadreDemocraticRecordById(String id)
    {
        return bizAssessCadreDemocraticRecordMapper.deleteBizAssessCadreDemocraticRecordById(id);
    }

    @Override
    public void submit(CadreDemocraticRecordVo.SubmitReq req) {
        BizAssessCadreDemocraticRecord record = bizAssessCadreDemocraticRecordMapper.selectBizAssessCadreDemocraticRecordById(req.getId());
        record.setStatus("1");
        record.setOverallEvaluation(req.getOverallEvaluation());
        record.setDeEvaluation(req.getDeEvaluation());
        record.setNengEvaluation(req.getNengEvaluation());
        record.setQinEvaluation(req.getQinEvaluation());
        record.setJiEvaluation(req.getJiEvaluation());
        record.setLianEvaluation(req.getLianEvaluation());
        record.setOpinion(req.getOpinion());
        bizAssessCadreDemocraticRecordMapper.updateBizAssessCadreDemocraticRecord(record);
    }
}
