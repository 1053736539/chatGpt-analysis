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
import com.cb.basedata.mapper.BasElectricityFeesMapper;
import com.cb.basedata.domain.BasElectricityFees;
import com.cb.basedata.service.IBasElectricityFeesService;

/**
 * 电费Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-25
 */
@Service
public class BasElectricityFeesServiceImpl implements IBasElectricityFeesService {
    private static final Logger log = LoggerFactory.getLogger(BasBuildingCodeServiceImpl.class);
    @Autowired
    private BasElectricityFeesMapper basElectricityFeesMapper;
    @Autowired
    private IBasBuildingCodeService basBuildingCodeService;

    @Autowired
    private AIClient aiClient;

    /**
     * 查询电费
     *
     * @param id 电费ID
     * @return 电费
     */
    @Override
    public BasElectricityFees selectBasElectricityFeesById(String id) {
        return basElectricityFeesMapper.selectBasElectricityFeesById(id);
    }

    @Override
    public BasElectricityFees selectBasElectricityFeesBySourceId(String sourceId) {
        return basElectricityFeesMapper.selectBasElectricityFeesBySourceId(sourceId);
    }

    @Override
    public Boolean existBasElectricityFeesBySourceId(String sourceId) {
        return basElectricityFeesMapper.existBasElectricityFeesBySourceId(sourceId);
    }

    /**
     * 查询电费列表
     *
     * @param basElectricityFees 电费
     * @return 电费
     */
    @Override
    public List<BasElectricityFees> selectBasElectricityFeesList(BasElectricityFees basElectricityFees) {
        return basElectricityFeesMapper.selectBasElectricityFeesList(basElectricityFees);
    }

    @Override
    public List<BasElectricityFees> selectAllBasElectricityFeesList() {
        return basElectricityFeesMapper.selectAllBasElectricityFeesList();
    }

    /**
     * 新增电费
     *
     * @param basElectricityFees 电费
     * @return 结果
     */
    @Override
    public int insertBasElectricityFees(BasElectricityFees basElectricityFees) {
        basElectricityFees.setId(IdUtils.randomUUID());
        basElectricityFees.setCreateTime(DateUtils.getNowDate());
        basElectricityFees.setCreateBy(SecurityUtils.getUsername());
        return basElectricityFeesMapper.insertBasElectricityFees(basElectricityFees);
    }

    /**
     * 修改电费
     *
     * @param basElectricityFees 电费
     * @return 结果
     */
    @Override
    public int updateBasElectricityFees(BasElectricityFees basElectricityFees) {
        basElectricityFees.setUpdateTime(DateUtils.getNowDate());
        basElectricityFees.setUpdateBy(SecurityUtils.getUsername());
        return basElectricityFeesMapper.updateBasElectricityFees(basElectricityFees);
    }

    /**
     * 批量删除电费
     *
     * @param ids 需要删除的电费ID
     * @return 结果
     */
    @Override
    public int deleteBasElectricityFeesByIds(String[] ids) {
        return basElectricityFeesMapper.deleteBasElectricityFeesByIds(ids);
    }

    /**
     * 删除电费信息
     *
     * @param id 电费ID
     * @return 结果
     */
    @Override
    public int deleteBasElectricityFeesById(String id) {
        return basElectricityFeesMapper.deleteBasElectricityFeesById(id);
    }

    @Override
    public String importBasElectricityFees(List<BasElectricityFees> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BasElectricityFees fees : list) {
            try {
                BasElectricityFees f = basElectricityFeesMapper.checkExistence(fees);
                if (StringUtils.isNull(f)) {
                    this.insertBasElectricityFees(fees);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用电缴费 " + fees.getUserNumber() + " 导入成功");
                } else if (isUpdateSupport) {
                    fees.setId(f.getId());
                    this.updateBasElectricityFees(fees);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用电缴费 " + fees.getUserNumber() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用电缴费 " + fees.getUserNumber() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、用电缴费 " + fees.getUserNumber() + " 导入失败：";
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
