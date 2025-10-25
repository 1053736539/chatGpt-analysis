package com.cb.web.controller.filemanage;

import com.cb.common.annotation.Log;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.filemanage.domain.BizAttach;
import com.cb.filemanage.service.IBizAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.cb.common.constant.Constants.RESOURCE_PREFIX;

/**
 * 业务文件Controller
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@RestController
@RequestMapping("/filemanage/attach")
public class BizAttachController extends BaseController
{
    @Autowired
    private IBizAttachService bizAttachService;

    /**
     * 查询业务文件列表
     */
    @PreAuthorize("@ss.hasPermi('filemanage:attach:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAttach bizAttach)
    {
        startPage();
        List<BizAttach> list = bizAttachService.selectBizAttachList(bizAttach);
        return getDataTable(list);
    }
    /**
     * 查询文件夹和视图列表
     */
//    @PreAuthorize("@ss.hasPermi('filemanage:file:list')")
    @GetMapping("/listByIds")
    public AjaxResult listByIds(String ids) {

        List<BizAttach> list = bizAttachService.selectVFolderOrFileListByIds(ids);
        return AjaxResult.success(list);
    }
    /**
     * 导出业务文件列表
     */
    @PreAuthorize("@ss.hasPermi('filemanage:attach:export')")
    @Log(title = "业务文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAttach bizAttach)
    {
        List<BizAttach> list = bizAttachService.selectBizAttachList(bizAttach);
        ExcelUtil<BizAttach> util = new ExcelUtil<BizAttach>(BizAttach.class);
        return util.exportExcel(list, "attach");
    }

    /**
     * 获取业务文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('filemanage:attach:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizAttachService.selectBizAttachById(id));
    }

    /**
     * 新增业务文件
     */
    @PreAuthorize("@ss.hasPermi('filemanage:attach:add')")
    @Log(title = "业务文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAttach bizAttach)
    {
        return toAjax(bizAttachService.insertBizAttach(bizAttach));
    }

    /**
     * 修改业务文件
     */
    @PreAuthorize("@ss.hasPermi('filemanage:attach:edit')")
    @Log(title = "业务文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAttach bizAttach)
    {
        return toAjax(bizAttachService.updateBizAttach(bizAttach));
    }

    /**
     * 删除业务文件
     */
    @PreAuthorize("@ss.hasPermi('filemanage:attach:remove')")
    @Log(title = "业务文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizAttachService.deleteBizAttachByIds(ids));
    }

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,String id,boolean previewPdf){
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            BizAttach bizAttach = bizAttachService.selectBizAttachByAttachId(id);
            if (null==bizAttach)return;
            String path=filePath+bizAttach.getPath().replace(RESOURCE_PREFIX+"/upload","");
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream inputStream = Files.newInputStream(Paths.get(path));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
            // 清空response
            response.reset();
//            InputStream inputStream = new ByteArrayInputStream(fileVo.getfRawData());
            try {
                response.reset();
                if(previewPdf){
                    response.setContentType("application/pdf");
                } else {
                    response.setContentType("application/octet-stream");
                    String oldName = bizAttach.getOldName();
                    if(StringUtils.isNotEmpty(oldName)) {
                        oldName = new String(oldName.getBytes("utf-8"), "ISO8859-1");
                    }
                    response.setHeader("Content-Disposition", "attachment;filename=" + oldName);
                }
                response.setCharacterEncoding("utf-8");
//            response.setContentLength((int) file.length());

                try (BufferedInputStream bis = new BufferedInputStream(inputStream);) {
                    byte[] buff = new byte[1024];
                    OutputStream os = response.getOutputStream();
                    int i = 0;
                    while ((i = bis.read(buff)) != -1) {
                        os.write(buff, 0, i);
                        os.flush();
                    }
                } catch (IOException e) {
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
