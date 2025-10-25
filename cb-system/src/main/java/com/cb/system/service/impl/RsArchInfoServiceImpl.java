package com.cb.system.service.impl;

import java.util.List;

import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.RsArchInfoMapper;
import com.cb.common.core.domain.entity.RsArchInfo;
import com.cb.system.service.IRsArchInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 干部档案目录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
@Service
public class RsArchInfoServiceImpl implements IRsArchInfoService 
{
    private static final Logger log = LoggerFactory.getLogger(RsArchInfoServiceImpl.class);
    @Autowired
    private RsArchInfoMapper rsArchInfoMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询干部档案目录
     * 
     * @param id 干部档案目录ID
     * @return 干部档案目录
     */
    @Override
    public RsArchInfo selectRsArchInfoById(Integer id)
    {
        return rsArchInfoMapper.selectRsArchInfoById(id);
    }

    /**
     * 查询干部档案目录列表
     * 
     * @param rsArchInfo 干部档案目录
     * @return 干部档案目录
     */
    @Override
    public List<RsArchInfo> selectRsArchInfoList(RsArchInfo rsArchInfo)
    {
        return rsArchInfoMapper.selectRsArchInfoList(rsArchInfo);
    }

    /**
     * 新增干部档案目录
     * 
     * @param rsArchInfo 干部档案目录
     * @return 结果
     */
    @Override
    public int insertRsArchInfo(RsArchInfo rsArchInfo)
    {
        rsArchInfo.setCreateTime(DateUtils.getNowDate());
        rsArchInfo.setUpdateBy(SecurityUtils.getUsername());
        return rsArchInfoMapper.insertRsArchInfo(rsArchInfo);
    }

    /**
     * 修改干部档案目录
     * 
     * @param rsArchInfo 干部档案目录
     * @return 结果
     */
    @Override
    public int updateRsArchInfo(RsArchInfo rsArchInfo)
    {
        rsArchInfo.setUpdateTime(DateUtils.getNowDate());
        rsArchInfo.setUpdateBy(SecurityUtils.getUsername());
        return rsArchInfoMapper.updateRsArchInfo(rsArchInfo);
    }

    /**
     * 批量删除干部档案目录
     * 
     * @param ids 需要删除的干部档案目录ID
     * @return 结果
     */
    @Override
    public int deleteRsArchInfoByIds(Integer[] ids)
    {
        return rsArchInfoMapper.deleteRsArchInfoByIds(ids);
    }

    /**
     * 删除干部档案目录信息
     * 
     * @param id 干部档案目录ID
     * @return 结果
     */
    @Override
    public int deleteRsArchInfoById(Integer id)
    {
        return rsArchInfoMapper.deleteRsArchInfoById(id);
    }

    @Override
    public String importRsArchInfo(List<RsArchInfo> rsArchInfoList, String operName) {
        if (StringUtils.isNull(rsArchInfoList) || rsArchInfoList.size() == 0) {
            throw new CustomException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (RsArchInfo rsArchInfo : rsArchInfoList) {
            try {
                    rsArchInfo.setCreateBy(operName);
                    rsArchInfo.setCreateTime(DateUtils.getNowDate());
                    this.importRsArchInfo(rsArchInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、档案名称 " + rsArchInfo.getMaterialName() + " 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + rsArchInfo.getMaterialName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Transactional
    public int importRsArchInfo(RsArchInfo rsArchInfo) {
        // 导入新增档案目录信息
        int rows = rsArchInfoMapper.insertRsArchInfo(rsArchInfo);
        return rows;
    }

    @Override
    public List<RsArchInfo> selectArchInfoCatalogList(RsArchInfo rsArchInfo) {
        return rsArchInfoMapper.selectArchInfoCatalogList(rsArchInfo);
    }

}
