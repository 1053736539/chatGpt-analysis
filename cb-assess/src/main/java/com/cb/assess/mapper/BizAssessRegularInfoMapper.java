package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessRegularInfo;
import com.cb.assess.domain.VAssessmentProcess;
import com.cb.assess.domain.VRegularFillInfo;
import com.cb.assess.domain.vo.AssessRegularVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平时考核登记Mapper接口
 *
 * @author ouyang
 * @date 2023-11-09
 */
public interface BizAssessRegularInfoMapper {
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
     * @param userId
     * @return 平时考核登记集合
     */
    public List<BizAssessRegularInfo> selectBizAssessRegularInfoList(Long userId);



    public List<VRegularFillInfo> selectVRegularFillInfoList(VRegularFillInfo fillInfo);

    public List<VRegularFillInfo> selectVRegularFillInfoListByTskIds(@Param("list") List<String> taskIds);

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

    public BizAssessRegularInfo selectBizAssessRegularInfoByTaskId(@Param("taskId") String taskId, @Param("userId") Long userId);

    /**
     *  获取待审核填写意见列表
     * @param jobLevels
     * @param deptIds
     * @param exclude true 排除   false 获取
     * @return
     */
    public List<BizAssessRegularInfo> selectNeedLeaderAuditList(@Param("jobLevels") List<String> jobLevels,
                                                                @Param("deptIds") List<Long> deptIds,
                                                                @Param("step") Integer step,
                                                                @Param("auditStatus") String auditStatus,
                                                                @Param("userName") String userName,
                                                                @Param("exclude") boolean exclude);

    /**
     * 待审定意见填写列表
     * @param jobLevels
     * @param step
     * @return
     */
    public List<BizAssessRegularInfo> selectNeedOrganFillingList(@Param("jobLevels") List<String> jobLevels,
                                                                @Param("step") Integer step);

    public List<BizAssessRegularInfo> selectNeedApprovalSummaryList(@Param("vo")VAssessmentProcess.ProcessVo processVo,
                                                                    @Param("jobLevels") List<String> jobLevels,
                                                                    @Param("step") Integer step);

    public List<BizAssessRegularInfo> selectNeedAuditSummaryList(@Param("vo")VAssessmentProcess.ProcessVo processVo,
                                                                    @Param("jobLevels") List<String> jobLevels,
                                                                   @Param("deptIds") List<Long> deptIds,
                                                                    @Param("step") Integer step,
                                                                    @Param("exclude") boolean exclude);

    @Deprecated
    public List<BizAssessRegularInfo> selectNeedAdminFillingList(Integer step);

    public int fillInComments(BizAssessRegularInfo bizAssessRegularInfo);


    public AssessRegularVo exportRegular2Word(@Param("taskId") String taskId, @Param("userId") Long userId);


    public int deleteRegularInfo(@Param("list") List<String> userTagIds);
}
