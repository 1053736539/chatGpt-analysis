package com.cb.basedata.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.domain.vo.LivingExpensesSourceVo;
import com.cb.basedata.service.IBasBuildingCodeService;
import com.cb.ai.client.AIClient;
import com.cb.common.exception.ServiceException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.basedata.mapper.BasGasCostMapper;
import com.cb.basedata.domain.BasGasCost;
import com.cb.basedata.service.IBasGasCostService;

/**
 * 燃气费Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-25
 */
@Service
public class BasGasCostServiceImpl implements IBasGasCostService {
    private static final Logger log = LoggerFactory.getLogger(BasBuildingCodeServiceImpl.class);
    @Autowired
    private BasGasCostMapper basGasCostMapper;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;

    @Autowired
    private AIClient aiClient;

    /**
     * 查询燃气费
     *
     * @param id 燃气费ID
     * @return 燃气费
     */
    @Override
    public BasGasCost selectBasGasCostById(String id) {
        return basGasCostMapper.selectBasGasCostById(id);
    }

    @Override
    public BasGasCost selectBasGasCostBySourceId(String sourceId) {
        return basGasCostMapper.selectBasGasCostBySourceId(sourceId);
    }

    @Override
    public Boolean existBasGasCostBySourceId(String sourceId) {
        return basGasCostMapper.existBasGasCostBySourceId(sourceId);
    }

    /**
     * 查询燃气费列表
     *
     * @param basGasCost 燃气费
     * @return 燃气费
     */
    @Override
    public List<BasGasCost> selectBasGasCostList(BasGasCost basGasCost) {
        return basGasCostMapper.selectBasGasCostList(basGasCost);
    }

    @Override
    public List<BasGasCost> selectAllBasGasCostList() {
        return basGasCostMapper.selectAllBasGasCostList();
    }

    /**
     * 新增燃气费
     *
     * @param basGasCost 燃气费
     * @return 结果
     */
    @Override
    public int insertBasGasCost(BasGasCost basGasCost) {
        basGasCost.setId(IdUtils.randomUUID());
        basGasCost.setCreateTime(DateUtils.getNowDate());
        basGasCost.setCreateBy(SecurityUtils.getUsername());
        return basGasCostMapper.insertBasGasCost(basGasCost);
    }

    /**
     * 修改燃气费
     *
     * @param basGasCost 燃气费
     * @return 结果
     */
    @Override
    public int updateBasGasCost(BasGasCost basGasCost) {
        basGasCost.setUpdateTime(DateUtils.getNowDate());
        basGasCost.setUpdateBy(SecurityUtils.getUsername());
        return basGasCostMapper.updateBasGasCost(basGasCost);
    }

    /**
     * 批量删除燃气费
     *
     * @param ids 需要删除的燃气费ID
     * @return 结果
     */
    @Override
    public int deleteBasGasCostByIds(String[] ids) {
        return basGasCostMapper.deleteBasGasCostByIds(ids);
    }

    /**
     * 删除燃气费信息
     *
     * @param id 燃气费ID
     * @return 结果
     */
    @Override
    public int deleteBasGasCostById(String id) {
        return basGasCostMapper.deleteBasGasCostById(id);
    }

    @Override
    public String importBasGasCost(List<BasGasCost> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BasGasCost cost : list) {
            try {
                BasGasCost c = basGasCostMapper.checkExistence(cost);
                if (StringUtils.isNull(c)) {
                    this.insertBasGasCost(cost);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、燃气缴费 " + cost.getUserNumber() + " 导入成功");
                } else if (isUpdateSupport) {
                    cost.setId(c.getId());
                    this.updateBasGasCost(cost);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、燃气缴费 " + cost.getUserNumber() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、燃气缴费 " + cost.getUserNumber() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、燃气缴费 " + cost.getUserNumber() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
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
}
