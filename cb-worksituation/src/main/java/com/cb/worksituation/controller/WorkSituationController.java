package com.cb.worksituation.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.ServletUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.framework.web.service.TokenService;
import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.service.IWorkSituationService;
import com.cb.worksituation.vo.WorkSituationImportVo;
import com.cb.worksituation.vo.WorkSituationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 考勤统计（上班情况）Controller
 *
 * @author ruoyi
 * @date 2023-11-15
 */
@RestController
@RequestMapping("/work/situation")
public class WorkSituationController extends BaseController
{
    @Autowired
    private IWorkSituationService workSituationService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询考勤统计（上班情况）列表
     */
//    @PreAuthorize("@ss.hasPermi('work:situation:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkSituationVo workSituation)
    {
        startPage();
        List<WorkSituationVo> list = workSituationService.selectWorkSituationList(workSituation);
        return getDataTable(list);
    }
    /**
     * 查询考勤统计（上班情况）列表
     */
//    @PreAuthorize("@ss.hasPermi('work:situation:list')")
    @GetMapping("/listBySelf")
    public TableDataInfo listBySelf(WorkSituationVo workSituation)
    {
        startPage();
        workSituation.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<WorkSituationVo> list = workSituationService.selectWorkSituationList(workSituation);
        return getDataTable(list);
    }
    @GetMapping("/listByPublicity")
    public TableDataInfo listByPublicity(WorkSituationVo workSituation)
    {
        startPage();
        workSituation.setIsPublicity("1");
        List<String> situationMonths = workSituation.getSituationMonths();
        List<String> situationYears = workSituation.getSituationYears();
        if (null!=situationMonths){
            workSituation.setSituationMonths(situationMonths.stream().distinct().collect(Collectors.toList()));
        }
        if (null!=situationYears){
            workSituation.setSituationYears(situationYears.stream().distinct().collect(Collectors.toList()));
        }
        List<WorkSituationVo> list = workSituationService.selectListByPublicity(workSituation);
        return getDataTable(list);
    }

    @PostMapping("/toPublicity")
    public AjaxResult toPublicity(@RequestBody WorkSituationVo workSituation)
    {
        workSituationService.toPublicity(workSituation);
        return success();
    }
    /**
     * 同步考勤
     */
    @PreAuthorize("@ss.hasPermi('work:situation:syncClock')")
    @GetMapping("/syncClock")
    public AjaxResult syncClock(WorkSituationVo workSituation)
    {
        String o = redisTemplate.opsForValue().get("syncClock");
        if (StringUtils.isBlank(o)){
            redisTemplate.opsForValue().set("syncClock","1",100000);
            Thread asyncThread = new Thread(){
                @Override
                public void run() {
                    workSituationService.synchronousClock(workSituation.getSituationYear(), workSituation.getSituationMonth());
                    redisTemplate.delete("syncClock");
                }
            };
            asyncThread.start();
            return success("开始同步，请稍后");
        }
        return error("正在同步中，请稍后！！");
    }

    /**
     * 查询统计饼图数据（数据为每个字段根据查询条件的和）
     * @param workSituationVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('work:situation:list')")
    @GetMapping("/selectPieVo")
    public AjaxResult selectPieVo(WorkSituationVo workSituationVo)
    {
        WorkSituation workSituation = workSituationService.selectPieVo(workSituationVo);
        return AjaxResult.success(workSituation);
    }

    /**
     * 导出考勤统计（上班情况）列表
     */
    @PreAuthorize("@ss.hasPermi('work:situation:export')")
    @Log(title = "考勤统计（上班情况）", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WorkSituationVo workSituation)
    {
        List<WorkSituationVo> list = workSituationService.selectWorkSituationList(workSituation);
        ExcelUtil<WorkSituationVo> util = new ExcelUtil<WorkSituationVo>(WorkSituationVo.class);
        return util.exportExcel(list, "situation");
    }

    /**
     * 获取考勤统计（上班情况）详细信息
     */
    @PreAuthorize("@ss.hasPermi('work:situation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(workSituationService.selectWorkSituationById(id));
    }

    /**
     * 新增考勤统计（上班情况）
     */
    @PreAuthorize("@ss.hasPermi('work:situation:add')")
    @Log(title = "考勤统计（上班情况）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkSituation workSituation)
    {
        return toAjax(workSituationService.insertWorkSituation(workSituation));
    }

    /**
     * 修改考勤统计（上班情况）
     */
    @PreAuthorize("@ss.hasPermi('work:situation:edit')")
    @Log(title = "考勤统计（上班情况）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkSituation workSituation)
    {
        return toAjax(workSituationService.updateWorkSituation(workSituation));
    }

    /**
     * 删除考勤统计（上班情况）
     */
    @PreAuthorize("@ss.hasPermi('work:situation:remove')")
    @Log(title = "考勤统计（上班情况）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(workSituationService.deleteWorkSituationByIds(ids));
    }

    @Log(title = "考勤统计（上班情况）", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('work:situation:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport, String month) throws Exception {
        ExcelUtil<WorkSituationImportVo> util = new ExcelUtil<WorkSituationImportVo>(WorkSituationImportVo.class);
        util.setHeadRowIndex(1);
        List<WorkSituationImportVo> dataList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String msg = workSituationService.importWorkSituation(dataList, updateSupport, month, operName);
        return AjaxResult.success(msg);
    }
}
