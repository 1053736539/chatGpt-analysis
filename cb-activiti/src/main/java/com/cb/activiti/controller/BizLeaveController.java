package com.cb.activiti.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.domain.BizLeaveVo;
import com.cb.activiti.enums.UserLevelEnum;
import com.cb.activiti.service.IBizLeaveService;
import com.cb.activiti.service.IProcessService;
import com.cb.activiti.util.LeaveUtil;
import com.cb.common.annotation.Log;
import com.cb.common.annotation.RepeatSubmit;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.domain.vo.ImportLeaveVo;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.ServletUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.framework.web.service.TokenService;
import com.cb.leave.domain.LeaveBalances;
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.domain.vo.LeaveCensusVo;
import com.cb.leave.mapper.LeaveTypesMapper;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.leave.service.ILeaveTypesService;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;
import com.cb.system.util.ExcelUtils;
import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.service.IWorkSituationService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.util.resources.cldr.gv.LocaleNames_gv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 请假Controller
 *
 * @author 一只闲鹿
 */
@RestController
@RequestMapping("/leave/leave")
@AllArgsConstructor
public class BizLeaveController extends BaseController
{
    private IBizLeaveService bizLeaveService;
    private IProcessService processService;
    private ILeaveBalancesService leaveBalancesService;
    private IWorkSituationService workSituationService;
    private LeaveTypesMapper leaveTypesMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ILeaveTypesService leaveTypesService;
    private  ISysUserService userService;
    private ISysDeptService deptService;

    /**
     * 查询请假列表
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizLeave bizLeave)
    {
        startPage();
        List<BizLeave> list = bizLeaveService.selectBizLeaveList(bizLeave);
        return getDataTable(list);
    }

    /**
     * 查询请假列表 区分个人和组织部管理员
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:list')")
    @GetMapping("/leaveList")
    public TableDataInfo leaveList(BizLeave bizLeave)
    {
        startPage();
        List<BizLeave> list = bizLeaveService.selectAllBizLeaveList(bizLeave);
        return getDataTable(list);
    }


    /**
     * 导出请假列表
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:export')")
    @Log(title = "请假数据导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizLeave bizLeave)
    {
        List<BizLeave> list = bizLeaveService.selectHistoryBizLeaveList(bizLeave);
        ExcelUtil<BizLeave> util = new ExcelUtil<BizLeave>(BizLeave.class);
        return util.exportExcel(list, "leave");
    }

    /**
     * 获取请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizLeaveService.selectBizLeaveById(id));
    }

    /**
     * 新增请假
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:add')")
    @Log(title = "请假", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody BizLeave bizLeave)
    {
        // 填写表单时检查请假是否可请
        LeaveUtil.checkLeaveLimit(bizLeave);
        int rows = bizLeaveService.insertBizLeave(bizLeave);
        if (rows > 0)
        {
            return AjaxResult.success("创建操作成功", bizLeave.getId());
        }
        return AjaxResult.error();
    }

    /**
     * 修改请假
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:edit')")
    @Log(title = "请假", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizLeave bizLeave)
    {

        int rows = bizLeaveService.updateBizLeave(bizLeave);
        if (rows > 0)
        {
            return AjaxResult.success("修改操作成功",bizLeave.getId());
        }
        return AjaxResult.error();
    }

    /**
     * 删除请假
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:remove')")
    @Log(title = "请假", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizLeaveService.deleteBizLeaveByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "请假业务", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply")
    @RepeatSubmit
    @ResponseBody
    public AjaxResult submitApply(@RequestBody BizLeaveVo bizLeaveVo ) {
        BizLeave bizLeave = bizLeaveService.selectBizLeaveById(bizLeaveVo.getId());
        // 检查请假是否可请
        LeaveUtil.checkLeaveLimit(bizLeave);
        //设置createUserLevel  因为部门负责人需要下拉选，改为填写申请单的时候设置createUserLevel
//        LeaveUtil.setCreateUserLevel(bizLeave);
//        if(UserLevelEnum.OTHER.getCode().equals(bizLeave.getCreateUserLevel())){
//            bizLeave.setNextUserName(bizLeaveVo.getNextUserName());
//        }
        bizLeave.setNextUserName(bizLeaveVo.getNextUserName());
        //提交申请后流程审批状态改为审核中
        bizLeave.setStatus("3");
        try {
            processService.submitApply(bizLeave, "leave");
            bizLeaveService.updateBizLeave(bizLeave);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
//            return error("提交申请出错：" + e.getMessage());
        }
        return success();
    }
    /**
     * 查询病假列表  审批通过的请假记录当类型为病假或者是否生病住院请假满足一个以上就在病假查看列表中可见
     */
    @PreAuthorize("@ss.hasPermi('leave:leave:list')")
    @GetMapping("/listByTypeOrHospital")
    public TableDataInfo listByTypeOrHospital(BizLeave bizLeave)
    {
        startPage();
        List<BizLeave> list = bizLeaveService.listByTypeOrHospital(bizLeave);
        return getDataTable(list);
    }

