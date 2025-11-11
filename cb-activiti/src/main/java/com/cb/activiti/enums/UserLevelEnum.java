package com.cb.activiti.enums;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.core.domain.entity.SysUser;

import java.util.List;

public enum UserLevelEnum {
    LEVEL_1("level_1","市纪委常委、市监委委员"),
    LEVEL_2("level_2","市纪委副书记、市监委副主任"),
    LEVEL_3("level_3","市纪委市监委机关各部门，市委巡察组，各派驻（出）机构负责人"),
    LEVEL_4("level_4","市委巡察办副主任"),
    LEVEL_5("level_5","市委巡察组副组长"),
    OTHER("other","其他人员");

    private String code;
    private String desc;

    UserLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static UserLevelEnum getByUserAndDept(SysUser user, SysDept dept){
        //TODO 获取申请人的类型
        String workPost = user.getWorkPost();
        List<SysRole> roles = user.getRoles();
        if(roles.stream().map(o->o.getRoleName()).filter(o->o.equals("市纪委常委") || o.equals("市监委委员")).count() > 0){
            // 市纪委常委、市监委委员
            return UserLevelEnum.LEVEL_1;
        } else if(roles.stream().map(o->o.getRoleName()).filter(o->o.equals("市纪委副书记") || o.equals("市监委副主任")).count() > 0){
            //市纪委副书记、市监委副主任
            return UserLevelEnum.LEVEL_2;
        } else if("1".equals(user.getIsMainLeader())){
            if(roles.stream().map(o->o.getRoleName()).filter(o->o.equals("市委巡察办副主任") ).count() > 0){
                //市委巡察办副主任
                return UserLevelEnum.LEVEL_4;
            }
            //市纪委市监委机关各部门，市委巡察办，市委巡察组，各派驻（出）机构负责人
            return UserLevelEnum.LEVEL_3;
        } else if(roles.stream().map(o->o.getRoleName()).filter(o-> o.equals("市委巡察组副组长")).count() > 0){
            //市委巡察组副组长
            return UserLevelEnum.LEVEL_5;
        }
        return UserLevelEnum.OTHER;
    }

}
