package com.cb.assess.controller;

import java.util.List;

import com.cb.assess.domain.BizAssessQuotaAllocation;
import com.cb.assess.domain.BizAssessQuotaAllocationUser;
import com.cb.assess.domain.dto.BizAssessQuotaAllocationDTO;
import com.cb.assess.domain.vo.BizAssessQuotaAllocationVo;
import com.cb.assess.service.IBizAssessQuotaAllocationService;
import com.cb.assess.service.IBizAssessQuotaAllocationUserService;
import com.cb.common.utils.StringUtils;
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
 * xxxx年度机关事业单位考核人数及优秀等次名额分配Controller
 * 
 * @author ruoyi
 * @date 2023-11-16
 */
@RestController
@RequestMapping("/assess/allocation")
public class BizAssessQuotaAllocationController extends BaseController
{
    @Autowired
    private IBizAssessQuotaAllocationService bizAssessQuotaAllocationService;
    @Autowired
    private IBizAssessQuotaAllocationUserService bizAssessQuotaAllocationUserService;

    /**
     * 查询xxxx年度机关事业单位考核人数及优秀等次名额分配列表
     */
    @PreAuthorize("@ss.hasPermi('assess:allocation:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessQuotaAllocationVo bizAssessQuotaAllocation)
    {
        startPage();
        List<BizAssessQuotaAllocationVo> list = bizAssessQuotaAllocationService.selectBizAssessQuotaAllocationList(bizAssessQuotaAllocation);
        return getDataTable(list);
    }

    /**
     * 导出xxxx年度机关事业单位考核人数及优秀等次名额分配列表
     */
    @PreAuthorize("@ss.hasPermi('assess:allocation:export')")
    @Log(title = "xxxx年度机关事业单位考核人数及优秀等次名额分配", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessQuotaAllocationVo bizAssessQuotaAllocation)
    {
        List<BizAssessQuotaAllocationVo> list = bizAssessQuotaAllocationService.selectBizAssessQuotaAllocationList(bizAssessQuotaAllocation);
        ExcelUtil<BizAssessQuotaAllocationVo> util = new ExcelUtil<BizAssessQuotaAllocationVo>(BizAssessQuotaAllocationVo.class);
        return util.exportExcel(list, "allocation");
    }

    /**
     * 获取xxxx年度机关事业单位考核人数及优秀等次名额分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:allocation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessQuotaAllocationService.selectBizAssessQuotaAllocationById(id));
    }

    /**
     * 新增xxxx年度机关事业单位考核人数及优秀等次名额分配
     */
    @PreAuthorize("@ss.hasPermi('assess:allocation:add')")
    @Log(title = "xxxx年度机关事业单位考核人数及优秀等次名额分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessQuotaAllocationDTO bizAssessQuotaAllocation)
    {
        BizAssessQuotaAllocationVo bizAssessQuotaAllocationQuery = new BizAssessQuotaAllocationVo();
        bizAssessQuotaAllocationQuery.setAssessmentYear(bizAssessQuotaAllocation.getAssessmentYear());
        List<BizAssessQuotaAllocationVo> bizAssessQuotaAllocationVos = bizAssessQuotaAllocationService.selectBizAssessQuotaAllocationList(bizAssessQuotaAllocationQuery);
        if (bizAssessQuotaAllocationVos.isEmpty()){
            bizAssessQuotaAllocationService.insertBizAssessQuotaAllocation(bizAssessQuotaAllocation);
            String allocationUsers = bizAssessQuotaAllocation.getAllocationUsers();
            if (StringUtils.isNoneBlank(allocationUsers)){
                String[] split = allocationUsers.split(",");
                for (String s : split) {
                    BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser = new BizAssessQuotaAllocationUser();
                    bizAssessQuotaAllocationUser.setUserId(Long.valueOf(s));
                    bizAssessQuotaAllocationUser.setQuotaAllocationId(bizAssessQuotaAllocation.getId());
                    bizAssessQuotaAllocationUserService.insertBizAssessQuotaAllocationUser(bizAssessQuotaAllocationUser);
                }
            }
            return success();
        }
        return error("当前年度已存在！！！");
    }

    /**
     * 修改xxxx年度机关事业单位考核人数及优秀等次名额分配
     */
    @PreAuthorize("@ss.hasPermi('assess:allocation:edit')")
    @Log(title = "xxxx年度机关事业单位考核人数及优秀等次名额分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessQuotaAllocationDTO bizAssessQuotaAllocation)
    {
        bizAssessQuotaAllocationService.updateBizAssessQuotaAllocation(bizAssessQuotaAllocation);
        String allocationUsers = bizAssessQuotaAllocation.getAllocationUsers();
        BizAssessQuotaAllocationUser bizAssessQuotaAllocationUserQuery = new BizAssessQuotaAllocationUser();
        bizAssessQuotaAllocationUserQuery.setQuotaAllocationId(bizAssessQuotaAllocation.getId());
        bizAssessQuotaAllocationUserService.deleteByQuery(bizAssessQuotaAllocationUserQuery);
        if (StringUtils.isNoneBlank(allocationUsers)){
            String[] split = allocationUsers.split(",");
            for (String s : split) {
                BizAssessQuotaAllocationUser bizAssessQuotaAllocationUser = new BizAssessQuotaAllocationUser();
                bizAssessQuotaAllocationUser.setUserId(Long.valueOf(s));
                bizAssessQuotaAllocationUser.setQuotaAllocationId(bizAssessQuotaAllocation.getId());
                bizAssessQuotaAllocationUserService.insertBizAssessQuotaAllocationUser(bizAssessQuotaAllocationUser);
            }
        }
        return  success();
    }

    /**
     * 删除xxxx年度机关事业单位考核人数及优秀等次名额分配
     */
    @PreAuthorize("@ss.hasPermi('assess:allocation:remove')")
    @Log(title = "xxxx年度机关事业单位考核人数及优秀等次名额分配", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessQuotaAllocationService.deleteBizAssessQuotaAllocationByIds(ids));
    }
}
