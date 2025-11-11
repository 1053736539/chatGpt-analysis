package com.cb.mapper;

import java.util.List;
import com.cb.domain.TransferringStudentSpecial;

/**
 * 选调生锻炼培养-参与专项任务Mapper接口
 * 
 * @author yangxin
 * @date 2024-08-28
 */
public interface TransferringStudentSpecialMapper 
{
    /**
     * 查询选调生锻炼培养-参与专项任务
     * 
     * @param id 选调生锻炼培养-参与专项任务ID
     * @return 选调生锻炼培养-参与专项任务
     */
    public TransferringStudentSpecial selectTransferringStudentSpecialById(String id);

    /**
     * 查询选调生锻炼培养-参与专项任务列表
     * 
     * @param transferringStudentSpecial 选调生锻炼培养-参与专项任务
     * @return 选调生锻炼培养-参与专项任务集合
     */
    public List<TransferringStudentSpecial> selectTransferringStudentSpecialList(TransferringStudentSpecial transferringStudentSpecial);

    /**
     * 新增选调生锻炼培养-参与专项任务
     * 
     * @param transferringStudentSpecial 选调生锻炼培养-参与专项任务
     * @return 结果
     */
    public int insertTransferringStudentSpecial(TransferringStudentSpecial transferringStudentSpecial);

    /**
     * 修改选调生锻炼培养-参与专项任务
     * 
     * @param transferringStudentSpecial 选调生锻炼培养-参与专项任务
     * @return 结果
     */
    public int updateTransferringStudentSpecial(TransferringStudentSpecial transferringStudentSpecial);

    /**
     * 删除选调生锻炼培养-参与专项任务
     * 
     * @param id 选调生锻炼培养-参与专项任务ID
     * @return 结果
     */
    public int deleteTransferringStudentSpecialById(String id);

    /**
     * 批量删除选调生锻炼培养-参与专项任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferringStudentSpecialByIds(String[] ids);
}
