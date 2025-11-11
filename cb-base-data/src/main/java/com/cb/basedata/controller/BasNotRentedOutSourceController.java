package com.cb.basedata.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.poi.excel.ExcelReader;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.domain.BasGasCostSource;
import com.cb.basedata.domain.BasNotRentedOut;
import com.cb.basedata.domain.BasNotRentedOutSource;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.basedata.service.IBasNotRentedOutService;
import com.cb.basedata.service.IBasNotRentedOutSourceService;
import com.cb.common.utils.DateUtils;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码-昆明市主城区未配租数据Controller
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
@RestController
@RequestMapping("/basedata/basNotRentedOutSource")
public class BasNotRentedOutSourceController extends BaseController
{
    @Autowired
    private IBasNotRentedOutSourceService basNotRentedOutSourceService;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;
    @Autowired
    private IBasNotRentedOutService basNotRentedOutService;
    /**
     * 查询代码-昆明市主城区未配租数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasNotRentedOutSource basNotRentedOutSource)
    {
        startPage();
        List<BasNotRentedOutSource> list = basNotRentedOutSourceService.selectBasNotRentedOutSourceList(basNotRentedOutSource);
        return getDataTable(list);
    }
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasNotRentedOutSource> util = new ExcelUtil<>(BasNotRentedOutSource.class);
        return util.importTemplateExcel("未配租");
    }
    @PostMapping("/import")
    public void imPort(MultipartFile file, HttpServletResponse response) throws IOException{
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(file.getInputStream());
        List<Map<String, Object>> readAll = reader.read(0, 0, Integer.MAX_VALUE);
        List<Map<String, Object>> collect = readAll.stream().map(map -> {
            //将map的key去除两边空格和换行符
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : entries) {
                String s = entry.getKey().replaceAll("\\n", "").trim();
                stringObjectHashMap.put(s, entry.getValue());
            }
            return stringObjectHashMap;
        }).collect(Collectors.toList());
        List<BasNotRentedOutSource> collect1 = collect.stream().map(stringObjectMap -> {
            BasNotRentedOutSource basNotRentedOut = new BasNotRentedOutSource();
            basNotRentedOut.setSourceId(UUID.randomUUID().toString());
            Object o = stringObjectMap.get("区域");
            basNotRentedOut.setAreaId(o == null ? "" : o.toString());
            basNotRentedOut.setProjectName(stringObjectMap.get("项目名称") == null ? "" : stringObjectMap.get("项目名称").toString());
            basNotRentedOut.setPropertiesSale(stringObjectMap.get("楼盘") == null ? "" : stringObjectMap.get("楼盘").toString());
            basNotRentedOut.setBuilding(stringObjectMap.get("楼栋") == null ? "" : stringObjectMap.get("楼栋").toString());
            basNotRentedOut.setUnit(stringObjectMap.get("单元") == null ? "" : stringObjectMap.get("单元").toString());
            basNotRentedOut.setRoomNumber(stringObjectMap.get("房号") == null ? "" : stringObjectMap.get("房号").toString());
            String string = stringObjectMap.get("月租金") == null ? "0" : stringObjectMap.get("月租金").toString();
            //string转小数再转分
            string = String.format("%.2f", Double.parseDouble(string)).replace(".", "");
            basNotRentedOut.setMonthlyRent(Integer.parseInt(string));
            basNotRentedOut.setImportTime(DateUtils.getNowDate());
            basNotRentedOut.setCreateTime(DateUtils.getNowDate());
            basNotRentedOut.setUpdateTime(DateUtils.getNowDate());
            return basNotRentedOut;
        }).collect(Collectors.toList());
        basNotRentedOutSourceService.insertBasNotRentedOutSourceBatch(collect1);
    }
    /**
     * 导出代码-昆明市主城区未配租数据列表
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasNotRentedOutSource basNotRentedOutSource)
    {
        List<BasNotRentedOutSource> list = basNotRentedOutSourceService.selectBasNotRentedOutSourceList(basNotRentedOutSource);
        ExcelUtil<BasNotRentedOutSource> util = new ExcelUtil<BasNotRentedOutSource>(BasNotRentedOutSource.class);
        return util.exportExcel(list, "source");
    }

    @PostMapping(value = "/cleanData")
    public AjaxResult cleanData(@RequestBody BasNotRentedOutSource basNotRentedOutSource)
    {
         basNotRentedOutSource = basNotRentedOutSourceService.selectBasNotRentedOutSourceById(basNotRentedOutSource.getSourceId());
         //怎么清洗呢，这里只是将楼盘和区域进行相似度比对
        BasBuildingCode basBuildingCode = new BasBuildingCode();
        List<BasBuildingCode> basBuildingCodes = basBuildingCodeService.selectBasBuildingCodeList(basBuildingCode);
        //按楼盘名称
        Map<String, String> collect = basBuildingCodes.stream().collect(Collectors.toMap(BasBuildingCode::getName, BasBuildingCode::getId));
        //洗数据
        //按项目名称
        String s = collect.get(basNotRentedOutSource.getProjectName());
        //按楼盘
        s=s==null? collect.get(basNotRentedOutSource.getPropertiesSale()):s;
        BasNotRentedOut basNotRentedOut = new BasNotRentedOut();
        BeanUtils.copyProperties(basNotRentedOutSource, basNotRentedOut);
        basNotRentedOut.setId(com.cb.common.utils.uuid.UUID.randomUUID().toString());
        basNotRentedOut.setSourceId(basNotRentedOutSource.getSourceId());
        basNotRentedOut.setAreaId(s);
        basNotRentedOutService.insertBasNotRentedOut(basNotRentedOut);
        return AjaxResult.success();
    }
    /**
     * 获取代码-昆明市主城区未配租数据详细信息
     */
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId)
    {
        return AjaxResult.success(basNotRentedOutSourceService.selectBasNotRentedOutSourceById(sourceId));
    }

    /**
     * 新增代码-昆明市主城区未配租数据
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasNotRentedOutSource basNotRentedOutSource)
    {
        return toAjax(basNotRentedOutSourceService.insertBasNotRentedOutSource(basNotRentedOutSource));
    }

    /**
     * 修改代码-昆明市主城区未配租数据
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasNotRentedOutSource basNotRentedOutSource)
    {
        return toAjax(basNotRentedOutSourceService.updateBasNotRentedOutSource(basNotRentedOutSource));
    }

    /**
     * 删除代码-昆明市主城区未配租数据
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sourceIds}")
    public AjaxResult remove(@PathVariable String[] sourceIds)
    {
        return toAjax(basNotRentedOutSourceService.deleteBasNotRentedOutSourceByIds(sourceIds));
    }
    @GetMapping("/getBasNotRentedOuDataCompare/{sourceId}")
    public AjaxResult getBasNotRentedOuDataCompare(@PathVariable("sourceId") String sourceId)
    {
        BasNotRentedOutSource basNotRentedOutSource = basNotRentedOutSourceService.selectBasNotRentedOutSourceById(sourceId);
        BasNotRentedOut basNotRentedOut = basNotRentedOutService.selectBasNotRentedOutBySourceId(sourceId);
        BasBuildingCode basBuildingCode = basBuildingCodeService.selectBasBuildingCodeById(basNotRentedOut.getAreaId());
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("basNotRentedOutSource", basNotRentedOutSource);
        stringObjectHashMap.put("basNotRentedOut", basNotRentedOut);
        stringObjectHashMap.put("basBuildingCode", basBuildingCode);
        return AjaxResult.success(stringObjectHashMap);
    }
}
