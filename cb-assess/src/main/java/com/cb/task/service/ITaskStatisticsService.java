package com.cb.task.service;

import com.cb.task.vo.TaskStatisticsVo;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/20 9:31
 */
public interface ITaskStatisticsService {

    /**
     * 子级（一级部门/指定部门下的人） 已完成/执行中 数量统计
     * @param req
     * @return
     */
    TaskStatisticsVo.HandleNumCountResp handleNumCount(TaskStatisticsVo.HandleNumCountReq req);

    /**
     * 统计任务来源/分类/难度
     * @param req
     * @return
     */
    TaskStatisticsVo.TaskCountResp taskCount(TaskStatisticsVo.TaskCountReq req);

    /**
     * 忙闲分析 经办数量
     * @param req
     */
    TaskStatisticsVo.MxfxHandleNumResp mxfxHandleNum(TaskStatisticsVo.MxfxHandleNumReq req);

}
