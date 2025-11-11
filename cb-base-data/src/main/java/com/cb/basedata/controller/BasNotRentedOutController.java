package com.cb.basedata.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.domain.BasNotRentedOut;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.basedata.service.IBasNotRentedOutService;
import com.cb.cadretrain.domain.BizTrainRecords;
import com.cb.common.core.domain.entity.SysDictData;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 代码-昆明市主城区未配租数据Controller
 * 
 * @author ruoyi
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/basedata/basNotRentedOut")
public class BasNotRentedOutController extends BaseController
{
    @Autowired
    private IBasNotRentedOutService basNotRentedOutService;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;
    /**
     * 查询代码-昆明市主城区未配租数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasNotRentedOut basNotRentedOut)
    {
        startPage();
        List<BasNotRentedOut> list = basNotRentedOutService.selectBasNotRentedOutList(basNotRentedOut);
        return getDataTable(list);
    }
    @PostMapping("/import")
    public void imPort(MultipartFile file, HttpServletResponse response) throws IOException {

        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(file.getInputStream());
        List<Map<String, Object>> readAll = reader.read(0, 1, Integer.MAX_VALUE);
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
        BasBuildingCode basBuildingCode = new BasBuildingCode();
        List<BasBuildingCode> basBuildingCodes = basBuildingCodeService.selectBasBuildingCodeList(basBuildingCode);
        Map<String, String> buildingCodeMap = basBuildingCodes.stream().collect(Collectors.toMap(BasBuildingCode::getName, BasBuildingCode::getId, (k1, k2) -> k1));
        System.out.println();
        for (Map<String, Object> stringObjectMap : collect) {
            BasNotRentedOut basNotRentedOut = new BasNotRentedOut();
            basNotRentedOut.setId(UUID.randomUUID().toString());
            Object o = buildingCodeMap.get(stringObjectMap.get("区域"));
            basNotRentedOut.setAreaId(o==null?buildingCodeMap.get("区域"):o.toString());
            basNotRentedOut.setPropertiesSale(stringObjectMap.get("楼盘").toString());
            basNotRentedOut.setBuilding(stringObjectMap.get("楼栋").toString());
            basNotRentedOut.setUnit(stringObjectMap.get("单元").toString());
            basNotRentedOut.setRoomNumber(stringObjectMap.get("房号").toString());
            String string = stringObjectMap.get("月租金").toString();
            //string转小数再转分
            string = String.format("%.2f", Double.parseDouble(string)).replace(".", "");
            basNotRentedOut.setMonthlyRent(Integer.parseInt(string));
            basNotRentedOut.setImportTime(DateUtils.getNowDate());
            basNotRentedOutService.insertBasNotRentedOut(basNotRentedOut);
        }
        return;
//        if (errList.size() > 0){
//            //最后，将导入失败的名单再返回下载回去
//            ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter();
//            writer.write(errList, true);
//            // 关闭writer，释放内存
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//            response.setHeader("Content-Disposition","attachment;filename=test.xlsx");
//            ServletOutputStream out=response.getOutputStream();
//
//            writer.flush(out, true);
//            writer.close();
//        }
    }
    /**
     * 导出代码-昆明市主城区未配租数据列表
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasNotRentedOut basNotRentedOut)
    {
        List<BasNotRentedOut> list = basNotRentedOutService.selectBasNotRentedOutList(basNotRentedOut);
        ExcelUtil<BasNotRentedOut> util = new ExcelUtil<BasNotRentedOut>(BasNotRentedOut.class);
        return util.exportExcel(list, "out");
    }

    /**
     * 获取代码-昆明市主城区未配租数据详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basNotRentedOutService.selectBasNotRentedOutById(id));
    }

    /**
     * 新增代码-昆明市主城区未配租数据
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasNotRentedOut basNotRentedOut)
    {
        basNotRentedOut.setImportTime(DateUtils.getNowDate());
        return toAjax(basNotRentedOutService.insertBasNotRentedOut(basNotRentedOut));
    }

    /**
     * 修改代码-昆明市主城区未配租数据
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasNotRentedOut basNotRentedOut)
    {
        return toAjax(basNotRentedOutService.updateBasNotRentedOut(basNotRentedOut));
    }

    /**
     * 删除代码-昆明市主城区未配租数据
     */
    @Log(title = "代码-昆明市主城区未配租数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basNotRentedOutService.deleteBasNotRentedOutByIds(ids));
    }
}
