package com.mystudy.entity;

import java.io.File;

public class FileMeta {
    private final Integer id;
    private final String name;
    private final String path;
    private final boolean directory;
    private final Long length;
    private final Long lastModifiedTimeStamp;

    //提供给扫描程序用
    public FileMeta(File file) {
        this.id = null;
        this.name = file.getName();
        this.path = file.getAbsolutePath();
        this.directory = file.isDirectory();
        this.length = file.length();
        this.lastModifiedTimeStamp = file.lastModified();
    }

    //提供给数据库使用
    public FileMeta(Integer id,String name,String path,boolean directory,Long length,Long lastModifiedTimeStamp) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.directory = directory;
        this.length = length;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getLength() {
        return String.valueOf(length);
    }

    public String getModified() {
        return String.valueOf(lastModifiedTimeStamp);
    }
}
