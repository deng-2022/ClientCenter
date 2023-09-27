package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 博文
 * @TableName article
 */
@TableName(value ="article")
public class Article implements Serializable {
    /**
     * 文章id
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String description;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创作者
     */
    private Long authorId;

    /**
     * 浏览量
     */
    private Integer view;

    /**
     * 点赞量
     */
    private Integer likes;

    /**
     * 评论量
     */
    private String comments;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDelete;

    /**
     * 收藏量
     */
    private Integer collects;

    /**
     * 封面图片
     */
    private String articleUrl;

    /**
     * 文章标签
     */
    private String tags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    public Long getId() {
        return id;
    }

    /**
     * 文章id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 文章摘要
     */
    public String getDescription() {
        return description;
    }

    /**
     * 文章摘要
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 创作者
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 创作者
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * 浏览量
     */
    public Integer getView() {
        return view;
    }

    /**
     * 浏览量
     */
    public void setView(Integer view) {
        this.view = view;
    }

    /**
     * 点赞量
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * 点赞量
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * 评论量
     */
    public String getComments() {
        return comments;
    }

    /**
     * 评论量
     */
    public void setComments(String comments) {
        this.comments = comments;
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
     * 逻辑删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 逻辑删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 收藏量
     */
    public Integer getCollects() {
        return collects;
    }

    /**
     * 收藏量
     */
    public void setCollects(Integer collects) {
        this.collects = collects;
    }

    /**
     * 封面图片
     */
    public String getArticleUrl() {
        return articleUrl;
    }

    /**
     * 封面图片
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    /**
     * 文章标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 文章标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
}