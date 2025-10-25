package com.cb.web.controller.cadretrain;

import com.cb.cadretrain.domain.BizArchivesFileFolder;
import com.cb.cadretrain.domain.VArchivesFolderOrFile;
import com.cb.cadretrain.service.IBizArchivesFileFolderService;
import com.cb.cadretrain.service.IVArchivesFolderOrFileService;
import com.cb.common.annotation.Log;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.common.enums.DeleteFlag;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.file.FileUploadUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.service.IBizAttachService;
import com.cb.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件夹和视图Controller
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@RestController
@RequestMapping("/archives/file")
public class VArchivesFolderOrFileController extends BaseController {

    @Autowired
    private IBizAttachService bizAttachService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IVArchivesFolderOrFileService vArchivesFolderOrFileService;

    @Autowired
    private IBizArchivesFileFolderService bizArchivesFileFolderService;


    /**
     * 查询文件夹和视图列表
     */
    //@PreAuthorize("@ss.hasPermi('archives:file:list')")
    @GetMapping("/list")
    public AjaxResult list(VArchivesFolderOrFile vFolderOrFile) {
        List<VArchivesFolderOrFile> list = vArchivesFolderOrFileService.selectVArchivesFolderOrFileList(vFolderOrFile);
        return AjaxResult.success(list);
    }


    /**
     * 获取文件夹和视图详细信息
     */
    @PreAuthorize("@ss.hasPermi('archives:file:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(vArchivesFolderOrFileService.selectVFolderOrFileById(id));
    }


    /**
     * 新增业务文件夹
     */
    @PreAuthorize("@ss.hasPermi('archives:file:addFolder')")
    @Log(title = "新增业务文件夹", businessType = BusinessType.INSERT)
    @PostMapping("/addFolder")
    public AjaxResult addFolder(@RequestBody BizArchivesFileFolder bizFileFolder) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        bizFileFolder.setCreateBy(username);
        bizFileFolder.setFolderId(IdUtils.randomUUID());
        bizFileFolder.setDelFlag(DeleteFlag.NORMAL.getCode());
        return toAjax(bizArchivesFileFolderService.insertBizArchivesFileFolder(bizFileFolder));
    }
    /**
     * 新增业务文件
     */
    @PreAuthorize("@ss.hasPermi('archives:file:saveAttach')")
    @Log(title = "上传业务文件", businessType = BusinessType.INSERT)
    @PostMapping("saveAttach")
    public AjaxResult saveAttach(@RequestBody BizAttach bizAttach)
    {
        return toAjax(bizAttachService.addAttach(bizAttach));
    }

    /**
     * 重命名文件或文件夹
     */
    @PreAuthorize("@ss.hasPermi('archives:file:rename')")
    @Log(title = "重命名文件或文件夹", businessType = BusinessType.UPDATE)
    @PutMapping("/renameFolderOrFile")
    public AjaxResult renameFolderOrFile(@RequestBody VArchivesFolderOrFile vFolderOrFile) {
        return toAjax(vArchivesFolderOrFileService.renameFolderOrFile(vFolderOrFile));
    }

    /**
     * 删除业务文件夹或者文件
     */
    @PreAuthorize("@ss.hasPermi('archives:file:delFolderOrFile')")
    @Log(title = "删除业务文件夹或者文件(logic delete)", businessType = BusinessType.UPDATE)
    @PutMapping("/delFolderOrFile")
    public AjaxResult delFolderOrFile(@RequestBody VArchivesFolderOrFile vFolderOrFile) {
        return toAjax(vArchivesFolderOrFileService.delFolderOrFile(vFolderOrFile));
    }


    /**
     * 业务文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "业务文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/biz/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            Map<String, String> map = FileUploadUtils.originalUpload(filePath, file);
            String originName =map.get("oldName");
            String relativePath =map.get("path");
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", originName);
            ajax.put("url", relativePath);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
    @Log(title = "业务文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/biz/uploadFileSave")
    public AjaxResult uploadFileSave(MultipartFile file,String folderId) throws Exception {
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            Map<String, String> map = FileUploadUtils.originalUpload(filePath, file);
            String originName =map.get("oldName");
            String relativePath =map.get("path");
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", originName);
            ajax.put("url", relativePath);
            BizAttach bizAttach = new BizAttach();
            bizAttach.setPath(relativePath);
            bizAttach.setFileSize(file.getSize());
            bizAttach.setFolderId(folderId);
            bizAttach.setOldName(originName);
            bizAttachService.saveAttach(bizAttach);
            return AjaxResult.success(bizAttach);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
