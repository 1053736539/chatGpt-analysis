package com.cb.message.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.message.domain.SysAnnouncementSend;
import com.cb.message.mapper.SysAnnouncementSendMapper;
import com.cb.message.service.ISysAnnouncementSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统消息Service业务层处理
 * 
 * @author ouyang
 * @date 2023-07-12
 */
@Service
public class SysAnnouncementSendServiceImpl implements ISysAnnouncementSendService
{
    @Autowired
    private SysAnnouncementSendMapper sysAnnouncementSendMapper;

    /**
     * 查询系统消息
     * 
     * @param id 系统消息ID
     * @return 系统消息
     */
    @Override
    public SysAnnouncementSend selectSysAnnouncementSendById(String id)
    {
        return sysAnnouncementSendMapper.selectSysAnnouncementSendById(id);
    }

    /**
     * 查询系统消息列表
     * 
     * @param sysAnnouncementSend 系统消息
     * @return 系统消息
     */
    @Override
    public List<SysAnnouncementSend> selectSysAnnouncementSendList(SysAnnouncementSend sysAnnouncementSend)
    {
        return sysAnnouncementSendMapper.selectSysAnnouncementSendList(sysAnnouncementSend);
    }

    /**
     * 新增系统消息
     * 
     * @param sysAnnouncementSend 系统消息
     * @return 结果
     */
    @Override
    public int insertSysAnnouncementSend(SysAnnouncementSend sysAnnouncementSend)
    {
        sysAnnouncementSend.setCreateTime(DateUtils.getNowDate());
        return sysAnnouncementSendMapper.insertSysAnnouncementSend(sysAnnouncementSend);
    }

    /**
     * 修改系统消息
     * 
     * @param sysAnnouncementSend 系统消息
     * @return 结果
     */
    @Override
    public int updateSysAnnouncementSend(SysAnnouncementSend sysAnnouncementSend)
    {
        sysAnnouncementSend.setUpdateTime(DateUtils.getNowDate());
        return sysAnnouncementSendMapper.updateSysAnnouncementSend(sysAnnouncementSend);
    }

    /**
     * 批量删除系统消息
     * 
     * @param ids 需要删除的系统消息ID
     * @return 结果
     */
    @Override
    public int deleteSysAnnouncementSendByIds(String[] ids)
    {
        return sysAnnouncementSendMapper.deleteSysAnnouncementSendByIds(ids);
    }

    /**
     * 删除系统消息信息
     * 
     * @param id 系统消息ID
     * @return 结果
     */
    @Override
    public int deleteSysAnnouncementSendById(String id)
    {
        return sysAnnouncementSendMapper.deleteSysAnnouncementSendById(id);
    }
}
