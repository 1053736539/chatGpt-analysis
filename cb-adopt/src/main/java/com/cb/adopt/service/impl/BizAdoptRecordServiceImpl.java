package com.cb.adopt.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.cb.adopt.domain.BizAdoptRecordScore;
import com.cb.adopt.mapper.BizAdoptRecordScoreMapper;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.adopt.mapper.BizAdoptRecordMapper;
import com.cb.adopt.domain.BizAdoptRecord;
import com.cb.adopt.service.IBizAdoptRecordService;

/**
 * 信息采用记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BizAdoptRecordServiceImpl implements IBizAdoptRecordService 
{
    @Autowired
    private BizAdoptRecordMapper bizAdoptRecordMapper;
    @Autowired
    private BizAdoptRecordScoreMapper bizAdoptRecordScoreMapper;

    /**
     * 查询信息采用记录
     * 
     * @param id 信息采用记录ID
     * @return 信息采用记录
     */
    @Override
    public BizAdoptRecord selectBizAdoptRecordById(String id)
    {
        BizAdoptRecord bizAdoptRecord =bizAdoptRecordMapper.selectBizAdoptRecordById(id);
        List<BizAdoptRecordScore> scoreList = bizAdoptRecordScoreMapper.selectScoreListByRecordId(id);
        bizAdoptRecord.setBizAdoptRecordScoreList(scoreList);
        return bizAdoptRecord;
    }

    /**
     * 查询信息采用记录列表
     * 
     * @param bizAdoptRecord 信息采用记录
     * @return 信息采用记录
     */
    @Override
    public List<BizAdoptRecord> selectBizAdoptRecordList(BizAdoptRecord bizAdoptRecord)
    {
        return bizAdoptRecordMapper.selectBizAdoptRecordList(bizAdoptRecord);
    }

    /**
     * 新增信息采用记录
     * 
     * @param bizAdoptRecord 信息采用记录
     * @return 结果
     */
    @Override
    public int insertBizAdoptRecord(BizAdoptRecord bizAdoptRecord)
    {
        if(StringUtils.isBlank(bizAdoptRecord.getId())) {
            bizAdoptRecord.setId(IdUtils.randomUUID());
        }
        try{
            bizAdoptRecord.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizAdoptRecord.setCreateTime(DateUtils.getNowDate());
        if(bizAdoptRecord.getBizAdoptRecordScoreList().size()>0){
            List<BizAdoptRecordScore> bizAdoptRecordScoreList = bizAdoptRecord.getBizAdoptRecordScoreList().stream()
                    .map(adoptRecordScore -> {
                        BizAdoptRecordScore recordScore = new BizAdoptRecordScore();
                        if(StringUtils.isBlank(recordScore.getId())) {
                            recordScore.setId(IdUtils.randomUUID());
                        }
                        recordScore.setRecordId(bizAdoptRecord.getId());
                        recordScore.setDept(adoptRecordScore.getDept());;
                        recordScore.setMainFlag(adoptRecordScore.getMainFlag());
                        recordScore.setScore(adoptRecordScore.getScore());
                        return recordScore;
                    })
                    .collect(Collectors.toList());
             bizAdoptRecordScoreMapper.insertBatch(bizAdoptRecordScoreList);
        }
     return bizAdoptRecordMapper.insertBizAdoptRecord(bizAdoptRecord);
    }


    /**
     * 批量新增信息采用记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizAdoptRecord> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted +=  bizAdoptRecordMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }

    /**
     * 批量新增信息采用记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatchByExcel(List<BizAdoptRecord> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
//            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted +=  bizAdoptRecordMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改信息采用记录
     * 
     * @param bizAdoptRecord 信息采用记录
     * @return 结果
     */
    @Override
    public int updateBizAdoptRecord(BizAdoptRecord bizAdoptRecord)
    {
        try{
            bizAdoptRecord.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizAdoptRecord.setUpdateTime(DateUtils.getNowDate());
        List<String> ids= new ArrayList<>();
        if(bizAdoptRecord.getBizAdoptRecordScoreList().size()>0){
            for (BizAdoptRecordScore adoptRecordScore : bizAdoptRecord.getBizAdoptRecordScoreList()) {
                if(StringUtils.isBlank(adoptRecordScore.getId())){
                    //子表id为空，新增数据
                    adoptRecordScore.setId(IdUtils.randomUUID());
                    adoptRecordScore.setRecordId(bizAdoptRecord.getId());
                    adoptRecordScore.setDept(adoptRecordScore.getDept());;
                    adoptRecordScore.setMainFlag(adoptRecordScore.getMainFlag());
                    adoptRecordScore.setScore(adoptRecordScore.getScore());
                    bizAdoptRecordScoreMapper.insertBizAdoptRecordScore(adoptRecordScore);
                    ids.add(adoptRecordScore.getId());
                }else {
                    //修改子表数据
                    adoptRecordScore.setId(adoptRecordScore.getId());
                    adoptRecordScore.setDept(adoptRecordScore.getDept());;
                    adoptRecordScore.setMainFlag(adoptRecordScore.getMainFlag());
                    adoptRecordScore.setScore(adoptRecordScore.getScore());
                    adoptRecordScore.setRecordId(bizAdoptRecord.getId());
                    bizAdoptRecordScoreMapper.updateBizAdoptRecordScore(adoptRecordScore);
                    ids.add(adoptRecordScore.getId());
                }
            }
            if(ids.size()>0){
                HashMap<String,Object> parms=new HashMap<String,Object>();
                parms.put("ids", ids);
                //删除在修改页面上删除的子表中的数据
                bizAdoptRecordScoreMapper.deleteRecordScoreNotIds(bizAdoptRecord.getId(),ids);
            }
        }
        return bizAdoptRecordMapper.updateBizAdoptRecord(bizAdoptRecord);
    }

    /**
     * 批量删除信息采用记录
     * 
     * @param ids 需要删除的信息采用记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptRecordByIds(String[] ids)
    {
        return bizAdoptRecordMapper.deleteBizAdoptRecordByIds(ids);
    }

    /**
     * 删除信息采用记录信息
     * 
     * @param id 信息采用记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptRecordById(String id)
    {
        return bizAdoptRecordMapper.deleteBizAdoptRecordById(id);
    }
}
