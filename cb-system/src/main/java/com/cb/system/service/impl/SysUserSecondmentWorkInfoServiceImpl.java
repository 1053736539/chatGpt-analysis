package com.cb.system.service.impl;

import com.cb.common.core.domain.entity.SysUserSecondmentWorkInfo;
import com.cb.common.core.domain.vo.SysUserSecondmentWorkInfoVo;
import com.cb.common.utils.DateUtils;
import com.cb.system.mapper.SysUserSecondmentWorkInfoMapper;
import com.cb.system.service.ISysUserSecondmentWorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员借调工作信息Service业务层处理
 *
 * @author huhaojie
 * @date 2023-12-21
 */
@Service
public class SysUserSecondmentWorkInfoServiceImpl implements ISysUserSecondmentWorkInfoService {
    @Autowired
    private SysUserSecondmentWorkInfoMapper sysUserSecondmentWorkInfoMapper;

    /**
     * 查询人员借调工作信息
     *
     * @param secondmentWorkInfoId 人员借调工作信息ID
     * @return 人员借调工作信息
     */
    @Override
    public SysUserSecondmentWorkInfo selectSysUserSecondmentWorkInfoById(String secondmentWorkInfoId) {
        return sysUserSecondmentWorkInfoMapper.selectSysUserSecondmentWorkInfoById(secondmentWorkInfoId);
    }

    /**
     * 查询人员借调工作信息列表
     *
     * @param sysUserSecondmentWorkInfo 人员借调工作信息
     * @return 人员借调工作信息
     */
    @Override
    public List<SysUserSecondmentWorkInfo> selectSysUserSecondmentWorkInfoList(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        return sysUserSecondmentWorkInfoMapper.selectSysUserSecondmentWorkInfoList(sysUserSecondmentWorkInfo);
    }

    @Override
    public List<SysUserSecondmentWorkInfoVo> selectSysUserSecondmentWorkInfoVoList(SysUserSecondmentWorkInfoVo sysUserSecondmentWorkInfoVo) {
        return sysUserSecondmentWorkInfoMapper.selectSysUserSecondmentWorkInfoVoList(sysUserSecondmentWorkInfoVo);
    }

    /**
     * 新增人员借调工作信息
     *
     * @param sysUserSecondmentWorkInfo 人员借调工作信息
     * @return 结果
     */
    @Override
    public int insertSysUserSecondmentWorkInfo(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        sysUserSecondmentWorkInfo.setCreateTime(DateUtils.getNowDate());
        return sysUserSecondmentWorkInfoMapper.insertSysUserSecondmentWorkInfo(sysUserSecondmentWorkInfo);
    }

    /**
     * 修改人员借调工作信息
     *
     * @param sysUserSecondmentWorkInfo 人员借调工作信息
     * @return 结果
     */
    @Override
    public int updateSysUserSecondmentWorkInfo(SysUserSecondmentWorkInfo sysUserSecondmentWorkInfo) {
        sysUserSecondmentWorkInfo.setUpdateTime(DateUtils.getNowDate());
        return sysUserSecondmentWorkInfoMapper.updateSysUserSecondmentWorkInfo(sysUserSecondmentWorkInfo);
    }

    /**
     * 批量删除人员借调工作信息
     *
     * @param secondmentWorkInfoIds 需要删除的人员借调工作信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserSecondmentWorkInfoByIds(String[] secondmentWorkInfoIds) {
        return sysUserSecondmentWorkInfoMapper.deleteSysUserSecondmentWorkInfoByIds(secondmentWorkInfoIds);
    }

    /**
     * 删除人员借调工作信息信息
     *
     * @param secondmentWorkInfoId 人员借调工作信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserSecondmentWorkInfoById(String secondmentWorkInfoId) {
        return sysUserSecondmentWorkInfoMapper.deleteSysUserSecondmentWorkInfoById(secondmentWorkInfoId);
    }
}
