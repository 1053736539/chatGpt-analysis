package com.cb.worksituation.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.cb.common.constant.NoticeBizUrlTpl;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.service.ISysUserOutService;
import com.cb.oa.service.OAService;
import com.cb.oa.vo.ClockItem;
import com.cb.oa.vo.ListResp;
import com.cb.system.domain.SysNotice;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysNoticeService;
import com.cb.system.service.ISysUserService;
import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.mapper.WorkSituationMapper;
import com.cb.worksituation.service.IWorkSituationService;
import com.cb.worksituation.vo.WorkSituationImportVo;
import com.cb.worksituation.vo.WorkSituationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 考勤统计（上班情况）Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-15
 */
@Service
public class WorkSituationServiceImpl implements IWorkSituationService
{
    private static final Logger log = LoggerFactory.getLogger(WorkSituationServiceImpl.class);

    @Autowired
    private WorkSituationMapper workSituationMapper;
    @Resource
    private OAService oaService;
    @Resource
    private ISysUserOutService sysUserOutService;
    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询考勤统计（上班情况）
     *
     * @param id 考勤统计（上班情况）ID
     * @return 考勤统计（上班情况）
     */
    @Override
    public WorkSituation selectWorkSituationById(String id)
    {
        return workSituationMapper.selectWorkSituationById(id);
    }

    /**
     * 查询考勤统计（上班情况）列表
     *
     * @param workSituation 考勤统计（上班情况）
     * @return 考勤统计（上班情况）
     */
    @Override
    public List<WorkSituationVo> selectWorkSituationList(WorkSituationVo workSituationVo)
    {
        return workSituationMapper.selectWorkSituationList(workSituationVo);
    }
    @Override
    public List<WorkSituationVo> selectListByPublicity(WorkSituationVo workSituationVo)
    {
        return workSituationMapper.selectListByPublicity(workSituationVo);
    }
    @Override
    public void toPublicity(WorkSituationVo workSituationVo)
    {
        //为空先不管
//        if (StringUtils.isBlank(isExcellent)||StringUtils.isBlank(year))return;
        List<String> months = workSituationVo.getMonths();
        List<String> ms = new ArrayList<>();
        List<String> ys = new ArrayList<>();
        List<String> titleYear = new ArrayList<>();
        for (String month : months) {
            String[] split = month.split("-");
            titleYear.add(month);
            ms.add(split[1]);
            ys.add(split[0]);
        }
        List<String> distinctMs = ms.stream().distinct().collect(Collectors.toList());
        List<String> distinctYs = ys.stream().distinct().collect(Collectors.toList());
        workSituationVo.setSituationMonths(distinctMs);
        workSituationVo.setSituationYears(distinctYs);
        workSituationVo.setIsPublicity("1");
        workSituationMapper.updateToPublicity(workSituationVo);
        SysNotice notice = new SysNotice();
        String title = StringUtils.format("{}考勤结果公示",StringUtils.join(titleYear,","));
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        String bizUrl = NoticeBizUrlTpl.WORK_SITUATION.build(StringUtils.join(titleYear,","));
        notice.setBizUrl(bizUrl);
        noticeService.insertNotice(notice);
    }
    @Override
    public List<WorkSituationVo> selectWorkSituationListLeft(WorkSituationVo workSituationVo)
    {
        return workSituationMapper.selectWorkSituationListLeft(workSituationVo);
    }
    @Override
    public Integer synchronousClock(String year, String month){
        ListResp<ClockItem> clockItemListResp = oaService.listClock(year + "-" + month);
        //查询所有映射的用户
        SysUserOut sysUserOut = new SysUserOut();
        List<SysUserOut> sysUserOuts = sysUserOutService.selectSysUserOutList(sysUserOut);
        Map<String, Long> sysUserOutMap = sysUserOuts.stream().filter(e -> null != e.getUserId()).collect(Collectors.toMap(SysUserOut::getId, SysUserOut::getUserId));
        //
        Integer count=0;
        List<String> deleteIds = new ArrayList<>();
        List<WorkSituation> saveList = new ArrayList<>();
        if (null!=clockItemListResp){
            List<ClockItem> data = clockItemListResp.getData();
            if (null!=data){
                //先查询所有当前同步的月份在本系统内的数据
                WorkSituationVo workSituationVo = new WorkSituationVo();
                workSituationVo.setSituationYear(year);
                workSituationVo.setSituationMonth(month);
                List<WorkSituationVo> workSituationVos = selectWorkSituationListLeft(workSituationVo);
                //当前系统已有的数据
                Map<String, WorkSituationVo> collect = workSituationVos.stream().collect(Collectors.toMap(WorkSituationVo::getOutUserId, o -> o));
                for (ClockItem datum : data) {
                    //有的就删除覆盖
                    WorkSituationVo workSituationVo1 = collect.get(datum.getUserId());

                    if (null != workSituationVo1){
                        //如果是已公示，那就跳过
                        if ("1".equals(workSituationVo1.getIsPublicity())){
                            continue;
                        }
                        deleteIds.add(workSituationVo1.getId());
                    }
                    WorkSituation workSituation = new WorkSituation();
                    workSituation.setWorkDay(datum.getWorkNum());//工作日
                    workSituation.setAttendanceDay(datum.getOutForWork());//出勤（天）
                    workSituation.setLeaveDay((float)datum.getLeaveDate());//请假（天）
                    workSituation.setNormal(datum.getNormal());//正常（天）
                    workSituation.setCardReplacement(datum.getMakeWorkCount());//补卡（天）
                    workSituation.setVacancyDay(datum.getNotWorkCount());//缺卡（天）
                    workSituation.setArriveLate(datum.getLateCount());//迟到（次）
                    workSituation.setLeaveEarly(datum.getLeaveCount());//早退（次）
                    workSituation.setOutUserId(datum.getUserId());//oa用户id
                    workSituation.setSituationYear(year);//年
                    workSituation.setSituationMonth(month);//月
                    workSituation.setUserId(sysUserOutMap.get(datum.getUserId()));//本系统用户id
                    workSituation.setSort(datum.getSort());//排序
                    saveList.add(workSituation);
                }
            }
        }
        //删除
        if(!deleteIds.isEmpty()){
            deleteWorkSituationByIds(deleteIds.toArray(new String[deleteIds.size()]));
        }
        if (!saveList.isEmpty()){
            insertWorkSituationBatch(saveList);
            count=saveList.size();
        }
        //本次同步0条
        return count;
    }
    /**
     * 查询饼图统计
     * @param workSituationVo
     * @return
     */
    @Override
    public WorkSituation selectPieVo(WorkSituationVo workSituationVo)
    {
        return workSituationMapper.selectPieVo(workSituationVo);
    }

