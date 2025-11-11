package com.cb.assess.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cb.assess.domain.BizAssessCadreDemocraticRecord;
import com.cb.assess.domain.BizAssessCadreDemocraticTask;
import com.cb.assess.mapper.BizAssessCadreDemocraticTaskMapper;
import com.cb.assess.mapper.BizAssessUserMapper;
import com.cb.assess.service.IBizAssessCadreDemocraticRecordService;
import com.cb.assess.service.IBizAssessCadreDemocraticTaskService;
import com.cb.assess.vo.CadreDemocraticTaskVo;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.deepoove.poi.XWPFTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 年度处级干部民主测评任务Service业务层处理
 * 
 * @author yangixn
 * @date 2023-11-25
 */
@Service
public class BizAssessCadreDemocraticTaskServiceImpl implements IBizAssessCadreDemocraticTaskService 
{
    @Autowired
    private BizAssessCadreDemocraticTaskMapper bizAssessCadreDemocraticTaskMapper;

    @Autowired
    private IBizAssessCadreDemocraticRecordService recordService;

    @Autowired
    private BizAssessUserMapper assessUserMapper;

    /**
     * 查询年度处级干部民主测评任务
     * 
     * @param id 年度处级干部民主测评任务ID
     * @return 年度处级干部民主测评任务
     */
    @Override
    public BizAssessCadreDemocraticTask selectBizAssessCadreDemocraticTaskById(String id)
    {
        BizAssessCadreDemocraticTask task = bizAssessCadreDemocraticTaskMapper.selectBizAssessCadreDemocraticTaskById(id);
        addUserIds(task);
        return task;
    }

