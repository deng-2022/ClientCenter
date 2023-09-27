package com.memory.usercenter.model.DTO.announcement;

import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/4/20 14:22
 * Function: 更新公告
 * Version 1.0
 */
@Data
public class AddAnnouncement {
    /**
     * 队长id
     */
    private Long userId;

    /**
     * 队长id
     */
    private Long teamId;

    /**
     * 公告
     */
    private String announcement;
}
