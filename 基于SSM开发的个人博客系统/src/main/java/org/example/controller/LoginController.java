package org.example.controller;

import org.example.entity.JSONResponse;
import org.example.entity.User;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    //用户登录操作
    @RequestMapping("/login")
    @ResponseBody
    public JSONResponse doLogin(User user, HttpServletRequest request) {
        //通过用户的用户名和密码调用相关的dao完成用户登录的验证
        JSONResponse isLoginJson = service.userLogin(user,request);
        //得到service的处理结果，向前端返回ajax响应
        return isLoginJson;
    }
}
