package com.memory.usercenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.common.BaseResponse;
import com.memory.usercenter.common.ErrorCode;
import com.memory.usercenter.common.ResultUtils;
import com.memory.usercenter.exception.BusinessException;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.model.request.user.UserLogin;
import com.memory.usercenter.model.request.user.UserRegister;
import com.memory.usercenter.service.UserService;
import com.memory.usercenter.utils.SMSUtils;
import com.memory.usercenter.utils.ValidateCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.memory.usercenter.common.ErrorCode.PARMS_ERROR;
import static com.memory.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 邓哈哈
 * 2023/3/10 13:23
 * Function:
 * Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    /**
     * 用户注册
     *
     * @param userRegisterRequest 注册用户信息
     * @return id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegister userRegisterRequest) {
        //controller对参数的校验
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode))
            throw new BusinessException(PARMS_ERROR);

        long userRegister = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(userRegister);
    }

    /**
     * 发送短信验证码
     *
     * @param phoneNumber 手机号
     * @param request     request
     * @return 短信验证码发送成功
     */
    @PostMapping("/sendMsg")
    public BaseResponse<String> sendMsg(String phoneNumber, HttpServletRequest request) {
        // 1.校验手机号
        if (StringUtils.isEmpty(phoneNumber))
            throw new BusinessException(PARMS_ERROR);
        // 2.生成随机的4位验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();
        // 3.调用阿里云提供的短信服务API完成发送短信
        SMSUtils.sendMessage("伙伴匹配", "", phoneNumber, code);
        // 4.需要将生成的验证码保存到Session
        request.getSession().setAttribute(phoneNumber, code);
        // 5.短信发送成功
        return ResultUtils.success("短信验证码发送成功!");
    }

    /**
     * 直接获取验证码
     *
     * @param phoneNumber 电话号码
     * @return 验证码
     */
    @GetMapping("/getCode")
    public BaseResponse<String> getCode(String phoneNumber) {
        //controller对参数的校验
        if (StringUtils.isAnyBlank(phoneNumber))
            throw new BusinessException(PARMS_ERROR);

        String code = userService.getCode(phoneNumber);
        return ResultUtils.success(code);
    }

    /**
     * 验证码登录
     *
     * @param phoneNumber 电话号码
     * @param code        验证码
     * @return 脱敏用户信息
     */
    @GetMapping("/codeLogin")
    public BaseResponse<User> codeLogin(String phoneNumber, String code, String rightCode, HttpServletRequest request) {
        //controller对参数的校验
        if (StringUtils.isAnyBlank(phoneNumber, code, rightCode))
            throw new BusinessException(PARMS_ERROR);

        User user = userService.codeLogin(phoneNumber, code, rightCode, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 登录用户信息
     * @param request          request
     * @return User
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLogin userLoginRequest, HttpServletRequest request) {
        //controller对参数的校验
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword))
            throw new BusinessException(PARMS_ERROR);
        redisTemplate.opsForValue().set("1", 3);
        User userLogin = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(userLogin);
    }

    /**
     * 用户注销
     *
     * @param request request
     * @return 注销成功与否(t / f)
     */
    @PostMapping("/logout")
    public BaseResponse<String> userLogout(HttpServletRequest request) {
        //controller对参数的校验
        if (request == null)
            throw new BusinessException(PARMS_ERROR);

        String userLogout = userService.userLogout(request);
        return ResultUtils.success(userLogout);
    }

    /**
     * 获取当前用户登录态
     *
     * @param request request
     * @return 当前用户信息
     */
    @GetMapping("/currentUser")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        // controller对参数的校验
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null)
            throw new BusinessException(PARMS_ERROR, "该用户未登录");

        User user = userService.getCurrentUser(request);
        return ResultUtils.success(user);
    }

    /**
     * 展示在线用户列表
     *
     * @param username 用户名
     * @return 查到的用户
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> userSearch(String username, HttpServletRequest request) {
        // controller对参数的校验
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (StringUtils.isBlank(username) && currentUser == null)
            throw new BusinessException(PARMS_ERROR);

        List<User> userList = userService.userSearch(username, request);
        return ResultUtils.success(userList);
    }

    /**
     * 根据标签查询用户
     *
     * @param tagNameList 标签列表
     * @return 用户列表
     */
    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchByTags(@RequestParam List<String> tagNameList) {
        // controller对参数的校验
        if (CollectionUtils.isEmpty(tagNameList))
            throw new BusinessException(PARMS_ERROR);

        List<User> userList = userService.searchUserByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    /**
     * 展示用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommend(@RequestParam long currentPage, long pageSize, HttpServletRequest request) {
        // controller对参数的校验

        Page<User> userList = userService.selectPage(currentPage, pageSize, request);
        return ResultUtils.success(userList);
    }

    /**
     * 删除用户
     *
     * @param id      用户id
     * @param request request
     * @return true/false
     */
    @DeleteMapping("/delete")
    public BaseResponse<Boolean> userDelete(Long id, HttpServletRequest request) {
        // controller对参数的校验
        if (id == 0)
            throw new BusinessException(PARMS_ERROR);

        Boolean remove = userService.userDelete(id, request);
        return ResultUtils.success(remove);
    }

    /**
     * 修改用户信息
     *
     * @param user    要修改的用户
     * @param request 执行修改的用户
     * @return 修改成功与否(t / f)
     */
    @PostMapping("/update")
    public BaseResponse<String> userUpdate(@RequestBody User user, HttpServletRequest request) {
        // controller对参数的校验
        User loginUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        System.out.println(loginUser);
        // 这里我们如此做实现:
        // 如果是管理员, 则进入管理员修改接口, 允许修改任何用户
        // 如果是普通用户, 则进入普通用户修改接口, 仅允许修改自己(当前用户)的信息

        if (user == null || loginUser == null) {
            throw new BusinessException(PARMS_ERROR);
        }
        String update = userService.userUpdate(user, loginUser);
        return ResultUtils.success(update);
    }

    /**
     * 用户匹配
     *
     * @param matchNum     推荐/匹配数目
     * @param request request 获取登陆用户
     * @return 匹配到的用户
     */
    @GetMapping("/match")
    public BaseResponse<Page<User>> matchUsers(Integer matchNum, HttpServletRequest request) {
        // controller对参数的校验
        if (matchNum == null)
            throw new BusinessException(PARMS_ERROR);

        Page<User> userList = userService.matchUsers(matchNum, request);
        return ResultUtils.success(userList);
    }

}
