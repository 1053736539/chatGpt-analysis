package com.cb.filemanage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.DeleteFlag;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.filemanage.mapper.BizAttachMapper;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.service.IBizAttachService;

/**
 * 业务文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
@Service
public class BizAttachServiceImpl implements IBizAttachService 
{
    @Autowired
    private BizAttachMapper bizAttachMapper;

    /**
     * 查询业务文件
     * 
     * @param id 业务文件ID
     * @return 业务文件
     */
    @Override
    public BizAttach selectBizAttachById(Long id)
    {
        return bizAttachMapper.selectBizAttachById(id);
    }
    @Override
    public BizAttach selectBizAttachByAttachId(String attachId){
        return bizAttachMapper.selectBizAttachByAttachId(attachId);
    }

    /**
     * 查询业务文件列表
     * 
     * @param bizAttach 业务文件
     * @return 业务文件
     */
    @Override
    public List<BizAttach> selectBizAttachList(BizAttach bizAttach)
    {
        return bizAttachMapper.selectBizAttachList(bizAttach);
    }

    @Override
    public List<BizAttach> selectVFolderOrFileListByIds(String attachIds) {
        if (StringUtils.isBlank(attachIds))
        {
            return new ArrayList<>();
        }
        String[] split = attachIds.split(",");
        List<String> collect = Arrays.stream(split).collect(Collectors.toList());
        return bizAttachMapper.selectBizAttachListByIds(collect);
    }

    /**
     * 新增业务文件
     * 
     * @param bizAttach 业务文件
     * @return 结果
     */
    @Override
    public int insertBizAttach(BizAttach bizAttach)
    {
        bizAttach.setCreateTime(DateUtils.getNowDate());
        return bizAttachMapper.insertBizAttach(bizAttach);
    }


    @Override
    public int saveAttach(BizAttach bizAttach)
    {
        bizAttach.setAttachId(IdUtils.randomUUID());
        String oldName = bizAttach.getOldName();
        String extName = FilenameUtils.getExtension(oldName);
        bizAttach.setExtName(extName);
        String path = bizAttach.getPath();
        String newName = path.substring(path.lastIndexOf("/")+1);
        bizAttach.setNewName(newName);
        bizAttach.setDelFlag(DeleteFlag.NORMAL.getCode());
        bizAttach.setVersion("1");
        String username = SecurityUtils.getUsername();
        bizAttach.setCreateBy(username);
        bizAttach.setCreateTime(DateUtils.getNowDate());
        return bizAttachMapper.insertBizAttach(bizAttach);
    }

    @Override
    public int addAttach(BizAttach bizAttach)
    {
        bizAttach.setAttachId(IdUtils.randomUUID());
        String oldName = bizAttach.getOldName();
        String extName = FilenameUtils.getExtension(oldName);
        bizAttach.setExtName(extName);
        String path = bizAttach.getPath();
        String newName = path.substring(path.lastIndexOf("/")+1);
        bizAttach.setNewName(newName);
        bizAttach.setDelFlag(DeleteFlag.NORMAL.getCode());
        bizAttach.setVersion("1");
//        String username = SecurityUtils.getUsername();
        bizAttach.setCreateBy(bizAttach.getCreateBy());
        bizAttach.setCreateTime(DateUtils.getNowDate());
        return bizAttachMapper.insertBizAttach(bizAttach);
    }

    /**
     * 修改业务文件
     * 
     * @param bizAttach 业务文件
     * @return 结果
     */
    @Override
    public int updateBizAttach(BizAttach bizAttach)
    {
        bizAttach.setUpdateTime(DateUtils.getNowDate());
        return bizAttachMapper.updateBizAttach(bizAttach);
    }

    @Override
    public int updateBizAttachByAttachId(BizAttach bizAttach)
    {
        bizAttach.setUpdateTime(DateUtils.getNowDate());
        return bizAttachMapper.updateBizAttachByAttachId(bizAttach);
    }

    /**
     * 批量删除业务文件
     * 
     * @param ids 需要删除的业务文件ID
     * @return 结果
     */
    @Override
    public int deleteBizAttachByIds(Long[] ids)
    {
        return bizAttachMapper.deleteBizAttachByIds(ids);
    }

    /**
     * 删除业务文件信息
     * 
     * @param id 业务文件ID
     * @return 结果
     */
    @Override
    public int deleteBizAttachById(Long id)
    {
        return bizAttachMapper.deleteBizAttachById(id);
    }

    @Override
    public int logicDeleteBizAttachByAttachId(String attachId,int delFlag) {
        return bizAttachMapper.logicDeleteBizAttachByAttachId(attachId,delFlag);
    }
}
