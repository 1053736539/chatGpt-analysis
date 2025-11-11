package com.cb.leave.service;

import com.cb.leave.domain.vo.LeaveCensusVo;
import com.cb.leave.vo.LeaveChartVo;

import java.util.List;

public interface LeaveCensusService {

    /**
     * 获取请假情况树形结构数据
     * 个人只能查看个人的请销假情况，部门领导可以查看整个部门的情况,其他返回空数据
     * @param leaveCensusVo
     * @return
     */
    public List<LeaveCensusVo> selectLeaveSituationList(LeaveCensusVo leaveCensusVo);

    /**
     * 首页图表数据
     * @param req
     * @return
     */
    public LeaveChartVo.HomeChartResp listHomeChartData(LeaveChartVo.HomeChartReq req);

}
