package org.example.controller;

import org.example.entity.Article;
import org.example.entity.JSONResponse;
import org.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService service;

    //查询文章列表操作(通过登录用户的id查询当前用户下的文章，并以json格式返回给前端)
    @RequestMapping("/articleList")
    @ResponseBody
    public JSONResponse articleList(HttpServletRequest request) {
        //调用service类得到处理结果
        JSONResponse json = service.selectArticleList(request);
        return json;
    }

    //添加文章操作
    @RequestMapping("/articleAdd")
    @ResponseBody
    public JSONResponse articleAdd(@RequestBody Article articleJson, HttpServletRequest request) {
        //调用service类得到处理结果
        JSONResponse json = service.addArticle(request,articleJson);
        return json;
    }

    //查询单篇文章操作
    @RequestMapping("/articleDetail")
    @ResponseBody
    public JSONResponse articleDetail(Integer id) {
        //调用service类得到处理结果
        JSONResponse json = service.selectArticle(id);
        return json;
    }

    //修改文章操作
    @RequestMapping("/articleUpdate")
    @ResponseBody
    public JSONResponse articleUpdate(@RequestBody Article article) {
        //调用service类得到处理结果
        JSONResponse json = service.updateArticle(article);
        return json;
    }

    //删除文章操作
    @RequestMapping("/articleDelete")
    @ResponseBody
    public JSONResponse articleDelete(String ids) {
        //调用service类得到处理结果
        JSONResponse json = service.deleteArticles(ids);
        return json;
    }
}
