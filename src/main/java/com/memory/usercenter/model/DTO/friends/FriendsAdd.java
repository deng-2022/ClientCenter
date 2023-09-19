package com.memory.usercenter.model.DTO.friends;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 邓哈哈
 * 2023/9/11 20:01
 * Function: 添加好友参数
 * Version 1.0
 */

@Data
public class FriendsAdd {
    /**
     * 好友id
     */
    private Long Id;

    /**
     * 好友备注
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;
}
