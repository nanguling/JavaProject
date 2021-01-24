package com.mystudy.demo;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScanService {
    private static DataSource dataSource;

    static {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();

        try {
            String classPath = GetClassPath.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            String decode = URLDecoder.decode(classPath,"utf-8");
            File classesDir = new File(decode);
            System.out.println(classesDir.getAbsoluteFile());
            String dbPath = classesDir.getParent() + "/test.db";
            System.out.println(dbPath);
            sqLiteDataSource.setUrl("jdbc:sqlite://"+dbPath);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dataSource = sqLiteDataSource;
    }

    public static void main(String[] args) throws SQLException {
        //createTable();

        scan();
    }

    private static List<File> scanResult = new ArrayList<>();
    private static List<File> queryResult = new ArrayList<>();
    private static void scan() throws SQLException {
        File root = new File("D:\\biteJAVA\\项目");

        scanResult.clear();
        scanDir(root);

        queryResult.clear();
        querryDir(root);

        System.out.println(scanResult);
        System.out.println(queryResult);

        //scanResult - queryResult 添加数据库
        //queryResult - scanResult 从数据库中删除

    }

    private static void querryDir(File root) throws SQLException {
        String sql = "SELECT name, path FROM file_meta WHERE path LIKE ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement s = connection.prepareStatement(sql)) {
                s.setString(1, root.getAbsolutePath() + "%");

                try (ResultSet rs = s.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString(1);
                        String path = rs.getString(2);

                        File file = new File(path);
                        queryResult.add(file);
                    }
                }
            }
        }
    }

    private static void scanDir(File root) {
        scanResult.add(root);

        if (!root.isDirectory()) {
            return;
        }
        File[] files = root.listFiles();
        if (files == null) {
            return;
        }

        for (File file:files) {
            scanDir(file);
        }
    }

    private static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS file_meta (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR(50) NOT NULL,\n" +
                "    path VARCHAR(1000) NOT NULL,\n" +
                "    is_directory BOOLEAN NOT NULL,\n" +
                "    pinyin VARCHAR(50) NOT NULL,\n" +
                "    pinyin_first VARCHAR(50) NOT NULL,\n" +
                "    size BIGINT NOT NULL,\n" +
                "    last_modified TIMESTAMP NOT NULL\n" +
                ");";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement s = connection.prepareStatement(sql)) {
                s.executeUpdate();
            }
        }
    }
}
