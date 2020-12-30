package org.example.service;

import org.example.entity.JSONResponse;
import org.example.entity.User;

import javax.servlet.http.HttpServletRequest;


public interface LoginService {
    JSONResponse userLogin(User user, HttpServletRequest request);
}
