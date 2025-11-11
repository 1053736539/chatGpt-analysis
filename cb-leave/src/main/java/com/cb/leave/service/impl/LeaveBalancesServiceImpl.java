package com.cb.leave.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.PageDomain;
import com.cb.common.core.page.TableSupport;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.sql.SqlUtil;
import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.LeaveBalancesChangeLog;
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.domain.vo.LeaveBalancesVo;
import com.cb.leave.mapper.LeaveBalancesMapper;
import com.cb.leave.mapper.LeaveTypesMapper;
import com.cb.leave.service.ILeaveBalancesChangeLogService;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.leave.util.UserRoleUtil;
import com.cb.message.service.MessageService;
import com.cb.system.mapper.SysDeptMapper;
import com.cb.system.service.ISysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 假期额度Service业务层处理
 *
 * @author yangcd
 * @date 2024-09-09
 */
@Service
public class LeaveBalancesServiceImpl implements ILeaveBalancesService
{
    @Autowired
    private LeaveBalancesMapper leaveBalancesMapper;

    @Autowired
    private LeaveTypesMapper leaveTypesMapper;

    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ILeaveBalancesChangeLogService changeLogService;


    /**
     * 查询假期额度
     *
     * @param id 假期额度ID
     * @return 假期额度
     */
    @Override
    public LeaveBalances selectLeaveBalancesById(Integer id)
    {
        return leaveBalancesMapper.selectLeaveBalancesById(id);
    }

    /**
     * 查询假期额度列表
     *
     * @param leaveBalances 假期额度
     * @return 假期额度
     */
    @Override
    public List<LeaveBalances> selectLeaveBalancesList(LeaveBalances leaveBalances)
    {
        return leaveBalancesMapper.selectLeaveBalancesList(leaveBalances);
    }

    /**
     * 新增假期额度
     *
     * @param leaveBalances 假期额度
     * @return 结果
     */
    @Override
    public int insertLeaveBalances(LeaveBalances leaveBalances) throws Exception {

        // 使用Calendar获取当前年份
//        Calendar calendar = Calendar.getInstance();
//        int currentYear = calendar.get(Calendar.YEAR);
//        leaveBalances.setLeaveYear(currentYear);
        // 检查是否存在该员工在当前年份和假期类型下的记录
//        根据年份新增公休假天数
        LeaveBalances existingBalance = leaveBalancesMapper.selectByUserIdAndType(leaveBalances.getUserId(), 1L, leaveBalances.getLeaveYear());

        if (existingBalance != null) {
            // 如果存在记录，抛出异常或者返回错误，避免重复插入
            throw new Exception("该员工在当前年份的该假期类型已经存在记录，无法重复插入");
        }
        leaveBalances.setCreateTime(DateUtils.getNowDate());
        return leaveBalancesMapper.insertLeaveBalances(leaveBalances);
    }

