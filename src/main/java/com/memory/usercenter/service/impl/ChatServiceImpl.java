package com.memory.usercenter.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.memory.usercenter.model.VO.ChatVO;
import com.memory.usercenter.model.entity.Message;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.service.ChatService;
import com.memory.usercenter.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import static com.memory.usercenter.constant.UserConstant.USER_CHAT_MESSAGE;

/**
 * 聊天信息处理
 */
@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    @Resource
    private UserService userService;


    /**
     * 获取聊天消息记录
     *
     * @param senderId   发送者
     * @param receiverId 接收者
     * @param request    request
     * @return 聊天记录
     */
    @Override
    public List<Message> listMessage(Long senderId, Long receiverId, HttpServletRequest request) {

        List<Message> message1 = getMessage(senderId, receiverId);

        List<Message> message2 = getMessage(receiverId, senderId);

        List<Message> messageList = new ArrayList<>();
        messageList.addAll(message1);
        messageList.addAll(message2);

        // 3.按发送时间sendTime, 排列
        messageList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getSendTime().compareTo(o2.getSendTime());
            }
        });

        return messageList;
    }

    /**
     * 获取消息
     *
     * @param senderId   发送者
     * @param receiverId 接收者
     * @return 聊天记录
     */
    public List<Message> getMessage(Long senderId, Long receiverId) {
        Map<Object, Object> mesEntriesJson = redisTemplate.opsForHash().entries(USER_CHAT_MESSAGE + senderId);

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
            // 获取与receiverId通信的消息
            if (message.getReceiverId().equals(receiverId)) {
                messageList.add(message);
            }
        }

        return messageList;
    }


    /**
     * 收发双方详细信息
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @param request    request
     * @return 收发双方详细信息
     */
    @Override
    public ChatVO getChatMsgOne(Long senderId, Long receiverId, HttpServletRequest request) {
        // 获取收发双方信息
        User sender = userService.getById(senderId);
        User receiver = userService.getById(receiverId);
        // 组装
        ChatVO chatVO = new ChatVO();
        chatVO.setUsernameSen(sender.getUsername());
        chatVO.setAvatarUrlSen(sender.getAvatarUrl());

        chatVO.setUsernameRec(receiver.getUsername());
        chatVO.setAvatarUrlRec(receiver.getAvatarUrl());
        //返回
        return chatVO;
    }
}




