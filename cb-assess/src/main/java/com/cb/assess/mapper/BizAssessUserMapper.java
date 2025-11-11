package com.cb.assess.mapper;


import com.cb.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考核专用用户Mapper
 */
public interface BizAssessUserMapper {

    /**
     * 获取平时考核被考核的人员
     *
     * @param deptIds
     * @param types
     * @param workCodes
     * @param positive
     * @return
     */
    public List<SysUser> selectAssessedUserIds(@Param("deptIds") List<Long> deptIds, @Param("types") List<String> types,
                                               @Param("workCodes") List<String> workCodes, @Param("positive") boolean positive,
                                               @Param("includeMainLeader") Boolean includeMainLeader,
                                               @Param("includeHostWork") Boolean includeHostWork);


    /**
     * 获取被考核用户部门内的普通人员
     *
     * @param deptIds
     * @param types
     * @param workCodes
     * @param positive
     * @return
     */
    public List<Long> selectOrdinaryUserInDept(@Param("deptIds") List<Long> deptIds, @Param("types") List<String> types,
                                               @Param("workCodes") List<String> workCodes, @Param("positive") boolean positive,
                                               @Param("excludingSelf")boolean excludingSelf, @Param("self")SysUser user);


    /**
     * 获取被考核用户所在部门的主要负责人
     *
     * @param deptIds
     * @param types
     * @return
     */
    public List<Long> selectDirectorUserInDept(@Param("deptIds") List<Long> deptIds, @Param("types") List<String> types);


    /**
     * 获取指定workCode 集合的用户
     * @param workCodes
     * @return
     */
    public List<Long> selectByWorkCodes(@Param("workCodes") List<String> workCodes);


    public List<SysUser> selectSysUserList(List<Long> list);

    /**
     * 获取用于年度处级领导干部民主评议的被评价人的id集合
     * @return
     */
    public List<Long> getAssessedUserIdList4Democratic(@Param("deptId") Long deptId);

    /**
     * 查询用户userId集合
     * @param deptId 部门id
     * @param workCodes 指定的workCode
     * @param excludeCodes 要排除的workCode
     * @return
     */
    public List<Long> getUserList(@Param("deptId") Long deptId,
                                  @Param("identityTypes") List<String> identityTypes,
                                  @Param("workCodes") List<String> workCodes,
                                  @Param("excludeCodes") List<String> excludeCodes,
                                  @Param("includeHostWork") Boolean includeHostWork,
                                  @Param("excludeHostWork") Boolean excludeHostWork);

}
