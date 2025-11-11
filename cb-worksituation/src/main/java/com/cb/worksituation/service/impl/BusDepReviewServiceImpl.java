package com.cb.worksituation.service.impl;

import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.service.ISysDeptService;
import com.cb.worksituation.domain.BusDepExpl;
import com.cb.worksituation.domain.BusDepReview;
import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.domain.BusDepReviewHeader;
import com.cb.worksituation.mapper.BusDepReviewDataMapper;
import com.cb.worksituation.mapper.BusDepReviewHeaderMapper;
import com.cb.worksituation.mapper.BusDepReviewMapper;
import com.cb.worksituation.service.IBusDepExplService;
import com.cb.worksituation.service.IBusDepReviewService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门评分Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Service
public class BusDepReviewServiceImpl implements IBusDepReviewService {
    @Autowired
    private BusDepReviewMapper busDepReviewMapper;

    @Autowired
    private BusDepReviewHeaderMapper busDepReviewHeaderMapper;

    @Autowired
    private BusDepReviewDataMapper busDepReviewDataMapper;

    @Autowired
    private IBusDepExplService busDepExplService;

    @Autowired
    private ISysDeptService sysDeptService;

    private static final String[] DESP_LIST_ONE = {"五华区纪委监委", "盘龙区纪委监委", "官渡区纪委监委", "西山区纪委监委", "呈贡区纪委监委", "东川区纪委监委", "安宁市纪委监委", "晋宁区纪委监委", "富明县纪委监委", "宜良县纪委监委", "嵩明县纪委监委", "石林县纪委监委", "禄劝县纪委监委", "寻甸县纪委监委"};


    private static final String[] DESP_LIST_TWO = {"第一监督检查室", "第二监督检查室", "第三监督检查室", "第四监督检查室", "第五监督检查室", "第六监督检查室", "第七监督检查室"};


    private static final String[] DESP_LIST_THREE = {"第八审查调查室", "第九审查调查室", "第十审查检查室", "第十一审查检查室", "第十二审查检查室", "第十三审查检查室", "第十四审查检查室"};

    private static final String[] DESP_LIST_FOUR = {"信息技术保障室", "组织部（含离退办）", "机关党委", "案件审理室", "党风政风监督室", "办公室", "案件监督管理室", "宣传部", "法规室", "研究室", "纪检监察干部监督室", "信访室", "数字与信息化室"};

    private static final String[] DESP_LIST_FIVE = {"滇中新区纪检监察工委", "滇池度假区纪检监察工委", "经开区纪检监察工委", "磨憨—磨丁合作区纪检监察工委", "高新区纪检监察工委", "昆明综保区纪检监察工委"};

    private static final String[] DESP_LIST_SEVEV = {"驻市公安局纪检监察组", "驻市住房城乡建设局纪检监察组", "驻市自然资源规划局纪检监察组", "驻市农业农村局纪检监察组", "驻市国资委纪检监察组", "驻市生态环境局纪检监察组", "驻市交通运输局纪检监察组", "驻阳宗海管理局纪检监察组", "驻市教育体育局纪检监察组", "驻市委组织部纪检监察组", "驻市市场监管局纪检监察组", "驻市政务服务局纪检监察组", "驻市卫生健康委纪检监察组", "驻市水务局纪检监察组", "市直机关纪检监察工委", "驻市民政局纪检监察组", "驻市检察院纪检监察组", "驻市政协机关纪检监察组", "驻市委办公室纪检监察组", "驻市工业和信息化局纪检监察组", "驻市政府办公室纪检监察组", "驻市委宣传部纪检监察组", "驻市法院纪检监察组", "驻市财政局纪检监察组", "驻市司法局纪检监察组", "驻市应急局纪检监察组", "驻市人大机关纪检监察组", "驻市发展改革委纪检监察组", "驻市文化和旅游局纪检监察组"};


    /**
     * 查询部门评分
     *
     * @param id 部门评分ID
     * @return 部门评分
     */
    @Override
    public BusDepReview selectBusDepReviewById(String id) {
        return busDepReviewMapper.selectBusDepReviewById(id);
    }

    /**
     * 查询部门评分列表
     *
     * @param busDepReview 部门评分
     * @return 部门评分
     */
    @Override
    public List<BusDepReview> selectBusDepReviewList(BusDepReview busDepReview) {
        return busDepReviewMapper.selectBusDepReviewList(busDepReview);
    }

