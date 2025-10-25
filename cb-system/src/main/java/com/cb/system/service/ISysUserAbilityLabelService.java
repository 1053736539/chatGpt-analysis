package com.cb.system.service;

import java.util.List;
import com.cb.common.core.domain.entity.SysUserAbilityLabel;

/**
 * 干部能力标签Service接口
 * 
 * @author ruoyi
 * @date 2025-02-22
 */
public interface ISysUserAbilityLabelService 
{
    /**
     * 查询干部能力标签
     * 
     * @param id 干部能力标签ID
     * @return 干部能力标签
     */
    public SysUserAbilityLabel selectSysUserAbilityLabelById(Integer id);

    /**
     * 查询干部能力标签列表
     * 
     * @param sysUserAbilityLabel 干部能力标签
     * @return 干部能力标签集合
     */
    public List<SysUserAbilityLabel> selectSysUserAbilityLabelList(SysUserAbilityLabel sysUserAbilityLabel);

    /**
     * 新增干部能力标签
     * 
     * @param sysUserAbilityLabel 干部能力标签
     * @return 结果
     */
    public int insertSysUserAbilityLabel(SysUserAbilityLabel sysUserAbilityLabel);

    /**
     * 修改干部能力标签
     * 
     * @param sysUserAbilityLabel 干部能力标签
     * @return 结果
     */
    public int updateSysUserAbilityLabel(SysUserAbilityLabel sysUserAbilityLabel);

    /**
     * 批量删除干部能力标签
     * 
     * @param ids 需要删除的干部能力标签ID
     * @return 结果
     */
    public int deleteSysUserAbilityLabelByIds(Integer[] ids);

    /**
     * 删除干部能力标签信息
     * 
     * @param id 干部能力标签ID
     * @return 结果
     */
    public int deleteSysUserAbilityLabelById(Integer id);
    /**
     * 查询干部能力标签下拉列表
     *
     * @param sysUserAbilityLabel 干部能力标签
     * @return 干部能力标签集合
     */
    public List<SysUserAbilityLabel> selectAbilityLabelList(SysUserAbilityLabel sysUserAbilityLabel);
}
