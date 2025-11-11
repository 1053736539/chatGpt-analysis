package com.cb.service;

import com.cb.domain.TransferringStudentSupervise;
import com.cb.vo.StudentSuperviseVo;

import java.util.List;

/**
 * 选调生教育监督Service接口
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public interface ITransferringStudentSuperviseService 
{
    /**
     * 查询选调生教育监督
     * 
     * @param id 选调生教育监督ID
     * @return 选调生教育监督
     */
    public TransferringStudentSupervise selectTransferringStudentSuperviseById(String id);

    /**
     * 查询选调生教育监督列表
     * 
     * @param transferringStudentSupervise 选调生教育监督
     * @return 选调生教育监督集合
     */
    public List<StudentSuperviseVo> selectTransferringStudentSuperviseList(StudentSuperviseVo studentSuperviseVo);

    /**
     * 新增选调生教育监督
     * 
     * @param transferringStudentSupervise 选调生教育监督
     * @return 结果
     */
    public int insertTransferringStudentSupervise(TransferringStudentSupervise transferringStudentSupervise);

    /**
     * 修改选调生教育监督
     * 
     * @param transferringStudentSupervise 选调生教育监督
     * @return 结果
     */
    public int updateTransferringStudentSupervise(TransferringStudentSupervise transferringStudentSupervise);

    /**
     * 批量删除选调生教育监督
     * 
     * @param ids 需要删除的选调生教育监督ID
     * @return 结果
     */
    public int deleteTransferringStudentSuperviseByIds(String[] ids);

    /**
     * 删除选调生教育监督信息
     * 
     * @param id 选调生教育监督ID
     * @return 结果
     */
    public int deleteTransferringStudentSuperviseById(String id);
}
