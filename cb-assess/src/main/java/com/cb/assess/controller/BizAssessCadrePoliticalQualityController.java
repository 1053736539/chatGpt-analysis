package com.cb.assess.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cb.assess.domain.BizAssessCadrePoliticalQuality;
import com.cb.assess.domain.dto.BizAssessCadrePoliticalQualityDTO;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityVo;
import com.cb.assess.service.IBizAssessCadrePoliticalQualityService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.BaseEntity;
import com.cb.common.core.domain.entity.SysDictData;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.easyexcel.TemplateExcelExpUtil;
import com.cb.system.service.ISysDictTypeService;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * xxxx年处级领导干部政治素质年度考察测评Controller
 *
 * @author ruoyi
 * @date 2023-11-16
 */
@RestController
@RequestMapping("/assess/quality")
public class BizAssessCadrePoliticalQualityController extends BaseController {
    @Autowired
    private IBizAssessCadrePoliticalQualityService bizAssessCadrePoliticalQualityService;
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysDictTypeService dictTypeService;
    @Resource
    private ISysUserService userService;
    /**
     * 查询xxxx年处级领导干部政治素质年度考察测评列表
     */
    //@PreAuthorize("@ss.hasPermi('assess:quality:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) {
        startPage();
        if (StringUtils.isNoneBlank(bizAssessCadrePoliticalQuality.getType()) && bizAssessCadrePoliticalQuality.getType().equals("1")) {
            bizAssessCadrePoliticalQuality.setReviewerUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        } else {
            bizAssessCadrePoliticalQuality.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        List<BizAssessCadrePoliticalQualityVo> list = bizAssessCadrePoliticalQualityService.selectBizAssessCadrePoliticalQualityList(bizAssessCadrePoliticalQuality);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public TableDataInfo listAll(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) {
        startPage();
        List<BizAssessCadrePoliticalQualityVo> list = bizAssessCadrePoliticalQualityService.selectBizAssessCadrePoliticalQualityList(bizAssessCadrePoliticalQuality);
        return getDataTable(list);
    }

    @GetMapping("/listMyCreate")
    public TableDataInfo listMyCreate(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) {
        startPage();
//        bizAssessCadrePoliticalQuality.setCreateBy(SecurityUtils.getUsername());
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        bizAssessCadrePoliticalQuality.setDeptId(deptId);
        List<BizAssessCadrePoliticalQualityVo> list = bizAssessCadrePoliticalQualityService.selectBizAssessCadrePoliticalQualityList(bizAssessCadrePoliticalQuality);
        List<String> collect = list.stream().map(BaseEntity::getCreateBy).distinct().collect(Collectors.toList());
        List<SysUser> sysUsers = userService.selectUserListByNames(collect);
        Map<String, SysUser> collect1 = sysUsers.stream().collect(Collectors.toMap(SysUser::getUserName, e -> e, (k1, k2) -> k1));
        for (BizAssessCadrePoliticalQualityVo bizAssessDeptExcellentEvaluationVo : list) {
            SysUser sysUser = collect1.get(bizAssessDeptExcellentEvaluationVo.getCreateBy());
            if (null != sysUser) {
                bizAssessDeptExcellentEvaluationVo.setCreateBy(sysUser.getName());
            }
        }
        return getDataTable(list);
    }

    @GetMapping("/selectStatistics")
    public TableDataInfo selectStatistics(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) {
        startPage();
        List<BizAssessCadrePoliticalQualityVo> list = bizAssessCadrePoliticalQualityService.selectStatistics(bizAssessCadrePoliticalQuality);
        return getDataTable(list);
    }

    @GetMapping("/selectStatisticsByDept")
    public TableDataInfo selectStatisticsByDept(BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) {
        startPage();
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        bizAssessCadrePoliticalQuality.setDeptId(deptId);
        List<BizAssessCadrePoliticalQualityVo> list = bizAssessCadrePoliticalQualityService.selectStatistics(bizAssessCadrePoliticalQuality);
        return getDataTable(list);
    }

    /**
     * 导出xxxx年处级领导干部政治素质年度考察测评列表,导出单个人的表
     */
//    @PreAuthorize("@ss.hasPermi('assess:quality:export')")
    @Log(title = "xxxx年处级领导干部政治素质年度考察测评", businessType = BusinessType.EXPORT)
    @GetMapping("/exportStaticOne")
    public void exportStaticOne(HttpServletResponse response, BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) throws Exception {
        List<BizAssessCadrePoliticalQualityVo> bizAssessCadrePoliticalQualityVos = bizAssessCadrePoliticalQualityService.selectStatistics(bizAssessCadrePoliticalQuality);
        BizAssessCadrePoliticalQualityVo one = bizAssessCadrePoliticalQualityVos.get(0);
        Map<String, Object> map = BeanUtil.beanToMap(bizAssessCadrePoliticalQualityVos.get(0));
        /**
         * 查询一下对应的数据字典
         */
        //总体评价字典
//        List<SysDictData> overall = dictTypeService.selectDictDataByType("evaluation_opinions_overall");
//        List<SysDictData> active = dictTypeService.selectDictDataByType("evaluation_opinions_active");
//        List<SysDictData> burden = dictTypeService.selectDictDataByType("evaluation_opinions_burden");
//        Map<String, String> overallMap = overall.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
//        Map<String, String> activMap = active.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
//        Map<String, String> burdenMap = burden.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        ArrayList<String> stringsActive = new ArrayList<>();
        stringsActive.add("loyalty");
        stringsActive.add("abilityConcentrate");
        stringsActive.add("assume");
        stringsActive.add("capacity");
        stringsActive.add("selfDiscipline");
        ArrayList<String> stringsBurdenMap = new ArrayList<>();
        stringsBurdenMap.add("lackLoyalty");
        stringsBurdenMap.add("lackAbilityConcentrate");
        stringsBurdenMap.add("lackAssume");
        stringsBurdenMap.add("lackMisconduct");
        stringsBurdenMap.add("violationOrganization");
        stringsBurdenMap.add("lackCapacity");
        for (Integer i = 1; i < 5; i++) {
            for (String s : stringsActive) {
                Object o = map.get(s);
                if (i.toString().equals("1"))
                    map.put(s + i, o);
                else
                    map.put(s + i, " ");
            }
            for (String s : stringsBurdenMap) {
                Object o = map.get(s);
                if (i.toString().equals("1"))
                    map.put(s + i, o);
                else
                    map.put(s + i, " ");
            }
            if (i.toString().equals("1"))
                map.put("overallEvaluation" + i, one.getOverallEvaluation());
            else map.put("overallEvaluation" + i, " ");
        }
        ServletOutputStream outputStream = response.getOutputStream();
        TemplateExcelExpUtil.setClassPath("template/qualityStatistics.xls").export(outputStream,map);
        outputStream.flush();
        /**
         * 拿到数据，先去拿字典数据
         */
    }

    /**
     * 导出统计表明细
     * @param response
     * @param bizAssessCadrePoliticalQuality
     * @throws Exception
     */
    @Log(title = "xxxx年处级领导干部政治素质年度考察测评", businessType = BusinessType.EXPORT)
    @GetMapping("/exportOne")
    public void exportOne(HttpServletResponse response, BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQuality) throws Exception {
        BizAssessCadrePoliticalQuality one = bizAssessCadrePoliticalQualityService.selectBizAssessCadrePoliticalQualityById(bizAssessCadrePoliticalQuality.getId());
        Map<String, Object> map = BeanUtil.beanToMap(one);
        /**
         * 查询一下对应的数据字典
         */
        //总体评价字典
        List<SysDictData> overall = dictTypeService.selectDictDataByType("evaluation_opinions_overall");
        List<SysDictData> active = dictTypeService.selectDictDataByType("evaluation_opinions_active");
        List<SysDictData> burden = dictTypeService.selectDictDataByType("evaluation_opinions_burden");
        Map<String, String> overallMap = overall.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        Map<String, String> activMap = active.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        Map<String, String> burdenMap = burden.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        ArrayList<String> stringsActive = new ArrayList<>();
        stringsActive.add("loyalty");
        stringsActive.add("abilityConcentrate");
        stringsActive.add("assume");
        stringsActive.add("capacity");
        stringsActive.add("selfDiscipline");
        ArrayList<String> stringsBurdenMap = new ArrayList<>();
        stringsBurdenMap.add("lackLoyalty");
        stringsBurdenMap.add("lackAbilityConcentrate");
        stringsBurdenMap.add("lackAssume");
        stringsBurdenMap.add("lackMisconduct");
        stringsBurdenMap.add("violationOrganization");
        stringsBurdenMap.add("lackCapacity");
        for (Integer i = 1; i < 5; i++) {
            for (String s : stringsActive) {
                Object o = map.get(s);
                if (i.toString().equals(o))
                    map.put(s + i, activMap.get(o == null ? "" : o.toString()));
                else
                    map.put(s + i, " ");
            }
            for (String s : stringsBurdenMap) {
                Object o = map.get(s);
                if (i.toString().equals(o))
                    map.put(s + i, burdenMap.get(o == null ? "" : o.toString()));
                else
                    map.put(s + i, " ");
            }
            if (i.toString().equals(one.getOverallEvaluation()))
                map.put("overallEvaluation" + i, "☑");
            else map.put("overallEvaluation" + i, "□");
        }
        ServletOutputStream outputStream = response.getOutputStream();
        TemplateExcelExpUtil.setClassPath("template/quality.xls").export(outputStream,map);
        outputStream.flush();
        /**
         * 拿到数据，先去拿字典数据
         */
    }

    @GetMapping("/selectYears")
    public AjaxResult selectYears() {
        return AjaxResult.success(bizAssessCadrePoliticalQualityService.selectYears());
    }

    /**
     * 获取xxxx年处级领导干部政治素质年度考察测评详细信息
     */
    // @PreAuthorize("@ss.hasPermi('assess:quality:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessCadrePoliticalQualityService.selectBizAssessCadrePoliticalQualityById(id));
    }

