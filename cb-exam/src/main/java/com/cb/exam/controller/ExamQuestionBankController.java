package com.cb.exam.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.ExamQuestionBank;
import com.cb.exam.service.IExamQuestionBankService;
import com.cb.exam.vo.ExamBankQuestionCountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题库Controller
 *
 * @author hu
 * @date 2023-11-01
 */
@Slf4j
@RestController
@RequestMapping("/exam/bank")
public class ExamQuestionBankController extends BaseController {
    @Autowired
    private IExamQuestionBankService examQuestionBankService;

    /**
     * 查询题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestionBank examQuestionBank) {
        //List<ExamQuestionBank> list = examQuestionBankService.selectExamQuestionBankList(examQuestionBank);
        List<ExamQuestionBank> list = examQuestionBankService.selectExamQuestionBankList(examQuestionBank);
        return getDataTable(list);
    }

    /**
     * 查询题库 下拉框
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:list')")
    @GetMapping("/listByRole")
    public TableDataInfo listByRole(ExamQuestionBank examQuestionBank) {
        //List<ExamQuestionBank> list = examQuestionBankService.selectExamQuestionBankList(examQuestionBank);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        List<Long> roleIds = new LinkedList<>();
        if(null != roles){
            roleIds = roles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        }
        if(roles.isEmpty()){
            return getDataTable(Collections.emptyList());
        }
        if(user.getUserName().equals("admin")){
            roleIds.add(20L);
        }
        List<ExamQuestionBank> list = examQuestionBankService.selectExamQuestionBankListByRole(roleIds); // getUserId
        return getDataTable(list);
    }

    /**
     * 查询题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:list')")
    @GetMapping("/listCount")
    public TableDataInfo listCount(ExamBankQuestionCountVo examBankQuestionCountVo) {
        startPage();
        //List<ExamQuestionBank> list = examQuestionBankService.selectExamQuestionBankList(examQuestionBank);
        List<ExamBankQuestionCountVo> list = examQuestionBankService.selectExamBankQuestionCountList(examBankQuestionCountVo);
        return getDataTable(list);
    }

    /**
     * 查询题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:list')")
    @GetMapping("/listByQuestionId/{questionId}")
    public TableDataInfo list(@PathVariable("questionId") Long questionId) {
        startPage();
        List<ExamQuestionBank> list = examQuestionBankService.selectBankByQuestionIdList(questionId);
        return getDataTable(list);
    }

    /**
     * 导出题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:export')")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestionBank examQuestionBank) {
        List<ExamQuestionBank> list = examQuestionBankService.selectExamQuestionBankList(examQuestionBank);
        ExcelUtil<ExamQuestionBank> util = new ExcelUtil<ExamQuestionBank>(ExamQuestionBank.class);
        return util.exportExcel(list, "题库数据");
    }

    /**
     * 导出题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:export')")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @GetMapping("/getstr")
    public AjaxResult getstr() {
        return AjaxResult.success("ModelData_1655717035335.csv");
    }

    /**
     * 获取题库详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(examQuestionBankService.selectExamQuestionBankById(id));
    }

    /**
     * 新增题库
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:add')")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamQuestionBank examQuestionBank) {
        return toAjax(examQuestionBankService.insertExamQuestionBank(examQuestionBank));
    }

    /**
     * 修改题库
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:edit')")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamQuestionBank examQuestionBank) {
        return toAjax(examQuestionBankService.updateExamQuestionBank(examQuestionBank));
    }

    /**
     * 删除题库
     */
    @PreAuthorize("@ss.hasPermi('exam:bank:remove')")
    @Log(title = "题库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(examQuestionBankService.deleteExamQuestionBankByIds(ids));
    }


    @Log(title = "文件的解析导入数据库的功能")
    public AjaxResult submit() {

        /**
         * 校验路径下的文件是否符合要求
         */

        /**
         * 校验模型是否可用
         */

        /**
         * 拼接SQL
         */

        /**
         * 物理服务 上传到数据库
         */
        return AjaxResult.success();

    }


}
