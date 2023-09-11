package generator.service;

import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.model.request.friends.FriendsAdd;
import generator.domain.Friends;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lenovo
 * @description 针对表【friends(好友表)】的数据库操作Service
 * @createDate 2023-06-09 23:22:53
 */
public interface FriendsService extends IService<Friends> {


}
