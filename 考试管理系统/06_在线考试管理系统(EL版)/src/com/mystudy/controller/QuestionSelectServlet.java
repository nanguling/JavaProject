package com.mystudy.controller;

import com.mystudy.dao.QuestionDao;
import com.mystudy.entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionSelectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过请求对象获取请求参数信息
        String id = request.getParameter("id");
        //通过id调用Dao查询此id下的详细题目信息
        QuestionDao qd = new QuestionDao();
        Questions questions = qd.select(Integer.parseInt(id),request);
        //通过请求作用域对象将questions作为共享数据
        request.setAttribute("ques",questions);
        //通过请求转发向tomcat申请update.jsp处理结果
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }
}
