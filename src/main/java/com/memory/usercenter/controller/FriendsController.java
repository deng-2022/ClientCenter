package com.memory.usercenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.common.BaseResponse;
import com.memory.usercenter.common.ErrorCode;
import com.memory.usercenter.common.ResultUtils;
import com.memory.usercenter.exception.BusinessException;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.model.request.friends.FriendsAdd;
import com.memory.usercenter.model.request.team.TeamJoin;
import com.memory.usercenter.service.FriendsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 邓哈哈
 * 2023/6/9 23:24
 * Function:
 * Version 1.0
 */

@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Resource
    private FriendsService friendsService;


    /**
     * 添加好友
     *
     * @param friend  添加好友参数
     * @param request request
     * @return 添加好友成功
     */
    @PostMapping("/add")
    public BaseResponse<String> addFriends(@RequestBody FriendsAdd friend, HttpServletRequest request) {
        // controller对参数的校验
        if (friend == null)
            throw new BusinessException(ErrorCode.PARMS_ERROR);

        String joinTeam = friendsService.addFriends(friend, request);
        return ResultUtils.success(joinTeam);
    }

    /**
     * 我的好友列表
     *
     * @param request request
     * @return 好友列表
     */
    @GetMapping("/list")
    public BaseResponse<Page<User>> joinTeam(HttpServletRequest request) {
        // controller对参数的校验

        Page<User> userPage = friendsService.listFriends(request);
        return ResultUtils.success(userPage);
    }

    // 删除好友

    /**
     * 加入队伍
     *
     * @param team    加入队伍参数
     * @param request request
     * @return 加入队伍成功
     */
//    @PostMapping("/delete")
//    public BaseResponse<String> joinTeam(@RequestBody TeamJoin team, HttpServletRequest request) {
//        // controller对参数的校验
//        if (team == null)
//            throw new BusinessException(ErrorCode.PARMS_ERROR);
//
//        String joinTeam = teamService.joinTeam(team, request);
//        return ResultUtils.success(joinTeam);
//    }
}
