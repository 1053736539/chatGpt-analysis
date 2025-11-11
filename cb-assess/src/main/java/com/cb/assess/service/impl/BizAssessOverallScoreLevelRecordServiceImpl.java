package com.cb.assess.service.impl;

import java.util.List;

import com.cb.assess.domain.BizAccessQuotaAllocateInfo;
import com.cb.assess.domain.vo.OrdinaryAssessParamVo;
import com.cb.assess.domain.vo.PersonalAssessResult;
import com.cb.assess.utils.CycleUtil;
import com.cb.common.constant.NoticeBizUrlTpl;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.domain.SysNotice;
import com.cb.system.service.ISysNoticeService;
import com.cb.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessOverallScoreLevelRecordMapper;
import com.cb.assess.domain.BizAssessOverallScoreLevelRecord;
import com.cb.assess.service.IBizAssessOverallScoreLevelRecordService;

/**
 * 平时考核最终综合得分及建议评定等次记录Service业务层处理
 *
 * @author ouyang
 * @date 2023-12-12
 */
@Service
public class BizAssessOverallScoreLevelRecordServiceImpl implements IBizAssessOverallScoreLevelRecordService {
    private static final Logger log = LoggerFactory.getLogger(BizAssessOverallScoreLevelRecordServiceImpl.class);
    @Autowired
    private BizAssessOverallScoreLevelRecordMapper bizAssessOverallScoreLevelRecordMapper;
    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 查询平时考核最终综合得分及建议评定等次记录
     *
     * @param id 平时考核最终综合得分及建议评定等次记录ID
     * @return 平时考核最终综合得分及建议评定等次记录
     */
    @Override
    public BizAssessOverallScoreLevelRecord selectBizAssessOverallScoreLevelRecordById(String id) {
        return bizAssessOverallScoreLevelRecordMapper.selectBizAssessOverallScoreLevelRecordById(id);
    }

    /**
     * 查询平时考核最终综合得分及建议评定等次记录列表
     *
     * @param bizAssessOverallScoreLevelRecord 平时考核最终综合得分及建议评定等次记录
     * @return 平时考核最终综合得分及建议评定等次记录
     */
    @Override
    public List<BizAssessOverallScoreLevelRecord> selectBizAssessOverallScoreLevelRecordList(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord) {
        return bizAssessOverallScoreLevelRecordMapper.selectBizAssessOverallScoreLevelRecordList(bizAssessOverallScoreLevelRecord);
    }

    /**
     * 新增平时考核最终综合得分及建议评定等次记录
     *
     * @param bizAssessOverallScoreLevelRecord 平时考核最终综合得分及建议评定等次记录
     * @return 结果
     */
    @Override
    public int insertBizAssessOverallScoreLevelRecord(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord) {
        bizAssessOverallScoreLevelRecord.setId(IdUtils.randomUUID());
        bizAssessOverallScoreLevelRecord.setCreateBy(SecurityUtils.getUsername());
        bizAssessOverallScoreLevelRecord.setCreateTime(DateUtils.getNowDate());
        return bizAssessOverallScoreLevelRecordMapper.insertBizAssessOverallScoreLevelRecord(bizAssessOverallScoreLevelRecord);
    }

    /**
     * 修改平时考核最终综合得分及建议评定等次记录
     *
     * @param bizAssessOverallScoreLevelRecord 平时考核最终综合得分及建议评定等次记录
     * @return 结果
     */
    @Override
    public int updateBizAssessOverallScoreLevelRecord(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord) {
        bizAssessOverallScoreLevelRecord.setUpdateBy(SecurityUtils.getUsername());
        bizAssessOverallScoreLevelRecord.setUpdateTime(DateUtils.getNowDate());
        return bizAssessOverallScoreLevelRecordMapper.updateBizAssessOverallScoreLevelRecord(bizAssessOverallScoreLevelRecord);
    }

