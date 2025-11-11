package com.cb.assess.controller;

import com.cb.assess.domain.BizAccessQuotaAllocateInfo;
import com.cb.assess.service.IBizAccessQuotaAllocateInfoService;
import com.cb.assess.vo.QuotaAllocateInfoVo;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 年度机关事业单位考核人数及优秀等次名额分配信息Controller
 * 
 * @author yangxin
 * @date 2023-12-09
 */
@RestController
@RequestMapping("/assess/quotaAllocateInfo")
@Validated
public class BizAccessQuotaAllocateInfoController extends BaseController
{
    @Autowired
    private IBizAccessQuotaAllocateInfoService bizAccessQuotaAllocateInfoService;

    /**
     * 查询年度机关事业单位考核人数及优秀等次名额分配信息列表
     */
    @PreAuthorize("@ss.hasPermi('assess:quotaAllocateInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        startPage();
        List<BizAccessQuotaAllocateInfo> list = bizAccessQuotaAllocateInfoService.selectBizAccessQuotaAllocateInfoList(bizAccessQuotaAllocateInfo);
        return getDataTable(list);
    }

    /**
     * 导出年度机关事业单位考核人数及优秀等次名额分配信息列表
     */
    @PreAuthorize("@ss.hasPermi('assess:quotaAllocateInfo:export')")
    @Log(title = "年度机关事业单位考核人数及优秀等次名额分配信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        List<BizAccessQuotaAllocateInfo> list = bizAccessQuotaAllocateInfoService.selectBizAccessQuotaAllocateInfoList(bizAccessQuotaAllocateInfo);
        ExcelUtil<BizAccessQuotaAllocateInfo> util = new ExcelUtil<BizAccessQuotaAllocateInfo>(BizAccessQuotaAllocateInfo.class);
        return util.exportExcel(list, "quotaAllocateInfo");
    }

    /**
     * 获取年度机关事业单位考核人数及优秀等次名额分配信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:quotaAllocateInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAccessQuotaAllocateInfoService.selectBizAccessQuotaAllocateInfoById(id));
    }

    /**
     * 新增年度机关事业单位考核人数及优秀等次名额分配信息
     */
    @PreAuthorize("@ss.hasPermi('assess:quotaAllocateInfo:add')")
    @Log(title = "年度机关事业单位考核人数及优秀等次名额分配信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        return toAjax(bizAccessQuotaAllocateInfoService.insertBizAccessQuotaAllocateInfo(bizAccessQuotaAllocateInfo));
    }

    /**
     * 修改年度机关事业单位考核人数及优秀等次名额分配信息
     */
    @PreAuthorize("@ss.hasPermi('assess:quotaAllocateInfo:edit')")
    @Log(title = "年度机关事业单位考核人数及优秀等次名额分配信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        return toAjax(bizAccessQuotaAllocateInfoService.updateBizAccessQuotaAllocateInfo(bizAccessQuotaAllocateInfo));
    }

    /**
     * 删除年度机关事业单位考核人数及优秀等次名额分配信息
     */
    @PreAuthorize("@ss.hasPermi('assess:quotaAllocateInfo:remove')")
    @Log(title = "年度机关事业单位考核人数及优秀等次名额分配信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAccessQuotaAllocateInfoService.deleteBizAccessQuotaAllocateInfoByIds(ids));
    }

    /**
     * 获取默认的表格
     * @return
     */
    @PostMapping("getDefaultTable")
    public AjaxResult getDefaultTable(){
        List<QuotaAllocateInfoVo.TableItemInfo> list = bizAccessQuotaAllocateInfoService.getDefaultTable();
        return AjaxResult.success(list);
    }

    /**
     * 获取详情信息
     * @param id
     * @return
     */
    @PostMapping("detail/{id}")
    public AjaxResult detail(@PathVariable("id") String id){
        BizAccessQuotaAllocateInfo info = bizAccessQuotaAllocateInfoService.getDetailInfo(id);
        return AjaxResult.success(info);
    }

    /**
     * 审核
     * @param info
     * @return
     */
    @PostMapping("audit")
    public AjaxResult audit(@RequestBody BizAccessQuotaAllocateInfo info){
        bizAccessQuotaAllocateInfoService.audit(info);
        return AjaxResult.success("操作成功");
    }

    /**
     * 重新提交审核
     * @param id
     * @return
     */
    @PostMapping("reSubmit/{id}")
    public AjaxResult reSubmit(@PathVariable("id") String id){
        bizAccessQuotaAllocateInfoService.reSubmit(id);
        return AjaxResult.success("操作成功");
    }

    /**
     * 获取指定部门指定年度的名额数量
     * @param year
     * @return
     */
    @GetMapping("getDeptAllocateInfo")
    public AjaxResult getDeptAllocateInfo(@NotBlank String year){
        Long deptId = SecurityUtils.getOnlineDept().getDeptId();
        QuotaAllocateInfoVo.TableItemInfo item = bizAccessQuotaAllocateInfoService.getDeptAllocateInfo(deptId, year);
        return AjaxResult.success(item);
    }

    /**
     * 获取二级巡视员总师等的优秀名额
     * @param year
     * @return
     */
    @GetMapping("getNoDeptAllocateInfo")
    public AjaxResult getNoDeptAllocateInfo(@NotBlank String year,@NotBlank String type){
        QuotaAllocateInfoVo.TableItemInfo item = bizAccessQuotaAllocateInfoService.getNoDeptAllocateInfo(year,type);
        return AjaxResult.success(item);
    }

    /**
     * 发布到公告
     * @param id
     * @return
     */
    @PostMapping("publish2Notice/{id}")
    public AjaxResult publish2Notice(@PathVariable("id") String id){
        bizAccessQuotaAllocateInfoService.publish2Notice(id);
        return AjaxResult.success("发布成功");
    }

    /**
     * 撤销公告
     * @param id
     * @return
     */
    @PostMapping("revokeNotice/{id}")
    public AjaxResult revokeNotice(@PathVariable("id") String id){
        bizAccessQuotaAllocateInfoService.revokeNotice(id);
        return AjaxResult.success("撤回成功");
    }

}
