package com.mystudy.controller;

import com.mystudy.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过请求对象读取请求参数信息
        String id = request.getParameter("id");
        //调用Dao通过JDBC根据id编号对question表进行响应的删除操作
        QuestionDao qd = new QuestionDao();
        int res = qd.delete(Integer.valueOf(id),request);
        //判断删除操作是否成功
        if(res == 1) {//删除成功
            //通过请求作用域对象保存共享数据
            request.setAttribute("info","删除试题成功");
            //通过请求转发通过delfo.jsp将处理结果写入到响应体中
            request.getRequestDispatcher("/info.jsp").forward(request,response);
        }else {
            //通过请求作用域对象保存共享数据
            request.setAttribute("info","删除试题失败");
            //通过请求转发通过delfo.jsp将处理结果写入到响应体中
            request.getRequestDispatcher("/info.jsp").forward(request,response);
        }
    }
}
