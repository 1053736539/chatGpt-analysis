package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentVillage;
import com.cb.mapper.TransferringStudentVillageMapper;
import com.cb.service.ITransferringStudentVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选调生锻炼培养-驻村Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
@Service
public class TransferringStudentVillageServiceImpl implements ITransferringStudentVillageService 
{
    @Autowired
    private TransferringStudentVillageMapper transferringStudentVillageMapper;

    /**
     * 查询选调生锻炼培养-驻村
     * 
     * @param id 选调生锻炼培养-驻村ID
     * @return 选调生锻炼培养-驻村
     */
    @Override
    public TransferringStudentVillage selectTransferringStudentVillageById(String id)
    {
        return transferringStudentVillageMapper.selectTransferringStudentVillageById(id);
    }

    /**
     * 查询选调生锻炼培养-驻村列表
     * 
     * @param transferringStudentVillage 选调生锻炼培养-驻村
     * @return 选调生锻炼培养-驻村
     */
    @Override
    public List<TransferringStudentVillage> selectTransferringStudentVillageList(TransferringStudentVillage transferringStudentVillage)
    {
        return transferringStudentVillageMapper.selectTransferringStudentVillageList(transferringStudentVillage);
    }

    /**
     * 新增选调生锻炼培养-驻村
     * 
     * @param transferringStudentVillage 选调生锻炼培养-驻村
     * @return 结果
     */
    @Override
    public int insertTransferringStudentVillage(TransferringStudentVillage transferringStudentVillage)
    {
        if (null == transferringStudentVillage.getUserId()){
            transferringStudentVillage.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        transferringStudentVillage.setId(UUID.randomUUID().toString());
        transferringStudentVillage.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentVillage.setCreateTime(DateUtils.getNowDate());
        transferringStudentVillage.setDelFlag("0");
        return transferringStudentVillageMapper.insertTransferringStudentVillage(transferringStudentVillage);
    }

    /**
     * 修改选调生锻炼培养-驻村
     * 
     * @param transferringStudentVillage 选调生锻炼培养-驻村
     * @return 结果
     */
    @Override
    public int updateTransferringStudentVillage(TransferringStudentVillage transferringStudentVillage)
    {
        transferringStudentVillage.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentVillage.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentVillageMapper.updateTransferringStudentVillage(transferringStudentVillage);
    }

    /**
     * 批量删除选调生锻炼培养-驻村
     * 
     * @param ids 需要删除的选调生锻炼培养-驻村ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentVillageByIds(String[] ids)
    {
        return transferringStudentVillageMapper.deleteTransferringStudentVillageByIds(ids);
    }

    /**
     * 删除选调生锻炼培养-驻村信息
     * 
     * @param id 选调生锻炼培养-驻村ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentVillageById(String id)
    {
        return transferringStudentVillageMapper.deleteTransferringStudentVillageById(id);
    }
}
