package com.cb.assess.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.cb.assess.domain.dto.BizAssessAnnualAssessmentDTO;
import com.cb.assess.domain.vo.BizAssessAnnualAssessmentVo;
import com.cb.assess.utils.PoiTlUtil;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.system.service.ISysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
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
import com.cb.assess.domain.BizAssessAnnualAssessment;
import com.cb.assess.service.IBizAssessAnnualAssessmentService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 年度考核Controller
 * 
 * @author ruoyi
 * @date 2023-11-17
 */
@RestController
@RequestMapping("/assess/assessment")
public class BizAssessAnnualAssessmentController extends BaseController
{
    @Autowired
    private IBizAssessAnnualAssessmentService bizAssessAnnualAssessmentService;

    @Resource
    private ISysDeptService sysDeptService;
    /**
     * 查询年度考核列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessAnnualAssessmentVo bizAssessAnnualAssessment)
    {
        startPage();
        bizAssessAnnualAssessment.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<BizAssessAnnualAssessmentVo> list = bizAssessAnnualAssessmentService.selectBizAssessAnnualAssessmentList(bizAssessAnnualAssessment);
        return getDataTable(list);
    }

    /**
     * 主管/分管领导评定的列表
     * @param bizAssessAnnualAssessment
     * @return
     */
    @GetMapping("/listByManagerAudit")
    public TableDataInfo listByManagerAudit(BizAssessAnnualAssessmentVo bizAssessAnnualAssessment)
    {
        //先检查自己是不是分管领导
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysDept sysDept = new SysDept();
        sysDept.setLeaderCharge(loginUser.getUser().getUserId().toString());
        List<SysDept> sysDepts = sysDeptService.selectDeptList(sysDept);
        startPage();

        if (null==sysDepts||sysDepts.isEmpty()){
            //审定环节要大于3
            bizAssessAnnualAssessment.setAuditStatus(3);
            Long deptId = loginUser.getDeptId();
            //仅拿本部门的
            bizAssessAnnualAssessment.setDeptId(deptId);
        }else{
            //是局领导，那就拿当前所任所有部门的二级巡视员等的年度考核登记表
            List<Long> collect = sysDepts.stream().map(SysDept::getDeptId).collect(Collectors.toList());
            bizAssessAnnualAssessment.setDeptIds(collect);
            String[] workCodes = {"121", "212"};
            bizAssessAnnualAssessment.setWorkCodes(Arrays.asList(workCodes));
        }
        List<BizAssessAnnualAssessmentVo> list = bizAssessAnnualAssessmentService.selectBizAssessAnnualAssessmentList(bizAssessAnnualAssessment);
        return getDataTable(list);
    }

    @GetMapping("/listByManagerCommander")
    public TableDataInfo listByManagerCommander(BizAssessAnnualAssessmentVo bizAssessAnnualAssessment)
    {
        startPage();
        //审定环节要大于4
        bizAssessAnnualAssessment.setAuditStatus(4);
        List<BizAssessAnnualAssessmentVo> list = bizAssessAnnualAssessmentService.selectBizAssessAnnualAssessmentList(bizAssessAnnualAssessment);
        return getDataTable(list);
    }
    //查询数据中所有年份，用来做筛选列表
    @GetMapping("/selectYears")
    public AjaxResult selectYears( )
    {
        List<BizAssessAnnualAssessmentVo> list = bizAssessAnnualAssessmentService.selectYears();
        return AjaxResult.success(list);
    }
    /**
     * 保存并导出年度考核登记表
     */
    @Log(title = "年度考核", businessType = BusinessType.EXPORT)
    @PostMapping("/saveAndExport")
    public void export(HttpServletResponse response, @RequestBody BizAssessAnnualAssessmentVo bizAssessAnnualAssessment) throws IOException {
        int i = bizAssessAnnualAssessmentService.insertBizAssessAnnualAssessment(bizAssessAnnualAssessment);
        ServletOutputStream outputStream = response.getOutputStream();
        bizAssessAnnualAssessmentService.exportOne(bizAssessAnnualAssessment.getId(),outputStream);

    }
    @GetMapping("/exportOne")
    public void exportOne(HttpServletResponse response,  BizAssessAnnualAssessmentVo bizAssessAnnualAssessment) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        bizAssessAnnualAssessmentService.exportOne(bizAssessAnnualAssessment.getId(),outputStream);

    }

    /**
     * 获取年度考核详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessAnnualAssessmentService.selectBizAssessAnnualAssessmentById(id));
    }

    /**
     * 新增年度考核
     */
    @PreAuthorize("@ss.hasPermi('assess:assessment:add')")
    @Log(title = "年度考核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessAnnualAssessment bizAssessAnnualAssessment)
    {
        int i = bizAssessAnnualAssessmentService.insertBizAssessAnnualAssessment(bizAssessAnnualAssessment);
        if (i==0){
            return error("已有年度登记");
        }
        return AjaxResult.success(bizAssessAnnualAssessment);
    }

    /**
     * 修改年度考核
     */
    @PreAuthorize("@ss.hasPermi('assess:assessment:edit')")
    @Log(title = "年度考核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessAnnualAssessment bizAssessAnnualAssessment)
    {
        return toAjax(bizAssessAnnualAssessmentService.updateBizAssessAnnualAssessment(bizAssessAnnualAssessment));
    }

    @PutMapping("/batchEvaluate")
    public AjaxResult batchEvaluate(@RequestBody BizAssessAnnualAssessmentDTO bizAssessAnnualAssessmentDTO)
    {
        List<String> ids = bizAssessAnnualAssessmentDTO.getIds();
        for (String id : ids) {
            BizAssessAnnualAssessment bizAssessAnnualAssessment = new BizAssessAnnualAssessment();
            bizAssessAnnualAssessment.setId(id);
            BeanUtils.copyProperties(bizAssessAnnualAssessmentDTO,bizAssessAnnualAssessment);
            System.out.println(bizAssessAnnualAssessment);
            bizAssessAnnualAssessmentService.updateBizAssessAnnualAssessment(bizAssessAnnualAssessment);
        }
//        bizAssessAnnualAssessmentService.updateBizAssessAnnualAssessment(bizAssessAnnualAssessment)
        return null;
    }
    /**
     * 删除年度考核
     */
    @PreAuthorize("@ss.hasPermi('assess:assessment:remove')")
    @Log(title = "年度考核", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessAnnualAssessmentService.deleteBizAssessAnnualAssessmentByIds(ids));
    }
}
