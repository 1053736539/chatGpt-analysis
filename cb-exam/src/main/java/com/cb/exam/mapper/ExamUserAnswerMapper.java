package com.cb.exam.mapper;

import com.cb.exam.domain.ExamUserAnswer;
import com.cb.exam.domain.VExamUserAnswer;
import com.cb.exam.vo.ExamUserAnswerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户答题Mapper接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface ExamUserAnswerMapper {
    /**
     * 查询用户答题
     *
     * @param id 用户答题主键
     * @return 用户答题
     */
    public ExamUserAnswer selectExamUserAnswerById(Long id);

    /**
     * 查询用户答题列表
     *
     * @param examUserAnswer 用户答题
     * @return 用户答题集合
     */
    public List<ExamUserAnswer> selectExamUserAnswerList(ExamUserAnswer examUserAnswer);

    /**
     * 查询用户答题列表
     *
     * @param taskIds  任务的id集合
     * @param paperIds 试卷的id集合
     * @return 用户答题集合
     */
    public List<ExamUserAnswer> selectListByTaskIdOrPaperId(@Param("taskIds") Long[] taskIds,
                                                            @Param("paperIds") Long[] paperIds);

    /**
     * 查询用户答题列表对返回值做了单独的处理
     *
     * @param examUserAnswer 用户答题
     * @return 用户答题集合
     */
    //public List<ExamUserAnswerVo> selectExamUserAnswerListVo(ExamUserAnswer examUserAnswer);
    public List<VExamUserAnswer> selectExamUserAnswerListVo(VExamUserAnswer examUserAnswer);
    /**
     * 新增用户答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    public int insertExamUserAnswer(ExamUserAnswer examUserAnswer);

    /**
     * 修改用户答题
     *
     * @param examUserAnswer 用户答题
     * @return 结果
     */
    public int updateExamUserAnswer(ExamUserAnswer examUserAnswer);

    /**
     * 删除用户答题
     *
     * @param id 用户答题主键
     * @return 结果
     */
    public int deleteExamUserAnswerById(Long id);

    /**
     * 批量删除用户答题
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamUserAnswerByIds(Long[] ids);
}
