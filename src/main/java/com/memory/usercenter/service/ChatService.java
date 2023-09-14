package com.memory.usercenter.service;

import com.memory.usercenter.model.DTO.Message;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;
import java.util.List;

/**
 * 聊天信息处理
 */
public interface ChatService {
    List<Message> listMessage(Long loginUserId, HttpServletRequest request);
}
