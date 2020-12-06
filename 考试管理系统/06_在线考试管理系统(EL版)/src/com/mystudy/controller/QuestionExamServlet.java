package com.mystudy.controller;

import com.mystudy.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionExamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Dao随机从question表拿出4道题
        QuestionDao qd = new QuestionDao();
        List list = qd.exam(request);
        //2.将4道题目添加到session作为共享数据
        HttpSession session = request.getSession(false);
        session.setAttribute("exam",list);
        //3.通过请求转发向tomcat申请exam.jsp将结果写入响应体交还给浏览器打印出来
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
