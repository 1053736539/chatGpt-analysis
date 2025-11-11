package com.cb.assess.assessUserQuery;

import com.cb.assess.domain.vo.ExaminerVo;
import com.cb.common.core.domain.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AssessUserQueryContext {
    private AssessUserQueryStrategy strategy;
    private List<Long> deptIds;
    private List<String> identityTypes;
    private List<String> workCodes;
    // true 正向查询 false 反向查询
    @Builder.Default
    private Boolean positive=false;

    // 是否排除自己 查询互评时有效
    @Builder.Default
    private Boolean excludingSelf=false;
    //是否包含部门主要负责人 true 包含 false 不包含
    @Builder.Default
    private Boolean includeMainLeader=false;
    // 是否包含主持工作 true 包含 false 不包含
    @Builder.Default
    private Boolean includeHostWork=false;

    // 被考核对象用户
    private SysUser user;

    public List<SysUser> executeAssessedUser(){
        return strategy.queryAssessedUserIds(this.deptIds,this.identityTypes,this.workCodes,this.positive,this.includeMainLeader,this.includeHostWork);
    }
    public List<SysUser> queryAssessedUserNoDept(){
        return strategy.queryAssessedUserIds(this.identityTypes,this.workCodes,this.positive,this.includeMainLeader,this.includeHostWork);
    }

    public List<Long> executeOrdinaryUser(){
        return strategy.queryOrdinaryUser(this.deptIds,this.identityTypes,this.workCodes,this.positive,this.excludingSelf,this.user);
    }

    public List<Long> executeDirectorUser(){
        return strategy.queryDirectorUser(this.deptIds,this.identityTypes);
    }
    @Deprecated
    public List<Long> executeChargeLeader(){
        return strategy.queryChargeLeader(this.workCodes);
    }

    public List<Long> executeDeputyDirectorLeader(){
        return strategy.executeDeputyDirectorLeader(this.workCodes);
    }

    public List<Long> executeMainLeader(){
        return strategy.queryMainLeader(this.workCodes);
    }

}
