package com.mystudy.util;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;

//使用单例模式 - 懒汉模式
public class DBUtil {
    private static volatile DataSource dataSource = null;

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            return initConnection();
        }
    };

    //关于单例的二次判断模式
    public static Connection initConnection() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    initDataSource();
                }
            }
        }
        //connection对象是线程不安全的 -- 每个线程都必须有自己的connection对象
        //一个线程只有一个connention对象没有问题
        //使用ThreadLocal实现，每个线程都有自己的connection对象
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initDataSource() {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        String url = "jdbc:sqlite://"+getDatabasePath();
        sqLiteDataSource.setUrl(url);
        dataSource = sqLiteDataSource;
    }

    private static String getDatabasePath() {
        String classPath = DBUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            String decode = URLDecoder.decode(classPath, "utf-8");
            File file = new File(decode);
            String path = file.getParent() + File.separator + "searcher.db";
            LogUtil.log("数据库的执行路径为： %s", path);
            return path;
        }catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
