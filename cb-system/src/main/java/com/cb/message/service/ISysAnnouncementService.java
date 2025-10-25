package com.cb.message.service;

import com.cb.message.domain.SysAnnouncement;

import java.util.List;

/**
 * 系统消息Service接口
 * 
 * @author ouyang
 * @date 2023-07-12
 */
public interface ISysAnnouncementService 
{
    /**
     * 查询系统消息
     * 
     * @param id 系统消息ID
     * @return 系统消息
     */
    public SysAnnouncement selectSysAnnouncementById(String id);

    /**
     * 查询系统消息列表
     * 
     * @param sysAnnouncement 系统消息
     * @return 系统消息集合
     */
    public List<SysAnnouncement> selectSysAnnouncementList(SysAnnouncement sysAnnouncement);

    /**
     * 新增系统消息
     * 
     * @param sysAnnouncement 系统消息
     * @return 结果
     */
    public int insertSysAnnouncement(SysAnnouncement sysAnnouncement);

    /**
     * 修改系统消息
     * 
     * @param sysAnnouncement 系统消息
     * @return 结果
     */
    public int updateSysAnnouncement(SysAnnouncement sysAnnouncement);

    /**
     * 批量删除系统消息
     * 
     * @param ids 需要删除的系统消息ID
     * @return 结果
     */
    public int deleteSysAnnouncementByIds(String[] ids);

    /**
     * 删除系统消息信息
     * 
     * @param id 系统消息ID
     * @return 结果
     */
    public int deleteSysAnnouncementById(String id);

    /**
     * 消息发布
     * @return
     */
    public int releaseAnnouncement(String id);

    /**
     * 撤销
     * @param id
     * @return
     */
    public int cancellationAnnouncement(String id);

    /**
     * 根据bizId删除消息及消息发送记录
     * @return
     */
    public int deleteByBizId(String bizId);

}
