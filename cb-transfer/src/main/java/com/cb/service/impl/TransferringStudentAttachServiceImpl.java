package com.cb.service.impl;

import java.util.*;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.domain.TransferringStudentAttach;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.mapper.BizAttachMapper;
import com.cb.filemanage.service.IBizAttachService;
import com.cb.mapper.TransferringStudentAttachMapper;
import com.cb.service.ITransferringStudentAttachService;
import com.cb.vo.AttachVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 选调生其他信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-31
 */
@Service
public class TransferringStudentAttachServiceImpl implements ITransferringStudentAttachService
{
    @Autowired
    private TransferringStudentAttachMapper transferringStudentAttachMapper;
    @Autowired
    private BizAttachMapper bizAttachMapper;
    @Autowired
    private IBizAttachService iBizAttachService;
    /**
     * 查询选调生其他信息
     * 
     * @param id 选调生其他信息ID
     * @return 选调生其他信息
     */
    @Override
    public TransferringStudentAttach selectTransferringStudentAttachById(String id)
    {
        return transferringStudentAttachMapper.selectTransferringStudentAttachById(id);
    }

    /**
     * 查询选调生其他信息列表
     * 
     * @param attachVo 选调生其他信息
     * @return 选调生其他信息
     */
    @Override
    public List<AttachVo> selectTransferringStudentAttachList(AttachVo attachVo)
    {
        attachVo.setDelFlag("0");
        List<AttachVo> attachVos = transferringStudentAttachMapper.selectTransferringStudentAttachList(attachVo);
        List<String> strings = new ArrayList<>();

        HashMap<String, AttachVo> stringAttachVoHashMap = new HashMap<>();
        for (AttachVo vo : attachVos) {
            if (StringUtils.isNoneBlank(vo.getAttach())){
                String[] split = vo.getAttach().split(",");
                for (String s1 : split) {
                    stringAttachVoHashMap.put(s1,vo);
                }
                strings.addAll(Arrays.asList(split));
            }
        }
        List<BizAttach> bizAttaches = iBizAttachService.selectVFolderOrFileListByIds(StringUtils.join(strings,","));
        for (BizAttach bizAttach : bizAttaches) {
            AttachVo attachVo1 = stringAttachVoHashMap.get(bizAttach.getAttachId());
            List<BizAttach> attachList = attachVo1.getAttachList();
            if (attachList==null){
                attachList = new ArrayList<>();
                attachList.add(bizAttach);
            }else {
                attachList.add(bizAttach);
            }
            attachVo1.setAttachList(attachList);
        }
        return attachVos;
    }

    /**
     * 新增选调生其他信息
     * 
     * @param transferringStudentAttach 选调生其他信息
     * @return 结果
     */
    @Override
    public int insertTransferringStudentAttach(TransferringStudentAttach transferringStudentAttach)
    {
        transferringStudentAttach.setId(UUID.randomUUID().toString());
        transferringStudentAttach.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        if (StringUtils.isBlank(transferringStudentAttach.getUserId())){
            transferringStudentAttach.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        }
        transferringStudentAttach.setDelFlag("0");
        transferringStudentAttach.setCreateTime(DateUtils.getNowDate());
        return transferringStudentAttachMapper.insertTransferringStudentAttach(transferringStudentAttach);
    }

    /**
     * 修改选调生其他信息
     * 
     * @param transferringStudentAttach 选调生其他信息
     * @return 结果
     */
    @Override
    public int updateTransferringStudentAttach(TransferringStudentAttach transferringStudentAttach)
    {
        transferringStudentAttach.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        transferringStudentAttach.setUpdateTime(DateUtils.getNowDate());
        return transferringStudentAttachMapper.updateTransferringStudentAttach(transferringStudentAttach);
    }

    /**
     * 批量删除选调生其他信息
     * 
     * @param ids 需要删除的选调生其他信息ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentAttachByIds(String[] ids)
    {
        return transferringStudentAttachMapper.deleteTransferringStudentAttachByIds(ids);
    }

    /**
     * 删除选调生其他信息信息
     * 
     * @param id 选调生其他信息ID
     * @return 结果
     */
    @Override
    public int deleteTransferringStudentAttachById(String id)
    {
        return transferringStudentAttachMapper.deleteTransferringStudentAttachById(id);
    }
}
