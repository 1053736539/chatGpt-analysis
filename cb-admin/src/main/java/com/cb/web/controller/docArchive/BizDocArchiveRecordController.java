package com.cb.web.controller.docArchive;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.docArchive.domain.BizDocArchiveRecord;
import com.cb.docArchive.service.IBizDocArchiveFolderService;
import com.cb.docArchive.service.IBizDocArchiveRecordService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 文书档案记录Controller
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
@RestController
@RequestMapping("/docArchive/record")
@Validated
public class BizDocArchiveRecordController extends BaseController
{
    @Autowired
    private IBizDocArchiveRecordService bizDocArchiveRecordService;

    @Autowired
    private IBizDocArchiveFolderService folderService;

    /**
     * 查询文书档案记录列表
     */
    @PreAuthorize("@ss.hasPermi('docArchive:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizDocArchiveRecord bizDocArchiveRecord)
    {
        startPage();
        List<BizDocArchiveRecord> list = bizDocArchiveRecordService.selectBizDocArchiveRecordList(bizDocArchiveRecord);
        return getDataTable(list);
    }

    @GetMapping("/listByFolder")
    public TableDataInfo listByFolder(BizDocArchiveRecord bizDocArchiveRecord){
        String folderId = bizDocArchiveRecord.getFolderId();
        List<String> folderIds = folderService.findSubChainIds(folderId);
        startPage();
        List<BizDocArchiveRecord> list = bizDocArchiveRecordService.listInFolderIds(bizDocArchiveRecord,folderIds);
        return getDataTable(list);
    }

    /**
     * 导出文书档案记录列表
     */
    @PreAuthorize("@ss.hasPermi('docArchive:record:export')")
    @Log(title = "文书档案记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizDocArchiveRecord bizDocArchiveRecord)
    {
        List<BizDocArchiveRecord> list = bizDocArchiveRecordService.selectBizDocArchiveRecordList(bizDocArchiveRecord);
        ExcelUtil<BizDocArchiveRecord> util = new ExcelUtil<BizDocArchiveRecord>(BizDocArchiveRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取文书档案记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('docArchive:record:query')")
    @GetMapping(value = "/{archiveNo}")
    public AjaxResult getInfo(@PathVariable("archiveNo") String archiveNo)
    {
        return AjaxResult.success(bizDocArchiveRecordService.selectBizDocArchiveRecordById(archiveNo));
    }

    /**
     * 新增文书档案记录
     */
    @PreAuthorize("@ss.hasPermi('docArchive:record:add')")
    @Log(title = "文书档案记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizDocArchiveRecord bizDocArchiveRecord)
    {
        try{
            return toAjax(bizDocArchiveRecordService.insertBizDocArchiveRecord(bizDocArchiveRecord));
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改文书档案记录
     */
    @PreAuthorize("@ss.hasPermi('docArchive:record:edit')")
    @Log(title = "文书档案记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizDocArchiveRecord bizDocArchiveRecord)
    {
        return toAjax(bizDocArchiveRecordService.updateBizDocArchiveRecord(bizDocArchiveRecord));
    }

    /**
     * 删除文书档案记录
     */
    @PreAuthorize("@ss.hasPermi('docArchive:record:remove')")
    @Log(title = "文书档案记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{archiveNos}")
    public AjaxResult remove(@PathVariable String[] archiveNos)
    {
        return toAjax(bizDocArchiveRecordService.deleteBizDocArchiveRecordByIds(archiveNos));
    }

    /**
     * 下载excel文书档案导入模板
     * @param response
     */
    @PostMapping("/downloadExcelTpl")
    public void downloadExcelTpl(HttpServletResponse response){
        ClassPathResource classPathResource = new ClassPathResource("static/excel/文书档案导入模板v2.xlsx");
        try(InputStream is = classPathResource.getInputStream()){
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("文书档案导入模板下载失败!");
        }
    }

    /**
     * 导入excel档案记录
     * @param folderId
     * @param file
     * @return
     */
    @PostMapping("/importByExcel")
    public AjaxResult importByExcel(HttpServletRequest request,HttpServletResponse response,
                                    @RequestParam(name = "folderId") String folderId){

        try {
            Part file = request.getPart("file");
            if(null == file){
                throw new CustomException("请选择上传文件");
            }
            InputStream is = file.getInputStream();
            String currUserName = SecurityUtils.getUsername();
            CompletableFuture.runAsync(()->{
                bizDocArchiveRecordService.importByExcel(folderId, is, currUserName);
            });
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

//        int count = bizDocArchiveRecordService.importByExcel(folderId, file);
//        return AjaxResult.success("导入成功,共导入" + count + "条数据。");
        return AjaxResult.success("后台导入中，请稍候刷新再试");
    }

    /**
     * 导入zip档案附件
     * @param file
     * @return
     */
    @PostMapping("/importAttachByZip/{folderId}")
    public AjaxResult importAttachByZip(HttpServletRequest request,HttpServletResponse response,
                                        @PathVariable("folderId") String folderId){
        try {
            Part file = request.getPart("file");
            if(null == file){
                throw new CustomException("请选择上传文件");
            }
            InputStream is = file.getInputStream();
            String currUserName = SecurityUtils.getUsername();
            CompletableFuture.runAsync(()->{
                bizDocArchiveRecordService.importAttachByZip(folderId, is, currUserName);
            });
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return AjaxResult.success("后台导入中，请稍候刷新再试");

//        int count = bizDocArchiveRecordService.importAttachByZip(folderId, file,currUserName);
//        return AjaxResult.success("导入成功,共导入" + count + "个附件");

    }

}
