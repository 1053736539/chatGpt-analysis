package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentTemporary;
import com.cb.mapper.TransferringStudentTemporaryMapper;
import com.cb.service.ITransferringStudentTemporaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 选调生锻炼培养-挂职Service业务层处理
 * 
 * @author yangxin
 * @date 2024-08-28
 */
@Service
public class TransferringStudentTemporaryServiceImpl implements ITransferringStudentTemporaryService 
{
    @Autowired
    private TransferringStudentTemporaryMapper transferringStudentTemporaryMapper;

    /**
     * 查询选调生锻炼培养-挂职
     * 
     * @param id 选调生锻炼培养-挂职ID
     * @return 选调生锻炼培养-挂职
     */
    @Override
    public TransferringStudentTemporary selectTransferringStudentTemporaryById(String id)
    {
        return transferringStudentTemporaryMapper.selectTransferringStudentTemporaryById(id);
    }

    /**
     * 查询选调生锻炼培养-挂职列表
     * 
     * @param transferringStudentTemporary 选调生锻炼培养-挂职
     * @return 选调生锻炼培养-挂职
     */
    @Override
    public List<TransferringStudentTemporary> selectTransferringStudentTemporaryList(TransferringStudentTemporary transferringStudentTemporary)
    {
        return transferringStudentTemporaryMapper.selectTransferringStudentTemporaryList(transferringStudentTemporary);
    }

    /**
     * 新增选调生锻炼培养-挂职
     * 
     * @param transferringStudentTemporary 选调生锻炼培养-挂职
     * @return 结果
     */
    @Override
    public int insertTransferringStudentTemporary(TransferringStudentTemporary transferringStudentTemporary)
    {
        if (null == transferringStudentTemporary.getUserId()){
            transferringStudentTemporary.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        transferringStudentTemporary.setId(UUID.randomUUID().toString());
        transferringStudentTemporary.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentTemporary.setCreateTime(DateUtils.getNowDate());
        transferringStudentTemporary.setDelFlag("0");
        return transferringStudentTemporaryMapper.insertTransferringStudentTemporary(transferringStudentTemporary);
    }

    /**
     * 修改选调生锻炼培养-挂职
     * 
     * @param transferringStudentTemporary 选调生锻炼培养-挂职
     * @return 结果
     */
    @Override
    public int updateTransferringStudentTemporary(TransferringStudentTemporary transferringStudentTemporary)
    {
        transferringStudentTemporary.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentTemporary.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentTemporaryMapper.updateTransferringStudentTemporary(transferringStudentTemporary);
    }

    /**
     * 批量删除选调生锻炼培养-挂职
     * 
     * @param ids 需要删除的选调生锻炼培养-挂职ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentTemporaryByIds(String[] ids)
    {
        return transferringStudentTemporaryMapper.deleteTransferringStudentTemporaryByIds(ids);
    }

    /**
     * 删除选调生锻炼培养-挂职信息
     * 
     * @param id 选调生锻炼培养-挂职ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentTemporaryById(String id)
    {
        return transferringStudentTemporaryMapper.deleteTransferringStudentTemporaryById(id);
    }

    @Override
    public List<Map<String, String>> getWorkPostDict() {
        return transferringStudentTemporaryMapper.getWorkPostList().stream()
                .map(o -> {
                    Map<String, String> item = new LinkedHashMap<>();
                    item.put("dictLabel", o);
                    item.put("dictValue", o);
                    return item;
                }).collect(Collectors.toList());
    }
}
