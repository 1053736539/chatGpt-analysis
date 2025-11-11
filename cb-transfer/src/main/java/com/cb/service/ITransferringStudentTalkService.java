package com.cb.service;

import com.cb.domain.TransferringStudentTalk;

import java.util.List;

/**
 * 监督管理-谈心谈话Service接口
 * 
 * @author yangxin
 * @date 2024-08-27
 */
public interface ITransferringStudentTalkService 
{
    /**
     * 查询监督管理-谈心谈话
     * 
     * @param id 监督管理-谈心谈话ID
     * @return 监督管理-谈心谈话
     */
    public TransferringStudentTalk selectTransferringStudentTalkById(String id);

    /**
     * 查询监督管理-谈心谈话列表
     * 
     * @param transferringStudentTalk 监督管理-谈心谈话
     * @return 监督管理-谈心谈话集合
     */
    public List<TransferringStudentTalk> selectTransferringStudentTalkList(TransferringStudentTalk transferringStudentTalk);

    /**
     * 新增监督管理-谈心谈话
     * 
     * @param transferringStudentTalk 监督管理-谈心谈话
     * @return 结果
     */
    public int insertTransferringStudentTalk(TransferringStudentTalk transferringStudentTalk);

    /**
     * 修改监督管理-谈心谈话
     * 
     * @param transferringStudentTalk 监督管理-谈心谈话
     * @return 结果
     */
    public int updateTransferringStudentTalk(TransferringStudentTalk transferringStudentTalk);

    /**
     * 批量删除监督管理-谈心谈话
     * 
     * @param ids 需要删除的监督管理-谈心谈话ID
     * @return 结果
     */
    public int deleteTransferringStudentTalkByIds(String[] ids);

    /**
     * 删除监督管理-谈心谈话信息
     * 
     * @param id 监督管理-谈心谈话ID
     * @return 结果
     */
    public int deleteTransferringStudentTalkById(String id);
}
