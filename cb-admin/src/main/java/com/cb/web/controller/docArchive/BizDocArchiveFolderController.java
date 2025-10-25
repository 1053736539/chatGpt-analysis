package com.cb.web.controller.docArchive;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.docArchive.domain.BizDocArchiveFolder;
import com.cb.docArchive.service.IBizDocArchiveFolderService;
import com.cb.docArchive.vo.DocArchiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文书档案文件夹目录Controller
 * 
 * @author ruoyi
 * @date 2023-12-21
 */
@RestController
@RequestMapping("/docArchive/folder")
public class BizDocArchiveFolderController extends BaseController
{
    @Autowired
    private IBizDocArchiveFolderService bizDocArchiveFolderService;

    /**
     * 查询文书档案文件夹目录列表
     */
    @PreAuthorize("@ss.hasPermi('docArchive:folder:list')")
    @GetMapping("/list")
    public AjaxResult list(BizDocArchiveFolder bizDocArchiveFolder)
    {
        List<BizDocArchiveFolder> list = bizDocArchiveFolderService.selectBizDocArchiveFolderList(bizDocArchiveFolder);
        return AjaxResult.success(list);
    }

    /**
     * 导出文书档案文件夹目录列表
     */
    @PreAuthorize("@ss.hasPermi('docArchive:folder:export')")
    @Log(title = "文书档案文件夹目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizDocArchiveFolder bizDocArchiveFolder)
    {
        List<BizDocArchiveFolder> list = bizDocArchiveFolderService.selectBizDocArchiveFolderList(bizDocArchiveFolder);
        ExcelUtil<BizDocArchiveFolder> util = new ExcelUtil<BizDocArchiveFolder>(BizDocArchiveFolder.class);
        return util.exportExcel(list, "folder");
    }

    /**
     * 获取文书档案文件夹目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('docArchive:folder:query')")
    @GetMapping(value = "/{folderId}")
    public AjaxResult getInfo(@PathVariable("folderId") String folderId)
    {
        return AjaxResult.success(bizDocArchiveFolderService.selectBizDocArchiveFolderById(folderId));
    }

    /**
     * 新增文书档案文件夹目录
     */
    @PreAuthorize("@ss.hasPermi('docArchive:folder:add')")
    @Log(title = "文书档案文件夹目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizDocArchiveFolder bizDocArchiveFolder)
    {
        return toAjax(bizDocArchiveFolderService.insertBizDocArchiveFolder(bizDocArchiveFolder));
    }

    /**
     * 修改文书档案文件夹目录
     */
    @PreAuthorize("@ss.hasPermi('docArchive:folder:edit')")
    @Log(title = "文书档案文件夹目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizDocArchiveFolder bizDocArchiveFolder)
    {
        return toAjax(bizDocArchiveFolderService.updateBizDocArchiveFolder(bizDocArchiveFolder));
    }

    /**
     * 删除文书档案文件夹目录
     */
    @PreAuthorize("@ss.hasPermi('docArchive:folder:remove')")
    @Log(title = "文书档案文件夹目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{folderIds}")
    public AjaxResult remove(@PathVariable String[] folderIds)
    {
        return toAjax(bizDocArchiveFolderService.deleteBizDocArchiveFolderByIds(folderIds));
    }

    /**
     * 获取目录树结构数据
     * @return
     */
    @GetMapping("/getFolderTree")
    public AjaxResult getFolderTree(){
        List<DocArchiveVo.FolderOrRecordNode> tree = bizDocArchiveFolderService.getFolderTree();
        return AjaxResult.success(tree);
    }

    /**
     * 获取目录树结构数据
     * @return
     */
    @GetMapping("/getSubFolder")
    public AjaxResult getSubFolder(String parentId){
        List<DocArchiveVo.FolderOrRecordNode> folderTree = bizDocArchiveFolderService.getSubFolder(parentId);
        return AjaxResult.success(folderTree);
    }

}
