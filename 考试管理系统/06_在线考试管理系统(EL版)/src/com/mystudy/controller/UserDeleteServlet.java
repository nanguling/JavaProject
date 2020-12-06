package com.mystudy.controller;

import com.mystudy.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.【调用请求对象】读取【请求头】中参数（用户编号）
        String id = request.getParameter("id");
        //2.【调用Dao】将用户编号填充到delete命令并发送到数据库服务器
        UserDao ud = new UserDao();
        int res = ud.delete(Integer.valueOf(id));
        //3.【调用响应对象】将处理结果以二进制写入到响应体中，交给浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (res == 1) {
            out.print("<center><font style='color:red;size:'40'>用户信息删除成功</font></center>");
        }else {
            out.print("<center><font style='color:red;size:'40'>用户信息删除失败</font></center>");
        }
    }
}
