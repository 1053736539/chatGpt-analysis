package com.cb.docArchive.mapper;

import com.cb.docArchive.domain.BizDocArchiveFolder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文书档案文件夹目录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
public interface BizDocArchiveFolderMapper 
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
     * 删除文书档案文件夹目录
     * 
     * @param folderId 文书档案文件夹目录ID
     * @return 结果
     */
    public int deleteBizDocArchiveFolderById(String folderId);

    /**
     * 批量删除文书档案文件夹目录
     * 
     * @param folderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizDocArchiveFolderByIds(String[] folderIds);

    public List<String> findSubChainIds(@Param("folderId") String folderId);
}
