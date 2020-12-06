package com.mystudy.controller;

import com.mystudy.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name,passwoord;
        //1.调用请求对象对请求体使用utf-8进行重新编译
        request.setCharacterEncoding("utf-8");
        //2.调用请求对象读取请求体中参数信息
        name = request.getParameter("userName");
        passwoord = request.getParameter("password");
        //3.将查询验证信息推送到数据库服务器
        UserDao ud = new UserDao();
        int res = ud.login(name,passwoord);
        //4.调用相应对象，根据验证结果，将不同资源文件地址写入响应头，交给浏览器
        if (res == 1) {//用户存在
            //为当前已经注册成功的用户在tomcat中申请私人储物柜HttpSession
            HttpSession session = request.getSession();
            response.sendRedirect("/myweb/index.html");
        }else {
            response.sendRedirect("/myweb/login_error.html");
        }
    }
}
