package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    //查询当前用户下的文章
    public List select(Integer id) {
        String sql = "select id,title from article where user_id = ?";
        List list = new ArrayList();
        ResultSet rs = null;
        //创建数据库连接&sql命令对象
        PreparedStatement ps = JdbcUtil.createPs(sql);
        try {
            //传参
            ps.setInt(1,id);
            //执行sql
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                list.add(article);
            }
        } catch (SQLException e) {
            throw new AppException("Jdbc301","数据库检索文章出错",e);
        } finally {
            JdbcUtil.close(rs);
        }
        return list;
    }

    //删除当前用户的文章
    public int delete(String[] split) {
        //对传进来的字符串数组进行拼接，成为一个sql语句
        StringBuilder sql = new StringBuilder( "delete from article where id in (");
        for (int i = 0; i < split.length; i++) {
            if (i != 0)
                sql.append(",");
            sql.append("?");
        }
        sql.append(")");
        //创建数据库连接&sql命令对象
        PreparedStatement ps = JdbcUtil.createPs(sql.toString());
        int res = 0;
        try {
            //替换占位符
            for (int i = 0; i < split.length; i++) {
                ps.setInt(i+1,Integer.parseInt(split[i]));
            }
            //执行sql
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("Jdbc300","数据库删除文章操作失败",e);
        } finally {
            JdbcUtil.close();
        }
        return res;
    }

    //当前用户添加文章
    public int add(Article article) {
        String sql = "insert into article (title,content,use    r_id) values (?,?,?)";
        //创建数据库连接&sql命令对象
        PreparedStatement ps = JdbcUtil.createPs(sql);
        int res = 0;
        try {
            //替换占位符
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getContent());
            ps.setInt(3,article.getUserId());
            //执行sql
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("Jdbc301","数据库添加文章操作失败",e);
        } finally {
            JdbcUtil.close();
        }
        return res;
    }

    //对某一篇文章进行修改
    public int upDate(Article article) {
        String sql = "update article set title = ?,content = ? where id = ?";
        //创建数据库连接&sql命令对象
        PreparedStatement ps = JdbcUtil.createPs(sql);
        int res = 0;
        try {
            //替换占位符
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getContent());
            ps.setInt(3,article.getId());
            //执行sql
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("Jdbc302","数据库修改文章操作失败",e);
        } finally {
            JdbcUtil.close();
        }
        return res;
    }

    //显示当前用户某一篇文章的详情
    public Article select(int id) {
        String sql = "select title,content from article where id = ?";
        //创建数据库连接&sql命令对象
        PreparedStatement ps = JdbcUtil.createPs(sql);
        Article article = new Article();
        ResultSet rs = null;
        try {
            //替换占位符
            ps.setInt(1,id);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集
            while (rs.next()) {
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
        }
        return article;
    }
}
