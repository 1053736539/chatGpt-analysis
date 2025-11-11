package com.cb.leave.mapper;


import com.cb.leave.domain.vo.LeaveCensusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请休统计Controller
 *
 * @author ouyang
 * @date 2025-01-11
 */
public interface LeaveCensusMapper {

    public List<LeaveCensusVo> selectLeaveCensus(@Param("query") LeaveCensusVo censusVo, @Param("exclude") Boolean exclude);
    //办公室
    public List<LeaveCensusVo> selectLeaveCensusListByDeptIds(@Param("query") LeaveCensusVo censusVo, @Param("exclude") Boolean exclude);

    /**
     * 根据depytIds和其他查询条件一起查询公休率情况
     * @param leaveCensusVo
     * @param deptIds
     * @return
     */
    public List<LeaveCensusVo> selectLeaveCensusByDeptIds(@Param("query") LeaveCensusVo leaveCensusVo,@Param("deptIds") List<Long> deptIds);

}