    /**
     * 批量删除平时考核最终综合得分及建议评定等次记录
     *
     * @param ids 需要删除的平时考核最终综合得分及建议评定等次记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessOverallScoreLevelRecordByIds(String[] ids) {
        return bizAssessOverallScoreLevelRecordMapper.deleteBizAssessOverallScoreLevelRecordByIds(ids);
    }

    /**
     * 删除平时考核最终综合得分及建议评定等次记录信息
     *
     * @param id 平时考核最终综合得分及建议评定等次记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessOverallScoreLevelRecordById(String id) {
        return bizAssessOverallScoreLevelRecordMapper.deleteBizAssessOverallScoreLevelRecordById(id);
    }

    @Override
    public Boolean checkExist(String id, Long userId, String quarter) {
        return bizAssessOverallScoreLevelRecordMapper.checkExist(id, userId, quarter);
    }

    @Override
    public String importData(List<BizAssessOverallScoreLevelRecord> list, boolean updateSupport, String operateName) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BizAssessOverallScoreLevelRecord item : list) {
            try {
                // 验证是否存在这个季度数据
                BizAssessOverallScoreLevelRecord record = this.selectOneLevelRecord(item.getAssessedUserId(), item.getQuarter());
                if (StringUtils.isNull(record)) {
                    this.insertBizAssessOverallScoreLevelRecord(item);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、考核数据 " + item.getUserName() + " 导入成功");
                } else if (updateSupport) {
                    item.setId(record.getId());
                    this.updateBizAssessOverallScoreLevelRecord(item);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、考核数据 " + item.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、考核数据 " + item.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、考核数据 " + item.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public BizAssessOverallScoreLevelRecord selectOneLevelRecord(Long userId, String quarter) {
        return bizAssessOverallScoreLevelRecordMapper.selectOneLevelRecord(userId, quarter);
    }

    @Override
    public void publish2Notice(OrdinaryAssessParamVo param) {
        String year = param.getYear();
        String quarter = param.getQuarter();
        SysNotice notice = new SysNotice();
        String title = StringUtils.format("{}"+NoticeBizUrlTpl.ORDINARY_ASSESSMENT.getDesc(), CycleUtil.parseCycle("2",quarter));
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        String bizUrl = NoticeBizUrlTpl.ORDINARY_ASSESSMENT.build(year,quarter);
        notice.setBizUrl(bizUrl);
        noticeService.insertNotice(notice);
    }

    @Override
    public List<BizAssessOverallScoreLevelRecord> selectVEvaluationGradeAsLevelRecords(String taskId, String year, String quarter) {
        return bizAssessOverallScoreLevelRecordMapper.selectVEvaluationGradeAsLevelRecords(taskId, year, quarter);
    }

    @Override
    public void batchInsertOrUpdateLevelRecord(List<BizAssessOverallScoreLevelRecord> list) {
        for (BizAssessOverallScoreLevelRecord item : list) {
            try {
                // 验证是否存在这个季度数据
                BizAssessOverallScoreLevelRecord record = this.selectOneLevelRecord(item.getAssessedUserId(), item.getQuarter());
                if (StringUtils.isNull(record)) {
                    item.setId(IdUtils.randomUUID());
                    item.setCreateBy("admin");
                    item.setCreateTime(DateUtils.getNowDate());
                    bizAssessOverallScoreLevelRecordMapper.insertBizAssessOverallScoreLevelRecord(item);
                } else {
                    item.setId(record.getId());
                    item.setUpdateBy("admin");
                    item.setUpdateTime(DateUtils.getNowDate());
                    bizAssessOverallScoreLevelRecordMapper.updateBizAssessOverallScoreLevelRecord(item);
                }
            } catch (Exception e) {
                log.error("数据处理失败", e);
            }
        }
    }

    @Override
    public List<PersonalAssessResult> selectPersonalAssessResult(PersonalAssessResult result) {
        return bizAssessOverallScoreLevelRecordMapper.selectPersonalAssessResult(result);
    }
}
