package com.cb.basedata.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.poi.excel.ExcelReader;
import com.cb.basedata.domain.*;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.basedata.service.IBasRentedOutService;
import com.cb.basedata.service.IBasRentedOutSourceService;
import com.cb.common.utils.DateUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 代码-昆明市主城区已配租数据Controller
 * 
 * @author ruoyi
 * @date 2024-10-30
 */
@RestController
@RequestMapping("/basedata/basRentedOutSource")
public class BasRentedOutSourceController extends BaseController
{
    @Autowired
    private IBasRentedOutSourceService basRentedOutSourceService;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;
    @Resource
    private IBasRentedOutService basRentedOutService;
    /**
     * 查询代码-昆明市主城区已配租数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasRentedOutSource basRentedOutSource)
    {
        startPage();
        List<BasRentedOutSource> list = basRentedOutSourceService.selectBasRentedOutSourceList(basRentedOutSource);
        return getDataTable(list);
    }
    /**
     * 导出代码-昆明市主城区已配租数据列表
     */
    @Log(title = "代码-昆明市主城区已配租数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasRentedOutSource basRentedOutSource)
    {
        List<BasRentedOutSource> list = basRentedOutSourceService.selectBasRentedOutSourceList(basRentedOutSource);
        ExcelUtil<BasRentedOutSource> util = new ExcelUtil<BasRentedOutSource>(BasRentedOutSource.class);
        return util.exportExcel(list, "source");
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
        List<BasRentedOutSource> basRentedOutSources = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : collect) {
            BasRentedOutSource basNotRentedOut = new BasRentedOutSource();
            basNotRentedOut.setSourceId(UUID.randomUUID().toString());
            basNotRentedOut.setPropertiesSale(stringObjectMap.get("楼盘名称")==null?"":stringObjectMap.get("楼盘名称").toString());
            basNotRentedOut.setBuilding(stringObjectMap.get("楼栋").toString());
            basNotRentedOut.setUnit(stringObjectMap.get("单元").toString());
            basNotRentedOut.setRoomNumber(stringObjectMap.get("房号").toString());
            String string = stringObjectMap.get("月租金")==null?"0":stringObjectMap.get("月租金").toString();
            //string转小数再转分
//            string = String.format("%.2f", Double.parseDouble(string)).replace(".", "");
            basNotRentedOut.setMonthlyRent(string);
            basNotRentedOut.setImportTime(DateUtils.getNowDate());
            //写入姓名
            basNotRentedOut.setName01(stringObjectMap.get("姓名")==null?"":stringObjectMap.get("姓名").toString());
            basNotRentedOut.setName02(stringObjectMap.get("共同申请人1")==null?"":stringObjectMap.get("共同申请人1").toString());
            basNotRentedOut.setName03(stringObjectMap.get("共同申请人2")==null?"":stringObjectMap.get("共同申请人2").toString());
            basNotRentedOut.setName04(stringObjectMap.get("共同申请人3")==null?"":stringObjectMap.get("共同申请人3").toString());
            basNotRentedOut.setName05(stringObjectMap.get("共同申请人4")==null?"":stringObjectMap.get("共同申请人4").toString());
            basNotRentedOut.setName06(stringObjectMap.get("共同申请人5")==null?"":stringObjectMap.get("共同申请人5").toString());
//            basNotRentedOut.setName07(stringObjectMap.get("姓名6")==null?"":stringObjectMap.get("姓名6").toString());
//            basNotRentedOut.setName08(stringObjectMap.get("姓名7")==null?"":stringObjectMap.get("姓名7").toString());
            basNotRentedOut.setIdcard01(stringObjectMap.get("身份证号")==null?"":stringObjectMap.get("身份证号").toString());
            basNotRentedOut.setIdcard02(stringObjectMap.get("身份证号1")==null?"":stringObjectMap.get("身份证号1").toString());
            basNotRentedOut.setIdcard03(stringObjectMap.get("身份证号2")==null?"":stringObjectMap.get("身份证号2").toString());
            basNotRentedOut.setIdcard04(stringObjectMap.get("身份证号3")==null?"":stringObjectMap.get("身份证号3").toString());
            basNotRentedOut.setIdcard05(stringObjectMap.get("身份证号4")==null?"":stringObjectMap.get("身份证号4").toString());
            basNotRentedOut.setIdcard06(stringObjectMap.get("身份证号5")==null?"":stringObjectMap.get("身份证号5").toString());
//            basNotRentedOut.setIdcard07(stringObjectMap.get("身份证号6")==null?"":stringObjectMap.get("身份证号6").toString());
//            basNotRentedOut.setIdcard08(stringObjectMap.get("身份证号7")==null?"":stringObjectMap.get("身份证号7").toString());

            basNotRentedOut.setNumberApplicants(stringObjectMap.get("申请人数量")==null?0:Integer.parseInt(stringObjectMap.get("申请人数量").toString()));
            basNotRentedOut.setCreateTime(DateUtils.getNowDate());
            basRentedOutSources.add(basNotRentedOut);
//            basRentedOutSourceService.insertBasRentedOutSource(basNotRentedOut);
        }
        for (BasRentedOutSource basRentedOutSource : basRentedOutSources) {
            basRentedOutSourceService.insertBasRentedOutSource(basRentedOutSource);
        }
//        basRentedOutSourceService.insertBasRentedOutSourceBatch(basRentedOutSources);
    }

