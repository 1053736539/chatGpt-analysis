package com.cb.task.mapper;

import com.cb.task.vo.TaskStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/20 9:34
 */
public interface TaskStatisticsMapper {

    List<TaskStatisticsVo.HandleCountItem> countDept(@Param("status") String status,
                                                     @Param("deptIds") List<Long> deptIds,
                                                     @Param("beginTime") String beginTime,
                                                     @Param("endTime") String endTime);

    List<TaskStatisticsVo.HandleCountItem> countUser(@Param("status") String status,
                                                     @Param("userNames") List<String> userNames,
                                                     @Param("beginTime") String beginTime,
                                                     @Param("endTime") String endTime);

    List<TaskStatisticsVo.TaskCountItem> groupTaskBy(@Param("deptId") Long deptId,
                                                     @Param("beginTime") String beginTime,
                                                     @Param("endTime") String endTime,
                                                     @Param("groupBy") String groupBy);

    List<TaskStatisticsVo.MxfxHandleNumDeptItem> mxfxHandleNumCountByDept(@Param("beginTime") String beginTime,
                                                                          @Param("endTime") String endTime);

    List<TaskStatisticsVo.MxfxHandleNumUserItem> mxfxHandleNumCountByUser(@Param("deptId") Long deptId,
                                                                          @Param("beginTime") String beginTime,
                                                                          @Param("endTime") String endTime);

}
