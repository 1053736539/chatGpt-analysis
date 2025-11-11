package com.cb.assess.controller;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.cb.assess.domain.BizAssessCadrePoliticalQualityIdentify;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityIdentifyVo;
import com.cb.assess.service.IBizAssessCadrePoliticalQualityIdentifyService;
import com.cb.assess.utils.PoiTlUtil;
import com.cb.common.core.domain.entity.SysDictData;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.vo.SysDeptVo;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.WordUtil;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysDictTypeService;
import com.cb.system.service.ISysUserService;
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
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 政治素质鉴定Controller
 * 
 * @author ruoyi
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/assess/quality/identify")
public class BizAssessCadrePoliticalQualityIdentifyController extends BaseController
{
    @Autowired
    private IBizAssessCadrePoliticalQualityIdentifyService bizAssessCadrePoliticalQualityIdentifyService;

    @Resource
    private ISysDictTypeService dictTypeService;
    @Resource
    private ISysUserService userService;
    @Resource
    private ISysDeptService deptService;
    /**
     * 查询政治素质鉴定列表（全部的统计）
     */
//    @PreAuthorize("@ss.hasPermi('system:identify:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        startPage();
        bizAssessCadrePoliticalQualityIdentify.setIsReport("2");
        List<BizAssessCadrePoliticalQualityIdentifyVo> list = bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyList(bizAssessCadrePoliticalQualityIdentify);
        return getDataTable(list);
    }

    /**
     * 根据部门获取
     * @param bizAssessCadrePoliticalQualityIdentify
     * @return
     */
    @GetMapping("/listByDept")
    public TableDataInfo listByDept(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        startPage();
        bizAssessCadrePoliticalQualityIdentify.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        List<BizAssessCadrePoliticalQualityIdentifyVo> list = bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyList(bizAssessCadrePoliticalQualityIdentify);
        return getDataTable(list);
    }

    /**
     * 获取自己的政治素质鉴定表
     * @param bizAssessCadrePoliticalQualityIdentify
     * @return
     */
    @GetMapping("/listByMy")
    public TableDataInfo listByMy(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        startPage();
        bizAssessCadrePoliticalQualityIdentify.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<BizAssessCadrePoliticalQualityIdentifyVo> list = bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyList(bizAssessCadrePoliticalQualityIdentify);
        return getDataTable(list);
    }
    /**
     * 获取自己的政治素质鉴定表
     * @param bizAssessCadrePoliticalQualityIdentify
     * @return
     */
    @GetMapping("/listByManagerUser")
    public TableDataInfo listByManagerUser(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        startPage();
        bizAssessCadrePoliticalQualityIdentify.setManagerUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        bizAssessCadrePoliticalQualityIdentify.setIsReport("0");
        List<BizAssessCadrePoliticalQualityIdentifyVo> list = bizAssessCadrePoliticalQualityIdentifyService.listByManagerUser(bizAssessCadrePoliticalQualityIdentify);
        return getDataTable(list);
    }

    /**
     * 查询数据中所有年份
     * @return
     */
    @GetMapping("/selectYears")
    public AjaxResult selectYears( )
    {
        List<BizAssessCadrePoliticalQualityIdentifyVo> list = bizAssessCadrePoliticalQualityIdentifyService.selectYears();
        return AjaxResult.success(list);
    }

    /**
     * 导出政治素质鉴定列表
     */
