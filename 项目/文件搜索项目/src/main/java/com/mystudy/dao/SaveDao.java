package com.mystudy.dao;

import com.mystudy.entity.FileMeta;
import com.mystudy.util.DBUtil;
import com.mystudy.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//保存扫描出的文件集合
public class SaveDao {
    public void save(List<FileMeta> fileMetas) {
        String sql = "insert into file_meta (name,path,is_directory,pinyin,pinyin_first,size,last_modified) values (?,?,?,?,?,?,?)";
        try {
            Connection c = DBUtil.getConnection();

            try (PreparedStatement ps = c.prepareStatement(sql)) {
                for(FileMeta file:fileMetas) {
                    ps.setString(1,file.getName());
                    ps.setString(2,file.getPath());
                    ps.setBoolean(3,file.isDirectory());
                    ps.setString(4,file.getPinyin());
                    ps.setString(5,file.getPinyinFirst());
                    ps.setLong(6,file.getLength());
                    ps.setLong(7,file.getModified());

                    int i = ps.executeUpdate();
                    LogUtil.log("【添加】操作执行的sql为： %s, %s，受到影响的行数 %d 行",sql,file,i);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
