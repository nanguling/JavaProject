package org.example.service;

import org.example.entity.Article;
import org.example.entity.JSONResponse;

import javax.servlet.http.HttpServletRequest;

public interface ArticleService {
    JSONResponse selectArticleList(HttpServletRequest request);

    JSONResponse addArticle(HttpServletRequest request,Article article);

    JSONResponse selectArticle(Integer id);

    JSONResponse updateArticle(Article article);

    JSONResponse deleteArticles(String ids);
}
