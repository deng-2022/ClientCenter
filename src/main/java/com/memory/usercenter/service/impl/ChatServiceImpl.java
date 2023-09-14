package com.memory.usercenter.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.memory.usercenter.exception.BusinessException;
import com.memory.usercenter.model.DTO.Message;
import com.memory.usercenter.service.ChatService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Type;
import java.util.*;

import static com.memory.usercenter.constant.UserConstant.USER_CHAT_MESSAGE;

/**
 * 聊天信息处理
 */
@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    /**
     * 获取聊天消息记录
     *
     * @param loginUserId 登录用户id
     * @param request     request
     */
    @Override
    public List<Message> listMessage(Long loginUserId, HttpServletRequest request) {
        Map<Object, Object> mesEntriesJson = redisTemplate.opsForHash().entries(USER_CHAT_MESSAGE + loginUserId);

        Gson gson = new Gson();
        String jsonString = gson.toJson(mesEntriesJson);
        // 1.以当前登录用户id作为key，获取聊天消息记录
        Map<Object, Object> mesEntries = gson.fromJson(jsonString, new TypeToken<Map<Object, Object>>() {
        }.getType());
        // 判空 消息记录为空
        if (mesEntries == null)
            return null;
        // 2.获取value值 - message
        ArrayList<Message> messageList = new ArrayList<>();
        Collection<Object> mesValuesJson = mesEntries.values();
        for (Object mesValueJson : mesValuesJson) {
            String mesValuesJsonStr = (String) mesValueJson;
            Message message = gson.fromJson(mesValuesJsonStr, Message.class);
            messageList.add(message);
        }
        // 3.按发送时间sendTime, 排列
        Collections.sort(messageList, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getSendTime().compareTo(o2.getSendTime());
            }
        });

        return messageList;
    }
}




