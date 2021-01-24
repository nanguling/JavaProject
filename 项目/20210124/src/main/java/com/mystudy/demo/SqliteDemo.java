package com.mystudy.demo;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteDemo {
    public static void main(String[] args) throws SQLException {
        //创建DataSource（区别）
        //获取Connection
        //操作

        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl("jdbc:sqlite://D:/biteJAVA/项目/存放数据库结果/test.db");

        DataSource dataSource = sqLiteDataSource;

        Connection connection = dataSource.getConnection();

        /*PreparedStatement ps = connection.prepareStatement("CREATE TABLE file_meta (id int)");
        ps.executeUpdate();*/

        PreparedStatement ps = connection.prepareStatement("insert into file_meta (id) values (1),(2)");
        ps.executeUpdate();

        PreparedStatement s = connection.prepareStatement("select * from file_meta");
        ResultSet rs = s.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

        connection.close();
    }
}
