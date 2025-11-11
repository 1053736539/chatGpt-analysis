package com.cb.assess.controller;

import java.util.List;

import com.cb.assess.domain.BizAssessQuotaAllocationUser;
import com.cb.assess.service.IBizAssessQuotaAllocationUserService;
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
import com.cb.common.core.page.TableDataInfo;

/**
 * 优秀名额分配单位人员名单Controller
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
@RestController
@RequestMapping("/assess/allocationUser")
public class BizAssessQuotaAllocationUserController extends BaseController
{
    @Autowired
    private IBizAssessQuotaAllocationUserService bizAssessQuotaAllocationUserService;

    /**
     * 查询优秀名额分配单位人员名单列表
     */
    @GetMapping("/list")
    public AjaxResult list(BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser)
    {
        List<BizAssessQuotaAllocationUser> list = bizAssessQuotaAllocationUserService.selectBizAssessQuotaAllocationUserList(bizAssessQuotaAllocationUser);
        return AjaxResult.success(list);
    }

    /**
     * 新增优秀名额分配单位人员名单
     */
    @Log(title = "优秀名额分配单位人员名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser)
    {
        return toAjax(bizAssessQuotaAllocationUserService.insertBizAssessQuotaAllocationUser(bizAssessQuotaAllocationUser));
    }

    /**
     * 删除优秀名额分配单位人员名单
     */
    @PreAuthorize("@ss.hasPermi('assess:user:remove')")
    @Log(title = "优秀名额分配单位人员名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        return toAjax(bizAssessQuotaAllocationUserService.deleteBizAssessQuotaAllocationUserByIds(userIds));
    }
}
