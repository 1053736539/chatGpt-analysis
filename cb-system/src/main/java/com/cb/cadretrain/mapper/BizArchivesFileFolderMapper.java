package com.cb.cadretrain.mapper;

import java.util.List;
import java.util.Map;

import com.cb.cadretrain.domain.BizArchivesFileFolder;
import com.cb.filemanage.domain.BizFileFolder;
import org.apache.ibatis.annotations.Param;

/**
 * 干部政治素质档案文件夹表Mapper接口
 * 
 * @author yangcd
 * @date 2023-11-07
 */
public interface BizArchivesFileFolderMapper 
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
     * 删除干部政治素质档案文件夹表
     * 
     * @param id 干部政治素质档案文件夹表ID
     * @return 结果
     */
    public int deleteBizArchivesFileFolderById(Integer id);

    /**
     * 批量删除干部政治素质档案文件夹表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizArchivesFileFolderByIds(Integer[] ids);

    /**
     * @Auther: yangcd
     * @Date: 2023/11/8 10:16
     */
    public List<Map<String, Object>> selectBizFileFolderMapList(int delFlag);

    public int hasChildren(String  folderId);

    public int deleteBizFileFolderByFolderId(String folderId);

    public int logicDeleteBizFileFolderByFolderId(@Param("folderId") String folderId, @Param("delFlag") int delFlag);

    public int updateBizFileFolderByFolderId(BizArchivesFileFolder bizFileFolder);
}
