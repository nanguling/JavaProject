package com.mystudy.dao;

import com.mystudy.entity.Users;
import com.mystudy.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    JdbcUtil ju = new JdbcUtil();
    //用户注册
    //JDBC规范中，Connection创建于销毁最浪费时间
    public int add(Users users, HttpServletRequest request) {
        String sql = "insert into users (name,password,sex,email) values (?,?,?,?)";
        //创建数据库连接&sql命令对象
        PreparedStatement ps =ju.createPs(sql,request);
        int res = 0;
        try {
            //传参
            ps.setString(1,users.getName());
            ps.setString(2,users.getPassword());
            ps.setString(3,users.getSex());
            ps.setString(4,users.getEmail());
            //执行sql
            res = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭资源
            ju.close(request);
        }
        return res;
    }
    //用户查询
    public List find() {
        String sql = "select * from users";
        List list = new ArrayList();
        //创建数据库连接&sql命令对象
        PreparedStatement ps = ju.createPs(sql);
        ResultSet rs = null;
        try {
            //执行sql
            rs = ps.executeQuery();
            //处理结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                //将结果放入Users对象，这样rs被关闭，里面保存的字段信息不会被销毁
                Users users = new Users(id,name,password,sex,email);
                list.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭资源
            ju.close(rs);
        }
        return list;
    }
    //根据用户编号删除表中用户
    public int delete(int id) {
        String sql = "delete from users where id = ?";
        //创建数据库连接&sql命令对象
        PreparedStatement ps = ju.createPs(sql);
        int res = 0;
        try {
            //传参
            ps.setInt(1,id);
            //执行sql
            res = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭资源
            ju.close();
        }
        return res;
    }
    //登录验证
    public int login(String name,String password) {
        String sql = "select count(*) from users where name = ? and password = ?";
        //创建数据库连接&sql命令对象
        PreparedStatement ps = ju.createPs(sql);
        ResultSet rs = null;
        int res = 0;
        try {
            //sql传参
            ps.setString(1,name);
            ps.setString(2,password);
            //运行sql
            rs = ps.executeQuery();
            //处理结果集
            while (rs.next()) {
                res = rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ju.close(rs);
        }
        return res;
    }
}
