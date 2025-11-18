package com.cb.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.domain.SysUserInfoChangeApply;
import com.cb.system.mapper.SysUserInfoChangeApplyMapper;
import com.cb.system.service.ISysUserInfoChangeApplyService;
import com.cb.system.vo.SysUserInfoChangeApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 用户信息修改申请Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-15
 */
@Service
public class SysUserInfoChangeApplyServiceImpl implements ISysUserInfoChangeApplyService
{
    @Autowired
    private SysUserInfoChangeApplyMapper sysUserInfoChangeApplyMapper;

    /**
     * 查询用户信息修改申请
     *
     * @param id 用户信息修改申请ID
     * @return 用户信息修改申请
     */
    @Override
    public SysUserInfoChangeApply selectSysUserInfoChangeApplyById(String id)
    {
        return sysUserInfoChangeApplyMapper.selectSysUserInfoChangeApplyById(id);
    }

    /**
     * 查询用户信息修改申请列表
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 用户信息修改申请
     */
    @Override
    public List<SysUserInfoChangeApply> selectSysUserInfoChangeApplyList(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        return sysUserInfoChangeApplyMapper.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
    }

    /**
     * 新增用户信息修改申请
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 结果
     */
    @Override
    public int insertSysUserInfoChangeApply(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        if(StringUtils.isBlank(sysUserInfoChangeApply.getId())) {
            sysUserInfoChangeApply.setId(IdUtils.randomUUID());
        }
        try{
            sysUserInfoChangeApply.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        if(StringUtils.isBlank(sysUserInfoChangeApply.getStatus())){
            sysUserInfoChangeApply.setStatus("1");
        }
        sysUserInfoChangeApply.setCreateTime(DateUtils.getNowDate());
        return sysUserInfoChangeApplyMapper.insertSysUserInfoChangeApply(sysUserInfoChangeApply);
    }


    /**
     * 批量新增用户信息修改申请
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<SysUserInfoChangeApply> entities) {
        if(null == entities || entities.isEmpty()){
            return 0;
        }
        String userName = null;
        try{
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
            item.setId(IdUtils.randomUUID());
            item.setStatus("1");
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted +=  sysUserInfoChangeApplyMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改用户信息修改申请
     *
     * @param sysUserInfoChangeApply 用户信息修改申请
     * @return 结果
     */
    @Override
    public int updateSysUserInfoChangeApply(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        try{
            sysUserInfoChangeApply.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        sysUserInfoChangeApply.setUpdateTime(DateUtils.getNowDate());
        return sysUserInfoChangeApplyMapper.updateSysUserInfoChangeApply(sysUserInfoChangeApply);
    }

    /**
     * 批量删除用户信息修改申请
     *
     * @param ids 需要删除的用户信息修改申请ID
     * @return 结果
     */
    @Override
    public int deleteSysUserInfoChangeApplyByIds(String[] ids)
    {
        return sysUserInfoChangeApplyMapper.deleteSysUserInfoChangeApplyByIds(ids);
    }

    /**
     * 批量删除用户信息修改申请(排除掉通过的)
     * @param ids
     * @return
     */
    @Override
    public int deleteSysUserInfoChangeApplyByIdsExcludePass(String[] ids) {
        return sysUserInfoChangeApplyMapper.deleteSysUserInfoChangeApplyByIdsExcludePass(ids);
    }

    /**
     * 删除用户信息修改申请信息
     *
     * @param id 用户信息修改申请ID
     * @return 结果
     */
    @Override
    public int deleteSysUserInfoChangeApplyById(String id)
    {
        return sysUserInfoChangeApplyMapper.deleteSysUserInfoChangeApplyById(id);
    }

    @Override
    public List<SysUserInfoChangeApply> selectByIds(List<String> ids) {
        if(ObjectUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        return sysUserInfoChangeApplyMapper.selectByIds(ids);
    }

    /**
     * 审批
     * @param req
     */
    @Override
    public void approval(SysUserInfoChangeApplyVo.ApprovalReq req) {
        if(ObjectUtil.isEmpty(req.getIds())){
            throw new IllegalArgumentException("请选择需要审批的记录");
        }
        if(StringUtils.isBlank(req.getStatus())){
            throw new IllegalArgumentException("请选择审批状态");
        }
        String userName = null;
        try {
            userName = SecurityUtils.getUsername();
        } catch (Exception e){}
        sysUserInfoChangeApplyMapper.approval(req.getIds(),  req.getStatus(), req.getApprovalOpinion(), userName, DateUtils.getNowDate());
    }

    @Override
    public void reApply(SysUserInfoChangeApplyVo.ReApplyReq req) {
        SysUserInfoChangeApply exist = selectSysUserInfoChangeApplyById(req.getId());
        if(ObjectUtil.isEmpty(exist)){
            throw new IllegalArgumentException("记录不存在");
        }
        if(!"3".equals(exist.getStatus())){
            throw new RuntimeException("该申请当前的状态不允许重新申请");
        }
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        if(!exist.getUserId().equals(userId)){
            throw new RuntimeException("您无权限对该记录重新申请");
        }
        String afterData = JSONObject.toJSONString(req.getUser());
        sysUserInfoChangeApplyMapper.reApply(req.getId(),afterData,DateUtils.getNowDate());
    }

    @Override
    public List<SysUserInfoChangeApply> selectUserNoPassList(Long userId) {
        return sysUserInfoChangeApplyMapper.selectUserNoPassList(userId);
    }

    @Override
    public long countPending() {
        return sysUserInfoChangeApplyMapper.countPending();
    }

    @Override
    public long countReject(Long userId) {
        return sysUserInfoChangeApplyMapper.countReject(userId);
    }
}
