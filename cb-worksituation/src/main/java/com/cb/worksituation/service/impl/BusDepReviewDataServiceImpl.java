package com.cb.worksituation.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.worksituation.domain.BusDepReviewData;
import com.cb.worksituation.mapper.BusDepReviewDataMapper;
import com.cb.worksituation.service.IBusDepReviewDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String multDeptScore = jsonObject.getString("multDeptScore");
                if (StringUtils.isNotBlank(multDeptScore) && multDeptScore.equals("1")) {
                    String headCode = jsonObject.getString(jsonObject.getString("headCode") + "List");
                    if (isJsonArray(headCode)) {
                        JSONArray headCodeArray = JSONArray.parseArray(headCode);
                        headCodeArray.add(jsonObject);
                        jsonObject.put(jsonObject.getString("headCode") + "List", headCodeArray);
                        jsonArray.add(jsonObject);
                    } else {
                        JSONArray headCodeArray = new JSONArray();
                        headCodeArray.add(jsonObject);
                        jsonObject.put(jsonObject.getString("headCode") + "List", headCodeArray);
                        jsonArray.add(jsonObject);
                    }
                }
            }

        }
        try {
            busDepReviewData.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        busDepReviewData.setCreateTime(DateUtils.getNowDate());
        return busDepReviewDataMapper.insertBusDepReviewData(busDepReviewData);
    }

    public static boolean isJsonArray(String input) {
        try {
            JSONArray.parseArray(input);
            return true;
        } catch (JSONException e) {
            return false;
        }
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
    public int updateBusDepReviewData(BusDepReviewData busDepReviewData) {
        try {
            busDepReviewData.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
        String dataJson = busDepReviewData.getDataJson();
        if (!StringUtils.isBlank(dataJson)) {
            JSONArray jsonArray = JSONArray.parseArray(dataJson);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String multDeptScore = jsonObject.getString("multDeptScore");
                if (StringUtils.isNotBlank(multDeptScore) && multDeptScore.equals("1")) {
                    String headCode = jsonObject.getString(jsonObject.getString("headCode") + "List");
                    if (isJsonArray(headCode)) {
                        JSONArray headCodeArray = JSONArray.parseArray(headCode);
                        headCodeArray.add(jsonObject);
                        jsonObject.put(jsonObject.getString("headCode") + "List", headCodeArray);
                        jsonArray.add(jsonObject);
                    } else {
                        JSONArray headCodeArray = new JSONArray();
                        headCodeArray.add(jsonObject);
                        jsonObject.put(jsonObject.getString("headCode") + "List", headCodeArray);
                        jsonArray.add(jsonObject);
                    }
                }
            }

        }
        busDepReviewData.setUpdateTime(DateUtils.getNowDate());
        return busDepReviewDataMapper.updateBusDepReviewData(busDepReviewData);
    }

    @Override
    public int submitGrading(BusDepReviewData busDepReviewData) {
        try {
            busDepReviewData.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e) {
        }
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
                    JSONArray headCodeArray = JSONArray.parseArray(headCode);
                    for (int j = 0; j < headCodeArray.size(); j++) {
                        JSONObject headCodeArrayJSONObject = headCodeArray.getJSONObject(i);
                        BigDecimal headScore = headCodeArrayJSONObject.getBigDecimal("headScore");
                        headScoreTotal = headScoreTotal.add(headScore);
                    }
                    // 赋值给表格
                    jsonObject.put("headScore", headScoreTotal);
                }
                String headType = jsonObject.getString("headType");
                if (StringUtils.isNotBlank(headType) && headType.equals("1")) {
                    subtotalQuanScore = jsonObject.getBigDecimal("headScore").add(subtotalQuanScore);
                } else if (StringUtils.isNotBlank(headType) && headType.equals("2")) {
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
            // reviewScore
            busDepReviewData.setReviewScore(subtotalQuanScore.add(bonusSubtotal).add(qualitativeEvaluationScore).add(deductPoints));
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


    public static void main(String[] args) {
        String s = "[{\"aa\":1,\"bb\":[{\"cc\":5},{\"cc\":6}]},{\"aa\":3,\"bb\":[{\"cc\":7},{\"cc\":6}]}]";
        JSONArray jsonArray = JSONArray.parseArray(s);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray bb = jsonObject.getJSONArray("bb");
            BigDecimal headScoreTotal = new BigDecimal(0);
            for (int j = 0; j < bb.size(); j++) {
                JSONObject object = bb.getJSONObject(j);
                BigDecimal cc = object.getBigDecimal("cc");
                headScoreTotal = headScoreTotal.add(cc);
            }
            jsonObject.put("aa", headScoreTotal.toString());
        }

        System.out.println(jsonArray);
    }
}
