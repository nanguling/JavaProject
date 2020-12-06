package org.example.servlet;

import org.example.dao.ArticleDao;
import org.example.model.Article;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest request, HttpServletResponse response) {
        //通过io流获取请求体中json字符串
        InputStream is = null;
        try {
            is = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将获取到的is反序列化为一个article对象
        Article article = JSONUtil.deserialize(is,Article.class);
        //调用Dao通过article完成对article表的修改操作
        ArticleDao ad = new ArticleDao();
        int res = ad.upDate(article);
        return res;
    }
}
