package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentVisit;
import com.cb.mapper.TransferringStudentVisitMapper;
import com.cb.service.ITransferringStudentVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 监督管理-走访基层Service业务层处理
 * 
 * @author yangxin
 * @date 2024-08-27
 */
@Service
public class TransferringStudentVisitServiceImpl implements ITransferringStudentVisitService 
{
    @Autowired
    private TransferringStudentVisitMapper transferringStudentVisitMapper;

    /**
     * 查询监督管理-走访基层
     * 
     * @param id 监督管理-走访基层ID
     * @return 监督管理-走访基层
     */
    @Override
    public TransferringStudentVisit selectTransferringStudentVisitById(String id)
    {
        return transferringStudentVisitMapper.selectTransferringStudentVisitById(id);
    }

    /**
     * 查询监督管理-走访基层列表
     * 
     * @param transferringStudentVisit 监督管理-走访基层
     * @return 监督管理-走访基层
     */
    @Override
    public List<TransferringStudentVisit> selectTransferringStudentVisitList(TransferringStudentVisit transferringStudentVisit)
    {
        return transferringStudentVisitMapper.selectTransferringStudentVisitList(transferringStudentVisit);
    }

    /**
     * 新增监督管理-走访基层
     * 
     * @param transferringStudentVisit 监督管理-走访基层
     * @return 结果
     */
    @Override
    public int insertTransferringStudentVisit(TransferringStudentVisit transferringStudentVisit)
    {
        if (null == transferringStudentVisit.getUserId()){
            transferringStudentVisit.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        transferringStudentVisit.setId(UUID.randomUUID().toString());
        transferringStudentVisit.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentVisit.setCreateTime(DateUtils.getNowDate());
        transferringStudentVisit.setDelFlag("0");
        return transferringStudentVisitMapper.insertTransferringStudentVisit(transferringStudentVisit);
    }

    /**
     * 修改监督管理-走访基层
     * 
     * @param transferringStudentVisit 监督管理-走访基层
     * @return 结果
     */
    @Override
    public int updateTransferringStudentVisit(TransferringStudentVisit transferringStudentVisit)
    {
        transferringStudentVisit.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentVisit.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentVisitMapper.updateTransferringStudentVisit(transferringStudentVisit);
    }

    /**
     * 批量删除监督管理-走访基层
     * 
     * @param ids 需要删除的监督管理-走访基层ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentVisitByIds(String[] ids)
    {
        return transferringStudentVisitMapper.deleteTransferringStudentVisitByIds(ids);
    }

    /**
     * 删除监督管理-走访基层信息
     * 
     * @param id 监督管理-走访基层ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentVisitById(String id)
    {
        return transferringStudentVisitMapper.deleteTransferringStudentVisitById(id);
    }
}
