package com.cb.filemanage.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.cb.filemanage.domain.BizFileFolder;

/**
 * 业务文件夹Service接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface IBizFileFolderService 
{
    /**
     * 查询业务文件夹
     * 
     * @param id 业务文件夹ID
     * @return 业务文件夹
     */
    public BizFileFolder selectBizFileFolderById(Long id);


    public BizFileFolder selectBizFileFolderByFolderId(String folderId);

    /**
     * 查询业务文件夹列表
     * 
     * @param bizFileFolder 业务文件夹
     * @return 业务文件夹集合
     */
    public List<BizFileFolder> selectBizFileFolderList(BizFileFolder bizFileFolder);


    public JSONArray selectBizFileFolderJSONArray();

    /**
     * 新增业务文件夹
     * 
     * @param bizFileFolder 业务文件夹
     * @return 结果
     */
    public int insertBizFileFolder(BizFileFolder bizFileFolder);

    /**
     * 修改业务文件夹
     * 
     * @param bizFileFolder 业务文件夹
     * @return 结果
     */
    public int updateBizFileFolder(BizFileFolder bizFileFolder);

    public int updateBizFileFolderByFolderId(BizFileFolder bizFileFolder);

    /**
     * 批量删除业务文件夹
     * 
     * @param ids 需要删除的业务文件夹ID
     * @return 结果
     */
    public int deleteBizFileFolderByIds(Long[] ids);

    /**
     * 删除业务文件夹信息
     * 
     * @param id 业务文件夹ID
     * @return 结果
     */
    public int deleteBizFileFolderById(Long id);

    public int deleteBizFileFolderByFolderId(String folderId);

    public int logicDeleteBizFileFolderByFolderId(String folderId,int delFlag);

    public Boolean hasChildren(String folderId);
}
