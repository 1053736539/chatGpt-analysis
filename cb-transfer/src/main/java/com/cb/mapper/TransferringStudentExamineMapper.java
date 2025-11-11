package com.cb.mapper;

import com.cb.domain.TransferringStudentExamine;
import com.cb.vo.StudentExamineVo;

import java.util.List;


/**
 * 选调生考核评价Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public interface TransferringStudentExamineMapper 
{
    /**
     * 查询选调生考核评价
     * 
     * @param id 选调生考核评价ID
     * @return 选调生考核评价
     */
    public TransferringStudentExamine selectTransferringStudentExamineById(String id);

    /**
     * 查询选调生考核评价列表
     * 
     * @param transferringStudentExamine 选调生考核评价
     * @return 选调生考核评价集合
     */
    public List<StudentExamineVo> selectTransferringStudentExamineList(StudentExamineVo studentExamineVo);

    /**
     * 新增选调生考核评价
     * 
     * @param transferringStudentExamine 选调生考核评价
     * @return 结果
     */
    public int insertTransferringStudentExamine(TransferringStudentExamine transferringStudentExamine);

    /**
     * 修改选调生考核评价
     * 
     * @param transferringStudentExamine 选调生考核评价
     * @return 结果
     */
    public int updateTransferringStudentExamine(TransferringStudentExamine transferringStudentExamine);

    /**
     * 删除选调生考核评价
     * 
     * @param id 选调生考核评价ID
     * @return 结果
     */
    public int deleteTransferringStudentExamineById(String id);

    /**
     * 批量删除选调生考核评价
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferringStudentExamineByIds(String[] ids);
}
