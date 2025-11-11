package com.cb.leave.domain.vo;

import com.cb.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LeaveCensusVo extends BaseEntity {
    private Long userId;
    private Long deptId;
    private Long parentDeptId;
    private String ancestors;
    private String deptName;
    private String userName;
    private String nickName;
    private String phonenumber;
    private String userType;
    private String sex;
    private String status;
    private String identityType;
    private String isMainLeader;
    private String isHostingWork;
    private String workTitleCode;
    private String personnelStatus;
    private String deptIdentityType;
    private String age;
    private String postTime;
    private Long leaveTypeId;
    private String leaveYear;
    private Float totalDays;
    private Float usedDays;
    private Float pendingDays;
    private Float remainingDays;

    @Data
    public static class HolidayRate{
        private String year;
        private String deptName;
        private List<LeaveCensusVo> censusVoList;
        private Float total;
        private Float used;
        private Double rate;

    }
}
