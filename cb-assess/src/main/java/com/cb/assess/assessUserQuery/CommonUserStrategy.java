package com.cb.assess.assessUserQuery;

import com.cb.assess.mapper.BizAssessUserMapper;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.spring.SpringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class CommonUserStrategy implements AssessUserQueryStrategy {


    private BizAssessUserMapper assessUserMapper;

    public CommonUserStrategy() {
        this.assessUserMapper =SpringUtils.getBean(BizAssessUserMapper.class);
    }

    @Override
    public List<SysUser> queryAssessedUserIds(List<Long> deptIds, List<String> identityTypes, List<String> workCodes, Boolean positive,Boolean includeMainLeader ,Boolean includeHostWork) {
        if(CollectionUtils.isEmpty(deptIds) || CollectionUtils.isEmpty(identityTypes) || CollectionUtils.isEmpty(workCodes)){
            throw new IllegalArgumentException("parameter deptIds identityTypes workCodes is incorrect !");
        }
        return assessUserMapper.selectAssessedUserIds(deptIds,identityTypes,workCodes,positive,includeMainLeader,includeHostWork);
    }
    @Override
    public List<SysUser> queryAssessedUserIds(List<String> identityTypes, List<String> workCodes, Boolean positive,Boolean includeMainLeader ,Boolean includeHostWork) {
        if(CollectionUtils.isEmpty(identityTypes) || CollectionUtils.isEmpty(workCodes)){
            throw new IllegalArgumentException("parameter deptIds identityTypes workCodes is incorrect !");
        }
        return assessUserMapper.selectAssessedUserIds(null,identityTypes,workCodes,positive,includeMainLeader,includeHostWork);
    }
    @Override
    public List<Long> queryOrdinaryUser(List<Long> deptIds, List<String> identityTypes, List<String> workCodes, boolean positive,boolean excludingSelf, SysUser user) {
        if(CollectionUtils.isEmpty(deptIds) || CollectionUtils.isEmpty(identityTypes) || CollectionUtils.isEmpty(workCodes)){
            throw new IllegalArgumentException("parameter deptIds identityTypes workCodes is incorrect !");
        }
        return assessUserMapper.selectOrdinaryUserInDept(deptIds, identityTypes, workCodes, positive,excludingSelf,user);
    }

    @Override
    public List<Long> queryDirectorUser(List<Long> deptIds, List<String> identityTypes) {
        if(CollectionUtils.isEmpty(deptIds) || CollectionUtils.isEmpty(identityTypes)){
            throw new IllegalArgumentException("parameter deptIds and identityTypes is incorrect !");
        }
        return assessUserMapper.selectDirectorUserInDept(deptIds, identityTypes);
    }

    @Override
    public List<Long> queryChargeLeader(List<String> workCodes) {
        if(CollectionUtils.isEmpty(workCodes) ){
            throw new IllegalArgumentException("parameter workCodes is incorrect !");
        }
        return assessUserMapper.selectByWorkCodes(workCodes);
    }


    @Override
    public List<Long> executeDeputyDirectorLeader(List<String> workCodes) {
        if(CollectionUtils.isEmpty(workCodes) ){
            throw new IllegalArgumentException("parameter workCodes is incorrect !");
        }
        return assessUserMapper.selectByWorkCodes(workCodes);
    }

    @Override
    public List<Long> queryMainLeader(List<String> workCodes) {
        if(CollectionUtils.isEmpty(workCodes) ){
            throw new IllegalArgumentException("parameter workCodes is incorrect !");
        }
        return assessUserMapper.selectByWorkCodes(workCodes);
    }
}
