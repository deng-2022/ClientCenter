package com.memory.usercenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.common.BaseResponse;
import com.memory.usercenter.common.ResultUtils;
import com.memory.usercenter.model.DTO.artical.ArticleDTO;
import com.memory.usercenter.model.entity.Article;
import com.memory.usercenter.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 邓哈哈
 * 2023/6/9 23:24
 * Function:
 * Version 1.0
 */

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 查看我的消息列表
     *
     * @param request request
     * @return 好友列表
     */
    @GetMapping("/list")
    public BaseResponse<Page<Article>> listArticle(ArticleDTO articleDTO, HttpServletRequest request) {
        // controller对参数的校验

        Page<Article> articlePage = articleService.listArticle(articleDTO, request);
        return ResultUtils.success(articlePage);
    }
}