    @Override
    public int batchGenerateLeaveBalances(LeaveBalances leaveBalances) throws Exception {
        SysUser query = new SysUser();
        query.setStatus("0");
        List<SysUser> sysUsers = sysUserService.selectUserListByAdmin(query);
        Date date = new Date();
        final Integer leaveType = 1; //公休假
        LeaveBalances balances;
        List<LeaveBalances> balancesList;
        List<LeaveBalances> leaveBalancesList = leaveBalancesMapper.selectLeaveBalancesList(leaveBalances);
        if(sysUsers.size()>0){
            try{
                for (SysUser user : sysUsers) {
                    balances = new LeaveBalances();
                    balances.setUserId(user.getUserId());
                    balances.setLeaveYear(leaveBalances.getLeaveYear());
                    balances.setLeaveTypeId(leaveType);
                    balancesList = leaveBalancesMapper.selectLeaveBalancesList(balances);
                    int workYear = 0;
                    if (balancesList.isEmpty()) {
                        String startWorkTime = user.getStartWorkTime();
                        if (StringUtils.isNotEmpty(startWorkTime) && StringUtils.isNotNull(startWorkTime)) {
                            try {
                                workYear = calculateWorkingYears(startWorkTime);
                            }catch (Exception e) {
                                System.out.println("eeeeeee========="+e+"name"+user.getUserName()+user.getNickName());
                            }
                        }
                        if(workYear < 1) {
                            balances.setTotalDays(0F);
                            balances.setPendingDays(0F);
                            balances.setRemainingDays(0F);
                        } else if(workYear >= 1 && workYear < 10) {
                            balances.setTotalDays(5F);
                            balances.setPendingDays(5F);
                            balances.setRemainingDays(5F);
                        } else if(workYear >= 10 && workYear < 20) {
                            balances.setTotalDays(10F);
                            balances.setPendingDays(10F);
                            balances.setRemainingDays(10F);
                        } else {
                            balances.setTotalDays(15F);
                            balances.setPendingDays(15F);
                            balances.setRemainingDays(15F);
                        }
                        balances.setUsedDays(0f);
                        balances.setCreateTime(date);
                        leaveBalancesMapper.insertLeaveBalances(balances);
                    }else{
                        //存在公休假天数,工作年限发生变化的，自动更新天数
                        for(LeaveBalances leaveBalances1 :balancesList){
                            String startWorkTime = user.getStartWorkTime();
                            if (StringUtils.isNotEmpty(startWorkTime) && StringUtils.isNotNull(startWorkTime)) {
                                try {
                                    workYear = calculateWorkingYears(startWorkTime);
                                }catch (Exception e) {
                                    System.out.println("eeeeeee========="+e+"name"+user.getUserName()+user.getNickName());
                                }
                            }
                            if(workYear < 1) {
                                balances.setTotalDays(0F);
                                balances.setPendingDays(0F);
                                balances.setRemainingDays(0F);
                            } else if(workYear >= 1 && workYear < 10) {
                                balances.setTotalDays(5F);
                                balances.setPendingDays(5F-leaveBalances1.getUsedDays());
                                balances.setRemainingDays(5F-leaveBalances1.getUsedDays());
                            } else if(workYear >= 10 && workYear < 20) {
                                balances.setTotalDays(10F);
                                balances.setPendingDays(10F-leaveBalances1.getUsedDays());
                                balances.setRemainingDays(10F-leaveBalances1.getUsedDays());
                            } else {
                                balances.setTotalDays(15F);
                                balances.setPendingDays(15F-leaveBalances1.getUsedDays());
                                balances.setRemainingDays(15F-leaveBalances1.getUsedDays());
                            }
                            balances.setUsedDays(leaveBalances1.getUsedDays());
                            balances.setUpdateTime(date);
                            balances.setId(leaveBalances1.getId());
                            leaveBalancesMapper.updateLeaveBalances(balances);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new Exception("当前年份的公休假已经存在记录，无法重复插入",e);
//            System.out.println("eeeeeee===="+e);
            };
        }

        return 0;
    }
    @Scheduled(cron = "0 0 0 1 * ?") // 每月1号凌晨00:00执行一次
//    @Scheduled(cron = "0 0/1 * * * ?") //每隔一分钟执行一次
    public void autoBatchGenerateLeaveBalances() {
        SysUser query = new SysUser();
        query.setStatus("0");
        List<SysUser> sysUsers = sysUserService.selectUserListByAdminBalance(query);
            if (sysUsers.size()==0){
              return;
            }
        Date date = new Date();
        //取当前年
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        final Integer leaveType = 1; //公休假
        List<LeaveBalances> balancesList;
        LeaveBalances leaveBalances = new LeaveBalances();
        leaveBalances.setLeaveYear(year);
        List<LeaveBalances> leaveBalancesList = leaveBalancesMapper.selectLeaveBalancesList(leaveBalances);
        LeaveBalances balances;
        if(sysUsers.size()>0){
                    for (SysUser user : sysUsers) {
                        balances = new LeaveBalances();
                        balances.setUserId(user.getUserId());
                        balances.setLeaveYear(year);
                        balances.setLeaveTypeId(leaveType);
                        balancesList = leaveBalancesMapper.selectLeaveBalancesList(balances);
                        int workYear = 0;
                        //当前年所有人员公休假天数已存在，根据工作年限更新公休天数"
                        String startWorkTime = user.getStartWorkTime();
                        if (StringUtils.isNotEmpty(startWorkTime) && StringUtils.isNotNull(startWorkTime)) {
                            try {
                                workYear = calculateWorkingYears(startWorkTime);
                            }catch (Exception e) {
                                System.out.println("eeeeeee========="+e+"name"+user.getUserName()+user.getNickName());
                            }
                        }
                        if (!balancesList.isEmpty()) {
                            //存在公休假天数,工作年限发生变化的，自动更新天数
                            for(LeaveBalances leaveBalances1 :balancesList){
                                if(workYear < 1) {
                                    balances.setTotalDays(0F);
                                    balances.setPendingDays(0F);
                                    balances.setRemainingDays(0F);
                                } else if(workYear >= 1 && workYear < 10) {
                                    balances.setTotalDays(5F);
                                    balances.setPendingDays(5F-leaveBalances1.getUsedDays());
                                    balances.setRemainingDays(5F-leaveBalances1.getUsedDays());
                                } else if(workYear >= 10 && workYear < 20) {
                                    balances.setTotalDays(10F);
                                    balances.setPendingDays(10F-leaveBalances1.getUsedDays());
                                    balances.setRemainingDays(10F-leaveBalances1.getUsedDays());
                                } else {
                                    balances.setTotalDays(15F);
                                    balances.setPendingDays(15F-leaveBalances1.getUsedDays());
                                    balances.setRemainingDays(15F-leaveBalances1.getUsedDays());
                                }
                                balances.setUsedDays(leaveBalances1.getUsedDays());
                                balances.setUpdateTime(date);
                                balances.setId(leaveBalances1.getId());
                                leaveBalancesMapper.updateLeaveBalances(balances);
                            }
                        }else{
                            //没有公休假天数，自动新增
                                if(workYear < 1) {
                                    balances.setTotalDays(0F);
                                    balances.setPendingDays(0F);
                                    balances.setRemainingDays(0F);
                                } else if(workYear >= 1 && workYear < 10) {
                                    balances.setTotalDays(5F);
                                    balances.setPendingDays(5F);
                                    balances.setRemainingDays(5F);
                                } else if(workYear >= 10 && workYear < 20) {
                                    balances.setTotalDays(10F);
                                    balances.setPendingDays(10F);
                                    balances.setRemainingDays(10F);
                                } else {
                                    balances.setTotalDays(15F);
                                    balances.setPendingDays(15F);
                                    balances.setRemainingDays(15F);
                                }
                                balances.setUsedDays(0f);
                                balances.setCreateTime(date);
                                leaveBalancesMapper.insertLeaveBalances(balances);
                            }
                }
        }
    }


    /**
     * 修改假期额度
     *
     * @param leaveBalances 假期额度
     * @return 结果
     */
    @Override
    public int updateLeaveBalances(LeaveBalances leaveBalances) throws Exception {
        // 确保要更新的记录存在
        LeaveBalances existingBalance = leaveBalancesMapper.selectLeaveBalancesById(leaveBalances.getId());
        if (existingBalance == null) {
            throw new Exception("要更新的假期余额记录不存在");
        }

        // 如果允许更新 leaveTypeId 或 leaveYear，需要确保不会与其他记录冲突
        if (!existingBalance.getLeaveTypeId().equals(leaveBalances.getLeaveTypeId()) ||
                !existingBalance.getLeaveYear().equals(leaveBalances.getLeaveYear())) {
            // 检查是否有其他记录存在相同的员工ID、假期类型和年份
            LeaveBalances conflictBalance = leaveBalancesMapper.selectByUserIdAndType(
                    leaveBalances.getUserId(), Long.valueOf(leaveBalances.getLeaveTypeId()), leaveBalances.getLeaveYear());

            if (conflictBalance != null && !conflictBalance.getId().equals(leaveBalances.getId())) {
                throw new Exception("该员工在该年份的该假期类型已经存在记录，无法更新");
            }
        }
        leaveBalances.setUpdateTime(DateUtils.getNowDate());
        return leaveBalancesMapper.updateLeaveBalances(leaveBalances);
    }

    /**
     * 批量删除假期额度
     *
     * @param ids 需要删除的假期额度ID
     * @return 结果
     */
    @Override
    public int deleteLeaveBalancesByIds(Integer[] ids)
    {
        return leaveBalancesMapper.deleteLeaveBalancesByIds(ids);
    }

    /**
     * 删除假期额度信息
     *
     * @param id 假期额度ID
     * @return 结果
     */
    @Override
    public int deleteLeaveBalancesById(Integer id)
    {
        return leaveBalancesMapper.deleteLeaveBalancesById(id);
    }

    /**
     * @Auther: yangcd
     * @Date: 2024/9/10 13:54
     * @param userId
     * @Description: 检查请假额度
     */
    @Override
    public boolean checkBalance(Long userId, Long leaveTypeId, Integer requestedDays, Integer leaveYear) throws Exception {
        // 获取假期类型
        LeaveTypes leaveType = leaveTypesMapper.selectLeaveTypesById(leaveTypeId);

        // 判断假期类型，如果是病假、事假或值班补休，直接返回true
        if ("病假".equals(leaveType.getLeaveName()) ||
                "事假".equals(leaveType.getLeaveName()) ||
                "值班补休".equals(leaveType.getLeaveName())) {
            return true;  // 不需要检查假期余额，允许申请
        }

        // 需要检查余额的假期类型（如年假等）
        LeaveBalances leaveBalances = leaveBalancesMapper.selectByUserIdAndType(userId, 1L, leaveYear);

        if (leaveBalances == null) {
            // 如果未找到该用户的假期余额信息，返回false
//            return false;
            throw new Exception("未找到对应的假期类型余额");
        }

        // 检查剩余天数是否足够
        if (leaveBalances.getRemainingDays() <= requestedDays) {
//            return false;  // 假期余额不足
            throw new Exception(leaveType.getLeaveName()+"剩余天数不足");
        }

        // 如果余额足够，返回true
        return true;
    }

    @Override
    public void updateLeaveBalancesAfterApproval(Long userId, Long leaveTypeId, Float approvedDays, Integer leaveYear) throws Exception {
        // 获取假期类型
        LeaveTypes leaveType = leaveTypesMapper.selectLeaveTypesById(leaveTypeId);

        // 判断假期类型，如果是病假、事假或值班补休，直接返回true
        if ("病假".equals(leaveType.getLeaveName()) ||
                "事假".equals(leaveType.getLeaveName()) ||
                "值班补休".equals(leaveType.getLeaveName())) {
            return;
        }
            // 获取假期余额记录李
            LeaveBalances leaveBalances = leaveBalancesMapper.selectByUserIdAndType(userId, 1L, leaveYear);

            if (leaveBalances == null) {
                throw new Exception("未找到该用户的假期余额记录");

            }

            // 如果任一字段为null，则初始化为0
            if (leaveBalances.getUsedDays() == null) {
                leaveBalances.setUsedDays(0f);
            }
            if (leaveBalances.getPendingDays() == null) {
                leaveBalances.setPendingDays(0f);
            }
            if (leaveBalances.getRemainingDays() == null) {
                leaveBalances.setRemainingDays(0f);
            }

            // 更新已休天数
            leaveBalances.setUsedDays(leaveBalances.getUsedDays() + approvedDays);

            // 更新待休天数（已批准的假期应从待休天数中扣除）
            leaveBalances.setPendingDays(leaveBalances.getPendingDays() - approvedDays);

            // 更新剩余天数
            leaveBalances.setRemainingDays(leaveBalances.getRemainingDays() - approvedDays);

            // 设置更新时间
            leaveBalances.setUpdateTime(DateUtils.getNowDate());

            // 更新假期余额记录
            leaveBalances.setUpdateTime(DateUtils.getNowDate());
            leaveBalancesMapper.updateLeaveBalances(leaveBalances);

    }
    /**
     * 获取所有用户的应休未休统计
     * 调用Mapper层接口获取数据库中的应休未休信息
     *
     * @return List<LeaveBalanceDTO> 应休未休统计列表
     */
    @Override
    public List<LeaveBalancesVo> getUnusedLeaveStatistics(LeaveBalances leaveBalances) {
        return leaveBalancesMapper.findUnusedLeaveStatistics(leaveBalances);
    }

    @Override
    public List<LeaveBalancesVo> listLeaveBalanceByUser(LeaveBalances leaveBalances, SysUser user, boolean pageQuery) {
        //根据当前用户查询假期额度
        List<Long> deptIds = new LinkedList<>();
        Long leaderId= user.getUserId();
        List<String> fgRoleKeys = UserRoleUtil.getFgRoleKeys(user);
        // admin/组织部管理员/书记角色 可以查看所有人的数据
        if (user.isAdmin() || UserRoleUtil.hasRole_shuji(user) || UserRoleUtil.hasRole_organization_admin(user)){
            leaderId = null;
        } else if(fgRoleKeys.size()>=1&&(UserRoleUtil.hasRole_fushuji(user) || UserRoleUtil.hasRole_changwei(user))){  //副书记 或 常委 查分管的部门
            List<SysDept> chargeDeptList = deptMapper.getDeptListByRoleKeys(fgRoleKeys);
            List<SysDept> deptList = new ArrayList<>();
            //分管部门及其子部门查询
            for (int i = 0; i < chargeDeptList.size(); i++) {
                SysDept o = chargeDeptList.get(i);
                String ancestors = o.getAncestors() + ',' + o.getDeptId();
                List<SysDept> oList = deptMapper.selectDeptListLeftLikeAncestorsAndDeptId(ancestors, o.getDeptId());
                if(!oList.isEmpty()){
                    deptList.addAll(oList);
                }
            }
            deptList = distinctSordExculdeDept(deptList, !UserRoleUtil.nameIsAdmin(user));
            Set<Long> deptIdSet = deptList.stream().map(SysDept::getDeptId).collect(Collectors.toSet());
            deptIds = new ArrayList<>(deptIdSet);
        } else if("1".equals(user.getIsMainLeader())){//部门负责人看部门所有
            Long deptId = user.getDeptId();
            if(null != deptId){
                deptIds = Collections.singletonList(deptId);
            }
        } else {//其他人看自己
            leaveBalances.setUserId(user.getUserId());
        }
        //分页查询
        if(pageQuery){
            startPage();
        }
        return leaveBalancesMapper.listLeaveBalanceByUser(leaveBalances,deptIds,leaderId, !UserRoleUtil.nameIsAdmin(user));
    }

    /**
     * 去重并排序
     * @param orginList
     * @param exculde 是否排除 县（市）区纪委、监委这个及以下的部门
     * @return
     */
    private List<SysDept> distinctSordExculdeDept(List<SysDept> orginList, boolean exculde){
        Stream<SysDept> deptStream  = orginList.stream().filter(o -> {
            Long deptId = o.getDeptId();
            if(new Long(100L).equals(deptId)){
                return false;
            }
            String status = o.getStatus();
            String delFlag = o.getDelFlag();
            return "0".equals(status) && "0".equals(delFlag);
        });
        if(exculde){
            deptStream = deptStream.filter(o -> {
                Long deptId = o.getDeptId();
                String ancestors = o.getAncestors();
                return !(new Long(138L).equals(deptId) || ancestors.startsWith("0,100,138"));
            });
        }
        Map<Long, SysDept> deptIdMap = deptStream.collect(Collectors.toMap(SysDept::getDeptId, o -> o, (o1, o2) -> o1));
        return deptIdMap.values().stream()
                .sorted(Comparator.comparing(o->{
                    String str = o.getOrderNum();
                    return StringUtils.isNotBlank(str) ? Integer.parseInt(str) : Integer.MAX_VALUE;
                }))
                .collect(Collectors.toList());
    }

    private void startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * @Auther: yangcd
     * @Date: 2024/9/12 16:42
     * @param user
     * @Description: 添加年休假(当前年）
     */
    @Override
    public void addAnnualLeaveForUser(SysUser user) {
        int workingYears = calculateWorkingYears(user.getStartWorkTime());
        int annualLeaveDays = calculateAnnualLeaveDays(workingYears);
        int currentYear = LocalDate.now().getYear();

        // 检查该用户在当前年份的年休假记录是否存在
        LeaveBalances existingLeaveBalance = leaveBalancesMapper.selectByUserIdAndType(
                user.getUserId(), 1L, currentYear); // 1 表示年休假类型ID

        if (existingLeaveBalance != null) {
            // 如果记录存在，更新假期天数和相关信息
            existingLeaveBalance.setTotalDays((float) annualLeaveDays); // 更新总天数
            existingLeaveBalance.setRemainingDays(existingLeaveBalance.getTotalDays() - existingLeaveBalance.getUsedDays()); // 更新剩余天数
            existingLeaveBalance.setUpdateBy(user.getUpdateBy());
            existingLeaveBalance.setUpdateTime(DateUtils.getNowDate());

            leaveBalancesMapper.updateLeaveBalances(existingLeaveBalance);
        } else {
            // 如果记录不存在，则插入新的假期记录
            if (annualLeaveDays > 0) {
                LeaveBalances newLeaveBalances = new LeaveBalances();
                newLeaveBalances.setUserId(user.getUserId());
                newLeaveBalances.setLeaveTypeId(1); // 年休假类型ID为1
                newLeaveBalances.setLeaveYear(currentYear); // 当前年份
                newLeaveBalances.setTotalDays((float) annualLeaveDays); // 总天数
                newLeaveBalances.setUsedDays(0f); // 初始已休天数为0
                newLeaveBalances.setPendingDays(0f); // 初始待休天数为0
                newLeaveBalances.setRemainingDays((float) annualLeaveDays); // 初始剩余天数等于总天数
                newLeaveBalances.setCreateBy(user.getCreateBy());
                newLeaveBalances.setCreateTime(DateUtils.getNowDate());

                leaveBalancesMapper.insertLeaveBalances(newLeaveBalances);
            }
        }
    }
    public static boolean isValidBySDF(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public int calculateWorkingYears(String startWorkTime) {
        //  startWorkTime 格式
        try {
                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM");
                Date date = sdfInput.parse(startWorkTime);
                SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
                String fullDate = sdfOutput.format(date);
                LocalDate startDate = LocalDate.parse(fullDate);
                LocalDate currentDate = LocalDate.now();
                // 计算两个日期之间的年数
                return Period.between(startDate, currentDate).getYears();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("e==========错误"+e);
        }
      return 0;

    }


    /**
     * @Auther: yangcd
     * @Date: 2024/9/12 16:44
     * @param workingYears
     * @Description:  根据工作年限决定员工的年休假天数：
     */
    public int calculateAnnualLeaveDays(int workingYears) {
        if (workingYears < 1) {
            return 0; // 工作不足1年，没有年休假
        } else if (workingYears < 10) {
            return 5; // 工作满1年不满10年
        } else if (workingYears < 20) {
            return 10; // 工作满10年不满20年
        } else {
            return 15; // 工作满20年
        }
    }

    @Override
    public void notifyUnusedPublicHolidayByYear(Integer year) {
        if(null == year){
            return;
        }
        LeaveBalances leaveBalances = new LeaveBalances();
        leaveBalances.setLeaveYear(year);
        notifyUnusedPublicHoliday(leaveBalances);
    }

    @Override
    public void notifyUnusedPublicHoliday(LeaveBalances leaveBalances) {
        Integer leaveYear = leaveBalances.getLeaveYear();
        //公休假
        leaveBalances.setLeaveTypeId(1);
        List<LeaveBalancesVo> unusedLeaveStatistics = getUnusedLeaveStatistics(leaveBalances);
        if(unusedLeaveStatistics.isEmpty()){
            return;
        }

        String senderName;
        try{
            senderName = SecurityUtils.getUsername();
        } catch (Exception e){
            senderName = "admin";
        }

        for (int i = 0; i < unusedLeaveStatistics.size(); i++) {
            LeaveBalancesVo vo = unusedLeaveStatistics.get(i);
            Long userId = vo.getUserId();
            String nickName = vo.getNickName();
            Float remainingDays = vo.getRemainingDays();

            if(remainingDays > 0){
                //发给个人
                String title = String.format("%d年的公休假应休未休",leaveYear);
                String message = String.format("您好，%s，您剩余的公休假为%.2f天，请及时申请休假。",nickName,remainingDays);
                messageService.sendMessage2User(title,message,senderName,userId+"");
                SysUser user = sysUserService.selectUserById(userId);
                //不是主要领导的
                if("0".equals(user.getIsMainLeader())){
                    List<SysUser> leaders = sysUserService.selectMainLeaderByDeptId(user.getDeptId());
                    if(!leaders.isEmpty()){
                        List<Long> leaderUserIds = leaders.stream().map(SysUser::getUserId).filter(Objects::nonNull).collect(Collectors.toList());
                        String leaderMessage = String.format("您好，您部门内的下属：%s，存在应休未休的公休假，剩余的公休假为%.2f天，请及时合理安排休假。",nickName,remainingDays);
                        messageService.sendMessage2User(title,leaderMessage,senderName,leaderUserIds);
                    }
                }
            }
        }
    }

    /**
     * @Auther wws
     * @Date 2025年2月21日
     * @param user
     * @param holidayYear
     * @Description:根据假期年度添加年休假
     */
    @Override
    public void addUsedLeaveForUser(SysUser user,Integer holidayYear) {
        int startWorkYear = Integer.valueOf(user.getStartWorkTime().substring(0,4));
        int workingYears = holidayYear-startWorkYear;
        int annualLeaveDays = calculateAnnualLeaveDays(workingYears);
        // 检查该用户在假期年度的年休假记录是否存在
        LeaveBalances existingLeaveBalance = leaveBalancesMapper.selectByUserIdAndType(
                user.getUserId(), 1L, holidayYear); // 1 表示年休假类型ID

        if (existingLeaveBalance != null) {
            // 如果记录存在，更新假期天数和相关信息
            existingLeaveBalance.setTotalDays((float) annualLeaveDays); // 更新总天数
            existingLeaveBalance.setRemainingDays(existingLeaveBalance.getTotalDays() - existingLeaveBalance.getUsedDays()); // 更新剩余天数
            existingLeaveBalance.setUpdateBy(user.getUpdateBy());
            existingLeaveBalance.setUpdateTime(DateUtils.getNowDate());

            leaveBalancesMapper.updateLeaveBalances(existingLeaveBalance);
        } else {
            // 如果记录不存在，则插入新的假期记录
            if (annualLeaveDays > 0) {
                LeaveBalances newLeaveBalances = new LeaveBalances();
                newLeaveBalances.setUserId(user.getUserId());
                newLeaveBalances.setLeaveTypeId(1); // 年休假类型ID为1
                newLeaveBalances.setLeaveYear(holidayYear); // 假期年度
                newLeaveBalances.setTotalDays((float) annualLeaveDays); // 总天数
                newLeaveBalances.setUsedDays(0f); // 初始已休天数为0
                newLeaveBalances.setPendingDays(0f); // 初始待休天数为0
                newLeaveBalances.setRemainingDays((float) annualLeaveDays); // 初始剩余天数等于总天数
                newLeaveBalances.setCreateBy(user.getCreateBy());
                newLeaveBalances.setCreateTime(DateUtils.getNowDate());

                leaveBalancesMapper.insertLeaveBalances(newLeaveBalances);
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void openBalanceConfirm(Integer year) {
        LeaveBalances query = new LeaveBalances();
        query.setLeaveYear(year);
        List<LeaveBalances> list = selectLeaveBalancesList(query);
        Integer[] ids = list.stream().map(LeaveBalances::getId).toArray(Integer[]::new);
        List<Integer> existIds = ArrayUtil.isEmpty(ids) ? Collections.emptyList() : changeLogService.checkExistIds(ids);
        // 先保存对应的原始记录日志
        List<LeaveBalancesChangeLog> changeLogList = new LinkedList<>();
        list.forEach(leaveBalances -> {
            // 如果不存在，则需要新增
            if(!existIds.contains(leaveBalances.getId())){
                LeaveBalancesChangeLog log = new LeaveBalancesChangeLog();
                log.setId(leaveBalances.getId());
                log.setInitData(JSON.toJSONString(leaveBalances));
                changeLogList.add(log);
            }
        });
        if(ObjectUtil.isNotEmpty(changeLogList)){
            changeLogService.insertBatch(changeLogList);
        }
        //修改状态 为待确认
        String userName = SecurityUtils.getUsername();
        leaveBalancesMapper.batchOpenStatusByYear(year, userName, new Date());
    }

    @Transactional
    @Override
    public int confirmBalance(Integer[] ids, Long leaderId) {
        if(null == ids || ids.length < 1){
            return 0;
        }
        Integer status = 2;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        boolean isMainLeader = "1".equals(user.getIsMainLeader());
        if(isMainLeader){
            status = 3;
            leaderId = user.getUserId();//设置审批人为自己
        } else {
            if(null == leaderId){
                throw new IllegalArgumentException("请选择审核人！");
            }
        }
        //更新状态为审核中或已确认（负责人）
        leaveBalancesMapper.batchConfirm(ids, status, leaderId, SecurityUtils.getUsername(), new Date());

        //查当前数据
        Map<Integer, LeaveBalances> dataMap = leaveBalancesMapper.selectListByIds(ids).stream()
                .collect(Collectors.toMap(LeaveBalances::getId, o -> o, (o1, o2) -> o2));
        //查出日志
        Map<Integer, LeaveBalancesChangeLog> logMap = changeLogService.selectListByIds(ids).stream()
                .collect(Collectors.toMap(LeaveBalancesChangeLog::getId, o -> o, (o1, o2) -> o2));
        //记录修改变化日志
        for (int i = 0; i < ids.length; i++) {
            Integer id = ids[i];
            LeaveBalances leaveBalances = dataMap.get(id);
            LeaveBalancesChangeLog log = logMap.get(id);
            if(null == log){
                // 按理说开启确认时应该会生成日志记录，假使没有日志记录时防止报错
                log = new LeaveBalancesChangeLog();
                log.setId(id);
                log.setLastData(JSON.toJSONString(leaveBalances));
                changeLogService.insertLeaveBalancesChangeLog(log);
            } else {
                log.setLastData(JSON.toJSONString(leaveBalances));
                changeLogService.updateLeaveBalancesChangeLog(log);
                try{
                    String initDataStr = log.getInitData();
                    if(StringUtils.isNotBlank(initDataStr)){
                        LeaveBalances initData = JSON.parseObject(initDataStr, LeaveBalances.class);
                        if(null != initData){
                            Integer changeFlag = balanceHasChange(initData,leaveBalances) ? 1 : 0;
                            leaveBalances.setChangeFlag(changeFlag);
                            updateLeaveBalances(leaveBalances);
                        }
                    }
                } catch (Exception e){}

            }
        }
        return ids.length;
    }

    private boolean balanceHasChange(LeaveBalances before, LeaveBalances after){
        return !floatEquals(before.getTotalDays(),after.getTotalDays())
                || !floatEquals(before.getUsedDays(),after.getUsedDays())
                || !floatEquals(before.getRemainingDays(),after.getRemainingDays());
    }

    private boolean floatEquals(Float f1, Float f2){
        BigDecimal b1 = new BigDecimal(Float.toString(f1));
        BigDecimal b2 = new BigDecimal(Float.toString(f2));
        return b1.compareTo(b2) == 0;
    }

    @Override
    public int approveBalance(Integer[] ids, Integer status, String reason) {
        if(null == ids || ids.length < 1){
            return 0;
        }
        return leaveBalancesMapper.batchApproveByIds(ids, status, reason, SecurityUtils.getUsername(), new Date());
    }

    @Override
    public int fixChangeFlag() {
        List<LeaveBalancesChangeLog> logList = changeLogService.listMaybeNeedFixChangeFlagList();
        if(null == logList || logList.isEmpty()){
            return 0;
        }
        List<Integer> ids = new LinkedList<>();
        // 根据id查询对应的数据，然后对比，如果数据有变化，则修改changeFlag为1
        logList.forEach(log -> {
            LeaveBalances init = JSON.parseObject(log.getInitData(), LeaveBalances.class);
            LeaveBalances last = JSON.parseObject(log.getLastData(), LeaveBalances.class);
            if(balanceHasChange(init,last)){
                Integer id = log.getId();
                ids.add(id);
            }
        });
        if(ids.isEmpty()){
            return 0;
        }

        return leaveBalancesMapper.batchSetChangeFlag(ids.toArray(new Integer[0]),1);
    }

    @Override
    public LeaveBalances leaveBalanceByUserId(LeaveBalances leaveBalances) {
       return  leaveBalancesMapper.selectLeaveBalanceByUserId(leaveBalances);
    }
}
