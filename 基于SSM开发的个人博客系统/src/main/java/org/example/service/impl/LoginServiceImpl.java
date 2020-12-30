package org.example.service.impl;

import org.example.dao.LoginDao;
import org.example.entity.JSONResponse;
import org.example.entity.User;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao dao;

    @Override
    public JSONResponse userLogin(User user, HttpServletRequest request) {
        JSONResponse json = new JSONResponse();

        if (!user.getUsername().equals("")) {
            //调用dao中的方法，完成登录验证
            User loginUser = dao.userLogin(user.getUsername());
            if (loginUser != null) {
                if (user.getUsername().equals(loginUser.getUsername())) {
                    if (user.getPassword().equals(loginUser.getPassword())) {
                        //验证通过，给用户创建一个session保存用户信息
                        HttpSession session = request.getSession();
                        session.setAttribute("user",loginUser);
                        json = getJson(true,null,null);
                    } else {
                        json = getJson(false,"LOG446","密码错误");
                    }
                } else {
                    json = getJson(false,"LOG445","用户不存在");
                }
            }else {
                json = getJson(false,"LOG445","用户不存在");
            }
        }else {
            json = getJson(false,"LOG444","请输入用户名");
        }
        
        return json;
    }

    public static JSONResponse getJson(boolean success,String code,String message) {
        JSONResponse json = new JSONResponse();
        json.setSuccess(success);
        json.setCode(code);
        json.setMessage(message);
        return json;
    }
}

