package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.domain.TransferringStudentSupervise;
import com.cb.mapper.TransferringStudentSuperviseMapper;
import com.cb.service.ITransferringStudentSuperviseService;
import com.cb.vo.StudentSuperviseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 选调生教育监督Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@Service
public class TransferringStudentSuperviseServiceImpl implements ITransferringStudentSuperviseService
{
    @Autowired
    private TransferringStudentSuperviseMapper transferringStudentSuperviseMapper;

    /**
     * 查询选调生教育监督
     * 
     * @param id 选调生教育监督ID
     * @return 选调生教育监督
     */
    @Override
    public TransferringStudentSupervise selectTransferringStudentSuperviseById(String id)
    {
        return transferringStudentSuperviseMapper.selectTransferringStudentSuperviseById(id);
    }

    /**
     * 查询选调生教育监督列表
     * 
     * @param transferringStudentSupervise 选调生教育监督
     * @return 选调生教育监督
     */
    @Override
    public List<StudentSuperviseVo> selectTransferringStudentSuperviseList(StudentSuperviseVo studentSuperviseVo)
    {
        studentSuperviseVo.setDelFlag("0");
        return transferringStudentSuperviseMapper.selectTransferringStudentSuperviseList(studentSuperviseVo);
    }

    /**
     * 新增选调生教育监督
     * 
     * @param transferringStudentSupervise 选调生教育监督
     * @return 结果
     */
    @Override
    public int insertTransferringStudentSupervise(TransferringStudentSupervise transferringStudentSupervise)
    {
        transferringStudentSupervise.setId(UUID.randomUUID().toString());
        if (StringUtils.isBlank(transferringStudentSupervise.getUserId())){
            transferringStudentSupervise.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        transferringStudentSupervise.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentSupervise.setCreateTime(new Date());
        transferringStudentSupervise.setDelFlag("0");
        return transferringStudentSuperviseMapper.insertTransferringStudentSupervise(transferringStudentSupervise);
    }

    /**
     * 修改选调生教育监督
     * 
     * @param transferringStudentSupervise 选调生教育监督
     * @return 结果
     */
    @Override
    public int updateTransferringStudentSupervise(TransferringStudentSupervise transferringStudentSupervise)
    {
        transferringStudentSupervise.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentSupervise.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentSuperviseMapper.updateTransferringStudentSupervise(transferringStudentSupervise);
    }

    /**
     * 批量删除选调生教育监督
     * 
     * @param ids 需要删除的选调生教育监督ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentSuperviseByIds(String[] ids)
    {
        return transferringStudentSuperviseMapper.deleteTransferringStudentSuperviseByIds(ids);
    }

    /**
     * 删除选调生教育监督信息
     * 
     * @param id 选调生教育监督ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentSuperviseById(String id)
    {
        return transferringStudentSuperviseMapper.deleteTransferringStudentSuperviseById(id);
    }
}
