package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 队伍
 * @TableName team
 */
@TableName(value ="team")
public class Team implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 队长id
     */
    private Long userId;

    /**
     * 已加人数
     */
    private Integer joinNum;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 队伍图片
     */
    private String imgUrl;

    /**
     * 队伍公告
     */
    private String announcement;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 队伍名称
     */
    public String getName() {
        return name;
    }

    /**
     * 队伍名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 最大人数
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * 最大人数
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * 过期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 队长id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 队长id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 已加人数
     */
    public Integer getJoinNum() {
        return joinNum;
    }

    /**
     * 已加人数
     */
    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 队伍图片
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 队伍图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 队伍公告
     */
    public String getAnnouncement() {
        return announcement;
    }

    /**
     * 队伍公告
     */
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}