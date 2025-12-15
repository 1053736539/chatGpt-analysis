package com.cb.system.controller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.alibaba.fastjson.JSONObject;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.leave.service.ILeaveBalancesService;
import com.cb.system.service.ISysUserService;
import com.cb.system.vo.SysUserInfoChangeApplyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.system.domain.SysUserInfoChangeApply;
import com.cb.system.service.ISysUserInfoChangeApplyService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户信息修改申请Controller
 *
 * @author ruoyi
 * @date 2025-05-15
 */
@Slf4j
@RestController
@RequestMapping("/system/userInfoChangeApply")
public class SysUserInfoChangeApplyController extends BaseController
{
    @Autowired
    private ISysUserInfoChangeApplyService sysUserInfoChangeApplyService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ILeaveBalancesService leaveBalancesService;


    /**
     * 导出该用户待审核的干部信息
     */
    @GetMapping("/exportUserPending")
    public void exportUserPending(HttpServletResponse response) {
        SysUserInfoChangeApply query = new SysUserInfoChangeApply();
        query.setStatus("1");
        List<SysUserInfoChangeApply> list =
                sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(query);

        Path dbPath = null;
        try {
            // 1. 生成 sqlite 数据库文件：gbgl_xxx.db
            dbPath = buildGbglSqlite(list);

            // 2. 设置响应头，导出 zip
            response.setContentType("application/zip");
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=pending_apply_" + System.currentTimeMillis() + ".zip"
            );

            try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
                 InputStream in = Files.newInputStream(dbPath)) {

                // zip 里的文件名就叫 gbgl.db
                zipOutputStream.putNextEntry(new ZipEntry("gbgl.db"));

                byte[] buffer = new byte[8192];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, len);
                }

                zipOutputStream.closeEntry();
                zipOutputStream.finish();
            }
        } catch (Exception e) {
            throw new RuntimeException("导出待审核干部信息失败", e);
        } finally {
            // 3. 删除临时 db 文件
            if (dbPath != null) {
                try {
                    Files.deleteIfExists(dbPath);
                } catch (IOException ignore) {}
            }
        }
    }

    /**
     * 查询用户信息修改申请列表
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        startPage();
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        return getDataTable(list);
    }

    /**
     * 查询用户自己的列表
     * @param sysUserInfoChangeApply
     * @return
     */
    @GetMapping("/listBySelf")
    public TableDataInfo listBySelf(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        String username = SecurityUtils.getUsername();
        sysUserInfoChangeApply.setCreateBy(username);
        startPage();
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        return getDataTable(list);
    }

    /**
     * 导出用户信息修改申请列表
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:export')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        ExcelUtil<SysUserInfoChangeApply> util = new ExcelUtil<SysUserInfoChangeApply>(SysUserInfoChangeApply.class);
        return util.exportExcel(list, "userInfoChangeApply");
    }

    /**
     * 获取用户信息修改申请详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyById(id));
    }

    /**
     * 新增用户信息修改申请
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:add')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        return toAjax(sysUserInfoChangeApplyService.insertSysUserInfoChangeApply(sysUserInfoChangeApply));
    }

    /**
     * 修改用户信息修改申请
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:edit')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        return toAjax(sysUserInfoChangeApplyService.updateSysUserInfoChangeApply(sysUserInfoChangeApply));
    }

    /**
     * 删除用户信息修改申请
     */
