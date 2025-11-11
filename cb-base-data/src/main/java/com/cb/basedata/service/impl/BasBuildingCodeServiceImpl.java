package com.cb.basedata.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cb.common.exception.ServiceException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.basedata.mapper.BasBuildingCodeMapper;
import com.cb.basedata.domain.BasBuildingCode;
import com.cb.basedata.service.IBasBuildingCodeService;


/**
 * 楼盘编码Service业务层处理
 *
 * @author ouyan
 * @date 2024-10-25
 */
@Service
public class BasBuildingCodeServiceImpl implements IBasBuildingCodeService {
    private static final Logger log = LoggerFactory.getLogger(BasBuildingCodeServiceImpl.class);

    @Autowired
    private BasBuildingCodeMapper basBuildingCodeMapper;

    /**
     * 查询楼盘编码
     *
     * @param id 楼盘编码ID
     * @return 楼盘编码
     */
    @Override
    public BasBuildingCode selectBasBuildingCodeById(String id) {
        return basBuildingCodeMapper.selectBasBuildingCodeById(id);
    }

    /**
     * 查询楼盘编码列表
     *
     * @param basBuildingCode 楼盘编码
     * @return 楼盘编码
     */
    @Override
    public List<BasBuildingCode> selectBasBuildingCodeList(BasBuildingCode basBuildingCode) {
        return basBuildingCodeMapper.selectBasBuildingCodeList(basBuildingCode);
    }

    /**
     * 新增楼盘编码
     *
     * @param basBuildingCode 楼盘编码
     * @return 结果
     */
    @Override
    public int insertBasBuildingCode(BasBuildingCode basBuildingCode) {
        basBuildingCode.setId(IdUtils.randomUUID());
        basBuildingCode.setCreateTime(DateUtils.getNowDate());
        basBuildingCode.setCreateBy(SecurityUtils.getUsername());
        return basBuildingCodeMapper.insertBasBuildingCode(basBuildingCode);
    }

    /**
     * 修改楼盘编码
     *
     * @param basBuildingCode 楼盘编码
     * @return 结果
     */
    @Override
    public int updateBasBuildingCode(BasBuildingCode basBuildingCode) {
        BasBuildingCode old = basBuildingCodeMapper.selectBasBuildingCodeById(basBuildingCode.getId());
        String oldCode = old.getCode();
        // TODO 检查当前编码下面是否存在楼盘信息，存在禁止修改code
        if (!oldCode.equals(basBuildingCode.getCode())) {

        }
        basBuildingCode.setUpdateBy(SecurityUtils.getUsername());
        basBuildingCode.setUpdateTime(DateUtils.getNowDate());
        return basBuildingCodeMapper.updateBasBuildingCode(basBuildingCode);
    }

    /**
     * 批量删除楼盘编码
     *
     * @param ids 需要删除的楼盘编码ID
     * @return 结果
     */
    @Override
    public int deleteBasBuildingCodeByIds(String[] ids) {
        //TODO 检查当前编码下面是否存在楼盘信息,存在禁止删除
        return basBuildingCodeMapper.deleteBasBuildingCodeByIds(ids);
    }

    /**
     * 删除楼盘编码信息
     *
     * @param id 楼盘编码ID
     * @return 结果
     */
    @Override
    public int deleteBasBuildingCodeById(String id) {
        return basBuildingCodeMapper.deleteBasBuildingCodeById(id);
    }

    @Override
    public String importBasBuildingCode(List<BasBuildingCode> list, Boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BasBuildingCode buildingCode : list) {
            try {
                BasBuildingCode b = basBuildingCodeMapper.selectBasBuildingCodeByCode(buildingCode.getCode());
                if (StringUtils.isNull(b)) {
                    this.insertBasBuildingCode(buildingCode);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、楼盘 " + buildingCode.getName() + " 导入成功");
                } else if (isUpdateSupport) {
                    buildingCode.setId(b.getId());
                    this.updateBasBuildingCode(buildingCode);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、楼盘 " + buildingCode.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、楼盘 " + buildingCode.getName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、楼盘 " + buildingCode.getName() + " 导入失败：";
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

    @Override
    public Map<String, BasBuildingCode> selectBasBuildingCodeMap() {
        List<BasBuildingCode> list = basBuildingCodeMapper.selectBasBuildingCodeAllList();
        Map<String, BasBuildingCode> codeMap = list.stream().collect(Collectors.toMap(BasBuildingCode::getAddress, o -> o));
        return codeMap;
    }
}
