package com.memory.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.model.entity.Friends;
import com.baomidou.mybatisplus.extension.service.IService;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.model.DTO.friends.FriendsAdd;

import javax.servlet.http.HttpServletRequest;

/**
* @author Lenovo
* @description 针对表【friends(好友表)】的数据库操作Service
* @createDate 2023-06-09 23:15:20
*/
public interface FriendsService extends IService<Friends> {
    /**
     * 添加好友
     *
     * @param friendsAdd 请求参数
     * @param request    request
     * @return 结果
     */
    String addFriends(FriendsAdd friendsAdd, HttpServletRequest request);

    Page<User> listFriends(HttpServletRequest request);

    void deleteFriends();

    User getLoginUser(HttpServletRequest request);
}
