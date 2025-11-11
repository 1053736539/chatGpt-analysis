package com.cb.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.ServletUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.user.domain.CensusUserInfo;
import com.cb.user.service.ICensusUserInfoService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * 普查人员Controller
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@RestController
@RequestMapping("census/info")
public class CensusUserInfoController extends BaseController
{
    @Autowired
    private ICensusUserInfoService censusUserInfoService;

    /**
     * 查询普查人员列表
     */
    @PreAuthorize("@ss.hasPermi('censusUser:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(CensusUserInfo censusUserInfo)
    {
        startPage();
        censusUserInfo.setDelFlag("0");
        List<CensusUserInfo> list = censusUserInfoService.selectCensusUserInfoList(censusUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出普查人员列表
     */
    @PreAuthorize("@ss.hasPermi('censusUser:info:export')")
    @Log(title = "普查人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CensusUserInfo censusUserInfo)
    {
        List<CensusUserInfo> list = censusUserInfoService.selectCensusUserInfoList(censusUserInfo);
        ExcelUtil<CensusUserInfo> util = new ExcelUtil<CensusUserInfo>(CensusUserInfo.class);
        return util.exportExcel(list, "普查人员信息");
    }

    @Log(title = "普查人员信息导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('censusUser:info:import')")
    @PostMapping("/importCensusUserInfoData")
    public AjaxResult importCensusUserInfoData(MultipartFile file) throws Exception {
        ExcelUtil<CensusUserInfo> util = new ExcelUtil<CensusUserInfo>(CensusUserInfo.class);
        // 读取第一个工作表
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        String sheetName = sheet.getSheetName();
        // 根据工作表名称设置 censusNumber
        int censusNumber = getCensusNumberFromSheetName(sheetName);
        List<CensusUserInfo> censusUserList = util.importExcel(file.getInputStream());
        String message = censusUserInfoService.importCensusUserInfo1(censusUserList,censusNumber);
        return AjaxResult.success(message);
    }
    private int getCensusNumberFromSheetName(String sheetName) {
        if (sheetName.contains("四经普")) {
            return 4;
        } else if (sheetName.contains("五经普")) {
            return 5;
        }
        // 可以继续添加其他经普人员的判断
        throw new IllegalArgumentException("无法识别的工作表名称: " + sheetName);
    }

    @GetMapping("/importCensusUserInfoTemplate")
    public AjaxResult importCensusUserInfoTemplate() {
        ExcelUtil<CensusUserInfo> util = new ExcelUtil<CensusUserInfo>(CensusUserInfo.class);
        return util.importTemplateExcel("普查人员信息数据");
    }

    /**
     * 获取普查人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('censusUser:info:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return AjaxResult.success(censusUserInfoService.selectCensusUserInfoById(userId));
    }

    /**
     * 新增普查人员
     */
    @PreAuthorize("@ss.hasPermi('censusUser:info:add')")
    @Log(title = "普查人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CensusUserInfo censusUserInfo)
    {
        censusUserInfo.setUserId(IdUtils.randomUUID());
        censusUserInfo.setCreateTime(new Date());
        censusUserInfo.setUpdateTime(new Date());
        censusUserInfo.setDelFlag("0");
        return toAjax(censusUserInfoService.insertCensusUserInfo(censusUserInfo));
    }

    /**
     * 修改普查人员
     */
    @PreAuthorize("@ss.hasPermi('censusUser:info:edit')")
    @Log(title = "普查人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CensusUserInfo censusUserInfo)
    {
        return toAjax(censusUserInfoService.updateCensusUserInfo(censusUserInfo));
    }

    /**
     * 删除普查人员
     */
    @PreAuthorize("@ss.hasPermi('censusUser:info:remove')")
    @Log(title = "普查人员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        List<CensusUserInfo> censusUserInfos = new ArrayList<>();
        for (String userId : userIds) {
            CensusUserInfo censusUserInfo = new CensusUserInfo();
            censusUserInfo.setUserId(userId);
            censusUserInfo.setDelFlag("1");
            censusUserInfos.add(censusUserInfo);
            censusUserInfoService.updateCensusUserInfo(censusUserInfo);
        }
        return toAjax(true);
    }
}
