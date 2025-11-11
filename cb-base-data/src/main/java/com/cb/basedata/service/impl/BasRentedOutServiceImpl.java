package com.cb.basedata.service.impl;

import java.util.List;

import com.cb.basedata.domain.BasRentedOut;
import com.cb.basedata.mapper.BasRentedOutMapper;
import com.cb.basedata.service.IBasRentedOutService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码-昆明市主城区已配租数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-25
 */
@Service
public class BasRentedOutServiceImpl implements IBasRentedOutService
{
    @Autowired
    private BasRentedOutMapper basRentedOutMapper;

    /**
     * 查询代码-昆明市主城区已配租数据
     * 
     * @param id 代码-昆明市主城区已配租数据ID
     * @return 代码-昆明市主城区已配租数据
     */
    @Override
    public BasRentedOut selectBasRentedOutById(String id)
    {
        return basRentedOutMapper.selectBasRentedOutById(id);
    }
    public BasRentedOut selectBasNotRentedOutBySourceId(String sourceId)
    {
        return basRentedOutMapper.selectBasNotRentedOutBySourceId(sourceId);
    }

    /**
     * 查询代码-昆明市主城区已配租数据列表
     * 
     * @param basRentedOut 代码-昆明市主城区已配租数据
     * @return 代码-昆明市主城区已配租数据
     */
    @Override
    public List<BasRentedOut> selectBasRentedOutList(BasRentedOut basRentedOut)
    {
        return basRentedOutMapper.selectBasRentedOutList(basRentedOut);
    }

    /**
     * 新增代码-昆明市主城区已配租数据
     * 
     * @param basRentedOut 代码-昆明市主城区已配租数据
     * @return 结果
     */
    @Override
    public int insertBasRentedOut(BasRentedOut basRentedOut)
    {
        basRentedOut.setId(UUID.randomUUID().toString());
        basRentedOut.setImportTime(DateUtils.getNowDate());
        basRentedOut.setCreateTime(DateUtils.getNowDate());
        return basRentedOutMapper.insertBasRentedOut(basRentedOut);
    }

    /**
     * 修改代码-昆明市主城区已配租数据
     * 
     * @param basRentedOut 代码-昆明市主城区已配租数据
     * @return 结果
     */
    @Override
    public int updateBasRentedOut(BasRentedOut basRentedOut)
    {
        basRentedOut.setUpdateTime(DateUtils.getNowDate());
        return basRentedOutMapper.updateBasRentedOut(basRentedOut);
    }

    /**
     * 批量删除代码-昆明市主城区已配租数据
     * 
     * @param ids 需要删除的代码-昆明市主城区已配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasRentedOutByIds(String[] ids)
    {
        return basRentedOutMapper.deleteBasRentedOutByIds(ids);
    }

    /**
     * 删除代码-昆明市主城区已配租数据信息
     * 
     * @param id 代码-昆明市主城区已配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasRentedOutById(String id)
    {
        return basRentedOutMapper.deleteBasRentedOutById(id);
    }
}
