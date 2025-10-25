package com.cb.message.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.message.domain.MessageVo;
import com.cb.message.domain.SysAnnouncement;
import com.cb.message.domain.SysAnnouncementSend;
import com.cb.message.mapper.MessageMapper;
import com.cb.message.mapper.SysAnnouncementSendMapper;
import com.cb.message.service.ISysAnnouncementService;
import com.cb.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessageServiceIml implements MessageService {
    @Autowired
    private MessageMapper messagemapper;
    @Autowired
    private ISysAnnouncementService announcementService;
    @Autowired
    private SysAnnouncementSendMapper sendMapper;

    @Override
    public List<MessageVo> selectList(MessageVo vo) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        vo.setUserId(userId);
        return messagemapper.selectList(vo);
    }

    @Override
    public MessageVo getInfo(String id) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return messagemapper.getInfo(id, userId);
    }


    @Override
    public List<MessageVo> selectUnReadList(MessageVo vo) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        vo.setUserId(userId);
        return messagemapper.selectUnReadList(vo);
    }

    @Override
    public int countUnRead(MessageVo vo) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        vo.setUserId(userId);
        return messagemapper.countUnRead(vo);
    }

    @Override
    public int readMessage(String announcementId) {
        SysAnnouncementSend announcementSend = new SysAnnouncementSend();
        announcementSend.setAnntId(announcementId);
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        announcementSend.setUserId(userId);
        SysAnnouncementSend send = sendMapper.selectSysAnnouncementSend(announcementSend);
        String id = send.getId();
        SysAnnouncementSend updateEntity = new SysAnnouncementSend();
        updateEntity.setId(id);
        updateEntity.setReadFlag("1");
        updateEntity.setReadTime(DateUtils.getNowDate());
        return sendMapper.updateSysAnnouncementSend(updateEntity);
    }

    /**
     * 系统消息列表
     * @return
     */
    @Override
    public List<Map<String, Object>> systemMessageList() {
        return messagemapper.systemMessageList();
    }

    @Override
    public List<Map<String, Object>> systemMessage2List() {
        return messagemapper.systemMessage2List();
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void sendMessage2User(String title, String message, String sender, String userIds) {
        List<Long> userIdList = Arrays.stream(userIds.split(","))
                .map(i -> Long.parseLong(i)).collect(Collectors.toList());
        sendMessage2User(title, message, sender, userIdList);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void sendMessage2User(String title, String message, String sender, String userIds,String bizId) {
        if(StringUtils.isBlank(userIds)){
            throw new IllegalArgumentException("userIds不能为空");
        }
        List<Long> ids = Arrays.asList(userIds.split(",")).stream().map(item -> Long.parseLong(item)).collect(Collectors.toList());
        sendMessage2User(title, message, sender, ids,bizId);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void sendMessage2User(String title, String message, String sender, List<Long> userIds) {
        sendMessage2User(title, message, sender, userIds,null);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void sendMessage2User(String title, String message, String sender, List<Long> userIds,String bizId) {
        if(CollectionUtil.isEmpty(userIds)){
            throw new IllegalArgumentException("userIds不能为空");
        }
        List<Long> distinctIds = userIds.stream().distinct().collect(Collectors.toList());// 去重
        String id = IdUtils.randomUUID();
        SysAnnouncement announcement = new SysAnnouncement();
        announcement.setId(id);
        announcement.setTitle(title);
        announcement.setMsgContent(message);
        announcement.setPriority("L");
        announcement.setMsgCategory("1");
        announcement.setMsgType("USER");
        announcement.setSendStatus("1");
        announcement.setSendTime(DateUtils.getNowDate());
        announcement.setUserIds(StringUtils.join(distinctIds,","));
        announcement.setMsgAbstract(message);
        if(StringUtils.isNotBlank(bizId)){
            announcement.setBizId(bizId);
        }
        if(StringUtils.isBlank(sender)){
            try{
                String username = SecurityUtils.getUsername();
                announcement.setSender(username);
            } catch (Exception e){
                announcement.setSender("admin");
            }
        } else {
            announcement.setSender(sender);
        }
        announcementService.insertSysAnnouncement(announcement);
        for (Long userId : distinctIds) {
            SysAnnouncementSend announcementSend = new SysAnnouncementSend();
            announcementSend.setId(IdUtils.randomUUID());
            announcementSend.setAnntId(id);
            announcementSend.setUserId(userId);
            announcementSend.setReadFlag("0");
            announcementSend.setCreateBy(announcement.getCreateBy());
            announcementSend.setCreateTime(announcement.getCreateTime());
            sendMapper.insertSysAnnouncementSend(announcementSend);
        }
    }


    @Override
    public void deleteMsgByBizId(String bizId) {
        if(StringUtils.isNotBlank(bizId)){
            announcementService.deleteByBizId(bizId);
        }
    }
}
