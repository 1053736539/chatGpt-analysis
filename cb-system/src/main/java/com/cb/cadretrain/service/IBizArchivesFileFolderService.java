package com.cb.cadretrain.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.cb.cadretrain.domain.BizArchivesFileFolder;

/**
 * 干部政治素质档案文件夹表Service接口
 * 
 * @author yangcd
 * @date 2023-11-07
 */
public interface IBizArchivesFileFolderService 
{
    /**
     * 查询干部政治素质档案文件夹表
     * 
     * @param id 干部政治素质档案文件夹表ID
     * @return 干部政治素质档案文件夹表
     */
    public BizArchivesFileFolder selectBizArchivesFileFolderById(Integer id);

    /**
     * 查询干部政治素质档案文件夹表列表
     * 
     * @param bizArchivesFileFolder 干部政治素质档案文件夹表
     * @return 干部政治素质档案文件夹表集合
     */
    public List<BizArchivesFileFolder> selectBizArchivesFileFolderList(BizArchivesFileFolder bizArchivesFileFolder);

    /**
     * 新增干部政治素质档案文件夹表
     * 
     * @param bizArchivesFileFolder 干部政治素质档案文件夹表
     * @return 结果
     */
    public int insertBizArchivesFileFolder(BizArchivesFileFolder bizArchivesFileFolder);

    /**
     * 修改干部政治素质档案文件夹表
     * 
     * @param bizArchivesFileFolder 干部政治素质档案文件夹表
     * @return 结果
     */
    public int updateBizArchivesFileFolder(BizArchivesFileFolder bizArchivesFileFolder);

    /**
     * 批量删除干部政治素质档案文件夹表
     * 
     * @param ids 需要删除的干部政治素质档案文件夹表ID
     * @return 结果
     */
    public int deleteBizArchivesFileFolderByIds(Integer[] ids);

    /**
     * 删除干部政治素质档案文件夹表信息
     * 
     * @param id 干部政治素质档案文件夹表ID
     * @return 结果
     */
    public int deleteBizArchivesFileFolderById(Integer id);

    /**
     * @Auther: yangcd
     * @Date: 2023/11/8 10:15
     * @param
     * @Description:
     */
    public JSONArray selectBizFileFolderJSONArray();

    public Boolean hasChildren(String folderId);
    public int deleteBizFileFolderByFolderId(String folderId);
}
