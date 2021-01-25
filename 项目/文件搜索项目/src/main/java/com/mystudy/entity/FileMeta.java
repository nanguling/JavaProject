package com.mystudy.entity;

import com.mystudy.util.OutPutUtil;
import com.mystudy.util.PinYinUtil;

import java.io.File;
import java.util.Objects;

public class FileMeta {
    private final Integer id;
    private final String name;
    private final String path;
    private final boolean directory;
    private final String pinyin;
    private final String pinyinFirst;
    private final Long length;
    private final Long lastModifiedTimeStamp;

    //提供给扫描程序用
    public FileMeta(File file) {
        this.id = null;
        this.name = file.getName();
        this.path = file.getAbsolutePath();
        this.directory = file.isDirectory();
        this.pinyin = PinYinUtil.getPinYin(name);
        this.pinyinFirst = PinYinUtil.getPinYinFirst(name);
        this.length = file.length();
        this.lastModifiedTimeStamp = file.lastModified();
    }

    //提供给数据库使用
    public FileMeta(Integer id,String name,String path,boolean directory,String pinyin,String pinyinFirst,Long length,Long lastModifiedTimeStamp) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.directory = directory;
        this.pinyin = pinyin;
        this.pinyinFirst = pinyinFirst;
        this.length = length;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getPinyinFirst() {
        return pinyinFirst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMeta fileMeta = (FileMeta) o;
        return Objects.equals(name, fileMeta.name) &&
                Objects.equals(path, fileMeta.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }

    @Override
    public String toString() {
        return "FileMeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", directory=" + directory +
                ", pinyin='" + pinyin + '\'' +
                ", pinyinFirst='" + pinyinFirst + '\'' +
                ", length=" + length +
                ", lastModifiedTimeStamp=" + lastModifiedTimeStamp +
                '}';
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

    public String getLengthUi() {
        return OutPutUtil.formatLength(length);
    }

    public Long getLength() {
        return length;
    }

    public String getModifiedUi() {
        return OutPutUtil.formatTimstamp(lastModifiedTimeStamp);
    }

    public Long getModified() {
        return lastModifiedTimeStamp;
    }
}
