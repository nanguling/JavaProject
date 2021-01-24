package com.mystudy.demo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class GetClassPath {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String classPath = GetClassPath.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        System.out.println(classPath);
        String decode = URLDecoder.decode(classPath,"utf-8");
        System.out.println(decode);
        File classesDir = new File(decode);
        System.out.println(classesDir.getAbsoluteFile());
    }
}
