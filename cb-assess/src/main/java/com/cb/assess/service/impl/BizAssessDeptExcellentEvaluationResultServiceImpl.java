package com.cb.assess.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.cb.assess.domain.BizAssessDeptExcellentEvaluationResult;
import com.cb.assess.domain.vo.BizAssessDeptExcellentEvaluationResultVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessDeptExcellentEvaluationResultMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.service.IBizAssessDeptExcellentEvaluationResultService;
import com.cb.common.constant.NoticeBizUrlTpl;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.system.domain.SysNotice;
import com.cb.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 年度处室（单位）年度考核优秀评议(最终部门上报结果)Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-11
 */
@Service
public class BizAssessDeptExcellentEvaluationResultServiceImpl implements IBizAssessDeptExcellentEvaluationResultService
{
    @Autowired
    private BizAssessDeptExcellentEvaluationResultMapper bizAssessDeptExcellentEvaluationResultMapper;
    @Resource
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    @Resource
    private ISysNoticeService noticeService;
    /**
     * 查询年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param id 年度处室（单位）年度考核优秀评议(最终部门上报结果)ID
     * @return 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     */
    @Override
    public BizAssessDeptExcellentEvaluationResult selectBizAssessDeptExcellentEvaluationResultById(String id)
    {
        return bizAssessDeptExcellentEvaluationResultMapper.selectBizAssessDeptExcellentEvaluationResultById(id);
    }

    /**
     * 查询年度处室（单位）年度考核优秀评议(最终部门上报结果)列表
     * 
     * @param bizAssessDeptExcellentEvaluationResult 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * @return 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     */
    @Override
    public List<BizAssessDeptExcellentEvaluationResultVo> selectBizAssessDeptExcellentEvaluationResultList(BizAssessDeptExcellentEvaluationResultVo bizAssessDeptExcellentEvaluationResult)
    {
        List<BizAssessDeptExcellentEvaluationResultVo> list = bizAssessDeptExcellentEvaluationResultMapper.selectBizAssessDeptExcellentEvaluationResultList(bizAssessDeptExcellentEvaluationResult);
//        setRegular(bizAssessDeptExcellentEvaluationResult.getAssessmentYear(),list.stream().map(e -> e.getUserId()).collect(Collectors.toList()),list,BizAssessDeptExcellentEvaluationResultVo.class);
        setRegular(bizAssessDeptExcellentEvaluationResult.getAssessmentYear(),list);
        return list;
    }
    //设置平时考核
    private void setRegular(String  year, List<BizAssessDeptExcellentEvaluationResultVo> bizAssessDeptExcellentEvaluationVos) {
        //拿平时考核
        List<Long> collect = bizAssessDeptExcellentEvaluationVos.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        List<RegularAssessmentVo> regularAssessmentVos = bizAssessAnnualReviewStatisticsService.regularAssessment(collect, year);
        //设置平时考核
        for (BizAssessDeptExcellentEvaluationResultVo assessDeptExcellentEvaluationVo : bizAssessDeptExcellentEvaluationVos) {
            List<RegularAssessmentVo> regularAssessmentVos1 = new ArrayList<>();
            if ("1".equals(assessDeptExcellentEvaluationVo.getIsEntry())){
                String regularAssessment = assessDeptExcellentEvaluationVo.getRegularAssessment();
                BizAssessDeptExcellentEvaluationResultVo bean = JSONUtil.toBean(regularAssessment, BizAssessDeptExcellentEvaluationResultVo.class);
//                BeanUtils.copyProperties(bean,assessDeptExcellentEvaluationVo);
                assessDeptExcellentEvaluationVo.setQuarter1(bean.getQuarter1());
                assessDeptExcellentEvaluationVo.setQuarter2(bean.getQuarter2());
                assessDeptExcellentEvaluationVo.setQuarter3(bean.getQuarter3());
                assessDeptExcellentEvaluationVo.setQuarter4(bean.getQuarter4());
//                System.out.println(bean);
//                regularAssessmentVos1 = objects.toList(RegularAssessmentVo.class);
            }else {
                for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                    if (assessDeptExcellentEvaluationVo.getUserId().equals(regularAssessmentVo.getUserId())&&assessDeptExcellentEvaluationVo.getAssessmentYear().equals(regularAssessmentVo.getYear()))
                        regularAssessmentVos1.add(regularAssessmentVo);
                }
            }

            assessDeptExcellentEvaluationVo.setRegularAssessmentVos(regularAssessmentVos1);
        }
    }
    private <T>  void setRegular(String  year,List<Long> userIds, List<T> list,Class<T> tClass) {
        //拿平时考核
//        tClass
        Class<? super T> superclass = tClass.getSuperclass();
        Field[] declaredFields = superclass.getDeclaredFields();
        Field[] tClassFields = tClass.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(tClassFields));
        fields.addAll(Arrays.asList(declaredFields));
        Map<String, Field> collect = fields.stream().collect(Collectors.toMap(Field::getName, o -> o));
        for (Field field : fields) {
            Class<?> type = field.getType();
            if (type.getName().equals("java.util.List")){

            }
            System.out.println(type.getTypeName());
        }
        List<RegularAssessmentVo> regularAssessmentVos = bizAssessAnnualReviewStatisticsService.regularAssessment(userIds, year);
