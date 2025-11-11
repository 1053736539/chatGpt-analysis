package com.cb.basedata.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.domain.BasWaterRent;
import com.cb.basedata.domain.BasWaterRentSource;
import com.cb.basedata.mapper.BasWaterRentSourceMapper;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.basedata.service.IBasWaterRentService;
import com.cb.basedata.service.IBasWaterRentSourceService;
import com.cb.common.exception.ServiceException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.framework.manager.AsyncManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 水费-原始数据Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-29
 */
@Service
public class BasWaterRentSourceServiceImpl implements IBasWaterRentSourceService {
    @Autowired
    private BasWaterRentSourceMapper basWaterRentSourceMapper;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;
    @Autowired
    private IBasWaterRentService rentService;


    /**
     * 查询水费-原始数据
     *
     * @param sourceId 水费-原始数据ID
     * @return 水费-原始数据
     */
    @Override
    public BasWaterRentSource selectBasWaterRentSourceById(String sourceId) {
        return basWaterRentSourceMapper.selectBasWaterRentSourceById(sourceId);
    }

    /**
     * 查询水费-原始数据列表
     *
     * @param basWaterRentSource 水费-原始数据
     * @return 水费-原始数据
     */
    @Override
    public List<BasWaterRentSource> selectBasWaterRentSourceList(BasWaterRentSource basWaterRentSource) {
        return basWaterRentSourceMapper.selectBasWaterRentSourceList(basWaterRentSource);
    }

    /**
     * 新增水费-原始数据
     *
     * @param basWaterRentSource 水费-原始数据
     * @return 结果
     */
    @Override
    public int insertBasWaterRentSource(BasWaterRentSource basWaterRentSource) {
        basWaterRentSource.setSourceId(IdUtils.randomUUID());
        basWaterRentSource.setCreateTime(DateUtils.getNowDate());
        basWaterRentSource.setCreateBy(SecurityUtils.getUsername());
        return basWaterRentSourceMapper.insertBasWaterRentSource(basWaterRentSource);
    }

    /**
     * 修改水费-原始数据
     *
     * @param basWaterRentSource 水费-原始数据
     * @return 结果
     */
    @Override
    public int updateBasWaterRentSource(BasWaterRentSource basWaterRentSource) {
        basWaterRentSource.setUpdateTime(DateUtils.getNowDate());
        basWaterRentSource.setUpdateBy(SecurityUtils.getUsername());
        return basWaterRentSourceMapper.updateBasWaterRentSource(basWaterRentSource);
    }

    /**
     * 批量删除水费-原始数据
     *
     * @param sourceIds 需要删除的水费-原始数据ID
     * @return 结果
     */
    @Override
    public int deleteBasWaterRentSourceByIds(String[] sourceIds) {
        return basWaterRentSourceMapper.deleteBasWaterRentSourceByIds(sourceIds);
    }

    /**
     * 删除水费-原始数据信息
     *
     * @param sourceId 水费-原始数据ID
     * @return 结果
     */
    @Override
    public int deleteBasWaterRentSourceById(String sourceId) {
        return basWaterRentSourceMapper.deleteBasWaterRentSourceById(sourceId);
    }

    @Override
    public String importData(List<BasWaterRentSource> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<BasWaterRentSource> cleanList = new ArrayList<>();
        for (BasWaterRentSource source : list) {
            try {
                BasWaterRentSource r = basWaterRentSourceMapper.checkExistence(source);
                if (StringUtils.isNull(r)) {
                    this.insertBasWaterRentSource(source);
                    cleanList.add(source);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用水缴费 " + source.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    this.updateBasWaterRentSource(source);
                    cleanList.add(source);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用水缴费 " + source.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用水缴费 " + source.getUserName() + " 已存在");
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
        BasWaterRentSource source = basWaterRentSourceMapper.selectBasWaterRentSourceById(sourceId);
        map.put("source",source);
        BasWaterRent rent = rentService.selectBasWaterRentBySourceId(sourceId);
        map.put("rent",rent);
        return map;
    }
}
