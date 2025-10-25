package com.cb.message.service;

import com.cb.message.domain.SysAnnouncementSend;

import java.util.List;

/**
 * 系统消息Service接口
 * 
 * @author ouyang
 * @date 2023-07-12
 */
public interface ISysAnnouncementSendService 
{
    /**
     * 查询系统消息
     * 
     * @param id 系统消息ID
     * @return 系统消息
     */
    public SysAnnouncementSend selectSysAnnouncementSendById(String id);

    /**
     * 查询系统消息列表
     * 
     * @param sysAnnouncementSend 系统消息
     * @return 系统消息集合
     */
    public List<SysAnnouncementSend> selectSysAnnouncementSendList(SysAnnouncementSend sysAnnouncementSend);

    /**
     * 新增系统消息
     * 
     * @param sysAnnouncementSend 系统消息
     * @return 结果
     */
    public int insertSysAnnouncementSend(SysAnnouncementSend sysAnnouncementSend);

    /**
     * 修改系统消息
     * 
     * @param sysAnnouncementSend 系统消息
     * @return 结果
     */
    public int updateSysAnnouncementSend(SysAnnouncementSend sysAnnouncementSend);

    /**
     * 批量删除系统消息
     * 
     * @param ids 需要删除的系统消息ID
     * @return 结果
     */
    public int deleteSysAnnouncementSendByIds(String[] ids);

    /**
     * 删除系统消息信息
     * 
     * @param id 系统消息ID
     * @return 结果
     */
    public int deleteSysAnnouncementSendById(String id);
}
