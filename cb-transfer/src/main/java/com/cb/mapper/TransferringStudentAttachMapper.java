package com.cb.mapper;

import com.cb.domain.TransferringStudentAttach;
import com.cb.vo.AttachVo;

import java.util.List;

/**
 * 选调生其他信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
public interface TransferringStudentAttachMapper 
{
    /**
     * 查询选调生其他信息
     * 
     * @param id 选调生其他信息ID
     * @return 选调生其他信息
     */
    public TransferringStudentAttach selectTransferringStudentAttachById(String id);

    /**
     * 查询选调生其他信息列表
     * 
     * @param transferringStudentAttach 选调生其他信息
     * @return 选调生其他信息集合
     */
    public List<AttachVo> selectTransferringStudentAttachList(AttachVo attachVo);

    /**
     * 新增选调生其他信息
     * 
     * @param transferringStudentAttach 选调生其他信息
     * @return 结果
     */
    public int insertTransferringStudentAttach(TransferringStudentAttach transferringStudentAttach);

    /**
     * 修改选调生其他信息
     * 
     * @param transferringStudentAttach 选调生其他信息
     * @return 结果
     */
    public int updateTransferringStudentAttach(TransferringStudentAttach transferringStudentAttach);

    /**
     * 删除选调生其他信息
     * 
     * @param id 选调生其他信息ID
     * @return 结果
     */
    public int deleteTransferringStudentAttachById(String id);

    /**
     * 批量删除选调生其他信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferringStudentAttachByIds(String[] ids);
}
