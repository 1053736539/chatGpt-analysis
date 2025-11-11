package com.cb.service;

import com.cb.domain.TransferringStudentAppoint;
import com.cb.vo.StudentAppointVo;

import java.util.List;


/**
 * 选调生选拔任用Service接口
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public interface ITransferringStudentAppointService 
{
    /**
     * 查询选调生选拔任用
     * 
     * @param id 选调生选拔任用ID
     * @return 选调生选拔任用
     */
    public TransferringStudentAppoint selectTransferringStudentAppointById(String id);

    /**
     * 查询选调生选拔任用列表
     * 
     * @param transferringStudentAppoint 选调生选拔任用
     * @return 选调生选拔任用集合
     */
    public List<StudentAppointVo> selectTransferringStudentAppointList(StudentAppointVo studentAppointVo);

    /**
     * 新增选调生选拔任用
     * 
     * @param transferringStudentAppoint 选调生选拔任用
     * @return 结果
     */
    public int insertTransferringStudentAppoint(TransferringStudentAppoint transferringStudentAppoint);

    /**
     * 修改选调生选拔任用
     * 
     * @param transferringStudentAppoint 选调生选拔任用
     * @return 结果
     */
    public int updateTransferringStudentAppoint(TransferringStudentAppoint transferringStudentAppoint);

    /**
     * 批量删除选调生选拔任用
     * 
     * @param ids 需要删除的选调生选拔任用ID
     * @return 结果
     */
    public int deleteTransferringStudentAppointByIds(String[] ids);

    /**
     * 删除选调生选拔任用信息
     * 
     * @param id 选调生选拔任用ID
     * @return 结果
     */
    public int deleteTransferringStudentAppointById(String id);
}
