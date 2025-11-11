package com.cb.probity.service.impl;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.annotation.Excel;
import com.cb.common.utils.DateUtils;
import com.cb.probity.domain.*;
import com.cb.probity.mapper.*;
import com.cb.probity.service.IBizProbityModifyRecordService;
import com.cb.probity.vo.ProbityInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.probity.service.IBizProbityService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 廉政档案记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-13
 */
@Service
@Transactional
public class BizProbityServiceImpl implements IBizProbityService {
    @Resource
    private BizProbityMapper bizProbityMapper;

    @Resource
    private BizProbityCertificateMapper bizProbityCertificateMapper;

    @Resource
    private BizProbityBasicMapper bizProbityBasicMapper;

    @Resource
    private BizProbityChildSpouseMapper bizProbityChildSpouseMapper;

    @Resource
    private BizProbityEnterpriseMapper bizProbityEnterpriseMapper;

    @Resource
    private BizProbityFamilyMemberMapper bizProbityFamilyMemberMapper;

    @Resource
    private BizProbityForeignDepositMapper bizProbityForeignDepositMapper;

    @Resource
    private BizProbityForeignInvestmentMapper bizProbityForeignInvestmentMapper;

    @Resource
    private BizProbityFundMapper bizProbityFundMapper;

    @Resource
    private BizProbityGiftMapper bizProbityGiftMapper;
    @Resource
    private BizProbityGoAbroadMapper bizProbityGoAbroadMapper;
    @Resource
    private BizProbityHouseMapper bizProbityHouseMapper;
    @Resource
    private BizProbityInsuranceMapper bizProbityInsuranceMapper;
    @Resource
    private BizProbityInvestigateMapper bizProbityInvestigateMapper;
    @Resource
    private BizProbityLectureMapper bizProbityLectureMapper;
    @Resource
    private BizProbityLiveAbroadMapper bizProbityLiveAbroadMapper;
    @Resource
    private BizProbityMaritalMapper bizProbityMaritalMapper;
    @Resource
    private BizProbityMigrateMapper bizProbityMigrateMapper;
    @Resource
    private BizProbityModifyApprovalMapper bizProbityModifyApprovalMapper;
    @Resource
    private BizProbityOperateMapper bizProbityOperateMapper;
    @Resource
    private BizProbityOtherMapper bizProbityOtherMapper;
    @Resource
    private BizProbitySalaryMapper bizProbitySalaryMapper;
    @Resource
    private BizProbityStockItemMapper bizProbityStockItemMapper;
    @Resource
    private BizProbityStockMapper bizProbityStockMapper;
    @Resource
    private BizProbityModifyRecordMapper bizProbityModifyRecordMapper;

    private static Set<String> skipFields = new HashSet<>();

    static {
        skipFields.add("id");
        skipFields.add("searchValue");
        skipFields.add("createBy");
        skipFields.add("createTime");
        skipFields.add("updateBy");
        skipFields.add("updateTime");
        skipFields.add("remark");
        skipFields.add("params");
        skipFields.add("probityId");
    }

    /**
     * 查询廉政档案记录
     *
     * @param id 廉政档案记录ID
     * @return 廉政档案记录
     */
    @Override
    public BizProbity selectBizProbityById(String id) {
        return bizProbityMapper.selectBizProbityById(id);
    }

    /**
     * 查询廉政档案记录列表
     *
     * @param bizProbity 廉政档案记录
     * @return 廉政档案记录
     */
    @Override
    public List<BizProbity> selectBizProbityList(BizProbity bizProbity) {
        return bizProbityMapper.selectBizProbityList(bizProbity);
    }

