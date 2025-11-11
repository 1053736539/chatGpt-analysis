package com.cb.assess.service.impl;

import com.cb.assess.domain.*;
import com.cb.assess.enums.Special;
import com.cb.assess.mapper.*;
import com.cb.assess.service.IBizAssessIndicatorProService;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cb.assess.enums.PersonnelType.*;

/**
 * 考核方案Service业务层处理
 *
 * @author ouyang
 * @date 2023-11-01
 */
@Service
public class BizAssessIndicatorProServiceImpl implements IBizAssessIndicatorProService {
    @Autowired
    private BizAssessIndicatorProMapper bizAssessIndicatorProMapper;
    @Autowired
    private BizAssessIndicatorProDutyMapper proDutyMapper;
    @Autowired
    private BizAssessIndicatorProRatGroupMapper ratGroupMapper;
    @Autowired
    private BizAssessIndicatorProProcedureMapper procedureMapper;
    @Autowired
    private BizAssessIndicatorProAssessorsMapper assessorsMapper;


    /**
     * 查询考核方案
     *
     * @param proId 考核方案ID
     * @return 考核方案
     */
    @Override
    public BizAssessIndicatorPro selectBizAssessIndicatorProById(String proId) {
        return bizAssessIndicatorProMapper.selectBizAssessIndicatorProById(proId);
    }

    /**
     * 查询考核方案列表
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 考核方案
     */
    @Override
    public List<BizAssessIndicatorPro> selectBizAssessIndicatorProList(BizAssessIndicatorPro bizAssessIndicatorPro) {
        return bizAssessIndicatorProMapper.selectBizAssessIndicatorProList(bizAssessIndicatorPro);
    }

    /**
     * 新增考核方案
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 结果
     */
    @Override
    @Transactional
    public int insertBizAssessIndicatorPro(BizAssessIndicatorPro bizAssessIndicatorPro) {
        bizAssessIndicatorPro.setProId(IdUtils.randomUUID());
        bizAssessIndicatorPro.setCreateBy(SecurityUtils.getUsername());
        bizAssessIndicatorPro.setCreateTime(DateUtils.getNowDate());
        int result = bizAssessIndicatorProMapper.insertBizAssessIndicatorPro(bizAssessIndicatorPro);
        batchInsertProDutyByProEntity(bizAssessIndicatorPro);
        batchInsertProRatGroupByEntity(bizAssessIndicatorPro);
        batchInsertProcedureByEntity(bizAssessIndicatorPro);
        batchInsertProcedureByPro(bizAssessIndicatorPro);
        return result;
    }

    /**
     * 修改考核方案
     *
     * @param bizAssessIndicatorPro 考核方案
     * @return 结果
     */
    @Override
    @Transactional
    public int updateBizAssessIndicatorPro(BizAssessIndicatorPro bizAssessIndicatorPro) {
        String status = bizAssessIndicatorPro.getStatus();
        if ("2".equals(status)) {
            bizAssessIndicatorPro.setStatus("0"); // 如果是审核不通过的，修改后重置为待审核
            bizAssessIndicatorPro.setAuditOption(""); //设置审核意见为空串
        }
        bizAssessIndicatorPro.setUpdateBy(SecurityUtils.getUsername());
        bizAssessIndicatorPro.setUpdateTime(DateUtils.getNowDate());
        int result = bizAssessIndicatorProMapper.updateBizAssessIndicatorPro(bizAssessIndicatorPro);
        proDutyMapper.deleteBizAssessIndicatorProDutyById(bizAssessIndicatorPro.getProId());
        batchInsertProDutyByProEntity(bizAssessIndicatorPro);
        ratGroupMapper.deleteBizAssessIndicatorProRatGroupById(bizAssessIndicatorPro.getProId());
        batchInsertProRatGroupByEntity(bizAssessIndicatorPro);
        procedureMapper.deleteBizAssessIndicatorProProcedureById(bizAssessIndicatorPro.getProId());
        batchInsertProcedureByEntity(bizAssessIndicatorPro);
        assessorsMapper.deleteProAssessorsByProId(bizAssessIndicatorPro.getProId());
        batchInsertProcedureByPro(bizAssessIndicatorPro);
        return result;
    }


