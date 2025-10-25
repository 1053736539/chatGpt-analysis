package com.cb.salary.service;

import java.util.List;
import com.cb.salary.domain.Salary;

/**
 * 工资条Service接口
 *
 * @author hujilie
 * @date 2024-06-06
 */
public interface ISalaryService
{
    /**
     * 查询工资条
     *
     * @param id 工资条ID
     * @return 工资条
     */
    public Salary selectSalaryById(Long id);

    /**
     * 查询工资条列表
     *
     * @param salary 工资条
     * @return 工资条集合
     */
    public List<Salary> selectSalaryList(Salary salary);

    /**
     * 新增工资条
     *
     * @param salary 工资条
     * @return 结果
     */
    public int insertSalary(Salary salary);

    /**
     * 修改工资条
     *
     * @param salary 工资条
     * @return 结果
     */
    public int updateSalary(Salary salary);

    /**
     * 审核工资条
     * @param months
     * @return
     */
    public int auditSalary(List<String> months);

    /**
     * 批量删除工资条
     *
     * @param ids 需要删除的工资条ID
     * @return 结果
     */
    public int deleteSalaryByIds(Long[] ids);

    /**
     * 删除工资条信息
     *
     * @param id 工资条ID
     * @return 结果
     */
    public int deleteSalaryById(Long id);

    public String importSalary(List<Salary> dataList, int userType, boolean updateSupport, String month, String operName);
}
