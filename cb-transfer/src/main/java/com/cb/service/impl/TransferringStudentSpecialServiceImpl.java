package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentSpecial;
import com.cb.mapper.TransferringStudentSpecialMapper;
import com.cb.service.ITransferringStudentSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选调生锻炼培养-参与专项任务Service业务层处理
 * 
 * @author yangxin
 * @date 2024-08-28
 */
@Service
public class TransferringStudentSpecialServiceImpl implements ITransferringStudentSpecialService 
{
    @Autowired
    private TransferringStudentSpecialMapper transferringStudentSpecialMapper;

    /**
     * 查询选调生锻炼培养-参与专项任务
     * 
     * @param id 选调生锻炼培养-参与专项任务ID
     * @return 选调生锻炼培养-参与专项任务
     */
    @Override
    public TransferringStudentSpecial selectTransferringStudentSpecialById(String id)
    {
        return transferringStudentSpecialMapper.selectTransferringStudentSpecialById(id);
    }

    /**
     * 查询选调生锻炼培养-参与专项任务列表
     * 
     * @param transferringStudentSpecial 选调生锻炼培养-参与专项任务
     * @return 选调生锻炼培养-参与专项任务
     */
    @Override
    public List<TransferringStudentSpecial> selectTransferringStudentSpecialList(TransferringStudentSpecial transferringStudentSpecial)
    {
        return transferringStudentSpecialMapper.selectTransferringStudentSpecialList(transferringStudentSpecial);
    }

    /**
     * 新增选调生锻炼培养-参与专项任务
     * 
     * @param transferringStudentSpecial 选调生锻炼培养-参与专项任务
     * @return 结果
     */
    @Override
    public int insertTransferringStudentSpecial(TransferringStudentSpecial transferringStudentSpecial)
    {
        if (null == transferringStudentSpecial.getUserId()){
            transferringStudentSpecial.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        transferringStudentSpecial.setId(UUID.randomUUID().toString());
        transferringStudentSpecial.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentSpecial.setCreateTime(DateUtils.getNowDate());
        transferringStudentSpecial.setDelFlag("0");
        return transferringStudentSpecialMapper.insertTransferringStudentSpecial(transferringStudentSpecial);
    }

    /**
     * 修改选调生锻炼培养-参与专项任务
     * 
     * @param transferringStudentSpecial 选调生锻炼培养-参与专项任务
     * @return 结果
     */
    @Override
    public int updateTransferringStudentSpecial(TransferringStudentSpecial transferringStudentSpecial)
    {
        transferringStudentSpecial.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentSpecial.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentSpecialMapper.updateTransferringStudentSpecial(transferringStudentSpecial);
    }

    /**
     * 批量删除选调生锻炼培养-参与专项任务
     * 
     * @param ids 需要删除的选调生锻炼培养-参与专项任务ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentSpecialByIds(String[] ids)
    {
        return transferringStudentSpecialMapper.deleteTransferringStudentSpecialByIds(ids);
    }

    /**
     * 删除选调生锻炼培养-参与专项任务信息
     * 
     * @param id 选调生锻炼培养-参与专项任务ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentSpecialById(String id)
    {
        return transferringStudentSpecialMapper.deleteTransferringStudentSpecialById(id);
    }
}