    /**
     * 批量删除考核方案
     *
     * @param proIds 需要删除的考核方案ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorProByIds(String[] proIds) {
        return bizAssessIndicatorProMapper.deleteBizAssessIndicatorProByIds(proIds);
    }

    /**
     * 删除考核方案信息
     *
     * @param proId 考核方案ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessIndicatorProById(String proId) {
        return bizAssessIndicatorProMapper.deleteBizAssessIndicatorProById(proId);
    }

    @Override
    public int logicDeleteBizAssessIndicatorProByIds(String[] proIds) {
        return bizAssessIndicatorProMapper.logicDeleteBizAssessIndicatorProByIds(proIds);
    }

    @Override
    public int logicDeleteBizAssessIndicatorProById(String proId) {
        return bizAssessIndicatorProMapper.logicDeleteBizAssessIndicatorProById(proId);
    }

    @Override
    public int changeStatus(BizAssessIndicatorPro pro) {
        return bizAssessIndicatorProMapper.changeStatus(pro);
    }

    @Override
    public List<BizAssessIndicatorPro> querySelectorList(BizAssessIndicatorPro pro) {
        return bizAssessIndicatorProMapper.querySelectorList(pro);
    }

    @Override
    public int auditAndSave(BizAssessIndicatorPro bizAssessIndicatorPro) {
        bizAssessIndicatorPro.setUpdateBy(SecurityUtils.getUsername());
        bizAssessIndicatorPro.setUpdateTime(DateUtils.getNowDate());
        int result = bizAssessIndicatorProMapper.updateBizAssessIndicatorPro(bizAssessIndicatorPro);
        proDutyMapper.deleteBizAssessIndicatorProDutyById(bizAssessIndicatorPro.getProId());
        batchInsertProDutyByProEntity(bizAssessIndicatorPro);
        ratGroupMapper.deleteBizAssessIndicatorProRatGroupById(bizAssessIndicatorPro.getProId());
        batchInsertProRatGroupByEntity(bizAssessIndicatorPro);
        procedureMapper.deleteBizAssessIndicatorProProcedureById(bizAssessIndicatorPro.getProId());
        batchInsertProcedureByEntity(bizAssessIndicatorPro);
        assessorsMapper.deleteProAssessorsByProId(bizAssessIndicatorPro.getProId());
        batchInsertProcedureByPro(bizAssessIndicatorPro);
        return result;
    }

    public void batchInsertProDutyByProEntity(BizAssessIndicatorPro pro) {
        List<BizAssessIndicatorProDuty> dutyList = pro.getDutyList();
        String proId = pro.getProId();
        if (StringUtils.isNotEmpty(dutyList)) {
            List<BizAssessIndicatorProDuty> list = new ArrayList<>();
            for (BizAssessIndicatorProDuty duty : dutyList) {
                duty.setProId(proId);
                list.add(duty);
            }
            if (list.size() > 0) {
                proDutyMapper.batchInsertProDuty(list);
            }
        }
    }

    public void batchInsertProRatGroupByEntity(BizAssessIndicatorPro pro) {
        this.checkRatGroup(pro);
        List<BizAssessIndicatorProRatGroup> ratGroupList = pro.getRatGroupList();
        String proId = pro.getProId();
        if (StringUtils.isNotEmpty(ratGroupList)) {
            List<BizAssessIndicatorProRatGroup> list = new ArrayList<>();
            for (BizAssessIndicatorProRatGroup group : ratGroupList) {
                group.setGroupId(IdUtils.randomUUID());
                group.setProId(proId);
                list.add(group);
            }
            if (list.size() > 0) {
                ratGroupMapper.batchInsertProRatGroup(list);
            }
        }
    }

    private void checkRatGroup(BizAssessIndicatorPro pro) {
        List<BizAssessIndicatorProRatGroup> ratGroupList = pro.getRatGroupList();
        List<String> collect = ratGroupList.stream().map(BizAssessIndicatorProRatGroup::getName).distinct().collect(Collectors.toList());
        if (ratGroupList.size() > collect.size()) {
            throw new CustomException("分值权重配置有重复项");
        }
        int sum = ratGroupList.stream().mapToInt(BizAssessIndicatorProRatGroup::getWeight).reduce(0, Integer::sum);
        if (sum > 100) throw new CustomException("分值权重配置项未达到100%，请重新配置！");

        if (CIVIL_SERVANT_SPECIAL.getCode().equals(pro.getPersonnelType())) {
            if (!collect.containsAll(Arrays.asList(CIVIL_SERVANT_SPECIAL.getMust())))
                throw new CustomException("分值权重配置项必须包含【民主测评、副局领导评分、局主要领导评分】");
        }
        if (CIVIL_SERVANT_GENERAL.getCode().equals(pro.getPersonnelType())) {
            if (!collect.containsAll(Arrays.asList(CIVIL_SERVANT_GENERAL.getMust())))
                throw new CustomException("分值权重配置项必须包含【民主测评、主要负责人评价】");
        }
        if (INSTITUTION_SPECIAL.getCode().equals(pro.getPersonnelType())) {
            if (!collect.containsAll(Arrays.asList(INSTITUTION_SPECIAL.getMust())))
                throw new CustomException("分值权重配置项必须包含【民主测评、副局领导评分、局主要领导评分】");
        }
        if (INSTITUTION_GENERAL.getCode().equals(pro.getPersonnelType())) {
            if (!collect.containsAll(Arrays.asList(INSTITUTION_GENERAL.getMust())))
                throw new CustomException("分值权重配置项必须包含【民主测评、主要负责人评价】");
        }
        if (SPECIAL_ASSESS.getCode().equals(pro.getSpecial())) {
            if (!collect.containsAll(Arrays.asList(SPECIAL_ASSESS.getMust())))
                throw new CustomException("分值权重配置项必须包含【专项考核评分】");
        }
    }

    public void batchInsertProcedureByEntity(BizAssessIndicatorPro pro) {
        List<BizAssessIndicatorProProcedure> procedureList = pro.getProcedureList();
        if(CollectionUtils.isEmpty(procedureList)){
            throw new CustomException("您未设置考核方法程序！");
        }
        List<String> collect = procedureList.stream().map(BizAssessIndicatorProProcedure::getName).distinct().collect(Collectors.toList());
        if (procedureList.size() > collect.size()) {
            throw new CustomException("考核方法程序有重复项");
        }
        String proId = pro.getProId();
        if (StringUtils.isNotEmpty(procedureList)) {
            List<BizAssessIndicatorProProcedure> list = new ArrayList<>();
            for (BizAssessIndicatorProProcedure procedure : procedureList) {
                procedure.setProcedureId(IdUtils.randomUUID());
                procedure.setProId(proId);
                list.add(procedure);
            }
            if (list.size() > 0) {
                procedureMapper.batchInsertProProcedure(list);
            }
        }
    }

    public void batchInsertProcedureByPro(BizAssessIndicatorPro pro){
        List<BizAssessIndicatorProAssessors> assessors = pro.getAssessors();
        String special = pro.getSpecial();
        if(Special.PECULIAR.getCode().equals(special)){
            if(CollectionUtils.isEmpty(assessors)) throw new RuntimeException("请选择考评人员!");
        }
        String proId = pro.getProId();
        if (StringUtils.isNotEmpty(assessors)) {
            List<BizAssessIndicatorProAssessors> list =new ArrayList<>();
            for (BizAssessIndicatorProAssessors o:assessors){
                o.setProId(proId);
                list.add(o);
            }
            if (list.size() > 0) {
                assessorsMapper.batchInsertProAssessors(list);
            }
        }
    }
}
