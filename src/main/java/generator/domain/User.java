package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 昵称
     */
    private String username;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0 - 正常
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0 - 正常
     */
    private Integer isDelete;

    /**
     * 用户权限 0 - 管理员 1 - 普通用户
     */
    private Integer userRole;

    /**
     * 星球编号
     */
    private String planetCode;

    /**
     * 用户描述
     */
    private String profile;

    /**
     * 标签
     */
    private String tags;

    /**
     * 是否在线
     */
    private Integer isOnline;

    /**
     * 文章数量
     */
    private Integer articleNum;

    /**
     * 粉丝数
     */
    private Integer fans;

    /**
     * 获赞数
     */
    private Integer likes;

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
     * 账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 昵称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 昵称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 邮箱
     */
    public String getGender() {
        return gender;
    }

    /**
     * 邮箱
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 状态 0 - 正常
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 状态 0 - 正常
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 是否删除 0 - 正常
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除 0 - 正常
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 用户权限 0 - 管理员 1 - 普通用户
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 用户权限 0 - 管理员 1 - 普通用户
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * 星球编号
     */
    public String getPlanetCode() {
        return planetCode;
    }

    /**
     * 星球编号
     */
    public void setPlanetCode(String planetCode) {
        this.planetCode = planetCode;
    }

    /**
     * 用户描述
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 用户描述
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 是否在线
     */
    public Integer getIsOnline() {
        return isOnline;
    }

    /**
     * 是否在线
     */
    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    /**
     * 文章数量
     */
    public Integer getArticleNum() {
        return articleNum;
    }

    /**
     * 文章数量
     */
    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    /**
     * 粉丝数
     */
    public Integer getFans() {
        return fans;
    }

    /**
     * 粉丝数
     */
    public void setFans(Integer fans) {
        this.fans = fans;
    }

    /**
     * 获赞数
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * 获赞数
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}