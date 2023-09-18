package com.memory.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.usercenter.model.DTO.ArticleDTO;
import com.memory.usercenter.model.entity.Article;
import com.memory.usercenter.service.ArticleService;
import com.memory.usercenter.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【article(博文)】的数据库操作Service实现
 * @createDate 2023-09-18 13:14:17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    /**
     * 获取文章列表
     *
     * @param articleDTO
     * @param request
     * @return
     */
    @Override
    public Page<Article> listArticle(ArticleDTO articleDTO, HttpServletRequest request) {
        Integer currentPage = articleDTO.getCurrentPage();
        Integer pageSize = articleDTO.getPageSize();

        QueryWrapper<Article> aqw = new QueryWrapper<>();

        List<Article> articleList = list(aqw);

        Page<Article> articlePage = new Page<>(currentPage, pageSize);
        articlePage.setRecords(articleList);

        return articlePage;
    }
}




