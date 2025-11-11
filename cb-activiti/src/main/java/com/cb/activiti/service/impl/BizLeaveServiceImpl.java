package com.cb.activiti.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.domain.TaskVo;
import com.cb.activiti.enums.UserLevelEnum;
import com.cb.activiti.mapper.TaskMapper;
import com.cb.activiti.service.IProcessService;
import com.cb.activiti.util.LeaveUtil;
import com.cb.common.annotation.DataScope;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.vo.ImportLeaveVo;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.DictUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.mapper.LeaveBalancesMapper;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.leave.service.ILeaveTypesService;
import com.cb.system.service.ISysUserService;
import lombok.AllArgsConstructor;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import com.cb.activiti.mapper.BizLeaveMapper;
import com.cb.activiti.service.IBizLeaveService;
import org.springframework.util.CollectionUtils;

/**
 * 请假Service业务层处理
 *
 * @author 一只闲鹿
 * @date 2020-11-29
 */
@Service
@AllArgsConstructor
public class BizLeaveServiceImpl implements IBizLeaveService
{
    private BizLeaveMapper bizLeaveMapper;
    private IProcessService processService;

    private ISysUserService userService;

    private ILeaveTypesService leaveTypesService;

    private TaskMapper taskMapper;

    private ILeaveBalancesService leaveBalancesService;

    private LeaveBalancesMapper leaveBalancesMapper;

    /**
     * 查询请假
     *
     * @param id 请假ID
     * @return 请假
     */
    @Override
    public BizLeave selectBizLeaveById(Long id)
    {
        return bizLeaveMapper.selectBizLeaveById(id);
    }

