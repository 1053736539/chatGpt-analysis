package com.cb.system.service.impl;

import java.util.List;
import java.util.UUID;

import cn.hutool.core.util.IdUtil;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.RsDescriptMapper;
import com.cb.common.core.domain.entity.RsDescript;
import com.cb.system.service.IRsDescriptService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 干部档案附件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-25
 */
@Service
public class RsDescriptServiceImpl implements IRsDescriptService 
{
    private static final Logger log = LoggerFactory.getLogger(RsDescriptServiceImpl.class);
    @Autowired
    private RsDescriptMapper rsDescriptMapper;

    /**
     * 查询干部档案附件信息
     * 
     * @param id 干部档案附件信息ID
     * @return 干部档案附件信息
     */
    @Override
    public RsDescript selectRsDescriptById(Integer id)
    {
        return rsDescriptMapper.selectRsDescriptById(id);
    }

    /**
     * 查询干部档案附件信息列表
     * 
     * @param rsDescript 干部档案附件信息
     * @return 干部档案附件信息
     */
    @Override
    public List<RsDescript> selectRsDescriptList(RsDescript rsDescript)
    {
        return rsDescriptMapper.selectRsDescriptList(rsDescript);
    }

    /**
     * 新增干部档案附件信息
     * 
     * @param rsDescript 干部档案附件信息
     * @return 结果
     */
    @Override
    public int insertRsDescript(RsDescript rsDescript)
    {
        rsDescript.setCreateTime(DateUtils.getNowDate());
        rsDescript.setCreateBy(SecurityUtils.getUsername());
        String uuid1 = IdUtils.simpleUUID();
        String uuid2 = IdUtils.simpleUUID();
        rsDescript.setPdfKey(uuid1);
        rsDescript.setGaoqingPdfKey(uuid2);
        return rsDescriptMapper.insertRsDescript(rsDescript);
    }

    /**
     * 修改干部档案附件信息
     * 
     * @param rsDescript 干部档案附件信息
     * @return 结果
     */
    @Override
    public int updateRsDescript(RsDescript rsDescript)
    {
        rsDescript.setUpdateTime(DateUtils.getNowDate());
        rsDescript.setUpdateBy(SecurityUtils.getUsername());
        return rsDescriptMapper.updateRsDescript(rsDescript);
    }

    /**
     * 批量删除干部档案附件信息
     * 
     * @param ids 需要删除的干部档案附件信息ID
     * @return 结果
     */
    @Override
    public int deleteRsDescriptByIds(Integer[] ids)
    {
        return rsDescriptMapper.deleteRsDescriptByIds(ids);
    }

    /**
     * 删除干部档案附件信息信息
     * 
     * @param id 干部档案附件信息ID
     * @return 结果
     */
    @Override
    public int deleteRsDescriptById(Integer id)
    {
        return rsDescriptMapper.deleteRsDescriptById(id);
    }

    @Override
    public String importRsDescript(List<RsDescript> rsDescriptList, String operName) {
        if (StringUtils.isNull(rsDescriptList) || rsDescriptList.size() == 0) {
            throw new CustomException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (RsDescript rsDescript : rsDescriptList) {
            try {
                rsDescript.setCreateBy(operName);
                rsDescript.setCreateTime(DateUtils.getNowDate());
                if(StringUtils.isNull(rsDescript.getPdfKey())||StringUtils.isEmpty(rsDescript.getPdfKey())){
                    String uuid1 = IdUtils.simpleUUID();
                    rsDescript.setPdfKey(uuid1);
                }

                if(StringUtils.isNull(rsDescript.getGaoqingPdfKey())||StringUtils.isEmpty(rsDescript.getGaoqingPdfKey())){
                    String uuid2 = IdUtils.simpleUUID();
                    rsDescript.setGaoqingPdfKey(uuid2);
                }
                this.importRsArchInfo(rsDescript);
                successNum++;
                successMsg.append("<br/>" + successNum + "、档案附件信息 "  + " 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、档案附件信息 "  + " 导入失败：";
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
    public int importRsArchInfo(RsDescript rsDescript) {
        // 导入新增档案目录信息
        int rows = rsDescriptMapper.insertRsDescript(rsDescript);
        return rows;
    }
}
