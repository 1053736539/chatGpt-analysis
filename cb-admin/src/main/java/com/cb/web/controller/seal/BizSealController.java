package com.cb.web.controller.seal;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.seal.domain.BizSeal;
import com.cb.seal.service.IBizSealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公章信息Controller
 * 
 * @author yangxin
 * @date 2023-12-04
 */
@RestController
@RequestMapping("/seal/seal")
public class BizSealController extends BaseController
{
    @Autowired
    private IBizSealService bizSealService;

    /**
     * 查询公章信息列表
     */
    @PreAuthorize("@ss.hasPermi('seal:seal:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizSeal bizSeal)
    {
        startPage();
        List<BizSeal> list = bizSealService.selectBizSealList(bizSeal);
        return getDataTable(list);
    }

    /**
     * 查询当前用户创建的公章信息列表
     */
    //@PreAuthorize("@ss.hasPermi('seal:seal:list')")
    @GetMapping("/listMyCreate")
    public TableDataInfo listMyCreate(BizSeal bizSeal)
    {
        bizSeal.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<BizSeal> list = bizSealService.selectBizSealList(bizSeal);
        return getDataTable(list);
    }

    /**
     * 获取当前用户可用的印章列表
     * @return
     */
    @GetMapping("/listMy")
    public AjaxResult listMy(){
        String userName = SecurityUtils.getUsername();
        BizSeal query = new BizSeal();
        List<BizSeal> myList = bizSealService.selectBizSealList(query).stream().filter(item->{
            String scope = item.getScope();
            if(StringUtils.isBlank(scope)){
                return false;
            }
            boolean containFlag = false;
            for(String i: scope.split(",")){
                if(i.equals(userName)){
                    containFlag = true;
                    break;
                }
            }
            return containFlag;

        }).collect(Collectors.toList());
        return AjaxResult.success(myList);
    }


    /**
     * 导出公章信息列表
     */
    @PreAuthorize("@ss.hasPermi('seal:seal:export')")
    @Log(title = "公章信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizSeal bizSeal)
    {
        List<BizSeal> list = bizSealService.selectBizSealList(bizSeal);
        ExcelUtil<BizSeal> util = new ExcelUtil<BizSeal>(BizSeal.class);
        return util.exportExcel(list, "seal");
    }

    /**
     * 获取公章信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('seal:seal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizSealService.selectBizSealById(id));
    }

    /**
     * 新增公章信息
     */
    @PreAuthorize("@ss.hasPermi('seal:seal:add')")
    @Log(title = "公章信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizSeal bizSeal)
    {
        return toAjax(bizSealService.insertBizSeal(bizSeal));
    }

    /**
     * 修改公章信息
     */
    @PreAuthorize("@ss.hasPermi('seal:seal:edit')")
    @Log(title = "公章信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizSeal bizSeal)
    {
        return toAjax(bizSealService.updateBizSeal(bizSeal));
    }

    /**
     * 删除公章信息
     */
    @PreAuthorize("@ss.hasPermi('seal:seal:remove')")
    @Log(title = "公章信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizSealService.deleteBizSealByIds(ids));
    }
}
