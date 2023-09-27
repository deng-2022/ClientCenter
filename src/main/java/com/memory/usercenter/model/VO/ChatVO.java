package com.memory.usercenter.model.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.memory.usercenter.model.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author 邓哈哈
 * 2023/9/18 13:19
 * Function:
 * Version 1.0
 */
@Data
public class ChatVO {
    /**
     * 发送者昵称
     */
    private String usernameSen;

    /**
     * 发送者头像
     */
    private String avatarUrlSen;

    /**
     * 接收者者昵称
     */
    private String usernameRec;

    /**
     * 接收者头像
     */
    private String avatarUrlRec;
}
