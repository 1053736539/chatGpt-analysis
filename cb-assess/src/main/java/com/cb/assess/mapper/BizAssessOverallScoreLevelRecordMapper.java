package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessOverallScoreLevelRecord;
import com.cb.assess.domain.vo.PersonalAssessResult;
import org.apache.ibatis.annotations.Param;

/**
 * 平时考核最终综合得分及建议评定等次记录Mapper接口
 *
 * @author ouyang
 * @date 2023-12-12
 */
public interface BizAssessOverallScoreLevelRecordMapper {
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
     * 删除平时考核最终综合得分及建议评定等次记录
     *
     * @param id 平时考核最终综合得分及建议评定等次记录ID
     * @return 结果
     */
    public int deleteBizAssessOverallScoreLevelRecordById(String id);

    /**
     * 批量删除平时考核最终综合得分及建议评定等次记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessOverallScoreLevelRecordByIds(String[] ids);

    public boolean checkExist(@Param("id") String id, @Param("userId") Long userId, @Param("quarter") String quarter);

    public BizAssessOverallScoreLevelRecord selectOneLevelRecord(@Param("userId") Long userId, @Param("quarter") String quarter);

    public List<BizAssessOverallScoreLevelRecord> selectVEvaluationGradeAsLevelRecords(@Param("taskId") String taskId,
                                                                                       @Param("year") String year,
                                                                                       @Param("quarter") String quarter);
    public List<PersonalAssessResult> selectPersonalAssessResult(PersonalAssessResult result);
}
