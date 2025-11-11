package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbitySalary;

/**
 * 廉政档案-10.本人工资及各类奖金、津贴、补贴情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbitySalaryMapper {
    /**
     * 查询廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @param id 廉政档案-10.本人工资及各类奖金、津贴、补贴情况ID
     * @return 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     */
    public BizProbitySalary selectBizProbitySalaryById(String id);

    /**
     * 查询廉政档案-10.本人工资及各类奖金、津贴、补贴情况列表
     *
     * @param bizProbitySalary 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * @return 廉政档案-10.本人工资及各类奖金、津贴、补贴情况集合
     */
    public List<BizProbitySalary> selectBizProbitySalaryList(BizProbitySalary bizProbitySalary);

    /**
     * 新增廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @param bizProbitySalary 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * @return 结果
     */
    public int insertBizProbitySalary(BizProbitySalary bizProbitySalary);


    /**
     * 批量新增廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbitySalary> entities);

    /**
     * 修改廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @param bizProbitySalary 廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     * @return 结果
     */
    public int updateBizProbitySalary(BizProbitySalary bizProbitySalary);

    /**
     * 删除廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @param id 廉政档案-10.本人工资及各类奖金、津贴、补贴情况ID
     * @return 结果
     */
    public int deleteBizProbitySalaryById(String id);

    /**
     * 批量删除廉政档案-10.本人工资及各类奖金、津贴、补贴情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbitySalaryByIds(String[] ids);
}
