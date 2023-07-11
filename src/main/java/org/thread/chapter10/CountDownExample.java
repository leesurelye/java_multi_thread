package org.thread.chapter10;

import org.thread.chapter10.thread.MyTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownExample
{
    private static final int TASKS = 10;

    public static void main(String[] args)
    {
        System.out.println("main BEGIN");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch downLatch = new CountDownLatch(TASKS);
        try {
            // 开始工作
            for(int i = 1 ; i<= TASKS; i++) {
                executorService.execute(new MyTask(downLatch, i));
            }
            System.out.println("AWAIT");
            // 等待工作结束，等待计数器变为0，等计数器变为0后，继续向下执行
            downLatch.await();
        } catch (InterruptedException e){}
        finally {
            executorService.shutdown();
            System.out.println("main END");
        }
    }
}
