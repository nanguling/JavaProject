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
    public List<FileMeta> query(String keyWord) {
        String sql = "select id,name,path,is_directory,pinyin,pinyin_first,size,last_modified from file_meta where name like ? or pinyin like ? or pinyin_first like ?";
        LogUtil.log("【查询】操作执行的sql为： %s, %s",sql,keyWord);
        try {
            Connection c = DBUtil.getConnection();
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, "%" + keyWord + "%");
                ps.setString(2, "%" + keyWord + "%");
                ps.setString(3, "%" + keyWord + "%");
                List<FileMeta> res = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String path = resultSet.getString("path");
                        boolean isDirectory = resultSet.getBoolean("is_directory");
                        String pinyin = resultSet.getString("pinyin");
                        String pinyinFirst = resultSet.getString("pinyin_first");
                        Long size = resultSet.getLong("size");
                        Long modified = resultSet.getLong("last_modified");

                        FileMeta fileMeta = new FileMeta(id,name,path,isDirectory,pinyin,pinyinFirst,size,modified);
                        res.add(fileMeta);

                        ;
                    }
                    LogUtil.log("【查询】操作一共查询出 %d 行数据",res.size());
                    return res;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<FileMeta> queryByPath(String queryPath) {
        String sql = "select id,name,path,is_directory,pinyin,pinyin_first,size,last_modified from file_meta where path like ?";
        LogUtil.log("【查询】操作执行的sql为： %s, %s",sql,queryPath);
        try {
            Connection c = DBUtil.getConnection();
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, queryPath + "%");
                List<FileMeta> res = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String path = resultSet.getString("path");
                        boolean isDirectory = resultSet.getBoolean("is_directory");
                        String pinyin = resultSet.getString("pinyin");
                        String pinyinFirst = resultSet.getString("pinyin_first");
                        Long size = resultSet.getLong("size");
                        Long modified = resultSet.getLong("last_modified");

                        FileMeta fileMeta = new FileMeta(id,name,path,isDirectory,pinyin,pinyinFirst,size,modified);
                        res.add(fileMeta);

                        ;
                    }
                    LogUtil.log("【查询】操作一共查询出 %d 行数据",res.size());
                    return res;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
