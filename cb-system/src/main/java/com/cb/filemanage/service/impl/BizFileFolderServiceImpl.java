package com.cb.filemanage.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.cb.common.enums.DeleteFlag;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.filemanage.domain.BizFileFolder;
import com.cb.filemanage.mapper.BizFileFolderMapper;
import com.cb.filemanage.service.IBizFileFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务文件夹Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@Service
public class BizFileFolderServiceImpl implements IBizFileFolderService {
    @Autowired
    private BizFileFolderMapper bizFileFolderMapper;

    /**
     * 查询业务文件夹
     *
     * @param id 业务文件夹ID
     * @return 业务文件夹
     */
    @Override
    public BizFileFolder selectBizFileFolderById(Long id) {
        return bizFileFolderMapper.selectBizFileFolderById(id);
    }

    public BizFileFolder selectBizFileFolderByFolderId(String folderId) {
        return bizFileFolderMapper.selectBizFileFolderByFolderId(folderId);
    }

    /**
     * 查询业务文件夹列表
     *
     * @param bizFileFolder 业务文件夹
     * @return 业务文件夹
     */
    @Override
    public List<BizFileFolder> selectBizFileFolderList(BizFileFolder bizFileFolder) {
        return bizFileFolderMapper.selectBizFileFolderList(bizFileFolder);
    }

    @Override
    public JSONArray selectBizFileFolderJSONArray() {
        List<Map<String, Object>> mapList = bizFileFolderMapper.selectBizFileFolderMapList(DeleteFlag.NORMAL.getCode());
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

    /**
     * 新增业务文件夹
     *
     * @param bizFileFolder 业务文件夹
     * @return 结果
     */
    @Override
    public int insertBizFileFolder(BizFileFolder bizFileFolder) {
        bizFileFolder.setFolderId(IdUtils.randomUUID());
        bizFileFolder.setCreateTime(DateUtils.getNowDate());
        bizFileFolder.setCreateBy(SecurityUtils.getUsername());
        return bizFileFolderMapper.insertBizFileFolder(bizFileFolder);
    }

    /**
     * 修改业务文件夹
     *
     * @param bizFileFolder 业务文件夹
     * @return 结果
     */
    @Override
    public int updateBizFileFolder(BizFileFolder bizFileFolder) {
        bizFileFolder.setUpdateTime(DateUtils.getNowDate());
        return bizFileFolderMapper.updateBizFileFolder(bizFileFolder);
    }

    @Override
    public int updateBizFileFolderByFolderId(BizFileFolder bizFileFolder) {
        bizFileFolder.setUpdateTime(DateUtils.getNowDate());
        return bizFileFolderMapper.updateBizFileFolderByFolderId(bizFileFolder);
    }

    /**
     * 批量删除业务文件夹
     *
     * @param ids 需要删除的业务文件夹ID
     * @return 结果
     */
    @Override
    public int deleteBizFileFolderByIds(Long[] ids) {
        return bizFileFolderMapper.deleteBizFileFolderByIds(ids);
    }


    /**
     * 删除业务文件夹信息
     *
     * @param id 业务文件夹ID
     * @return 结果
     */
    @Override
    public int deleteBizFileFolderById(Long id) {
        return bizFileFolderMapper.deleteBizFileFolderById(id);
    }

    @Override
    public int deleteBizFileFolderByFolderId(String folderId) {
        return bizFileFolderMapper.deleteBizFileFolderByFolderId(folderId);
    }

    @Override
    public int logicDeleteBizFileFolderByFolderId(String folderId, int delFlag) {
        return bizFileFolderMapper.logicDeleteBizFileFolderByFolderId(folderId, delFlag);
    }

    public Boolean hasChildren(String folderId) {
        int i = bizFileFolderMapper.hasChildren(folderId);

        return i > 0 ? true : false;
    }
}