    /**
     * 新增xxxx年处级领导干部政治素质年度考察测评
     */
//    @PreAuthorize("@ss.hasPermi('assess:quality:add')")
    @Log(title = "xxxx年处级领导干部政治素质年度考察测评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated BizAssessCadrePoliticalQualityDTO bizAssessCadrePoliticalQuality) {
        String[] split1 = bizAssessCadrePoliticalQuality.getUserIds().split(",");
        for (String uid : split1) {
            //先查询有没有
            BizAssessCadrePoliticalQualityVo bizAssessCadrePoliticalQualityQuery = new BizAssessCadrePoliticalQualityVo();
            bizAssessCadrePoliticalQualityQuery.setAssessmentYear(bizAssessCadrePoliticalQuality.getAssessmentYear());
            bizAssessCadrePoliticalQualityQuery.setUserId(Long.valueOf(uid));
            SysUser sysUser = sysUserService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
            List<BizAssessCadrePoliticalQualityVo> list = bizAssessCadrePoliticalQualityService.selectBizAssessCadrePoliticalQualityList(bizAssessCadrePoliticalQualityQuery);
            if (!list.isEmpty()) {
                bizAssessCadrePoliticalQualityService.deleteByUserIdAndYear(Long.valueOf(uid), bizAssessCadrePoliticalQuality.getAssessmentYear());
            }
            if (StringUtils.isNoneBlank(bizAssessCadrePoliticalQuality.getReviewerUserIds())) {
                String[] split = bizAssessCadrePoliticalQuality.getReviewerUserIds().split(",");
                for (String s : split) {
                    BizAssessCadrePoliticalQuality save = new BizAssessCadrePoliticalQuality();
                    BeanUtils.copyProperties(bizAssessCadrePoliticalQuality, save);
                    save.setUserId(Long.valueOf(uid));
                    save.setReviewerUserId(Long.valueOf(s));
                    save.setDeptId(sysUser.getDeptId());
                    bizAssessCadrePoliticalQualityService.insertBizAssessCadrePoliticalQuality(save);
                }
            }
        }

        return success("成功！");
    }

    /**
     * 修改xxxx年处级领导干部政治素质年度考察测评
     */
//    @PreAuthorize("@ss.hasPermi('assess:quality:edit')")
    @Log(title = "xxxx年处级领导干部政治素质年度考察测评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessCadrePoliticalQuality bizAssessCadrePoliticalQuality) {
        return toAjax(bizAssessCadrePoliticalQualityService.updateBizAssessCadrePoliticalQuality(bizAssessCadrePoliticalQuality));
    }

    /**
     * 删除xxxx年处级领导干部政治素质年度考察测评
     */
//    @PreAuthorize("@ss.hasPermi('assess:quality:remove')")
    @Log(title = "xxxx年处级领导干部政治素质年度考察测评", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessCadrePoliticalQualityService.deleteBizAssessCadrePoliticalQualityByIds(ids));
    }
}