    /**
     * 新增廉政档案记录
     *
     * @param bizProbity 廉政档案记录
     * @return 结果
     */
    @Override
    public String insertBizProbity(BizProbity bizProbity) {
        if (StringUtils.isBlank(bizProbity.getId())) {
            bizProbity.setConfirmStatus("1");
            bizProbity.setId(IdUtils.randomUUID());
            bizProbity.setDeptName(SecurityUtils.getOnlineDept().getDeptName());
        }

        bizProbity.setCreateBy(SecurityUtils.getUsername());
        //设置填写人用户ID
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        bizProbity.setUserId(userId);
        bizProbity.setCreateTime(DateUtils.getNowDate());
        bizProbityMapper.insertBizProbity(bizProbity);
        // 判断是否有历史数据，如果有历史数据，则添加到记录
        List<BizProbity> bizProbities = bizProbityMapper.selectBizProbityList(new BizProbity() {{
            setUserId(userId);
        }});
        List<BizProbity> collect = bizProbities.stream().filter(e -> !Objects.equals(e.getYear(), bizProbity.getYear())).sorted((a, b) -> b.getYear().compareTo(a.getYear())).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            String id = collect.get(0).getId();
            ProbityInfoVo probityInfoVo = this.searchInfoById(id);
            this.probityInfoVoUpdateProbityId(probityInfoVo, bizProbity.getId());
            this.updateProbityInfo(probityInfoVo);
        }
        return bizProbity.getId();
    }

    private void probityInfoVoUpdateProbityId(ProbityInfoVo probityInfoVo, String probityId) {
        probityInfoVo.getProbity().setId(probityId);
        BizProbityBasic probityBasic = probityInfoVo.getProbityBasic();
        if (probityBasic != null) {
            probityBasic.setProbityId(probityId);
        }
        BizProbityMarital probityMarital = probityInfoVo.getProbityMarital();
        if (probityMarital != null) {
            probityMarital.setProbityId(probityId);
        }
        BizProbityStock probityStock = probityInfoVo.getProbityStock();
        if (probityStock != null) {
            probityStock.setProbityId(probityId);
        }
        BizProbityOther probityOther = probityInfoVo.getProbityOther();
        if (probityOther != null) {
            probityOther.setProbityId(probityId);
        }
        probityInfoVo.getProbityStockItemList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbitySalaryList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityGiftList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityFamilyMemberList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityForeignDepositList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityForeignInvestmentList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityFundList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityHouseList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityOperateList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityInvestigateList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityCertificateList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityChildSpouseList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityEnterpriseList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityMigrateList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityGoAbroadList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityLectureList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityLiveAbroadList().forEach(item -> item.setProbityId(probityId));
        probityInfoVo.getProbityInsuranceList().forEach(item -> item.setProbityId(probityId));

    }


    /**
     * 批量新增廉政档案记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizProbity> entities) {
        if (null == entities || entities.isEmpty()) {
            return 0;
        }
        String userName = null;
        try {
            userName = SecurityUtils.getUsername();
        } catch (Exception e) {
        }
        Date nowDate = DateUtils.getNowDate();
        String finalUserName = userName;
        entities.parallelStream().forEach(item -> {
            item.setId(IdUtils.randomUUID());
            item.setCreateBy(finalUserName);
            item.setCreateTime(nowDate);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted += bizProbityMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改廉政档案记录
     *
     * @param bizProbity 廉政档案记录
     * @return 结果
     */
    @Override
    public int updateBizProbity(BizProbity bizProbity) {
        try {
            bizProbity.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        bizProbity.setUpdateTime(DateUtils.getNowDate());
        return bizProbityMapper.updateBizProbity(bizProbity);
    }

    /**
     * 批量删除廉政档案记录
     *
     * @param ids 需要删除的廉政档案记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityByIds(String[] ids) {
        this.deleteInfoByProbityId(ids[0]);
        return bizProbityMapper.deleteBizProbityByIds(ids);
    }

    /**
     * 删除廉政档案记录信息
     *
     * @param id 廉政档案记录ID
     * @return 结果
     */
    @Override
    public int deleteBizProbityById(String id) {
        this.deleteInfoByProbityId(id);
        return bizProbityMapper.deleteBizProbityById(id);
    }

    @Override
    public ProbityInfoVo searchInfoById(String id) {
        ProbityInfoVo vo = new ProbityInfoVo();
        BizProbity bizProbity = bizProbityMapper.selectBizProbityById(id);
        List<BizProbityBasic> bizProbityBasics = bizProbityBasicMapper.selectBizProbityBasicList(new BizProbityBasic() {{
            setProbityId(id);
        }});
        List<BizProbityCertificate> bizProbityCertificates = bizProbityCertificateMapper.selectBizProbityCertificateList(new BizProbityCertificate() {{
            setProbityId(id);
        }});
        List<BizProbityChildSpouse> bizProbityChildSpouses = bizProbityChildSpouseMapper.selectBizProbityChildSpouseList(new BizProbityChildSpouse() {{
            setProbityId(id);
        }});
        List<BizProbityEnterprise> bizProbityEnterprises = bizProbityEnterpriseMapper.selectBizProbityEnterpriseList(new BizProbityEnterprise() {{
            setProbityId(id);
        }});
        List<BizProbityFamilyMember> bizProbityFamilyMembers = bizProbityFamilyMemberMapper.selectBizProbityFamilyMemberList(new BizProbityFamilyMember() {{
            setProbityId(id);
        }});
        List<BizProbityForeignDeposit> bizProbityForeignDeposits = bizProbityForeignDepositMapper.selectBizProbityForeignDepositList(new BizProbityForeignDeposit() {{
            setProbityId(id);
        }});
        List<BizProbityForeignInvestment> bizProbityForeignInvestments = bizProbityForeignInvestmentMapper.selectBizProbityForeignInvestmentList(new BizProbityForeignInvestment() {{
            setProbityId(id);
        }});
        List<BizProbityFund> bizProbityFunds = bizProbityFundMapper.selectBizProbityFundList(new BizProbityFund() {{
            setProbityId(id);
        }});
        List<BizProbityGift> bizProbityGifts = bizProbityGiftMapper.selectBizProbityGiftList(new BizProbityGift() {{
            setProbityId(id);
        }});
        List<BizProbityGoAbroad> bizProbityGoAbroads = bizProbityGoAbroadMapper.selectBizProbityGoAbroadList(new BizProbityGoAbroad() {{
            setProbityId(id);
        }});
        List<BizProbityHouse> bizProbityHouses = bizProbityHouseMapper.selectBizProbityHouseList(new BizProbityHouse() {{
            setProbityId(id);
        }});
        List<BizProbityInsurance> bizProbityInsurances = bizProbityInsuranceMapper.selectBizProbityInsuranceList(new BizProbityInsurance() {{
            setProbityId(id);
        }});
        List<BizProbityInvestigate> bizProbityInvestigates = bizProbityInvestigateMapper.selectBizProbityInvestigateList(new BizProbityInvestigate() {{
            setProbityId(id);
        }});
        List<BizProbityLecture> bizProbityLectures = bizProbityLectureMapper.selectBizProbityLectureList(new BizProbityLecture() {{
            setProbityId(id);
        }});
        List<BizProbityLiveAbroad> bizProbityLiveAbroads = bizProbityLiveAbroadMapper.selectBizProbityLiveAbroadList(new BizProbityLiveAbroad() {{
            setProbityId(id);
        }});
        List<BizProbityMarital> bizProbityMaritals = bizProbityMaritalMapper.selectBizProbityMaritalList(new BizProbityMarital() {{
            setProbityId(id);
        }});
        List<BizProbityMigrate> bizProbityMigrates = bizProbityMigrateMapper.selectBizProbityMigrateList(new BizProbityMigrate() {{
            setProbityId(id);
        }});
        List<BizProbityOperate> bizProbityOperates = bizProbityOperateMapper.selectBizProbityOperateList(new BizProbityOperate() {{
            setProbityId(id);
        }});
        List<BizProbityOther> bizProbityOthers = bizProbityOtherMapper.selectBizProbityOtherList(new BizProbityOther() {{
            setProbityId(id);
        }});
        List<BizProbitySalary> probitySalaries = bizProbitySalaryMapper.selectBizProbitySalaryList(new BizProbitySalary() {{
            setProbityId(id);
        }});
        List<BizProbityStockItem> bizProbityStockItems = bizProbityStockItemMapper.selectBizProbityStockItemList(new BizProbityStockItem() {{
            setProbityId(id);
        }});
        List<BizProbityStock> bizProbityStocks = bizProbityStockMapper.selectBizProbityStockList(new BizProbityStock() {{
            setProbityId(id);
        }});
        vo.setProbity(bizProbity);
        if (!bizProbityBasics.isEmpty()) {
            vo.setProbityBasic(bizProbityBasics.get(0));
        }
        vo.setProbityCertificateList(bizProbityCertificates);
        vo.setProbityFamilyMemberList(bizProbityFamilyMembers);
        vo.setProbityChildSpouseList(bizProbityChildSpouses);
        vo.setProbityEnterpriseList(bizProbityEnterprises);
        vo.setProbityHouseList(bizProbityHouses);
        vo.setProbityLectureList(bizProbityLectures);
        vo.setProbityLiveAbroadList(bizProbityLiveAbroads);
        vo.setProbityFundList(bizProbityFunds);
        vo.setProbityInsuranceList(bizProbityInsurances);
        vo.setProbityForeignDepositList(bizProbityForeignDeposits);
        vo.setProbityForeignInvestmentList(bizProbityForeignInvestments);
        vo.setProbityGiftList(bizProbityGifts);
        vo.setProbityGoAbroadList(bizProbityGoAbroads);
        vo.setProbityInvestigateList(bizProbityInvestigates);
        if (!bizProbityMaritals.isEmpty()) {
            vo.setProbityMarital(bizProbityMaritals.get(0));
        }
        vo.setProbityMigrateList(bizProbityMigrates);
        vo.setProbityOperateList(bizProbityOperates);
        if (!bizProbityOthers.isEmpty()) {
            vo.setProbityOther(bizProbityOthers.get(0));
        }
        vo.setProbitySalaryList(probitySalaries);
        vo.setProbityStockItemList(bizProbityStockItems);
        if (!bizProbityStocks.isEmpty()) {
            vo.setProbityStock(bizProbityStocks.get(0));
        }
        return vo;
    }

    /**
     * 更新保存操作
     *
     * @param vo
     * @return
     */
    @Override
    public String updateProbityInfo(ProbityInfoVo vo) {
        // 根据id先删除在插入
        BizProbity probity = vo.getProbity();
        String probityId = probity.getId();
        this.deleteInfoByProbityId(probityId);
        BizProbityBasic probityBasic = vo.getProbityBasic();
            if(StringUtils.isNotNull(probityBasic)){
                probityBasic.setProbityId(probityId);
                probityBasic.setId(IdUtils.fastSimpleUUID());
                bizProbityBasicMapper.insertBizProbityBasic(probityBasic);
            }

        BizProbityMarital probityMarital = vo.getProbityMarital();
            if(StringUtils.isNotNull(probityMarital)){
                probityMarital.setProbityId(probityId);
                probityMarital.setId(IdUtils.fastSimpleUUID());
                bizProbityMaritalMapper.insertBizProbityMarital(probityMarital);
            }
        BizProbityStock probityStock = vo.getProbityStock();
            if(StringUtils.isNotNull(probityStock)){
                probityStock.setProbityId(probityId);
                probityStock.setId(IdUtils.fastSimpleUUID());
                bizProbityStockMapper.insertBizProbityStock(probityStock);
            }
        BizProbityOther probityOther = vo.getProbityOther();
            if(StringUtils.isNotNull(probityOther)){
                probityOther.setProbityId(probityId);
                probityOther.setId(IdUtils.fastSimpleUUID());
                bizProbityOtherMapper.insertBizProbityOther(probityOther);
            }
        //处理数组数据
        try {
            //本人、配偶、子女及其配偶被追究责任的情况记录
            List<BizProbityInvestigate> probityInvestigateList = vo.getProbityInvestigateList();
            probityInvestigateList = probityInvestigateList.stream().filter(e -> e.getInvestigateTime() != null && !Objects.equals("无", e.getInvestigateTime().trim())).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityInvestigateList.isEmpty()) {
                bizProbityInvestigateMapper.insertBatch(probityInvestigateList);
            }
            //本人拒收或上交礼金、礼品情况登记表格数据
            List<BizProbityGift> probityGiftList = vo.getProbityGiftList();
            probityGiftList = probityGiftList.stream().filter(e -> e.getRejectionSubmitAmount() != null && !Objects.equals("无", e.getRejectionSubmitAmount().trim())).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityGiftList.isEmpty()) {
                this.bizProbityGiftMapper.insertBatch(probityGiftList);
            }
            //家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）表格数据
            List<BizProbityFamilyMember> probityFamilyMemberList = vo.getProbityFamilyMemberList();
            probityFamilyMemberList = probityFamilyMemberList.stream().peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityFamilyMemberList.isEmpty()) {
                this.bizProbityFamilyMemberMapper.insertBatch(probityFamilyMemberList);
            }
            //廉政档案-6.1本人持有护照、往来港澳台证件情况表格数据
            List<BizProbityCertificate> probityCertificateList = vo.getProbityCertificateList();
            probityCertificateList = probityCertificateList.stream().filter(e -> e.getCertificateNameNo() != null && !Objects.equals("无", e.getCertificateNameNo().trim())).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityCertificateList.isEmpty()) {
                this.bizProbityCertificateMapper.insertBatch(probityCertificateList);
            }
            //廉政档案-6.2本人因私出国（境）及往来港澳台情况表格数据
            List<BizProbityGoAbroad> probityGoAbroadList = vo.getProbityGoAbroadList();
            probityGoAbroadList = probityGoAbroadList.stream().filter(e -> e.getRoundTripTime() != null && !Objects.equals(e.getRoundTripTime().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityGoAbroadList.isEmpty()) {
                this.bizProbityGoAbroadMapper.insertBatch(probityGoAbroadList);
            }
            // 廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况表格数据
            List<BizProbityHouse> probityHouseList = vo.getProbityHouseList();
            probityHouseList = probityHouseList.stream().filter(e -> e.getOwner() != null && !Objects.equals(e.getOwner().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityHouseList.isEmpty()) {
                this.bizProbityHouseMapper.insertBatch(probityHouseList);
            }
            //廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况表格数据
            List<BizProbityChildSpouse> probityChildSpouseList = vo.getProbityChildSpouseList();
            probityChildSpouseList = probityChildSpouseList.stream().filter(e -> e.getName() != null && !Objects.equals(e.getName().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityChildSpouseList.isEmpty()) {
                this.bizProbityChildSpouseMapper.insertBatch(probityChildSpouseList);
            }
            //廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况表格数据
            List<BizProbityMigrate> probityMigrateList = vo.getProbityMigrateList();
            probityMigrateList = probityMigrateList.stream().filter(e -> e.getName() != null && !Objects.equals(e.getName().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityMigrateList.isEmpty()) {
                this.bizProbityMigrateMapper.insertBatch(probityMigrateList);
            }
            // 廉政档案-19.本人或本人子女操办婚丧嫁娶情况表格数据
            List<BizProbityOperate> probityOperateList = vo.getProbityOperateList();
            probityOperateList = probityOperateList.stream().filter(e -> e.getOperateItem() != null && !Objects.equals(e.getOperateItem().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityOperateList.isEmpty()) {
                this.bizProbityOperateMapper.insertBatch(probityOperateList);
            }
            // 廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况表格数据
            List<BizProbityLiveAbroad> probityLiveAbroadList = vo.getProbityLiveAbroadList();
            probityLiveAbroadList = probityLiveAbroadList.stream().filter(e -> e.getName() != null && !Objects.equals(e.getName().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityLiveAbroadList.isEmpty()) {
                this.bizProbityLiveAbroadMapper.insertBatch(probityLiveAbroadList);
            }
            // 廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况表格数据
            List<BizProbityFund> probityFundList = vo.getProbityFundList();
            probityFundList = probityFundList.stream().filter(e -> e.getHolder() != null && !Objects.equals(e.getHolder().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityFundList.isEmpty()) {
                this.bizProbityFundMapper.insertBatch(probityFundList);
            }
            // 廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况表格数据
            List<BizProbityInsurance> probityInsuranceList = vo.getProbityInsuranceList();
            probityInsuranceList = probityInsuranceList.stream().filter(e -> e.getPolicyholder() != null && !Objects.equals(e.getPolicyholder().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityInsuranceList.isEmpty()) {
                this.bizProbityInsuranceMapper.insertBatch(probityInsuranceList);
            }
            // 廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况表格数据
            List<BizProbityForeignDeposit> probityForeignDepositList = vo.getProbityForeignDepositList();
            probityForeignDepositList = probityForeignDepositList.stream().filter(e -> e.getDepositor() != null && !Objects.equals(e.getDepositor().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityForeignDepositList.isEmpty()) {
                this.bizProbityForeignDepositMapper.insertBatch(probityForeignDepositList);
            }
            // 廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况表格数据
            List<BizProbityForeignInvestment> probityForeignInvestmentList = vo.getProbityForeignInvestmentList();
            probityForeignInvestmentList = probityForeignInvestmentList.stream().filter(e -> e.getInvestor() != null && !Objects.equals(e.getInvestor().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityForeignInvestmentList.isEmpty()) {
                this.bizProbityForeignInvestmentMapper.insertBatch(probityForeignInvestmentList);
            }
            // 廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况表格数据
            List<BizProbityEnterprise> probityEnterpriseList = vo.getProbityEnterpriseList();
            probityEnterpriseList = probityEnterpriseList.stream().filter(e -> e.getName() != null && !Objects.equals(e.getName().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityEnterpriseList.isEmpty()) {
                this.bizProbityEnterpriseMapper.insertBatch(probityEnterpriseList);
            }
            // 廉政档案-10.本人工资及各类奖金、津贴、补贴情况表格数据
            List<BizProbitySalary> probitySalaryList = vo.getProbitySalaryList();
            probitySalaryList = probitySalaryList.stream().filter(e -> e.getSalary() != null && !Objects.equals(e.getSalary().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probitySalaryList.isEmpty()) {
                this.bizProbitySalaryMapper.insertBatch(probitySalaryList);
            }
            // 廉政档案-13.2持有股票记录信息项表格数据
            List<BizProbityStockItem> probityStockItemList = vo.getProbityStockItemList();
            probityStockItemList = probityStockItemList.stream().filter(e -> e.getHolder() != null && !Objects.equals(e.getHolder().trim(), "无")).peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityStockItemList.isEmpty()) {
                this.bizProbityStockItemMapper.insertBatch(probityStockItemList);
            }
            // 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得表格数据
            List<BizProbityLecture> probityLectureList = vo.getProbityLectureList();
            probityLectureList = probityLectureList.stream().peek(e -> {
                e.setProbityId(probityId);
                e.setId(IdUtils.fastSimpleUUID());
            }).collect(Collectors.toList());
            if (!probityLectureList.isEmpty()) {
                this.bizProbityLectureMapper.insertBatch(probityLectureList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "操作成功";
    }

    private void deleteInfoByProbityId(String probityId) {
        bizProbityMapper.deleteInfoByProbityId(probityId);
    }

    @Override
    public int verifyBizProbity(String[] ids) {

        //确定的时候需要进行版本对比功能
        for (String id : ids) {
            //获取当前版本信息
            ProbityInfoVo currentInfoVo = this.searchInfoById(id);
            ProbityInfoVo prevInfoVo = new ProbityInfoVo();
            Integer year = currentInfoVo.getProbity().getYear();
            Long userId = currentInfoVo.getProbity().getUserId();
            String userName = currentInfoVo.getProbity().getUserName();
            String probityId = currentInfoVo.getProbity().getId();
            String deptName = currentInfoVo.getProbity().getDeptName();
            //获取上一年填报数据
            List<BizProbity> bizProbities = this.bizProbityMapper.selectBizProbityList(new BizProbity() {{
                setYear(year - 1);
                setUserId(userId);
                // 已确认
                setConfirmStatus("2");
            }});
            //有上一年的数据才进行数据对比
            if (!bizProbities.isEmpty()) {
                prevInfoVo = this.searchInfoById(bizProbities.get(0).getId());
                String modifyRecord = this.diffInfo(currentInfoVo, prevInfoVo).get("modifyRecord");
                String modifyFiled = this.diffInfo(currentInfoVo, prevInfoVo).get("modifyFiled");
                BizProbityModifyRecord bizProbityModifyRecord = new BizProbityModifyRecord();
                bizProbityModifyRecord.setProbityId(probityId);
                bizProbityModifyRecord.setModifyRecord(modifyRecord);
                bizProbityModifyRecord.setRecordTime(new Date());
                bizProbityModifyRecord.setUserName(userName);
                bizProbityModifyRecord.setUserId(userId);
                bizProbityModifyRecord.setModifyFiled(modifyFiled);
                bizProbityModifyRecord.setId(IdUtils.fastSimpleUUID());
                bizProbityModifyRecord.setCreateBy(SecurityUtils.getUsername());
                bizProbityModifyRecord.setCreateTime(new Date());
                bizProbityModifyRecord.setYear(year);
                bizProbityModifyRecord.setDeptName(deptName);
                //先删除再添加
                bizProbityModifyRecordMapper.deleteByProbityId(probityId);
                bizProbityModifyRecordMapper.insertBizProbityModifyRecord(bizProbityModifyRecord);
            }

        }
        // modifyRecordService.updateModifyRecord(ids);
        return bizProbityMapper.verifyBizProbity(ids);
    }

    private Map<String, String> diffInfo(ProbityInfoVo currentInfoVo, ProbityInfoVo prevInfoVo) {
        Map<String, String> result = new HashMap<>();
        JSONArray array = new JSONArray();
        Set<String> filesSet = new HashSet<>();
        JSONObject basicJson = this.diffBasic(prevInfoVo.getProbityBasic(), currentInfoVo.getProbityBasic(), "报告人基本情况");
        array.add(basicJson);
        filesSet.addAll((List<String>) basicJson.get("changedFields"));
        JSONObject maritalJson = this.diffBasic(prevInfoVo.getProbityMarital(), currentInfoVo.getProbityMarital(), "本人婚姻状况及紧急联系人情况");
        array.add(maritalJson);
        filesSet.addAll((List<String>) maritalJson.get("changedFields"));
        JSONObject stockJson = this.diffBasic(prevInfoVo.getProbityStock(), currentInfoVo.getProbityStock(), "本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况");
        array.add(stockJson);
        filesSet.addAll((List<String>) stockJson.get("changedFields"));
        JSONObject otherJson = this.diffBasic(prevInfoVo.getProbityOther(), currentInfoVo.getProbityOther(), "个人认为需要报告的其他事项");
        array.add(otherJson);
        filesSet.addAll((List<String>) otherJson.get("changedFields"));
        array.add(this.diffInfo(currentInfoVo.getProbityInvestigateList().stream().map(BizProbityInvestigate::getInvestigateTime).collect(Collectors.toSet()), prevInfoVo.getProbityInvestigateList().stream().map(BizProbityInvestigate::getInvestigateTime).collect(Collectors.toSet()), "本人、配偶、子女及其配偶被追究责任的情况记录"));
        array.add(this.diffInfo(currentInfoVo.getProbityGiftList().stream().map(BizProbityGift::getGiftTime).collect(Collectors.toSet()), prevInfoVo.getProbityGiftList().stream().map(BizProbityGift::getGiftTime).collect(Collectors.toSet()), "本人拒收或上交礼金、礼品情况登记表"));
        array.add(this.diffInfo(currentInfoVo.getProbityFamilyMemberList().stream().map(BizProbityFamilyMember::getName).collect(Collectors.toSet()), prevInfoVo.getProbityFamilyMemberList().stream().map(BizProbityFamilyMember::getName).collect(Collectors.toSet()), "家庭成员基本信息"));
        array.add(this.diffInfo(currentInfoVo.getProbityCertificateList().stream().map(BizProbityCertificate::getCertificateNameNo).collect(Collectors.toSet()), prevInfoVo.getProbityCertificateList().stream().map(BizProbityCertificate::getCertificateNameNo).collect(Collectors.toSet()), "本人持有护照、往来港澳台证件情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityGoAbroadList().stream().map(BizProbityGoAbroad::getRoundTripTime).collect(Collectors.toSet()), prevInfoVo.getProbityGoAbroadList().stream().map(BizProbityGoAbroad::getRoundTripTime).collect(Collectors.toSet()), "本人因私出国（境）及往来港澳台情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityChildSpouseList().stream().map(BizProbityChildSpouse::getName).collect(Collectors.toSet()), prevInfoVo.getProbityChildSpouseList().stream().map(BizProbityChildSpouse::getName).collect(Collectors.toSet()), "本人子女与外国人、无国籍人及港澳、台湾居民通婚情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityMigrateList().stream().map(BizProbityMigrate::getName).collect(Collectors.toSet()), prevInfoVo.getProbityMigrateList().stream().map(BizProbityMigrate::getName).collect(Collectors.toSet()), "配偶、子女及其配偶移居、居住国（境）外及港澳台的情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityLiveAbroadList().stream().map(BizProbityLiveAbroad::getName).collect(Collectors.toSet()), prevInfoVo.getProbityLiveAbroadList().stream().map(BizProbityLiveAbroad::getName).collect(Collectors.toSet()), "配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况"));
        //array.add(this.diffInfo(currentInfoVo.getProbitySalaryList().stream().map(BizProbitySalary::getSalaryTime).collect(Collectors.toSet()), prevInfoVo.getProbitySalaryList().stream().map(BizProbitySalary::getSalaryTime).collect(Collectors.toSet()), "本人、配偶、子女及其配偶取得、使用、转移、使用、转移、使用)))
        array.add(this.diffInfo(currentInfoVo.getProbityHouseList().stream().map(BizProbityHouse::getOwner).collect(Collectors.toSet()), prevInfoVo.getProbityHouseList().stream().map(BizProbityHouse::getOwner).collect(Collectors.toSet()), "本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityStockItemList().stream().map(BizProbityStockItem::getHolder).collect(Collectors.toSet()), prevInfoVo.getProbityStockItemList().stream().map(BizProbityStockItem::getHolder).collect(Collectors.toSet()), "配偶、共同生活的子女投资或者以其他方式持有股票的情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityFundList().stream().map(BizProbityFund::getHolder).collect(Collectors.toSet()), prevInfoVo.getProbityFundList().stream().map(BizProbityFund::getHolder).collect(Collectors.toSet()), "本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityInsuranceList().stream().map(BizProbityInsurance::getPolicyholder).collect(Collectors.toSet()), prevInfoVo.getProbityInsuranceList().stream().map(BizProbityInsurance::getPolicyholder).collect(Collectors.toSet()), "本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityEnterpriseList().stream().map(BizProbityEnterprise::getName).collect(Collectors.toSet()), prevInfoVo.getProbityEnterpriseList().stream().map(BizProbityEnterprise::getName).collect(Collectors.toSet()), "本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityForeignDepositList().stream().map(BizProbityForeignDeposit::getDepositor).collect(Collectors.toSet()), prevInfoVo.getProbityForeignDepositList().stream().map(BizProbityForeignDeposit::getDepositor).collect(Collectors.toSet()), "本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityForeignInvestmentList().stream().map(BizProbityForeignInvestment::getInvestor).collect(Collectors.toSet()), prevInfoVo.getProbityForeignInvestmentList().stream().map(BizProbityForeignInvestment::getInvestor).collect(Collectors.toSet()), "本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况"));
        array.add(this.diffInfo(currentInfoVo.getProbityOperateList().stream().map(BizProbityOperate::getOperateTime).collect(Collectors.toSet()), prevInfoVo.getProbityOperateList().stream().map(BizProbityOperate::getOperateTime).collect(Collectors.toSet()), "本人或本人子女操办婚丧嫁娶情况"));
        result.put("modifyRecord", JSONObject.toJSONString(array));
        result.put("modifyFiled", JSONObject.toJSONString(filesSet));
        return result;
    }

    private String handleNull(Object str) {
        return str == null ? "" : str.toString();
    }

    private JSONObject diffBasic(Object prev, Object current, String type) {
        JSONObject result = new JSONObject();
        List<String> changedFields = new ArrayList<>();
        List<String> changeHistory = new ArrayList<>();
        Arrays.stream(current.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                if (!skipFields.contains(field.getName())) {
                    Object a = field.get(prev);
                    Object b = field.get(current);
                    Excel excel = field.getAnnotation(Excel.class);
                    if (!Objects.equals(a, b)) {
                        changedFields.add(field.getName());
                        changeHistory.add(excel.name() + ":" + handleNull(a) + "->" + handleNull(b));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        result.put("added", "");
        result.put("deleted", "");
        result.put("changedFields", changedFields);
        result.put("changeHistory", String.join(",", changeHistory));
        result.put("type", type);
        return result;
    }

    private JSONObject diffInfo(Set<String> current, Set<String> prev, String name) {
        JSONObject result = new JSONObject();
        Set<String> collect = current.stream().filter(prev::contains).collect(Collectors.toSet());
        current.removeAll(collect);
        prev.removeAll(collect);
        result.put("deleted", String.join(",", prev));
        result.put("added", String.join(",", current));
        result.put("changedFields", "");
        result.put("changeHistory", "");
        result.put("type", name);
        return result;
    }

    @Override
    public int applyModifyProbity(String[] ids) {
        //先判断状态
        BizProbity bizProbity = this.selectBizProbityById(ids[0]);
        if ("2".equals(bizProbity.getConfirmStatus())) {
            //添加修改记录
            BizProbityModifyApproval approval = new BizProbityModifyApproval();
            approval.setId(IdUtils.fastSimpleUUID());
            approval.setProbityId(ids[0]);
            approval.setCreateBy(SecurityUtils.getUsername());
            approval.setStatus(1);
            approval.setCreateTime(DateUtils.getNowDate());
            approval.setApplyUser(bizProbity.getUserName());
            approval.setYear(bizProbity.getYear());
            approval.setDeptName(bizProbity.getDeptName());
            bizProbityModifyApprovalMapper.insertBizProbityModifyApproval(approval);
            //修改状态
            bizProbity.setConfirmStatus("4");
            bizProbityMapper.updateBizProbity(bizProbity);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int updateConfirmStatus(String id, String confirmStatus) {
        return this.bizProbityMapper.updateConfirmStatus(id, confirmStatus);
    }
}
