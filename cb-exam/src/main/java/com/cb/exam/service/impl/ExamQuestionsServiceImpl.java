package com.cb.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cb.common.annotation.LogAop;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.constant.Constants;
import com.cb.common.constant.HttpStatus;
import com.cb.common.core.domain.entity.SysRole;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.file.FileUploadUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.exam.domain.*;
import com.cb.exam.dto.ExamOptionDto;
import com.cb.exam.dto.ExamPaperDetailDto;
import com.cb.exam.dto.ExamQuestionsDto;
import com.cb.exam.enums.QuestionTypeEnum;
import com.cb.exam.mapper.ExamQuestionsMapper;
import com.cb.exam.service.*;
import com.cb.exam.vo.ExamOptionVo;
import com.cb.exam.vo.ExamQuestionShowVo;
import com.cb.exam.vo.ExamQuestionVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 试题Service业务层处理
 *
 * @author hu
 * @date 2023-11-07
 */
@SuppressWarnings("ALL")
@Service
// extends BaseServicePage
public class ExamQuestionsServiceImpl implements IExamQuestionsService {
    @Autowired
    private ExamQuestionsMapper examQuestionsMapper;

    @Autowired
    private IExamQuestionBankQuestionsService questionBankQuestionsService;

    @Autowired
    private IExamLabelQuestionsService labelQuestionsService;

    @Autowired
    private IExamOptionService optionService;

    @Autowired
    private IExamLabelService labelService;

    @Autowired
    private IExamQuestionBankService bankService;

    /**
     * 查询试题
     *
     * @param id 试题主键
     * @return 试题
     */
    @Override
    /*public ExamQuestions selectExamQuestionsById(Long id) {
        return examQuestionsMapper.selectExamQuestionsById(id);
    }*/
    public ExamQuestionEditVo selectExamQuestionsById(Long id) {
        return examQuestionsMapper.selectExamQuestionsById(id);
    }

    /***
     * @Summary
     * @Param: [paperDetail]
     * @Return: com.cb.exam.domain.ExamPaperDetail
     * @Author: hu
     * @Date: 2023/11/07 16:25
     * @Description 根据选择的这个试卷生成的所需要的标签和题库，去查询对应的不同题目类型的题目数量
     */
    @Override
    public ExamPaperDetail selectExamQuestionCountByType(ExamPaperDetailDto.SelectQuestionTypeCount.Input paperDetail) {
        //查询不同的题目类型的题目数量
        ExamPaperDetail detail = new ExamPaperDetail();
        ExamPaperDetail examPaperDetail = new ExamPaperDetail();
        examPaperDetail.setAnswerCount((long) 0);
        examPaperDetail.setJudgeCount((long) 0);
        examPaperDetail.setSelectCount((long) 0);
        examPaperDetail.setSelectsCount((long) 0);
        if (paperDetail.getLabel().size() < 1 && paperDetail.getBank().size() < 1) {
            return examPaperDetail;
        }
        detail.setBankList(paperDetail.getBank().toArray(new Long[paperDetail.getBank().size()]));
        detail.setLabelList(paperDetail.getLabel().toArray(new Long[paperDetail.getLabel().size()]));
        List<QuestionCount> typeCountList = examQuestionsMapper.selectExamQuestionCountByType(detail);
        //设置返回值

        for (QuestionCount entity : typeCountList) {
            if (entity.getType().equals(QuestionTypeEnum.SELECT.getCode())) {
                examPaperDetail.setSelectCount(entity.getCount());
            } else if (entity.getType().equals(QuestionTypeEnum.SELECTS.getCode())) {
                examPaperDetail.setSelectsCount(entity.getCount());
            } else if (entity.getType().equals(QuestionTypeEnum.TEXT.getCode())) {
                examPaperDetail.setAnswerCount(entity.getCount());
            } else if (entity.getType().equals(QuestionTypeEnum.VERDICT.getCode())) {
                examPaperDetail.setJudgeCount(entity.getCount());
            }
        }
        return examPaperDetail;
    }

    /**
     * 生成试卷的时候 按照试卷详情生成具体的题目信息
     */
    @Override
    public List<ExamQuestions> selectExamQuestionByLabelsBanks(ExamPaperDetail examPaperDetail) {
        return examQuestionsMapper.selectExamQuestionByLabelsBanks(examPaperDetail);
    }

    /*protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }*/

