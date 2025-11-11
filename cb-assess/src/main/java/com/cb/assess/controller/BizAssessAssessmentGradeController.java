package com.cb.assess.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cb.assess.domain.BizAssessAssessmentGrade;
import com.cb.assess.domain.dto.BizAssessAssessmentGradeDTO;
import com.cb.assess.domain.vo.BizAssessAssessmentGradeVo;
import com.cb.assess.service.IBizAssessAssessmentGradeService;
import com.cb.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
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
 * 机关参公和事业单位等次Controller
 * 
 * @author ruoyi
 * @date 2023-11-25
 */
@RestController
@RequestMapping("/assess/assessmentGrade")
public class BizAssessAssessmentGradeController extends BaseController
{
    @Autowired
    private IBizAssessAssessmentGradeService bizAssessAssessmentGradeService;

    /**
     * 查询机关参公和事业单位等次列表
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessAssessmentGradeVo bizAssessAssessmentGrade)
    {
        startPage();
        bizAssessAssessmentGrade.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        List<BizAssessAssessmentGradeVo> list = bizAssessAssessmentGradeService.selectBizAssessAssessmentGradeList(bizAssessAssessmentGrade);
        return getDataTable(list);
    }
    /**
     * 查询机关参公和事业单位等次列表(权限区分，这个是全部不分部门的数据)
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:all')")
    @GetMapping("/listAll")
    public TableDataInfo listAll(BizAssessAssessmentGradeVo bizAssessAssessmentGrade)
    {
        startPage();
        List<BizAssessAssessmentGradeVo> list = bizAssessAssessmentGradeService.selectBizAssessAssessmentGradeList(bizAssessAssessmentGrade);
        return getDataTable(list);
    }
    /**
     * 导出机关参公和事业单位等次列表
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:export')")
    @Log(title = "机关参公和事业单位等次", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessAssessmentGradeVo bizAssessAssessmentGrade)
    {
        List<BizAssessAssessmentGradeVo> list = bizAssessAssessmentGradeService.selectBizAssessAssessmentGradeList(bizAssessAssessmentGrade);
        ExcelUtil<BizAssessAssessmentGradeVo> util = new ExcelUtil<BizAssessAssessmentGradeVo>(BizAssessAssessmentGradeVo.class);
        return util.exportExcel(list, "grade");
    }

    /**
     * 获取机关参公和事业单位等次详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessAssessmentGradeService.selectBizAssessAssessmentGradeById(id));
    }

    /**
     * 新增机关参公和事业单位等次
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:add')")
    @Log(title = "机关参公和事业单位等次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessAssessmentGradeDTO bizAssessAssessmentGrade)
    {
        //先检查今年有没有录入过
        BizAssessAssessmentGradeVo bizAssessAssessmentGradeQuery = new BizAssessAssessmentGradeVo();
        bizAssessAssessmentGradeQuery.setAssessmentYear(bizAssessAssessmentGradeQuery.getAssessmentYear());
        bizAssessAssessmentGradeQuery.setDeptId(SecurityUtils.getOnlineDept().getDeptId());
        List<BizAssessAssessmentGradeVo> bizAssessAssessmentGrades = bizAssessAssessmentGradeService.selectBizAssessAssessmentGradeList(bizAssessAssessmentGradeQuery);
        Map<Long, BizAssessAssessmentGrade> collect = bizAssessAssessmentGrades.stream().collect(Collectors.toMap(BizAssessAssessmentGrade::getUserId, o -> o));
        String[] split = bizAssessAssessmentGrade.getUserIds().split(",");
        for (String s : split) {
            //不存在，再创建
            if (null==collect.get(Long.valueOf(s))){
                BizAssessAssessmentGrade bizAssessAssessmentGradeSave = new BizAssessAssessmentGrade();
                BeanUtils.copyProperties(bizAssessAssessmentGrade,bizAssessAssessmentGradeSave);
                bizAssessAssessmentGradeSave.setUserId(Long.valueOf(s));
                bizAssessAssessmentGradeSave.setDeptId(SecurityUtils.getOnlineDept().getDeptId());
                bizAssessAssessmentGradeService.insertBizAssessAssessmentGrade(bizAssessAssessmentGradeSave);
            }
        }
        return success("成功！！");
    }

    /**
     * 修改机关参公和事业单位等次
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:edit')")
    @Log(title = "机关参公和事业单位等次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessAssessmentGrade bizAssessAssessmentGrade)
    {
        return toAjax(bizAssessAssessmentGradeService.updateBizAssessAssessmentGrade(bizAssessAssessmentGrade));
    }

    /**
     * 删除机关参公和事业单位等次
     */
    @PreAuthorize("@ss.hasPermi('assess:assessmentGrade:remove')")
    @Log(title = "机关参公和事业单位等次", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessAssessmentGradeService.deleteBizAssessAssessmentGradeByIds(ids));
    }
}
