package com.cb.adopt.service.impl;

import java.util.List;
import java.util.Date;

import com.cb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.adopt.mapper.BizAdoptDeptMapper;
import com.cb.adopt.domain.BizAdoptDept;
import com.cb.adopt.service.IBizAdoptDeptService;

/**
 * 报送单位信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BizAdoptDeptServiceImpl implements IBizAdoptDeptService 
{
    @Autowired
    private BizAdoptDeptMapper bizAdoptDeptMapper;

    /**
     * 查询报送单位信息
     * 
     * @param id 报送单位信息ID
     * @return 报送单位信息
     */
    @Override
    public BizAdoptDept selectBizAdoptDeptById(Integer id)
    {
        return bizAdoptDeptMapper.selectBizAdoptDeptById(id);
    }

    /**
     * 查询报送单位信息列表
     * 
     * @param bizAdoptDept 报送单位信息
     * @return 报送单位信息
     */
    @Override
    public List<BizAdoptDept> selectBizAdoptDeptList(BizAdoptDept bizAdoptDept)
    {
        return bizAdoptDeptMapper.selectBizAdoptDeptList(bizAdoptDept);
    }

    /**
     * 新增报送单位信息
     * 
     * @param bizAdoptDept 报送单位信息
     * @return 结果
     */
    @Override
    public int insertBizAdoptDept(BizAdoptDept bizAdoptDept)
    {
//        if(StringUtils.isBlank(bizAdoptDept.getId())) {
//            bizAdoptDept.setId(IdUtils.randomUUID());
//        }
        return bizAdoptDeptMapper.insertBizAdoptDept(bizAdoptDept);
    }


    /**
     * 批量新增报送单位信息
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizAdoptDept> entities) {
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
            totalInserted +=  bizAdoptDeptMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改报送单位信息
     * 
     * @param bizAdoptDept 报送单位信息
     * @return 结果
     */
    @Override
    public int updateBizAdoptDept(BizAdoptDept bizAdoptDept)
    {
        return bizAdoptDeptMapper.updateBizAdoptDept(bizAdoptDept);
    }

    /**
     * 批量删除报送单位信息
     * 
     * @param ids 需要删除的报送单位信息ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptDeptByIds(Integer[] ids)
    {
        return bizAdoptDeptMapper.deleteBizAdoptDeptByIds(ids);
    }

    /**
     * 删除报送单位信息信息
     * 
     * @param id 报送单位信息ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptDeptById(Integer id)
    {
        return bizAdoptDeptMapper.deleteBizAdoptDeptById(id);
    }
}
