package com.cb.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.UserUtil;
import com.cb.exam.domain.*;
import com.cb.exam.dto.ExamPaperDto;
import com.cb.exam.enums.QuestionTypeEnum;
import com.cb.exam.mapper.*;
import com.cb.exam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷Service业务层处理
 *
 * @author hu
 * @date 2023-11-08
 */
@Service
public class ExamPaperServiceImpl implements IExamPaperService {
    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Autowired
    private IExamPaperDetailService examPaperDetailService;

    @Autowired
    private IExamQuestionsService questionsService;

    @Autowired
    private IExamOptionService optionService;

    @Autowired
    private IExamPaperQuestionsService paperQuestionsService;

    @Autowired
    private IExamPaperQuestionsOptionService paperQuestionsOptionService;

    @Autowired
    private ExamUserAnswerMapper userAnswerMapper;

    @Autowired
    private ExamTaskMapper examTaskMapper;

    @Autowired
    private ExamPaperDetailMapper paperDetailMapper;


    /**
     * 查询试卷
     *
     * @param id 试卷主键
     * @return 试卷
     */
    @Override
    public ExamPaper selectExamPaperById(Long id) {
        return examPaperMapper.selectExamPaperById(id);
    }

    /**
     * 查询试卷列表
     *
     * @param examPaper 试卷
     * @return 试卷
     */
    @Override
    public List<ExamPaper> selectExamPaperList(ExamPaper examPaper) {
        return examPaperMapper.selectExamPaperList(examPaper);
    }

