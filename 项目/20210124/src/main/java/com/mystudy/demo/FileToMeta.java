package com.mystudy.demo;

import java.io.File;

public class FileToMeta {
    public static void main(String[] args) {
        File file = new File("");
        System.out.println(file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.isDirectory());
        System.out.println(file.length());
        System.out.println(file.lastModified());
    }
}
