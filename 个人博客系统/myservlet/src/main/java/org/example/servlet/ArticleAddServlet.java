package org.example.servlet;

import org.example.dao.ArticleDao;
import org.example.exception.AppException;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest request, HttpServletResponse response) {
        //向tomcat索要当前用户的私人储物柜session，以获取用户id
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("key");
        //通过一个io流获取请求体中json字符串
        InputStream is = null;
        try {
            is = request.getInputStream();
        } catch (IOException e) {
            throw new AppException("IO555","IO流异常",e);
        }
        //json字符串反序列化为一个文章对象
        Article article = JSONUtil.deserialize(is,Article.class);
        article.setUserId(user.getId());
        //调用Dao通过JBDC对article表完成添加操作
        ArticleDao ad = new ArticleDao();
        int res = ad.add(article);
        return res;
    }
}
