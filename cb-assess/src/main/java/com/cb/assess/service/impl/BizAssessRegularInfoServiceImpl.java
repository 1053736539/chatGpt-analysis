package com.cb.assess.service.impl;

import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.vo.AssessRegularParam;
import com.cb.assess.mapper.BizAssessRegularInfoMapper;
import com.cb.assess.service.IBizAssessRegularInfoService;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 平时考核登记Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-09
 */
@Service
public class BizAssessRegularInfoServiceImpl implements IBizAssessRegularInfoService {
    @Autowired
    private BizAssessRegularInfoMapper bizAssessRegularInfoMapper;
    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询平时考核登记
     *
     * @param id 平时考核登记ID
     * @return 平时考核登记
     */
    @Override
    public BizAssessRegularInfo selectBizAssessRegularInfoById(String id) {
        return bizAssessRegularInfoMapper.selectBizAssessRegularInfoById(id);
    }

    @Override
    public BizAssessRegularInfo selectBizAssessRegularInfoByUserTagId(String userTagId) {
        return bizAssessRegularInfoMapper.selectBizAssessRegularInfoByUserTagId(userTagId);
    }

    /**
     * 查询平时考核登记列表
     *
     * @return 平时考核登记
     */
    @Override
    public List<BizAssessRegularInfo> selectBizAssessRegularInfoList() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return bizAssessRegularInfoMapper.selectBizAssessRegularInfoList(userId);
    }

    /**
     * 新增平时考核登记
     *
     * @param bizAssessRegularInfo 平时考核登记
     * @return 结果
     */
    @Override
    public int insertBizAssessRegularInfo(BizAssessRegularInfo bizAssessRegularInfo) {
        bizAssessRegularInfo.setId(IdUtils.randomUUID());
        bizAssessRegularInfo.setCreateBy(SecurityUtils.getUsername());
        bizAssessRegularInfo.setCreateTime(DateUtils.getNowDate());
        bizAssessRegularInfo.setAuditStatus("0");
        bizAssessRegularInfo.setStep(0);
        return bizAssessRegularInfoMapper.insertBizAssessRegularInfo(bizAssessRegularInfo);
    }

    /**
     * 修改平时考核登记
     *
     * @param bizAssessRegularInfo 平时考核登记
     * @return 结果
     */
    @Override
    public int updateBizAssessRegularInfo(BizAssessRegularInfo bizAssessRegularInfo) {
        bizAssessRegularInfo.setUpdateBy(SecurityUtils.getUsername());
        bizAssessRegularInfo.setUpdateTime(DateUtils.getNowDate());
        if("2".equals(bizAssessRegularInfo.getAuditStatus())){
            bizAssessRegularInfo.setAuditStatus("0");
        }
        return bizAssessRegularInfoMapper.updateBizAssessRegularInfo(bizAssessRegularInfo);
    }


    @Override
    public List<BizAssessRegularInfo> selectNeedLeaderAuditList(BizAssessRegularInfo regular) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        Long userId = user.getUserId();
        Set<BizAssessRegularInfo> set = new LinkedHashSet<>();
        //  部门主要负责人，主任
        if (SecurityUtils.hasForceRole("dept_master_leader")) {
            // 获取数据时排除(111:正厅级，112:副厅级，121:正处级，212:二级巡视员)
            String[] jobLevels = {"111", "112", "121", "212"};
            List<Long> deptIds = new ArrayList<>();
            deptIds.add(deptId);
            if(!CollectionUtils.isEmpty(deptIds)){
                List<BizAssessRegularInfo> deptMasters = bizAssessRegularInfoMapper.selectNeedLeaderAuditList(Arrays.asList(jobLevels), deptIds,
                        regular.getStep(), regular.getAuditStatus(), regular.getUserName(),true);
                set.addAll(deptMasters);
            }
        }
        // 分管领导
        if (SecurityUtils.hasForceRole("leader_charge")) {
            List<SysDept> chargeDeptList = deptService.selectChargeDeptList(userId);
            //  获取 121:正处级，212:二级巡视员
            String[] jobLevels = {"121", "212"};
            List<Long> deptIds = chargeDeptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(deptIds)){
                List<BizAssessRegularInfo> leaderCharges = bizAssessRegularInfoMapper.selectNeedLeaderAuditList(Arrays.asList(jobLevels), deptIds,
                        regular.getStep(), regular.getAuditStatus(), regular.getUserName(),false);
                set.addAll(leaderCharges);
            }
        }
        if(SecurityUtils.hasForceRole("admin")){
            List<BizAssessRegularInfo> alls = bizAssessRegularInfoMapper.selectNeedAdminFillingList(regular.getStep());
            set.addAll(alls);
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<BizAssessRegularInfo> selectNeedOrganList(BizAssessRegularInfo regular) {
        String[] jobLevels = {"111", "112", "211"}; //获取数据时排除(111:正厅级，112:副厅级，211 一级巡视员)
        List<BizAssessRegularInfo> list = bizAssessRegularInfoMapper.selectNeedOrganFillingList(Arrays.asList(jobLevels), regular.getStep());
        return list;
    }


    @Override
    public int fillInComments(BizAssessRegularInfo regular) {
        String auditStatus = regular.getAuditStatus();
        SysUser onlineUser = SecurityUtils.getLoginUser().getUser();
        if (StringUtils.isNotBlank(auditStatus) && 1 == regular.getStep()) {
            regular.setAuditer(onlineUser.getUserId());
            regular.setAuditTime(DateUtils.getNowDate());
            regular.setSignImg(onlineUser.getName());
        } else {
            regular.setUpdateBy(SecurityUtils.getUsername());
            regular.setUpdateTime(DateUtils.getNowDate());
        }
        return bizAssessRegularInfoMapper.fillInComments(regular);
    }

    @Override
    public int batchFillInComments(AssessRegularParam param) {
        List<BizAssessRegularInfo> regulars = param.getRegulars();
        for (BizAssessRegularInfo regular : regulars) {
            regular.setUpdateBy(SecurityUtils.getUsername());
            regular.setUpdateTime(DateUtils.getNowDate());
            bizAssessRegularInfoMapper.updateBizAssessRegularInfo(regular);
        }
        return 1;
    }

    @Override
    public int deleteRegularInfo(List<String> userTagIds) {
        return bizAssessRegularInfoMapper.deleteRegularInfo(userTagIds);
    }
}
