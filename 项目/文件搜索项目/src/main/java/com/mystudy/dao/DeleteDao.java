package com.mystudy.dao;

import com.mystudy.util.DBUtil;
import com.mystudy.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteDao {
    public static void delete(List<Integer> ids) throws SQLException {
        Connection connection = DBUtil.getConnection();//由于实现成一个线程只有一个connection对象，因此不需要关闭

        //jdk8中支持的stream用法
        Stream<Integer> stream = ids.stream();
        Stream<String> stringStream = stream.map(id -> "?");
        List<String> collect = stringStream.collect(Collectors.toList());

        String placeHolder = String.join(",",collect);

        String sql = String.format("delete from file_meta where id in (%s)",placeHolder);
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0;i < ids.size();i++) {
                Integer id = ids.get(i);
                ps.setInt(i+1,id);
            }
            LogUtil.log("执行的sql: %s, %s",sql,ids);
            ps.executeUpdate();
        }
    }

    //测试
    public static void main(String[] args) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        delete(ids);
    }
}
