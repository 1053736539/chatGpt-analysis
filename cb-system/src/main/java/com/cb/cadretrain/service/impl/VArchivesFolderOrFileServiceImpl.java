package com.cb.cadretrain.service.impl;

import com.cb.cadretrain.domain.BizArchivesFileFolder;
import com.cb.cadretrain.domain.VArchivesFolderOrFile;
import com.cb.cadretrain.mapper.BizArchivesFileFolderMapper;
import com.cb.cadretrain.mapper.VArchivesFolderOrFileMapper;
import com.cb.cadretrain.service.IVArchivesFolderOrFileService;
import com.cb.common.enums.DeleteFlag;
import com.cb.common.utils.SecurityUtils;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.mapper.BizAttachMapper;
import com.cb.filemanage.service.IVFolderOrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文件夹和视图Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@Service
public class VArchivesFolderOrFileServiceImpl implements IVArchivesFolderOrFileService {
   
    @Autowired
    private BizAttachMapper bizAttachMapper;

    @Autowired
    private VArchivesFolderOrFileMapper vArchivesFolderOrFileMapper;
    
    @Autowired
    private BizArchivesFileFolderMapper bizArchivesFileFolderMapper;


    /**
     * 查询文件夹和视图
     *
     * @param id 文件夹和视图ID
     * @return 文件夹和视图
     */
    @Override
    public VArchivesFolderOrFile selectVFolderOrFileById(String id) {
        return vArchivesFolderOrFileMapper.selectVFolderOrFileById(id);
    }

    /**
     * 查询文件夹和视图列表
     *
     * @param vFolderOrFile 文件夹和视图
     * @return 文件夹和视图
     */
    @Override
    public List<VArchivesFolderOrFile> selectVArchivesFolderOrFileList(VArchivesFolderOrFile vFolderOrFile) {
        return vArchivesFolderOrFileMapper.selectVArchivesFolderOrFileList(vFolderOrFile);
    }

    @Override
    public int renameFolderOrFile(VArchivesFolderOrFile vFolderOrFile) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        Long type = vFolderOrFile.getType();
        if (type == 0) {
            BizArchivesFileFolder bizFileFolder = new BizArchivesFileFolder();
            bizFileFolder.setFolderId(vFolderOrFile.getId());
            bizFileFolder.setUpdateBy(username);
            bizFileFolder.setFolderName(vFolderOrFile.getName());
            return bizArchivesFileFolderMapper.updateBizFileFolderByFolderId(bizFileFolder);
        } else {
            BizAttach bizAttach = new BizAttach();
            bizAttach.setAttachId(vFolderOrFile.getId());
            bizAttach.setUpdateBy(username);
            bizAttach.setOldName(vFolderOrFile.getName());
            return bizAttachMapper.updateBizAttachByAttachId(bizAttach);
        }
    }

    @Override
    public int delFolderOrFile(VArchivesFolderOrFile vFolderOrFile) {
        String bizId = vFolderOrFile.getId();
        Long type = vFolderOrFile.getType();
        if (type == 0) {
            return bizArchivesFileFolderMapper.logicDeleteBizFileFolderByFolderId(bizId, DeleteFlag.DELETED.getCode());
        } else {
            return bizAttachMapper.logicDeleteBizAttachByAttachId(bizId,DeleteFlag.DELETED.getCode());
        }
    }
}
