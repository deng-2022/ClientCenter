package com.memory.usercenter.model.VO;

import com.memory.usercenter.model.entity.User;
import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/9/19 15:10
 * Function:
 * Version 1.0
 */
@Data
public class UserVO extends User {
    private double percentage;
}
