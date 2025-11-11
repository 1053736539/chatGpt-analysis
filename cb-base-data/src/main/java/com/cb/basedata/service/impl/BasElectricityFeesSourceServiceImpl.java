package com.cb.basedata.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.domain.BasElectricityFees;
import com.cb.basedata.domain.BasElectricityFeesSource;
import com.cb.basedata.mapper.BasElectricityFeesSourceMapper;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.basedata.service.IBasElectricityFeesService;
import com.cb.basedata.service.IBasElectricityFeesSourceService;
import com.cb.common.exception.ServiceException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.framework.manager.AsyncManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 电费-原始数据Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-29
 */
@Service
public class BasElectricityFeesSourceServiceImpl implements IBasElectricityFeesSourceService {
    @Autowired
    private BasElectricityFeesSourceMapper basElectricityFeesSourceMapper;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;
    @Autowired
    private IBasElectricityFeesService electricityFeesService;

    @Autowired
    private AIClient aiClient;
    /**
     * 查询电费-原始数据
     *
     * @param sourceId 电费-原始数据ID
     * @return 电费-原始数据
     */
    @Override
    public BasElectricityFeesSource selectBasElectricityFeesSourceById(String sourceId) {
        return basElectricityFeesSourceMapper.selectBasElectricityFeesSourceById(sourceId);
    }

    /**
     * 查询电费-原始数据列表
     *
     * @param basElectricityFeesSource 电费-原始数据
     * @return 电费-原始数据
     */
    @Override
    public List<BasElectricityFeesSource> selectBasElectricityFeesSourceList(BasElectricityFeesSource basElectricityFeesSource) {
        return basElectricityFeesSourceMapper.selectBasElectricityFeesSourceList(basElectricityFeesSource);
    }

    /**
     * 新增电费-原始数据
     *
     * @param basElectricityFeesSource 电费-原始数据
     * @return 结果
     */
    @Override
    public int insertBasElectricityFeesSource(BasElectricityFeesSource basElectricityFeesSource) {
        basElectricityFeesSource.setSourceId(IdUtils.randomUUID());
        basElectricityFeesSource.setCreateTime(DateUtils.getNowDate());
        basElectricityFeesSource.setCreateBy(SecurityUtils.getUsername());
        return basElectricityFeesSourceMapper.insertBasElectricityFeesSource(basElectricityFeesSource);
    }

    /**
     * 修改电费-原始数据
     *
     * @param basElectricityFeesSource 电费-原始数据
     * @return 结果
     */
    @Override
    public int updateBasElectricityFeesSource(BasElectricityFeesSource basElectricityFeesSource) {
        basElectricityFeesSource.setUpdateTime(DateUtils.getNowDate());
        basElectricityFeesSource.setUpdateBy(SecurityUtils.getUsername());
        return basElectricityFeesSourceMapper.updateBasElectricityFeesSource(basElectricityFeesSource);
    }

    /**
     * 批量删除电费-原始数据
     *
     * @param sourceIds 需要删除的电费-原始数据ID
     * @return 结果
     */
    @Override
    public int deleteBasElectricityFeesSourceByIds(String[] sourceIds) {
        return basElectricityFeesSourceMapper.deleteBasElectricityFeesSourceByIds(sourceIds);
    }

    /**
     * 删除电费-原始数据信息
     *
     * @param sourceId 电费-原始数据ID
     * @return 结果
     */
    @Override
    public int deleteBasElectricityFeesSourceById(String sourceId) {
        return basElectricityFeesSourceMapper.deleteBasElectricityFeesSourceById(sourceId);
    }

    @Override
    public String importData(List<BasElectricityFeesSource> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<BasElectricityFeesSource> cleanList = new ArrayList<>();
        for (BasElectricityFeesSource source : list) {
            try {
                BasElectricityFeesSource r = basElectricityFeesSourceMapper.checkExistence(source);
                if (StringUtils.isNull(r)) {
                    this.insertBasElectricityFeesSource(source);
                    cleanList.add(source);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用电缴费 " + source.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    this.updateBasElectricityFeesSource(source);
                    cleanList.add(source);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用电缴费 " + source.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用电缴费 " + source.getUserName() + " 已存在");
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
        BasElectricityFeesSource source = basElectricityFeesSourceMapper.selectBasElectricityFeesSourceById(sourceId);
        map.put("source",source);
        BasElectricityFees fees = electricityFeesService.selectBasElectricityFeesBySourceId(sourceId);
        map.put("fees",fees);
        return map;
    }
}
