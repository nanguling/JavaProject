package org.example.servlet;

import org.example.dao.ArticleDao;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest request, HttpServletResponse response) {
        //向tomcat索要当前用户的session
        HttpSession session = request.getSession();
        //得到当前用户
        User user =(User) session.getAttribute("key");
        //通过对应Dao根据JDBC对article表进行查询操作
        ArticleDao ad = new ArticleDao();
        /*//这里判空是为了防止恶意登录的用户，恶意登陆的用户是没有保存在session中的
        if (user == null)
            throw new AppException("ART555","尚未注册，请注册后操作!");*/
        List list = ad.select(user.getId());
        return list;
    }
}
