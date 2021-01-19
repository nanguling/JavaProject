package count_down_latch_demo;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int N = 100;
        final int[][] arr = new int[N][];

        for (int i = 0; i < N; i++) {
            arr[i] = new int[100];
        }

        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(N);

        //做一些准备工作，在子线程处理任务之前
        Random random = new Random();
        for (int i = 0;i < N;i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            final int[] targetArray = arr[i];
                Thread work = new Thread(() -> {
                    try {
                        //做一些任务的准备工作
                        //等待直到收到开始信号
                        startSignal.await();

                        //收到开始信号，开始执行任务
                        Arrays.sort(targetArray);

                        Thread thread = Thread.currentThread();
                        System.out.printf("%s:已经完成任务\n",thread.getName());
                        //任务执行完毕，但是还没有汇报

                        doneSignal.countDown();//汇报完成的任务
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },String.format("工作线程-%02d",i));
                work.start();
        }
        
        startSignal.countDown();//开始信号，通知所有子线程开始执行任务

        doneSignal.await();//等待所有子线程完成任务

        //对子线程回报的工作进行汇总
        for (int i = 0; i < N; i++) {
            if (arr[i][0] > arr[i][100-1]) {
                System.out.println("排序出错");
            }
        }
    }
}
