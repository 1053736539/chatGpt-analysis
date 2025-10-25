package com.cb.cadretrain.mapper;

import com.cb.cadretrain.domain.VArchivesFolderOrFile;

import java.util.List;

/**
 * 文件夹和视图Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface VArchivesFolderOrFileMapper
{
    /**
     * 查询文件夹和视图
     * 
     * @param id 文件夹和视图ID
     * @return 文件夹和视图
     */
    public VArchivesFolderOrFile selectVFolderOrFileById(String id);

    /**
     * 查询文件夹和视图列表
     * 
     * @param vFolderOrFile 文件夹和视图
     * @return 文件夹和视图集合
     */
    public List<VArchivesFolderOrFile> selectVArchivesFolderOrFileList(VArchivesFolderOrFile vFolderOrFile);

}
