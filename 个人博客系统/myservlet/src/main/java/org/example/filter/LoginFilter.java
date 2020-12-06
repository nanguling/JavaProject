package org.example.filter;

import org.example.model.JSONResponse;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        //判断当前文件是否和登录相关以及是前端加载资源相关的文件，或者是默认登录页面
        if (uri.contains("login") || uri.contains("static") || uri.contains("app.js") || "/myweb/".equals(uri)) {
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (session == null) {
            //判断访问的是否为后端资源，后端资源返回json字符串给前端；否则跳转页面至登录界面
            fun(request,response);
        }else {
            User user =(User) session.getAttribute("key");
            if (user == null) {
                //判断访问的是否为后端资源，后端资源返回json字符串给前端；否则跳转页面至登录界面
                fun(request,response);
            }else {
                //放行
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
    public void fun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前uri，判断访问的资源文件是什么
        String uri = request.getRequestURI();
        if (uri.contains("jsp")) {
            //当前访问的是敏感资源，直接通过请求转发到登录页面
            request.getRequestDispatcher("/view/login.html").forward(request,response);
        }else {
            //当前访问的是后端资源，向前端返回一个json字符串,并且状态码设置为401
            response.setStatus(401);
            JSONResponse json = new JSONResponse();
            //设置错误码和错误信息
            json.setCode("LOG444");
            json.setMessage("恶意登陆!请登陆后操作!");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            //json对象序列化为json字符串
            out.println(JSONUtil.serialize(json));
            out.flush();
            out.close();
        }
    }

    @Override
    public void destroy() {

    }
}
