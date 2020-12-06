package com.mystudy.controller;

import com.mystudy.dao.QuestionDao;
import com.mystudy.entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id,title,optionA,optionB,optionC,optionD,answer;
        //通过请求对象读取请求参数信息
        id = request.getParameter("id");
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        //调用Dao通过JDBC完成对question表的更新操作
        Questions questions = new Questions(Integer.valueOf(id),title,optionA,optionB,optionC,optionD,answer);
        QuestionDao qd = new QuestionDao();
        int res = qd.upDate(questions,request);
        //判断是否更新成功
        if (res == 1) {
            request.setAttribute("info","试题更新成功");
            //通过请求转发向tomcat申请outUp.jsp将处理结果写入响应体中
            request.getRequestDispatcher("/info.jsp").forward(request,response);
        }else {
            request.setAttribute("info","试题更新失败");
            request.getRequestDispatcher("/info.jsp").forward(request,response);
        }
    }
}
