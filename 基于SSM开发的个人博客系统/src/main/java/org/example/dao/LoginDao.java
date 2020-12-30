package org.example.dao;


import org.example.entity.User;

public interface LoginDao {

    //验证用户登录
    User userLogin(String name);
}
