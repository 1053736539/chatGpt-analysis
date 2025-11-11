package com.cb.assess.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.cb.assess.assessUserQuery.AssessUserQueryContext;
import com.cb.assess.assessUserQuery.CommonUserStrategy;
import com.cb.assess.domain.BizAssessIndicatorPro;
import com.cb.assess.domain.BizAssessIndicatorProAssessors;
import com.cb.assess.domain.BizAssessIndicatorProRatGroup;
import com.cb.assess.domain.BizAssessTaskManage;
import com.cb.assess.domain.vo.ExaminerVo;
import com.cb.assess.enums.PersonnelType;
import com.cb.assess.enums.RatGroup;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.BizAssessIndicatorProMapper;
import com.cb.assess.mapper.BizAssessUserMapper;
import com.cb.assess.service.IBizAssessUserService;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.entity.UserDeptPost;
import com.cb.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class IBizAssessUserServiceImpl implements IBizAssessUserService {

    @Autowired
    private BizAssessUserMapper assessUserMapper;

    @Override
    public List<SysUser> selectAssessedUserByTaskManage(BizAssessTaskManage manage) {
        try {
            List<SysUser> users = new ArrayList<>();
            BizAssessIndicatorPro pro = manage.getPro();
            // 获取方案是不是平时考核 0 平时考核 1 专项考核
            String special = pro.getSpecial();
            // 如果是平时考核
            if (special.equals(Special.NORMAL.getCode())) {
                //1. 获取考核对象
                String personnelType = pro.getPersonnelType();
                // 获取选择的部门ID
                List<Long> deptIds = pro.getDutyList().stream().filter(o -> "0".equals(o.getType()))
                        .map(item -> item.getDeptId()).collect(Collectors.toList());
                String[] identityTypes = PersonnelType.ofCode(personnelType).getIdentity(); //编制类型
                AssessUserQueryContext queryContext = new AssessUserQueryContext()
                        .setDeptIds(deptIds)
                        .setIdentityTypes(Arrays.asList(identityTypes))
                        .setStrategy(new CommonUserStrategy());
                if (PersonnelType.CIVIL_SERVANT_SPECIAL.getCode().equals(personnelType)) {  //公务员：二级巡视员、总师、督查员、主要负责人
                    String[] workCodes = {"121", "212"};
                    queryContext.setWorkCodes(Arrays.asList(workCodes))
                            .setPositive(true)
                            .setIncludeMainLeader(true)
                            .setIncludeHostWork(true);
                    List<SysUser> ids = queryContext.executeAssessedUser();
                    users.addAll(ids);
                }
                if (PersonnelType.CIVIL_SERVANT_GENERAL.getCode().equals(personnelType)) { // 公务员：其他考核对象
                    String[] workCodes = {"111", "112", "121", "211", "212"};
                    queryContext.setWorkCodes(Arrays.asList(workCodes))
                            .setPositive(false)
                            .setIncludeMainLeader(false)
                            .setIncludeHostWork(false);
                    List<SysUser> ids = queryContext.executeAssessedUser();
                    users.addAll(ids);
                }
                if (PersonnelType.INSTITUTION_SPECIAL.getCode().equals(personnelType)) { // 事业单位：主要负责人
                    String[] workCodes = {"121", "212"};
                    queryContext.setWorkCodes(Arrays.asList(workCodes))
                            .setPositive(true)
                            .setIncludeMainLeader(true)
                            .setIncludeHostWork(true);
                    List<SysUser> ids = queryContext.executeAssessedUser();
                    users.addAll(ids);
                }
                if (PersonnelType.INSTITUTION_GENERAL.getCode().equals(personnelType)) {
                    String[] workCodes = {"111", "112", "121", "211", "212"}; // 职务级别代码数组
                    queryContext.setWorkCodes(Arrays.asList(workCodes))
                            .setPositive(false)
                            .setIncludeMainLeader(false)
                            .setIncludeHostWork(false);
                    List<SysUser> ids = queryContext.executeAssessedUser();
                    users.addAll(ids);
                }
            } else { // 专项考核
                List<Long> ids = pro.getDutyList().stream().filter(o -> "1".equals(o.getType()))
                        .map(item -> item.getUserId()).collect(Collectors.toList());
                List<SysUser> list = assessUserMapper.selectSysUserList(ids);
                users.addAll(list);
            }
            // UserId 去重
            return users.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(() -> new TreeSet<>(
                            Comparator.comparing(p -> p.getUserId()))), ArrayList::new));
        } catch (Exception e) {
            e.printStackTrace();
            return CollectionUtil.newArrayList();
        }
    }

    @Override
    public List<ExaminerVo> selectExaminerVoListByTaskManage(BizAssessTaskManage manage, SysUser user) {
        try {
            BizAssessIndicatorPro pro = manage.getPro();
            List<BizAssessIndicatorProRatGroup> ratGroupList = pro.getRatGroupList();
            // 获取分值权重配置
            List<String> groupNameList = ratGroupList.stream()
                    .map(BizAssessIndicatorProRatGroup::getName).collect(Collectors.toList());
            // 获取分值权重配置 键值对
            /*Map<String, String> ratGroupMap = ratGroupList.stream()
                    .collect(Collectors.toMap(item -> item.getName(), item -> item.getGroupId()));*/
            String personnelType = pro.getPersonnelType();

            //List<UserDeptPost> deptPostList = user.getUserDeptPostList();
            // 获取被评价用户的部门ID数组
            //List<Long> deptIds = deptPostList.stream().map(item -> item.getDeptId()).collect(Collectors.toList());

            List<Long> deptIds = new ArrayList<>();
            deptIds.add(user.getDeptId());

            String special = pro.getSpecial();
            if (Special.NORMAL.getCode().equals(special)) {
                String[] identityTypes = PersonnelType.ofCode(personnelType).getIdentity(); // 编制类型
                AssessUserQueryContext queryContext = new AssessUserQueryContext()
                        .setDeptIds(deptIds)
                        .setIdentityTypes(Arrays.asList(identityTypes))
                        .setStrategy(new CommonUserStrategy());

                List<ExaminerVo> voList = new ArrayList<>();
                if (groupNameList.contains(RatGroup.RATE_EACH.getCode())) { // 民主测评
                    String[] workCodes = {"111", "112", "121", "211", "212"}; // 职务级别代码数组(排除 正厅级、副厅级、正处级、一级巡视员、二级巡视员)
                    queryContext.setWorkCodes(Arrays.asList(workCodes))
                            .setPositive(false)
                            .setExcludingSelf(false);// 不排除自身
                    // 因为ids 有add 操作，每次进来都用新的集合存储，MyBatis 会对查询结果进行缓存，如果缓存已经存在，它可能不会重新执行查询。
                    List<Long> ids=new ArrayList<>();
                    ids.addAll(queryContext.executeOrdinaryUser());
                    // 防止选择了不在本部门下的人员无法自评
                    if(!ids.contains(user.getUserId())){
                        ids.add(user.getUserId());
                    }
                    // 二级巡视员、总师、督查员、主要负责人需要自己给自评
                    if (PersonnelType.CIVIL_SERVANT_SPECIAL.getCode().equals(personnelType)
                            || PersonnelType.INSTITUTION_SPECIAL.getCode().equals(personnelType)) {
                        if(!ids.contains(user.getUserId())){
                            ids.add(user.getUserId());
                        }
                    }
                    List<ExaminerVo> list = this.buildExaminerVo(ids, RatGroup.RATE_EACH.getCode());
                    voList.addAll(list);
                }
                if (groupNameList.contains(RatGroup.RATE_DIRECTOR.getCode())) { // 主要负责人
                    List<Long> ids = queryContext.executeDirectorUser();
                    List<ExaminerVo> list = this.buildExaminerVo(ids, RatGroup.RATE_DIRECTOR.getCode());
                    voList.addAll(list);
                }
                if (groupNameList.contains(RatGroup.RATE_CHARGE_LEADER.getCode())) { // 副局长评分
                    String[] workCodes = {"112"};
                    queryContext.setWorkCodes(Arrays.asList(workCodes));
                    List<Long> ids = queryContext.executeDeputyDirectorLeader();
                    List<ExaminerVo> list = this.buildExaminerVo(ids, RatGroup.RATE_CHARGE_LEADER.getCode());
                    voList.addAll(list);
                }
                if (groupNameList.contains(RatGroup.RATE_MAIN_LEADER.getCode())) { //主要领导(局长)评分
                    String[] workCodes = {"111"};
                    queryContext.setWorkCodes(Arrays.asList(workCodes));
                    List<Long> ids = queryContext.executeMainLeader();
                    List<ExaminerVo> list = this.buildExaminerVo(ids, RatGroup.RATE_MAIN_LEADER.getCode());
                    voList.addAll(list);
                }
                return voList;
            } else {
                //  获取专项考核的考官信息即可
                List<BizAssessIndicatorProAssessors> assessors = pro.getAssessors();
                List<Long> ids = assessors.stream().map(BizAssessIndicatorProAssessors::getUserId).collect(Collectors.toList());
                List<ExaminerVo> list = this.buildExaminerVo(ids, RatGroup.RATE_SPECIAL.getCode());
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CollectionUtil.newArrayList();
        }
    }

    /**
     * @param ids
     * @param code
     * @return
     */
    private List<ExaminerVo> buildExaminerVo(List<Long> ids, String code) {
        return ids.stream().distinct().map(item -> {
            ExaminerVo examinerVo = new ExaminerVo();
            examinerVo.setUserId(item);
            examinerVo.setVoteType(code);
            return examinerVo;
        }).collect(Collectors.toList());
    }
}
