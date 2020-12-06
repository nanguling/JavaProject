package com.mystudy.controller;

import com.mystudy.dao.UserDao;
import com.mystudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "UserAddServlet")
public class UserAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.【调用请求对象】读取【请求头】参数信息，得到用户注册信息
        String name,password,sex,email;
        name = request.getParameter("userName");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");
        //2.【调用UserDao】将用户信息填充到INSERT命令并借助JDBC规范发送到数据库服务器
        Users users = new Users(null,name,password,sex,email);
        UserDao ud = new UserDao();
        Date startDate = new Date();//开始时间
        int res = ud.add(users,request);
        Date endDate = new Date();//结束时间
        System.out.println("添加所消耗的时间为："+(endDate.getTime()-startDate.getTime())+"毫秒");//10-14
        //3.【调用响应对象】将【处理结果】以二进制形式写入【响应体】
        //声明类型
        response.setContentType("text/html;charset=utf-8");
        PrintWriter result = response.getWriter();
        if (res == 1) {
            result.print("<center><font style='color:red;size:'40'>用户信息注册成功</font></center>");
        }else {
            result.print("<center><font style='color:red;size:'40'>用户信息注册失败</font></center>");
        }

    }
    //tomcat负责销毁【请求对象】和【响应对象】
    //tomcat负责将Http响应协议包推送到发起请求的浏览器上
    //浏览器根据响应头content-type指定编译器对响应体二进制内容进行编译
    //浏览器将编辑后的结果在窗口中展示给用户【结束】
}
