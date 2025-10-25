package com.cb.filemanage.service.impl;

import java.util.List;

import com.cb.common.enums.DeleteFlag;
import com.cb.common.utils.SecurityUtils;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.domain.BizFileFolder;
import com.cb.filemanage.mapper.BizAttachMapper;
import com.cb.filemanage.mapper.BizFileFolderMapper;
import com.cb.filemanage.mapper.VFolderOrFileMapper;
import com.cb.filemanage.service.IVFolderOrFileService;
import com.cb.filemanage.domain.VFolderOrFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 文件夹和视图Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@Service
public class VFolderOrFileServiceImpl implements IVFolderOrFileService {
    @Autowired
    private VFolderOrFileMapper vFolderOrFileMapper;
    @Autowired
    private BizAttachMapper bizAttachMapper;

    @Autowired
    private BizFileFolderMapper bizFileFolderMapper;


    /**
     * 查询文件夹和视图
     *
     * @param id 文件夹和视图ID
     * @return 文件夹和视图
     */
    @Override
    public VFolderOrFile selectVFolderOrFileById(String id) {
        return vFolderOrFileMapper.selectVFolderOrFileById(id);
    }

    /**
     * 查询文件夹和视图列表
     *
     * @param vFolderOrFile 文件夹和视图
     * @return 文件夹和视图
     */
    @Override
    public List<VFolderOrFile> selectVFolderOrFileList(VFolderOrFile vFolderOrFile) {
        return vFolderOrFileMapper.selectVFolderOrFileList(vFolderOrFile);
    }

    @Override
    public int renameFolderOrFile(VFolderOrFile vFolderOrFile) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        Long type = vFolderOrFile.getType();
        if (type == 0) {
            BizFileFolder bizFileFolder = new BizFileFolder();
            bizFileFolder.setFolderId(vFolderOrFile.getId());
            bizFileFolder.setUpdateBy(username);
            bizFileFolder.setFolderName(vFolderOrFile.getName());
            return bizFileFolderMapper.updateBizFileFolderByFolderId(bizFileFolder);
        } else {
            BizAttach bizAttach = new BizAttach();
            bizAttach.setAttachId(vFolderOrFile.getId());
            bizAttach.setUpdateBy(username);
            bizAttach.setOldName(vFolderOrFile.getName());
            return bizAttachMapper.updateBizAttachByAttachId(bizAttach);
        }
    }

    @Override
    public int delFolderOrFile(VFolderOrFile vFolderOrFile) {
        String bizId = vFolderOrFile.getId();
        Long type = vFolderOrFile.getType();
        if (type == 0) {
            return bizFileFolderMapper.logicDeleteBizFileFolderByFolderId(bizId, DeleteFlag.DELETED.getCode());
        } else {
            return bizAttachMapper.logicDeleteBizAttachByAttachId(bizId,DeleteFlag.DELETED.getCode());
        }
    }

    @Override
    public VFolderOrFile selectVFolderOrFileByName(String name) {
        return vFolderOrFileMapper.selectVFolderOrFileByName(name);
    }

    @Override
    public List<VFolderOrFile> selectVFolderOrFileListByParentId(String parentId) {
        return vFolderOrFileMapper.selectVFolderOrFileListByParentId(parentId);
    }
}
