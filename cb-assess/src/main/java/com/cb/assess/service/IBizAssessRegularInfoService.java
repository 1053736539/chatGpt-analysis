package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.VAssessmentProcess;
import com.cb.assess.domain.vo.AssessRegularParam;

/**
 * 平时考核登记Service接口
 *
 * @author ouyang
 * @date 2023-11-09
 */
public interface IBizAssessRegularInfoService {
    /**
     * 查询平时考核登记
     *
     * @param id 平时考核登记ID
     * @return 平时考核登记
     */
    public BizAssessRegularInfo selectBizAssessRegularInfoById(String id);

    public BizAssessRegularInfo selectBizAssessRegularInfoByUserTagId(String userTagId);

    /**
     * 查询平时考核登记列表
     *
     * @return 平时考核登记集合
     */
    public List<BizAssessRegularInfo> selectBizAssessRegularInfoList();

    /**
     * 新增平时考核登记
     *
     * @param bizAssessRegularInfo 平时考核登记
     * @return 结果
     */
    public int insertBizAssessRegularInfo(BizAssessRegularInfo bizAssessRegularInfo);

    /**
     * 修改平时考核登记
     *
     * @param bizAssessRegularInfo 平时考核登记
     * @return 结果
     */
    public int updateBizAssessRegularInfo(BizAssessRegularInfo bizAssessRegularInfo);


    public List<BizAssessRegularInfo> selectNeedLeaderAuditList(BizAssessRegularInfo regular);

    public List<BizAssessRegularInfo> selectNeedOrganList(BizAssessRegularInfo regular);



    public int fillInComments(BizAssessRegularInfo bizAssessRegularInfo);

    public int batchFillInComments(AssessRegularParam param);

    public int deleteRegularInfo(List<String> userTagIds);
}
