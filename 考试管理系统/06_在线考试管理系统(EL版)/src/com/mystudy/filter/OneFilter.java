package com.mystudy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //1.调用请求对象读取当前请求包中请求行中uri，了解用户要访问的资源文件名
        String uri = request.getRequestURI();//  /网站名/资源文件名
        //2.判断当前uri是否包含login关键字（login.html/login）或者是否为默认请求（/myweb），如果是则放行
        if (uri.indexOf("login") != -1 || "/myweb/".equals(uri)) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.不包含login关键字和默认请求时，判断用户当前在tomcat中是否有HttpSession，有则放放行，没有就是恶意登录
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.getRequestDispatcher("/login.html").forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
