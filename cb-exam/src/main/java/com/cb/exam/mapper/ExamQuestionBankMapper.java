package com.cb.exam.mapper;

import com.cb.exam.domain.ExamQuestionBank;
import com.cb.exam.vo.ExamBankQuestionCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题库Mapper接口
 *
 * @author hu
 * @date 2023-11-01
 */
public interface ExamQuestionBankMapper {

    /***
     *
     *   根据  这个题目ID查询 关联的题目的信息
     * @param questionId:
     * @return: java.util.List<com.cb.exam.domain.ExamQuestionBank>
     * @author: hu
     * @date: 2023/11/08
     * @throws:
     */
    public List<ExamQuestionBank> selectBankByQuestionIdList(Long questionId);


    /**
     * 查询题库
     *
     * @param id 题库主键
     * @return 题库
     */
    public ExamQuestionBank selectExamQuestionBankById(Long id);

    public List<ExamBankQuestionCountVo> selectExamBankQuestionCountList(ExamBankQuestionCountVo examBankQuestionCountVo);

    /**
     * 查询题库列表
     *
     * @param examQuestionBank 题库
     * @return 题库集合
     */
    public List<ExamQuestionBank> selectExamQuestionBankList(ExamQuestionBank examQuestionBank);

    /**
     * 查询题库列表
     *
     * @param roleId
     * @return 题库集合
     */
    public List<ExamQuestionBank> selectExamQuestionBankListByRole(@Param("roleIds") List<Long> roleIds);


    /**
     * 新增题库
     *
     * @param examQuestionBank 题库
     * @return 结果
     */
    public int insertExamQuestionBank(ExamQuestionBank examQuestionBank);

    /**
     * 修改题库
     *
     * @param examQuestionBank 题库
     * @return 结果
     */
    public int updateExamQuestionBank(ExamQuestionBank examQuestionBank);

    /**
     * 删除题库
     *
     * @param id 题库主键
     * @return 结果
     */
    public int deleteExamQuestionBankById(Long id);

    /**
     * 批量删除题库
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamQuestionBankByIds(Long[] ids);
}