    @Override
    @LogAop(title = "这个是试题模块", businessType = BusinessType.OTHER, remark = "这个是备注 查看模块")
    public TableDataInfo selectExamQuestionAndOptionsByLabelsBanks(ExamQuestions examPaperDetail) {
//        LoginUser user = SecurityUtils.getLoginUser();
//        //获取所有的符合条件的题目
//        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
//        examPaperDetail.setUserId(userId);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        List<Long> roleIds = new LinkedList<>();
        if(null != roles){
            roleIds = roles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        }
        if(roles.isEmpty()){
            return getDataTable(Collections.emptyList());
        }
        if(user.getUserName().equals("admin")){
            roleIds.add(20L);
        }
        List<ExamQuestionShowVo> questionsList = examQuestionsMapper.selectExamQuestionShowVo(examPaperDetail);
        List<ExamQuestionShowVo> resList = new ArrayList<>(questionsList);
        TableDataInfo tableDataInfo = getDataTable(questionsList);
        //判断题目数量是否为小于1
        if (resList.size() < 1) {
            return tableDataInfo;
        }
        //取出题目的id
        List<Long> questionIdList = resList.stream().map(ExamQuestionShowVo::getId).collect(Collectors.toList());
        //根据题目的id取出所有的选项的id
        List<ExamOption> optionsList = optionService.selectExamOptionByquestionId(questionIdList.toArray(new Long[questionIdList.size()]));
        //题目匹配选项然后做信息返回
        List<ExamQuestionVo> questionVoList = resList.stream().map(item -> {
            ExamQuestionVo questionVo = new ExamQuestionVo();
            BeanUtil.copyProperties(item, questionVo, "");
            questionVo.setOptionVoList(
                    optionsList.stream().filter(obj -> item.getId().equals(obj.getOptionId())).map(obj -> {
                        ExamOptionVo optionVo = new ExamOptionVo();
                        BeanUtil.copyProperties(obj, optionVo, "");
                        return optionVo;
                    }).collect(Collectors.toList())
            );
            return questionVo;
        }).collect(Collectors.toList());
        tableDataInfo.setRows(questionVoList);

        return tableDataInfo;
    }

