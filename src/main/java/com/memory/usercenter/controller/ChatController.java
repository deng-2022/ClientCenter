package com.memory.usercenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.common.BaseResponse;
import com.memory.usercenter.common.ResultUtils;
import com.memory.usercenter.model.DTO.Message;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 邓哈哈
 * 2023/6/9 23:24
 * Function:
 * Version 1.0
 */

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;

    /**
     * 查看我的消息列表
     *
     * @param request request
     * @return 好友列表
     */
    @GetMapping("/list")
    public BaseResponse<Message> getMessage(Long loginUserId, HttpServletRequest request) {
        // controller对参数的校验

        Message message = chatService.listMessage(loginUserId, request);
        return ResultUtils.success(message);
    }
}
