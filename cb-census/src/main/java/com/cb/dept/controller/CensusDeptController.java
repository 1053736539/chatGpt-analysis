package com.cb.dept.controller;

import java.util.List;

import com.cb.common.constant.UserConstants;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.utils.SecurityUtils;
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
import com.cb.dept.domain.CensusDept;
import com.cb.dept.service.ICensusDeptService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 普查人员部门Controller
 * 
 * @author yangcd
 * @date 2023-11-11
 */
@RestController
@RequestMapping("/dept/censusDept")
public class CensusDeptController extends BaseController
{
    @Autowired
    private ICensusDeptService censusDeptService;

    /**
     * 查询普查人员部门列表
     */
    @PreAuthorize("@ss.hasPermi('dept:censusDept:list')")
    @GetMapping("/list")
    public TableDataInfo list(CensusDept censusDept)
    {
        startPage();
        List<CensusDept> list = censusDeptService.selectCensusDeptList(censusDept);
        return getDataTable(list);
    }

    /**
     * 导出普查人员部门列表
     */
    @PreAuthorize("@ss.hasPermi('dept:censusDept:export')")
    @Log(title = "普查人员部门", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CensusDept censusDept)
    {
        List<CensusDept> list = censusDeptService.selectCensusDeptList(censusDept);
        ExcelUtil<CensusDept> util = new ExcelUtil<CensusDept>(CensusDept.class);
        return util.exportExcel(list, "censusDept");
    }

    /**
     * 获取普查人员部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('dept:censusDept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return AjaxResult.success(censusDeptService.selectCensusDeptById(deptId));
    }

    /**
     * 新增普查人员部门
     */
    @PreAuthorize("@ss.hasPermi('dept:censusDept:add')")
    @Log(title = "普查人员部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CensusDept censusDept)
    {
        if (UserConstants.NOT_UNIQUE.equals(censusDeptService.checkDeptNameUnique(censusDept)))
        {
            return AjaxResult.error("新增部门'" + censusDept.getDeptName() + "'失败，部门名称已存在");
        }
        censusDept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(censusDeptService.insertCensusDept(censusDept));
//        return toAjax(censusDeptService.insertCensusDept(censusDept));
    }

    /**
     * 修改普查人员部门
     */
    @PreAuthorize("@ss.hasPermi('dept:censusDept:edit')")
    @Log(title = "普查人员部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CensusDept dept)
    {

        if (UserConstants.NOT_UNIQUE.equals(censusDeptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && censusDeptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(censusDeptService.updateCensusDept(dept));
//        return toAjax(censusDeptService.updateCensusDept(censusDept));
    }

    /**
     * 删除普查人员部门
     */
    @PreAuthorize("@ss.hasPermi('dept:censusDept:remove')")
    @Log(title = "普查人员部门", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId)
    {
//        return toAjax(censusDeptService.deleteCensusDeptByIds(deptIds));
        if (censusDeptService.hasChildByDeptId(deptId))
        {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
       /* if (censusDeptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.error("部门存在用户,不允许删除");
        }*/
        return toAjax(censusDeptService.deleteCensusDeptById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(CensusDept censusDept)
    {
        List<CensusDept> censusDepts = censusDeptService.selectCensusDeptList(censusDept);
        return AjaxResult.success(censusDeptService.buildDeptTreeSelect(censusDepts));
    }

    /**
     * @Auther: yangcd
     * @Date: 2024/8/30 10:30
     * @param file
     * @Description: 新增execl导入CensusDepts
     */

    @PreAuthorize("@ss.hasPermi('dept:censusDept:import')")
    @Log(title = "普查人员部门", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<CensusDept> util = new ExcelUtil<CensusDept>(CensusDept.class);
        List<CensusDept> censusDepts = util.importExcel(file.getInputStream());
        String message = censusDeptService.importCensusDepts(censusDepts);
        return AjaxResult.success(message);
    }

}
