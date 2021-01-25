package com.mystudy.task;

import com.mystudy.entity.FileMeta;
import com.mystudy.service.FileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {
    private final FileService fileService = new FileService();

    public void scan(File root) {
        scanDir(root);
    }

    private void scanDir(File root) {
        if (!root.isDirectory()) {
            return;
        }

        File[] files = root.listFiles();
        if (files == null) {
            return;
        }

        List<FileMeta> scanList = new ArrayList<>();
        for (File child:files) {
            scanDir(child);
            //判断是否为文件
            if (child.isFile()) {
                scanList.add(new FileMeta(child));
            }
        }
        fileService.service(root.getAbsolutePath(),scanList);
    }

}