    /**
     * 新增考勤统计（上班情况）
     *
     * @param workSituation 考勤统计（上班情况）
     * @return 结果
     */
    @Override
    public int insertWorkSituation(WorkSituation workSituation)
    {
        workSituation.setId(UUID.randomUUID().toString());
        workSituation.setCreateTime(DateUtils.getNowDate());
        return workSituationMapper.insertWorkSituation(workSituation);
    }
    @Override
    public int addWorkSituation(WorkSituation workSituation)
    {
        workSituation.setId(UUID.randomUUID().toString());
        workSituation.setCreateTime(DateUtils.getNowDate());
        return workSituationMapper.insertWorkSituation(workSituation);
    }
    @Override
    public int insertWorkSituationBatch(List<WorkSituation> workSituations)
    {
        for (WorkSituation workSituation : workSituations) {
            workSituation.setId(UUID.randomUUID().toString());
            workSituation.setCreateTime(DateUtils.getNowDate());
        }
        return workSituationMapper.insertWorkSituationBatch(workSituations);
    }

    /**
     * 修改考勤统计（上班情况）
     *
     * @param workSituation 考勤统计（上班情况）
     * @return 结果
     */
    @Override
    public int updateWorkSituation(WorkSituation workSituation)
    {
        workSituation.setUpdateTime(DateUtils.getNowDate());
        return workSituationMapper.updateWorkSituation(workSituation);
    }

    /**
     * 批量删除考勤统计（上班情况）
     *
     * @param ids 需要删除的考勤统计（上班情况）ID
     * @return 结果
     */
    @Override
    public int deleteWorkSituationByIds(String[] ids)
    {
        return workSituationMapper.deleteWorkSituationByIds(ids);
    }

    /**
     * 删除考勤统计（上班情况）信息
     *
     * @param id 考勤统计（上班情况）ID
     * @return 结果
     */
    @Override
    public int deleteWorkSituationById(String id)
    {
        return workSituationMapper.deleteWorkSituationById(id);
    }

