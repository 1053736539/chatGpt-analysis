package com.cb.docArchive.service.impl;

import com.cb.common.config.RuoYiConfig;
import com.cb.common.enums.DeleteFlag;
import com.cb.common.exception.CustomException;
import com.cb.common.exception.file.InvalidExtensionException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.file.FileUploadUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.docArchive.domain.BizDocArchiveRecord;
import com.cb.docArchive.mapper.BizDocArchiveRecordMapper;
import com.cb.docArchive.service.IBizDocArchiveRecordService;
import com.cb.docArchive.vo.DocArchiveVo;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.service.impl.BizAttachServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文书档案记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
@Service
public class BizDocArchiveRecordServiceImpl implements IBizDocArchiveRecordService 
{
    @Autowired
    private BizDocArchiveRecordMapper bizDocArchiveRecordMapper;

    @Autowired
    private BizAttachServiceImpl bizAttachService;

    /**
     * 查询文书档案记录
     * 
     * @param archiveNo 文书档案记录ID
     * @return 文书档案记录
     */
    @Override
    public BizDocArchiveRecord selectBizDocArchiveRecordById(String archiveNo)
    {
        return bizDocArchiveRecordMapper.selectBizDocArchiveRecordById(archiveNo);
    }

    /**
     * 查询文书档案记录列表
     * 
     * @param bizDocArchiveRecord 文书档案记录
     * @return 文书档案记录
     */
    @Override
    public List<BizDocArchiveRecord> selectBizDocArchiveRecordList(BizDocArchiveRecord bizDocArchiveRecord)
    {
        return bizDocArchiveRecordMapper.selectBizDocArchiveRecordList(bizDocArchiveRecord);
    }

    /**
     * 查询文件夹下（在folderIds下）的所有文件，用于查找子孙级目录下
     * @param bizDocArchiveRecord
     * @param folderIds
     * @return
     */
    @Override
    public List<BizDocArchiveRecord> listInFolderIds(BizDocArchiveRecord bizDocArchiveRecord, List<String> folderIds) {
        bizDocArchiveRecord.setFolderIds(folderIds);
        return bizDocArchiveRecordMapper.listInFolderIds(bizDocArchiveRecord);
    }

