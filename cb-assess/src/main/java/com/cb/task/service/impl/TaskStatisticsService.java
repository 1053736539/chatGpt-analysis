package com.cb.task.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysDictTypeService;
import com.cb.system.service.ISysUserService;
import com.cb.task.domain.BizTaskDocType;
import com.cb.task.mapper.TaskStatisticsMapper;
import com.cb.task.service.IBizTaskDocTypeService;
import com.cb.task.service.ITaskStatisticsService;
import com.cb.task.vo.TaskStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/20 9:31
 */
@Service
public class TaskStatisticsService implements ITaskStatisticsService {

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TaskStatisticsMapper statisticsMapper;

    @Autowired
    private IBizTaskDocTypeService docTypeService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @Override
    public TaskStatisticsVo.HandleNumCountResp handleNumCount(TaskStatisticsVo.HandleNumCountReq req) {
        TaskStatisticsVo.HandleNumCountResp result = new TaskStatisticsVo.HandleNumCountResp();
        if(null == req.getDeptId()){
            return handleNumCountByDept(req);
        } else {
            return handleNumCountByUser(req);
        }
    }

    private TaskStatisticsVo.HandleNumCountResp handleNumCountByDept(TaskStatisticsVo.HandleNumCountReq req){
        SysDept query = new SysDept();
        query.setParentId(100L);
        query.setStatus("0");
        query.setDelFlag("0");
        List<SysDept> deptList = deptService.selectDeptList(query).stream()
                .filter(item->100L != item.getDeptId())
                //移除局领导部门
                .filter(item->!("局领导".equals(item.getDeptName()) || "系统管理员".equals(item.getDeptName())))
                .collect(Collectors.toList());
        List<Long> deptIds = deptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());

        Map<Long, TaskStatisticsVo.HandleCountItem> deptExecutingMap = statisticsMapper.countDept("1", deptIds,req.getBeginTime(),req.getEndTime())
                .stream().collect(Collectors.toMap(TaskStatisticsVo.HandleCountItem::getDeptId, item -> item, (value1, value2) -> value1));

        Map<Long, TaskStatisticsVo.HandleCountItem> deptFinishMap = statisticsMapper.countDept("2", deptIds,req.getBeginTime(),req.getEndTime())
                .stream().collect(Collectors.toMap(TaskStatisticsVo.HandleCountItem::getDeptId, item -> item, (value1, value2) -> value1));

        List<TaskStatisticsVo.HandleCountItem> executingList = new LinkedList<>();
        List<TaskStatisticsVo.HandleCountItem> finishList = new LinkedList<>();

        for (int i = 0; i < deptList.size(); i++) {
            SysDept item = deptList.get(i);
            Long depId = item.getDeptId();
            //执行中
            TaskStatisticsVo.HandleCountItem executingItem = deptExecutingMap.get(depId);
            if (null == executingItem) {
                executingItem = new TaskStatisticsVo.HandleCountItem();
                executingItem.setDeptId(depId);
                executingItem.setLabel(item.getDeptAbbreviation());
                executingItem.setNum(0L);
            }
            executingList.add(executingItem);

            //已完成
            TaskStatisticsVo.HandleCountItem finishItem = deptFinishMap.get(depId);
            if(null == finishItem){
                finishItem = new TaskStatisticsVo.HandleCountItem();
                finishItem.setDeptId(depId);
                finishItem.setLabel(item.getDeptAbbreviation());
                finishItem.setNum(0L);
            }
            finishList.add(finishItem);
        }

        executingList = executingList.stream().sorted(Comparator.comparing(TaskStatisticsVo.HandleCountItem::getNum).reversed()).collect(Collectors.toList());
        finishList = finishList.stream().sorted(Comparator.comparing(TaskStatisticsVo.HandleCountItem::getNum).reversed()).collect(Collectors.toList());

        TaskStatisticsVo.HandleNumCountResp result = new TaskStatisticsVo.HandleNumCountResp();

