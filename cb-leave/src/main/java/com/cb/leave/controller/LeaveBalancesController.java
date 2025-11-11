package com.cb.leave.controller;

import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.LeaveBalancesChangeLog;
import com.cb.leave.domain.vo.LeaveBalancesVo;
import com.cb.leave.service.ILeaveBalancesChangeLogService;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.leave.vo.BalanceConfirmVo;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 假期额度Controller
 *
 * @author yangcd
 * @date 2024-09-09
 */
@RestController
@RequestMapping("/leave/balances")
public class LeaveBalancesController extends BaseController
{
    @Autowired
    private ILeaveBalancesService leaveBalancesService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ILeaveBalancesChangeLogService changeLogService;

    /**
     * 查询假期额度列表
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaveBalances leaveBalances)
    {
        startPage();
        List<LeaveBalances> list = leaveBalancesService.selectLeaveBalancesList(leaveBalances);
        return getDataTable(list);
    }

    /**
     * 导出假期额度列表
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:export')")
    @Log(title = "假期额度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LeaveBalances leaveBalances)
    {
        List<LeaveBalances> list = leaveBalancesService.selectLeaveBalancesList(leaveBalances);
        ExcelUtil<LeaveBalances> util = new ExcelUtil<LeaveBalances>(LeaveBalances.class);
        return util.exportExcel(list, "balances");
    }

    /**
     * 获取假期额度详细信息
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(leaveBalancesService.selectLeaveBalancesById(id));
    }

    /**
     * 新增假期额度
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:add')")
    @Log(title = "假期额度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaveBalances leaveBalances) throws Exception {
        return toAjax(leaveBalancesService.insertLeaveBalances(leaveBalances));
    }

    /**
     * 根据假期年度自动生成公休假额度
     * @param leaveBalances
     * @return
     * @throws Exception
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:add')")
    @Log(title = "批量生成假期额度", businessType = BusinessType.INSERT)
    @PostMapping("/generate")
    public AjaxResult generate(@RequestBody LeaveBalances leaveBalances) throws Exception {

        int i = leaveBalancesService.batchGenerateLeaveBalances(leaveBalances);
        return AjaxResult.success(i);
    }

    /**
     * 修改假期额度
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:edit')")
    @Log(title = "假期额度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaveBalances leaveBalances) throws Exception {
        return toAjax(leaveBalancesService.updateLeaveBalances(leaveBalances));
    }

    /**
     * 删除假期额度
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:remove')")
    @Log(title = "假期额度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(leaveBalancesService.deleteLeaveBalancesByIds(ids));
    }

    /**
     * 获取所有用户的应休未休统计信息
     *
     * @return List<LeaveBalanceDTO> 应休未休统计列表leave:balances:unusedList
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:unusedList')")
    @GetMapping("/unused")
    public TableDataInfo getUnusedLeaveStatistics(LeaveBalances leaveBalances) {
//        startPage();
//        List<LeaveBalancesVo> unusedLeaveStatistics = leaveBalancesService.getUnusedLeaveStatistics(leaveBalances);
//        return getDataTable(unusedLeaveStatistics);
        // 根据权限查所有额度
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
//        startPage();
        List<LeaveBalancesVo> list = leaveBalancesService.listLeaveBalanceByUser(leaveBalances, sysUser, true);
        // 添加日志数据
        addLogData(list);
        return getDataTable(list);
    }

    private void addLogData(List<LeaveBalancesVo> list){
        if(null == list || list.isEmpty()){
            return;
        }
        Integer[] ids = list.stream().map(LeaveBalancesVo::getId).toArray(Integer[]::new);
        if(ids.length < 1){
           return;
        }
        List<LeaveBalancesChangeLog> logList = changeLogService.selectListByIds(ids);
        if(logList.isEmpty()){
            return;
        }
        Map<Integer, LeaveBalancesChangeLog> idMap = logList.stream()
                .collect(Collectors.toMap(
                        LeaveBalancesChangeLog::getId,
                        o->o,
                        (v1, v2) -> v1)
                );
        for (LeaveBalancesVo leaveBalancesVo : list) {
            LeaveBalancesChangeLog changeLog = idMap.get(leaveBalancesVo.getId());
            if(null != changeLog){
                leaveBalancesVo.setInitData(changeLog.getInitData());
                leaveBalancesVo.setLastData(changeLog.getLastData());
            }
        }

    }

    /**
     * 导出应休未休假期额度列表
     */
    @PreAuthorize("@ss.hasPermi('leave:balances:export')")
    @Log(title = "假期额度", businessType = BusinessType.EXPORT)
    @GetMapping("/unusedExport")
    public AjaxResult unusedExport(LeaveBalances leaveBalances)
    {
//        List<LeaveBalancesVo> list = leaveBalancesService.getUnusedLeaveStatistics(leaveBalances);
//        ExcelUtil<LeaveBalancesVo> util = new ExcelUtil<LeaveBalancesVo>(LeaveBalancesVo.class);
//        return util.exportExcel(list, "balances");
        //根据权限导出
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        List<LeaveBalancesVo> list = leaveBalancesService.listLeaveBalanceByUser(leaveBalances, sysUser, false);
        list.forEach(item -> {
            Float totalDays = item.getTotalDays();
            Float usedDays = item.getUsedDays();
            if(null == totalDays || null == usedDays || totalDays <= 0){
                item.setUsedRate("0%");
            } else {
                item.setUsedRate(String.format("%.0f", usedDays/totalDays*100) + "%");
            }
        });
        ExcelUtil<LeaveBalancesVo> util = new ExcelUtil<LeaveBalancesVo>(LeaveBalancesVo.class);
        return util.exportExcel(list, "balances");
    }


