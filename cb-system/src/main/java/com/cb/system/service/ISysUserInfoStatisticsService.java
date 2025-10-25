package com.cb.system.service;

import com.cb.common.core.domain.vo.GwyIdentityTypeVo;
import com.cb.common.core.domain.vo.VSysUser;

import java.util.List;
import java.util.Map;

public interface ISysUserInfoStatisticsService {

    /**
     * 部门人员性别（男/女）人员统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectSexCount(Long deptId);

    /**
     * 政治面貌统计 党员、群众数量
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectPartyCount(Long deptId);

    /**
     * 年龄阶段统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectAgeCount(Long deptId);

    /**
     * 职级统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectWorkPostCount(Long deptId);


    /**
     * 编制类型统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectIdentityTypeCount(Long deptId);

    /**
     * 现任职务领导年限统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectAppointmentCount(Long deptId);

    /**
     * 学历统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectEduCount(Long deptId);

    /**
     * 职务年龄段统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectPostAgeCount(Long deptId);

    /**
     * 职级数量统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectWorkPostNumCount(Long deptId);

    /**
     * 事业职务（职称）职级数量统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectShiYeWorkPostCount(Long deptId);


    /**
     * 行政编制和事业编制分布情况统计
     * @param deptId
     * @return
     */
    public List<GwyIdentityTypeVo> selectIdentityTypeNumber(Long deptId);

    /**
     * 行政编制和事业编制性别、民族、政治面貌、年龄、学历情况统计
     * @return
     */
    public List<Map<String, Object>> selectXzbSybIdentityDistributionNum();

    /**
     * 副处级及以上行政编制和事业编制的性别、民族、政治情况统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpXzsybDistributionNum();

    /**
     * 副处级及以上行政编制和事业编制的年龄情况统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpXzsybAgeDistributionNum();

    /**
     * 副处级及以上行政编制和事业编制的学历情况统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpXzsybEducationDistributionNum();

    /**
     * 行政编职级统计
     * @return
     */
    public List<Map<String, Object>> selectXzbzjNum();

    /**
     * 事业编职级统计
     * @return
     */
    public List<Map<String, Object>> selectSybzjNum();

    /**
     * 行政编制性别、民族、政治面貌、学历、年龄情况
     * @return
     */
    public List<Map<String, Object>> selectXzbIdentityDistributionNum();

    /**
     * 参公性别、民族、政治面貌、学历、年龄情况
     * @return
     */
    public List<Map<String, Object>> selectCgIdentityDistributionNum();


    public Map<String, Integer> selectUserAgeStageNum(String [] identityType,String [] personnelStatus,List<VSysUser> userList);

    /**
     * 参公职级数量统计
     * @return
     */
    public List<Map<String, Object>> selectCgzjNum();

    /**
     * 副处级及以上行政编制1性别、民族、政治面貌情况统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpXzbDistributionNum();

    /**
     * 副处级及以上参公性别、民族、政治面貌情况统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpCgDistributionNum();

    /**
     * 副处级及以上行政编制和参公年龄情况数量统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpXzbcgAgeDistributionNum();

    /**
     * 副处级及以上行政编制学历情况数量统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpXzbEducationDistributionNum();

    /**
     * 副处级及以上参公学历情况数量统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpCgEducationDistributionNum();

    /**
     * 事业编性别、民族、政治面貌、学历、年龄情况
     * @return
     */
    public List<Map<String, Object>> selectSybIdentityDistributionNum();

    /**
     * 副处级及以上事业编性别、民族、政治面貌情况统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpSybDistributionNum();

    /**
     * 副处级及以上事业编年龄情况数量统计
     * @return
     */
    public List<Map<String, Object>> fcjUpSybAgeDistributionNum();

    /**
     * 副处级及以上事业编学历情况数量统计
     * @return
     */
    public List<Map<String, Object>> selectFcjUpSybEducationDistributionNum();

    List<Map<String, Object>> selectAbilityLabelCount();
}
