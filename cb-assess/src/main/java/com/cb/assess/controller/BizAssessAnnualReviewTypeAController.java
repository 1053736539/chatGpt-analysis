package com.cb.assess.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.assess.domain.BizAssessAnnualReviewTypeA;
import com.cb.assess.domain.BizAssessAnnualReviewTypeB;
import com.cb.assess.domain.dto.BizAssessAnnualReviewTypeDTO;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;
import com.cb.assess.service.IBizAssessAnnualReviewTypeAService;
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
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议A类Controller
 *
 * @author ruoyi
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/assess/annualReviewA")
public class BizAssessAnnualReviewTypeAController extends BaseController {
    @Autowired
    private IBizAssessAnnualReviewTypeAService bizAssessAnnualReviewTypeAService;

    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议A类列表
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewA:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo) {
        if (null == bizAssessAnnualReviewTypeVo.getDataType() || bizAssessAnnualReviewTypeVo.getDataType().equals("1")) {
            bizAssessAnnualReviewTypeVo.setDiscussantUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            bizAssessAnnualReviewTypeVo.setUserId(null);
        } else {
            bizAssessAnnualReviewTypeVo.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            bizAssessAnnualReviewTypeVo.setDiscussantUserId(null);
        }
        startPage();
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeAService.selectBizAssessAnnualReviewTypeAList(bizAssessAnnualReviewTypeVo);
        return getDataTable(list);
    }
    //全部详情表
    @GetMapping("/listAll")
    public TableDataInfo listAll(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo) {
        startPage();
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeAService.selectBizAssessAnnualReviewTypeAList(bizAssessAnnualReviewTypeVo);
        return getDataTable(list);
    }

    @GetMapping("/listByMyCreate")
    public TableDataInfo listByMyCreate(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo) {
        bizAssessAnnualReviewTypeVo.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeAService.selectBizAssessAnnualReviewTypeAList(bizAssessAnnualReviewTypeVo);
        return getDataTable(list);
    }

    /**
     * 导出省统计局二级巡视员、总师、处（室）负责人年度考核评议A类列表
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewA:export')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议A类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeA) {
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeAService.selectBizAssessAnnualReviewTypeAList(bizAssessAnnualReviewTypeA);
        ExcelUtil<BizAssessAnnualReviewTypeVo> util = new ExcelUtil<BizAssessAnnualReviewTypeVo>(BizAssessAnnualReviewTypeVo.class);
        return util.exportExcel(list, "a");
    }

    /**
     * 获取省统计局二级巡视员、总师、处（室）负责人年度考核评议A类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewA:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessAnnualReviewTypeAService.selectBizAssessAnnualReviewTypeAById(id));
    }

    /**
     * 新增省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewA:add')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议A类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessAnnualReviewTypeDTO bizAssessAnnualReviewTypeDTO) {
        //获取当前设置的年度的考评信息，用来处理这个人是否被创建过以及是否被评议
        BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeAQuery = new BizAssessAnnualReviewTypeVo();
        bizAssessAnnualReviewTypeAQuery.setAssessmentYear(bizAssessAnnualReviewTypeDTO.getAssessmentYear());
        List<BizAssessAnnualReviewTypeVo> bizAssessAnnualReviewTypeAS = bizAssessAnnualReviewTypeAService.selectBizAssessAnnualReviewTypeAList(bizAssessAnnualReviewTypeAQuery);
        Map<String, BizAssessAnnualReviewTypeVo> map = new HashMap<>();
        for (BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeA : bizAssessAnnualReviewTypeAS) {
            map.put(bizAssessAnnualReviewTypeA.getUserId() + "-" + bizAssessAnnualReviewTypeA.getDiscussantUserId(), bizAssessAnnualReviewTypeA);
        }
        //重组
        String[] userIds = bizAssessAnnualReviewTypeDTO.getUserIds().split(",");
        String[] discussantUserIds = bizAssessAnnualReviewTypeDTO.getDiscussantUserIds().split(",");
        for (String userId : userIds) {
            for (String discussantUserId : discussantUserIds) {
                BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeA = map.get(userId + "-" + discussantUserId);
                if (null == bizAssessAnnualReviewTypeA) {
                    if (!userId.equals(discussantUserId)) {
                        BizAssessAnnualReviewTypeA bizAssessAnnualReviewTypeA1 = new BizAssessAnnualReviewTypeA();
                        bizAssessAnnualReviewTypeA1.setAssessmentYear(bizAssessAnnualReviewTypeDTO.getAssessmentYear());
                        bizAssessAnnualReviewTypeA1.setUserId(Long.valueOf(userId));
                        bizAssessAnnualReviewTypeA1.setStatus("0");
                        bizAssessAnnualReviewTypeA1.setDiscussantUserId(Long.valueOf(discussantUserId));
                        bizAssessAnnualReviewTypeA1.setType(bizAssessAnnualReviewTypeDTO.getType());
                        bizAssessAnnualReviewTypeAService.insertBizAssessAnnualReviewTypeA(bizAssessAnnualReviewTypeA1);
                    }
                }
            }
        }
        return success("成功！");
    }

    /**
     * 修改省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewA:edit')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议A类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessAnnualReviewTypeA bizAssessAnnualReviewTypeA) {
        return toAjax(bizAssessAnnualReviewTypeAService.updateBizAssessAnnualReviewTypeA(bizAssessAnnualReviewTypeA));
    }

    @PutMapping("/editBatch")
    public AjaxResult editBatch(@RequestBody List<BizAssessAnnualReviewTypeA> list) {
        for (BizAssessAnnualReviewTypeA bizAssessAnnualReviewType : list) {
            bizAssessAnnualReviewTypeAService.updateBizAssessAnnualReviewTypeA(bizAssessAnnualReviewType);
        }
        return success("成功！");
    }

    /**
     * 删除省统计局二级巡视员、总师、处（室）负责人年度考核评议A类
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewA:remove')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议A类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessAnnualReviewTypeAService.deleteBizAssessAnnualReviewTypeAByIds(ids));
    }
}
