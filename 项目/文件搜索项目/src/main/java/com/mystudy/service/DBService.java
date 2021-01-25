package com.mystudy.service;

import com.mystudy.dao.InitDao;

//主要提供给用户在搜索框中输入了关键字之后，进行结果查询的
public class DBService {
    public void init() {
        InitDao.init();
    }
}
