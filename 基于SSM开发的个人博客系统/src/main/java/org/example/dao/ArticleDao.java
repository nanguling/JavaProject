package org.example.dao;

import org.example.entity.Article;

import java.util.List;

public interface ArticleDao {
    //查询当前用户下的所有文章
    public List select(Integer id);

    //删除当前用户的文章
    public int delete(List<Integer> list) ;

    //当前用户添加文章
    public int add(Article article);

    //对某一篇文章进行修改
    public int upDate(Article article);

    //显示当前用户某一篇文章的详情
    public Article selectOne(Integer id);
}
