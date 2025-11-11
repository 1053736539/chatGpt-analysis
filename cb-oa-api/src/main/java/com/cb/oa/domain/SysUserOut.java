package com.cb.oa.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * oa用户对象 sys_user_out
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
public class SysUserOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** $column.columnComment */
    @Excel(name = "用户名")
    private String usernameLetter;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 办公室电话 */
    @Excel(name = "办公室电话")
    private String officeTel;

    /** 短号码 */
    @Excel(name = "短号码")
    private String shortNumber;

    /** 称谓 */
    @Excel(name = "称谓")
    private String userAppellation;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String userPost;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String officeAddress;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String gender;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String email;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private Integer enabled;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String mainDeptid;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String pinyin;

    /** $column.columnComment */
    @Excel(name = "称谓")
    private String csort;

    /** $column.columnComment */
    private String delFlag;

    /** 自己内部系统的id */
    @Excel(name = "自己内部系统的id")
    private Long userId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setUsernameLetter(String usernameLetter) 
    {
        this.usernameLetter = usernameLetter;
    }

    public String getUsernameLetter() 
    {
        return usernameLetter;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setOfficeTel(String officeTel) 
    {
        this.officeTel = officeTel;
    }

    public String getOfficeTel() 
    {
        return officeTel;
    }
    public void setShortNumber(String shortNumber) 
    {
        this.shortNumber = shortNumber;
    }

    public String getShortNumber() 
    {
        return shortNumber;
    }
    public void setUserAppellation(String userAppellation) 
    {
        this.userAppellation = userAppellation;
    }

    public String getUserAppellation() 
    {
        return userAppellation;
    }
    public void setUserPost(String userPost) 
    {
        this.userPost = userPost;
    }

    public String getUserPost() 
    {
        return userPost;
    }
    public void setOfficeAddress(String officeAddress) 
    {
        this.officeAddress = officeAddress;
    }

    public String getOfficeAddress() 
    {
        return officeAddress;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setEnabled(Integer enabled) 
    {
        this.enabled = enabled;
    }

    public Integer getEnabled() 
    {
        return enabled;
    }
    public void setMainDeptid(String mainDeptid)
    {
        this.mainDeptid = mainDeptid;
    }

    public String getMainDeptid()
    {
        return mainDeptid;
    }
    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }
    public void setCsort(String csort) 
    {
        this.csort = csort;
    }

    public String getCsort() 
    {
        return csort;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("usernameLetter", getUsernameLetter())
            .append("nickName", getNickName())
            .append("officeTel", getOfficeTel())
            .append("shortNumber", getShortNumber())
            .append("userAppellation", getUserAppellation())
            .append("userPost", getUserPost())
            .append("officeAddress", getOfficeAddress())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("enabled", getEnabled())
            .append("mainMeptid", getMainDeptid())
            .append("pinyin", getPinyin())
            .append("csort", getCsort())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .append("userId", getUserId())
            .toString();
    }
}