    @Override
    public BusDepReview getDisplayHeaderList(String id) {
        return busDepReviewMapper.getDisplayHeaderList(id);
    }

    /**
     * 新增部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    @Override
    public int insertBusDepReview(BusDepReview busDepReview) {
        if (StringUtils.isBlank(busDepReview.getId())) {
            busDepReview.setId(IdUtils.randomUUID());
        }
        try {
            busDepReview.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        busDepReview.setCreateTime(DateUtils.getNowDate());
        return busDepReviewMapper.insertBusDepReview(busDepReview);
    }


    /**
     * 批量新增部门评分
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepReview> entities) {
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
            totalInserted += busDepReviewMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改部门评分
     *
     * @param busDepReview 部门评分
     * @return 结果
     */
    @Override
    public AjaxResult updateBusDepReview(BusDepReview busDepReview) {
        if (isEdit(busDepReview.getId())) {
            return AjaxResult.error("该评分表已进行填报，不允许修改状态");
        }
        if ("1".equals(busDepReview.getBusStatus())) {
            List<BusDepReviewData> busDepReviewDataList = new ArrayList<>();
            BusDepReview depReview = busDepReviewMapper.selectBusDepReviewById(busDepReview.getId());
            if ("1".equals(depReview.getDivisionDept())) {
                for (String deptNmae : DESP_LIST_ONE) {
                    BusDepReviewData busDepReviewData = new BusDepReviewData();
                    busDepReviewData.setId(IdUtils.randomUUID());
                    busDepReviewData.setBusDepReviewId(depReview.getId());
                    busDepReviewData.setEvaluatTarget(deptNmae);
                    busDepReviewData.setDataStatus("1");
                    busDepReviewDataList.add(busDepReviewData);
                }
                // 2 委机关监督检查室 1-7
            } else if ("2".equals(depReview.getDivisionDept())) {
                for (String deptNmae : DESP_LIST_TWO) {
                    BusDepReviewData busDepReviewData = new BusDepReviewData();
                    busDepReviewData.setId(IdUtils.randomUUID());
                    busDepReviewData.setBusDepReviewId(depReview.getId());
                    busDepReviewData.setEvaluatTarget(deptNmae);
                    busDepReviewData.setDataStatus("1");
                    busDepReviewDataList.add(busDepReviewData);
                }
                // 3 委机关审查调查室
            } else if ("3".equals(depReview.getDivisionDept())) {
                for (String deptNmae : DESP_LIST_THREE) {
                    BusDepReviewData busDepReviewData = new BusDepReviewData();
                    busDepReviewData.setId(IdUtils.randomUUID());
                    busDepReviewData.setBusDepReviewId(depReview.getId());
                    busDepReviewData.setEvaluatTarget(deptNmae);
                    busDepReviewData.setDataStatus("1");
                    busDepReviewDataList.add(busDepReviewData);
                }
                // 4 委机关综合业务部门
            } else if ("4".equals(depReview.getDivisionDept())) {
                for (String deptNmae : DESP_LIST_FOUR) {
                    BusDepReviewData busDepReviewData = new BusDepReviewData();
                    busDepReviewData.setId(IdUtils.randomUUID());
                    busDepReviewData.setBusDepReviewId(depReview.getId());
                    busDepReviewData.setEvaluatTarget(deptNmae);
                    busDepReviewData.setDataStatus("1");
                    busDepReviewDataList.add(busDepReviewData);
                }
                // 5 开发（度假）园区
            } else if ("5".equals(depReview.getDivisionDept())) {
                for (String deptNmae : DESP_LIST_FIVE) {
                    BusDepReviewData busDepReviewData = new BusDepReviewData();
                    busDepReviewData.setId(IdUtils.randomUUID());
                    busDepReviewData.setBusDepReviewId(depReview.getId());
                    busDepReviewData.setEvaluatTarget(deptNmae);
                    busDepReviewData.setDataStatus("1");
                    busDepReviewDataList.add(busDepReviewData);
                }
                // 6 纪检监察组
            } else if ("6".equals(depReview.getDivisionDept())) {
                for (String deptNmae : DESP_LIST_SEVEV) {
                    BusDepReviewData busDepReviewData = new BusDepReviewData();
                    busDepReviewData.setId(IdUtils.randomUUID());
                    busDepReviewData.setBusDepReviewId(depReview.getId());
                    busDepReviewData.setEvaluatTarget(deptNmae);
                    busDepReviewData.setDataStatus("1");
                    busDepReviewDataList.add(busDepReviewData);
                }
            }
            busDepReviewDataMapper.deleteByReviewIdAndCreator(depReview.getId());
            busDepReviewDataMapper.insertBatch(busDepReviewDataList);
        }
        busDepReview.setUpdateBy(SecurityUtils.getUsername());
        busDepReview.setUpdateTime(DateUtils.getNowDate());
        busDepReviewMapper.updateBusDepReview(busDepReview);
        return AjaxResult.success("修改成功");
    }

