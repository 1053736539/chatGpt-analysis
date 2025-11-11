package com.cb.activiti.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.enums.UserLevelEnum;
import com.cb.activiti.service.IBizLeaveService;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.spring.SpringUtils;
import com.cb.leave.calendar.service.ICalendarService;
import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.leave.service.ILeaveTypesService;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LeaveUtil {

    private static ISysUserService userService;
    private static ISysDeptService deptService;
    private static ILeaveTypesService leaveTypeService;
    private static ILeaveBalancesService leaveBalanceService;

    private static ICalendarService calendarService;

    private  static IBizLeaveService bizLeaveService;

    static {
        userService = SpringUtils.getBean(ISysUserService.class);
        deptService = SpringUtils.getBean(ISysDeptService.class);
        leaveTypeService = SpringUtils.getBean(ILeaveTypesService.class);
        leaveBalanceService = SpringUtils.getBean(ILeaveBalancesService.class);
        calendarService = SpringUtils.getBean(ICalendarService.class);
        bizLeaveService = SpringUtils.getBean(IBizLeaveService.class);
    }

    /**
     * 设置发起人级别
     * @return
     */
    public static void setCreateUserLevel(BizLeave leave){
        if(null != leave){
            String userName = leave.getCreateBy();
            SysUser user = userService.selectUserByUserName(userName);
            SysDept dept = user.getDept();
            leave.setCreateUserLevel(UserLevelEnum.getByUserAndDept(user,dept).getCode());
        }
    }

    /**
     * 请假之前检查
     * @param bizLeave
     */
    public static void checkLeaveLimit(BizLeave bizLeave) {
        // TODO 是否要计算可用额度，还是当年次数，还是别的允许条件
        // ......

        Date leaveStartTime = bizLeave.getLeaveStartTime();
        Date leaveEndTime = bizLeave.getLeaveEndTime();
        int leaveTypeId = bizLeave.getType().intValue();
        String setApplyUserId = SecurityUtils.getUsername();
        bizLeave.setApplyUserId(setApplyUserId);
        //查询以上时间段内有无请假
         List<BizLeave> bizLeaveList = bizLeaveService.selectPassedBizLeaveList(bizLeave);
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
         String startTime= sdfOutput.format(leaveStartTime);
         String endTime= sdfOutput.format(leaveEndTime);
         boolean hasLeaveInRange= hasLeaveInRange(bizLeaveList,LocalDate.parse(startTime),LocalDate.parse(endTime));
         if(hasLeaveInRange){
             throw new RuntimeException(String.format("您%s年度,%s至%s内请过假，请核实检查后，再请假！",bizLeave.getHolidayYear(), startTime, endTime));
         }
        // 病假、事假不做限制，其它的类型要进行检查
//        if(2 == leaveTypeId || 3 == leaveTypeId){
//            return;
//        }
//        公休假进行检查，其他的不做限制
        if(1 == leaveTypeId){
            LeaveTypes leaveTypes = leaveTypeService.selectLeaveTypesById(bizLeave.getType());
            // 计算请假天数
            float leaveDays = countLeaveDay(leaveStartTime,leaveEndTime, bizLeave.getType(), bizLeave.getIsHalfDay().equals(1));
            String userName = SecurityUtils.getUsername();
            SysUser applyUser = userService.selectUserByUserName(userName);
            LeaveBalances query = new LeaveBalances();
            query.setUserId(applyUser.getUserId());
            query.setLeaveYear(Integer.valueOf(bizLeave.getHolidayYear()));
            query.setLeaveTypeId(leaveTypeId);
            List<LeaveBalances> leaveBalances = leaveBalanceService.selectLeaveBalancesList(query);
            if(null == leaveBalances || leaveBalances.isEmpty()){
                throw new RuntimeException(String.format("您%s年度的%s类型暂无可请天数！",bizLeave.getHolidayYear(),leaveTypes.getLeaveName()));
            }
            LeaveBalances balances = leaveBalances.get(0);
            if(null != balances.getStatus()){
                throw new RuntimeException(String.format("您%s年度的%s类型的假期额度已处确认审核的阶段！该年度该类型的假期不能发起申请了！",bizLeave.getHolidayYear(),leaveTypes.getLeaveName()));
            } else if(balances.getRemainingDays() < leaveDays){
                throw new RuntimeException(String.format("您%s年度的%s类型可用的天数不足，当前可用天数为%.1f天！",bizLeave.getHolidayYear(),leaveTypes.getLeaveName(),leaveBalances.get(0).getRemainingDays()));
            }
        }else{
            return;
        }

    }

    /**
     * 计算请假天数
     * @param startTime
     * @param endTime
     * @param isHalfDay 是否半天
     * @return
     */
    public static float countLeaveDay(Date startTime, Date endTime, Long leaveTypeId, boolean isHalfDay){
        int workDays = calendarService.calcWorkDays(DateUtil.format(startTime,"yyyy-MM-dd"),DateUtil.format(endTime,"yyyy-MM-dd"),leaveTypeId);
        if(workDays > 0 && isHalfDay){
            return workDays - 0.5f;
        } else if(workDays == 0 && isHalfDay){
            throw new IllegalArgumentException("请假天数为0时不能设置请半天!");
        } else {
            return workDays;
        }
    }

    /**
     * 判断给定的日期是否为工作日（周一到周五）
     * @param date
     * @return
     */
    public static boolean isWeekday(Date date) {
        int weekDay = DateUtil.dayOfWeek(date);
        return BooleanUtil.and(weekDay != 6, weekDay != 7); // 不是周六和周日
    }

    /**
     * 判断用户填写的请假时间段内是否请过假
     * @param leaves
     * @param checkStart
     * @param checkEnd
     * @return
     */
    public static boolean hasLeaveInRange(List<BizLeave> leaves, LocalDate checkStart, LocalDate checkEnd) {
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
        return leaves.stream()
                .anyMatch(leave -> !LocalDate.parse(sdfOutput.format(leave.getLeaveEndTime())).isBefore(checkStart) && !LocalDate.parse(sdfOutput.format(leave.getLeaveStartTime())).isAfter(checkEnd));
    }

}