    /**
     * 下载请假单导入excel模板
     * @param request
     * @param response
     */
    @GetMapping("/downloadExcelTpl")
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response){
        try{
            ClassPathResource classPathResource = new ClassPathResource("static/excel/休假信息导入模板.xls");
            InputStream is = classPathResource.getInputStream();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = "休假信息导入模板.xls";
            response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(fileName,"utf-8") +"\"");
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 请假单信息导入（Excel 使用客户提供的模板导入）
     */
    static final String FLAGS = "flags";
    static final String CONTENT = "content";
    @PostMapping(value = "/leaveImport", consumes = "multipart/form-data")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Map<String, Object> leaveListImport(@PathParam("file") MultipartFile file, HttpServletRequest request) throws IOException, Throwable {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer status = 1;
        String content = "导入成功!";
        InputStream fileIn = file.getInputStream();
        // 根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = ExcelUtils.getWorkbook(file);
        //Workbook wb0 = new HSSFWorkbook(fileIn);
        // 获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        List<BizLeave> bizLeaves = new ArrayList<BizLeave>();
        int i = 0;
        List<String> errors = new ArrayList<>();
        int rowIndex = 0;
        for (Row row : sht0) {
            ++rowIndex;
            if (rowIndex > 4) {
                String str = ExcelUtils.getCellValue(row, 1);
                BizLeave bizLeave = new BizLeave();
                if (StringUtils.isNotNull(str) && StringUtils.isNotEmpty(str)) {
                    //用户信息获取
                    bizLeave.setApplyUserName(ExcelUtils.getCellValue(row, 1));// 姓名
                    String userName = PinyinUtil.getPinyin(ExcelUtils.getCellValue(row, 1)).replaceAll(" ", "");
                    bizLeave.setDeptName(ExcelUtils.getCellValue(row, 2));// 部门
                    String deptName = ExcelUtils.getCellValue(row, 2);
                    String nickName = ExcelUtils.getCellValue(row, 1);
                    List<SysUser> sysUserList = userService.selectUserByNickName(nickName);
                    if (sysUserList.size() > 1) {
                        //姓名重复，查询部门
                        SysDept dept = deptService.selectDeptByDeptName(deptName);
                        if (StringUtils.isNull(dept)) {
                            status = 0;
                            content = "导入失败！第" + rowIndex + "行，部门【" + deptName + "】不存在或者有误，请核实！";
                            break;
                        } else {
                            List<SysUser> UserList = userService.selectUserByNickNameAndDeptId(nickName, dept.getDeptId());
                            if (UserList.size() == 1) {
                                //同一个部门不存在重复的人员
                                for (SysUser sysUser : UserList) {
                                    bizLeave.setApplyUserId(sysUser.getUserName());//用户拼音
                                    bizLeave.setDeptId(sysUser.getDeptId());//部门ID
                                    bizLeave.setDeptName(sysUser.getDeptName());
                                    bizLeave.setWorkPost(sysUser.getCurrentPosition());//职务
                                }
                            } else {
                                //同一个部门存在重复的人员
                                status = 0;
                                content = "导入失败！第" + rowIndex + "行，部门【" + deptName + "】中，姓名【" + bizLeave.getApplyUserName() + "】重复，请核实！";
                                break;
                            }
                        }
                    } else if (sysUserList.size() == 1) {
                        //姓名不重复
                        for (SysUser sysUser : sysUserList) {
                            bizLeave.setApplyUserId(sysUser.getUserName());//用户拼音
                            bizLeave.setDeptId(sysUser.getDeptId());//部门ID
                            bizLeave.setDeptName(sysUser.getDeptName());
                            bizLeave.setWorkPost(sysUser.getCurrentPosition());//职务
                        }
                    } else {
                        status = 0;
                        content = "导入失败！第" + rowIndex + "行，姓名【" + bizLeave.getApplyUserName() + "】有误，请核实！";
                        break;
                    }
                LeaveTypes leaveTypes = leaveTypesService.selectLeaveIdByLeavName(ExcelUtils.getCellValue(row, 3));//根据请假类型查询请假ID
                if (StringUtils.isNotNull(leaveTypes)) {
                    bizLeave.setType(Long.parseLong(leaveTypes.getId().toString()));//请假类型
                }
                if (StringUtils.isNotNull(ExcelUtils.getCellValue(row, 4)) && StringUtils.isNotEmpty(ExcelUtils.getCellValue(row, 4))) {
                    bizLeave.setLeaveNum(Float.valueOf(ExcelUtils.getCellValue(row, 4)));//请假天数 会有半天的情况
                }
                if (StringUtils.isNotNull(ExcelUtils.getCellValue(row, 5)) && StringUtils.isNotEmpty(ExcelUtils.getCellValue(row, 5))) {
                    bizLeave.setLeaveStartTime(DateUtils.parseDate(ExcelUtils.getCellValue(row, 5)));// 休假开始时间
                    bizLeave.setApplyTime(DateUtils.parseDate(ExcelUtils.getCellValue(row, 5)));//申请时间
                }
                if (StringUtils.isNotNull(ExcelUtils.getCellValue(row, 6)) && StringUtils.isNotEmpty(ExcelUtils.getCellValue(row, 6))) {
                    bizLeave.setLeaveEndTime(DateUtils.parseDate(ExcelUtils.getCellValue(row, 6)));// 休假结束时间
                    bizLeave.setDestructionDate(DateUtils.parseDate(ExcelUtils.getCellValue(row, 6)));//销假日期
                }
                if (StringUtils.isNotNull(ExcelUtils.getCellValue(row, 7)) && StringUtils.isNotEmpty(ExcelUtils.getCellValue(row, 7))) {
                    bizLeave.setHolidayYear(ExcelUtils.getCellValue(row, 7));//休假年度
                }
                if (checkBizLeave(bizLeave, rowIndex, errors)) {
                    bizLeave.setStatus("1");
                    bizLeave.setTaskName("已结束");
                    bizLeave.setProcessKey("leave");
                    bizLeave.setCreateBy(SecurityUtils.getUsername());
                    bizLeave.setCreateTime(DateUtils.getNowDate());
                    bizLeaves.add(bizLeave);
                } else {
                    status = 0;
                    content = String.join(";", errors);
                    break;
                }
            } else {
                break;
            }
        }
    }
       if (status != 0) {
           if(bizLeaves.size()>0){
               for (BizLeave bizLeave : bizLeaves) {
                   bizLeaveService.insertImportBizLeave(bizLeave);
                   try {
                       SysUser sysUser = userService.selectUserByUserName(bizLeave.getApplyUserId());
                       //只有年休假需要修改额度
                       Long leaveTypeId = bizLeave.getType();
                       if(null != leaveTypeId && leaveTypeId.equals(1L)){
                           //判断假期年度是否是当前年
                           String currYear = DateUtil.format(new Date(), "yyyy");
                           if(currYear.equals(bizLeave.getHolidayYear())){
                               //当前年
                               leaveBalancesService.addAnnualLeaveForUser(sysUser);
                           }else {
                               //非当前年
                               leaveBalancesService.addUsedLeaveForUser(sysUser,Integer.valueOf(bizLeave.getHolidayYear()));
                           }
                           leaveBalancesService.updateLeaveBalancesAfterApproval(sysUser.getUserId(),
                                   bizLeave.getType(),bizLeave.getLeaveNum(),Integer.valueOf(bizLeave.getHolidayYear()));
                       }
                       WorkSituation workSituation = new WorkSituation();
                       workSituation.setUserId(sysUser.getUserId());
                       workSituation.setLeaveTypeId(bizLeave.getType());
                       workSituation.setLeaveDay(bizLeave.getLeaveNum());//请假天数
                       workSituation.setSituationYear(bizLeave.getHolidayYear());//哪一年请假
                       // 对 totalTime 进行 null 检查
                       if (bizLeave.getTotalTime() != null) {
                           workSituation.setTotalTime(bizLeave.getTotalTime());
                       }
                       workSituation.setRealityStartTime(bizLeave.getLeaveStartTime());
                       workSituation.setRealityEndTime(bizLeave.getLeaveEndTime());
                       //  将请假记录新增到考勤表中
                       workSituationService.addWorkSituation(workSituation);

                   } catch (Exception e) {
                       e.printStackTrace();
                   }

               }
               content="成功导入"+bizLeaves.size()+"条数据";
           }else{
               status = 0;
               content = "不能导入空数据";
           }
        }
        map.put(FLAGS, status);
        map.put(CONTENT, content);
        return map;
    }

    boolean checkBizLeave(BizLeave bizLeave, int row, List<String> errors) {
        if ("".equals(bizLeave.getApplyUserName())||bizLeave.getApplyUserName()==null) {
            errors.add("导入失败！第" + row + "行，姓名不能为空！");
        }
//        if ("".equals(bizLeave.getDeptName())||bizLeave.getDeptName()==null) {
//            errors.add("导入失败！第" + row + "行，部门不能为空！");
//        }
        if ("".equals(bizLeave.getType())||bizLeave.getType()==null) {
            errors.add("导入失败！第" + row + "行，请假类型不能为空！");
        }
        if ("".equals(bizLeave.getLeaveNum())||bizLeave.getLeaveNum()==null) {
            errors.add("导入失败！第" + row + "行，请假天数不能为空！");
        }
        if ("".equals(bizLeave.getLeaveStartTime())||bizLeave.getLeaveStartTime()==null) {
            errors.add("导入失败！第" + row + "行，开始日期不能为空！");
        }
        if ("".equals(bizLeave.getLeaveEndTime())||bizLeave.getLeaveEndTime()==null) {
            errors.add("导入失败！第" + row + "行，结束日期不能为空！");
        }
        return errors.size() == 0;
    }

    /**
     * 下载请假导入模板
     * @return
     */
    @GetMapping("/importLeaveTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<ImportLeaveVo> util = new ExcelUtil<ImportLeaveVo>(ImportLeaveVo.class);
        return util.importTemplateExcel("请假数据");
    }

    @Log(title = "请假数据导入", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('system:leave:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<ImportLeaveVo> util = new ExcelUtil<ImportLeaveVo>(ImportLeaveVo.class);
        List<ImportLeaveVo> leaveList = util.importExcel(file.getInputStream()).stream()
                .filter(u-> StringUtils.isNotBlank(u.getApplyUserName()))
                .collect(Collectors.toList());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = bizLeaveService.importLeaveList(leaveList, operName);
        return AjaxResult.success();
    }



    @PostMapping(value = "/getMainLeader")
    public TableDataInfo getMainLeader(@RequestBody BizLeave bizLeave) {
        startPage();
        List<SysUser> functionUsers = new ArrayList<SysUser>();
        if (StringUtils.isNull(bizLeave.getInstanceId())) {
            // 请假流程发起前
            if (UserLevelEnum.OTHER.getCode().equals(bizLeave.getCreateUserLevel())) {
                //查询获取部门负责人
                SysUser sysUser= new SysUser();
                List<SysUser> users = userService.selectMainLeaderList(sysUser);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }
            if (UserLevelEnum.LEVEL_3.getCode().equals(bizLeave.getCreateUserLevel())||UserLevelEnum.LEVEL_4.getCode().equals(bizLeave.getCreateUserLevel())) {
                //查询组织部审批人员
                String rowKey = "zzb_leave_role";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else if(UserLevelEnum.LEVEL_5.getCode().equals(bizLeave.getCreateUserLevel())){
                //查询巡查组组长
                String rowKey = "xcz_group_role";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else if(UserLevelEnum.LEVEL_1.getCode().equals(bizLeave.getCreateUserLevel())){
                //查询副书记
                String rowKey = "fushuji";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else if(UserLevelEnum.LEVEL_2.getCode().equals(bizLeave.getCreateUserLevel())){
                //查询书记
                String rowKey = "shuji";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }

        }else {
            //请假流程发起后，审核中
            if("toChangwei".equals(bizLeave.getActivityId())){
                //查询市纪委常委
                String rowKey = "changwei";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else if("toShuji".equals(bizLeave.getActivityId())||"toShuji2".equals(bizLeave.getActivityId())){
                //查询书记
                String rowKey = "shuji";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else  if("toFushuji".equals(bizLeave.getActivityId())||"toFushuji2".equals(bizLeave.getActivityId())){
                //查询副书记
                String rowKey = "fushuji";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else if("toOrganizationDeptOne".equals(bizLeave.getActivityId())){
                String rowKey = "zzb_leave_role";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }else if("toXczZuZhang".equals(bizLeave.getActivityId())){
                //查询巡查组组长
                String rowKey = "xcz_group_role";
                List<SysUser> users = userService.listUserByRoleKey(rowKey);
                for (SysUser user : users) {
                    user.setNickName(user.getNickName());
                }
                functionUsers.addAll(users);
            }

        }
        return getDataTable(functionUsers);
    }


}



