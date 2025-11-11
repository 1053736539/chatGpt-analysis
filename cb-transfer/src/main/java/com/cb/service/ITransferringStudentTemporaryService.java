package com.cb.service;

import com.cb.domain.TransferringStudentTemporary;

import java.util.List;
import java.util.Map;

/**
 * 选调生锻炼培养-挂职Service接口
 * 
 * @author yangxin
 * @date 2024-08-28
 */
public interface ITransferringStudentTemporaryService 
{
    /**
     * 查询选调生锻炼培养-挂职
     * 
     * @param id 选调生锻炼培养-挂职ID
     * @return 选调生锻炼培养-挂职
     */
    public TransferringStudentTemporary selectTransferringStudentTemporaryById(String id);

    /**
     * 查询选调生锻炼培养-挂职列表
     * 
     * @param transferringStudentTemporary 选调生锻炼培养-挂职
     * @return 选调生锻炼培养-挂职集合
     */
    public List<TransferringStudentTemporary> selectTransferringStudentTemporaryList(TransferringStudentTemporary transferringStudentTemporary);

    /**
     * 新增选调生锻炼培养-挂职
     * 
     * @param transferringStudentTemporary 选调生锻炼培养-挂职
     * @return 结果
     */
    public int insertTransferringStudentTemporary(TransferringStudentTemporary transferringStudentTemporary);

    /**
     * 修改选调生锻炼培养-挂职
     * 
     * @param transferringStudentTemporary 选调生锻炼培养-挂职
     * @return 结果
     */
    public int updateTransferringStudentTemporary(TransferringStudentTemporary transferringStudentTemporary);

    /**
     * 批量删除选调生锻炼培养-挂职
     * 
     * @param ids 需要删除的选调生锻炼培养-挂职ID
     * @return 结果
     */
    public int deleteTransferringStudentTemporaryByIds(String[] ids);

    /**
     * 删除选调生锻炼培养-挂职信息
     * 
     * @param id 选调生锻炼培养-挂职ID
     * @return 结果
     */
    public int deleteTransferringStudentTemporaryById(String id);

    /**
     * 获取职务字典
     * @return
     */
    public List<Map<String,String>> getWorkPostDict();
}
