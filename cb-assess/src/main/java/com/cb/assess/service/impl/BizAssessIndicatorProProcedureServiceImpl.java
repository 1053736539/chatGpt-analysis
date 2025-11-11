package com.cb.assess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessIndicatorProProcedureMapper;
import com.cb.assess.service.IBizAssessIndicatorProProcedureService;

/**
 * 考核方法程序Service业务层处理
 * 
 * @author ouyang
 * @date 2023-11-03
 */
@Service
public class BizAssessIndicatorProProcedureServiceImpl implements IBizAssessIndicatorProProcedureService 
{
    @Autowired
    private BizAssessIndicatorProProcedureMapper bizAssessIndicatorProProcedureMapper;


}
