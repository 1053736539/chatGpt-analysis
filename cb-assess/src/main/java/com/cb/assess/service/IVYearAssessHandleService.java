package com.cb.assess.service;

import com.cb.assess.vo.VYearAssessHandleVo;

import java.util.List;

/**
 * 年度考核相关任务合并查询
 */
public interface IVYearAssessHandleService {

    List<VYearAssessHandleVo.HandleInfo> selectList(VYearAssessHandleVo.Req req);
}
