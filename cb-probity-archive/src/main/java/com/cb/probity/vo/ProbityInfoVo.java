package com.cb.probity.vo;

import com.cb.probity.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
public class ProbityInfoVo {
    private BizProbity probity;
    private BizProbityBasic probityBasic;
    private BizProbityMarital probityMarital;
    private BizProbityStock probityStock;
    private BizProbityOther probityOther;
    private List<BizProbityInvestigate> probityInvestigateList = new ArrayList<>();
    private List<BizProbityGift> probityGiftList = new ArrayList<>();
    private List<BizProbityFamilyMember> probityFamilyMemberList = new ArrayList<>();
    private List<BizProbityCertificate> probityCertificateList = new ArrayList<>();
    private List<BizProbityGoAbroad> probityGoAbroadList = new ArrayList<>();
    private List<BizProbityChildSpouse> probityChildSpouseList = new ArrayList<>();
    private List<BizProbityMigrate> probityMigrateList = new ArrayList<>();
    private List<BizProbityLiveAbroad> probityLiveAbroadList = new ArrayList<>();
    private List<BizProbitySalary> probitySalaryList = new ArrayList<>();
    private List<BizProbityLecture> probityLectureList = new ArrayList<>();
    private List<BizProbityHouse> probityHouseList = new ArrayList<>();
    private List<BizProbityStockItem> probityStockItemList = new ArrayList<>();
    private List<BizProbityFund> probityFundList = new ArrayList<>();
    private List<BizProbityInsurance> probityInsuranceList = new ArrayList<>();
    private List<BizProbityEnterprise> probityEnterpriseList = new ArrayList<>();
    private List<BizProbityForeignDeposit> probityForeignDepositList = new ArrayList<>();
    private List<BizProbityForeignInvestment> probityForeignInvestmentList = new ArrayList<>();
    private List<BizProbityOperate> probityOperateList = new ArrayList<>();
}
