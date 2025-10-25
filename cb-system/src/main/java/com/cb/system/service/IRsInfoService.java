package com.cb.system.service;

import java.util.List;
import com.cb.common.core.domain.entity.RsInfo;

/**
 * 档案系统用户信息Service接口
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
public interface IRsInfoService 
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
     * 批量删除档案系统用户信息
     * 
     * @param ids 需要删除的档案系统用户信息ID
     * @return 结果
     */
    public int deleteRsInfoByIds(Integer[] ids);

    /**
     * 删除档案系统用户信息信息
     * 
     * @param id 档案系统用户信息ID
     * @return 结果
     */
    public int deleteRsInfoById(Integer id);

    /**
     * 档案系统用户信息导入
     * @param rsInfoList
     * @param operName
     * @return
     */
    public String importRsInfo(List<RsInfo> rsInfoList, String operName);
}
