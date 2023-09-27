package com.memory.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.usercenter.model.DTO.artical.ArticleDTO;
import com.memory.usercenter.model.VO.ArticleVO;
import com.memory.usercenter.model.VO.TeamVO;
import com.memory.usercenter.model.entity.Article;
import com.memory.usercenter.model.entity.Team;
import com.memory.usercenter.model.entity.User;
import com.memory.usercenter.service.ArticleService;
import com.memory.usercenter.mapper.ArticleMapper;
import com.memory.usercenter.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lenovo
 * @description 针对表【article(博文)】的数据库操作Service实现
 * @createDate 2023-09-18 13:14:17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Resource
    private UserService userService;

    /**
     * 获取文章列表
     *
     * @param articleDTO
     * @param request
     * @return
     */
    @Override
    public Page<ArticleVO> listArticle(ArticleDTO articleDTO, HttpServletRequest request) {
        // 获取页码
        Integer currentPage = articleDTO.getCurrentPage();
        Integer pageSize = articleDTO.getPageSize();
        // 搜索条件
        QueryWrapper<Article> aqw = new QueryWrapper<>();
        // 博文列表
        List<Article> articleList = list(aqw);
        // 封装文章和作者信息
        List<ArticleVO> articleVOList = getArticleVOByArticle(articleList);
        // 封装Page
        Page<ArticleVO> articleVOPage = new Page<>(currentPage, pageSize);
        articleVOPage.setRecords(articleVOList);
        return articleVOPage;
    }

    /**
     * 获取文章信息
     *
     * @param articleDTO
     * @param request
     * @return
     */
    @Override
    public ArticleVO getArticle(ArticleDTO articleDTO, HttpServletRequest request) {
        Long id = articleDTO.getId();
        // 获取文章
        Article article = getById(id);
        // 封装文章和作者信息
        return getArticleVOByArticle(article);

    }

    /**
     * 转换articleList 为 articleVOList
     *
     * @param articleList articleList
     * @return articleVOList
     */
    public List<ArticleVO> getArticleVOByArticle(List<Article> articleList) {
        return articleList.stream().map(article -> {
            Long authorId = article.getAuthorId();
            User author = userService.getById(authorId);
            ArticleVO articleVO = new ArticleVO();

            articleVO.setId(article.getId());
            ;
            articleVO.setTitle(article.getTitle());
            articleVO.setDescription(article.getDescription());
            articleVO.setContent(article.getContent());
            articleVO.setView(article.getView());
            articleVO.setLikes(article.getLikes());
            articleVO.setCollects(article.getCollects());
            articleVO.setComments(article.getComments());
            articleVO.setAuthor(author);
            articleVO.setArticleUrl(article.getArticleUrl());
            articleVO.setTags(article.getTags());
            articleVO.setCreateTime(article.getCreateTime());
            articleVO.setUpdateTime(article.getUpdateTime());

            return articleVO;
        }).collect(Collectors.toList());
    }

    /**
     * articleVO 为 articleVO
     *
     * @param article article
     * @return articleVO
     */
    public ArticleVO getArticleVOByArticle(Article article) {
        Long authorId = article.getAuthorId();
        User author = userService.getById(authorId);
        ArticleVO articleVO = new ArticleVO();

        articleVO.setId(article.getId());
        ;
        articleVO.setTitle(article.getTitle());
        articleVO.setDescription(article.getDescription());
        articleVO.setContent(article.getContent());
        articleVO.setView(article.getView());
        articleVO.setLikes(article.getLikes());
        articleVO.setCollects(article.getCollects());
        articleVO.setComments(article.getComments());
        articleVO.setAuthor(author);
        articleVO.setArticleUrl(article.getArticleUrl());
        articleVO.setTags(article.getTags());
        articleVO.setCreateTime(article.getCreateTime());
        articleVO.setUpdateTime(article.getUpdateTime());

        return articleVO;
    }
}




