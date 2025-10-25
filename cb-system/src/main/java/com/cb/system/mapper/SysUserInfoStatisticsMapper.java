package com.cb.system.mapper;


import com.cb.common.core.domain.vo.GwyIdentityTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserInfoStatisticsMapper {

    /**
     * 部门人员性别（男/女）人员统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectSexCount(@Param("deptId") Long deptId);

    /**
     * 政治面貌统计 党员、群众数量
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectPartyCount(@Param("deptId") Long deptId);

    /**
     * 年龄阶段统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectAgeCount(@Param("deptId") Long deptId);

    /**
     * 职级统计
     * @param deptId
     */
    public List<Map<String, Object>> selectWorkPostCount(@Param("deptId") Long deptId);

    /**
     * 编制类型统计
     * @param deptId
     */
    public List<Map<String, Object>> selectIdentityTypeCount(@Param("deptId") Long deptId);

    /**
     * 学历统计
     * @param deptId
     */
    public List<Map<String, Object>> selectEduCount(@Param("deptId") Long deptId);

    /**
     * 现任领导职务年限统计
     * @param deptId
     */
    public List<Map<String, Object>> selectAppointmentCount(@Param("deptId") Long deptId);

    /**
     * 职务任职年龄段统计
     * @param deptId
     */
    public List<Map<String, Object>> selectPostAgeCount(@Param("deptId") Long deptId);

    /**
     * 职级数量统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectWorkPostNumCount(@Param("deptId") Long deptId);

    /**
     * 事业职务（职称）职级数量统计
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectShiYeWorkPostCount(@Param("deptId") Long deptId);

    /**
     * 行政编制和事业编制分布情况统计
     * @param deptId
     * @return
     */
    public List<GwyIdentityTypeVo> selectIdentityTypeNumber(@Param("deptId") Long deptId);

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
    List<Map<String, Object>> selectFcjUpXzsybAgeDistributionNum();

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

    /**
     * 参公职级数量统计
     * @return
     */
    public List<Map<String, Object>> selectCgzjNum();

    /**
     * 副处级及以上行政编制性别、民族、政治面貌情况统计
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

    /**
     * 统计查询干部标签
     * @return
     */
    List<Map<String, Object>> selectAbilityLabelCount();
}
