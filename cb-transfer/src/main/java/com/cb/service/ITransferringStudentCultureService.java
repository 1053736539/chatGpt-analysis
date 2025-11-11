package com.cb.service;

import com.cb.domain.TransferringStudentCulture;
import com.cb.vo.StudentCultureVo;

import java.util.List;

/**
 * 选调生培养Service接口
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public interface ITransferringStudentCultureService 
{
    /**
     * 查询选调生培养
     * 
     * @param id 选调生培养ID
     * @return 选调生培养
     */
    public TransferringStudentCulture selectTransferringStudentCultureById(String id);

    /**
     * 查询选调生培养列表
     * 
     * @param transferringStudentCulture 选调生培养
     * @return 选调生培养集合
     */
    public List<StudentCultureVo> selectTransferringStudentCultureList(StudentCultureVo studentCultureVo);

    /**
     * 新增选调生培养
     * 
     * @param transferringStudentCulture 选调生培养
     * @return 结果
     */
    public int insertTransferringStudentCulture(TransferringStudentCulture transferringStudentCulture);

    /**
     * 修改选调生培养
     * 
     * @param transferringStudentCulture 选调生培养
     * @return 结果
     */
    public int updateTransferringStudentCulture(TransferringStudentCulture transferringStudentCulture);

    /**
     * 批量删除选调生培养
     * 
     * @param ids 需要删除的选调生培养ID
     * @return 结果
     */
    public int deleteTransferringStudentCultureByIds(String[] ids);

    /**
     * 删除选调生培养信息
     * 
     * @param id 选调生培养ID
     * @return 结果
     */
    public int deleteTransferringStudentCultureById(String id);
}
