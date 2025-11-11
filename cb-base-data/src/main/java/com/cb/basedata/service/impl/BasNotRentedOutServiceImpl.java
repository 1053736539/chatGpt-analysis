package com.cb.basedata.service.impl;

import java.util.List;

import com.cb.basedata.domain.BasNotRentedOut;
import com.cb.basedata.mapper.BasNotRentedOutMapper;
import com.cb.basedata.service.IBasNotRentedOutService;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码-昆明市主城区未配租数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-25
 */
@Service
public class BasNotRentedOutServiceImpl implements IBasNotRentedOutService
{
    @Autowired
    private BasNotRentedOutMapper basNotRentedOutMapper;

    /**
     * 查询代码-昆明市主城区未配租数据
     * 
     * @param id 代码-昆明市主城区未配租数据ID
     * @return 代码-昆明市主城区未配租数据
     */
    @Override
    public BasNotRentedOut selectBasNotRentedOutById(String id)
    {
        return basNotRentedOutMapper.selectBasNotRentedOutById(id);
    }
    @Override
    public BasNotRentedOut selectBasNotRentedOutBySourceId(String sourceId)
    {
        return basNotRentedOutMapper.selectBasNotRentedOutBySourceId(sourceId);
    }

    /**
     * 查询代码-昆明市主城区未配租数据列表
     * 
     * @param basNotRentedOut 代码-昆明市主城区未配租数据
     * @return 代码-昆明市主城区未配租数据
     */
    @Override
    public List<BasNotRentedOut> selectBasNotRentedOutList(BasNotRentedOut basNotRentedOut)
    {
        return basNotRentedOutMapper.selectBasNotRentedOutList(basNotRentedOut);
    }

    /**
     * 新增代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOut 代码-昆明市主城区未配租数据
     * @return 结果
     */
    @Override
    public int insertBasNotRentedOut(BasNotRentedOut basNotRentedOut)
    {
        basNotRentedOut.setId(UUID.randomUUID().toString());
        basNotRentedOut.setCreateTime(DateUtils.getNowDate());
        return basNotRentedOutMapper.insertBasNotRentedOut(basNotRentedOut);
    }

    /**
     * 修改代码-昆明市主城区未配租数据
     * 
     * @param basNotRentedOut 代码-昆明市主城区未配租数据
     * @return 结果
     */
    @Override
    public int updateBasNotRentedOut(BasNotRentedOut basNotRentedOut)
    {
        basNotRentedOut.setUpdateTime(DateUtils.getNowDate());
        return basNotRentedOutMapper.updateBasNotRentedOut(basNotRentedOut);
    }

    /**
     * 批量删除代码-昆明市主城区未配租数据
     * 
     * @param ids 需要删除的代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasNotRentedOutByIds(String[] ids)
    {
        return basNotRentedOutMapper.deleteBasNotRentedOutByIds(ids);
    }

    /**
     * 删除代码-昆明市主城区未配租数据信息
     * 
     * @param id 代码-昆明市主城区未配租数据ID
     * @return 结果
     */
    @Override
    public int deleteBasNotRentedOutById(String id)
    {
        return basNotRentedOutMapper.deleteBasNotRentedOutById(id);
    }
}