    /**
     * 查询请假列表
     *
     * @param bizLeave 请假
     * @return 请假
     */
    @Override
    public List<BizLeave> selectBizLeaveList(BizLeave bizLeave)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId()) && !SecurityUtils.hasRole("organization_admin")) {
            bizLeave.setApplyUserId(SecurityUtils.getUsername());
        }
        List<BizLeave> list = bizLeaveMapper.selectBizLeaveList(bizLeave);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                try {
                    processService.richProcessField(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String userName=item.getApplyUserId();
//                String createBy = item.getCreateBy();
                SysUser applyUser = userService.selectUserByUserName(userName);
                   item.setApplyUser(applyUser);
            });
        }
        return list;
    }


    /**
     * 查询请假列表：个人查询个人的 组织部管理员查询所有人的请假情况
     *
     * @param bizLeave 请假
     * @return 请假
     */
    @Override
    public List<BizLeave> selectAllBizLeaveList(BizLeave bizLeave)
    {
        if (!SecurityUtils.hasRole("organization_admin")) {
            bizLeave.setApplyUserId(SecurityUtils.getUsername());
        }
        List<BizLeave> list = bizLeaveMapper.selectBizLeaveList(bizLeave);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                try {
                    processService.richProcessField(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String userName = item.getApplyUserId();
                SysUser applyUser = userService.selectUserByUserName(userName);
                item.setApplyUser(applyUser);
            });
        }
        return list;
    }

    /**
     * 查询请假列表：个人查询个人的 组织部管理员查询所有人的请假情况
     *(导出数据）
     * @param bizLeave 请假
     * @return 请假
     */
    @Override
    public List<BizLeave> selectHistoryBizLeaveList(BizLeave bizLeave)
    {
        if (!SecurityUtils.hasRole("organization_admin")) {
            bizLeave.setApplyUserId(SecurityUtils.getUsername());
        }
        List<BizLeave> list = bizLeaveMapper.selectBizLeaveList(bizLeave);
        return list;
    }


    /**
     * 导入请假数据（未使用）
     * @param leaveList
     * @param operName
     * @return
     */
    @Override
    public String importLeaveList(List<ImportLeaveVo> leaveList, String operName) {
        if (StringUtils.isNull(leaveList) || leaveList.size() == 0) {
            throw new CustomException("导入请假数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        BizLeave  bizLeave= new BizLeave();
        for(ImportLeaveVo leaveVo: leaveList){
            try{
                // 根据用户姓名查询用户信息
                SysUser u = userService.selectUserByUserName(leaveVo.getApplyUserName());
                bizLeave.setApplyUserName(leaveVo.getApplyUserName());
                if(StringUtils.isNotNull(u)) {
                    bizLeave.setCreateBy(SecurityUtils.getUsername());
                    bizLeave.setCreateTime(DateUtils.getNowDate());
                    bizLeave.setApplyUserId(SecurityUtils.getUsername());
                    bizLeave.setApplyTime(DateUtils.getNowDate());
                    bizLeave.setApplyUserName(SecurityUtils.getLoginUser().getUser().getNickName());
//                    bizLeave.setApplyUserId(u.getUserId().toString());//申请人ID
                    bizLeave.setDeptId(u.getDeptId());//部门ID
                    bizLeave.setWorkPost(u.getCurrentPosition());//职务
                    bizLeave.setDeptName(leaveVo.getDeptName());//部门
                    LeaveTypes leaveType= leaveTypesService.selectLeaveIdByLeavName(leaveVo.getType());
                    bizLeave.setType(Long.parseLong(leaveType.getId().toString()));
                    bizLeave.setLeaveStartTime(DateUtils.parseDate(leaveVo.getLeaveStartTime()));
                    bizLeave.setLeaveEndTime(DateUtils.parseDate(leaveVo.getLeaveEndTime()));
                    bizLeave.setLeaveNum(Float.valueOf(leaveVo.getLeaveNum()));

                }
            }catch (Exception e){

            }
        }

        return null;
    }

    /**
     * 新增请假
     *
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int insertBizLeave(BizLeave bizLeave)
    {
        bizLeave.setCreateBy(SecurityUtils.getUsername());
        bizLeave.setCreateTime(DateUtils.getNowDate());
        bizLeave.setApplyUserId(SecurityUtils.getUsername());
        bizLeave.setApplyTime(DateUtils.getNowDate());
        bizLeave.setApplyUserName(SecurityUtils.getLoginUser().getUser().getNickName());
        bizLeave.setProcessKey("leave");
        //设置createUserLevel
        String userName = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(userName);
        SysDept dept = user.getDept();
        bizLeave.setCreateUserLevel(UserLevelEnum.getByUserAndDept(user,dept).getCode());
        return bizLeaveMapper.insertBizLeave(bizLeave);
    }
    /**
     * 新增请假（导入的请假数据保存到数据库）
     *
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int insertImportBizLeave(BizLeave bizLeave)
    {
        return bizLeaveMapper.insertBizLeave(bizLeave);
    }

    @Override
    public List<BizLeave> selectPassedBizLeaveList(BizLeave bizLeave) {
        return bizLeaveMapper.selectPassedBizLeaveList(bizLeave);
    }

    /**
     * 修改请假
     *
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int updateBizLeave(BizLeave bizLeave)
    {
        bizLeave.setUpdateTime(DateUtils.getNowDate());
        bizLeave.setUpdateBy(SecurityUtils.getUsername());
        return bizLeaveMapper.updateBizLeave(bizLeave);
    }

    /**
     * 批量删除请假
     *
     * @param ids 需要删除的请假ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveByIds(Long[] ids)
    {
         List<BizLeave> bizLeaveList= bizLeaveMapper.selectBizLeaveByIds(ids);
         if(bizLeaveList.size()>0){
             for(BizLeave bizLeave: bizLeaveList){
               String  instanseId=bizLeave.getInstanceId();
               if(instanseId!=null &&instanseId!="" ) {
                   // 1、删除INSTANCE_BUSINESS表
                   Map<String, Object>  str=  taskMapper.selectInstanceBusinessByInstanceId(instanseId);
                   if(str!=null){
                       taskMapper.deleteByInstanceId(instanseId);
                   }
                   List<Map>  leavetaskList=taskMapper.findTaskLeaveList(instanseId);
                  if(leavetaskList.size()>0){
                      //2、删除待办任务表
                      taskMapper.deleteByTaskByInstanceId(instanseId);
                  }
                   List<Map>  leaveHisTaskList =taskMapper.findHisTaskLeaveList(instanseId);
                  if(leaveHisTaskList.size()>0){
                      //3、删除历史任务表
                      taskMapper.deleteHisTaskByInstanceId(instanseId);
                  }

               }
                 SysUser sysUser = userService.selectUserByUserName(bizLeave.getApplyUserId());
                 // 获取公休假余额记录
                 if(1l==bizLeave.getType()&&"1".equals(bizLeave.getStatus())&&StringUtils.isNotNull(sysUser)){
                     LeaveBalances leaveBalances = leaveBalancesMapper.selectByUserIdAndType(sysUser.getUserId(), 1l, Integer.parseInt(bizLeave.getHolidayYear()));
                     float usedDays = leaveBalances.getUsedDays()-bizLeave.getLeaveNum();
                     // 更新已休天数
                     leaveBalances.setUsedDays(leaveBalances.getUsedDays() - bizLeave.getLeaveNum());
                     // 更新待休天数（已删除的假期应从待休天数中加回来）
                     leaveBalances.setPendingDays(leaveBalances.getPendingDays() +  bizLeave.getLeaveNum());
                     // 更新剩余天数
                     leaveBalances.setRemainingDays(leaveBalances.getRemainingDays() + bizLeave.getLeaveNum());
                     // 设置更新时间
                     leaveBalances.setUpdateTime(DateUtils.getNowDate());
                     // 更新假期余额记录
                     leaveBalances.setUpdateTime(DateUtils.getNowDate());
                     leaveBalancesMapper.updateLeaveBalances(leaveBalances);
                 }

         }

    }

        return bizLeaveMapper.deleteBizLeaveByIds(ids);
    }

    /**
     * 删除请假信息
     *
     * @param id 请假ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveById(Long id)
    {
        return bizLeaveMapper.deleteBizLeaveById(id);
    }

    @Override
    public List<BizLeave> listByTypeOrHospital(BizLeave bizLeave) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())  && !SecurityUtils.hasRole("organization_admin")) {
            bizLeave.setApplyUserId(SecurityUtils.getUsername());
        }
        List<BizLeave> list = bizLeaveMapper.listByTypeOrHospital(bizLeave);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                try {
                    processService.richProcessField(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String createBy = item.getApplyUserId();
                SysUser applyUser = userService.selectUserByUserName(createBy);
                item.setApplyUser(applyUser);
            });
        }
        return list;
    }
}
