package org.example.dao;


import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.JdbcUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    //登录
    public static User login(String userName) {
        String sql = "select id, username, password, nickname, birthday, phone_number, email, head from user where username = ?";
        //创建数据库连接&sql命令对象
        PreparedStatement ps = JdbcUtil.createPs(sql);
        ResultSet rs = null;
        User user = null;
        try {
            //传参
            ps.setString(1,userName);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                Date birthday = rs.getDate("birthday");
                if(birthday != null)
                    user.setBirthday(new Date(birthday.getTime()));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setHead(rs.getString("head"));
            }
        } catch (SQLException e) {
            throw new AppException("Jdbc551","数据库校验错误",e);
        } finally {
            JdbcUtil.close(rs);
        }
        return user;
    }
}
