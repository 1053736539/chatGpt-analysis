package com.cb.controller;

import cn.hutool.core.date.DateUtil;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.domain.TransferringStudentTalk;
import com.cb.service.ITransferringStudentTalkService;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 监督管理-谈心谈话Controller
 * 
 * @author yangxin
 * @date 2024-08-27
 */
@RestController
@RequestMapping("/transfer/talk")
public class TransferringStudentTalkController extends BaseController
{
    @Autowired
    private ITransferringStudentTalkService transferringStudentTalkService;

    /**
     * 查询监督管理-谈心谈话列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TransferringStudentTalk transferringStudentTalk)
    {
        Map<String, Object> params = transferringStudentTalk.getParams();
        if(null != params){
            params.entrySet().forEach(entry -> {
                if("null".equals(entry.getValue())){
                    entry.setValue(null);
                }
            });
        }
        startPage();
        List<TransferringStudentTalk> list = transferringStudentTalkService.selectTransferringStudentTalkList(transferringStudentTalk);
        return getDataTable(list);
    }

    /**
     * 导出监督管理-谈心谈话列表
     */
    @Log(title = "监督管理-谈心谈话", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransferringStudentTalk transferringStudentTalk)
    {
        List<TransferringStudentTalk> list = transferringStudentTalkService.selectTransferringStudentTalkList(transferringStudentTalk);
        ExcelUtil<TransferringStudentTalk> util = new ExcelUtil<TransferringStudentTalk>(TransferringStudentTalk.class);
        return util.exportExcel(list, "talk");
    }

    /**
     * 获取监督管理-谈心谈话详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(transferringStudentTalkService.selectTransferringStudentTalkById(id));
    }

    /**
     * 新增监督管理-谈心谈话
     */
    @Log(title = "监督管理-谈心谈话", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferringStudentTalk transferringStudentTalk)
    {
        return toAjax(transferringStudentTalkService.insertTransferringStudentTalk(transferringStudentTalk));
    }

    /**
     * 修改监督管理-谈心谈话
     */
    @Log(title = "监督管理-谈心谈话", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferringStudentTalk transferringStudentTalk)
    {
        return toAjax(transferringStudentTalkService.updateTransferringStudentTalk(transferringStudentTalk));
    }

    /**
     * 删除监督管理-谈心谈话
     */
    @Log(title = "监督管理-谈心谈话", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(transferringStudentTalkService.deleteTransferringStudentTalkByIds(ids));
    }

    /**
     * 导出单条记录word
     * @param id
     */
    @GetMapping("/exportWord/{id}")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, @PathVariable String id){
        try{
            TransferringStudentTalk transferringStudentTalk = transferringStudentTalkService.selectTransferringStudentTalkById(id);
            ClassPathResource classPathResource = new ClassPathResource("template/transferring_student_talk.docx");
            InputStream is = classPathResource.getInputStream();
            Configure configure = Configure.builder().build();

            String talkTime = transferringStudentTalk.getTalkTime();
            talkTime = DateUtil.format(DateUtil.parse(talkTime,"yyyy-MM-dd HH:mm"),"M月d日 H:mm");
            transferringStudentTalk.setTalkTime(talkTime);
            XWPFTemplate template = XWPFTemplate.compile(is, configure).render(transferringStudentTalk);
            template.writeAndClose(response.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
            throw new CustomException("导出word失败!");
        }
    }
}
