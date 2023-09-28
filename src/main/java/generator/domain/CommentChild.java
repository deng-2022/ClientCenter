package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment_child
 */
@TableName(value ="comment_child")
public class CommentChild implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 条目id
     */
    private Long itemId;

    /**
     * 父评论id，也即第一级评论
     */
    private Long parentId;

    /**
     * 被回复的评论id（没有则是回复父级评论，有则是回复这个人的评论）
     */
    private Long replyId;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 是否为发布者
     */
    private Integer isPublisher;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 条目id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 条目id
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 父评论id，也即第一级评论
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父评论id，也即第一级评论
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 被回复的评论id（没有则是回复父级评论，有则是回复这个人的评论）
     */
    public Long getReplyId() {
        return replyId;
    }

    /**
     * 被回复的评论id（没有则是回复父级评论，有则是回复这个人的评论）
     */
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    /**
     * 评论人id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 评论人id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 点赞数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 点赞数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 是否为发布者
     */
    public Integer getIsPublisher() {
        return isPublisher;
    }

    /**
     * 是否为发布者
     */
    public void setIsPublisher(Integer isPublisher) {
        this.isPublisher = isPublisher;
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
}