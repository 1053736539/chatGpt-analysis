package com.cb.assess.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cb.assess.domain.BizAssessAnnualReviewTypeResult;
import com.cb.assess.domain.vo.BizAssessAnnualReviewTypeResultVo;
import com.cb.assess.domain.vo.RegularAssessmentVo;
import com.cb.assess.mapper.BizAssessAnnualReviewTypeResultMapper;
import com.cb.assess.service.BizAssessAnnualReviewStatisticsService;
import com.cb.assess.service.IBizAssessAnnualReviewTypeResultService;
import com.cb.assess.utils.CycleUtil;
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
 * AB类评分结果，Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-16
 */
@Service
public class BizAssessAnnualReviewTypeResultServiceImpl implements IBizAssessAnnualReviewTypeResultService
{
    @Autowired
    private BizAssessAnnualReviewTypeResultMapper bizAssessAnnualReviewTypeResultMapper;
    @Autowired
    private ISysNoticeService noticeService;

    @Resource
    private BizAssessAnnualReviewStatisticsService bizAssessAnnualReviewStatisticsService;
    /**
     * 查询AB类评分结果，
     * 
     * @param id AB类评分结果，ID
     * @return AB类评分结果，
     */
    @Override
    public BizAssessAnnualReviewTypeResult selectBizAssessAnnualReviewTypeResultById(String id)
    {
        return bizAssessAnnualReviewTypeResultMapper.selectBizAssessAnnualReviewTypeResultById(id);
    }

    /**
     * 查询AB类评分结果，列表
     * 
     * @param bizAssessAnnualReviewTypeResult AB类评分结果，
     * @return AB类评分结果，
     */
    @Override
    public List<BizAssessAnnualReviewTypeResultVo> selectBizAssessAnnualReviewTypeResultList(BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResult)
    {
        List<BizAssessAnnualReviewTypeResultVo> bizAssessAnnualReviewTypeResultVos = bizAssessAnnualReviewTypeResultMapper.selectBizAssessAnnualReviewTypeResultList(bizAssessAnnualReviewTypeResult);
        //拿平时考核
        List<Long> collect = bizAssessAnnualReviewTypeResultVos.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        List<RegularAssessmentVo> regularAssessmentVos = bizAssessAnnualReviewStatisticsService.regularAssessment(collect,bizAssessAnnualReviewTypeResult.getAssessmentYear());
        //设置平时考核
        for (BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResultVo : bizAssessAnnualReviewTypeResultVos) {
            List<RegularAssessmentVo> regularAssessmentVos1 = new ArrayList<>();
            for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                if (bizAssessAnnualReviewTypeResultVo.getUserId().equals(regularAssessmentVo.getUserId())&&bizAssessAnnualReviewTypeResultVo.getAssessmentYear().equals(regularAssessmentVo.getYear()))
                    regularAssessmentVos1.add(regularAssessmentVo);
            }
            bizAssessAnnualReviewTypeResultVo.setRegularAssessmentVos(regularAssessmentVos1);
        }
        return bizAssessAnnualReviewTypeResultVos;
    }

    /**
     * 新增AB类评分结果，
     * 
     * @param bizAssessAnnualReviewTypeResult AB类评分结果，
     * @return 结果
     */
    @Override
    public int insertBizAssessAnnualReviewTypeResult(BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult)
    {
        bizAssessAnnualReviewTypeResult.setCreateTime(DateUtils.getNowDate());
        bizAssessAnnualReviewTypeResult.setCreateBy(SecurityUtils.getUsername());
        bizAssessAnnualReviewTypeResult.setId(UUID.randomUUID().toString());
        bizAssessAnnualReviewTypeResult.setIsPublicity("0");
        bizAssessAnnualReviewTypeResult.setCanEdit("1");
        return bizAssessAnnualReviewTypeResultMapper.insertBizAssessAnnualReviewTypeResult(bizAssessAnnualReviewTypeResult);
    }

    /**
     * 修改AB类评分结果，
     * 
     * @param bizAssessAnnualReviewTypeResult AB类评分结果，
     * @return 结果
     */
    @Override
    public int updateBizAssessAnnualReviewTypeResult(BizAssessAnnualReviewTypeResult bizAssessAnnualReviewTypeResult)
    {
        bizAssessAnnualReviewTypeResult.setUpdateTime(DateUtils.getNowDate());
        bizAssessAnnualReviewTypeResult.setUpdateBy(SecurityUtils.getUsername());
        return bizAssessAnnualReviewTypeResultMapper.updateBizAssessAnnualReviewTypeResult(bizAssessAnnualReviewTypeResult);
    }

    /**
     * 批量删除AB类评分结果，
     * 
     * @param ids 需要删除的AB类评分结果，ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualReviewTypeResultByIds(String[] ids)
    {
        return bizAssessAnnualReviewTypeResultMapper.deleteBizAssessAnnualReviewTypeResultByIds(ids);
    }

    /**
     * 删除AB类评分结果，信息
     * 
     * @param id AB类评分结果，ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessAnnualReviewTypeResultById(String id)
    {
        return bizAssessAnnualReviewTypeResultMapper.deleteBizAssessAnnualReviewTypeResultById(id);
    }

    @Override
    public void publicityAnnualReviewTypeResult(String year) {
        bizAssessAnnualReviewTypeResultMapper.updateToPublicity("1",year);
        bizAssessAnnualReviewTypeResultMapper.updateToCannotEdit(year);
//        BizAssessPersonalSummary bizAssessPersonalSummary = selectBizAssessPersonalSummaryById(id);
//        if(null == bizAssessPersonalSummary){
//            throw new CustomException("信息不存在，请刷新重试");
//        }
//
        SysNotice notice = new SysNotice();
        String title = StringUtils.format("{}年度机关事业单位考核优秀名单公示",year);
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        String bizUrl = NoticeBizUrlTpl.ANNUAL_REVIEW_VIEW.build(year);
        notice.setBizUrl(bizUrl);
        noticeService.insertNotice(notice);
    }

    @Override
    public List<BizAssessAnnualReviewTypeResultVo> setIndex(List<BizAssessAnnualReviewTypeResultVo> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIndex(String.valueOf(i+1));
        }
        return list;
    }

    @Override
    public List<BizAssessAnnualReviewTypeResultVo> setRegularAssessment(List<BizAssessAnnualReviewTypeResultVo> list) {
        for (BizAssessAnnualReviewTypeResultVo bizAssessAnnualReviewTypeResultVo : list) {
            //平时考核
            List<RegularAssessmentVo> regularAssessmentVos = bizAssessAnnualReviewTypeResultVo.getRegularAssessmentVos();
            if (null != regularAssessmentVos && !regularAssessmentVos.isEmpty()) {
                for (RegularAssessmentVo regularAssessmentVo : regularAssessmentVos) {
                    String s = CycleUtil.parseCycle(regularAssessmentVo.getCycle(), regularAssessmentVo.getCycleDesc());
                    if (null != s && s.contains("第一")) {
                        bizAssessAnnualReviewTypeResultVo.setQuarter1(regularAssessmentVo.getGrade());
                    }
                    if (null != s && s.contains("第二")) {
                        bizAssessAnnualReviewTypeResultVo.setQuarter2(regularAssessmentVo.getGrade());
                    }
                    if (null != s && s.contains("第三")) {
                        bizAssessAnnualReviewTypeResultVo.setQuarter3(regularAssessmentVo.getGrade());
                    }
                    if (null != s && s.contains("第四")) {
                        bizAssessAnnualReviewTypeResultVo.setQuarter4(regularAssessmentVo.getGrade());
                    }
                }
            }
        }
       return list;
    }
}
