package com.cb.web.controller.cadretrain;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
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
import com.cb.cadretrain.domain.BizArchivesFileFolder;
import com.cb.cadretrain.service.IBizArchivesFileFolderService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

/**
 * 干部政治素质档案文件夹表Controller
 * 
 * @author yangcd
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/cadretrain/folder")
public class BizArchivesFileFolderController extends BaseController
{
    @Autowired
    private IBizArchivesFileFolderService bizArchivesFileFolderService;

    /**
     * 查询干部政治素质档案文件夹表列表
     */
    //@PreAuthorize("@ss.hasPermi('cadretrain:folder:list')")
    @GetMapping("/list1")
    public TableDataInfo list1(BizArchivesFileFolder bizArchivesFileFolder)
    {
        startPage();
        List<BizArchivesFileFolder> list = bizArchivesFileFolderService.selectBizArchivesFileFolderList(bizArchivesFileFolder);
        return getDataTable(list);
    }
    //@PreAuthorize("@ss.hasPermi('cadretrain:folder:list')")
    @GetMapping("/list")
    public AjaxResult list(BizArchivesFileFolder bizArchivesFileFolder)
    {
        List<BizArchivesFileFolder> list = bizArchivesFileFolderService.selectBizArchivesFileFolderList(bizArchivesFileFolder);
        return AjaxResult.success(list);
    }

    /**
     * 导出干部政治素质档案文件夹表列表
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:folder:export')")
    @Log(title = "干部政治素质档案文件夹表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizArchivesFileFolder bizArchivesFileFolder)
    {
        List<BizArchivesFileFolder> list = bizArchivesFileFolderService.selectBizArchivesFileFolderList(bizArchivesFileFolder);
        ExcelUtil<BizArchivesFileFolder> util = new ExcelUtil<BizArchivesFileFolder>(BizArchivesFileFolder.class);
        return util.exportExcel(list, "folder");
    }

    /**
     * 获取干部政治素质档案文件夹表详细信息
     */
    //@PreAuthorize("@ss.hasPermi('cadretrain:folder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(bizArchivesFileFolderService.selectBizArchivesFileFolderById(id));
    }

    /**
     * 新增干部政治素质档案文件夹表
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:folder:add')")
    @Log(title = "干部政治素质档案文件夹表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizArchivesFileFolder bizArchivesFileFolder)
    {
        return toAjax(bizArchivesFileFolderService.insertBizArchivesFileFolder(bizArchivesFileFolder));
    }

    /**
     * 修改干部政治素质档案文件夹表
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:folder:edit')")
    @Log(title = "干部政治素质档案文件夹表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizArchivesFileFolder bizArchivesFileFolder)
    {
        return toAjax(bizArchivesFileFolderService.updateBizArchivesFileFolder(bizArchivesFileFolder));
    }

    /**
     * 删除干部政治素质档案文件夹表
     */
   /* @PreAuthorize("@ss.hasPermi('cadretrain:folder:remove')")
    @Log(title = "干部政治素质档案文件夹表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(bizArchivesFileFolderService.deleteBizArchivesFileFolderByIds(ids));
    }*/

    @PreAuthorize("@ss.hasPermi('cadretrain:folder:remove')")
    @Log(title = "业务文件夹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{folderId}")
    public AjaxResult remove(@PathVariable String  folderId)
    {
        if(bizArchivesFileFolderService.hasChildren(folderId)){
            return AjaxResult.error("该文件夹存在子集，禁止删除");
        }
        return toAjax(bizArchivesFileFolderService.deleteBizFileFolderByFolderId(folderId));
    }

    /**
     * @Auther: yangcd
     * @Date: 2023/11/8 10:15

     * @Description:
     */
    @GetMapping("/folderTree")
    public AjaxResult folderTree()
    {
        JSONArray jsonArray = bizArchivesFileFolderService.selectBizFileFolderJSONArray();
        return AjaxResult.success(jsonArray);
    }


}
