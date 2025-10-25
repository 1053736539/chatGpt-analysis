package com.cb.message.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.message.domain.SysAnnouncement;
import com.cb.message.domain.SysAnnouncementSend;
import com.cb.message.mapper.SysAnnouncementMapper;
import com.cb.message.mapper.SysAnnouncementSendMapper;
import com.cb.message.service.ISysAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统消息Service业务层处理
 *
 * @author ouyang
 * @date 2023-07-12
 */
@Service
public class SysAnnouncementServiceImpl implements ISysAnnouncementService {
    @Autowired
    private SysAnnouncementMapper sysAnnouncementMapper;

    @Autowired
    private SysAnnouncementSendMapper sendMapper;

    /**
     * 查询系统消息
     *
     * @param id 系统消息ID
     * @return 系统消息
     */
    @Override
    public SysAnnouncement selectSysAnnouncementById(String id) {
        return sysAnnouncementMapper.selectSysAnnouncementById(id);
    }

    /**
     * 查询系统消息列表
     *
     * @param sysAnnouncement 系统消息
     * @return 系统消息
     */
    @Override
    public List<SysAnnouncement> selectSysAnnouncementList(SysAnnouncement sysAnnouncement) {
        return sysAnnouncementMapper.selectSysAnnouncementList(sysAnnouncement);
    }

    /**
     * 新增系统消息
     *
     * @param sysAnnouncement 系统消息
     * @return 结果
     */
    @Override
    public int insertSysAnnouncement(SysAnnouncement sysAnnouncement) {
        if(StringUtils.isBlank(sysAnnouncement.getId())){
            sysAnnouncement.setId(IdUtils.randomUUID());
        }
        if(StringUtils.isBlank(sysAnnouncement.getCreateBy())){
            try{
                String username = SecurityUtils.getUsername();
                sysAnnouncement.setCreateBy(username);
            } catch (Exception e){}
        }
        sysAnnouncement.setCreateTime(DateUtils.getNowDate());
        return sysAnnouncementMapper.insertSysAnnouncement(sysAnnouncement);
    }

    /**
     * 修改系统消息
     *
     * @param sysAnnouncement 系统消息
     * @return 结果
     */
    @Override
    public int updateSysAnnouncement(SysAnnouncement sysAnnouncement) {
        String username = SecurityUtils.getUsername();
        sysAnnouncement.setUpdateBy(username);
        sysAnnouncement.setUpdateTime(DateUtils.getNowDate());
        return sysAnnouncementMapper.updateSysAnnouncement(sysAnnouncement);
    }

    /**
     * 批量删除系统消息
     *
     * @param ids 需要删除的系统消息ID
     * @return 结果
     */
    @Override
    public int deleteSysAnnouncementByIds(String[] ids) {
        return sysAnnouncementMapper.deleteSysAnnouncementByIds(ids);
    }

    /**
     * 删除系统消息信息
     *
     * @param id 系统消息ID
     * @return 结果
     */
    @Override
    public int deleteSysAnnouncementById(String id) {
        return sysAnnouncementMapper.deleteSysAnnouncementById(id);
    }

    @Override
    @Transactional
    public int releaseAnnouncement(String id) {
        SysAnnouncement announcement = new SysAnnouncement();
        announcement.setId(id);
        announcement.setSender(SecurityUtils.getUsername());
        announcement.setSendStatus("1");
        announcement.setSendTime(DateUtils.getNowDate());
        SysAnnouncement message = sysAnnouncementMapper.selectSysAnnouncementById(id);
        saveAnnouncementSend(message);
        return sysAnnouncementMapper.updateSysAnnouncement(announcement);
    }

    @Override
    public int cancellationAnnouncement(String id) {
        SysAnnouncement announcement = new SysAnnouncement();
        announcement.setId(id);
        announcement.setSendStatus("2");
        announcement.setCancelTime(DateUtils.getNowDate());
        return sysAnnouncementMapper.updateSysAnnouncement(announcement);
    }

    private void saveAnnouncementSend(SysAnnouncement announcement){
        String id = announcement.getId();
        boolean b = "USER".equals(announcement.getMsgType());
        if(b){
            String userIds = announcement.getUserIds();
            String[] split = userIds.split(",");
            for (String s : split) {
                SysAnnouncementSend announcementSend = new SysAnnouncementSend();
                announcementSend.setId(IdUtils.randomUUID());
                announcementSend.setAnntId(id);
                announcementSend.setUserId(Long.parseLong(s));
                announcementSend.setReadFlag("0");
                announcementSend.setCreateBy(announcement.getCreateBy());
                announcementSend.setCreateTime(announcement.getCreateTime());
                sendMapper.insertSysAnnouncementSend(announcementSend);
            }
        }
    }

    @Override
    public int deleteByBizId(String bizId) {
        if(StringUtils.isNotBlank(bizId)){
            SysAnnouncement msgQuery = new SysAnnouncement();
            msgQuery.setBizId(bizId);
            List<String> anntIdList = sysAnnouncementMapper.selectSysAnnouncementList(msgQuery).stream()
                    .map(SysAnnouncement::getId)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            if(!anntIdList.isEmpty()){
                //删除发送记录
                sendMapper.deleteByAnntIds(anntIdList);
                //删除消息
                return deleteSysAnnouncementByIds(anntIdList.toArray(new String[anntIdList.size()]));
            }

        }
        return 0;

    }
}
