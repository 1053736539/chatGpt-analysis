package com.cb.user.mapper;

import com.cb.user.domain.CensusUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 普查人员Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface CensusUserInfoMapper 
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
     * 删除普查人员
     * 
     * @param userId 普查人员ID
     * @return 结果
     */
    public int deleteCensusUserInfoById(String userId);

    /**
     * 批量删除普查人员
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCensusUserInfoByIds(String[] userIds);

    /**
     * 通过用户名查询普查人员用户
     *
     * @param userName 查人员用户名
     * @return 用户对象信息
     */
    public CensusUserInfo selectUserByUserName(String userName);

    /**
     * 根据 censusNumber 删除用户数据
     * @param censusNumber 普查编号
     * @return 删除的记录数
     */
    int deleteUsersByCensusNumber(@Param("censusNumber") int censusNumber);

}
