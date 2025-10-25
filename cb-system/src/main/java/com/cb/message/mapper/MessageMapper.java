package com.cb.message.mapper;

import com.cb.message.domain.MessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageMapper {

    public List<MessageVo> selectList(MessageVo vo);

    public MessageVo getInfo(@Param("id") String id, @Param("userId") Long userId);

    public List<MessageVo> selectUnReadList(MessageVo vo);

    public int countUnRead(MessageVo vo);

    /**
     * 系统消息列表
     * @return
     */
    public List<Map<String, Object>> systemMessageList();

    public List<Map<String, Object>> systemMessage2List();

}
