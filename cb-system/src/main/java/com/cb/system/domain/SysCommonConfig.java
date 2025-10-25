package com.cb.system.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统配置对象 SysCommonConfig
 *
 * @author ruoyi
 * @date 2023-06-01
 */
public class SysCommonConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 三员配置 1 启用 2禁用 */
    @Excel(name = "三员配置 0 启用 1禁用")
    private Integer enableThreeMember;

    /** 是否开启密码策略 */
    @Excel(name = "是否开启密码策略 0 启用 1禁用")
    private Integer enablePasswordPolicy;

    /** 密码策略 */
    @Excel(name = "密码策略")
    private Integer passwordPolicy;

    /** 密码长度 */
    @Excel(name = "密码长度")
    private Integer passwordLength;

    @Excel(name = "是否开启密码过期策略 0 启用 1禁用")
    private  Integer enablePasswordExpiration;
    /** 密码过期时间 */
    @Excel(name = "密码过期时间")
    private Integer passwordExpiration;
    /** 密码到期提醒 */
    @Excel(name = "密码到期提醒")
    private Integer passwordReminderTime;

    @Excel(name = "是否开启登录失败锁 0 启用 1禁用")
    private Integer enablePasswordLock;
    /** 密码错误次数 */
    @Excel(name = "密码错误次数")
    private Integer passwordErrorsNo;

    /** 错误锁定时间 */
    @Excel(name = "错误锁定时间")
    private Integer passwordLockTime;

    private Integer enableAutoMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnableThreeMember() {
        return enableThreeMember;
    }

    public void setEnableThreeMember(Integer enableThreeMember) {
        this.enableThreeMember = enableThreeMember;
    }

    public Integer getEnablePasswordPolicy() {
        return enablePasswordPolicy;
    }

    public void setEnablePasswordPolicy(Integer enablePasswordPolicy) {
        this.enablePasswordPolicy = enablePasswordPolicy;
    }

    public Integer getPasswordPolicy() {
        return passwordPolicy;
    }

    public void setPasswordPolicy(Integer passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    public Integer getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(Integer passwordLength) {
        this.passwordLength = passwordLength;
    }

    public Integer getEnablePasswordExpiration() {
        return enablePasswordExpiration;
    }

    public void setEnablePasswordExpiration(Integer enablePasswordExpiration) {
        this.enablePasswordExpiration = enablePasswordExpiration;
    }

    public Integer getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(Integer passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
    }

    public Integer getPasswordReminderTime() {
        return passwordReminderTime;
    }

    public void setPasswordReminderTime(Integer passwordReminderTime) {
        this.passwordReminderTime = passwordReminderTime;
    }

    public Integer getEnablePasswordLock() {
        return enablePasswordLock;
    }

    public void setEnablePasswordLock(Integer enablePasswordLock) {
        this.enablePasswordLock = enablePasswordLock;
    }

    public Integer getPasswordErrorsNo() {
        return passwordErrorsNo;
    }

    public void setPasswordErrorsNo(Integer passwordErrorsNo) {
        this.passwordErrorsNo = passwordErrorsNo;
    }

    public Integer getPasswordLockTime() {
        return passwordLockTime;
    }

    public void setPasswordLockTime(Integer passwordLockTime) {
        this.passwordLockTime = passwordLockTime;
    }

    public Integer getEnableAutoMessage() {
        return enableAutoMessage;
    }

    public void setEnableAutoMessage(Integer enableAutoMessage) {
        this.enableAutoMessage = enableAutoMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("enableThreeMember", getEnableThreeMember())
                .append("enablePasswordPolicy", getEnablePasswordPolicy())
                .append("passwordPolicy", getPasswordPolicy())
                .append("passwordLength", getPasswordLength())
                .append("passwordExpiration", getPasswordExpiration())
                .append("passwordErrorsNo", getPasswordErrorsNo())
                .append("passwordLockTime", getPasswordLockTime())
                .append("passwordReminderTime", getPasswordReminderTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