    /**
     * 新增文书档案记录
     * 
     * @param bizDocArchiveRecord 文书档案记录
     * @return 结果
     */
    @Override
    public int insertBizDocArchiveRecord(BizDocArchiveRecord bizDocArchiveRecord)
    {
        if(StringUtils.isBlank(bizDocArchiveRecord.getCreateBy())){
            try{
                bizDocArchiveRecord.setCreateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }
        bizDocArchiveRecord.setCreateTime(DateUtils.getNowDate());
        return bizDocArchiveRecordMapper.insertBizDocArchiveRecord(bizDocArchiveRecord);
    }

    /**
     * 修改文书档案记录
     * 
     * @param bizDocArchiveRecord 文书档案记录
     * @return 结果
     */
    @Override
    public int updateBizDocArchiveRecord(BizDocArchiveRecord bizDocArchiveRecord)
    {
        if(StringUtils.isBlank(bizDocArchiveRecord.getUpdateBy())){
            try{
                bizDocArchiveRecord.setUpdateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }

        bizDocArchiveRecord.setUpdateTime(DateUtils.getNowDate());
        return bizDocArchiveRecordMapper.updateBizDocArchiveRecord(bizDocArchiveRecord);
    }

    /**
     * 批量删除文书档案记录
     * 
     * @param archiveNos 需要删除的文书档案记录ID
     * @return 结果
     */
    @Override
    public int deleteBizDocArchiveRecordByIds(String[] archiveNos)
    {
        return bizDocArchiveRecordMapper.deleteBizDocArchiveRecordByIds(archiveNos);
    }

    /**
     * 删除文书档案记录信息
     * 
     * @param archiveNo 文书档案记录ID
     * @return 结果
     */
    @Override
    public int deleteBizDocArchiveRecordById(String archiveNo)
    {
        return bizDocArchiveRecordMapper.deleteBizDocArchiveRecordById(archiveNo);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int importByExcel(String folderId, InputStream is,String currUserName) {
        int count = 0;
        ExcelUtil<DocArchiveVo.RecordImportVo> util = new ExcelUtil<>(DocArchiveVo.RecordImportVo.class);
        util.setHeadRowIndex(1);
        try {
            List<DocArchiveVo.RecordImportVo> list = util.importExcel(is);
            List<BizDocArchiveRecord> recordList = list.stream().filter(item -> StringUtils.isNotBlank(item.getArchiveNo())).map(item -> {
                String archiveNo = item.getArchiveNo();
                // Z081-WS·2022-永久-举报情况说明-0001
                String[] split = archiveNo.split("[-·]");
                if (split.length < 6) {
                    throw new CustomException("导入失败，档号格式错误：" + archiveNo);
                }
                String fondsNo = split[0];
                String archiveKindCode = split[1];
                String archiveYear = split[2];
                String retentionPeriod = split[3];
                String organizationProblem = split[4];
                String partNo = split[split.length - 1];

                BizDocArchiveRecord record = new BizDocArchiveRecord();
                record.setArchiveNo(archiveNo);
                record.setFondsNo(fondsNo);
                record.setArchiveKindCode(archiveKindCode);
                record.setArchiveYear(archiveYear);
                record.setRetentionPeriod(retentionPeriod);
                record.setOrganizationProblem(organizationProblem);
                record.setPartNo(partNo);
                record.setResponsibility(item.getResponsibility());
                record.setTitle(item.getTitle());
                record.setFileNo(item.getFileNo());
                record.setFileDate(item.getFileDate());
                record.setFilePage(item.getFilePage());
                record.setSecretLevel(item.getSecretLevel());
                record.setFolderId(folderId);
                record.setRemark(item.getRemark());
                return record;
            }).collect(Collectors.toList());

            for (int i = 0; i < recordList.size(); i++) {
                BizDocArchiveRecord item = recordList.get(i);
                BizDocArchiveRecord record = selectBizDocArchiveRecordById(item.getArchiveNo());
                if(null == record){
                    item.setCreateBy(currUserName);
                    count += insertBizDocArchiveRecord(item);
                } else {
                    record.setUpdateBy(currUserName);
                    updateBizDocArchiveRecord(item);
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("解析excel文件写入数据库时发生错误!" + e.getMessage());
        } finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //文书档案的附件文件夹id
    public static final String ARCHIVE_FILE_FOLDER_ID = "a6ae8ca1-57a4-4af2-b459-63d337e9970a";

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int importAttachByZip(String folderId, InputStream is, String currUserName) {
        int i = 0;
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(is, Charset.forName("GBK"));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                try {
                    if(entry.isDirectory()){

                    } else {
                        String filePath = entry.getName();
                        int lastIndex = filePath.lastIndexOf("/");
                        String parentPath = filePath.substring(0,lastIndex);
                        String fileName = filePath.substring(lastIndex + 1);
                        // Z081-WS·2022-永久-举报情况说明-0001
                        String archiveNo = "Z081-" + parentPath.replace("/","-");
                        BizDocArchiveRecord exist = selectBizDocArchiveRecordById(archiveNo);
                        // 非空且附件id已有的就跳过了
                        if(exist != null && StringUtils.isNotBlank(exist.getAttachId())){
                            continue;
                        }
                        //System.out.println(archiveNo);
                        String baseDir = RuoYiConfig.getUploadPath();
                        Map<String,Object> map = FileUploadUtils.uploadUnClose(baseDir,fileName,zis);
                        String originName = (String) map.get("oldName");
                        String relativePath = (String) map.get("path");
                        long size = (long) map.get("size");

                        String attachId = IdUtils.randomUUID();
                        BizAttach bizAttach = new BizAttach();
                        bizAttach.setAttachId(attachId);
                        bizAttach.setPath(relativePath);
                        bizAttach.setFileSize(size);
                        bizAttach.setFolderId(ARCHIVE_FILE_FOLDER_ID);
                        bizAttach.setOldName(originName);
                        String oldName = bizAttach.getOldName();
                        String extName = FilenameUtils.getExtension(oldName);
                        bizAttach.setExtName(extName);
                        String path = bizAttach.getPath();
                        String newName = path.substring(path.lastIndexOf("/")+1);
                        bizAttach.setNewName(newName);
                        bizAttach.setDelFlag(DeleteFlag.NORMAL.getCode());
                        bizAttach.setVersion("1");
                        bizAttach.setCreateBy(currUserName);
                        bizAttach.setCreateTime(DateUtils.getNowDate());
                        bizAttachService.insertBizAttach(bizAttach);

                        BizDocArchiveRecord record = new BizDocArchiveRecord();
                        record.setArchiveNo(archiveNo);
                        record.setAttachId(attachId);
                        updateBizDocArchiveRecord(record);
                        i++;
                    }
                } catch (InvalidExtensionException e) {
                    e.printStackTrace();
                } finally {
                    zis.closeEntry(); // 关闭zipEntry
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null != zis){
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return i;
    }
}
