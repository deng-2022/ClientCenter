package com.memory.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.model.DTO.ArticleDTO;
import com.memory.usercenter.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lenovo
 * @description 针对表【article(博文)】的数据库操作Service
 * @createDate 2023-09-18 13:14:17
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取文章列表
     *
     * @param articleDTO
     * @param request
     * @return
     */
    Page<Article> listArticle(ArticleDTO articleDTO, HttpServletRequest request);
}
