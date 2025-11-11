package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessCadreDemocraticTask;

import java.util.List;

/**
 * 年度处级干部民主测评任务Mapper接口
 * 
 * @author yangixn
 * @date 2023-11-25
 */
public interface BizAssessCadreDemocraticTaskMapper 
{
    /**
     * 查询年度处级干部民主测评任务
     * 
     * @param id 年度处级干部民主测评任务ID
     * @return 年度处级干部民主测评任务
     */
    public BizAssessCadreDemocraticTask selectBizAssessCadreDemocraticTaskById(String id);

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
     * 删除年度处级干部民主测评任务
     * 
     * @param id 年度处级干部民主测评任务ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticTaskById(String id);

    /**
     * 批量删除年度处级干部民主测评任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessCadreDemocraticTaskByIds(String[] ids);
}
