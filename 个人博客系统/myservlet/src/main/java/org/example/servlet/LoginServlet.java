package org.example.servlet;

import org.example.dao.LoginDao;
import org.example.exception.AppException;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest request, HttpServletResponse response) {
        //通过请求对象获取请求参数信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //通过获取到的请求参数信息做登录操作
        User user = LoginDao.login(userName);
        //校验账号密码
        if (user == null)
            throw new AppException("LOG555","用户不存在");
        if (!user.getPassword().equals(password))
            throw new AppException("LOG554","账号或密码错误");
        //校验成功，设置私人储物柜session,方便下次登录
        HttpSession session = request.getSession();
        session.setAttribute("key",user);
        return null;
    }
 }