    /**
     * 公休假应休未休提醒部门领导及个人
     * @return
     */
    @GetMapping("/notifyUnusedPublicHoliday")
    public AjaxResult notifyUnusedPublicHoliday(LeaveBalances leaveBalances){
        Integer leaveYear = leaveBalances.getLeaveYear();
        if(null == leaveYear){
            throw new IllegalArgumentException("请选择年份!");
        }
        leaveBalancesService.notifyUnusedPublicHoliday(leaveBalances);
        return AjaxResult.success(String.format("提醒已发送。"));
    }

    /**
     * 开始休假情况确认
     * @param year
     * @return
     */
    @GetMapping("/openBalanceConfirm/{year}")
    public AjaxResult openBalanceConfirm(@PathVariable("year") Integer year){
        leaveBalancesService.openBalanceConfirm(year);
        return AjaxResult.success("操作成功");
    }

    /**
     * 确认假期额度
     * @param req
     * @return
     */
    @PostMapping("/confirmBalance")
    public AjaxResult confirmBalance(@RequestBody @Valid BalanceConfirmVo.ConfirmReq req){
        Integer[] ids = req.getIds();
        Arrays.stream(ids).filter(Objects::nonNull).findFirst().orElseThrow(() -> new IllegalArgumentException("请选择要确认的记录!"));
        Long leaderId = req.getLeaderId();
        if(null != leaderId){
            SysUser leader = sysUserService.selectUserById(leaderId);
            if(null == leader || !"1".equals(leader.getIsMainLeader())){
                throw new IllegalArgumentException("选择的负责人不存在或无审批权限!");
            }
        }
        leaveBalancesService.confirmBalance(ids, leaderId);
        return AjaxResult.success("操作成功");
    }

    /**
     * 负责人审批假期额度
     * @param req
     * @return
     */
    @PostMapping("/approveBalance")
    public AjaxResult approveBalance(@RequestBody @Valid BalanceConfirmVo.ApproveReq req){
        Integer[] ids = req.getIds();
        Arrays.stream(ids).filter(Objects::nonNull).findFirst().orElseThrow(() -> new IllegalArgumentException("请选择要审核的记录!"));
        leaveBalancesService.approveBalance(req.getIds(), req.getStatus(), req.getApprovalOpinion());
        return AjaxResult.success("操作成功");
    }

    /**
     * 修复是否修改的状态（是否修改的字段添加之前就产生的数据）
     * @return
     */
    @GetMapping("/fixChangeFlag")
    public AjaxResult fixChangeFlag(){
        leaveBalancesService.fixChangeFlag();
        return AjaxResult.success();
    }

    /**
     * 获取请假单申请人的的应休未休信息
     * @param leaveBalances
     * @return
     */
    @GetMapping("/getUserLeaveBalances")
    public AjaxResult  getUsedLeaveDays(LeaveBalances leaveBalances) {
       LeaveBalances leaveBalances1= leaveBalancesService.leaveBalanceByUserId(leaveBalances);
        return AjaxResult.success(leaveBalances1);
    }

}
