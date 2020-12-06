package com.mystudy.controller;

import com.mystudy.dao.UserDao;
import com.mystudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.【调用DAO】将查询命令推送到数据库服务器，得到所有用户信息的【list】
        UserDao ud = new UserDao();
        List<Users> list = ud.find();
        /*//判断当前用户在tomcat中是否存在HttpSession，防止恶意登录
        HttpSession session = request.getSession(false);
        if (session == null) {
            //恶意登录用户
            response.sendRedirect("/myweb/login_error.html");
        }*/
        //2.【调用响应对象】将用户信息结合<table>标签返回给浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        for (Users users : list) {
            out.print("<tr>");
            out.print("<td>"+users.getId()+"</td>");
            out.print("<td>"+users.getName()+"</td>");
            out.print("<td>*******</td>");
            out.print("<td>"+users.getSex()+"</td>");
            out.print("<td>"+users.getEmail()+"</td>");
            out.print("<td><a href='/myweb/user/delete?id="+users.getId()+"'>删除用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
