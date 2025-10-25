package com.cb.web.controller.system;


import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.system.service.ISysUserInfoStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户信息统计
 *
 * @author hu
 */
@RestController
@RequestMapping("/userInfoStatistics")
public class SysUserInfoStatisticsController {
    @Autowired
    private ISysUserInfoStatisticsService userInfoStatisticsService;

    /**
     * 部门人员性别（男/女）人员统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/sexCount", "/sexCount/{deptId}"} )
    public AjaxResult selectSexCount(@PathVariable(value = "deptId", required = false) Long deptId) {

        List<Map<String, Object>> selectSexCount = userInfoStatisticsService.selectSexCount(deptId);
        return AjaxResult.success(selectSexCount);
    }

    /**
     * 政治面貌统计 党员、群众数量
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectPartyCount", "/selectPartyCount/{deptId}"} )
    public AjaxResult selectPartyCount(@PathVariable(value = "deptId", required = false) Long deptId) {

        List<Map<String, Object>> selectPartyCount = userInfoStatisticsService.selectPartyCount(deptId);
        return AjaxResult.success(selectPartyCount);
    }

    /**
     * 年龄阶段统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectAgeCount", "/selectAgeCount/{deptId}"} )
    public AjaxResult selectAgeCount(@PathVariable(value = "deptId", required = false) Long deptId) {

        List<Map<String, Object>> selectAgeCount = userInfoStatisticsService.selectAgeCount(deptId);
        return AjaxResult.success(selectAgeCount);
    }

    /**
     * 职级统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectWorkPostCount", "/selectWorkPostCount/{deptId}"} )
    public AjaxResult selectWorkPostCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectWorkPostCount = userInfoStatisticsService.selectWorkPostCount(deptId);
        return AjaxResult.success(selectWorkPostCount);
    }

    /**
     * 编制类型统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectIdentityTypeCount", "/selectIdentityTypeCount/{deptId}"} )
    public AjaxResult selectIdentityTypeCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectIdentityTypeCount = userInfoStatisticsService.selectIdentityTypeCount(deptId);
        return AjaxResult.success(selectIdentityTypeCount);
    }


    /**
     * 学历统计
     * */
    @GetMapping(value = {"/selectEduCount", "/selectEduCount/{deptId}"} )
    public AjaxResult selectEduCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectEduCount = userInfoStatisticsService.selectEduCount(deptId);
        return AjaxResult.success(selectEduCount);
    }


    /**
     * 现任领导职务年限统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectAppointmentCount", "/selectAppointmentCount/{deptId}"} )
    public AjaxResult selectAppointmentCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectAppointmentCount = userInfoStatisticsService.selectAppointmentCount(deptId);
        return AjaxResult.success(selectAppointmentCount);
    }

    /**
     * 职务任职年龄段统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectPostAgeCount", "/selectPostAgeCount/{deptId}"} )
    public AjaxResult selectPostAgeCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectPostAgeCount = userInfoStatisticsService.selectPostAgeCount(deptId);
        return AjaxResult.success(selectPostAgeCount);
    }

    /**
     * 职级数量统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectWorkPostNumCount", "/selectWorkPostNumCount/{deptId}"} )
    public AjaxResult selectWorkPostNumCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectWorkPostNumCount = userInfoStatisticsService.selectWorkPostNumCount(deptId);
        return AjaxResult.success(selectWorkPostNumCount);
    }



    /**
     * 事业职务（职称）职级数量统计
     * @param deptId
     * @return
     */
    @GetMapping(value = {"/selectShiYeWorkPostCount", "/selectShiYeWorkPostCount/{deptId}"} )
    public AjaxResult selectShiYeWorkPostCount(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<Map<String, Object>> selectShiYeWorkPostCount = userInfoStatisticsService.selectShiYeWorkPostCount(deptId);
        return AjaxResult.success(selectShiYeWorkPostCount);
    }

    @GetMapping(value = {"/selectAbilityLabelCount"})
    public  AjaxResult selectAbilityLabelCount(){
        List<Map<String, Object>> selectAbilityLabelCount = userInfoStatisticsService.selectAbilityLabelCount();
        return AjaxResult.success(selectAbilityLabelCount);
    }
}
