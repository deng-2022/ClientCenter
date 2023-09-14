package com.memory.usercenter.common;

import com.google.gson.Gson;
import com.memory.usercenter.exception.BusinessException;
import com.memory.usercenter.model.DTO.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.http.WebSocket;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @author 邓哈哈
 * 2023/9/13 13:19
 * Function:
 * @ ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * Version 1.0
 */

@Slf4j
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
    // Redis 负责存储离线消息
    private static RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        WebSocketServer.redisTemplate = redisTemplate;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this); //加入set中
        this.sid = sid;
        addOnlineCount(); //在线数加1
        try {
            sendMessage("conn_success");
            log.info("有新窗口开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减1
        //断开连接情况下，更新主板占用情况为释放
        log.info("释放的sid为：" + sid);
        //这里写你 释放的时候，要处理的业务
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 接受客户端发来的消息
     *
     * @param message 消息
     * @throws IOException IOException
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        // 1.消息存放Redis
        boolean saveMessage = saveMessage(message);
        // 2.校验存放
        if (!saveMessage) {
            throw new BusinessException(ErrorCode.UPDATE_ERROR_REDIS, "用户信息存放失败");
        }
        // 3.消息转发
        msgForward(message, webSocketSet, sid);
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务器主动推送
     *
     * @param message 消息
     * @throws IOException IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 消息转发
     *
     * @param message      消息
     * @param webSocketSet webSocketSet
     * @param sid          转发用户id
     */
    public void msgForward(String message, CopyOnWriteArraySet<WebSocketServer> webSocketSet, String sid) {
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }else {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 存放消息到Redis 用户id -> (key: UUID, value: message)
     *
     * @param message 消息
     * @return 存放成功与否
     */
    public boolean saveMessage(String message) {
        // 3.存储信息到Redis 30min
        // 3.1.设置key
        String msgKey = "memory:user:message:";
        // 3.2.获取发送者 接收者
        Message sendMes = getSendMes(message);
        String senderMsgKey = msgKey + sendMes.getSenderId();
        String receiverMsgKey = msgKey + sendMes.getReceiverId();
        // 3.3.存放message
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(senderMsgKey, generateMessageId(), message);
        opsForHash.put(receiverMsgKey, generateMessageId(), message);
        // 3.3.设置键的过期时间，单位为h
        long expireTime = 2; // 设置为2hour
        redisTemplate.expire(senderMsgKey, expireTime, TimeUnit.HOURS);
        redisTemplate.expire(receiverMsgKey, expireTime, TimeUnit.HOURS);
        return true;
    }

//        String msgKey = "memory:user:message:";
//        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
//        String senderMsgKey = msgKey + senderId;
//        String receiverMsgKey = msgKey + receiverId;
//        long expireTime = 2; // 设置为2hour
//        redisTemplate.execute(new SessionCallback<Object>() {
//            @Override
//            public Object execute(RedisOperations operations) throws DataAccessException {
//                operations.multi(); // 开始事务
//                operations.opsForHash().put(senderMsgKey, generateMessageId(), message);
//                operations.opsForHash().put(receiverMsgKey, generateMessageId(), message);
//                operations.expire(senderMsgKey, expireTime, TimeUnit.HOURS);
//                operations.expire(receiverMsgKey, expireTime, TimeUnit.HOURS);
//                operations.exec(); // 提交事务
//                return null;
//            }
//        });

    /**
     * 解析消息 -> 发送者 接收者
     *
     * @param message 消息
     * @return message
     */
    private Message getSendMes(String message) {
        // 1.将JSON字符串 转成Java对象
        Gson gson = new Gson();
        Message newMessage = gson.fromJson(message, Message.class);
        if (newMessage == null) {
            throw new BusinessException(ErrorCode.PARMS_ERROR, "服务器解析消息失败");
        }
        return newMessage;
    }

    /**
     * 生成消息id
     *
     * @return 消息id
     */
    private String generateMessageId() {
        // 随机生成一个唯一的消息ID
        UUID messageId = UUID.randomUUID();
        return messageId.toString();
    }
}