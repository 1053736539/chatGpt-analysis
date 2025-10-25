package com.cb.filemanage.service;

import com.cb.filemanage.domain.VFolderOrFile;

import java.util.List;

/**
 * 文件夹和视图Service接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface IVFolderOrFileService 
{
    /**
     * 查询文件夹和视图
     * 
     * @param id 文件夹和视图ID
     * @return 文件夹和视图
     */
    public VFolderOrFile selectVFolderOrFileById(String id);

    /**
     * 查询文件夹和视图列表
     * 
     * @param vFolderOrFile 文件夹和视图
     * @return 文件夹和视图集合
     */
    public List<VFolderOrFile> selectVFolderOrFileList(VFolderOrFile vFolderOrFile);



    public int renameFolderOrFile(VFolderOrFile vFolderOrFile);


    public int delFolderOrFile(VFolderOrFile vFolderOrFile);

    public  VFolderOrFile selectVFolderOrFileByName(String name);

    List<VFolderOrFile> selectVFolderOrFileListByParentId(String parentId);
}
