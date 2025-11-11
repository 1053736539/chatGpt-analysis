package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentTalk;
import com.cb.mapper.TransferringStudentTalkMapper;
import com.cb.service.ITransferringStudentTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 监督管理-谈心谈话Service业务层处理
 * 
 * @author yangxin
 * @date 2024-08-27
 */
@Service
public class TransferringStudentTalkServiceImpl implements ITransferringStudentTalkService 
{
    @Autowired
    private TransferringStudentTalkMapper transferringStudentTalkMapper;

    /**
     * 查询监督管理-谈心谈话
     * 
     * @param id 监督管理-谈心谈话ID
     * @return 监督管理-谈心谈话
     */
    @Override
    public TransferringStudentTalk selectTransferringStudentTalkById(String id)
    {
        return transferringStudentTalkMapper.selectTransferringStudentTalkById(id);
    }

    /**
     * 查询监督管理-谈心谈话列表
     * 
     * @param transferringStudentTalk 监督管理-谈心谈话
     * @return 监督管理-谈心谈话
     */
    @Override
    public List<TransferringStudentTalk> selectTransferringStudentTalkList(TransferringStudentTalk transferringStudentTalk)
    {
        return transferringStudentTalkMapper.selectTransferringStudentTalkList(transferringStudentTalk);
    }

    /**
     * 新增监督管理-谈心谈话
     * 
     * @param transferringStudentTalk 监督管理-谈心谈话
     * @return 结果
     */
    @Override
    public int insertTransferringStudentTalk(TransferringStudentTalk transferringStudentTalk)
    {
        if (null == transferringStudentTalk.getUserId()){
            transferringStudentTalk.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        transferringStudentTalk.setId(UUID.randomUUID().toString());
        transferringStudentTalk.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentTalk.setCreateTime(DateUtils.getNowDate());
        transferringStudentTalk.setDelFlag("0");
        return transferringStudentTalkMapper.insertTransferringStudentTalk(transferringStudentTalk);
    }

    /**
     * 修改监督管理-谈心谈话
     * 
     * @param transferringStudentTalk 监督管理-谈心谈话
     * @return 结果
     */
    @Override
    public int updateTransferringStudentTalk(TransferringStudentTalk transferringStudentTalk)
    {
        transferringStudentTalk.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentTalk.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentTalkMapper.updateTransferringStudentTalk(transferringStudentTalk);
    }

    /**
     * 批量删除监督管理-谈心谈话
     * 
     * @param ids 需要删除的监督管理-谈心谈话ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentTalkByIds(String[] ids)
    {
        return transferringStudentTalkMapper.deleteTransferringStudentTalkByIds(ids);
    }

    /**
     * 删除监督管理-谈心谈话信息
     * 
     * @param id 监督管理-谈心谈话ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentTalkById(String id)
    {
        return transferringStudentTalkMapper.deleteTransferringStudentTalkById(id);
    }
}
