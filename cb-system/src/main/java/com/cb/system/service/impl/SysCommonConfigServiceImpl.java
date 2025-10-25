package com.cb.system.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.system.domain.SysCommonConfig;
import com.cb.system.mapper.SysCommonConfigMapper;
import com.cb.system.service.ISysCommonConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class SysCommonConfigServiceImpl implements ISysCommonConfigService {
    @Autowired
    private SysCommonConfigMapper sysCommonConfigMapper;

    /**
     * 查询系统配置
     *
     * @param id 系统配置ID
     * @return 系统配置
     */
    @Override
    public SysCommonConfig selectSysCommonConfigById(Long id) {
        return sysCommonConfigMapper.selectSysCommonConfigById(id);
    }

    /**
     * 新增系统配置
     *
     * @param SysCommonConfig 系统配置
     * @return 结果
     */
    @Override
    public int insertSysCommonConfig(SysCommonConfig SysCommonConfig) {
        SysCommonConfig.setCreateTime(DateUtils.getNowDate());
        return sysCommonConfigMapper.insertSysCommonConfig(SysCommonConfig);
    }

    /**
     * 查询系统配置列表
     *
     * @param SysCommonConfig 系统配置
     * @return 系统配置
     */
    @Override
    public List<SysCommonConfig> selectSysCommonConfigList(SysCommonConfig SysCommonConfig) {
        return sysCommonConfigMapper.selectSysCommonConfigList(SysCommonConfig);
    }

    /**
     * 修改系统配置
     *
     * @param SysCommonConfig 系统配置
     * @return 结果
     */
    @Override
    public int updateSysCommonConfig(SysCommonConfig SysCommonConfig) {
        SysCommonConfig.setUpdateTime(DateUtils.getNowDate());
        return sysCommonConfigMapper.updateSysCommonConfig(SysCommonConfig);
    }


    @Override
    public SysCommonConfig selectOneSysCommonConfigById() {
        return sysCommonConfigMapper.selectSysCommonConfigById(1L);
    }

    @Override
    public Boolean selectThreeMemberSwitch() {
        SysCommonConfig SysCommonConfig = sysCommonConfigMapper.selectSysCommonConfigById(1L);
        return SysCommonConfig.getEnableThreeMember() == 0 ? true : false;
    }

    @Override
    public Boolean selectPasswordPolicySwitch() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getEnablePasswordPolicy() == 0 ? true : false;
    }

    @Override
    public int selectPasswordPolicy() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getPasswordPolicy();
    }

    @Override
    public int selectPasswordLength() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getPasswordLength();
    }

    @Override
    public int selectPasswordExpiration() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getPasswordExpiration();
    }

    @Override
    public int selectPasswordErrorsNo() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getPasswordErrorsNo();
    }

    @Override
    public int selectPasswordLockTime() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getPasswordLockTime();
    }

    @Override
    public int selectPasswordReminderTime() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getPasswordReminderTime();
    }

    @Override
    public Boolean selectEnableAutoMessageSwitch() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getEnableAutoMessage() == 0 ? true : false;
    }

    @Override
    public Boolean selectPasswordExpirationSwitch() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getEnablePasswordExpiration() == 0 ? true : false;
    }

    @Override
    public Boolean selectPasswordLockSwitch() {
        SysCommonConfig SysCommonConfig = this.selectOneSysCommonConfigById();
        return SysCommonConfig.getEnablePasswordLock() == 0 ? true : false;
    }
}
