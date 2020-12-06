package com.mystudy.controller;

import com.mystudy.entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过session获取考试的四道题目
        HttpSession session = request.getSession(false);
        List<Questions> list =(List<Questions>) session.getAttribute("exam");
        //遍历list获取每个题的id，并且通过id拼接answer后通过请求对象获取请求参数(答案)
        //通过答案和每道题的正确答案进行判断，答对加分，答错不做处理
        int score = 0;//学生的分数
        for (Questions q:list) {
            String stuAnswer = request.getParameter("answer_"+q.getId());
            if (q.getAnswer().equals(stuAnswer)) {
                score += 25;
            }
        }
        //通过请求作用域对象将考试的分数作为共享数据
        request.setAttribute("info","本次考试得分为:"+score);
        //哦那通过请求转发向tomcat申请info.jsp将分数结果写入响应体返回给浏览器
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
