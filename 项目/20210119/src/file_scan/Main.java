package file_scan;

import java.io.File;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        File root = new File("D:\\biteJAVA");

        scanDirectory(root);

        System.out.println("所有文件扫描完成");
    }

    private static void scanDirectory(File root) throws InterruptedException {
        ExecutorService threadPool = new ThreadPoolExecutor(10,10,0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        AtomicInteger counter = new AtomicInteger(0);

        CountDownLatch doneSignal = new CountDownLatch(1);//结束信号

        ScanTask task = new ScanTask(root,threadPool,counter,doneSignal);
        threadPool.execute(task);

        doneSignal.await();//等待结束
        threadPool.shutdown();//关闭线程池
    }
}
