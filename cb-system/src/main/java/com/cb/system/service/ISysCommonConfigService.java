package com.cb.system.service;

import com.cb.system.domain.SysCommonConfig;

import java.util.List;

/**
 * 系统配置Service接口
 *
 * @author ruoyi
 * @date 2023-06-01
 */
public interface ISysCommonConfigService {
    /**
     * 查询系统配置
     *
     * @param id 系统配置ID
     * @return 系统配置
     */
    public SysCommonConfig selectSysCommonConfigById(Long id);

    /**
     * 查询系统配置列表
     *
     * @param SysCommonConfig 系统配置
     * @return 系统配置集合
     */
    public List<SysCommonConfig> selectSysCommonConfigList(SysCommonConfig SysCommonConfig);
    /**
     * 新增系统配置
     *
     * @param SysCommonConfig 系统配置
     * @return 结果
     */
    public int insertSysCommonConfig(SysCommonConfig SysCommonConfig);

    /**
     * 修改系统配置
     *
     * @param SysCommonConfig 系统配置
     * @return 结果
     */
    public int updateSysCommonConfig(SysCommonConfig SysCommonConfig);


    /**
     * 获取配置
     *
     * @return
     */
    public SysCommonConfig selectOneSysCommonConfigById();

    /**
     * 获取三员开关设置
     *
     * @return
     */
    public Boolean selectThreeMemberSwitch();

    /**
     * 获取密码策略开关设置
     *
     * @return
     */
    public Boolean selectPasswordPolicySwitch();

    /**
     * 获取密码策略
     *
     * @return
     */

    public int selectPasswordPolicy();
    /**
     * 获取密码长度(单位:字符)
     *
     * @return
     */
    public int selectPasswordLength();

    /**
     * 获取过期时间(单位:天)
     *
     * @return
     */
    public int selectPasswordExpiration();

    /**
     * 获取错误次数(单位:次)
     * @return
     */
    public int selectPasswordErrorsNo();

    /**
     * 获取错误锁定时间(单位:分钟)
     * @return
     */
    public int selectPasswordLockTime();

    /**
     * 获取到期提醒时间(单位:天)
     * @return
     */
    public int selectPasswordReminderTime();

    public Boolean selectEnableAutoMessageSwitch();


    public Boolean selectPasswordExpirationSwitch();

    public Boolean selectPasswordLockSwitch();
}
