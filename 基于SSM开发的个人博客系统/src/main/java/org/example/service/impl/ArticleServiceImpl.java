package org.example.service.impl;

import org.example.dao.ArticleDao;
import org.example.entity.Article;
import org.example.entity.JSONResponse;
import org.example.entity.User;
import org.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service()
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao dao;

    //显示文章列表
    @Override
    public JSONResponse selectArticleList(HttpServletRequest request) {
        JSONResponse json = new JSONResponse();

        //通过请求对象从session中获取当前用户
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            Integer id = user.getId();
            //调用dao得到文章集合
            List<Article> arList = dao.select(id);
                json = getJson(true,null,null,arList);
        }else {
            //说明用户当前没有session
            json = getJson(false,"ART444","获取文章列表失败",null);
        }

        return json;
    }

    //添加文章操作
    @Override
    public JSONResponse addArticle(HttpServletRequest request,Article articleJson) {
        JSONResponse json = new JSONResponse();

        //通过请求对象获取当前用户在服务器中的session
        HttpSession session = request.getSession(false);
        //得到当前用户
        if (session != null) {
            User user = (User) session.getAttribute("user");
            //从处理器对象传过来的对象，就是json对象
            articleJson.setUserId(user.getId());
            //调用dao完整文章添加操作
            int res = dao.add(articleJson);
            if (res == 1) {
                json = getJson(true,null,null,null);
            }else {
                json = getJson(false,"DAO445","添加文章失败",null);
            }
        }else {
            json = getJson(false,"ART445","添加文章失败",null);
        }
        return json;
    }

    @Override
    public JSONResponse selectArticle(Integer id) {
        JSONResponse json = new JSONResponse();

        //调用dao完成查询功能
        Article article = dao.selectOne(id);
        if (article != null) {
            json = getJson(true,null,null,article);
        }else {
            json = getJson(false,"DAO446","查询文章失败",null);
        }
        return json;
    }

    @Override
    public JSONResponse updateArticle(Article article) {
        JSONResponse json = new JSONResponse();
        //调用dao完成修改文章操作
        int res = dao.upDate(article);
        if (res == 1) {
            json = getJson(true,null,null,null);
        }else {
            json = getJson(false,"DAO447","修改文章失败",null);
        }
        return json;
    }

    @Override
    public JSONResponse deleteArticles(String ids) {
        JSONResponse json = new JSONResponse();
        List<Integer> list = new ArrayList<>();
        //将ids转为list集合
        String[] str = ids.split(",");
        for (String id:str) {
            list.add(Integer.valueOf(id));
        }
        //调用dao完成删除操作
        int res = dao.delete(list);
        if (res >= 1) {
            json = getJson(true,null,null,null);
        }else {
            json = getJson(false,"DAO448","删除文章失败",null);
        }
        return json;
    }

    public static JSONResponse getJson(boolean success,String code,String message,Object data) {
        JSONResponse json = new JSONResponse();
        json.setSuccess(success);
        json.setCode(code);
        json.setMessage(message);
        json.setData(data);
        return json;
    }
}
