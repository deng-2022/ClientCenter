package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.usercenter.model.request.friends.FriendsAdd;
import generator.domain.Friends;
import generator.service.FriendsService;
import generator.mapper.FriendsMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author Lenovo
* @description 针对表【friends(好友表)】的数据库操作Service实现
* @createDate 2023-06-09 23:22:53
*/
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
    implements FriendsService{


}




