package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessOverallScoreLevelRecord;
import com.cb.assess.domain.vo.OrdinaryAssessParamVo;
import com.cb.assess.domain.vo.PersonalAssessResult;

/**
 * 平时考核最终综合得分及建议评定等次记录Service接口
 *
 * @author ouyang
 * @date 2023-12-12
 */
public interface IBizAssessOverallScoreLevelRecordService {
    /**
     * 查询平时考核最终综合得分及建议评定等次记录
     *
     * @param id 平时考核最终综合得分及建议评定等次记录ID
     * @return 平时考核最终综合得分及建议评定等次记录
     */
    public BizAssessOverallScoreLevelRecord selectBizAssessOverallScoreLevelRecordById(String id);

    /**
     * 查询平时考核最终综合得分及建议评定等次记录列表
     *
     * @param bizAssessOverallScoreLevelRecord 平时考核最终综合得分及建议评定等次记录
     * @return 平时考核最终综合得分及建议评定等次记录集合
     */
    public List<BizAssessOverallScoreLevelRecord> selectBizAssessOverallScoreLevelRecordList(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord);

    /**
     * 新增平时考核最终综合得分及建议评定等次记录
     *
     * @param bizAssessOverallScoreLevelRecord 平时考核最终综合得分及建议评定等次记录
     * @return 结果
     */
    public int insertBizAssessOverallScoreLevelRecord(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord);

    /**
     * 修改平时考核最终综合得分及建议评定等次记录
     *
     * @param bizAssessOverallScoreLevelRecord 平时考核最终综合得分及建议评定等次记录
     * @return 结果
     */
    public int updateBizAssessOverallScoreLevelRecord(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord);

    /**
     * 批量删除平时考核最终综合得分及建议评定等次记录
     *
     * @param ids 需要删除的平时考核最终综合得分及建议评定等次记录ID
     * @return 结果
     */
    public int deleteBizAssessOverallScoreLevelRecordByIds(String[] ids);

    /**
     * 删除平时考核最终综合得分及建议评定等次记录信息
     *
     * @param id 平时考核最终综合得分及建议评定等次记录ID
     * @return 结果
     */
    public int deleteBizAssessOverallScoreLevelRecordById(String id);

    public Boolean checkExist(String id, Long userId, String quarter);

    public String importData(List<BizAssessOverallScoreLevelRecord> list, boolean updateSupport, String operateName);

    public BizAssessOverallScoreLevelRecord selectOneLevelRecord(Long userId, String quarter);

    @Deprecated
    public void publish2Notice(OrdinaryAssessParamVo param);


    public List<BizAssessOverallScoreLevelRecord> selectVEvaluationGradeAsLevelRecords(String taskId, String year, String quarter);

    public void batchInsertOrUpdateLevelRecord(List<BizAssessOverallScoreLevelRecord> list);


    public List<PersonalAssessResult> selectPersonalAssessResult(PersonalAssessResult result);

}
