package com.memory.usercenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.usercenter.common.BaseResponse;
import com.memory.usercenter.common.ResultUtils;
import com.memory.usercenter.model.DTO.artical.ArticleDTO;
import com.memory.usercenter.model.VO.ArticleVO;
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
     * 获取博文列表
     *
     * @param request request
     * @return 好友列表
     */
    @GetMapping("/list/VO")
    public BaseResponse<Page<ArticleVO>> getArticleVOList(ArticleDTO articleDTO, HttpServletRequest request) {
        // controller对参数的校验

        Page<ArticleVO> articleVOPage = articleService.listArticle(articleDTO, request);
        return ResultUtils.success(articleVOPage);
    }

    /**
     * 获取博文
     *
     * @param articleDTO
     * @param request
     * @return
     */
    @GetMapping("/get/VO")
    public BaseResponse<ArticleVO> getArticleById(ArticleDTO articleDTO, HttpServletRequest request) {
        // controller对参数的校验

        ArticleVO articleVO = articleService.getArticle(articleDTO, request);
        return ResultUtils.success(articleVO);
    }
}
