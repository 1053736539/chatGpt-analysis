package com.cb.adopt.service.impl;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cb.adopt.constant.AdoptConst;
import com.cb.adopt.domain.BizAdoptDept;
import com.cb.adopt.domain.BizAdoptRecord;
import com.cb.adopt.enums.DeptTypeEnum;
import com.cb.adopt.mapper.BizAdoptDeptMapper;
import com.cb.adopt.mapper.BizAdoptRecordMapper;
import com.cb.adopt.vo.AdoptVo;
import com.cb.common.core.domain.entity.SysDictData;
import com.cb.common.utils.DateUtils;
import com.cb.system.service.ISysDictTypeService;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.adopt.mapper.BizAdoptRecordScoreMapper;
import com.cb.adopt.domain.BizAdoptRecordScore;
import com.cb.adopt.service.IBizAdoptRecordScoreService;

import javax.servlet.http.HttpServletResponse;

/**
 * 信息采用记录的相关单位及分值记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BizAdoptRecordScoreServiceImpl implements IBizAdoptRecordScoreService {
    @Autowired
    private BizAdoptRecordScoreMapper bizAdoptRecordScoreMapper;
    @Autowired
    private BizAdoptDeptMapper bizAdoptDeptMapper;
    @Autowired
    private BizAdoptRecordMapper bizAdoptRecordMapper;

    @Autowired
    private ISysDictTypeService sysDictTypeService;

    /**
     * 查询信息采用记录的相关单位及分值记录
     *
     * @param id 信息采用记录的相关单位及分值记录ID
     * @return 信息采用记录的相关单位及分值记录
     */
    @Override
    public BizAdoptRecordScore selectBizAdoptRecordScoreById(String id) {
        return bizAdoptRecordScoreMapper.selectBizAdoptRecordScoreById(id);
    }

    /**
     * 查询信息采用记录的相关单位及分值记录列表
     *
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 信息采用记录的相关单位及分值记录
     */
    @Override
    public List<BizAdoptRecordScore> selectBizAdoptRecordScoreList(BizAdoptRecordScore bizAdoptRecordScore) {
        return bizAdoptRecordScoreMapper.selectBizAdoptRecordScoreList(bizAdoptRecordScore);
    }

    /**
     * 新增信息采用记录的相关单位及分值记录
     *
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 结果
     */
    @Override
    public int insertBizAdoptRecordScore(BizAdoptRecordScore bizAdoptRecordScore) {
        if (StringUtils.isBlank(bizAdoptRecordScore.getId())) {
            bizAdoptRecordScore.setId(IdUtils.randomUUID());
        }
        return bizAdoptRecordScoreMapper.insertBizAdoptRecordScore(bizAdoptRecordScore);
    }


    /**
     * 批量新增信息采用记录的相关单位及分值记录
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BizAdoptRecordScore> entities) {
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
            totalInserted += bizAdoptRecordScoreMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改信息采用记录的相关单位及分值记录
     *
     * @param bizAdoptRecordScore 信息采用记录的相关单位及分值记录
     * @return 结果
     */
    @Override
    public int updateBizAdoptRecordScore(BizAdoptRecordScore bizAdoptRecordScore) {
        return bizAdoptRecordScoreMapper.updateBizAdoptRecordScore(bizAdoptRecordScore);
    }

    /**
     * 批量删除信息采用记录的相关单位及分值记录
     *
     * @param ids 需要删除的信息采用记录的相关单位及分值记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptRecordScoreByIds(String[] ids) {
        return bizAdoptRecordScoreMapper.deleteBizAdoptRecordScoreByIds(ids);
    }

    /**
     * 删除信息采用记录的相关单位及分值记录信息
     *
     * @param id 信息采用记录的相关单位及分值记录ID
     * @return 结果
     */
    @Override
    public int deleteBizAdoptRecordScoreById(String id) {
        return bizAdoptRecordScoreMapper.deleteBizAdoptRecordScoreById(id);
    }

    @Override
    public void exportStatisticWord(HttpServletResponse response, AdoptVo.StatisticReq req) {
        String oBeginDate = req.getBeginDate();
        String oEndDate = req.getEndDate();
        Date beginDate = StringUtils.isBlank(oBeginDate) ? null : DateUtil.beginOfDay(DateUtil.parseDate(oBeginDate));
        Date endDate = StringUtils.isBlank(oEndDate) ? null : DateUtil.endOfDay(DateUtil.parseDate(oEndDate));
        Map<Integer, List<BizAdoptDept>> typeDeptListMap = bizAdoptDeptMapper.selectBizAdoptDeptList(new BizAdoptDept())
                .stream().collect(Collectors.groupingBy(BizAdoptDept::getType));
        Map<String, Object> data = new HashMap<>();
        data.put("nowDate", DateUtil.format(new Date(), "yyyy年M月d日"));
        data.put("beginDate", null == beginDate ? null : DateUtil.format(beginDate, "yyyy年M月"));
        data.put("endDate", null == endDate ? null : DateUtil.format(endDate, "yyyy年M月"));
        DeptTypeEnum[] deptTypes = DeptTypeEnum.values();
        for (DeptTypeEnum deptType : deptTypes) {
            List<AdoptVo.StatisticItem> typeDataList = new LinkedList<>();
            Map<String, AdoptVo.StatisticItem> deptCountMap = bizAdoptRecordScoreMapper.statisticByDeptTypeAndDateRange(deptType.getCode(), beginDate, endDate)
                    .stream().collect(Collectors.toMap(AdoptVo.StatisticItem::getDept, item -> item));
            List<BizAdoptDept> deptList = typeDeptListMap.get(deptType.getCode());
            for (BizAdoptDept dept : deptList) {
                String deptName = dept.getName();
                AdoptVo.StatisticItem item = deptCountMap.get(deptName);
                if (null == item) {
                    item = AdoptVo.StatisticItem.emptyEntity(deptName);
                }
                typeDataList.add(item);
            }
            data.put("list_" + deptType.getCode(), typeDataList);
        }
        try {
            ClassPathResource classPathResource = new ClassPathResource("tpl/word/statistic_word_tpl.docx");
            InputStream is = classPathResource.getInputStream();
            HackLoopTableRenderPolicy tablePolicy = new HackLoopTableRenderPolicy();
            Configure configure = Configure.builder()
                    .bind("list_1", tablePolicy)
                    .bind("list_2", tablePolicy)
                    .bind("list_3", tablePolicy)
                    .build();
            XWPFTemplate template = XWPFTemplate.compile(is, configure).render(data);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" +
                    URLEncoder.encode(String.format("%s至%s昆明市纪检监察信息采用情况", oBeginDate, oEndDate), "utf-8") + ".docx\"");
            template.write(response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void exportCarrierRecordWord(HttpServletResponse response, AdoptVo.StatisticReq req) {
        String oBeginDate = req.getBeginDate();
        String oEndDate = req.getEndDate();
        Date beginDate = StringUtils.isBlank(oBeginDate) ? null : DateUtil.beginOfDay(DateUtil.parseDate(oBeginDate));
        Date endDate = StringUtils.isBlank(oEndDate) ? null : DateUtil.endOfDay(DateUtil.parseDate(oEndDate));
        Map<String, Object> data = new HashMap<>();
        List<AdoptVo.CarrierRenderItem> itemList = new LinkedList<>();
        List<SysDictData> levelList = sysDictTypeService.selectDictDataByType(AdoptConst.LEVEL_DICT_TYPE);
        for (int i = 0; i < levelList.size(); i++) {
            String level = levelList.get(i).getDictLabel();
//            if(level.equals("领导批示")){
//                continue;
//            }
            List<SysDictData> carrierList = sysDictTypeService.selectDictDataByType(AdoptConst.LEVEL_CARRIER_DICT_TYPE_MAP.get(level));
            if (ObjectUtil.isEmpty(carrierList)) {
                List<AdoptVo.ListCarrierRecordScoreItem> recordScoreItemList = bizAdoptRecordScoreMapper.listCarrierRecordScore(level, null, beginDate, endDate);
                if(ObjectUtil.isNotEmpty(recordScoreItemList)){
                    AdoptVo.CarrierRenderItem renderItem = new AdoptVo.CarrierRenderItem();
                    renderItem.setLevel(level);
                    renderItem.setCarrier(null);
                    renderItem.setRecordList(convert(recordScoreItemList));
                    itemList.add(renderItem);
                }

            } else {
                for (int j = 0; j < carrierList.size(); j++) {
                    String carrier = carrierList.get(j).getDictLabel();
                    List<AdoptVo.ListCarrierRecordScoreItem> recordScoreItemList = bizAdoptRecordScoreMapper.listCarrierRecordScore(level, carrier, beginDate, endDate);
                    if(ObjectUtil.isNotEmpty(recordScoreItemList)) {
                        AdoptVo.CarrierRenderItem renderItem = new AdoptVo.CarrierRenderItem();
                        renderItem.setLevel(level);
                        renderItem.setCarrier(carrier);
                        renderItem.setRecordList(convert(recordScoreItemList));
                        itemList.add(renderItem);
                    }
                }
            }

        }
        data.put("list", itemList);
        try {
            ClassPathResource classPathResource = new ClassPathResource("tpl/word/carrier_record_tpl.docx");
            InputStream is = classPathResource.getInputStream();
            // 1. 设置循环策略
            HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
            Configure config = Configure.newBuilder()
                    .bind("recordList", policy)
                    .useSpringEL()
                    .build();
            XWPFTemplate template = XWPFTemplate.compile(is, config).render(data);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" +
                    URLEncoder.encode(String.format("%s至%s载体采用条目情况", oBeginDate, oEndDate), "utf-8") + ".docx\"");
            template.write(response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private List<AdoptVo.CarrierRecordItem> convert(List<AdoptVo.ListCarrierRecordScoreItem> list) {
        Map<String, List<AdoptVo.ListCarrierRecordScoreItem>> collect = list.stream().collect(Collectors.groupingBy(
                AdoptVo.ListCarrierRecordScoreItem::getRecordId,
                LinkedHashMap::new,  // 保持插入顺序
                Collectors.toList()
        ));
        List<AdoptVo.CarrierRecordItem> rst = collect.entrySet().stream().map(entry -> {
            AdoptVo.CarrierRecordItem item = new AdoptVo.CarrierRecordItem();
            item.setIssueNo(entry.getValue().get(0).getIssueNo());
            item.setTitle(entry.getValue().get(0).getTitle());
            String mainDept = buildDeptNameStr(entry.getValue().stream().filter(o -> o.getMainFlag().equals(1)).collect(Collectors.toList()));
            String adoptDept = buildDeptNameStr(entry.getValue().stream().filter(o -> o.getMainFlag().equals(2)).collect(Collectors.toList()));
            String otherDept = buildDeptNameStr(entry.getValue().stream().filter(o -> o.getMainFlag().equals(0)).collect(Collectors.toList()));
            //增加分值
            AdoptVo.ListCarrierRecordScoreItem mainItem = entry.getValue().stream().filter(o -> o.getMainFlag().equals(1)).findAny().orElse(null);
            AdoptVo.ListCarrierRecordScoreItem adoptItem = entry.getValue().stream().filter(o -> o.getMainFlag().equals(2)).findAny().orElse(null);
            AdoptVo.ListCarrierRecordScoreItem otherItem = entry.getValue().stream().filter(o -> o.getMainFlag().equals(0)).findAny().orElse(null);
            if(null != mainItem){
                mainDept += "(" + mainItem.getScore() + ")";
            }
            if(null != adoptItem){
                adoptDept += "(" + adoptItem.getScore() + ")";
            }
            if(null != otherItem){
                otherDept += "(" + otherItem.getScore() + ")";
            }
            item.setMainDept(mainDept);
            item.setAdoptDept(adoptDept);
            item.setOtherDept(otherDept);
            Object[] noBlankStrArr = Arrays.stream(new String[]{mainDept, adoptDept, otherDept}).filter(o -> StringUtils.isNotBlank(o)).toArray();
            String fullDept = StringUtils.joinWith( "，", noBlankStrArr ).replaceFirst("^，|，/","");
            item.setFullDept(fullDept);
            return item;
        }).collect(Collectors.toList());
        for (int i = 0; i < rst.size(); i++) {
            rst.get(i).setIndexNo(i + 1);
        }
        return rst;
    }

    private String buildDeptNameStr(List<AdoptVo.ListCarrierRecordScoreItem> list){
        if(ObjectUtil.isEmpty(list)){
            return "";
        }
        String names1 = list.stream().filter(o -> o.getDeptType().equals(1))
                .sorted(Comparator.comparing(AdoptVo.ListCarrierRecordScoreItem::getDeptOrderNo))
                .map(item -> item.getDept()).collect(Collectors.joining("、"));
        String names2 = list.stream().filter(o -> !o.getDeptType().equals(1))
                .sorted(Comparator.comparing(AdoptVo.ListCarrierRecordScoreItem::getDeptOrderNo))
                .map(item -> item.getDept()).collect(Collectors.joining("、"));
        if(StringUtils.isNotBlank(names1)){
            names1 += "纪委监委";
        }
        Object[] noBlankStrArr = Arrays.stream(new String[]{names1, names2}).filter(o -> StringUtils.isNotBlank(o)).toArray();
        String fullName = StringUtils.joinWith( "、", noBlankStrArr).replaceFirst("^、|、/","");
        return fullName;
    }

}
