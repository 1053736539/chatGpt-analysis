package com.cb.assess.service;

import com.cb.assess.domain.BizAssessCadreDemocraticTask;
import com.cb.assess.vo.CadreDemocraticTaskVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 年度处级干部民主测评任务Service接口
 * 
 * @author yangixn
 * @date 2023-11-25
 */
public interface IBizAssessCadreDemocraticTaskService 
{
    /**
     * 查询年度处级干部民主测评任务
     * 
     * @param id 年度处级干部民主测评任务ID
     * @return 年度处级干部民主测评任务
     */
    public BizAssessCadreDemocraticTask selectBizAssessCadreDemocraticTaskById(String id);

    /**
     * 导出word
     * @param taskId
     * @param userId
     */
    void exportTaskInfoWord(HttpServletResponse response, String taskId, Long userId);

    /**
     * 查询年度处级干部民主测评任务列表
     * 
     * @param bizAssessCadreDemocraticTask 年度处级干部民主测评任务
     * @return 年度处级干部民主测评任务集合
     */
    public List<BizAssessCadreDemocraticTask> selectBizAssessCadreDemocraticTaskList(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask);

    /**
     * 新增年度处级干部民主测评任务
     * 
     * @param bizAssessCadreDemocraticTask 年度处级干部民主测评任务
     * @return 结果
     */
    public int insertBizAssessCadreDemocraticTask(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask);

    /**
     * 修改年度处级干部民主测评任务
     * 
     * @param bizAssessCadreDemocraticTask 年度处级干部民主测评任务
     * @return 结果
     */
    public int updateBizAssessCadreDemocraticTask(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask);

    /**
     * 批量删除年度处级干部民主测评任务
     * 
     * @param ids 需要删除的年度处级干部民主测评任务ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticTaskByIds(String[] ids);

    /**
     * 删除年度处级干部民主测评任务信息
     * 
     * @param id 年度处级干部民主测评任务ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticTaskById(String id);

    /**
     * 获取默认的待评价用户ids
     * @return
     */
    public List<Long> getAssessedUserIds();

    /**
     * 创建任务
     * @param req
     */
    public void create(CadreDemocraticTaskVo.CreateReq req);

    /**
     * 上报到人事处
     * @param taskId
     */
    public void report2RSC(String taskId);

}