    private boolean isEdit(String reviewId) {
        BusDepReviewData busDepReviewData = new BusDepReviewData();
        busDepReviewData.setBusDepReviewId(reviewId);
        List<BusDepReviewData> busDepReviewDataList = busDepReviewDataMapper.selectBusDepReviewDataList(busDepReviewData);
        if (CollectionUtils.isNotEmpty(busDepReviewDataList)) {
            for (BusDepReviewData busDepRevData : busDepReviewDataList) {
                if (StringUtils.isNotEmpty(busDepRevData.getDataJson())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 批量删除部门评分
     *
     * @param ids 需要删除的部门评分ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteBusDepReviewByIds(String[] ids) {
        if (isEdit(ids[0])) {
            return AjaxResult.error("该评分表已进行填报，不允许删除");
        }
        busDepReviewMapper.deleteBusDepReviewByIds(ids);
        return AjaxResult.success("删除成功");
    }

    /**
     * 删除部门评分信息
     *
     * @param id 部门评分ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteBusDepReviewById(String id) {
        if (isEdit(id)) {
            return AjaxResult.error("该评分表已进行填报，不允许修改状态");
        }
        busDepReviewMapper.deleteBusDepReviewById(id);
        return AjaxResult.success("删除成功");
    }


    /**
     * 获取评分表表格配置信息
     *
     * @param id 评分表ID
     * @return 包含表头和数据的评分表
     */
    @Override
    public BusDepReview getReviewTableConfig(String id, Boolean sign) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        BusDepReview busDepReview = busDepReviewMapper.selectBusDepReviewById(id);
        if (busDepReview == null) {
            return null;
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        boolean isAdmin = loginUserAuth();
        Set<String> permittedDepts = isAdmin ? Collections.emptySet() : collectUserDeptNames(loginUser);
        BusDepReviewHeader headerQuery = new BusDepReviewHeader();
        headerQuery.setBusDepReviewId(id);
        List<BusDepReviewHeader> headerList = busDepReviewHeaderMapper.selectBusDepReviewHeaderList(headerQuery);
        if (!CollectionUtils.isEmpty(headerList)) {
            headerList.forEach(header -> {
                if (StringUtils.isNotBlank(header.getBusDepExplId())) {
                    BusDepExpl busDepExpl = busDepExplService.selectBusDepExplById(header.getBusDepExplId());
                    header.setBusDepExpl(busDepExpl);
                } else {
                    header.setBusDepExpl(null);
                }
            });
            if (!isAdmin) {
                headerList = headerList.stream().filter(header -> headerVisibleForUser(header, permittedDepts)).collect(Collectors.toList());
            }
            headerList.sort(Comparator.comparing(BusDepReviewHeader::getHeadOrder, Comparator.nullsLast(Comparator.naturalOrder())));
        }
        busDepReview.setBusDepReviewHeaderList(headerList);

        BusDepReviewData dataQuery = new BusDepReviewData();
        dataQuery.setBusDepReviewId(id);
        if (sign) {
            dataQuery.setDataStatus("2");
        }
        List<BusDepReviewData> dataList = busDepReviewDataMapper.selectBusDepReviewDataList(dataQuery);
        // 如果数据为空-新增默认值

        if (!CollectionUtils.isEmpty(dataList)) {
            dataList.sort(Comparator.comparing(BusDepReviewData::getCreateTime, Comparator.nullsLast(Comparator.naturalOrder())));
        }
        busDepReview.setBusDepReviewDataList(dataList);
        return busDepReview;
    }

    @Override
    public List<BusDepReview> selectBusDepReviewListForCurrentUser(BusDepReview busDepReview) {
        if (busDepReview == null) {
            busDepReview = new BusDepReview();
        }
        busDepReview.setBusStatus("1");
        List<BusDepReview> reviews = null;
        if (loginUserAuth()) {
            reviews = busDepReviewMapper.selectBusDepReviewList(busDepReview);
        } else {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String deptName = loginUser.getUser().getDeptName();
            reviews = busDepReviewMapper.selectBusDepReviewListByEvaluatTargets(busDepReview, deptName);
        }
        return reviews;
    }

    private void enrichDataStatus(List<BusDepReview> reviews, boolean isAdmin, String username) {
        if (CollectionUtils.isEmpty(reviews)) {
            return;
        }
        if (isAdmin) {
            reviews.stream().filter(Objects::nonNull).forEach(review -> review.setDataStatus("2"));
            return;
        }
        if (StringUtils.isBlank(username)) {
            reviews.stream().filter(Objects::nonNull).forEach(review -> review.setDataStatus("0"));
            return;
        }
        reviews.stream().filter(Objects::nonNull).forEach(review -> {
            if (StringUtils.isBlank(review.getId())) {
                review.setDataStatus("0");
                return;
            }
            List<String> statuses = busDepReviewDataMapper.selectDataStatusByReviewIdAndCreator(review.getId(), username);
            review.setDataStatus(resolveDataStatus(statuses));
        });
    }

    private Set<String> collectUserDeptNames(LoginUser loginUser) {
        Set<String> targetDeptNames = new HashSet<>();
        if (loginUser == null || loginUser.getUser() == null) {
            return targetDeptNames;
        }
        SysDept dept = loginUser.getUser().getDept();
        if (dept != null && StringUtils.isNotBlank(dept.getDeptName())) {
            targetDeptNames.add(dept.getDeptName());
        }
        if (StringUtils.isNotBlank(loginUser.getUser().getDeptName())) {
            targetDeptNames.add(loginUser.getUser().getDeptName());
        }

        List<SysDept> chargeDepts = sysDeptService.selectChargeDeptList(loginUser.getUser().getUserId());
        if (CollectionUtils.isNotEmpty(chargeDepts)) {
            chargeDepts.stream().map(SysDept::getDeptName).filter(StringUtils::isNotBlank).forEach(targetDeptNames::add);
        }
        return targetDeptNames;
    }

    @Override
    public boolean loginUserAuth() {
        // 办公室（管理员
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String deptName = loginUser.getUser().getDeptName();
        if (StringUtils.isBlank(deptName)) {
            deptName = loginUser.getUser().getDept().getDeptName();
        }
        if ("办公室".equals(deptName) || SecurityUtils.isAdmin(loginUser.getUser().getUserId())) {
            return true;
        }
        return false;
    }

    private boolean headerVisibleForUser(BusDepReviewHeader header, Set<String> permittedDepts) {
        if (header == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(permittedDepts)) {
            BusDepExpl expl = header.getBusDepExpl();
            return expl == null || StringUtils.isBlank(expl.getEvaluationDept());
        }
        BusDepExpl expl = header.getBusDepExpl();
        if (expl == null || StringUtils.isBlank(expl.getEvaluationDept())) {
            return true;
        }
        String evaluationDept = expl.getEvaluationDept();
        for (String deptName : permittedDepts) {
            if (StringUtils.isBlank(deptName)) {
                continue;
            }
            if (matchesEvaluationDept(evaluationDept, deptName)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesEvaluationDept(String evaluationDept, String candidateDept) {
        if (StringUtils.isBlank(evaluationDept) || StringUtils.isBlank(candidateDept)) {
            return false;
        }
        String normalizedCandidate = candidateDept.trim();
        if (StringUtils.isBlank(normalizedCandidate)) {
            return false;
        }
        String[] parts = evaluationDept.split("[,，;；、\\s]+");
        for (String part : parts) {
            if (StringUtils.isNotBlank(part) && part.trim().equals(normalizedCandidate)) {
                return true;
            }
        }
        return evaluationDept.contains(normalizedCandidate);
    }

    private String resolveDataStatus(List<String> statuses) {
        if (CollectionUtils.isEmpty(statuses)) {
            return "0";
        }
        Set<String> normalized = statuses.stream().filter(StringUtils::isNotBlank).map(String::trim).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(normalized)) {
            return "0";
        }
        if (normalized.size() == 1) {
            String status = normalized.iterator().next();
            if ("2".equals(status)) {
                return "2";
            }
            if ("1".equals(status)) {
                return "1";
            }
        }
        // 混合状态或未知状态，默认为草稿状态
        return "1";
    }


    @Override
    public boolean existsReviewHeaders(String reviewId) {
        if (StringUtils.isBlank(reviewId)) {
            return false;
        }
        return busDepReviewMapper.countHeadersByReviewId(reviewId) > 0;
    }


}
