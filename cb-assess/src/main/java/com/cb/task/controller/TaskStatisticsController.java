package com.cb.task.controller;

import com.cb.common.constant.HttpStatus;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.PageDomain;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.core.page.TableSupport;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.sql.SqlUtil;
import com.cb.task.domain.BizTaskHandle;
import com.cb.task.service.IBizTaskHandleService;
import com.cb.task.service.ITaskStatisticsService;
import com.cb.task.vo.TaskStatisticsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/20 9:15
 */
@RestController
@RequestMapping("/task/statistics")
public class TaskStatisticsController {

    @Autowired
    private ITaskStatisticsService statisticsService;

    @Autowired
    private IBizTaskHandleService bizTaskHandleService;

    /**
     * 子级（一级部门/指定部门下的人） 已完成/执行中 经办记录数量统计
     * @param req
     * @return
     */
    @PostMapping("handleNumCount")
    public AjaxResult handleNumCount(@RequestBody TaskStatisticsVo.HandleNumCountReq req){
        TaskStatisticsVo.HandleNumCountResp subNumCountResp = statisticsService.handleNumCount(req);
        return AjaxResult.success(subNumCountResp);
    }

    /**
     * 查询统计用的任务列表（压力关爱）
     * @param bizTaskHandle
     * @param userId
     * @param userName
     * @param deptId
     * @return
     */
    @GetMapping("/listHandleList")
    public TableDataInfo listHandleList(BizTaskHandle bizTaskHandle,Long userId,String userName,Long deptId){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
        List<BizTaskHandle> list = bizTaskHandleService.listDeptOrUserList4Statistics(bizTaskHandle,userId,userName,deptId);

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 统计任务信息的来源、难度、分类数据
     * @param req
     * @return
     */
    @PostMapping("taskCount")
    public AjaxResult taskCount(@RequestBody TaskStatisticsVo.TaskCountReq req){
        TaskStatisticsVo.TaskCountResp taskCountResp = statisticsService.taskCount(req);
        return AjaxResult.success(taskCountResp);
    }

    /**
     * 忙闲分析 统计经办数量
     * @return
     */
    @PostMapping("mxfxHandleNum")
    public AjaxResult mxfxHandleNum(@Valid @RequestBody TaskStatisticsVo.MxfxHandleNumReq req){
        TaskStatisticsVo.MxfxHandleNumResp mxfxHandleNumResp = statisticsService.mxfxHandleNum(req);
        return AjaxResult.success(mxfxHandleNumResp);
    }

}
