package com.cb.web.controller.salary;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.ServletUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.framework.web.service.TokenService;
import com.cb.salary.domain.Salary;
import com.cb.salary.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 工资条Controller
 *
 * @author hujilie
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/salary/salary")
public class SalaryController extends BaseController
{
    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询工资条列表
     */
    @PreAuthorize("@ss.hasPermi('salary:salary:list')")
    @GetMapping("/list")
    public TableDataInfo list(Salary salary)
    {
        startPage();
        String month = salary.getMonth();
        if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
            String[] temp = month.split("-");
            salary.setYear(temp[0]);
            salary.setMonth(temp[1]);
        }
        List<Salary> list = salaryService.selectSalaryList(salary);
        return getDataTable(list);
    }

    @GetMapping("/myList")
    public TableDataInfo myList(Salary salary)
    {
        startPage();
        String month = salary.getMonth();
        if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
            String[] temp = month.split("-");
            salary.setYear(temp[0]);
            salary.setMonth(temp[1]);
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        salary.setUserId(loginUser.getUser().getUserId());
        salary.setStatus(1);

        List<Salary> list = salaryService.selectSalaryList(salary);
        return getDataTable(list);
    }

    /**
     * 导出工资条列表
     */
    @PreAuthorize("@ss.hasPermi('salary:salary:export')")
    @Log(title = "工资条", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Salary salary)
    {
        String month = salary.getMonth();
        if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
            String[] temp = month.split("-");
            salary.setYear(temp[0]);
            salary.setMonth(temp[1]);
        }
        List<Salary> list = salaryService.selectSalaryList(salary);
        ExcelUtil<Salary> util = new ExcelUtil<Salary>(Salary.class);
        return util.exportExcel(list, "salary");
    }
    @Log(title = "工资条", businessType = BusinessType.EXPORT)
    @GetMapping("/exportMySalary")
    public AjaxResult exportMySalary(Salary salary)
    {
        String month = salary.getMonth();
        if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
            String[] temp = month.split("-");
            salary.setYear(temp[0]);
            salary.setMonth(temp[1]);
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        salary.setUserId(loginUser.getUser().getUserId());
        salary.setStatus(1);
        List<Salary> list = salaryService.selectSalaryList(salary);
        ExcelUtil<Salary> util = new ExcelUtil<Salary>(Salary.class);
        return util.exportExcel(list, "salary");
    }

    /**
     * 获取工资条详细信息
     */
    @PreAuthorize("@ss.hasPermi('salary:salary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(salaryService.selectSalaryById(id));
    }

    /**
     * 新增工资条
     */
    @PreAuthorize("@ss.hasPermi('salary:salary:add')")
    @Log(title = "工资条", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Salary salary)
    {
        return toAjax(salaryService.insertSalary(salary));
    }

    /**
     * 修改工资条
     */
    @PreAuthorize("@ss.hasPermi('salary:salary:edit')")
    @Log(title = "工资条", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Salary salary)
    {
        return toAjax(salaryService.updateSalary(salary));
    }

    /**
     * 删除工资条
     */
    @PreAuthorize("@ss.hasPermi('salary:salary:remove')")
    @Log(title = "工资条", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(salaryService.deleteSalaryByIds(ids));
    }

    @Log(title = "工资条", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('work:situation:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport, String month) throws Exception {
        ExcelUtil<Salary> util = new ExcelUtil<Salary>(Salary.class);
        Map<String, List<Salary>> dataMap = util.importAllExcelSheet(file.getInputStream());
        List<Salary> dataList;
        Iterator<String> keys = dataMap.keySet().iterator();
        String sheetName;
        String msg = "";
        int userType;
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        while (keys.hasNext()) {
            sheetName = keys.next();
            dataList = dataMap.get(sheetName);
            if(msg.length() > 0) {
                msg += "\n\n";
            }
            if(sheetName.indexOf("行政") > -1) {
                userType = 1;
            } else {
                userType = 2;
            }
            msg += salaryService.importSalary(dataList, userType, updateSupport, month, operName);
        }
        return AjaxResult.success(msg);
    }

    @Log(title = "工资条", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody Salary salary)
    {
        return toAjax(salaryService.auditSalary(salary.getMonths()));
    }
}
