package com.cb.cadretrain.domain;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 干部政治素质档案对象 biz_quality_profile
 * 
 * @author lennon
 * @date 2023-11-01
 */
public class BizQualityProfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 素质档案ID */
    private String profileId;

    /** 档案类型 */
    @Excel(name = "档案类型")
    private String profileType;

    /** 档案子类 */
    @Excel(name = "档案子类")
    private String subclass;

    /** 附件名 */
    @Excel(name = "附件名")
    private String filesName;

    /** 附件 */
    @Excel(name = "附件")
    private String files;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 状态（1-草稿，2-已发布） */
    @Excel(name = "状态", readConverterExp = "1=-草稿，2-已发布")
    private String userId;

    public void setProfileId(String profileId) 
    {
        this.profileId = profileId;
    }

    public String getProfileId() 
    {
        return profileId;
    }
    public void setProfileType(String profileType) 
    {
        this.profileType = profileType;
    }

    public String getProfileType() 
    {
        return profileType;
    }
    public void setSubclass(String subclass) 
    {
        this.subclass = subclass;
    }

    public String getSubclass() 
    {
        return subclass;
    }
    public void setFilesName(String filesName) 
    {
        this.filesName = filesName;
    }

    public String getFilesName() 
    {
        return filesName;
    }
    public void setFiles(String files) 
    {
        this.files = files;
    }

    public String getFiles() 
    {
        return files;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("profileId", getProfileId())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("profileType", getProfileType())
            .append("subclass", getSubclass())
            .append("filesName", getFilesName())
            .append("files", getFiles())
            .append("username", getUsername())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("userId", getUserId())
            .toString();
    }
}
