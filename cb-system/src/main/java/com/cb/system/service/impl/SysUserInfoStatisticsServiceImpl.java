package com.cb.system.service.impl;


import com.cb.common.core.domain.vo.GwyIdentityTypeVo;
import com.cb.common.core.domain.vo.VSysUser;
import com.cb.system.mapper.SysUserInfoStatisticsMapper;
import com.cb.system.mapper.SysUserMapper;
import com.cb.system.service.ISysUserInfoStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysUserInfoStatisticsServiceImpl implements ISysUserInfoStatisticsService {

    @Autowired
    private SysUserInfoStatisticsMapper userInfoStatisticsMapper;

    /**
     * 部门人员性别（男/女）人员统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectSexCount(Long deptId) {
        return userInfoStatisticsMapper.selectSexCount(deptId);
    }

    /**
     * 政治面貌统计 党员、群众数量
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectPartyCount(Long deptId) {
        return userInfoStatisticsMapper.selectPartyCount(deptId);
    }

    /**
     * 年龄阶段统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAgeCount(Long deptId) {
        return userInfoStatisticsMapper.selectAgeCount(deptId);
    }

    /**
     * 职级统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectWorkPostCount(Long deptId) {
        return userInfoStatisticsMapper.selectWorkPostCount(deptId);
    }


    /**
     * 编制类型统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectIdentityTypeCount(Long deptId) {
        return userInfoStatisticsMapper.selectIdentityTypeCount(deptId);
    }

    /***
     * 学历统计
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectEduCount(Long deptId) {
        return userInfoStatisticsMapper.selectEduCount(deptId);
    }

    /***
     * 现任职务领导年限统计
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAppointmentCount(Long deptId) {
        return userInfoStatisticsMapper.selectAppointmentCount(deptId);
    }

    /**
     * 职务任职年龄段统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectPostAgeCount(Long deptId) {
        return userInfoStatisticsMapper.selectPostAgeCount(deptId);
    }

    /**
     * 职级数量统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectWorkPostNumCount(Long deptId) {
        return userInfoStatisticsMapper.selectWorkPostNumCount(deptId);
    }

    /**
     * 事业职务（职称）职级数量统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectShiYeWorkPostCount(Long deptId) {
        return userInfoStatisticsMapper.selectShiYeWorkPostCount(deptId);
    }

    /**
     * 行政编制和事业编制分布情况统计
     *
     * @param deptId
     * @return
     */
    @Override
    public List<GwyIdentityTypeVo> selectIdentityTypeNumber(Long deptId) {
        return userInfoStatisticsMapper.selectIdentityTypeNumber(deptId);
    }

    /**
     * 行政编制和事业编制性别、民族、政治面貌、年龄、学历情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectXzbSybIdentityDistributionNum() {
        return userInfoStatisticsMapper.selectXzbSybIdentityDistributionNum();
    }

    /**
     * 副处级及以上行政编制和事业编制的性别、民族、政治情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpXzsybDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpXzsybDistributionNum();
    }

    /**
     * 副处级及以上行政编制和事业编制的年龄情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpXzsybAgeDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpXzsybAgeDistributionNum();
    }

    /**
     * 副处级及以上行政编制和事业编制的学历情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpXzsybEducationDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpXzsybEducationDistributionNum();
    }

    /**
     * 行政编职级统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectXzbzjNum() {
        return userInfoStatisticsMapper.selectXzbzjNum();
    }

    /**
     * 事业编职级统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectSybzjNum() {
        return userInfoStatisticsMapper.selectSybzjNum();
    }

    /**
     * 行政编制性别、民族、政治面貌、学历、年龄情况
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectXzbIdentityDistributionNum() {
        return userInfoStatisticsMapper.selectXzbIdentityDistributionNum();
    }

    /**
     * 参公性别、民族、政治面貌、学历、年龄情况
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCgIdentityDistributionNum() {
        return userInfoStatisticsMapper.selectCgIdentityDistributionNum();
    }

    @Override
    public Map<String, Integer> selectUserAgeStageNum(String [] identityType, String[] personnelStatus,List<VSysUser> userList) {
        List<String> identityTypeList = Arrays.asList(identityType);
        List<String> personnelStatusList = Arrays.asList(personnelStatus);
        Map<String, Integer> map = new HashMap<>();
        map.put("35BelowNum",calculationUserAgeStageNum(0, 35, identityTypeList, personnelStatusList, userList));
        map.put("36to40Num",calculationUserAgeStageNum(36, 40, identityTypeList, personnelStatusList, userList));
        map.put("41to45Num",calculationUserAgeStageNum(41, 45, identityTypeList, personnelStatusList, userList));
        map.put("46to50Num",calculationUserAgeStageNum(46, 50, identityTypeList, personnelStatusList, userList));
        map.put("51to54Num",calculationUserAgeStageNum(51, 54, identityTypeList, personnelStatusList, userList));
        map.put("55overNum",calculationUserAgeStageNum(55, 999, identityTypeList, personnelStatusList, userList));
        return map;
    }

    private Integer calculationUserAgeStageNum(Integer startAge, Integer endAge,
                                            List<String> identityTypeList, List<String> personnelStatusList,
                                                           List<VSysUser> list) {
        Long count = list.stream()
                .filter(u -> identityTypeList.contains(u.getIdentityType()) && (personnelStatusList.contains(u.getPersonnelStatus())))
                .filter(u -> {
                    Integer age = u.getAge();
                    return age >= startAge && age < endAge;
                }).count();
        return count.intValue();
    }

    /**
     * 参公职级数量统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCgzjNum() {
        return userInfoStatisticsMapper.selectCgzjNum();
    }

    /**
     * 副处级及以上行政编制性别、民族、政治面貌情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpXzbDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpXzbDistributionNum();
    }

    /**
     * 副处级及以上参公性别、民族、政治面貌情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpCgDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpCgDistributionNum();
    }

    /**
     * 副处级及以上行政编制和参公年龄情况数量统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpXzbcgAgeDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpXzbcgAgeDistributionNum();
    }

    /**
     * 副处级及以上行政编制学历情况数量统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpXzbEducationDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpXzbEducationDistributionNum();
    }

    /**
     * 副处级及以上参公学历情况数量统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpCgEducationDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpCgEducationDistributionNum();
    }

    /**
     * 事业编性别、民族、政治面貌、学历、年龄情况
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectSybIdentityDistributionNum() {
        return userInfoStatisticsMapper.selectSybIdentityDistributionNum();
    }

    /**
     * 副处级及以上事业编性别、民族、政治面貌情况统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpSybDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpSybDistributionNum();
    }

    /**
     * 副处级及以上事业编年龄情况数量统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> fcjUpSybAgeDistributionNum() {
        return userInfoStatisticsMapper.fcjUpSybAgeDistributionNum();
    }

    /**
     * 副处级及以上事业编学历情况数量统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFcjUpSybEducationDistributionNum() {
        return userInfoStatisticsMapper.selectFcjUpSybEducationDistributionNum();
    }

    @Override
    public List<Map<String, Object>> selectAbilityLabelCount() {
        return userInfoStatisticsMapper.selectAbilityLabelCount();
    }


}
