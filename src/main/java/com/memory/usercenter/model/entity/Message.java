package com.memory.usercenter.model.entity;

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
public class Message {
    private Long senderId;
    private Long receiverId;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
}
