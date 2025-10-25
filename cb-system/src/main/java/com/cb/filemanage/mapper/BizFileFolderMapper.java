package com.cb.filemanage.mapper;

import java.util.List;
import java.util.Map;

import com.cb.common.enums.DeleteFlag;
import com.cb.filemanage.domain.BizFileFolder;
import org.apache.ibatis.annotations.Param;

/**
 * 业务文件夹Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface BizFileFolderMapper 
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


    public List<Map<String, Object>> selectBizFileFolderMapList(int delFlag);

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
     * 删除业务文件夹
     * 
     * @param id 业务文件夹ID
     * @return 结果
     */
    public int deleteBizFileFolderById(Long id);
    public int deleteBizFileFolderByFolderId(String folderId);
    /**
     * 批量删除业务文件夹
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizFileFolderByIds(Long[] ids);

    public int logicDeleteBizFileFolderByFolderId(@Param("folderId") String folderId, @Param("delFlag") int delFlag);

    public int hasChildren(String  folderId);
}
