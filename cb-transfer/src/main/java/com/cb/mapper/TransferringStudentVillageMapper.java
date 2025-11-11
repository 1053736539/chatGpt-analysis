package com.cb.mapper;

import java.util.List;
import com.cb.domain.TransferringStudentVillage;

/**
 * 选调生锻炼培养-驻村Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
public interface TransferringStudentVillageMapper 
{
    /**
     * 查询选调生锻炼培养-驻村
     * 
     * @param id 选调生锻炼培养-驻村ID
     * @return 选调生锻炼培养-驻村
     */
    public TransferringStudentVillage selectTransferringStudentVillageById(String id);

    /**
     * 查询选调生锻炼培养-驻村列表
     * 
     * @param transferringStudentVillage 选调生锻炼培养-驻村
     * @return 选调生锻炼培养-驻村集合
     */
    public List<TransferringStudentVillage> selectTransferringStudentVillageList(TransferringStudentVillage transferringStudentVillage);

    /**
     * 新增选调生锻炼培养-驻村
     * 
     * @param transferringStudentVillage 选调生锻炼培养-驻村
     * @return 结果
     */
    public int insertTransferringStudentVillage(TransferringStudentVillage transferringStudentVillage);

    /**
     * 修改选调生锻炼培养-驻村
     * 
     * @param transferringStudentVillage 选调生锻炼培养-驻村
     * @return 结果
     */
    public int updateTransferringStudentVillage(TransferringStudentVillage transferringStudentVillage);

    /**
     * 删除选调生锻炼培养-驻村
     * 
     * @param id 选调生锻炼培养-驻村ID
     * @return 结果
     */
    public int deleteTransferringStudentVillageById(String id);

    /**
     * 批量删除选调生锻炼培养-驻村
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferringStudentVillageByIds(String[] ids);
}
