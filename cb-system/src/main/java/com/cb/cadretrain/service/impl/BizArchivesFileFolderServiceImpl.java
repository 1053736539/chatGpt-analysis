package com.cb.cadretrain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.cb.common.enums.DeleteFlag;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.cadretrain.mapper.BizArchivesFileFolderMapper;
import com.cb.cadretrain.domain.BizArchivesFileFolder;
import com.cb.cadretrain.service.IBizArchivesFileFolderService;

/**
 * 干部政治素质档案文件夹表Service业务层处理
 * 
 * @author yangcd
 * @date 2023-11-07
 */
@Service
public class BizArchivesFileFolderServiceImpl implements IBizArchivesFileFolderService 
{
    @Autowired
    private BizArchivesFileFolderMapper bizArchivesFileFolderMapper;

    /**
     * 查询干部政治素质档案文件夹表
     * 
     * @param id 干部政治素质档案文件夹表ID
     * @return 干部政治素质档案文件夹表
     */
    @Override
    public BizArchivesFileFolder selectBizArchivesFileFolderById(Integer id)
    {
        return bizArchivesFileFolderMapper.selectBizArchivesFileFolderById(id);
    }

    /**
     * 查询干部政治素质档案文件夹表列表
     * 
     * @param bizArchivesFileFolder 干部政治素质档案文件夹表
     * @return 干部政治素质档案文件夹表
     */
    @Override
    public List<BizArchivesFileFolder> selectBizArchivesFileFolderList(BizArchivesFileFolder bizArchivesFileFolder)
    {
        return bizArchivesFileFolderMapper.selectBizArchivesFileFolderList(bizArchivesFileFolder);
    }

    /**
     * 新增干部政治素质档案文件夹表
     * 
     * @param bizArchivesFileFolder 干部政治素质档案文件夹表
     * @return 结果
     */
    @Override
    public int insertBizArchivesFileFolder(BizArchivesFileFolder bizArchivesFileFolder)
    {
        bizArchivesFileFolder.setCreateTime(DateUtils.getNowDate());
        bizArchivesFileFolder.setFolderId(IdUtils.randomUUID());
        bizArchivesFileFolder.setCreateBy(SecurityUtils.getUsername());
        return bizArchivesFileFolderMapper.insertBizArchivesFileFolder(bizArchivesFileFolder);
    }

    /**
     * 修改干部政治素质档案文件夹表
     * 
     * @param bizArchivesFileFolder 干部政治素质档案文件夹表
     * @return 结果
     */
    @Override
    public int updateBizArchivesFileFolder(BizArchivesFileFolder bizArchivesFileFolder)
    {

        bizArchivesFileFolder.setUpdateTime(DateUtils.getNowDate());
        return bizArchivesFileFolderMapper.updateBizArchivesFileFolder(bizArchivesFileFolder);
    }

    /**
     * 批量删除干部政治素质档案文件夹表
     * 
     * @param ids 需要删除的干部政治素质档案文件夹表ID
     * @return 结果
     */
    @Override
    public int deleteBizArchivesFileFolderByIds(Integer[] ids)
    {
        return bizArchivesFileFolderMapper.deleteBizArchivesFileFolderByIds(ids);
    }

    /**
     * 删除干部政治素质档案文件夹表信息
     * 
     * @param id 干部政治素质档案文件夹表ID
     * @return 结果
     */
    @Override
    public int deleteBizArchivesFileFolderById(Integer id)
    {
        return bizArchivesFileFolderMapper.deleteBizArchivesFileFolderById(id);
    }

    @Override
    public JSONArray selectBizFileFolderJSONArray() {
        List<Map<String, Object>> mapList = bizArchivesFileFolderMapper.selectBizFileFolderMapList(DeleteFlag.NORMAL.getCode());
        return buildFolderTree(mapList, "-1");
    }

    private JSONArray buildFolderTree(List<Map<String, Object>> folderMapList, String parentId) {
        return folderMapList.stream().filter(folder -> {
            return folder.get("parentId").equals(parentId);
        }).map(folder -> {
            JSONArray childList = buildFolderTree(folderMapList, folder.get("id").toString());
            if (childList != null && childList.size() > 0) {
                folder.put("children", childList);
            }
            return folder;
        }).collect(Collectors.toCollection(JSONArray::new));
    }

    public Boolean hasChildren(String folderId) {
        int i = bizArchivesFileFolderMapper.hasChildren(folderId);

        return i > 0 ? true : false;
    }

    @Override
    public int deleteBizFileFolderByFolderId(String folderId) {
        return bizArchivesFileFolderMapper.deleteBizFileFolderByFolderId(folderId);
    }
}
