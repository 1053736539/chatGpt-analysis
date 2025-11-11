package com.cb.assess.service.impl;

import com.cb.assess.domain.BizAssessPersonalSummary;
import com.cb.assess.domain.vo.BizAssessPersonalSummaryVo;
import com.cb.assess.mapper.BizAssessPersonalSummaryMapper;
import com.cb.assess.service.IBizAssessPersonalSummaryService;
import com.cb.common.constant.NoticeBizUrlTpl;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.system.domain.SysNotice;
import com.cb.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 个人总结Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
@Service
public class BizAssessPersonalSummaryServiceImpl implements IBizAssessPersonalSummaryService
{
    @Autowired
    private BizAssessPersonalSummaryMapper bizAssessPersonalSummaryMapper;
    @Autowired
    private ISysNoticeService noticeService;
    /**
     * 查询个人总结
     * 
     * @param id 个人总结ID
     * @return 个人总结
     */
    @Override
    public BizAssessPersonalSummary selectBizAssessPersonalSummaryById(String id)
    {
        return bizAssessPersonalSummaryMapper.selectBizAssessPersonalSummaryById(id);
    }

    /**
     * 查询个人总结列表
     * 
     * @param bizAssessPersonalSummary 个人总结
     * @return 个人总结
     */
    @Override
    public List<BizAssessPersonalSummaryVo> selectBizAssessPersonalSummaryList(BizAssessPersonalSummaryVo bizAssessPersonalSummary)
    {
        return bizAssessPersonalSummaryMapper.selectBizAssessPersonalSummaryList(bizAssessPersonalSummary);
    }


    @Override
    public List<BizAssessPersonalSummaryVo> selectYears()
    {
        return bizAssessPersonalSummaryMapper.selectYears();
    }

    /**
     * 新增个人总结
     * 
     * @param bizAssessPersonalSummary 个人总结
     * @return 结果
     */
    @Override
    public int insertBizAssessPersonalSummary(BizAssessPersonalSummary bizAssessPersonalSummary)
    {

        bizAssessPersonalSummary.setCreateTime(DateUtils.getNowDate());

        bizAssessPersonalSummary.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        bizAssessPersonalSummary.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        bizAssessPersonalSummary.setDelFlag("0");
        bizAssessPersonalSummary.setIsReport("0");
        bizAssessPersonalSummary.setIsPublicity("0");
        bizAssessPersonalSummary.setAuditFlag("1");
        boolean exist = bizAssessPersonalSummaryMapper.checkSummaryExist(bizAssessPersonalSummary);
        if(exist){
            throw new RuntimeException("当前年度总结已存在");
        }
        bizAssessPersonalSummary.setId(UUID.randomUUID().toString());
        return bizAssessPersonalSummaryMapper.insertBizAssessPersonalSummary(bizAssessPersonalSummary);
    }

    /**
     * 修改个人总结
     * 
     * @param bizAssessPersonalSummary 个人总结
     * @return 结果
     */
    @Override
    public int updateBizAssessPersonalSummary(BizAssessPersonalSummary bizAssessPersonalSummary)
    {
        bizAssessPersonalSummary.setUpdateTime(DateUtils.getNowDate());
        boolean exist = bizAssessPersonalSummaryMapper.checkSummaryExist(bizAssessPersonalSummary);
        if(exist){
            throw new RuntimeException("当前年度总结已存在");
        }
        return bizAssessPersonalSummaryMapper.updateBizAssessPersonalSummary(bizAssessPersonalSummary);
    }

    /**
     * 批量删除个人总结
     * 
     * @param ids 需要删除的个人总结ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessPersonalSummaryByIds(String[] ids)
    {
        return bizAssessPersonalSummaryMapper.deleteBizAssessPersonalSummaryByIds(ids);
    }

    /**
     * 删除个人总结信息
     * 
     * @param id 个人总结ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessPersonalSummaryById(String id)
    {
        return bizAssessPersonalSummaryMapper.deleteBizAssessPersonalSummaryById(id);
    }
    @Override
    public void publishPersonalSummary2Notice(String year) {
        bizAssessPersonalSummaryMapper.updateToPublicity(year);
//        BizAssessPersonalSummary bizAssessPersonalSummary = selectBizAssessPersonalSummaryById(id);
//        if(null == bizAssessPersonalSummary){
//            throw new CustomException("信息不存在，请刷新重试");
//        }
//
        SysNotice notice = new SysNotice();
        String title = StringUtils.format("{}年度机关事业单位负责人总结公示",year);
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        String bizUrl = NoticeBizUrlTpl.PERSONAL_SUMMARY.build(year);
        notice.setBizUrl(bizUrl);
        noticeService.insertNotice(notice);
    }
}
