package com.memory.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.usercenter.model.entity.Friends;
import com.memory.usercenter.service.FriendsService;
import com.memory.usercenter.mapper.FriendsMapper;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * @description 针对表【friends(好友表)】的数据库操作Service实现
 * @createDate 2023-06-09 23:15:20
 */
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
        implements FriendsService {

}




