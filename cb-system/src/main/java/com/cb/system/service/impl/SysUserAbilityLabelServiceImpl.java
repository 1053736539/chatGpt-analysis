package com.cb.system.service.impl;

import java.util.List;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.SysUserAbilityLabelMapper;
import com.cb.common.core.domain.entity.SysUserAbilityLabel;
import com.cb.system.service.ISysUserAbilityLabelService;

/**
 * 干部能力标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-22
 */
@Service
public class SysUserAbilityLabelServiceImpl implements ISysUserAbilityLabelService 
{
    @Autowired
    private SysUserAbilityLabelMapper sysUserAbilityLabelMapper;

    /**
     * 查询干部能力标签
     * 
     * @param id 干部能力标签ID
     * @return 干部能力标签
     */
    @Override
    public SysUserAbilityLabel selectSysUserAbilityLabelById(Integer id)
    {
        return sysUserAbilityLabelMapper.selectSysUserAbilityLabelById(id);
    }

    /**
     * 查询干部能力标签列表
     * 
     * @param sysUserAbilityLabel 干部能力标签
     * @return 干部能力标签
     */
    @Override
    public List<SysUserAbilityLabel> selectSysUserAbilityLabelList(SysUserAbilityLabel sysUserAbilityLabel)
    {
        return sysUserAbilityLabelMapper.selectSysUserAbilityLabelList(sysUserAbilityLabel);
    }

    /**
     * 新增干部能力标签
     * 
     * @param sysUserAbilityLabel 干部能力标签
     * @return 结果
     */
    @Override
    public int insertSysUserAbilityLabel(SysUserAbilityLabel sysUserAbilityLabel)
    {
        sysUserAbilityLabel.setCreateTime(DateUtils.getNowDate());
        sysUserAbilityLabel.setCreateBy(SecurityUtils.getUsername());
        return sysUserAbilityLabelMapper.insertSysUserAbilityLabel(sysUserAbilityLabel);
    }

    /**
     * 修改干部能力标签
     * 
     * @param sysUserAbilityLabel 干部能力标签
     * @return 结果
     */
    @Override
    public int updateSysUserAbilityLabel(SysUserAbilityLabel sysUserAbilityLabel)
    {
        sysUserAbilityLabel.setUpdateTime(DateUtils.getNowDate());
        sysUserAbilityLabel.setUpdateBy(SecurityUtils.getUsername());
        return sysUserAbilityLabelMapper.updateSysUserAbilityLabel(sysUserAbilityLabel);
    }

    /**
     * 批量删除干部能力标签
     * 
     * @param ids 需要删除的干部能力标签ID
     * @return 结果
     */
    @Override
    public int deleteSysUserAbilityLabelByIds(Integer[] ids)
    {
        return sysUserAbilityLabelMapper.deleteSysUserAbilityLabelByIds(ids);
    }

    /**
     * 删除干部能力标签信息
     * 
     * @param id 干部能力标签ID
     * @return 结果
     */
    @Override
    public int deleteSysUserAbilityLabelById(Integer id)
    {
        return sysUserAbilityLabelMapper.deleteSysUserAbilityLabelById(id);
    }
    /**
     * 查询干部能力标签下拉选项
     *
     * @param sysUserAbilityLabel 干部能力标签
     * @return 干部能力标签
     */
    @Override
    public List<SysUserAbilityLabel> selectAbilityLabelList(SysUserAbilityLabel sysUserAbilityLabel)
    {
        return sysUserAbilityLabelMapper.selectAbilityLabelList(sysUserAbilityLabel);
    }
}
