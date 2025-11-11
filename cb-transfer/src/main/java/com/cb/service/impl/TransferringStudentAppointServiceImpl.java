package com.cb.service.impl;

import java.util.List;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentAppoint;
import com.cb.mapper.TransferringStudentAppointMapper;
import com.cb.service.ITransferringStudentAppointService;
import com.cb.vo.StudentAppointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 选调生选拔任用Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@Service
public class TransferringStudentAppointServiceImpl implements ITransferringStudentAppointService
{
    @Autowired
    private TransferringStudentAppointMapper transferringStudentAppointMapper;

    /**
     * 查询选调生选拔任用
     * 
     * @param id 选调生选拔任用ID
     * @return 选调生选拔任用
     */
    @Override
    public TransferringStudentAppoint selectTransferringStudentAppointById(String id)
    {
        return transferringStudentAppointMapper.selectTransferringStudentAppointById(id);
    }

    /**
     * 查询选调生选拔任用列表
     * 
     * @param studentAppointVo 选调生选拔任用
     * @return 选调生选拔任用
     */
    @Override
    public List<StudentAppointVo> selectTransferringStudentAppointList(StudentAppointVo studentAppointVo)
    {
        studentAppointVo.setDelFlag("0");
        return transferringStudentAppointMapper.selectTransferringStudentAppointList(studentAppointVo);
    }

    /**
     * 新增选调生选拔任用
     * 
     * @param transferringStudentAppoint 选调生选拔任用
     * @return 结果
     */
    @Override
    public int insertTransferringStudentAppoint(TransferringStudentAppoint transferringStudentAppoint)
    {
        transferringStudentAppoint.setId(UUID.randomUUID().toString());
        transferringStudentAppoint.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        if (StringUtils.isBlank(transferringStudentAppoint.getUserId())){
            transferringStudentAppoint.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        transferringStudentAppoint.setDelFlag("0");
        transferringStudentAppoint.setCreateTime(DateUtils.getNowDate());
        return transferringStudentAppointMapper.insertTransferringStudentAppoint(transferringStudentAppoint);
    }

    /**
     * 修改选调生选拔任用
     * 
     * @param transferringStudentAppoint 选调生选拔任用
     * @return 结果
     */
    @Override
    public int updateTransferringStudentAppoint(TransferringStudentAppoint transferringStudentAppoint)
    {
        transferringStudentAppoint.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentAppoint.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentAppointMapper.updateTransferringStudentAppoint(transferringStudentAppoint);
    }

    /**
     * 批量删除选调生选拔任用
     * 
     * @param ids 需要删除的选调生选拔任用ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentAppointByIds(String[] ids)
    {
        return transferringStudentAppointMapper.deleteTransferringStudentAppointByIds(ids);
    }

    /**
     * 删除选调生选拔任用信息
     * 
     * @param id 选调生选拔任用ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentAppointById(String id)
    {
        return transferringStudentAppointMapper.deleteTransferringStudentAppointById(id);
    }
}
