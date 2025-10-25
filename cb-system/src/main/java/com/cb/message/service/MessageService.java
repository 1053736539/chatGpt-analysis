package com.cb.message.service;

import com.cb.message.domain.MessageVo;

import java.util.List;
import java.util.Map;

/**
 * 系统消息Service接口
 *
 * @author ouyang
 * @date 2023-07-12
 */
public interface MessageService {
    public List<MessageVo> selectList(MessageVo vo);

    public MessageVo getInfo(String id);

    public List<MessageVo> selectUnReadList(MessageVo vo);

    public int countUnRead(MessageVo vo);

    public int readMessage(String announcementId);

    /**
     * 系统消息列表
     * @return
     */
    public List<Map<String, Object>> systemMessageList();

    public List<Map<String, Object>> systemMessage2List();

    /**
     * 发送消息给指定用户
     * @param title
     * @param message
     * @param sender
     * @param userIds
     */
    public void sendMessage2User(String title,String message,String sender,String userIds);

    public void sendMessage2User(String title,String message,String sender,String userIds,String bizId);

    public void sendMessage2User(String title,String message,String sender,List<Long> userIds);

    public void sendMessage2User(String title,String message,String sender,List<Long> userIds,String bizId);

    /**
     * 根据bizId删除消息及小计发送记录
     * @param bizId
     */
    public void deleteMsgByBizId(String bizId);

}
