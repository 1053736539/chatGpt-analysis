package com.cb.assess.controller;

import com.cb.assess.domain.BizAssessAnnualReviewTypeB;
import com.cb.assess.domain.dto.BizAssessAnnualReviewTypeDTO;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeVo;
import com.cb.assess.service.IBizAssessAnnualReviewTypeBService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省统计局二级巡视员、总师、处（室）负责人年度考核评议B类Controller
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/assess/annualReviewB")
public class BizAssessAnnualReviewTypeBController extends BaseController
{
    @Autowired
    private IBizAssessAnnualReviewTypeBService bizAssessAnnualReviewTypeBService;

    /**
     * 查询省统计局二级巡视员、总师、处（室）负责人年度考核评议B类列表
     */
//    @PreAuthorize("@ss.hasPermi('assess:annualReviewB:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo)
    {
        if (null==bizAssessAnnualReviewTypeVo.getType()||bizAssessAnnualReviewTypeVo.getType().equals("1")){
            bizAssessAnnualReviewTypeVo.setDiscussantUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            bizAssessAnnualReviewTypeVo.setUserId(null);
        }else {
            bizAssessAnnualReviewTypeVo.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            bizAssessAnnualReviewTypeVo.setDiscussantUserId(null);
        }
        startPage();
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeBService.selectBizAssessAnnualReviewTypeBList(bizAssessAnnualReviewTypeVo);
        return getDataTable(list);
    }
    @GetMapping("/listByMyCreate")
    public TableDataInfo listByMyCreate(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo) {
        bizAssessAnnualReviewTypeVo.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeBService.selectBizAssessAnnualReviewTypeBList(bizAssessAnnualReviewTypeVo);
        return getDataTable(list);
    }
    //全部详情表
    @GetMapping("/listAll")
    public TableDataInfo listAll(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeVo) {
        startPage();
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeBService.selectBizAssessAnnualReviewTypeBList(bizAssessAnnualReviewTypeVo);
        return getDataTable(list);
    }
    /**
     * 导出省统计局二级巡视员、总师、处（室）负责人年度考核评议B类列表
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewB:export')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议B类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeB)
    {
        List<BizAssessAnnualReviewTypeVo> list = bizAssessAnnualReviewTypeBService.selectBizAssessAnnualReviewTypeBList(bizAssessAnnualReviewTypeB);
        ExcelUtil<BizAssessAnnualReviewTypeVo> util = new ExcelUtil<BizAssessAnnualReviewTypeVo>(BizAssessAnnualReviewTypeVo.class);
        return util.exportExcel(list, "b");
    }

    /**
     * 获取省统计局二级巡视员、总师、处（室）负责人年度考核评议B类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewB:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessAnnualReviewTypeBService.selectBizAssessAnnualReviewTypeBById(id));
    }

    /**
     * 新增省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewB:add')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议B类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessAnnualReviewTypeDTO bizAssessAnnualReviewTypeDTO)
    {
        //获取当前设置的年度的考评信息，用来处理这个人是否被创建过以及是否被评议
        BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeAQuery = new BizAssessAnnualReviewTypeVo();
        bizAssessAnnualReviewTypeAQuery.setAssessmentYear(bizAssessAnnualReviewTypeDTO.getAssessmentYear());
        List<BizAssessAnnualReviewTypeVo> bizAssessAnnualReviewTypeAS = bizAssessAnnualReviewTypeBService.selectBizAssessAnnualReviewTypeBList(bizAssessAnnualReviewTypeAQuery);
        Map<String, BizAssessAnnualReviewTypeVo> map = new HashMap<>();
        for (BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeA : bizAssessAnnualReviewTypeAS) {
            map.put(bizAssessAnnualReviewTypeA.getUserId()+"-"+bizAssessAnnualReviewTypeA.getDiscussantUserId(),bizAssessAnnualReviewTypeA);
        }
        //重组
        String[] userIds = bizAssessAnnualReviewTypeDTO.getUserIds().split(",");
        String[] discussantUserIds = bizAssessAnnualReviewTypeDTO.getDiscussantUserIds().split(",");
        for (String userId : userIds) {
            for (String discussantUserId : discussantUserIds) {
                BizAssessAnnualReviewTypeVo bizAssessAnnualReviewTypeA = map.get(userId + "-" + discussantUserId);
                if (null==bizAssessAnnualReviewTypeA){
                    //因为他们自己也要评自己，所以这里先注释掉
//                    if (!userId.equals(discussantUserId)){
                        BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB = new BizAssessAnnualReviewTypeB();
                        bizAssessAnnualReviewTypeB.setAssessmentYear(bizAssessAnnualReviewTypeDTO.getAssessmentYear());
                        bizAssessAnnualReviewTypeB.setUserId(Long.valueOf(userId));
                        bizAssessAnnualReviewTypeB.setStatus("0");
                        bizAssessAnnualReviewTypeB.setDiscussantUserId(Long.valueOf(discussantUserId));
                        bizAssessAnnualReviewTypeB.setType(bizAssessAnnualReviewTypeDTO.getType());
                        bizAssessAnnualReviewTypeBService.insertBizAssessAnnualReviewTypeB(bizAssessAnnualReviewTypeB);
//                    }
                }
            }
        }
        return success("成功！");
    }

    /**
     * 修改省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewB:edit')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议B类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB)
    {
        return toAjax(bizAssessAnnualReviewTypeBService.updateBizAssessAnnualReviewTypeB(bizAssessAnnualReviewTypeB));
    }

    @PutMapping("/editBatch")
    public AjaxResult editBatch(@RequestBody List<BizAssessAnnualReviewTypeB> list)
    {
        for (BizAssessAnnualReviewTypeB bizAssessAnnualReviewTypeB : list) {
            bizAssessAnnualReviewTypeBService.updateBizAssessAnnualReviewTypeB(bizAssessAnnualReviewTypeB);
        }
        return success("成功！");
    }
    /**
     * 删除省统计局二级巡视员、总师、处（室）负责人年度考核评议B类
     */
    //@PreAuthorize("@ss.hasPermi('assess:annualReviewB:remove')")
    @Log(title = "省统计局二级巡视员、总师、处（室）负责人年度考核评议B类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessAnnualReviewTypeBService.deleteBizAssessAnnualReviewTypeBByIds(ids));
    }
}
