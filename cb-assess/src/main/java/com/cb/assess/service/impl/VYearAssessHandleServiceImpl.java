package com.cb.assess.service.impl;

import com.cb.assess.mapper.VYearAssessHandleMapper;
import com.cb.assess.service.IVYearAssessHandleService;
import com.cb.assess.vo.VYearAssessHandleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  年度考核相关任务合并查询
 */
@Service
public class VYearAssessHandleServiceImpl implements IVYearAssessHandleService {

    @Autowired
    private VYearAssessHandleMapper vYearAssessHandleMapper;

    @Override
    public List<VYearAssessHandleVo.HandleInfo> selectList(VYearAssessHandleVo.Req req){
        return vYearAssessHandleMapper.selectList(req);
    }

}