//        //设置平时考核
        for (T assessDeptExcellentEvaluationVo : list) {
            List<RegularAssessmentVo> regularAssessmentVos1 = new ArrayList<>();
            for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                Field userId = collect.get("userId");
                try {
                    Object o = userId.get(assessDeptExcellentEvaluationVo);
                    if (null!=o) {

                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

//                if (assessDeptExcellentEvaluationVo.getUserId().equals(regularAssessmentVo.getUserId())&&assessDeptExcellentEvaluationVo.getAssessmentYear().equals(regularAssessmentVo.getYear()))
//                    regularAssessmentVos1.add(regularAssessmentVo);
            }
//            assessDeptExcellentEvaluationVo.setRegularAssessmentVos(regularAssessmentVos1);
        }
    }
    /**
     * 新增年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param bizAssessDeptExcellentEvaluationResult 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * @return 结果
     */
    @Override
    public int insertBizAssessDeptExcellentEvaluationResult(BizAssessDeptExcellentEvaluationResult bizAssessDeptExcellentEvaluationResult)
    {
        bizAssessDeptExcellentEvaluationResult.setCreateTime(DateUtils.getNowDate());
        bizAssessDeptExcellentEvaluationResult.setDelFlag("0");
        bizAssessDeptExcellentEvaluationResult.setId(UUID.randomUUID().toString());
        bizAssessDeptExcellentEvaluationResult.setCreateBy(SecurityUtils.getUsername());
        return bizAssessDeptExcellentEvaluationResultMapper.insertBizAssessDeptExcellentEvaluationResult(bizAssessDeptExcellentEvaluationResult);
    }

    /**
     * 修改年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param bizAssessDeptExcellentEvaluationResult 年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * @return 结果
     */
    @Override
    public int updateBizAssessDeptExcellentEvaluationResult(BizAssessDeptExcellentEvaluationResult bizAssessDeptExcellentEvaluationResult)
    {
        bizAssessDeptExcellentEvaluationResult.setUpdateTime(DateUtils.getNowDate());
        bizAssessDeptExcellentEvaluationResult.setUpdateBy(SecurityUtils.getUsername());
        return bizAssessDeptExcellentEvaluationResultMapper.updateBizAssessDeptExcellentEvaluationResult(bizAssessDeptExcellentEvaluationResult);
    }

    /**
     * 批量删除年度处室（单位）年度考核优秀评议(最终部门上报结果)
     * 
     * @param ids 需要删除的年度处室（单位）年度考核优秀评议(最终部门上报结果)ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessDeptExcellentEvaluationResultByIds(String[] ids)
    {
        return bizAssessDeptExcellentEvaluationResultMapper.deleteBizAssessDeptExcellentEvaluationResultByIds(ids);
    }

    /**
     * 删除年度处室（单位）年度考核优秀评议(最终部门上报结果)信息
     * 
     * @param id 年度处室（单位）年度考核优秀评议(最终部门上报结果)ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessDeptExcellentEvaluationResultById(String id)
    {
        return bizAssessDeptExcellentEvaluationResultMapper.deleteBizAssessDeptExcellentEvaluationResultById(id);
    }

    /**
     *
     * @param year 年份
     * @param isExcellent 1是优秀，2是其他
     */
    @Override
    public void push2Publicity(String year, String isExcellent) {
        //为空先不管
        if (StringUtils.isBlank(isExcellent)||StringUtils.isBlank(year))return;
        bizAssessDeptExcellentEvaluationResultMapper.updateToPublicity("1",year,isExcellent);
        SysNotice notice = new SysNotice();
        String title = StringUtils.format("{}年云南省统计局年度考核{}汇总表",year,"1".equals(isExcellent)?"优秀":"");
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        String bizUrl = NoticeBizUrlTpl.EVALUATION_PUBLICITY.build(year,isExcellent);
        notice.setBizUrl(bizUrl);
        noticeService.insertNotice(notice);
    }

    @Override
    public List<BizAssessDeptExcellentEvaluationResultVo> selectYears() {
        return bizAssessDeptExcellentEvaluationResultMapper.selectYears();
    }
}
