package org.example.servlet;

import org.example.dao.ArticleDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest request, HttpServletResponse response) {
        //通过请求对象获取请求参数
        String ids = request.getParameter("ids");
        //调用Dao通过id完成article表的删除操作
        ArticleDao ad = new ArticleDao();
        //因为请求参数是id=1,2,3这种格式，我们需要对字符串进行相应的操作
        int res = ad.delete(ids.split(","));
        return res;
    }
}
