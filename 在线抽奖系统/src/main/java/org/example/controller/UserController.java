package org.example.controller;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest request) {//username,password
        //根据账号查用户
        User exist = userService.queryByUserName(user.getUsername());
        //用户不存在
        if (exist == null) {
            throw new AppException("LOG404","用户不存在");
        }
        //用户存在，校验密码
        if (!exist.getPassword().equals(user.getPassword())) {
            throw new AppException("LOG405","密码错误");
        }
        //校验通过，保存数据库的用户到session
        HttpSession session = request.getSession();//创建session
        session.setAttribute("user",exist);//将查询到的用户保存到session
        return null;//登陆成功
    }

    @PostMapping("/register")
    public Object register(User user, MultipartFile headFile) {
        //校验请求数据：这里省略

        //保存上传的用户头像，到服务端的本地
        if (headFile != null) {
            String head = userService.saveHead(headFile);

            //把上传的路径映射为http服务路径
            user.setHead(head);
        }

        //把用户头像的http服务路径设置到user的head属性中，把user插入到数据库
        userService.register(user);

        return null;
    }

    @GetMapping("/logout")
    public Object logout(HttpSession session) {
        session.removeAttribute("user");
        return null;
    }
}