//    @PreAuthorize("@ss.hasPermi('system:identify:export')")
    @Log(title = "政治素质鉴定", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify)
    {
        List<BizAssessCadrePoliticalQualityIdentifyVo> list = bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyList(bizAssessCadrePoliticalQualityIdentify);
        ExcelUtil<BizAssessCadrePoliticalQualityIdentifyVo> util = new ExcelUtil<BizAssessCadrePoliticalQualityIdentifyVo>(BizAssessCadrePoliticalQualityIdentifyVo.class);
        return util.exportExcel(list, "identify");
    }

    /**
     * 到处单个人的政治素质鉴定表word
     * @param bizAssessCadrePoliticalQualityIdentify
     * @return
     */
    @GetMapping("/exportOne")
    public void exportOne(HttpServletResponse response, BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify) throws IOException {
        BizAssessCadrePoliticalQualityIdentifyVo one = bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyById(bizAssessCadrePoliticalQualityIdentify.getId());
        if (null==one){
            throw new EOFException("数据未找到！！！");
        }
        ClassPathResource classPathResource = new ClassPathResource("template/qualityIdentify.docx");
        List<SysDictData> data = dictTypeService.selectDictDataByType("evaluation_opinions_overall");
        Map<String, String> collect = data.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
        InputStream is = classPathResource.getInputStream();
        String s = collect.get(one.getLeaderCommentGrade());
        String s1 = collect.get(one.getFinalIdentify());
        one.setLeaderCommentGrade(s==null?"   ":s);
        one.setFinalIdentify(s1==null?"   ":s1);
        Map<String, Object> map = BeanUtil.beanToMap(one);
        ServletOutputStream outputStream = response.getOutputStream();
//        bizAssessCadrePoliticalQualityIdentify.getSelfAssessment()//WordUtil.exportWord(is, outputStream, map, list, true);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("selfAssessment");
        PoiTlUtil.export2Word(is, outputStream, map, strings, true);
        outputStream.flush();
//        WordUtil.easyPoiExport(is, map, response);
        return;
    }
    /**
     * 获取政治素质鉴定详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:identify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyById(id));
    }
    @GetMapping(value = "/selectStaticsByUser")
    public AjaxResult selectStaticsByUser(String userId,String assessmentYear)
    {
        return AjaxResult.success(bizAssessCadrePoliticalQualityIdentifyService.selectStaticsByUser(userId,assessmentYear));
    }

    /**
     * 新增政治素质鉴定
     */
//    @PreAuthorize("@ss.hasPermi('system:identify:add')")
    @Log(title = "政治素质鉴定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify)
    {
        //检查当前年度当前用户是否有，有了就不管
        BizAssessCadrePoliticalQualityIdentifyVo query = new BizAssessCadrePoliticalQualityIdentifyVo();
        query.setAssessmentYear(bizAssessCadrePoliticalQualityIdentify.getAssessmentYear());
        query.setUserId(bizAssessCadrePoliticalQualityIdentify.getUserId());
        List<BizAssessCadrePoliticalQualityIdentifyVo> bizAssessCadrePoliticalQualityIdentifyVos = bizAssessCadrePoliticalQualityIdentifyService.selectBizAssessCadrePoliticalQualityIdentifyList(query);
        SysDeptVo sysDeptVo = deptService.selectDeptById(SecurityUtils.getLoginUser().getUser().getDeptId());
        if (null==sysDeptVo||null==sysDeptVo.getLeaderCharge())
            return error("未找到部门的主管/分管领导！");
        if (bizAssessCadrePoliticalQualityIdentifyVos.isEmpty()){
            SysUser sysUser = userService.selectUserById(bizAssessCadrePoliticalQualityIdentify.getUserId());
            bizAssessCadrePoliticalQualityIdentify.setWorkPost(sysUser.getWorkPost());
            bizAssessCadrePoliticalQualityIdentify.setDeptId(sysUser.getDeptId());
            bizAssessCadrePoliticalQualityIdentify.setManagerUserId(Long.valueOf(sysDeptVo.getLeaderCharge()));
            return toAjax(bizAssessCadrePoliticalQualityIdentifyService.insertBizAssessCadrePoliticalQualityIdentify(bizAssessCadrePoliticalQualityIdentify));
        }
        return error("该用户当前年度已存在鉴定表！！");
    }

    /**
     * 修改政治素质鉴定
     */
//    @PreAuthorize("@ss.hasPermi('system:identify:edit')")
    @Log(title = "政治素质鉴定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify)
    {
        SysUser sysUser = userService.selectUserById(bizAssessCadrePoliticalQualityIdentify.getUserId());
        bizAssessCadrePoliticalQualityIdentify.setWorkPost(sysUser.getWorkPost());
        bizAssessCadrePoliticalQualityIdentify.setDeptId(sysUser.getDeptId());
        return toAjax(bizAssessCadrePoliticalQualityIdentifyService.updateBizAssessCadrePoliticalQualityIdentify(bizAssessCadrePoliticalQualityIdentify));
    }
    //上报
    @PutMapping("/report")
    public AjaxResult report(@RequestBody BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify)
    {
        bizAssessCadrePoliticalQualityIdentify.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        bizAssessCadrePoliticalQualityIdentify.setIsReport("1");
        return toAjax(bizAssessCadrePoliticalQualityIdentifyService.updateSetIsReport(bizAssessCadrePoliticalQualityIdentify));
    }

    /**
     * 删除政治素质鉴定
     */
//    @PreAuthorize("@ss.hasPermi('system:identify:remove')")
    @Log(title = "政治素质鉴定", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bizAssessCadrePoliticalQualityIdentifyService.deleteBizAssessCadrePoliticalQualityIdentifyByIds(ids));
    }
}
