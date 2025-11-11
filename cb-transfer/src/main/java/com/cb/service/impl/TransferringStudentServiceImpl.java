package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.domain.TransferringStudent;
import com.cb.mapper.TransferringStudentMapper;
import com.cb.service.ITransferringStudentService;
import com.cb.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 选调生Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
@Service
public class TransferringStudentServiceImpl implements ITransferringStudentService
{
    @Autowired
    private TransferringStudentMapper transferringStudentMapper;

    /**
     * 查询选调生
     * 
     * @param userId 选调生ID
     * @return 选调生
     */
    @Override
    public TransferringStudent selectTransferringStudentById(String userId)
    {
        return transferringStudentMapper.selectTransferringStudentById(userId);
    }

    /**
     * 查询选调生列表
     * 
     * @param transferringStudent 选调生
     * @return 选调生
     */
    @Override
    public List<StudentVo> selectTransferringStudentList(StudentVo studentVo)
    {
        return transferringStudentMapper.selectTransferringStudentList(studentVo);
    }

    /**
     * 新增选调生
     * 
     * @param transferringStudent 选调生
     * @return 结果
     */
    @Override
    public int insertTransferringStudent(TransferringStudent transferringStudent)
    {
        transferringStudent.setCreateTime(DateUtils.getNowDate());
        transferringStudent.setDelFlag("0");
        return transferringStudentMapper.insertTransferringStudent(transferringStudent);
    }

    /**
     * 修改选调生
     * 
     * @param transferringStudent 选调生
     * @return 结果
     */
    @Override
    public int updateTransferringStudent(TransferringStudent transferringStudent)
    {
//        transferringStudent.setId(UUID.randomUUID().toString());
        if (StringUtils.isBlank(transferringStudent.getUserId())){
            transferringStudent.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        TransferringStudent transferringStudent1 = transferringStudentMapper.selectTransferringStudentByIdNoJoin(transferringStudent.getUserId());
        if (null==transferringStudent1){
            transferringStudent.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
            transferringStudent.setCreateTime(new Date());
            transferringStudent.setUpdateTime(DateUtils.getNowDate());
            return this.insertTransferringStudent(transferringStudent);
        }else {
            transferringStudent.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
            transferringStudent.setUpdateTime(DateUtils.getNowDate());
            return transferringStudentMapper.updateTransferringStudent(transferringStudent);
        }

    }

    /**
     * 批量删除选调生
     * 
     * @param userIds 需要删除的选调生ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentByIds(String[] userIds)
    {
        return transferringStudentMapper.deleteTransferringStudentByIds(userIds);
    }

    /**
     * 删除选调生信息
     * 
     * @param userId 选调生ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentById(String userId)
    {
        return transferringStudentMapper.deleteTransferringStudentById(userId);
    }
}
