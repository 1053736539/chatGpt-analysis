package com.cb.salary.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.salary.domain.Salary;
import com.cb.salary.mapper.SalaryMapper;
import com.cb.salary.service.ISalaryService;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 工资条Service业务层处理
 *
 * @author hujilie
 * @date 2024-06-06
 */
@Service
public class SalaryServiceImpl implements ISalaryService
{
    private static final Logger log = LoggerFactory.getLogger(SalaryServiceImpl.class);

    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询工资条
     *
     * @param id 工资条ID
     * @return 工资条
     */
    @Override
    public Salary selectSalaryById(Long id)
    {
        return salaryMapper.selectSalaryById(id);
    }

    /**
     * 查询工资条列表
     *
     * @param salary 工资条
     * @return 工资条
     */
    @Override
    public List<Salary> selectSalaryList(Salary salary)
    {
        return salaryMapper.selectSalaryList(salary);
    }

    /**
     * 新增工资条
     *
     * @param salary 工资条
     * @return 结果
     */
    @Override
    public int insertSalary(Salary salary)
    {
        salary.setId(UUID.randomUUID().toString());
        salary.setCreateTime(DateUtils.getNowDate());
        return salaryMapper.insertSalary(salary);
    }

    /**
     * 修改工资条
     *
     * @param salary 工资条
     * @return 结果
     */
    @Override
    public int updateSalary(Salary salary)
    {
        salary.setUpdateTime(DateUtils.getNowDate());
        return salaryMapper.updateSalary(salary);
    }

    /**
     * 批量删除工资条
     *
     * @param ids 需要删除的工资条ID
     * @return 结果
     */
    @Override
    public int deleteSalaryByIds(Long[] ids)
    {
        return salaryMapper.deleteSalaryByIds(ids);
    }

    /**
     * 删除工资条信息
     *
     * @param id 工资条ID
     * @return 结果
     */
    @Override
    public int deleteSalaryById(Long id)
    {
        return salaryMapper.deleteSalaryById(id);
    }

    @Override
    public String importSalary(List<Salary> dataList, int userType, boolean updateSupport, String month, String operName) {
        String msg = "";
        /*String y, m;
        if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
            String[] temp = month.split("-");
            y = temp[0];
            m = temp[1];
        } else {
            Calendar cal = Calendar.getInstance();
            y = cal.get(Calendar.YEAR) + "";
            m = (cal.get(Calendar.MONTH) + 1) + "";
        }*/
        String userName;
        SysUser sysUser;
        List<SysUser> userList;
        Salary salary;
        List<Salary> salaryList;
        Date date = new Date();
        int success = 0, fail = 0, add = 0, edit = 0;
        int index = 0;
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        copyOptions.setIgnoreCase(true);
        copyOptions.setIgnoreError(true);
        for(Salary vo : dataList) {
            index++;
            try {
                userName = vo.getName();
                sysUser = new SysUser();
                sysUser.setName(userName);
                userList = sysUserService.selectUserListByName(sysUser);
                if(userList.size() > 0) {
                    if(userList.size() > 1) {
                        msg += "\n第"+index + "行导入失败，姓名【"+userName+"】重复;";
                        fail++;
                        continue;
                    } else {
                        sysUser = userList.get(0);
                    }
                    salary = new Salary();

                    salary.setYear(vo.getYear());
                    salary.setMonth(vo.getMonth());
                    salary.setUserType(userType);
                    salary.setUserId(sysUser.getUserId());
                    salaryList = selectSalaryList(salary);
                    if(salaryList.size() > 0) {
                        salary = salaryList.get(0);
                        salary.setUpdateBy(operName);
                        salary.setUpdateTime(date);
                    } else {
                        salary.setCreateBy(operName);
                        salary.setCreateTime(date);
                    }
                    BeanUtil.copyProperties(vo, salary, copyOptions);
                    if(StringUtils.isEmpty(salary.getId())) {
                        salary.setStatus(0);
                        insertSalary(salary);
                        add++;
                    } else {
                        updateSalary(salary);
                        edit++;
                    }
                    success++;
                } else {
                    msg += "\n第"+index + "行导入失败，姓名【"+userName+"】未找到对应的系统用户;";
                    log.info("未找到系统用户，姓名=" + userName);
                    fail++;
                }
            }catch (Exception e) {
                log.error("导入错误", e);
                msg += "\n第"+index + "行导入失败，姓名【"+vo.getName()+"】;";
                fail++;
            }
        }
        msg = "工资记录共" + dataList.size() + "条，导入失败" + fail  + "条，导入成功" + success+ "条。（新增"
                + add + "条，更新" + edit + "条。）" + msg;
        if(userType == 1) {
            msg = "行政" + msg;
        } else {
            msg = "事业" + msg;
        }
        return msg;
    }

    @Override
    public int auditSalary(List<String> months) {
        String y, m;
        Salary salary = new Salary();
        for(String month : months) {
            if(StringUtils.isNotEmpty(month) && month.indexOf("-") > -1) {
                String[] temp = month.split("-");
                y = temp[0];
                m = temp[1];
                salary.setYear(y);
                salary.setMonth(m);
                salaryMapper.auditSalary(salary);
            }
        }
        return 1;
    }
}
