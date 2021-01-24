package com.mystudy.dao;

import com.mystudy.util.DBUtil;
import com.mystudy.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class InitDao {
    //找到init.sql文件，并且读取其内容
    //得到一组sql语句String[]
    //init.sql 是一组以“;”作为分割的多个sql语句
    private static String[] getSqls() {
        try(InputStream is = InitDao.class.getClassLoader().getResourceAsStream("init.sql")) {
            Scanner scanner = new Scanner(is, "utf-8");
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line);
            }
            return sb.toString().split(";");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() {
        try {
            Connection c = DBUtil.getConnection();

            String[] sqls = InitDao.getSqls();
            for (String sql : sqls) {
                LogUtil.log("执行的sql为： %s",sql);
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    if (sql.toUpperCase().startsWith("SELECT")) {
                        try (ResultSet resultSet = ps.executeQuery()) {
                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            int rowCount = 0;
                            while (resultSet.next()) {
                                StringBuilder sb = new StringBuilder("|");
                                for (int i = 1; i < columnCount; i++) {
                                    String value = resultSet.getString(i);
                                    sb.append(value).append(" | ");
                                }
                                rowCount++;
                                LogUtil.log(sb.toString());
                            }
                            LogUtil.log("一共查询出 %d 行",rowCount);
                        }
                    }else {
                        int i = ps.executeUpdate();
                        LogUtil.log("受到影响的一共有 %d 行",i);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        InitDao.init();
    }
}
