package com.memory.usercenter.model.DTO.team;

import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/9/10 19:30
 * Function: 所有队伍列表参数
 * Version 1.0
 */

@Data
public class TeamList {
    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Boolean isSecret;
}
