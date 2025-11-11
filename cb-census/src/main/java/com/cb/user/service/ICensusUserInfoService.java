package com.cb.user.service;

import com.cb.user.domain.CensusUserInfo;
import org.apache.avalon.framework.service.ServiceException;

import java.util.List;


/**
 * 普查人员Service接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface ICensusUserInfoService 
{
    /**
     * 查询普查人员
     * 
     * @param userId 普查人员ID
     * @return 普查人员
     */
    public CensusUserInfo selectCensusUserInfoById(String userId);

    /**
     * 查询普查人员列表
     * 
     * @param censusUserInfo 普查人员
     * @return 普查人员集合
     */
    public List<CensusUserInfo> selectCensusUserInfoList(CensusUserInfo censusUserInfo);

    /**
     * 新增普查人员
     * 
     * @param censusUserInfo 普查人员
     * @return 结果
     */
    public int insertCensusUserInfo(CensusUserInfo censusUserInfo);

    /**
     * 修改普查人员
     * 
     * @param censusUserInfo 普查人员
     * @return 结果
     */
    public int updateCensusUserInfo(CensusUserInfo censusUserInfo);

    /**
     * 批量删除普查人员
     * 
     * @param userIds 需要删除的普查人员ID
     * @return 结果
     */
    public int deleteCensusUserInfoByIds(String[] userIds);

    /**
     * 删除普查人员信息
     * 
     * @param userId 普查人员ID
     * @return 结果
     */
    public int deleteCensusUserInfoById(String userId);

    /**
     * 导入普查人员信息数据
     *
     * @param censusUserList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importCensusUserInfo(List<CensusUserInfo> censusUserList, Boolean isUpdateSupport) throws ServiceException;
    public String importCensusUserInfo1(List<CensusUserInfo> censusUserList,Integer censusNumber) throws ServiceException;

}
