package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessIndicatorProProcedure;
import com.cb.assess.domain.vo.ProcedureVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 考核方法程序Mapper接口
 *
 * @author ouyang
 * @date 2023-11-03
 */
public interface BizAssessIndicatorProProcedureMapper {

    public List<BizAssessIndicatorProProcedure> selectProProcedureByProId(String proId);

    public int batchInsertProProcedure(List<BizAssessIndicatorProProcedure> list);


    /**
     * 删除考核方法程序
     *
     * @param id 考核方法程序ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProProcedureById(String proId);

    /**
     * 批量删除考核方法程序
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessIndicatorProProcedureByIds(String[] proIds);
}
