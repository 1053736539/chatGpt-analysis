package com.cb.system.mapper;

import com.cb.common.core.domain.entity.RsInfo;

import java.util.List;
import java.util.Map;

/**
 * 档案系统用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
public interface RsInfoMapper 
{
    /**
     * 查询档案系统用户信息
     * 
     * @param id 档案系统用户信息ID
     * @return 档案系统用户信息
     */
    public RsInfo selectRsInfoById(Integer id);

    /**
     * 查询档案系统用户信息列表
     * 
     * @param rsInfo 档案系统用户信息
     * @return 档案系统用户信息集合
     */
    public List<RsInfo> selectRsInfoList(RsInfo rsInfo);

    /**
     * 新增档案系统用户信息
     * 
     * @param rsInfo 档案系统用户信息
     * @return 结果
     */
    public int insertRsInfo(RsInfo rsInfo);

    /**
     * 修改档案系统用户信息
     * 
     * @param rsInfo 档案系统用户信息
     * @return 结果
     */
    public int updateRsInfo(RsInfo rsInfo);

    /**
     * 删除档案系统用户信息
     * 
     * @param id 档案系统用户信息ID
     * @return 结果
     */
    public int deleteRsInfoById(Integer id);

    /**
     * 批量删除档案系统用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRsInfoByIds(Integer[] ids);

    /**
     * 导出档案人员信息数据
     *
     * @param params 查询参数
     * @return 结果
     */
    List<RsInfo> exportArchivesUserInfoData(Map<String, Object> params);
}
