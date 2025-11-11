package com.cb.mapper;

import com.cb.domain.TransferringStudent;
import com.cb.vo.StudentVo;

import java.util.List;

/**
 * 选调生Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
public interface TransferringStudentMapper 
{
    /**
     * 查询选调生
     * 
     * @param userId 选调生ID
     * @return 选调生
     */
    public StudentVo selectTransferringStudentById(String userId);
    public TransferringStudent selectTransferringStudentByIdNoJoin(String userId);

    /**
     * 查询选调生列表
     * 
     * @param transferringStudent 选调生
     * @return 选调生集合
     */
    public List<StudentVo> selectTransferringStudentList(StudentVo studentVo);

    /**
     * 新增选调生
     * 
     * @param transferringStudent 选调生
     * @return 结果
     */
    public int insertTransferringStudent(TransferringStudent transferringStudent);

    /**
     * 修改选调生
     * 
     * @param transferringStudent 选调生
     * @return 结果
     */
    public int updateTransferringStudent(TransferringStudent transferringStudent);

    /**
     * 删除选调生
     * 
     * @param userId 选调生ID
     * @return 结果
     */
    public int deleteTransferringStudentById(String userId);

    /**
     * 批量删除选调生
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferringStudentByIds(String[] userIds);
}
