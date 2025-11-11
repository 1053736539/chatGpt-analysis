package com.cb.exam.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.exam.domain.ExamTask;
import com.cb.exam.domain.ExamUserAnswer;
import com.cb.exam.mapper.ExamTaskMapper;
import com.cb.exam.mapper.ExamUserAnswerMapper;
import com.cb.exam.service.IExamTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试任务Service业务层处理
 *
 * @author hu
 * @date 2023-11-08
 */
@Service
public class ExamTaskServiceImpl implements IExamTaskService {
    @Autowired
    private ExamTaskMapper examTaskMapper;

    @Resource
    private ExamUserAnswerMapper userAnswerMapper;

    /**
     * 查询考试任务
     *
     * @param id 考试任务主键
     * @return 考试任务
     */
    @Override
    public ExamTask selectExamTaskById(Long id) {
        return examTaskMapper.selectExamTaskById(id);
    }

    /**
     * 查询考试任务列表
     *
     * @param examTask 考试任务
     * @return 考试任务
     */
    @Override
    public List<ExamTask> selectExamTaskList(ExamTask examTask) {
        return examTaskMapper.selectExamTaskList(examTask);
    }

    /**
     * 新增考试任务
     *
     * @param examTask 考试任务
     * @return 结果
     */
    @Override
    public int insertExamTask(ExamTask examTask) {
        examTask.setCreateTime(DateUtils.getNowDate());
        return examTaskMapper.insertExamTask(examTask);
    }

    /**
     * 修改考试任务
     *
     * @param examTask 考试任务
     * @return 结果
     */
    @Override
    public int updateExamTask(ExamTask examTask) {
        return examTaskMapper.updateExamTask(examTask);
    }

    /**
     * 批量删除考试任务
     *
     * @param ids 需要删除的考试任务主键
     * @return 结果
     */
    @Override
    public int deleteExamTaskByIds(Long[] ids) throws Exception {

        /**
         * 删除前 先校验是否 有被 用户答题引用
         */
        List<ExamUserAnswer> list = userAnswerMapper.selectListByTaskIdOrPaperId(ids, null);

        if (StringUtils.isNotNull(list) && list.size() > 0) {
            throw new Exception("任务有被用户答题引用，不允许删除！");
        }

        return examTaskMapper.deleteExamTaskByIds(ids);
    }

    /**
     * 删除考试任务信息
     *
     * @param id 考试任务主键
     * @return 结果
     */
    @Override
    public int deleteExamTaskById(Long id) throws Exception {

        /**
         * 删除前 先校验是否 有被 用户答题引用
         */
        Long[] ids = new Long[1];
        ids[0] = id;
        List<ExamUserAnswer> list = userAnswerMapper.selectListByTaskIdOrPaperId(ids, null);

        if (StringUtils.isNotNull(list) && list.size() > 0) {
            throw new Exception("任务有被用户答题引用，不允许删除！");
        }
        return examTaskMapper.deleteExamTaskById(id);
    }
}
