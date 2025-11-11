package com.cb.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.domain.TransferringStudentExamine;
import com.cb.mapper.TransferringStudentExamineMapper;
import com.cb.service.ITransferringStudentExamineService;
import com.cb.vo.StudentExamineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 选调生考核评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@Service
public class TransferringStudentExamineServiceImpl implements ITransferringStudentExamineService
{
    @Autowired
    private TransferringStudentExamineMapper transferringStudentExamineMapper;

    /**
     * 查询选调生考核评价
     * 
     * @param id 选调生考核评价ID
     * @return 选调生考核评价
     */
    @Override
    public TransferringStudentExamine selectTransferringStudentExamineById(String id)
    {
        return transferringStudentExamineMapper.selectTransferringStudentExamineById(id);
    }

    /**
     * 查询选调生考核评价列表
     * 
     * @param transferringStudentExamine 选调生考核评价
     * @return 选调生考核评价
     */
    @Override
    public List<StudentExamineVo> selectTransferringStudentExamineList(StudentExamineVo studentExamineVo)
    {
        studentExamineVo.setDelFlag("0");
        return transferringStudentExamineMapper.selectTransferringStudentExamineList(studentExamineVo);
    }

    /**
     * 新增选调生考核评价
     * 
     * @param transferringStudentExamine 选调生考核评价
     * @return 结果
     */
    @Override
    public int insertTransferringStudentExamine(TransferringStudentExamine transferringStudentExamine)
    {
        transferringStudentExamine.setId(UUID.randomUUID().toString());
        if (StringUtils.isBlank(transferringStudentExamine.getUserId())){
            transferringStudentExamine.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        transferringStudentExamine.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentExamine.setCreateTime(new Date());
        transferringStudentExamine.setDelFlag("0");
        return transferringStudentExamineMapper.insertTransferringStudentExamine(transferringStudentExamine);
    }

    /**
     * 修改选调生考核评价
     * 
     * @param transferringStudentExamine 选调生考核评价
     * @return 结果
     */
    @Override
    public int updateTransferringStudentExamine(TransferringStudentExamine transferringStudentExamine)
    {
        transferringStudentExamine.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentExamine.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentExamineMapper.updateTransferringStudentExamine(transferringStudentExamine);
    }

    /**
     * 批量删除选调生考核评价
     * 
     * @param ids 需要删除的选调生考核评价ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentExamineByIds(String[] ids)
    {
        return transferringStudentExamineMapper.deleteTransferringStudentExamineByIds(ids);
    }

    /**
     * 删除选调生考核评价信息
     * 
     * @param id 选调生考核评价ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentExamineById(String id)
    {
        return transferringStudentExamineMapper.deleteTransferringStudentExamineById(id);
    }
}
