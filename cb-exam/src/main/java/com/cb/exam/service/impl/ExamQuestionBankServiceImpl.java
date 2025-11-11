package com.cb.exam.service.impl;

import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.exam.domain.ExamQuestionBank;
import com.cb.exam.domain.ExamQuestionBankRole;
import com.cb.exam.mapper.ExamQuestionBankMapper;
import com.cb.exam.service.IExamQuestionBankRoleService;
import com.cb.exam.service.IExamQuestionBankService;
import com.cb.exam.vo.ExamBankQuestionCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题库Service业务层处理
 *
 * @author hu
 * @date 2023-11-01
 */
@Service
public class ExamQuestionBankServiceImpl implements IExamQuestionBankService {
    @Autowired
    private ExamQuestionBankMapper examQuestionBankMapper;

    @Autowired
    private IExamQuestionBankRoleService examQuestionBankRoleService;

    /**
     * 查询题库
     *
     * @param id 题库主键
     * @return 题库
     */
    @Override
    public ExamQuestionBank selectExamQuestionBankById(Long id) {
        return examQuestionBankMapper.selectExamQuestionBankById(id);
    }

    /**
     * 查询题库列表
     *
     * @param examQuestionBank 题库
     * @return 题库
     */
    @Override
    public List<ExamQuestionBank> selectExamQuestionBankList(ExamQuestionBank examQuestionBank) {
        return examQuestionBankMapper.selectExamQuestionBankList(examQuestionBank);
    }

    /**
     * 根据当前用户查询所有的题库信息
     *
     * @param roleId:
     * @return: java.util.List<com.cb.exam.domain.ExamQuestionBank>
     * @author: hu
     * @date: 2023/1/07 13:10
     * @throws:
     */
    @Override
    public List<ExamQuestionBank> selectExamQuestionBankListByRole(List<Long> roleIds) {
        return examQuestionBankMapper.selectExamQuestionBankListByRole(roleIds);
    }

    @Override
    public List<ExamBankQuestionCountVo> selectExamBankQuestionCountList(ExamBankQuestionCountVo examBankQuestionCountVo) {
//        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
//        examBankQuestionCountVo.setUserId(userId);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        List<Long> roleIds = new LinkedList<>();
        if(null != roles){
            roleIds = roles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        }
        if(roles.isEmpty()){
            return Collections.emptyList();
        }
        if(user.getUserName().equals("admin")){
            roleIds.add(20L);
        }
        examBankQuestionCountVo.setRoleIds(roleIds);
        return examQuestionBankMapper.selectExamBankQuestionCountList(examBankQuestionCountVo);
    }

    @Override
    public List<ExamQuestionBank> selectBankByQuestionIdList(Long questionId) {
        return examQuestionBankMapper.selectBankByQuestionIdList(questionId);
    }

    /**
     * 新增题库
     *
     * @param examQuestionBank 题库
     * @return 结果
     */
    @Override
    public int insertExamQuestionBank(ExamQuestionBank examQuestionBank) {
        //用户信息
        LoginUser user = SecurityUtils.getLoginUser();
        Date time = DateUtils.getNowDate();
        examQuestionBank.setCreateTime(time);
        examQuestionBank.setCreateBy(user.getUsername());
        int count = examQuestionBankMapper.insertExamQuestionBank(examQuestionBank);
        /**
         * 添加  试题和用户关系表
         */
        ExamQuestionBankRole bankRole = new ExamQuestionBankRole();
        bankRole.setUpdateTime(time);
        bankRole.setUpdateBy(user.getUsername());
        bankRole.setCreateTime(time);
        bankRole.setCreateBy(user.getUsername());
        Long roleId = 20L;//普查员的角色id
        bankRole.setRoleId(roleId);
        bankRole.setQuestionBankId(examQuestionBank.getId());
        count = examQuestionBankRoleService.insertExamQuestionBankRole(bankRole);
        /**
         * 添加试题题库
         */

        return count;
    }

    /**
     * 修改题库
     *
     * @param examQuestionBank 题库
     * @return 结果
     */
    @Override
    public int updateExamQuestionBank(ExamQuestionBank examQuestionBank) {//用户信息
        LoginUser user = SecurityUtils.getLoginUser();
        examQuestionBank.setUpdateBy(user.getUsername());
        examQuestionBank.setUpdateTime(DateUtils.getNowDate());
        return examQuestionBankMapper.updateExamQuestionBank(examQuestionBank);
    }

    /**
     * 批量删除题库
     *
     * @param ids 需要删除的题库主键
     * @return 结果
     */
    @Override
    public int deleteExamQuestionBankByIds(Long[] ids) {
        return examQuestionBankMapper.deleteExamQuestionBankByIds(ids);
    }

    /**
     * 删除题库信息
     *
     * @param id 题库主键
     * @return 结果
     */
    @Override
    public int deleteExamQuestionBankById(Long id) {
        return examQuestionBankMapper.deleteExamQuestionBankById(id);
    }
}
