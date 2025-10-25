package com.cb.docArchive.service;

import com.cb.docArchive.domain.BizDocArchiveFolder;
import com.cb.docArchive.vo.DocArchiveVo;

import java.util.List;

/**
 * 文书档案文件夹目录Service接口
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
public interface IBizDocArchiveFolderService 
{
    /**
     * 查询文书档案文件夹目录
     * 
     * @param folderId 文书档案文件夹目录ID
     * @return 文书档案文件夹目录
     */
    public BizDocArchiveFolder selectBizDocArchiveFolderById(String folderId);

    /**
     * 查询文书档案文件夹目录列表
     * 
     * @param bizDocArchiveFolder 文书档案文件夹目录
     * @return 文书档案文件夹目录集合
     */
    public List<BizDocArchiveFolder> selectBizDocArchiveFolderList(BizDocArchiveFolder bizDocArchiveFolder);

    /**
     * 新增文书档案文件夹目录
     * 
     * @param bizDocArchiveFolder 文书档案文件夹目录
     * @return 结果
     */
    public int insertBizDocArchiveFolder(BizDocArchiveFolder bizDocArchiveFolder);

    /**
     * 修改文书档案文件夹目录
     * 
     * @param bizDocArchiveFolder 文书档案文件夹目录
     * @return 结果
     */
    public int updateBizDocArchiveFolder(BizDocArchiveFolder bizDocArchiveFolder);

    /**
     * 批量删除文书档案文件夹目录
     * 
     * @param folderIds 需要删除的文书档案文件夹目录ID
     * @return 结果
     */
    public int deleteBizDocArchiveFolderByIds(String[] folderIds);

    /**
     * 删除文书档案文件夹目录信息
     * 
     * @param folderId 文书档案文件夹目录ID
     * @return 结果
     */
    public int deleteBizDocArchiveFolderById(String folderId);

    /**
     * 构建目录树结构
     * @return
     */
    List<DocArchiveVo.FolderOrRecordNode> getFolderTree();

    /**
     * 获取文件夹树结构数据
     * @return
     */
    List<DocArchiveVo.FolderOrRecordNode> getSubFolder(String parentId);

    /**
     * 获取子孙级ids
     * @param folderId
     * @return
     */
    List<String> findSubChainIds(String folderId);

}
