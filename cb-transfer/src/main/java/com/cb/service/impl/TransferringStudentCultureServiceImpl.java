package com.cb.service.impl;

import java.util.List;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentCulture;
import com.cb.mapper.TransferringStudentCultureMapper;
import com.cb.service.ITransferringStudentCultureService;
import com.cb.vo.StudentCultureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 选调生培养Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@Service
public class TransferringStudentCultureServiceImpl implements ITransferringStudentCultureService
{
    @Autowired
    private TransferringStudentCultureMapper transferringStudentCultureMapper;

    /**
     * 查询选调生培养
     * 
     * @param id 选调生培养ID
     * @return 选调生培养
     */
    @Override
    public TransferringStudentCulture selectTransferringStudentCultureById(String id)
    {
        return transferringStudentCultureMapper.selectTransferringStudentCultureById(id);
    }

    /**
     * 查询选调生培养列表
     * 
     * @param studentCultureVo 选调生培养
     * @return 选调生培养
     */
    @Override
    public List<StudentCultureVo> selectTransferringStudentCultureList(StudentCultureVo studentCultureVo)
    {
        studentCultureVo.setDelFlag("0");
        return transferringStudentCultureMapper.selectTransferringStudentCultureList(studentCultureVo);
    }

    /**
     * 新增选调生培养
     * 
     * @param transferringStudentCulture 选调生培养
     * @return 结果
     */
    @Override
    public int insertTransferringStudentCulture(TransferringStudentCulture transferringStudentCulture)
    {
        if (StringUtils.isBlank(transferringStudentCulture.getUserId())){
            transferringStudentCulture.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        transferringStudentCulture.setId(UUID.randomUUID().toString());
        transferringStudentCulture.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentCulture.setCreateTime(DateUtils.getNowDate());
        transferringStudentCulture.setDelFlag("0");
        return transferringStudentCultureMapper.insertTransferringStudentCulture(transferringStudentCulture);
    }

    /**
     * 修改选调生培养
     * 
     * @param transferringStudentCulture 选调生培养
     * @return 结果
     */
    @Override
    public int updateTransferringStudentCulture(TransferringStudentCulture transferringStudentCulture)
    {
        transferringStudentCulture.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentCulture.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentCultureMapper.updateTransferringStudentCulture(transferringStudentCulture);
    }

    /**
     * 批量删除选调生培养
     * 
     * @param ids 需要删除的选调生培养ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentCultureByIds(String[] ids)
    {
        return transferringStudentCultureMapper.deleteTransferringStudentCultureByIds(ids);
    }

    /**
     * 删除选调生培养信息
     * 
     * @param id 选调生培养ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentCultureById(String id)
    {
        return transferringStudentCultureMapper.deleteTransferringStudentCultureById(id);
    }
}
