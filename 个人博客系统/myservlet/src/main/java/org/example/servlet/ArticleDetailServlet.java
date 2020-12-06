package org.example.servlet;

import org.example.dao.ArticleDao;
import org.example.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest request, HttpServletResponse response) {
        //通过请求对象获取当前文章的id
        String id = request.getParameter("id");
        //调用Dao通过文章id显示出文章详情--title，content
        ArticleDao ad = new ArticleDao();
        Article article = ad.select(Integer.parseInt(id));
        return article;
    }
}