    //导出模板
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BasRentedOutSource> util = new ExcelUtil<>(BasRentedOutSource.class);
        return util.importTemplateExcel("已配租");
    }


    @PostMapping(value = "/cleanData")
    public AjaxResult cleanData(@RequestBody BasRentedOutSource basRentedOutSource)
    {
        basRentedOutSource=basRentedOutSourceService.selectBasRentedOutSourceById(basRentedOutSource.getSourceId());
        //怎么清洗呢，这里只是将楼盘和区域进行相似度比对
        BasBuildingCode basBuildingCode = new BasBuildingCode();
        List<BasBuildingCode> basBuildingCodes = basBuildingCodeService.selectBasBuildingCodeList(basBuildingCode);
        //按楼盘名称
        Map<String, String> collect = basBuildingCodes.stream().collect(Collectors.toMap(BasBuildingCode::getName, BasBuildingCode::getId));
        //洗数据
        String s = null;
        //按楼盘
        s=collect.get(basRentedOutSource.getPropertiesSale());
        BasRentedOut basRentedOut = new BasRentedOut();
        BeanUtils.copyProperties(basRentedOutSource, basRentedOut);
        //从字符串中取出数字
        String unit = basRentedOutSource.getUnit();
        unit = unit.replaceAll("[^0-9.]", "");
        basRentedOut.setUnit(unit);
        //设置月租金
        basRentedOut.setId(com.cb.common.utils.uuid.UUID.randomUUID().toString());
        basRentedOut.setSourceId(basRentedOutSource.getSourceId());
        basRentedOut.setAreaId(s);
        basRentedOutService.insertBasRentedOut(basRentedOut);
        return AjaxResult.success();
    }
    /**
     * 获取代码-昆明市主城区已配租数据详细信息
     */
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId)
    {
        return AjaxResult.success(basRentedOutSourceService.selectBasRentedOutSourceById(sourceId));
    }

    /**
     * 新增代码-昆明市主城区已配租数据
     */
    @Log(title = "代码-昆明市主城区已配租数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasRentedOutSource basRentedOutSource)
    {
        return toAjax(basRentedOutSourceService.insertBasRentedOutSource(basRentedOutSource));
    }

    /**
     * 修改代码-昆明市主城区已配租数据
     */
    @Log(title = "代码-昆明市主城区已配租数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasRentedOutSource basRentedOutSource)
    {
        return toAjax(basRentedOutSourceService.updateBasRentedOutSource(basRentedOutSource));
    }

    /**
     * 删除代码-昆明市主城区已配租数据
     */
    @Log(title = "代码-昆明市主城区已配租数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sourceIds}")
    public AjaxResult remove(@PathVariable String[] sourceIds)
    {
        return toAjax(basRentedOutSourceService.deleteBasRentedOutSourceByIds(sourceIds));
    }

    @GetMapping("/getBasRentedOuDataCompare/{sourceId}")
    public AjaxResult getBasNotRentedOuDataCompare(@PathVariable("sourceId") String sourceId)
    {
        BasRentedOut basRentedOut = basRentedOutService.selectBasNotRentedOutBySourceId(sourceId);
        BasRentedOutSource basRentedOutSource = basRentedOutSourceService.selectBasRentedOutSourceById(sourceId);
        BasBuildingCode basBuildingCode = basBuildingCodeService.selectBasBuildingCodeById(basRentedOut.getAreaId());
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("basRentedOutSource", basRentedOutSource);
        stringObjectHashMap.put("basRentedOut", basRentedOut);
        stringObjectHashMap.put("basBuildingCode", basBuildingCode);
        return AjaxResult.success(stringObjectHashMap);
    }
}
