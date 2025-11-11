package com.cb.basedata.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.domain.BasGasCost;
import com.cb.basedata.domain.BasGasCostSource;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.basedata.service.IBasGasCostService;
import com.cb.common.exception.ServiceException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.framework.manager.AsyncManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.basedata.mapper.BasGasCostSourceMapper;
import com.cb.basedata.service.IBasGasCostSourceService;

/**
 * 燃气费-原始数据Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-29
 */
@Service
public class BasGasCostSourceServiceImpl implements IBasGasCostSourceService {
    @Autowired
    private BasGasCostSourceMapper basGasCostSourceMapper;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;
    @Autowired
    private IBasGasCostService gasCostService;

    @Autowired
    private AIClient aiClient;

    /**
     * 查询燃气费-原始数据
     *
     * @param sourceId 燃气费-原始数据ID
     * @return 燃气费-原始数据
     */
    @Override
    public BasGasCostSource selectBasGasCostSourceById(String sourceId) {
        return basGasCostSourceMapper.selectBasGasCostSourceById(sourceId);
    }

    /**
     * 查询燃气费-原始数据列表
     *
     * @param basGasCostSource 燃气费-原始数据
     * @return 燃气费-原始数据
     */
    @Override
    public List<BasGasCostSource> selectBasGasCostSourceList(BasGasCostSource basGasCostSource) {
        return basGasCostSourceMapper.selectBasGasCostSourceList(basGasCostSource);
    }

    /**
     * 新增燃气费-原始数据
     *
     * @param basGasCostSource 燃气费-原始数据
     * @return 结果
     */
    @Override
    public int insertBasGasCostSource(BasGasCostSource basGasCostSource) {
        basGasCostSource.setSourceId(IdUtils.randomUUID());
        basGasCostSource.setCreateTime(DateUtils.getNowDate());
        basGasCostSource.setCreateBy(SecurityUtils.getUsername());
        return basGasCostSourceMapper.insertBasGasCostSource(basGasCostSource);
    }

    /**
     * 修改燃气费-原始数据
     *
     * @param basGasCostSource 燃气费-原始数据
     * @return 结果
     */
    @Override
    public int updateBasGasCostSource(BasGasCostSource basGasCostSource) {
        basGasCostSource.setUpdateTime(DateUtils.getNowDate());
        basGasCostSource.setUpdateBy(SecurityUtils.getUsername());
        return basGasCostSourceMapper.updateBasGasCostSource(basGasCostSource);
    }

    /**
     * 批量删除燃气费-原始数据
     *
     * @param sourceIds 需要删除的燃气费-原始数据ID
     * @return 结果
     */
    @Override
    public int deleteBasGasCostSourceByIds(String[] sourceIds) {
        return basGasCostSourceMapper.deleteBasGasCostSourceByIds(sourceIds);
    }

    /**
     * 删除燃气费-原始数据信息
     *
     * @param sourceId 燃气费-原始数据ID
     * @return 结果
     */
    @Override
    public int deleteBasGasCostSourceById(String sourceId) {
        return basGasCostSourceMapper.deleteBasGasCostSourceById(sourceId);
    }

    @Override
    public String importData(List<BasGasCostSource> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<BasGasCostSource> cleanList = new ArrayList<>();
        for (BasGasCostSource source : list) {
            try {
                BasGasCostSource r = basGasCostSourceMapper.checkExistence(source);
                if (StringUtils.isNull(r)) {
                    this.insertBasGasCostSource(source);
                    cleanList.add(source);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、燃气缴费 " + source.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    this.updateBasGasCostSource(source);
                    cleanList.add(source);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、燃气缴费 " + source.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、燃气缴费 " + source.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、用水缴费 " + source.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
    @Override
    public Map<String, Object> dataComparison(String sourceId) {
        HashMap<String, Object> map = new HashMap<>();
        BasGasCostSource source = basGasCostSourceMapper.selectBasGasCostSourceById(sourceId);
        map.put("source",source);
        BasGasCost cost = gasCostService.selectBasGasCostBySourceId(sourceId);
        map.put("cost",cost);
        return map;
    }
}
