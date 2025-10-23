package com.cb.worksituation.mapper;

import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.vo.WorkSituationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤统计（上班情况）Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-15
 */
public interface WorkSituationMapper 
{
    /**
     * 查询考勤统计（上班情况）
     * 
     * @param id 考勤统计（上班情况）ID
     * @return 考勤统计（上班情况）
     */
    public WorkSituation selectWorkSituationById(String id);
    public WorkSituation selectPieVo(WorkSituationVo workSituationVo);

    /**
     * 查询考勤统计（上班情况）列表
     * 
     * @param workSituation 考勤统计（上班情况）
     * @return 考勤统计（上班情况）集合
     */
    public List<WorkSituationVo> selectWorkSituationList(WorkSituationVo workSituationVo);
    public List<WorkSituationVo> selectListByPublicity(WorkSituationVo workSituationVo);
    public List<WorkSituationVo> selectWorkSituationListLeft(WorkSituationVo workSituationVo);

    /**
     * 新增考勤统计（上班情况）
     * 
     * @param workSituation 考勤统计（上班情况）
     * @return 结果
     */
    public int insertWorkSituation(WorkSituation workSituation);
    public int insertWorkSituationBatch(@Param("list") List<WorkSituation> workSituation);

    /**
     * 修改考勤统计（上班情况）
     * 
     * @param workSituation 考勤统计（上班情况）
     * @return 结果
     */
    public int updateWorkSituation(WorkSituation workSituation);
    public int updateToPublicity(WorkSituation workSituation);

    /**
     * 删除考勤统计（上班情况）
     * 
     * @param id 考勤统计（上班情况）ID
     * @return 结果
     */
    public int deleteWorkSituationById(String id);

    /**
     * 批量删除考勤统计（上班情况）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkSituationByIds(String[] ids);

    public WorkSituation selectAssessWorkSituation(@Param("userId") Long userId,
                                                   @Param("year") String year,
                                                   @Param("start")Integer start,
                                                   @Param("end")Integer end,
                                                   @Param("cycle") String cycle);
}
