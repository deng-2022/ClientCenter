package com.memory.usercenter.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 邓哈哈
 * 2023/9/13 16:19
 * Function:
 * Version 1.0
 */
@Data
public class MessageVO {
    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 接收者id
     */
    private Long receiverId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;

    /**
     * 发送者昵称
     */
    private String username;

    /**
     * 发送者头像
     */
    private String avatarUrl;
}
