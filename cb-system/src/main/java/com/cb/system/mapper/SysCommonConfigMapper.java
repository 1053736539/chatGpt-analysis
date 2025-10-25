package com.cb.system.mapper;

import com.cb.system.domain.SysCommonConfig;

import java.util.List;

/**
 * 系统配置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface SysCommonConfigMapper
{
    /**
     * 查询系统配置
     * 
     * @param id 系统配置ID
     * @return 系统配置
     */
    public SysCommonConfig selectSysCommonConfigById(Long id);

    /**
     * 查询系统配置列表
     * 
     * @param SysCommonConfig 系统配置
     * @return 系统配置集合
     */
    public List<SysCommonConfig> selectSysCommonConfigList(SysCommonConfig SysCommonConfig);

    /**
     * 新增系统配置
     * 
     * @param SysCommonConfig 系统配置
     * @return 结果
     */
    public int insertSysCommonConfig(SysCommonConfig SysCommonConfig);

    /**
     * 修改系统配置
     * 
     * @param SysCommonConfig 系统配置
     * @return 结果
     */
    public int updateSysCommonConfig(SysCommonConfig SysCommonConfig);

    /**
     * 删除系统配置
     * 
     * @param id 系统配置ID
     * @return 结果
     */
    public int deleteSysCommonConfigById(Long id);

    /**
     * 批量删除系统配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCommonConfigByIds(Long[] ids);
}
