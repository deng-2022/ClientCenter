package com.memory.usercenter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.usercenter.common.ErrorCode;
import com.memory.usercenter.exception.BusinessException;
import com.memory.usercenter.model.entity.Friends;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.model.request.friends.FriendsAdd;
import com.memory.usercenter.service.FriendsService;
import com.memory.usercenter.mapper.FriendsMapper;
import com.memory.usercenter.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.memory.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author Lenovo
 * @description 针对表【friends(好友表)】的数据库操作Service实现
 * @createDate 2023-06-09 23:15:20
 */
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
        implements FriendsService {
    @Resource
    private UserService userService;

    @Override
    public String addFriends(FriendsAdd friend, HttpServletRequest request) {
        User loginUser = this.getLoginUser(request);
        Long userId = loginUser.getId();
        // 不能重复加好友
        QueryWrapper<Friends> fqw = new QueryWrapper<>();
        fqw.eq("user_id", userId).eq("friend_id", friend.getId());

        long count = count(fqw);
        if (count > 0) {
            throw new BusinessException(ErrorCode.UPDATE_ERROR, "不能重复加好友");
        }

        Friends friends = new Friends();
        friends.setUserId(userId);
        friends.setFriendId(friend.getId());
        friends.setNote("我的好友");
        friends.setCreateTime(new Date());
        friends.setUpdateTime(new Date());
        friends.setIsDelete(0);

        boolean save = this.save(friends);

        if (!save) {
            throw new BusinessException(ErrorCode.UPDATE_ERROR, "添加好友失败");
        }

        return "添加好友成功";
    }

    /**
     * 我的好友列表
     *
     * @param request
     * @return
     */
    @Override
    public Page<User> listFriends(HttpServletRequest request) {
        User loginUser = getLoginUser(request);

        // 1.查出所有好友id
        QueryWrapper<Friends> fqw = new QueryWrapper<>();
//        fqw.select("friend_id");
        fqw.eq("user_id", loginUser.getId());
        List<Friends> friendsList = list(fqw);

        List<User> userList = new ArrayList<>();
        // 2.根据id查询道好友信息
        for (Friends friends : friendsList) {
            Long friendId = friends.getFriendId();
            User one = userService.getById(friendId);
            userList.add(one);
        }

        Page<User> userPage = new Page<>(1, 20);
        userPage.setRecords(userList);
        return userPage;
    }

    @Override
    public void deleteFriends() {

    }

    /**
     * 用户信息脱敏
     *
     * @param originUser 原始用户
     * @return 脱敏后的用户
     */
    public User getSafetyUser(User originUser) {
        if (originUser == null) return null;

        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setIsDelete(originUser.getIsDelete());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setProfile(originUser.getProfile());
        safetyUser.setTags(originUser.getTags());

        return safetyUser;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return getSafetyUser(loginUser);
    }
}