        result.setExecutingList(executingList);
        result.setFinishList(finishList);
        result.setExecutingTotal(executingList.stream().mapToLong(TaskStatisticsVo.HandleCountItem::getNum).sum());
        result.setFinishTotal(finishList.stream().mapToLong(TaskStatisticsVo.HandleCountItem::getNum).sum());
        return result;
    }

    private TaskStatisticsVo.HandleNumCountResp handleNumCountByUser(TaskStatisticsVo.HandleNumCountReq req){
        //TODO 暂未考虑一人多部门
        SysUser query = new SysUser();
        query.setDeptId(req.getDeptId());
        query.setStatus("0");
        query.setDelFlag("0");
        List<SysUser> userList = userService.selectUserList(query);
        if(userList.isEmpty()){
            TaskStatisticsVo.HandleNumCountResp result = new TaskStatisticsVo.HandleNumCountResp();
            result.setExecutingList(Collections.emptyList());
            result.setFinishList(Collections.emptyList());
            result.setExecutingTotal(0L);
            result.setFinishTotal(0L);
            return result;
        }
        List<String> userNames = userList.stream().map(SysUser::getUserName).collect(Collectors.toList());

        Map<String, TaskStatisticsVo.HandleCountItem> deptExecutingMap = statisticsMapper.countUser("1", userNames,req.getBeginTime(),req.getEndTime())
                .stream().collect(Collectors.toMap(TaskStatisticsVo.HandleCountItem::getUserName, item -> item, (value1, value2) -> value1));

        Map<String, TaskStatisticsVo.HandleCountItem> deptFinishMap = statisticsMapper.countUser("2", userNames,req.getBeginTime(),req.getEndTime())
                .stream().collect(Collectors.toMap(TaskStatisticsVo.HandleCountItem::getUserName, item -> item, (value1, value2) -> value1));

        List<TaskStatisticsVo.HandleCountItem> executingList = new LinkedList<>();
        List<TaskStatisticsVo.HandleCountItem> finishList = new LinkedList<>();

        for (int i = 0; i < userList.size(); i++) {
            SysUser item = userList.get(i);
            String userName = item.getUserName();
            //执行中
            TaskStatisticsVo.HandleCountItem executingItem = deptExecutingMap.get(userName);
            if (null == executingItem) {
                executingItem = new TaskStatisticsVo.HandleCountItem();
                executingItem.setUserName(userName);
                executingItem.setLabel(item.getName());
                executingItem.setNum(0L);
                executingItem.setUserId(item.getUserId());
            }
            executingList.add(executingItem);

            //已完成
            TaskStatisticsVo.HandleCountItem finishItem = deptFinishMap.get(userName);
            if(null == finishItem){
                finishItem = new TaskStatisticsVo.HandleCountItem();
                executingItem.setUserName(userName);
                finishItem.setLabel(item.getName());
                finishItem.setNum(0L);
                executingItem.setUserId(item.getUserId());
            }
            finishList.add(finishItem);
        }

        executingList = executingList.stream().sorted(Comparator.comparing(TaskStatisticsVo.HandleCountItem::getNum).reversed()).collect(Collectors.toList());
        finishList = finishList.stream().sorted(Comparator.comparing(TaskStatisticsVo.HandleCountItem::getNum).reversed()).collect(Collectors.toList());

        TaskStatisticsVo.HandleNumCountResp result = new TaskStatisticsVo.HandleNumCountResp();

        result.setExecutingList(executingList);
        result.setFinishList(finishList);
        result.setExecutingTotal(executingList.stream().mapToLong(TaskStatisticsVo.HandleCountItem::getNum).sum());
        result.setFinishTotal(finishList.stream().mapToLong(TaskStatisticsVo.HandleCountItem::getNum).sum());
        return result;
    }

    @Override
    public TaskStatisticsVo.TaskCountResp taskCount(TaskStatisticsVo.TaskCountReq req) {
        //来源 1=OA办文事项,2=OA通知公告,3=系统自建
//        List<String> causationList = Arrays.asList("1", "2", "3");
        Map<String, String> causationDictMap = dictTypeService.selectDictDataMapByType("task_source");
        //难度系数 1-简单 2-一般 3-难 4-较难 5-高难
//        List<String> diffcultList = Arrays.asList("1", "2", "3", "4", "5");
        Map<String, String> difficultDictMap = dictTypeService.selectDictDataMapByType("task_difficult");
        List<BizTaskDocType> docTypeList;//分类列表
        if(null == req.getDeptId()){
            BizTaskDocType query = new BizTaskDocType();
            query.setDelFlag("0");
            docTypeList = docTypeService.selectBizTaskDocTypeList(query);
        } else {
            docTypeList = docTypeService.listMy(new BizTaskDocType(), req.getDeptId());
        }
        Map<String,String> docTypeDictMap = docTypeList.stream().collect(Collectors.toMap(BizTaskDocType::getCode,BizTaskDocType::getLabel,(value1,value2)->value1));
        // causation-来源 difficult-难度系数 docType-公文分类
        Map<String, TaskStatisticsVo.TaskCountItem> causationMap = statisticsMapper.groupTaskBy(req.getDeptId(), req.getBeginTime(), req.getEndTime(), "causation")
                .stream().collect(Collectors.toMap(TaskStatisticsVo.TaskCountItem::getGroupId, item -> item, (value1, value2) -> value1));
        Map<String, TaskStatisticsVo.TaskCountItem> difficultMap = statisticsMapper.groupTaskBy(req.getDeptId(), req.getBeginTime(), req.getEndTime(), "difficult")
                .stream().collect(Collectors.toMap(TaskStatisticsVo.TaskCountItem::getGroupId, item -> item, (value1, value2) -> value1));
        Map<String, TaskStatisticsVo.TaskCountItem> docTypeMap = statisticsMapper.groupTaskBy(req.getDeptId(), req.getBeginTime(), req.getEndTime(), "docType")
                .stream().collect(Collectors.toMap(TaskStatisticsVo.TaskCountItem::getGroupId, item -> item, (value1, value2) -> value1));

        List<TaskStatisticsVo.TaskCountItem> causationResultList = causationDictMap.entrySet().stream().map(entry -> {
            String key = entry.getKey();
            TaskStatisticsVo.TaskCountItem taskCountItem = causationMap.get(key);
            if (null == taskCountItem) {
                taskCountItem = new TaskStatisticsVo.TaskCountItem();
                taskCountItem.setGroupId(key);
                taskCountItem.setNum(0L);
            }
            taskCountItem.setLabel(entry.getValue());
            return taskCountItem;
        }).sorted(Comparator.comparing(TaskStatisticsVo.TaskCountItem::getNum).reversed()).collect(Collectors.toList());

        List<TaskStatisticsVo.TaskCountItem> difficultResultList = difficultDictMap.entrySet().stream().map(entry -> {
            String key = entry.getKey();
            TaskStatisticsVo.TaskCountItem taskCountItem = difficultMap.get(key);
            if (null == taskCountItem) {
                taskCountItem = new TaskStatisticsVo.TaskCountItem();
                taskCountItem.setGroupId(key);
                taskCountItem.setNum(0L);
            }
            taskCountItem.setLabel(entry.getValue());
            return taskCountItem;
        }).sorted(Comparator.comparing(TaskStatisticsVo.TaskCountItem::getNum).reversed()).collect(Collectors.toList());

        List<TaskStatisticsVo.TaskCountItem> docTypeResultList = docTypeDictMap.entrySet().stream().map(entry -> {
            String key = entry.getKey();
            TaskStatisticsVo.TaskCountItem taskCountItem = docTypeMap.get(key);
            if (null == taskCountItem) {
                taskCountItem = new TaskStatisticsVo.TaskCountItem();
                taskCountItem.setGroupId(key);
                taskCountItem.setNum(0L);
            }
            taskCountItem.setLabel(entry.getValue());
            return taskCountItem;
        }).sorted(Comparator.comparing(TaskStatisticsVo.TaskCountItem::getNum).reversed()).collect(Collectors.toList());

        TaskStatisticsVo.TaskCountResp result = new TaskStatisticsVo.TaskCountResp();
        result.setCausationList(causationResultList);
        result.setDifficultList(difficultResultList);
        result.setDocTypeList(docTypeResultList);

        return result;
    }

    @Override
    public TaskStatisticsVo.MxfxHandleNumResp mxfxHandleNum(TaskStatisticsVo.MxfxHandleNumReq req) {
        TaskStatisticsVo.MxfxHandleNumResp result = new TaskStatisticsVo.MxfxHandleNumResp();
        Long deptId = req.getDeptId();
        String year = req.getYear();
        Integer quarter = req.getQuarter();
        DateTime yearDate = DateUtil.parse(year, "yyyy");
        DateTime start;
        DateTime end;
        if(null == quarter){
            start = DateUtil.beginOfYear(yearDate);
            end = DateUtil.endOfYear(yearDate);
        } else {
            DateTime d = DateUtil.offsetMonth(yearDate, (quarter) * 3 - 1);
            start = DateUtil.beginOfQuarter(d);
            end = DateUtil.endOfQuarter(d);
        }

        String startStr = DateUtil.formatDateTime(start);
        String endStr = DateUtil.formatDateTime(end);
        //x轴月份
        List<String> xDataList = new LinkedList<>();
        for(DateTime temp = DateUtil.date(start);temp.isBeforeOrEquals(end);temp = DateUtil.offsetMonth(temp,1)){
            String format = DateUtil.format(temp, "yyyy-MM");
            xDataList.add(format);
        }
        result.setXData(xDataList);

        //是否整个统计局
        boolean rootFlag = new Long(100).equals(deptId);
        if(rootFlag){
            SysDept query = new SysDept();
            query.setParentId(100L);
            query.setStatus("0");
            query.setDelFlag("0");
            List<SysDept> deptList = deptService.selectDeptList(query).stream().filter(item->100L != item.getDeptId()).collect(Collectors.toList());
            Map<Long, Map<String, List<TaskStatisticsVo.MxfxHandleNumDeptItem>>> deptNumMap = statisticsMapper.mxfxHandleNumCountByDept(startStr, endStr)
                    .stream().filter(item-> ObjectUtil.isNotNull(item.getDeptId()))
                    .collect(Collectors.groupingBy(TaskStatisticsVo.MxfxHandleNumDeptItem::getDeptId,
                            Collectors.groupingBy(TaskStatisticsVo.MxfxHandleNumDeptItem::getYearMonth)));

            List<TaskStatisticsVo.MxfxHandleNumItem> data = deptList.stream().map(dept -> {
                Long id = dept.getDeptId();
                TaskStatisticsVo.MxfxHandleNumItem item = new TaskStatisticsVo.MxfxHandleNumItem();
                item.setLabel(dept.getDeptAbbreviation());
                List<Long> list = new LinkedList<>();
                Map<String, List<TaskStatisticsVo.MxfxHandleNumDeptItem>> yearMonthNumMap = deptNumMap.get(id);
                if (null != yearMonthNumMap) {
                    for (int i = 0; i < xDataList.size(); i++) {
                        String dateStr = xDataList.get(i);
                        List<TaskStatisticsVo.MxfxHandleNumDeptItem> recordList = yearMonthNumMap.get(dateStr);
                        if (null != recordList && !recordList.isEmpty()) {
                            list.add(recordList.get(0).getNum());
                        } else {
                            list.add(0L);
                        }
                    }
                } else {
                    for (int i = 0; i < xDataList.size(); i++) {
                        list.add(0L);
                    }
                }
                item.setList(list);
                item.setSum(list.stream().mapToLong(a->a).sum());
                return item;
            }).collect(Collectors.toList());

            result.setData(data);
            return result;
        } else {
            SysUser query = new SysUser();
            query.setDeptId(deptId);
            query.setStatus("0");
            query.setDelFlag("0");
            List<SysUser> userList = userService.selectUserList(query);
            Map<String, Map<String,List<TaskStatisticsVo.MxfxHandleNumUserItem>>> userNumMap = statisticsMapper.mxfxHandleNumCountByUser(deptId, startStr, endStr)
                    .stream().filter(item->ObjectUtil.isNotNull(item.getUserName()))
                    .collect(Collectors.groupingBy(TaskStatisticsVo.MxfxHandleNumUserItem::getUserName,
                            Collectors.groupingBy(TaskStatisticsVo.MxfxHandleNumUserItem::getYearMonth)));

            List<TaskStatisticsVo.MxfxHandleNumItem> data = userList.stream().map(user -> {
                String userName = user.getUserName();
                TaskStatisticsVo.MxfxHandleNumItem item = new TaskStatisticsVo.MxfxHandleNumItem();
                item.setLabel(user.getName());
                List<Long> list = new LinkedList<>();
                Map<String, List<TaskStatisticsVo.MxfxHandleNumUserItem>> yearMonthNumMap = userNumMap.get(userName);
                if (null != yearMonthNumMap) {
                    for (int i = 0; i < xDataList.size(); i++) {
                        String dateStr = xDataList.get(i);
                        List<TaskStatisticsVo.MxfxHandleNumUserItem> recordList = yearMonthNumMap.get(dateStr);
                        if (null != recordList && !recordList.isEmpty()) {
                            list.add(recordList.get(0).getNum());
                        } else {
                            list.add(0L);
                        }
                    }
                } else {
                    for (int i = 0; i < xDataList.size(); i++) {
                        list.add(0L);
                    }
                }
                item.setList(list);
                item.setSum(list.stream().mapToLong(a->a).sum());
                return item;
            }).collect(Collectors.toList());

            result.setData(data);
            return result;
        }

    }
}