    @Override
    public void exportTaskInfoWord(HttpServletResponse response, String taskId, Long userId) {
        BizAssessCadreDemocraticTask task = bizAssessCadreDemocraticTaskMapper.selectBizAssessCadreDemocraticTaskById(taskId);

        BizAssessCadreDemocraticRecord query = new BizAssessCadreDemocraticRecord();
        query.setTaskId(taskId);
        query.setAssessedUserId(userId);
        List<BizAssessCadreDemocraticRecord> list = recordService.selectBizAssessCadreDemocraticRecordList(query);
        if(null == list || list.isEmpty()){
            throw new CustomException("该用户在该任务暂不评测记录");
        } else {
            String userName = list.get(0).getAssessedUserName();
            String userWorkPost = list.get(0).getAssessedUserWorkPost();
            CadreDemocraticTaskVo.assessedUserInfo info = new CadreDemocraticTaskVo.assessedUserInfo();
            info.setUserId(userId);
            info.setUserName(userName);
            info.setUserWorkPost(userWorkPost);

            info.setOverallCount1(list.stream().filter(item->"1".equals(item.getOverallEvaluation())).count());
            info.setOverallCount2(list.stream().filter(item->"2".equals(item.getOverallEvaluation())).count());
            info.setOverallCount3(list.stream().filter(item->"3".equals(item.getOverallEvaluation())).count());
            info.setOverallCount4(list.stream().filter(item->"4".equals(item.getOverallEvaluation())).count());
            info.setDeCount1(list.stream().filter(item->"1".equals(item.getDeEvaluation())).count());
            info.setDeCount2(list.stream().filter(item->"2".equals(item.getDeEvaluation())).count());
            info.setDeCount3(list.stream().filter(item->"3".equals(item.getDeEvaluation())).count());
            info.setDeCount4(list.stream().filter(item->"4".equals(item.getDeEvaluation())).count());
            info.setNengCount1(list.stream().filter(item->"1".equals(item.getNengEvaluation())).count());
            info.setNengCount2(list.stream().filter(item->"2".equals(item.getNengEvaluation())).count());
            info.setNengCount3(list.stream().filter(item->"3".equals(item.getNengEvaluation())).count());
            info.setNengCount4(list.stream().filter(item->"4".equals(item.getNengEvaluation())).count());
            info.setQinCount1(list.stream().filter(item->"1".equals(item.getQinEvaluation())).count());
            info.setQinCount2(list.stream().filter(item->"2".equals(item.getQinEvaluation())).count());
            info.setQinCount3(list.stream().filter(item->"3".equals(item.getQinEvaluation())).count());
            info.setQinCount4(list.stream().filter(item->"4".equals(item.getQinEvaluation())).count());
            info.setJiCount1(list.stream().filter(item->"1".equals(item.getJiEvaluation())).count());
            info.setJiCount2(list.stream().filter(item->"2".equals(item.getJiEvaluation())).count());
            info.setJiCount3(list.stream().filter(item->"3".equals(item.getJiEvaluation())).count());
            info.setJiCount4(list.stream().filter(item->"4".equals(item.getJiEvaluation())).count());
            info.setLianCount1(list.stream().filter(item->"1".equals(item.getLianEvaluation())).count());
            info.setLianCount2(list.stream().filter(item->"2".equals(item.getLianEvaluation())).count());
            info.setLianCount3(list.stream().filter(item->"3".equals(item.getLianEvaluation())).count());
            info.setLianCount4(list.stream().filter(item->"4".equals(item.getLianEvaluation())).count());

            InputStream is = null;

            try {
                ClassPathResource classPathResource = new ClassPathResource("static/word/处级领导干部民主测评统计表导出模板.docx");
                is = classPathResource.getInputStream();

                Map<String, Object> model = BeanUtil.beanToMap(info);
                Set<Map.Entry<String, Object>> entries = model.entrySet();
                entries.forEach(entry->{
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if(key.contains("Count")){
                        if(null != value){
                            Long longVal = (Long) value;
                            if(longVal.equals(0L)){
                                entry.setValue(null);
                            }
                        }
                    }
                });
                model.put("year",task.getYear());
                model.put("nextYear",Integer.parseInt(task.getYear()) + 1);
                XWPFTemplate template = XWPFTemplate.compile(is).render(model);
                template.writeAndClose(response.getOutputStream());
            } catch (Exception e){
                e.printStackTrace();
                throw new CustomException("民主评测导出失败!");
            } finally {
                if(null != is){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    /**
     * 附加用户ids
     * @param task
     */
    private void addUserIds(BizAssessCadreDemocraticTask task){
        String taskId = task.getId();

        BizAssessCadreDemocraticRecord query = new BizAssessCadreDemocraticRecord();
        query.setTaskId(taskId);
        List<BizAssessCadreDemocraticRecord> recordList = recordService.selectBizAssessCadreDemocraticRecordList(query);
        List<Long> assessedUserIdList = recordList.stream().map(BizAssessCadreDemocraticRecord::getAssessedUserId).distinct().collect(Collectors.toList());
        List<Long> voteUserIdList = recordList.stream().map(BizAssessCadreDemocraticRecord::getVoteUserId).distinct().collect(Collectors.toList());
        task.setAssessedUserIds(assessedUserIdList);
        task.setVoteUserIds(voteUserIdList);

        List<CadreDemocraticTaskVo.assessedUserInfo> accessUserInfoList = new LinkedList<>();
        Map<Long, List<BizAssessCadreDemocraticRecord>> accessIdMap = recordList.stream().collect(Collectors.groupingBy(BizAssessCadreDemocraticRecord::getAssessedUserId));
        accessIdMap.entrySet().stream().forEach(entry->{
            Long userId = entry.getKey();
            List<BizAssessCadreDemocraticRecord> list = entry.getValue();
            String userName = list.get(0).getAssessedUserName();
            String userWorkPost = list.get(0).getAssessedUserWorkPost();
            CadreDemocraticTaskVo.assessedUserInfo info = new CadreDemocraticTaskVo.assessedUserInfo();
            info.setUserId(userId);
            info.setUserName(userName);
            info.setUserWorkPost(userWorkPost);

            info.setOverallCount1(list.stream().filter(item->"1".equals(item.getOverallEvaluation())).count());
            info.setOverallCount2(list.stream().filter(item->"2".equals(item.getOverallEvaluation())).count());
            info.setOverallCount3(list.stream().filter(item->"3".equals(item.getOverallEvaluation())).count());
            info.setOverallCount4(list.stream().filter(item->"4".equals(item.getOverallEvaluation())).count());
            info.setDeCount1(list.stream().filter(item->"1".equals(item.getDeEvaluation())).count());
            info.setDeCount2(list.stream().filter(item->"2".equals(item.getDeEvaluation())).count());
            info.setDeCount3(list.stream().filter(item->"3".equals(item.getDeEvaluation())).count());
            info.setDeCount4(list.stream().filter(item->"4".equals(item.getDeEvaluation())).count());
            info.setNengCount1(list.stream().filter(item->"1".equals(item.getNengEvaluation())).count());
            info.setNengCount2(list.stream().filter(item->"2".equals(item.getNengEvaluation())).count());
            info.setNengCount3(list.stream().filter(item->"3".equals(item.getNengEvaluation())).count());
            info.setNengCount4(list.stream().filter(item->"4".equals(item.getNengEvaluation())).count());
            info.setQinCount1(list.stream().filter(item->"1".equals(item.getQinEvaluation())).count());
            info.setQinCount2(list.stream().filter(item->"2".equals(item.getQinEvaluation())).count());
            info.setQinCount3(list.stream().filter(item->"3".equals(item.getQinEvaluation())).count());
            info.setQinCount4(list.stream().filter(item->"4".equals(item.getQinEvaluation())).count());
            info.setJiCount1(list.stream().filter(item->"1".equals(item.getJiEvaluation())).count());
            info.setJiCount2(list.stream().filter(item->"2".equals(item.getJiEvaluation())).count());
            info.setJiCount3(list.stream().filter(item->"3".equals(item.getJiEvaluation())).count());
            info.setJiCount4(list.stream().filter(item->"4".equals(item.getJiEvaluation())).count());
            info.setLianCount1(list.stream().filter(item->"1".equals(item.getLianEvaluation())).count());
            info.setLianCount2(list.stream().filter(item->"2".equals(item.getLianEvaluation())).count());
            info.setLianCount3(list.stream().filter(item->"3".equals(item.getLianEvaluation())).count());
            info.setLianCount4(list.stream().filter(item->"4".equals(item.getLianEvaluation())).count());

            info.setRecordList(list);

            accessUserInfoList.add(info);
        });

        task.setAssessedUserInfoList(accessUserInfoList);
    }

    /**
     * 查询年度处级干部民主测评任务列表
     * 
     * @param bizAssessCadreDemocraticTask 年度处级干部民主测评任务
     * @return 年度处级干部民主测评任务
     */
    @Override
    public List<BizAssessCadreDemocraticTask> selectBizAssessCadreDemocraticTaskList(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        return bizAssessCadreDemocraticTaskMapper.selectBizAssessCadreDemocraticTaskList(bizAssessCadreDemocraticTask);
    }

    /**
     * 新增年度处级干部民主测评任务
     * 
     * @param bizAssessCadreDemocraticTask 年度处级干部民主测评任务
     * @return 结果
     */
    @Override
    public int insertBizAssessCadreDemocraticTask(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        if(StringUtils.isBlank(bizAssessCadreDemocraticTask.getId())){
            bizAssessCadreDemocraticTask.setId(IdUtils.randomUUID());
        }
        try{
            bizAssessCadreDemocraticTask.setCreateBy(SecurityUtils.getUsername());
            bizAssessCadreDemocraticTask.setCreateDept(SecurityUtils.getOnlineDept().getDeptId());
        } catch (Exception e){}
        bizAssessCadreDemocraticTask.setCreateTime(DateUtils.getNowDate());
        return bizAssessCadreDemocraticTaskMapper.insertBizAssessCadreDemocraticTask(bizAssessCadreDemocraticTask);
    }

    /**
     * 修改年度处级干部民主测评任务
     * 
     * @param bizAssessCadreDemocraticTask 年度处级干部民主测评任务
     * @return 结果
     */
    @Override
    public int updateBizAssessCadreDemocraticTask(BizAssessCadreDemocraticTask bizAssessCadreDemocraticTask)
    {
        try{
            bizAssessCadreDemocraticTask.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        bizAssessCadreDemocraticTask.setUpdateTime(DateUtils.getNowDate());
        return bizAssessCadreDemocraticTaskMapper.updateBizAssessCadreDemocraticTask(bizAssessCadreDemocraticTask);
    }

    /**
     * 批量删除年度处级干部民主测评任务
     * 
     * @param ids 需要删除的年度处级干部民主测评任务ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessCadreDemocraticTaskByIds(String[] ids)
    {
        Arrays.stream(ids).forEach(id->{
            deleteBizAssessCadreDemocraticTaskById(id);
        });
        return ids.length;
    }

    /**
     * 删除年度处级干部民主测评任务信息
     * 
     * @param id 年度处级干部民主测评任务ID
     * @return 结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteBizAssessCadreDemocraticTaskById(String id)
    {
        //先删除记录
        recordService.deleteByTaskIds(new String[]{id});
        return bizAssessCadreDemocraticTaskMapper.deleteBizAssessCadreDemocraticTaskById(id);
    }

    @Override
    public List<Long> getAssessedUserIds() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long deptId = user.getDeptId();
        return assessUserMapper.getAssessedUserIdList4Democratic(deptId);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void create(CadreDemocraticTaskVo.CreateReq req) {
        String year = req.getYear();
        String taskId = IdUtils.randomUUID();
        BizAssessCadreDemocraticTask task = new BizAssessCadreDemocraticTask();
        task.setId(taskId);
        task.setYear(year);
        task.setRemark(req.getRemark());
        insertBizAssessCadreDemocraticTask(task);

        List<BizAssessCadreDemocraticRecord> recordList = buildRecordList(req,taskId);
        recordService.batchInsert(recordList);
    }

    private List<BizAssessCadreDemocraticRecord> buildRecordList(CadreDemocraticTaskVo.CreateReq req,String taskId){
        List<BizAssessCadreDemocraticRecord> result = new LinkedList<>();
        List<Long> assessedUserIds = req.getAssessedUserIds();
        List<Long> voteUserIds = req.getVoteUserIds();

        for (int i = 0; i < assessedUserIds.size(); i++) {
            Long assessUserId = assessedUserIds.get(i);
            for (int j = 0; j < voteUserIds.size(); j++) {
                Long voteUserId = voteUserIds.get(j);
                if(voteUserId.equals(assessUserId)){
                    continue;
                }
                BizAssessCadreDemocraticRecord record = new BizAssessCadreDemocraticRecord();
                record.setId(IdUtils.randomUUID());
                record.setAssessedUserId(assessUserId);
                record.setVoteUserId(voteUserId);
                record.setTaskId(taskId);
                record.setStatus("0");
                result.add(record);
            }

        }
        return result;
    }

    @Override
    public void report2RSC(String taskId) {
        BizAssessCadreDemocraticRecord query = new BizAssessCadreDemocraticRecord();
        query.setTaskId(taskId);
        query.setStatus("0");
        List<BizAssessCadreDemocraticRecord> recordList = recordService.selectBizAssessCadreDemocraticRecordList(query);
        Set<String> voteUserNames = recordList.stream().map(item -> item.getVoteUserName()).collect(Collectors.toSet());
        if(!recordList.isEmpty()){
            throw new CustomException("当前评测任务尚未完成！还有" + voteUserNames.size() + "名用户未完成评测");
        }

        BizAssessCadreDemocraticTask task = new BizAssessCadreDemocraticTask();
        task.setId(taskId);
        task.setReportFlag("1");
        task.setReportDate(DateUtils.getDate());
        updateBizAssessCadreDemocraticTask(task);
    }
}
