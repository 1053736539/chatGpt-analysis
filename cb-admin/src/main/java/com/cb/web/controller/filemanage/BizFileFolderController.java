package com.cb.web.controller.filemanage;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.cb.filemanage.domain.BizFileFolder;
import com.cb.filemanage.service.IBizFileFolderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;

/**
 * 业务文件夹Controller
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
@RestController
@RequestMapping("/filemanage/folder")
public class BizFileFolderController extends BaseController
{
    @Autowired
    private IBizFileFolderService bizFileFolderService;

    /**
     * 查询业务文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('filemanage:folder:list')")
    @GetMapping("/list")
    public AjaxResult list(BizFileFolder bizFileFolder)
    {
        List<BizFileFolder> list = bizFileFolderService.selectBizFileFolderList(bizFileFolder);
        return AjaxResult.success(list);
    }


    @GetMapping("/folderTree")
    public AjaxResult folderTree()
    {
        JSONArray jsonArray = bizFileFolderService.selectBizFileFolderJSONArray();
        return AjaxResult.success(jsonArray);
    }

    /**
     * 导出业务文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('filemanage:folder:export')")
    @Log(title = "业务文件夹", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizFileFolder bizFileFolder)
    {
        List<BizFileFolder> list = bizFileFolderService.selectBizFileFolderList(bizFileFolder);
        ExcelUtil<BizFileFolder> util = new ExcelUtil<BizFileFolder>(BizFileFolder.class);
        return util.exportExcel(list, "folder");
    }

    /**
     * 获取业务文件夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('filemanage:folder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizFileFolderService.selectBizFileFolderById(id));
    }

    /**
     * 新增业务文件夹
     */
    @PreAuthorize("@ss.hasPermi('filemanage:folder:add')")
    @Log(title = "业务文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizFileFolder bizFileFolder)
    {
        return toAjax(bizFileFolderService.insertBizFileFolder(bizFileFolder));
    }

    /**
     * 修改业务文件夹
     */
    @PreAuthorize("@ss.hasPermi('filemanage:folder:edit')")
    @Log(title = "业务文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizFileFolder bizFileFolder)
    {
        return toAjax(bizFileFolderService.updateBizFileFolder(bizFileFolder));
    }

    /**
     * 删除业务文件夹
     */
    @PreAuthorize("@ss.hasPermi('filemanage:folder:remove')")
    @Log(title = "业务文件夹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{folderId}")
    public AjaxResult remove(@PathVariable String  folderId)
    {
        if(bizFileFolderService.hasChildren(folderId)){
            return AjaxResult.error("该文件夹存在子集，禁止删除");
        }
        return toAjax(bizFileFolderService.deleteBizFileFolderByFolderId(folderId));
    }



}
