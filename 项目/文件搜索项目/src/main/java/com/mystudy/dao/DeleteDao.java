package com.mystudy.dao;

import com.mystudy.util.DBUtil;
import com.mystudy.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteDao {
    public void delete(List<Integer> ids) {
        try {
            Connection connection = DBUtil.getConnection();//由于实现成一个线程只有一个connection对象，因此不需要关闭

            //jdk8中支持的stream用法
            Stream<Integer> stream = ids.stream();
            Stream<String> stringStream = stream.map(id -> "?");
            List<String> collect = stringStream.collect(Collectors.toList());

            String placeHolder = String.join(",", collect);

            String sql = String.format("delete from file_meta where id in (%s)", placeHolder);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < ids.size(); i++) {
                    Integer id = ids.get(i);
                    ps.setInt(i + 1, id);
                }
                LogUtil.log("【删除】操作执行的sql: %s, %s,受影响的行数 %d 行", sql, ids, ids.size());
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
