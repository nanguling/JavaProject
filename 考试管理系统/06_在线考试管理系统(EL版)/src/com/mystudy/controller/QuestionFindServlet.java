package com.mystudy.controller;

import com.mystudy.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用Dao类通过JDBC查询question表
        QuestionDao qd = new QuestionDao();
        List list = qd.select(request);
        //将返回的结果通过请求作用域对象的Attribute属性变为共享数据
        request.setAttribute("outfo",list);
        //通过请求转发将处理结果交给outfo.jsp写入到响应体并输出
        request.getRequestDispatcher("/outfo.jsp").forward(request,response);
    }
}
