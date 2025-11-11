package com.cb.worksituation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.mapper.BusDepReviewDataMapper;
import com.cb.worksituation.service.IBusDepReviewDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 评分数据Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-11
 */
@Service
public class BusDepReviewDataServiceImpl implements IBusDepReviewDataService {
    @Autowired
    private BusDepReviewDataMapper busDepReviewDataMapper;

    /**
     * 查询评分数据
     *
     * @param id 评分数据ID
     * @return 评分数据
     */
    @Override
    public BusDepReviewData selectBusDepReviewDataById(String id) {
        return busDepReviewDataMapper.selectBusDepReviewDataById(id);
    }

    /**
     * 查询评分数据列表
     *
     * @param busDepReviewData 评分数据
     * @return 评分数据
     */
    @Override
    public List<BusDepReviewData> selectBusDepReviewDataList(BusDepReviewData busDepReviewData) {
        return busDepReviewDataMapper.selectBusDepReviewDataList(busDepReviewData);
    }

    /**
     * 新增评分数据
     *
     * @param busDepReviewData 评分数据
     * @return 结果
     */
    @Override
    public int insertBusDepReviewData(BusDepReviewData busDepReviewData) {
        if (StringUtils.isBlank(busDepReviewData.getId())) {
            busDepReviewData.setId(IdUtils.randomUUID());
        }
        String dataJson = busDepReviewData.getDataJson();
        if (!StringUtils.isBlank(dataJson)) {
            JSONArray jsonArray = JSONArray.parseArray(dataJson);
            populateAttachmentInfo(busDepReviewData, jsonArray);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String multDeptScore = jsonObject.getString("multDeptScore");
                    if (StringUtils.isNotBlank(multDeptScore) && multDeptScore.equals("1")) {
                        String headCode = jsonObject.getString("headCode");
                        if (StringUtils.isBlank(headCode)) {
                            continue;
                        }
                        String listKey = headCode + "List";
                        JSONArray headCodeArray = extractJSONArray(jsonObject.get(listKey));
                        headCodeArray.add(jsonObject);
                        jsonObject.put(listKey, headCodeArray);
                    }
                }
            }
        } else {
            populateAttachmentInfo(busDepReviewData, null);
        }
        try {
            busDepReviewData.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        busDepReviewData.setCreateTime(DateUtils.getNowDate());
        return busDepReviewDataMapper.insertBusDepReviewData(busDepReviewData);
    }

    public static boolean isJsonArray(String input) {
        if (StringUtils.isBlank(input)) {
            return false;
        }
        try {
            JSONArray.parseArray(input);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private JSONArray extractJSONArray(Object value) {
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        if (value instanceof String && isJsonArray((String) value)) {
            JSONArray parsed = JSONArray.parseArray((String) value);
            return parsed == null ? new JSONArray() : parsed;
        }
        return new JSONArray();
    }


    /**
     * 批量新增评分数据
     *
     * @param entities
     * @return 结果
     */
    @Override
    public int insertBatch(List<BusDepReviewData> entities) {
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
            String dataJson = item.getDataJson();
            JSONArray jsonArray = StringUtils.isBlank(dataJson) ? null : JSONArray.parseArray(dataJson);
            populateAttachmentInfo(item, jsonArray);
        });
        // 定义每批次的大小
        int batchSize = 500;
        int totalInserted = 0;
        int num = entities.size() / batchSize + (entities.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < num; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, entities.size());
            totalInserted += busDepReviewDataMapper.insertBatch(entities.subList(start, end));
        }
        return totalInserted;
    }


    /**
     * 修改评分数据
     *
     * @param busDepReviewData 评分数据
     * @return 结果
     */
    @Override
    public int saveBusDepReviewData(BusDepReviewData busDepReviewData) {
        String dataJson = busDepReviewData.getDataJson();
        if (!StringUtils.isBlank(dataJson)) {
            BusDepReviewData depReviewData = busDepReviewDataMapper.selectBusDepReviewDataById(busDepReviewData.getId());
            JSONArray jsonArray;
            if (depReviewData != null) {
                String reviewDataDataJson = depReviewData.getDataJson();
                JSONArray backendArray = StringUtils.isNotBlank(reviewDataDataJson) ? JSONArray.parseArray(reviewDataDataJson) : new JSONArray();
                JSONArray frontendArray = JSONArray.parseArray(dataJson);
                jsonArray = mergeJsonArrays(backendArray, frontendArray);
            } else {
                jsonArray = JSONArray.parseArray(dataJson);
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String multDeptScore = jsonObject.getString("multDeptScore");
                if (StringUtils.isNotBlank(multDeptScore) && multDeptScore.equals("1")) {
                    String headCode = jsonObject.getString(jsonObject.getString("headCode") + "List");
                    if (isJsonArray(headCode)) {
                        JSONArray headCodeArray = JSONArray.parseArray(headCode);
                        for (int j = 0; j < headCodeArray.size(); j++) {
                            JSONObject jsonArrayJSONObject = jsonArray.getJSONObject(j);
                            if (Objects.equals(SecurityUtils.getOnlineDept().getDeptName(), jsonArrayJSONObject.getString("deptName"))) {
                                headCodeArray.remove(j);
                                break;
                            }
                        }
                        JSONObject copyObject = deepCopyJsonObject(jsonObject);
                        copyObject.put("deptName", SecurityUtils.getOnlineDept().getDeptName());
                        copyObject.remove(jsonObject.getString("headCode") + "List");
                        headCodeArray.add(copyObject);
                        jsonObject.put(jsonObject.getString("headCode") + "List", headCodeArray);
                    } else {
                        JSONArray headCodeArray = new JSONArray();
                        JSONObject copyObject = deepCopyJsonObject(jsonObject);
                        copyObject.put("deptName", SecurityUtils.getOnlineDept().getDeptName());
                        copyObject.remove(jsonObject.getString("headCode") + "List");
                        headCodeArray.add(copyObject);
                        jsonObject.put(jsonObject.getString("headCode") + "List", headCodeArray);
                    }
                }
            }
            busDepReviewData.setDataJson(jsonArray.toString());
        }
        if (null == busDepReviewData || busDepReviewData.getId() == null) {
            busDepReviewData.setId(IdUtils.randomUUID());
            busDepReviewData.setCreateTime(DateUtils.getNowDate());
            busDepReviewData.setCreateBy(SecurityUtils.getUsername());
            return busDepReviewDataMapper.insertBusDepReviewData(busDepReviewData);
        } else {
            busDepReviewData.setUpdateTime(DateUtils.getNowDate());
            busDepReviewData.setUpdateBy(SecurityUtils.getUsername());
            return busDepReviewDataMapper.updateBusDepReviewData(busDepReviewData);
        }

    }


    private JSONArray mergeJsonArrays(JSONArray backendArray, JSONArray frontendArray) {
        // 创建后端数据的headCode映射，便于快速查找
        Map<String, JSONObject> backendMap = createHeadCodeMapping(backendArray);

        // 处理前端数据
        JSONArray resultArray = new JSONArray();

        // 先添加所有后端数据（后续会被前端数据覆盖相同headCode的项）
        resultArray.addAll(backendArray);

        // 遍历前端数据，进行合并
        for (int i = 0; i < frontendArray.size(); i++) {
            JSONObject frontendObj = frontendArray.getJSONObject(i);
            String headCode = frontendObj.getString("headCode");

            if (StringUtils.isBlank(headCode)) {
                continue; // 跳过没有headCode的数据
            }

            // 查找是否已存在相同headCode的数据
            boolean found = false;
            for (int j = 0; j < resultArray.size(); j++) {
                JSONObject backendObj = resultArray.getJSONObject(j);
                String backendHeadCode = backendObj.getString("headCode");

                if (headCode.equals(backendHeadCode)) {
                    // 替换现有数据（保持原有位置）
                    JSONObject mergedObj = mergeJsonObjects(backendObj, frontendObj);
                    resultArray.set(j, mergedObj);
                    found = true;
                    break;
                }
            }

            if (!found) {
                // 新增数据
                resultArray.add(frontendObj);
            }
        }

        return resultArray;
    }

    /**
     * 创建headCode到JSON对象的映射
     */
    private Map<String, JSONObject> createHeadCodeMapping(JSONArray jsonArray) {
        Map<String, JSONObject> mapping = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String headCode = obj.getString("headCode");
            if (StringUtils.isNotBlank(headCode)) {
                mapping.put(headCode, obj);
            }
        }
        return mapping;
    }


    /**
     * 合并两个JSON对象（前端数据优先）
     */
    private JSONObject mergeJsonObjects(JSONObject backendObj, JSONObject frontendObj) {
        JSONObject merged = new JSONObject();

        // 先添加后端数据的所有字段
        merged.putAll(backendObj);

        // 用前端数据覆盖（前端数据优先）
        for (String key : frontendObj.keySet()) {
            merged.put(key, frontendObj.get(key));
        }

        return merged;
    }

    @Override
    public int submitGrading(BusDepReviewData busDepReviewData) {
        saveBusDepReviewData(busDepReviewData);
        busDepReviewData.setUpdateBy(SecurityUtils.getUsername());
        String dataJson = busDepReviewData.getDataJson();
        if (!StringUtils.isBlank(dataJson)) {
            // 对一对多进行求和
            JSONArray jsonArray = JSONArray.parseArray(dataJson);
            // 对分组进行 定量评价得分 加分 求和
            // 定量评价得分（70分)小计 headCode subtotal_quan_score headType =1
            // 定性评价得分（30分）headCode qualitative_evaluation_score
            // 加分小计 headCode bonus_subtotal headType =2
            // 扣分 headCode deduct_points
            // 党建业务协作单元加分 headCode bus_unit_bonus
            // 业务评价得分=定量评价小计+定性评价得分+加分小计-扣分
            // 分类(1-定量评价得分（70分,2-加分,3-其他)
            BigDecimal subtotalQuanScore = new BigDecimal(0);
            BigDecimal bonusSubtotal = new BigDecimal(0);
            BigDecimal qualitativeEvaluationScore = new BigDecimal(0);
            BigDecimal deductPoints = new BigDecimal(0);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String multDeptScore = jsonObject.getString("multDeptScore");
                // 对多次填报进行求和
                if (StringUtils.isNotBlank(multDeptScore) && multDeptScore.equals("1")) {
                    String headCode = jsonObject.getString(jsonObject.getString("headCode") + "List");
                    // 求和
                    BigDecimal headScoreTotal = new BigDecimal(0);
                    if (StringUtils.isNotBlank(headCode)) {
                        JSONArray headCodeArray = JSONArray.parseArray(headCode);
                        for (int j = 0; j < headCodeArray.size(); j++) {
                            JSONObject headCodeArrayJSONObject = headCodeArray.getJSONObject(j);
                            BigDecimal headScore = headCodeArrayJSONObject.getBigDecimal("headScore");
                            headScoreTotal = headScoreTotal.add(headScore);
                        }
                        // 赋值给表格
                        jsonObject.put("headScore", headScoreTotal);
                    }
                }
                String headType = jsonObject.getString("headType");
                if (StringUtils.isNotBlank(headType) && headType.equals("2")) {
                    subtotalQuanScore = jsonObject.getBigDecimal("headScore").add(subtotalQuanScore);
                } else if (StringUtils.isNotBlank(headType) && headType.equals("4")) {
                    bonusSubtotal = jsonObject.getBigDecimal("headScore").add(bonusSubtotal);
                }
                String headCode = jsonObject.getString("headCode");

                if (StringUtils.isNotBlank(headCode) && headCode.equals("qualitative_evaluation_score")) {
                    qualitativeEvaluationScore = qualitativeEvaluationScore.add(jsonObject.getBigDecimal("headScore"));
                }

                if (StringUtils.isNotBlank(headCode) && headCode.equals("deduct_points")) {
                    deductPoints = deductPoints.add(jsonObject.getBigDecimal("headScore"));
                }
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String headCode = jsonObject.getString("headCode");
                if (StringUtils.isNotBlank(headCode) && headCode.equals("subtotal_quan_score")) {
                    jsonObject.put("headScore", subtotalQuanScore);
                }
                if (StringUtils.isNotBlank(headCode) && headCode.equals("bonus_subtotal")) {
                    jsonObject.put("headScore", bonusSubtotal);
                }
            }
            busDepReviewData.setDataJson(jsonArray.toString());
            busDepReviewData.setSubtotalQuanScore(subtotalQuanScore.toString());
            busDepReviewData.setBonusSubtotal(bonusSubtotal.toString());
            // reviewScore
            busDepReviewData.setReviewScore(subtotalQuanScore.add(bonusSubtotal).add(qualitativeEvaluationScore).subtract(deductPoints));
        }
        busDepReviewData.setUpdateTime(DateUtils.getNowDate());
        return busDepReviewDataMapper.updateBusDepReviewData(busDepReviewData);
    }

    /**
     * 批量删除评分数据
     *
     * @param ids 需要删除的评分数据ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewDataByIds(String[] ids) {
        return busDepReviewDataMapper.deleteBusDepReviewDataByIds(ids);
    }

    /**
     * 删除评分数据信息
     *
     * @param id 评分数据ID
     * @return 结果
     */
    @Override
    public int deleteBusDepReviewDataById(String id) {
        return busDepReviewDataMapper.deleteBusDepReviewDataById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int replaceReviewDataForCurrentUser(String reviewId, List<BusDepReviewData> busDepReviewDatas) {
        if (StringUtils.isBlank(reviewId) || busDepReviewDatas == null || busDepReviewDatas.isEmpty()) {
            return 0;
        }
        String username = SecurityUtils.getUsername();
        busDepReviewDataMapper.deleteByReviewIdAndCreator(reviewId);
        int rows = 0;
        for (BusDepReviewData busDepReviewData : busDepReviewDatas) {
            if (busDepReviewData == null) {
                continue;
            }
            busDepReviewData.setBusDepReviewId(reviewId);
            rows += insertBusDepReviewData(busDepReviewData);
        }
        return rows;
    }

    @Override
    public int submitReviewData(String reviewId) {
        if (StringUtils.isBlank(reviewId)) {
            return 0;
        }
        String username = null;
        try {
            username = SecurityUtils.getUsername();
        } catch (Exception e) {
        }
        if (StringUtils.isBlank(username)) {
            return 0;
        }
        Date now = DateUtils.getNowDate();
        return busDepReviewDataMapper.updateDataStatusByReviewIdAndCreator(reviewId, username, "2", username, now);
    }

    private void populateAttachmentInfo(BusDepReviewData busDepReviewData, JSONArray jsonArray) {
        busDepReviewData.setFilePath(null);
        busDepReviewData.setAttachId(null);
        if (jsonArray == null || jsonArray.isEmpty()) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject == null) {
                continue;
            }
            String filePath = jsonObject.getString("filePath");
            if (StringUtils.isNotBlank(filePath)) {
                busDepReviewData.setFilePath(filePath);
            }
            String attachId = jsonObject.getString("attachId");
            if (StringUtils.isNotBlank(attachId)) {
                busDepReviewData.setAttachId(attachId);
            }
        }
    }

    private static JSONObject deepCopyJsonObject(JSONObject original) {
        if (original == null) return null;

        // 通过序列化和反序列化实现深拷贝
        String jsonStr = JSON.toJSONString(original, SerializerFeature.DisableCircularReferenceDetect);
        return JSON.parseObject(jsonStr);
    }
}
