package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessCadreDemocraticRecord;
import com.cb.assess.service.IBizAssessCadreDemocraticRecordService;
import com.cb.assess.vo.CadreDemocraticRecordVo;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 年度处级领导干部民主测评Controller
 * 
 * @author yangxin
 * @date 2023-11-25
 */
@RestController
@RequestMapping("/assess/cadreDemocraticRecord")
public class BizAssessCadreDemocraticRecordController extends BaseController
{
    @Autowired
    private IBizAssessCadreDemocraticRecordService bizAssessCadreDemocraticRecordService;

    /**
     * 查询年度处级领导干部民主测评列表
     */
    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        startPage();
        List<BizAssessCadreDemocraticRecord> list = bizAssessCadreDemocraticRecordService.selectBizAssessCadreDemocraticRecordList(bizAssessCadreDemocraticRecord);
        return getDataTable(list);
    }

    /**
     * 查询当前用户为评议人的记录
     * @param bizAssessCadreDemocraticRecord
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:list')")
    @GetMapping("/listMy")
    public TableDataInfo listMy(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        //查询自己为评议人的记录
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        bizAssessCadreDemocraticRecord.setVoteUserId(userId);
        startPage();
        List<BizAssessCadreDemocraticRecord> list = bizAssessCadreDemocraticRecordService.selectBizAssessCadreDemocraticRecordList(bizAssessCadreDemocraticRecord);
        return getDataTable(list);
    }

    /**
     * 导出年度处级领导干部民主测评列表
     */
    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:export')")
    @Log(title = "年度处级领导干部民主测评", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        List<BizAssessCadreDemocraticRecord> list = bizAssessCadreDemocraticRecordService.selectBizAssessCadreDemocraticRecordList(bizAssessCadreDemocraticRecord);
        ExcelUtil<BizAssessCadreDemocraticRecord> util = new ExcelUtil<BizAssessCadreDemocraticRecord>(BizAssessCadreDemocraticRecord.class);
        return util.exportExcel(list, "cadreDemocraticRecord");
    }

    /**
     * 获取年度处级领导干部民主测评详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessCadreDemocraticRecordService.selectBizAssessCadreDemocraticRecordById(id));
    }

    /**
     * 新增年度处级领导干部民主测评
     */
    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:add')")
    @Log(title = "年度处级领导干部民主测评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        return toAjax(bizAssessCadreDemocraticRecordService.insertBizAssessCadreDemocraticRecord(bizAssessCadreDemocraticRecord));
    }

    /**
     * 修改年度处级领导干部民主测评
     */
    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:edit')")
    @Log(title = "年度处级领导干部民主测评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessCadreDemocraticRecord bizAssessCadreDemocraticRecord)
    {
        return toAjax(bizAssessCadreDemocraticRecordService.updateBizAssessCadreDemocraticRecord(bizAssessCadreDemocraticRecord));
    }

    /**
     * 删除年度处级领导干部民主测评
     */
    @PreAuthorize("@ss.hasPermi('assess:cadreDemocraticRecord:remove')")
    @Log(title = "年度处级领导干部民主测评", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessCadreDemocraticRecordService.deleteBizAssessCadreDemocraticRecordByIds(ids));
    }

    /**
     * 提交评议记录
     * @param req
     * @return
     */
    @PostMapping("submit")
    public AjaxResult submit(@Valid @RequestBody CadreDemocraticRecordVo.SubmitReq req){
        bizAssessCadreDemocraticRecordService.submit(req);
        return AjaxResult.success();
    }

}
