package com.cb.probity.service.impl;

import java.util.List;
import java.util.Date;
import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.mapper.BizProbityLectureMapper;
import com.cb.probity.domain.BizProbityLecture;
import com.cb.probity.service.IBizProbityLectureService;

/**
 * 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-18
 */
@Service
public class BizProbityLectureServiceImpl implements IBizProbityLectureService 
{
    @Autowired
    private BizProbityLectureMapper bizProbityLectureMapper;

    /**
     * 查询廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * 
     * @param id 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得ID
     * @return 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     */
    @Override
    public BizProbityLecture selectBizProbityLectureById(String id)
    {
        return bizProbityLectureMapper.selectBizProbityLectureById(id);
    }

    /**
     * 查询廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得列表
     * 
     * @param bizProbityLecture 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * @return 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     */
    @Override
    public List<BizProbityLecture> selectBizProbityLectureList(BizProbityLecture bizProbityLecture)
    {
        return bizProbityLectureMapper.selectBizProbityLectureList(bizProbityLecture);
    }

    /**
     * 新增廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * 
     * @param bizProbityLecture 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * @return 结果
     */
    @Override
    public int insertBizProbityLecture(BizProbityLecture bizProbityLecture)
    {
        if(StringUtils.isBlank(bizProbityLecture.getId())) {
            bizProbityLecture.setId(IdUtils.randomUUID());
        }
        try{
            bizProbityLecture.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityLecture.setCreateTime(DateUtils.getNowDate());
        return bizProbityLectureMapper.insertBizProbityLecture(bizProbityLecture);
    }


    /**
     * 批量新增廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbityLecture> entities) {
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
            totalInserted +=  bizProbityLectureMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * 
     * @param bizProbityLecture 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * @return 结果
     */
    @Override
    public int updateBizProbityLecture(BizProbityLecture bizProbityLecture)
    {
        try{
            bizProbityLecture.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizProbityLecture.setUpdateTime(DateUtils.getNowDate());
        return bizProbityLectureMapper.updateBizProbityLecture(bizProbityLecture);
    }

    /**
     * 批量删除廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * 
     * @param ids 需要删除的廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityLectureByIds(String[] ids)
    {
        return bizProbityLectureMapper.deleteBizProbityLectureByIds(ids);
    }

    /**
     * 删除廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得信息
     * 
     * @param id 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityLectureById(String id)
    {
        return bizProbityLectureMapper.deleteBizProbityLectureById(id);
    }
}
