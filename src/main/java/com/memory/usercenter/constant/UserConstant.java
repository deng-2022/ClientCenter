package com.memory.usercenter.constant;

/**
 * @author 邓哈哈
 * 2023/3/10 16:53
 * Function:
 * Version 1.0
 */

public class UserConstant {
    // 登录用户 session Key
    public static final String USER_LOGIN_STATE = "userLoginState";
    // 加密密码前的盐值
    public static final String SALT = "memory";
    // 用户消息
    public static final String USER_CHAT_MESSAGE = "memory:user:message:";
    // 管理员权限
    public static final int ADMIN_ROLE = 1;
    // 普通用户权限
    public static final int DEFAULT_ROLE = 0;
    // 用户在线
    public static final int ONLINE = 1;
    // 用户下线
    public static final int OFFLINE = 0;


}
