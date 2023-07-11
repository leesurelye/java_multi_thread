package org.thread.chapter10.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MyTask implements Runnable
{
    private final CountDownLatch downLatch;
    private final int context;

    public MyTask(CountDownLatch downLatch, int context)
    {
        this.downLatch = downLatch;
        this.context = context;
    }

    @Override
    public void run()
    {
        doTask();
        // 每一次调用countDown，计数器就会减1
        downLatch.countDown();
    }
    protected void doTask()
    {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": MyTask: BEGIN: context" + context);
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){}
        finally {
            System.out.println(name + ": MyTask: END: context" + context);
        }
    }
}
