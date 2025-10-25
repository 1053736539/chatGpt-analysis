package com.cb.docArchive.mapper;

import com.cb.docArchive.domain.BizDocArchiveRecord;

import java.util.List;

/**
 * 文书档案记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
public interface BizDocArchiveRecordMapper 
{
    /**
     * 查询文书档案记录
     * 
     * @param archiveNo 文书档案记录ID
     * @return 文书档案记录
     */
    public BizDocArchiveRecord selectBizDocArchiveRecordById(String archiveNo);

    /**
     * 查询文书档案记录列表
     * 
     * @param bizDocArchiveRecord 文书档案记录
     * @return 文书档案记录集合
     */
    public List<BizDocArchiveRecord> selectBizDocArchiveRecordList(BizDocArchiveRecord bizDocArchiveRecord);

    /**
     * 查询文件夹下（在folderIds下）的所有文件，用于查找子孙级目录下
     * @param bizDocArchiveRecord
     * @return
     */
    List<BizDocArchiveRecord> listInFolderIds(BizDocArchiveRecord bizDocArchiveRecord);

    /**
     * 新增文书档案记录
     * 
     * @param bizDocArchiveRecord 文书档案记录
     * @return 结果
     */
    public int insertBizDocArchiveRecord(BizDocArchiveRecord bizDocArchiveRecord);

    /**
     * 修改文书档案记录
     * 
     * @param bizDocArchiveRecord 文书档案记录
     * @return 结果
     */
    public int updateBizDocArchiveRecord(BizDocArchiveRecord bizDocArchiveRecord);

    /**
     * 删除文书档案记录
     * 
     * @param archiveNo 文书档案记录ID
     * @return 结果
     */
    public int deleteBizDocArchiveRecordById(String archiveNo);

    /**
     * 批量删除文书档案记录
     * 
     * @param archiveNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizDocArchiveRecordByIds(String[] archiveNos);
}
