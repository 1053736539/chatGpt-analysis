package com.cb.assess.assessUserQuery;
import com.cb.common.core.domain.entity.SysUser;

import java.util.List;

public interface AssessUserQueryStrategy {
    List<SysUser> queryAssessedUserIds(List<Long> deptIds, List<String> identityTypes, List<String> workCodes, Boolean positive,Boolean includeMainLeader ,Boolean includeHostWork);

    List<SysUser> queryAssessedUserIds(List<String> identityTypes, List<String> workCodes, Boolean positive,Boolean includeMainLeader,Boolean includeHostWork);

    /**
     * 查询普通人员
     * @param deptIds
     * @param identityTypes
     * @param workCodes
     * @param positive
     * @param excludingSelf
     * @param user
     * @return
     */
    List<Long> queryOrdinaryUser(List<Long> deptIds, List<String> identityTypes, List<String> workCodes, boolean positive, boolean excludingSelf, SysUser user);

    /**
     * 查询主要负责人
     * @param deptIds
     * @param identityTypes
     * @return
     */
    List<Long> queryDirectorUser(List<Long> deptIds, List<String> identityTypes);

    /**
     * 查询分管领导
     * @param workCodes
     * @return
     */
    List<Long> queryChargeLeader(List<String> workCodes);

    /**
     * 获取副局长 职务级别代码为112
     * @param workCodes
     * @return
     */
    List<Long> executeDeputyDirectorLeader(List<String> workCodes);

    /**
     * 查询主要局领导  职务级别代码为111
     * @param workCodes
     * @return
     */
    List<Long> queryMainLeader(List<String> workCodes);
}