    private TableDataInfo getDataTable(List<ExamQuestionShowVo> questionsList) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(questionsList);
        rspData.setTotal(new PageInfo(questionsList).getTotal());
        return rspData;
    }

    public void exec() {
        System.out.println(Thread.currentThread().getName() + "---------1111--------------------------");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    //do Something
                    System.out.println("执行结束---------------------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000 * 60 * 2, 1000);
    }

    /**
     * 查询试题列表
     *
     * @param examQuestions 试题
     * @return 试题
     */
    @Override
    public List<ExamQuestions> selectExamQuestionsList(ExamQuestions examQuestions) {
        //TODO  只显示当前拥有的题库的 题目\
        /**
         * 先查询当前用户的题库数据
         */
        return examQuestionsMapper.selectExamQuestionsList(examQuestions);
    }

    /**
     * 新增试题
     *
     * @param examQuestions 试题
     * @return 结果
     */
    @Override
    public int insertExamQuestions(ExamQuestions examQuestions) {
        examQuestions.setCreateTime(DateUtils.getNowDate());
        LoginUser user = SecurityUtils.getLoginUser();
        examQuestions.setCreateBy(user.getUsername());
        return examQuestionsMapper.insertExamQuestions(examQuestions);
    }

    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Override
    @Transactional
    @LogAop(title = "新增的方法")
    public int addExamQuestions(ExamQuestionsDto.Add.Input params) {
        //判断是否有需要新建的标签
        if (params.getNewLabelList().size() > 0) {
            //如果有 就添加进去
            List<ExamLabel> labelList = params.getNewLabelList().stream().map(item -> {
                ExamLabel label = new ExamLabel();
                label.setName(item);
                //label.setCreateBy(getUsername());
                label.setCreateTime(new Date());
                return label;
            }).collect(Collectors.toList());
            labelService.insertBatchExamLabel(labelList);
            labelList.forEach(item -> {
                params.getLabels().add(item.getId().intValue());
            });
        }
        System.out.println(params.getLabels().toString());
        //试题的实体类
        ExamQuestions question = new ExamQuestions();
        BeanUtil.copyProperties(params, question, "");
        //判断要添加的题目类型
        if (params.getType().equals(QuestionTypeEnum.SELECT.getCode())) {
            //单选题
            question.setSelectAnswer(params.getSelectAnswer().toString());
        } else if (params.getType().equals(QuestionTypeEnum.SELECTS.getCode())) {
            //多选题
            if(params.getSelectAnswers() != null) {
                String str = "";
                for (int i = 0; i < params.getSelectAnswers().size(); i++) {
                    if (i == 0) {
                        str = params.getSelectAnswers().get(i).toString();
                    } else {
                        str = str + "," + params.getSelectAnswers().get(i);
                    }
                }
                question.setSelectAnswer(str);
            }
        } else if (params.getType().equals(QuestionTypeEnum.VERDICT.getCode())) {
            //判断题
            int s = 0 == Optional.ofNullable(params.getJudgeAnswer()).orElse(0) ? 0 : 1;
            question.setJudgeAnswer(s);
        } else if (params.getType().equals(QuestionTypeEnum.TEXT.getCode())) {
            //问答题
            question.setAnswer(params.getAnswer());
        }
        //设置是否启用的属性
        int s = 0 == Optional.ofNullable(params.getIsEnable()).orElse(0) ? 0 : 1;
        question.setIsEnable(s);
        //调用添加试题的方法
        int count = this.insertExamQuestions(question);
        //批量添加题库，添加标签
        insertBatchBankQuestion(question, params.getBanks());
        insertBatchLabelQuestion(question, params.getLabels());
        //TODO 方法做拆分
        //多选题或者单选题 添加选项并修改题目答案绑定
        insertOptions(params.getList(), question);
        return count;
    }

    @Autowired
    private IExamPaperQuestionsService paperQuestionsService;

    @Override
    @Transactional
    public int updateExamQuestions(ExamQuestionsDto.Add.Input params) {
        //有被引用的不允许改
        ExamPaperQuestions query = new ExamPaperQuestions();
        query.setExaminationQuestionsId(params.getId());
        List<ExamPaperQuestions> useList = paperQuestionsService.selectExamPaperQuestionsList(query);
        if(!useList.isEmpty()){
            throw new RuntimeException("该试题已被引用，不允许修改及删除！");
        }
        //判断是否有需要新建的标签
        if (params.getNewLabelList().size() > 0) {
            //如果有 就添加进去
            List<ExamLabel> labelList = params.getNewLabelList().stream().map(item -> {
                ExamLabel label = new ExamLabel();
                label.setName(item);
                label.setCreateTime(new Date());
                return label;
            }).collect(Collectors.toList());
            labelService.insertBatchExamLabel(labelList);
            labelList.forEach(item -> {
                params.getLabels().add(item.getId().intValue());
            });
        }
        System.out.println(params.getLabels().toString());
        //试题的实体类
        ExamQuestions question = new ExamQuestions();
        BeanUtil.copyProperties(params, question, "");
        //判断要添加的题目类型
        if (params.getType().equals(QuestionTypeEnum.SELECT.getCode())) {
            //单选题
            question.setSelectAnswer(params.getSelectAnswer().toString());
        } else if (params.getType().equals(QuestionTypeEnum.SELECTS.getCode())) {
            //多选题
            String str = "";
            for (int i = 0; i < params.getSelectAnswers().size(); i++) {
                if (i == 0) {
                    str = params.getSelectAnswers().get(i).toString();
                } else {
                    str = str + "," + params.getSelectAnswers().get(i);
                }
            }
            question.setSelectAnswer(str);
        } else if (params.getType().equals(QuestionTypeEnum.VERDICT.getCode())) {
            //判断题
            int s = 0 == Optional.ofNullable(params.getJudgeAnswer()).orElse(0) ? 0 : 1;
            question.setJudgeAnswer(s);
        } else if (params.getType().equals(QuestionTypeEnum.TEXT.getCode())) {
            //问答题
            question.setAnswer(params.getAnswer());
        }
        //设置是否启用的属性
        int s = 0 == Optional.ofNullable(params.getIsEnable()).orElse(0) ? 0 : 1;
        question.setIsEnable(s);
        //调用添加试题的方法
        int count = this.updateExamQuestions(question);
        //批量添加题库，添加标签
        questionBankQuestionsService.deleteExamQuestionBankQuestionsByQuestionId(params.getId());
        insertBatchBankQuestion(question, params.getBanks());
        labelQuestionsService.deleteExamLabelQuestionsByQuestionId(params.getId());
        insertBatchLabelQuestion(question, params.getLabels());
        //多选题或者单选题 添加选项并修改题目答案绑定
        optionService.deleteExamOptionByQuestionId(params.getId());
        insertOptions(params.getList(), question);
        return count;
    }

    @Override
    public Map uploadExamQuestions(MultipartFile file) throws IOException {
        Map map = new HashMap();
        try {
            //上传文件
            String fileName = FileUploadUtils.upload(file);
            String filePath = RuoYiConfig.getProfile() + StringUtils.substringAfter(fileName, Constants.RESOURCE_PREFIX);
            //读取文件
            ExcelUtil<ExamQuestionVo> util = new ExcelUtil<ExamQuestionVo>(ExamQuestionVo.class);
            List<ExamQuestionVo> questionList = util.importExcel("试题", new FileInputStream(filePath));

            ExcelUtil<ExamOptionVo> util2 = new ExcelUtil<ExamOptionVo>(ExamOptionVo.class);
            List<ExamOptionVo> optionList = util2.importExcel("选项", new FileInputStream(filePath));
            Map<String, List<ExamOptionVo>> queOptionsMap = new HashMap<>();
            Map<String, String> typeMap = new HashMap<>();
            typeMap.put("1", QuestionTypeEnum.SELECT.getCode());
            typeMap.put("2", QuestionTypeEnum.SELECTS.getCode());
            typeMap.put("3", QuestionTypeEnum.VERDICT.getCode());
            List<Integer> bankIds = new ArrayList<>();
            bankIds.add(79);
            List<String> newLabelList = new ArrayList<>();
            List<Integer> labels = new ArrayList<>();

            List<ExamOptionVo> queOptions;
            if(optionList != null) {
                for(ExamOptionVo optionVo : optionList) {
                    queOptions = queOptionsMap.get(optionVo.getQuestionId());
                    if(queOptions == null) {
                        queOptions = new ArrayList<>();
                    }
                    queOptions.add(optionVo);
                    queOptionsMap.put(optionVo.getQuestionId(), queOptions);
                }
            }
            ExamQuestionsDto.Add.Input params;
            List<ExamOptionDto.Add.Input> opList;
            ExamOptionDto.Add.Input option;
            for(ExamQuestionVo vo : questionList) {
                try {
                    params = new ExamQuestionsDto.Add.Input();
                    params.setName(vo.getName());
                    params.setSource(vo.getSource());
                    params.setType(typeMap.get(vo.getType()));
                    if(params.getType().equals(QuestionTypeEnum.VERDICT.getCode())) {
                        params.setJudgeAnswer(vo.getJudgeAnswer());
                    } else {
                        opList = new ArrayList<>();
                        queOptions = queOptionsMap.get(vo.getStrId());
                        if(queOptions != null) {
                            for(ExamOptionVo optionVo : queOptions) {
                                option = new ExamOptionDto.Add.Input();
                                option.setName(optionVo.getOptionContent());
                                option.setOption(optionVo.getOption());
                                opList.add(option);
                            }
                        }
                        params.setList(opList);
                        params.setSelectAnswer(vo.getAnswer());
                    }
                    params.setBanks(bankIds);
                    params.setNewLabelList(newLabelList);
                    params.setLabels(labels);
                    params.setIsEnable(0);
                    addExamQuestions(params);
                }catch (Exception e){

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return map;
    }

    /*批量添加实体与题库的关系*/
    private void insertBatchBankQuestion(ExamQuestions question, List<Integer> bankIds) {
        if (bankIds.size() > 0) {
            List<ExamQuestionBankQuestions> list = bankIds.stream().map(item -> {
                ExamQuestionBankQuestions entity = new ExamQuestionBankQuestions();
                entity.setExaminationQuestionId(question.getId());
                entity.setQuestionBankId(item.longValue());
                return entity;
            }).collect(Collectors.toList());
            System.out.println(list);
            int count = questionBankQuestionsService.insertBatchExamQuestionBankQuestions(list);
            if (count != bankIds.size()) {
                throw new RuntimeException("添加题库与试题关系失败");
            }
        }
    }

    /*批量添加试题与标签的关系*/
    private void insertBatchLabelQuestion(ExamQuestions question, List<Integer> labelIds) {
        if (labelIds.size() > 0) {
            List<ExamLabelQuestions> list = labelIds.stream().map(item -> {
                ExamLabelQuestions entity = new ExamLabelQuestions();
                entity.setExaminationQuestionsId(question.getId());
                entity.setLabelId(item.longValue());
                return entity;
            }).collect(Collectors.toList());
            int count = labelQuestionsService.insertBatchExamLabelQuestions(list);
            if (count != labelIds.size()) {
                throw new RuntimeException("添加标签与试题关系失败");
            }
        }
    }

    /*多选题添加选项*/
    private void insertOptions(List<ExamOptionDto.Add.Input> optionsList, ExamQuestions question) {
        if (optionsList.size() > 0) {
            //调用批量添加选项的方法，整理初始化数据
            List<ExamOption> optionList = new ArrayList<>();
            for (ExamOptionDto.Add.Input item : optionsList) {
                ExamOption option = new ExamOption();
                //设置试题的主键
                option.setOptionId(question.getId());
                option.setOptionContent(item.getName());
                int i = optionService.insertExamOption(option);
                optionList.add(option);
            }
            //添加后 修改题目的正确答案 绑定选项的id
            this.updateOption(optionsList,optionList, question);
        }
    }

    /*多选题单选题在添加完选项后 修改题目的正确答案进行绑定*/
    public void updateOption(List<ExamOptionDto.Add.Input> optionsList, List<ExamOption> optionList, ExamQuestions question) {
        List<ExamOption> list = optionList.stream().map(item -> {
            optionsList.stream().forEach(obj -> {
                if (item.getOptionContent().equals(obj.getName())) {
                    item.setOptionContent(obj.getOption());
                }
            });
            return item;
        }).collect(Collectors.toList());
        //修改question 匹配正确的试题选项
        String answer = "";
        int index = 0;
        for (ExamOption item : list) {
            /*if(params.getType().equals(QuestionTypeEnum.SELECT.getCode())){
                //不需要再次循环 直接进行替换即可
                if(item.getOptionContent().equals(question.getSelectAnswer())){
                    question.setSelectAnswer(item.getId().toString());
                }
            }else if(params.getType().equals(QuestionTypeEnum.SELECTS.getCode())){
                //多选，做切割，数组循环
                List<String> answerList = Arrays.asList(question.getSelectAnswer().split(","));
                for(String str : answerList){
                    if(str.equals(item.getOptionContent())){
                        answer= index==0?item.getId().toString():answer+","+item.getId();
                        index=index+1;
                    }
                }
            }*/
            //多选，做切割，数组循环
            List<String> answerList = Arrays.asList(question.getSelectAnswer().split(","));
            for (String str : answerList) {
                if (str.equals(item.getOptionContent())) {
                    answer = index == 0 ? item.getId().toString() : answer + "," + item.getId();
                    index = index + 1;
                }
            }
        }
        question.setSelectAnswer(answer);
        int count = this.updateExamQuestions(question);
        if (count < 1) {
            throw new RuntimeException("修改题目正确答案失败");
        }
    }

    /**
     * 修改试题
     *
     * @param examQuestions 试题
     * @return 结果
     */
    @Override
    public int updateExamQuestions(ExamQuestions examQuestions) {
        examQuestions.setUpdateTime(DateUtils.getNowDate());
        LoginUser user = SecurityUtils.getLoginUser();
        examQuestions.setUpdateBy(user.getUsername());
        return examQuestionsMapper.updateExamQuestions(examQuestions);
    }

    /**
     * 批量删除试题
     *
     * @param ids 需要删除的试题主键
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsByIds(Long[] ids) {
        return examQuestionsMapper.deleteExamQuestionsByIds(ids);
    }

    /**
     * 删除试题信息
     *
     * @param id 试题主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteExamQuestionsById(Long id) {
        //有被引用的不允许改
        ExamPaperQuestions query = new ExamPaperQuestions();
        query.setExaminationQuestionsId(id);
        List<ExamPaperQuestions> useList = paperQuestionsService.selectExamPaperQuestionsList(query);
        if(!useList.isEmpty()){
            throw new RuntimeException("该试题已被引用，不允许修改及删除！");
        }
        //删除选项
        optionService.deleteExamOptionByQuestionId(id);
        //删除标签绑定
        labelQuestionsService.deleteExamLabelQuestionsByQuestionId(id);
        //删除题库绑定
        questionBankQuestionsService.deleteExamQuestionBankQuestionsByQuestionId(id);
        //删除题目
        return examQuestionsMapper.deleteExamQuestionsById(id);
    }
}
