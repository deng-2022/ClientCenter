package com.memory.usercenter.service;

import com.memory.usercenter.model.VO.ChatVO;
import com.memory.usercenter.model.VO.MessageVO;
import com.memory.usercenter.model.entity.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 聊天信息处理
 */
public interface ChatService {
    List<MessageVO> listMessage(Long senderId, Long receiverId, HttpServletRequest request);
    ChatVO getChatMsgOne(Long senderId, Long receiverId, HttpServletRequest request);
}
