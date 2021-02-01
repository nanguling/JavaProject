package com.mystudy.task;

import com.mystudy.service.FileService;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
    传入参数：一个文件夹
    目标：
        1.扫描这个文件夹的所有孩子
        2.针对这个孩子中也是文件夹的，包装成一个新的任务提交给线程池
        3.收集所以体验的非文件夹孩子们
        4.FileService.service(...)入库（先对比，后入库）
 */
class ScanJob implements Runnable {
    private File root;
    private ExecutorService threadPool;
    private FileService fileService;

    public ScanJob(File root,ExecutorService threadPool,FileService fileService) {
        this.root = root;
        this.threadPool = threadPool;
        this.fileService = fileService;
    }
    @Override
    public void run() {

    }
}


public class FileScanner {
    private ExecutorService threadPool = new ThreadPoolExecutor(4,4,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));


    private final FileService fileService = new FileService();

    /*public void scan(File root) {
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
    }*/

}
