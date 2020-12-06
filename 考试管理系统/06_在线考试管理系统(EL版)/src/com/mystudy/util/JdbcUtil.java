package com.mystudy.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * 将JDBC规范下相关对象【创建】与【关闭】封装到方法
 */
public class JdbcUtil {
    //创建数据库连接池
    DataSource dataSource = new MysqlDataSource();
    public JdbcUtil(){
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
    }
    private Connection con = null;
    private PreparedStatement ps = null;

    //---------通过全局作用域对象得到Connection对象--------start//
    //重载createCon方法
    public Connection createCon(HttpServletRequest request) {
        //1.得当当前全局作用域对象
        ServletContext appliaction = request.getServletContext();
        //2.通过全局作用域对象得到Connection对象
        Map map = (Map) appliaction.getAttribute("key1");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            this.con = (Connection) it.next();;
            if ((boolean)map.get(this.con)) {
                //此con被使用了，置为false
                map.put(this.con,false);
                break;
            }
        }
        return this.con;
    }
    //重载createPs方法
    //创建sql命令对象
    public PreparedStatement createPs(String sql,HttpServletRequest request) {
        try {
            this.ps = createCon(request).prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.ps;
    }
    //重载关闭资源方法
    public void close(HttpServletRequest request) {
        try {
            if (this.ps != null) {
                this.ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //得到当前全局作用域对象
        ServletContext appliaction = request.getServletContext();
        //得到全局作用域对象中的共享数据
        Map map = (Map) appliaction.getAttribute("key1");
        //将map中的con置为true，即为关闭
        map.put(this.con,true);
    }
    //重载关闭ResultSet资源
    public void close(ResultSet rs,HttpServletRequest request) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(request);
    }
    //---------通过全局作用域对象得到Connection对象----------end//

    //创建数据库连接
    public Connection createCon() {
        //创建数据库连接
        try {
            this.con = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.con;
    }
    //创建sql命令对象
    public PreparedStatement createPs(String sql) {
        try {
            this.ps = createCon().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.ps;
    }
    //关闭资源
    public void close() {
        try {
            if (this.ps != null) {
                this.ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //关闭ResultSet资源
    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
    }
}