    @Override
    public WorkSituation selectAssessWorkSituation(Long userId, String year, Integer start, Integer end, String cycle) {
        if (userId == null || StringUtils.isBlank(year) || StringUtils.isBlank(cycle)) {
            throw new IllegalArgumentException("参数异常");
        } else {
            if ("1".equals(cycle) && start == null) {
                throw new IllegalArgumentException("参数异常");
            } else if ("2".equals(cycle) && (start == null || end == null)) {
                throw new IllegalArgumentException("参数异常");
            }
        }
        return workSituationMapper.selectAssessWorkSituation(userId, year, start, end, cycle);
    }

    @Override
    public String importWorkSituation(List<WorkSituationImportVo> dataList, boolean update, String month, String operName) {
        String y, m;
        String msg = "";
        if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
            String[] temp = month.split("-");
            y = temp[0];
            m = temp[1];
        } else {
            Calendar cal = Calendar.getInstance();
            y = cal.get(Calendar.YEAR) + "";
            m = (cal.get(Calendar.MONTH) + 1) + "";
        }
        String deptName, userName;
        SysUser sysUser;
        List<SysUser> userList;
        SysDept dept = null;
        List<SysDept> deptList;
        WorkSituation situation;
        WorkSituationVo situationVo;
        List<WorkSituationVo> situationList;
        Date date = new Date();
        int success = 0, fail = 0, add = 0, edit = 0;
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        copyOptions.setIgnoreCase(true);
        copyOptions.setIgnoreError(true);
        for(WorkSituationImportVo vo : dataList) {
            try {
                if(StringUtils.isNotEmpty(vo.getDeptName())) {
                    deptName = vo.getDeptName();
                    dept = new SysDept();
                    dept.setDeptName(deptName);
                    deptList = sysDeptService.selectDeptList(dept);
                    if(deptList.size() > 0) {
                        dept = deptList.get(0);
                    } else {
                        dept = null;
                    }
                }
                userName = vo.getName();
                sysUser = new SysUser();
                sysUser.setName(userName);
                userList = sysUserService.selectUserListByName(sysUser);
                if(userList.size() > 0) {
                    if(userList.size() > 1) {
                        sysUser = null;
                        for(SysUser user : userList) {
                            if(user.getDeptId() != null && dept != null
                                    && dept.getDeptId().longValue() == user.getDeptId().longValue()) {
                                sysUser = user;
                                break;
                            }
                        }
                        if(sysUser == null) {
                            msg += "\n第"+vo.getSort() + "行导入失败，姓名【"+userName+"】重复;";
                            fail++;
                            continue;
                        }
                    } else {
                        sysUser = userList.get(0);
                    }
                    situation = new WorkSituation();

                    situationVo = new WorkSituationVo();
                    situationVo.setSituationYear(y);
                    situationVo.setSituationMonth(m);
                    situationVo.setUserId(sysUser.getUserId());
                    situationList = selectWorkSituationList(situationVo);
                    if(situationList.size() > 0) {
                        situationVo = situationList.get(0);
                        BeanUtil.copyProperties(situationVo, situation, copyOptions);
                        situation.setUpdateBy(operName);
                        situation.setUpdateTime(date);
                    } else {
                        situation.setSituationYear(y);
                        situation.setSituationMonth(m);
                        situation.setUserId(sysUser.getUserId());
                        situation.setCreateBy(operName);
                        situation.setCreateTime(date);
                    }
                    BeanUtil.copyProperties(vo, situation, copyOptions);
                    if(StringUtils.isEmpty(situation.getId())) {
                        insertWorkSituation(situation);
                        add++;
                    } else {
                        updateWorkSituation(situation);
                        edit++;
                    }
                    success++;
                } else {
                    msg += "\n第"+vo.getSort() + "行导入失败，姓名【"+userName+"】未找到对应的系统用户;";
                    log.info("未找到系统用户，姓名=" + userName);
                    fail++;
                }
            }catch (Exception e) {
                log.error("导入错误", e);
                msg += "\n第"+vo.getSort() + "行导入失败，姓名【"+vo.getName()+"】;";
                fail++;
            }
        }
        msg = "考勤记录共" + dataList.size() + "条，导入失败" + fail  + "条，导入成功" + success+ "条。（新增"
                + add + "条，更新" + edit + "条。）" + msg;
        return msg;
    }
}
