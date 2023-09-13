package com.memory.usercenter.service.impl;

import com.memory.usercenter.exception.BusinessException;
import com.memory.usercenter.model.DTO.Message;
import com.memory.usercenter.service.ChatService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.memory.usercenter.constant.UserConstant.USER_CHAT_MESSAGE;

/**
 * 聊天信息处理
 */
@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    /**
     * 获取聊天消息
     *
     * @param loginUserId 登录用户id
     * @param request     request
     */
    @Override
    public Message listMessage(Long loginUserId, HttpServletRequest request) {
        Message message = (Message) redisTemplate.opsForValue().get(USER_CHAT_MESSAGE + loginUserId);
        if (message == null)
            return null;

        return message;
    }
}




