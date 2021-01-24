package com.mystudy.dao;

import com.mystudy.entity.FileMeta;
import com.mystudy.util.DBUtil;
import com.mystudy.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {
    public static List<FileMeta> query(String keyWord) {
        String sql = "select id,name,path,is_directory,size,last_modified from file_meta where name like ? or pinyin like ? or pinyin_first like ?";
        LogUtil.log("执行的sql为： %s, %s",sql,keyWord);
        try {
            Connection c = DBUtil.getConnection();
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, "%" + keyWord + "%");
                ps.setString(2, "%" + keyWord + "%");
                ps.setString(3, "%" + keyWord + "%");
                List<FileMeta> res = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery()) {
                    int rowCount = 0;
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String path = resultSet.getString("path");
                        boolean isDirectory = resultSet.getBoolean("is_directory");
                        Long size = resultSet.getLong("size");
                        Long modified = resultSet.getLong("last_modified");

                        FileMeta fileMeta = new FileMeta(id,name,path,isDirectory,size,modified);
                        res.add(fileMeta);

                        rowCount++;
                    }
                    LogUtil.log("一共查询出 %d 行数据",rowCount);
                    return res;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //测试
    public static void main(String[] args) {
        List<FileMeta> res = QueryDao.query("中");
        System.out.println(res);
    }
}