    /**
     * 新增试卷
     *
     * @param examPaper 试卷
     * @return 结果
     */
    @Override
    public int insertExamPaper(ExamPaper examPaper) {
        examPaper.setCreateTime(DateUtils.getNowDate());
        return examPaperMapper.insertExamPaper(examPaper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addExamPaper(ExamPaperDto.Add.Input examPaper) {
        //先添加试卷
        ExamPaper paper = new ExamPaper();
        BeanUtil.copyProperties(examPaper, paper, "");
        //初始化
        paper = JSONObject.parseObject(UserUtil.created(JSONObject.toJSONString(paper)), ExamPaper.class);
        int count = examPaperMapper.insertExamPaper(paper);
        //在添加试卷详情
        examPaper.setId(paper.getId());
        count = examPaperDetailService.addExamPaperDetail(examPaper);
        //在添加题目 获取选择的题目信息
        List<ExamPaperQuestions> paperQuestionList = this.getPaperQuestionList(examPaper);
        //批量添加试卷的题目信息
        count = paperQuestionsService.insertBatchExamPaperQuestions(paperQuestionList);
        //获取试卷的题目的单选多选的题目选项信息
        List<ExamPaperQuestionsOption> optionList = this.getPaperQuestonOption(paperQuestionList, examPaper.getCreateUser());
        //校验是否有选择题的选项，有的话就添加
        if (optionList.size() > 0) {
            //批量添加试卷的题目的选项的信息
            count = paperQuestionsOptionService.insertBatchExamPaperQuestionsOption(optionList);
        }
        return count;
    }

    /**
     * 获取可以添加的试卷题目的选项信息
     */
    private List<ExamPaperQuestionsOption> getPaperQuestonOption(List<ExamPaperQuestions> questionList, String createUser) {
        List<ExamPaperQuestionsOption> paperQuestionsOptions = new ArrayList<>();
        //筛选题目，筛选出单选多选题
        List<ExamPaperQuestions> questionListBytype = questionList.stream().filter(item -> item.getExaminationQuestionsType().equals(QuestionTypeEnum.SELECTS.getCode()) || item.getExaminationQuestionsType().equals(QuestionTypeEnum.SELECT.getCode())).collect(Collectors.toList());
        //校验是否有单选或者多选题
        if (questionListBytype.size() > 0) {
            //取出所有的单选题 多选题的id
            List<Long> questionIdList = questionListBytype.stream().map(ExamPaperQuestions::getExaminationQuestionsId).collect(Collectors.toList());
            //所有题目关联的选项数据
            List<ExamOption> optionList = optionService.selectExamOptionByquestionId(questionIdList.toArray(new Long[questionIdList.size()]));
            //筛选初始化后的试卷的题目的可以添加的选项
            questionListBytype.forEach(item -> {
                List<ExamPaperQuestionsOption> optionListByQuestion = new ArrayList();
                //筛选出题目的所有选项
                for (ExamOption obj : optionList) {
                    if (obj.getOptionId().equals(item.getExaminationQuestionsId())) {
                        ExamPaperQuestionsOption entity = new ExamPaperQuestionsOption();
                        entity.setCreateBy(createUser);
                        entity.setCreateTime(new Date());
                        //题目的id
                        entity.setOptionId(obj.getId());
                        //试卷的试题的id
                        entity.setExaminationPaperQuestionsId(item.getId());
                        optionListByQuestion.add(entity);
                    }
                }
                //选项打乱 然后添加选项序号
                Collections.shuffle(optionListByQuestion);
                //打乱顺序后 填补ABCD
                for (int i = 0; i < optionListByQuestion.size(); i++) {
                    ExamPaperQuestionsOption entity = optionListByQuestion.get(i);
                    switch (i) {
                        case 0:
                            entity.setSerialNumber("A");
                            break;
                        case 1:
                            entity.setSerialNumber("B");
                            break;
                        case 2:
                            entity.setSerialNumber("C");
                            break;
                        case 3:
                            entity.setSerialNumber("D");
                            break;
                        case 4:
                            entity.setSerialNumber("E");
                            break;
                        case 5:
                            entity.setSerialNumber("F");
                            break;
                        case 6:
                            entity.setSerialNumber("G");
                            break;
                        default:
                            entity.setSerialNumber("H");
                            break;
                    }
                    paperQuestionsOptions.add(entity);
                }
            });
        }
        return paperQuestionsOptions;
    }

    /**
     * 获取可以添加的试卷题目数据
     */
    private List<ExamPaperQuestions> getPaperQuestionList(ExamPaperDto.Add.Input examPaper) {
        //获取到所有的随机题目
        List<ExamQuestions> questionList = this.getOptionListBylabelsBanks(examPaper);
        //设置初始化试卷关联的题目
        List<ExamPaperQuestions> list = questionList.stream().map(item -> {
            ExamPaperQuestions entity = new ExamPaperQuestions();
            entity.setCreateBy(examPaper.getCreateUser());
            entity.setCreateTime(new Date());
            entity.setExaminationPaperId(examPaper.getId());
            entity.setExaminationQuestionsId(item.getId());
            entity.setExaminationQuestionsType(item.getType());
            if (item.getType().equals(QuestionTypeEnum.SELECTS.getCode())) {
                entity.setScore(examPaper.getSelectsScore());
            } else if (item.getType().equals(QuestionTypeEnum.SELECT.getCode())) {
                entity.setScore(examPaper.getSelectScore());
            } else if (item.getType().equals(QuestionTypeEnum.TEXT.getCode())) {
                entity.setScore(examPaper.getAnswerScore());
            } else if (item.getType().equals(QuestionTypeEnum.VERDICT.getCode())) {
                entity.setScore(examPaper.getJudgeScore());
            }
            return entity;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * 查询符合试卷详情的题目
     */
    private List<ExamQuestions> getOptionListBylabelsBanks(ExamPaperDto.Add.Input examPaper) {
        List<ExamQuestions> questionList = new ArrayList<>();
        examPaper.getList().stream().forEach(item -> {
            ExamPaperDetail paperDetail = new ExamPaperDetail(
                    item.getBank(),
                    item.getLabel(),
                    item.getSelectCount(), item.getSelectsCount(),
                    item.getJudgeCount(), item.getAnswerCount());
            if (questionList.size() > 0) {
                //如果已有查出的数据，就加上此数据作为条件 在次筛选的时候 避开这些数据
                paperDetail.setQuestionId(questionList.stream().map(ExamQuestions::getId).toArray(Long[]::new));
            }
            List<ExamQuestions> list = questionsService.selectExamQuestionByLabelsBanks(paperDetail);
            questionList.addAll(list);
        });
        return questionList;
    }

    /**
     * 修改试卷
     *
     * @param examPaper 试卷
     * @return 结果
     */
    @Override
    public int updateExamPaper(ExamPaper examPaper) {
        return examPaperMapper.updateExamPaper(examPaper);
    }

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的试卷主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteExamPaperByIds(Long[] ids) throws Exception {

        /**
         * 先校验时是否有任务引用
         */
        List<ExamTask> taskList = examTaskMapper.selectExamTaskListByPaperId(ids);

        if (StringUtils.isNotNull(taskList) && taskList.size() > 0) {
            throw new Exception("试卷有被任务引用，不允许删除！");
        }

        /**
         * 删除前 先校验是否 有被 用户答题引用
         */
        List<ExamUserAnswer> list = userAnswerMapper.selectListByTaskIdOrPaperId(null, ids);

        if (StringUtils.isNotNull(list) && list.size() > 0) {
            throw new Exception("试卷有被用户答题引用，不允许删除！");
        }
        /**
         * 开始删除 试卷
         */
        int count = examPaperMapper.deleteExamPaperByIds(ids);

        //删除 详情表
        count = paperDetailMapper.deleteExamPaperDetailByPaperIds(ids);

        //删除试卷关联的试题表
        paperQuestionsService.deleteExamPaperQuestionsByPaperids(ids);
        return count;
    }

    /**
     * 删除试卷信息
     *
     * @param id 试卷主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteExamPaperById(Long id) throws Exception {

        Long[] ids = new Long[1];
        ids[0] = id;
        return this.deleteExamPaperByIds(ids);
    }
}