//    @PreAuthorize("@ss.hasPermi('system:userInfoChangeApply:remove')")
    @Log(title = "用户信息修改申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids, Boolean mgrFlag)
    {
        if(null != mgrFlag && mgrFlag.booleanValue()){
            sysUserInfoChangeApplyService.deleteSysUserInfoChangeApplyByIds(ids);
        } else {
            sysUserInfoChangeApplyService.deleteSysUserInfoChangeApplyByIdsExcludePass(ids);
        }
        return AjaxResult.success();
    }

    /**
     * 审批操作
     * @param req
     * @return
     */
    @PostMapping("/approval")
    public AjaxResult approval(@RequestBody @Valid SysUserInfoChangeApplyVo.ApprovalReq req){
        //先修改申请记录的状态
        sysUserInfoChangeApplyService.approval(req);
        //审批通过要将数据更新到用户表
        if("2".equals(req.getStatus())){
            List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectByIds(req.getIds());
            list.forEach(item -> {
                String userName = item.getUserName();
                try {
                    String afterData = item.getAfterData();
                    SysUser sysUser = JSONObject.parseObject(afterData, SysUser.class);
                    //更新用户信息
                    sysUserService.updateByUserInfoApply(sysUser);
                    //更新年休假
                    leaveBalancesService.addAnnualLeaveForUser(sysUser);
                }catch (Exception e){
                    log.error("用户信息修改审批通过时更新用户表失败!用户名：{} 原因：{}", userName, e.getMessage());
                }
            });
        }
        return AjaxResult.success();
    }

    /**
     * 重新发起申请
     * @param req
     * @return
     */
    @PostMapping("/reApply")
    public AjaxResult reApply(@RequestBody @Valid SysUserInfoChangeApplyVo.ReApplyReq req){
        sysUserInfoChangeApplyService.reApply(req);
        return AjaxResult.success();
    }

    /**
     * 获取待审批数量
     * @return
     */
    @GetMapping("/countPending")
    public AjaxResult countPending(){
        long count = sysUserInfoChangeApplyService.countPending();
        return AjaxResult.success(count);
    }

    /**
     * 当用户目前有被组织部驳回的修改记录
     * @return
     */
    @GetMapping("/countReject")
    public AjaxResult countReject(){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        long count = sysUserInfoChangeApplyService.countReject(userId);
        return AjaxResult.success(count);
    }

    /**
     * 查询现任职务发生变动的用户信息修改申请列表
     */
    @GetMapping("/listCurrentPositionChanged")
    public TableDataInfo listCurrentPositionChanged(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        startPage();
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        List<SysUserInfoChangeApply> filteredList = list.stream()
                .filter(this::currentPositionChanged)
                .collect(Collectors.toList());
        return getDataTable(filteredList);
    }

    /**
     * 统计职务不匹配待审批数量
     */
    @GetMapping("/countPendingCurrentPositionChanged")
    public AjaxResult countPendingCurrentPositionChanged(SysUserInfoChangeApply sysUserInfoChangeApply)
    {
        sysUserInfoChangeApply.setStatus("1");
        List<SysUserInfoChangeApply> list = sysUserInfoChangeApplyService.selectSysUserInfoChangeApplyList(sysUserInfoChangeApply);
        long count = list.stream()
                .filter(item -> StringUtils.equals("1", item.getStatus()))
                .filter(this::currentPositionChanged)
                .count();
        return AjaxResult.success(count);
    }


    private boolean currentPositionChanged(SysUserInfoChangeApply item)
    {
        String beforePosition = extractCurrentPosition(item.getBeforeData());
        String afterPosition = extractCurrentPosition(item.getAfterData());
        return !Objects.equals(beforePosition, afterPosition);
    }

    private String extractCurrentPosition(String data)
    {
        if (StringUtils.isBlank(data))
        {
            return null;
        }
        try
        {
            SysUser sysUser = JSONObject.parseObject(data, SysUser.class);
            return sysUser == null ? null : sysUser.getCurrentPosition();
        }
        catch (Exception e)
        {
            log.error("解析用户信息时出现异常: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 组装干部信息待审核导出数据表
     * @param list
     * @return
     * @throws Exception
     */
    private Path buildGbglSqlite(List<SysUserInfoChangeApply> list) throws Exception {
        // 创建临时 db 文件：gbgl_xxx.db
        Path dbPath = Files.createTempFile("gbgl_", ".db");
        String url = "jdbc:sqlite:" + dbPath.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // 1. 建表：db_dshgbxx
            String ddl = "CREATE TABLE IF NOT EXISTS db_dshgbxx (" +
                    "id               TEXT PRIMARY KEY," +
                    "user_id          INTEGER," +
                    "user_name        TEXT," +
                    "before_data      TEXT," +
                    "after_data       TEXT," +
                    "status           TEXT," +
                    "approval_opinion TEXT," +
                    "approval_by      TEXT," +
                    "approval_name    TEXT," +
                    "create_by        TEXT," +
                    "create_time      TEXT," +
                    "update_by        TEXT," +
                    "update_time      TEXT" +
                    ")";
            stmt.execute(ddl);

            // 2. 插入数据
            String insertSql = "INSERT INTO db_dshgbxx (" +
                    "id, user_id, user_name, before_data, after_data, status, " +
                    "approval_opinion, approval_by, approval_name, create_by, create_time, update_by, update_time" +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                for (SysUserInfoChangeApply item : list) {
                    int i = 1;

                    ps.setString(i++, item.getId());

                    // user_id
                    if (item.getUserId() == null) {
                        ps.setNull(i++, Types.INTEGER);
                    } else {
                        ps.setLong(i++, item.getUserId());
                    }


                    ps.setString(i++, item.getUserName());

                    ps.setString(i++, item.getBeforeData());
                    ps.setString(i++, item.getAfterData());
                    ps.setString(i++, item.getStatus());
                    ps.setString(i++, item.getApprovalOpinion());
                    ps.setString(i++, item.getApprovalBy());


                    ps.setString(i++, item.getApprovalName());

                    ps.setString(i++, item.getCreateBy());

                    if (item.getCreateTime() != null) {
                        ps.setString(i++, sdf.format(item.getCreateTime()));
                    } else {
                        ps.setNull(i++, Types.VARCHAR);
                    }

                    ps.setString(i++, item.getUpdateBy());

                    if (item.getUpdateTime() != null) {
                        ps.setString(i++, sdf.format(item.getUpdateTime()));
                    } else {
                        ps.setNull(i++, Types.VARCHAR);
                    }

                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
        }

        return dbPath;
    }

}
