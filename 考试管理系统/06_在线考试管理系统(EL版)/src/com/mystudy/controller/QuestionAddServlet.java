package com.mystudy.controller;

import com.mystudy.dao.QuestionDao;
import com.mystudy.entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer;
        //1.通过请求对象获取请求参数信息
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        //将参数信息传入实体类的实例对象里
        Questions questions = new Questions(null,title,optionA,optionB,optionC,optionD,answer);
        //2.通过QuestionDao将题目添加到数据库中
        QuestionDao qd = new QuestionDao();
        int res = qd.add(questions,request);
        //3.通过请求转发，向tomcat索要info.jsp将处理结果写入到响应体中
        if (res == 1) {
            request.setAttribute("info","试题添加成功");                                    ;
        }else {
            request.setAttribute("info","试题添加失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
