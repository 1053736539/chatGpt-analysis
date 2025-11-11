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
import com.cb.basedata.mapper.BasWaterRentMapper;
import com.cb.basedata.domain.BasWaterRent;
import com.cb.basedata.service.IBasWaterRentService;

/**
 * 水费Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-25
 */
@Service
public class BasWaterRentServiceImpl implements IBasWaterRentService {
    private static final Logger log = LoggerFactory.getLogger(BasWaterRentServiceImpl.class);
    @Autowired
    private BasWaterRentMapper basWaterRentMapper;
    /**
     * 查询水费
     *
     * @param id 水费ID
     * @return 水费
     */
    @Override
    public BasWaterRent selectBasWaterRentById(String id) {
        return basWaterRentMapper.selectBasWaterRentById(id);
    }

    @Override
    public BasWaterRent selectBasWaterRentBySourceId(String sourceId) {
        return basWaterRentMapper.selectBasWaterRentBySourceId(sourceId);
    }

    @Override
    public Boolean existBasWaterRentBySourceId(String sourceId) {
        return basWaterRentMapper.existBasWaterRentBySourceId(sourceId);
    }

    /**
     * 查询水费列表
     *
     * @param basWaterRent 水费
     * @return 水费
     */
    @Override
    public List<BasWaterRent> selectBasWaterRentList(BasWaterRent basWaterRent) {
        return basWaterRentMapper.selectBasWaterRentList(basWaterRent);
    }

    @Override
    public List<BasWaterRent> selectAllBasWaterRentList() {
        return basWaterRentMapper.selectAllBasWaterRentList();
    }

    /**
     * 新增水费
     *
     * @param basWaterRent 水费
     * @return 结果
     */
    @Override
    public int insertBasWaterRent(BasWaterRent basWaterRent) {
        basWaterRent.setId(IdUtils.randomUUID());
        basWaterRent.setCreateTime(DateUtils.getNowDate());
        basWaterRent.setCreateBy(SecurityUtils.getUsername());
        return basWaterRentMapper.insertBasWaterRent(basWaterRent);
    }

    /**
     * 修改水费
     *
     * @param basWaterRent 水费
     * @return 结果
     */
    @Override
    public int updateBasWaterRent(BasWaterRent basWaterRent) {
        basWaterRent.setUpdateTime(DateUtils.getNowDate());
        basWaterRent.setUpdateBy(SecurityUtils.getUsername());
        return basWaterRentMapper.updateBasWaterRent(basWaterRent);
    }

    /**
     * 批量删除水费
     *
     * @param ids 需要删除的水费ID
     * @return 结果
     */
    @Override
    public int deleteBasWaterRentByIds(String[] ids) {
        return basWaterRentMapper.deleteBasWaterRentByIds(ids);
    }

    /**
     * 删除水费信息
     *
     * @param id 水费ID
     * @return 结果
     */
    @Override
    public int deleteBasWaterRentById(String id) {
        return basWaterRentMapper.deleteBasWaterRentById(id);
    }

    @Override
    public String importBasWaterRent(List<BasWaterRent> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BasWaterRent rent : list) {
            try {
                BasWaterRent r = basWaterRentMapper.checkExistence(rent);
                if (StringUtils.isNull(r)) {
                    this.insertBasWaterRent(rent);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用水缴费 " + rent.getUserNumber() + " 导入成功");
                } else if (isUpdateSupport) {
                    rent.setId(r.getId());
                    this.updateBasWaterRent(rent);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用水缴费 " + rent.getUserNumber() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用水缴费 " + rent.getUserNumber() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、用水缴费 " + rent.getUserNumber() + " 导入失败：";
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
}
