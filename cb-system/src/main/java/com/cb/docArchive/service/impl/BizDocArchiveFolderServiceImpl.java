package com.cb.docArchive.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.tree.TreeBuildUtil;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.docArchive.domain.BizDocArchiveFolder;
import com.cb.docArchive.mapper.BizDocArchiveFolderMapper;
import com.cb.docArchive.service.IBizDocArchiveFolderService;
import com.cb.docArchive.vo.DocArchiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文书档案文件夹目录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
@Service
public class BizDocArchiveFolderServiceImpl implements IBizDocArchiveFolderService 
{
    @Autowired
    private BizDocArchiveFolderMapper bizDocArchiveFolderMapper;

    /**
     * 查询文书档案文件夹目录
     * 
     * @param folderId 文书档案文件夹目录ID
     * @return 文书档案文件夹目录
     */
    @Override
    public BizDocArchiveFolder selectBizDocArchiveFolderById(String folderId)
    {
        return bizDocArchiveFolderMapper.selectBizDocArchiveFolderById(folderId);
    }

    /**
     * 查询文书档案文件夹目录列表
     * 
     * @param bizDocArchiveFolder 文书档案文件夹目录
     * @return 文书档案文件夹目录
     */
    @Override
    public List<BizDocArchiveFolder> selectBizDocArchiveFolderList(BizDocArchiveFolder bizDocArchiveFolder)
    {
        return bizDocArchiveFolderMapper.selectBizDocArchiveFolderList(bizDocArchiveFolder);
    }

    /**
     * 新增文书档案文件夹目录
     * 
     * @param bizDocArchiveFolder 文书档案文件夹目录
     * @return 结果
     */
    @Override
    public int insertBizDocArchiveFolder(BizDocArchiveFolder bizDocArchiveFolder)
    {
        if(StringUtils.isBlank(bizDocArchiveFolder.getFolderId())){
            bizDocArchiveFolder.setFolderId(IdUtils.randomUUID());
        }
        try{
            bizDocArchiveFolder.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}

        bizDocArchiveFolder.setCreateTime(DateUtils.getNowDate());
        return bizDocArchiveFolderMapper.insertBizDocArchiveFolder(bizDocArchiveFolder);
    }

    /**
     * 修改文书档案文件夹目录
     * 
     * @param bizDocArchiveFolder 文书档案文件夹目录
     * @return 结果
     */
    @Override
    public int updateBizDocArchiveFolder(BizDocArchiveFolder bizDocArchiveFolder)
    {
        try{
            bizDocArchiveFolder.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}

        bizDocArchiveFolder.setUpdateTime(DateUtils.getNowDate());
        return bizDocArchiveFolderMapper.updateBizDocArchiveFolder(bizDocArchiveFolder);
    }

    /**
     * 批量删除文书档案文件夹目录
     * 
     * @param folderIds 需要删除的文书档案文件夹目录ID
     * @return 结果
     */
    @Override
    public int deleteBizDocArchiveFolderByIds(String[] folderIds)
    {
        int rst = 0;
        for (String folderId : folderIds) {
            int i = deleteBizDocArchiveFolderById(folderId);
            rst += i;
        }
        return rst;
//        return bizDocArchiveFolderMapper.deleteBizDocArchiveFolderByIds(folderIds);
    }

    /**
     * 删除文书档案文件夹目录信息
     * 
     * @param folderId 文书档案文件夹目录ID
     * @return 结果
     */
    @Override
    public int deleteBizDocArchiveFolderById(String folderId)
    {
        BizDocArchiveFolder entity = new BizDocArchiveFolder();
        entity.setFolderId(folderId);
        entity.setDelFlag("2");
        return updateBizDocArchiveFolder(entity);
//        return bizDocArchiveFolderMapper.deleteBizDocArchiveFolderById(folderId);
    }

    @Override
    public List<DocArchiveVo.FolderOrRecordNode> getFolderTree() {
        BizDocArchiveFolder query = new BizDocArchiveFolder();
        List<BizDocArchiveFolder> folderList = selectBizDocArchiveFolderList(query);
        List<DocArchiveVo.FolderOrRecordNode> nodeList = folderList.stream().map(item -> {
            DocArchiveVo.FolderOrRecordNode node = new DocArchiveVo.FolderOrRecordNode();
            node.setId(item.getFolderId());
            node.setLabel(item.getFolderName());
            node.setParentId(item.getParentId());
            node.setType("folder");
            node.setSort(item.getSort());
            node.setEnable(true);
            return node;
        }).collect(Collectors.toList());
        return TreeBuildUtil.buildTree(nodeList);
    }

    @Override
    public List<DocArchiveVo.FolderOrRecordNode> getSubFolder(String parentId) {
        BizDocArchiveFolder query = new BizDocArchiveFolder();
        query.setParentId(StringUtils.isBlank(parentId) ? "0" : parentId);
        List<BizDocArchiveFolder> folderList = selectBizDocArchiveFolderList(query);
        List<DocArchiveVo.FolderOrRecordNode> nodeList = folderList.stream().map(item -> {
            DocArchiveVo.FolderOrRecordNode node = new DocArchiveVo.FolderOrRecordNode();
            node.setId(item.getFolderId());
            node.setLabel(item.getFolderName());
            node.setParentId(item.getParentId());
            node.setType("folder");
            node.setSort(item.getSort());
            node.setEnable(true);
            return node;
        }).collect(Collectors.toList());
//        return TreeBuildUtil.buildTree(nodeList);
        return nodeList;
    }

    @Override
    public List<String> findSubChainIds(String folderId) {
        if(StringUtils.isBlank(folderId)){
            folderId = "0";
        }
        List<String> subChainIds = bizDocArchiveFolderMapper.findSubChainIds(folderId);
        subChainIds.add(0,folderId);
        return subChainIds;
    }
}
