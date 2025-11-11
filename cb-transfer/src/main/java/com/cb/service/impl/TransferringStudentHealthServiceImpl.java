package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentHealth;
import com.cb.mapper.TransferringStudentHealthMapper;
import com.cb.service.ITransferringStudentHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 监督管理-身心健康情况Service业务层处理
 *
 * @author yangxin
 * @date 2024-08-27
 */
@Service
public class TransferringStudentHealthServiceImpl implements ITransferringStudentHealthService
{
    @Autowired
    private TransferringStudentHealthMapper transferringStudentHealthMapper;

    /**
     * 查询监督管理-身心健康情况
     *
     * @param id 监督管理-身心健康情况ID
     * @return 监督管理-身心健康情况
     */
    @Override
    public TransferringStudentHealth selectTransferringStudentHealthById(String id)
    {
        return transferringStudentHealthMapper.selectTransferringStudentHealthById(id);
    }

    /**
     * 查询监督管理-身心健康情况列表
     *
     * @param transferringStudentHealth 监督管理-身心健康情况
     * @return 监督管理-身心健康情况
     */
    @Override
    public List<TransferringStudentHealth> selectTransferringStudentHealthList(TransferringStudentHealth transferringStudentHealth)
    {
        return transferringStudentHealthMapper.selectTransferringStudentHealthList(transferringStudentHealth);
    }

    /**
     * 新增监督管理-身心健康情况
     *
     * @param transferringStudentHealth 监督管理-身心健康情况
     * @return 结果
     */
    @Override
    public int insertTransferringStudentHealth(TransferringStudentHealth transferringStudentHealth)
    {
        if (null == transferringStudentHealth.getUserId()){
            transferringStudentHealth.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        transferringStudentHealth.setId(UUID.randomUUID().toString());
        transferringStudentHealth.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentHealth.setCreateTime(DateUtils.getNowDate());
        transferringStudentHealth.setDelFlag("0");
        return transferringStudentHealthMapper.insertTransferringStudentHealth(transferringStudentHealth);
    }

    /**
     * 修改监督管理-身心健康情况
     *
     * @param transferringStudentHealth 监督管理-身心健康情况
     * @return 结果
     */
    @Override
    public int updateTransferringStudentHealth(TransferringStudentHealth transferringStudentHealth)
    {
        transferringStudentHealth.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentHealth.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentHealthMapper.updateTransferringStudentHealth(transferringStudentHealth);
    }

    /**
     * 批量删除监督管理-身心健康情况
     *
     * @param ids 需要删除的监督管理-身心健康情况ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentHealthByIds(String[] ids)
    {
        return transferringStudentHealthMapper.deleteTransferringStudentHealthByIds(ids);
    }

    /**
     * 删除监督管理-身心健康情况信息
     *
     * @param id 监督管理-身心健康情况ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentHealthById(String id)
    {
        return transferringStudentHealthMapper.deleteTransferringStudentHealthById(id);
    }
}