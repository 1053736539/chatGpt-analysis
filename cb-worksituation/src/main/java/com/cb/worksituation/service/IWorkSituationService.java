package com.cb.worksituation.service;

import com.cb.worksituation.domain.WorkSituation;
import com.cb.worksituation.vo.WorkSituationImportVo;
import com.cb.worksituation.vo.WorkSituationVo;

import java.util.List;

/**
 * 考勤统计（上班情况）Service接口
 *
 * @author ruoyi
 * @date 2023-11-15
 */
public interface IWorkSituationService
{
    /**
     * 查询考勤统计（上班情况）
     *
     * @param id 考勤统计（上班情况）ID
     * @return 考勤统计（上班情况）
     */
    public WorkSituation selectWorkSituationById(String id);

    /**
     * 查询考勤统计（上班情况）列表
     *
     * @param workSituation 考勤统计（上班情况）
     * @return 考勤统计（上班情况）集合
     */
    public List<WorkSituationVo> selectWorkSituationList(WorkSituationVo workSituationVo);

    List<WorkSituationVo> selectListByPublicity(WorkSituationVo workSituationVo);

    void toPublicity(WorkSituationVo workSituationVo);

    List<WorkSituationVo> selectWorkSituationListLeft(WorkSituationVo workSituationVo);

    Integer synchronousClock(String year, String month);

    WorkSituation selectPieVo(WorkSituationVo workSituationVo);

    /**
     * 新增考勤统计（上班情况）
     *
     * @param workSituation 考勤统计（上班情况）
     * @return 结果
     */
    public int insertWorkSituation(WorkSituation workSituation);

    /**
     * @Auther: yangcd
     * @Date: 2024/9/13 9:18
     * @param workSituation
     * @Description: 请假审批同步过来的
     */
    public int addWorkSituation(WorkSituation workSituation);

    int insertWorkSituationBatch(List<WorkSituation> workSituations);

    /**
     * 修改考勤统计（上班情况）
     *
     * @param workSituation 考勤统计（上班情况）
     * @return 结果
     */
    public int updateWorkSituation(WorkSituation workSituation);

    /**
     * 批量删除考勤统计（上班情况）
     *
     * @param ids 需要删除的考勤统计（上班情况）ID
     * @return 结果
     */
    public int deleteWorkSituationByIds(String[] ids);

    /**
     * 删除考勤统计（上班情况）信息
     *
     * @param id 考勤统计（上班情况）ID
     * @return 结果
     */
    public int deleteWorkSituationById(String id);

    public WorkSituation selectAssessWorkSituation(Long userId, String year, Integer start,
                                                   Integer end, String cycle);

    public String importWorkSituation(List<WorkSituationImportVo> dataList, boolean update, String month, String operName);
}
