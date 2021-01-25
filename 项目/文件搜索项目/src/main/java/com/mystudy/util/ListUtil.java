package com.mystudy.util;

import com.mystudy.entity.FileMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
    //写一个泛型方法
    public static <E>List<E> differenceSet(List<E> l1,List<E> l2) {
        List<E> res = new ArrayList<>();

        for (E item:l1) {
            if (!l2.contains(item)) { // E类型的item必须正确支持equals方法
                res.add(item);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<FileMeta> fileMetas = Arrays.asList(
                new FileMeta(new File("D:\\biteJAVA\\项目\\文件搜索项目\\测试2.txt")),
                new FileMeta(new File("D:\\biteJAVA\\项目\\文件搜索项目\\测试3.txt")),
                new FileMeta(new File("D:\\biteJAVA\\项目\\文件搜索项目\\测试4.txt"))
        );

        List<FileMeta> fileMetas1 = Arrays.asList(
                new FileMeta(new File("D:\\biteJAVA\\项目\\文件搜索项目\\测试.txt")),
                new FileMeta(new File("D:\\biteJAVA\\项目\\文件搜索项目\\测试1.txt")),
                new FileMeta(new File("D:\\biteJAVA\\项目\\文件搜索项目\\测试2.txt"))
        );

        System.out.println(differenceSet(fileMetas, fileMetas1));
        System.out.println("==================================");
        System.out.println(differenceSet(fileMetas1,fileMetas));
    }
}
