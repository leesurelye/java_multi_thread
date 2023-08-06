package org.thread.chapter10;

import org.thread.chapter10.thread.CyclicBarrierTask;
import org.thread.chapter10.thread.MyTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample
{
    private static final int THREADS = 3;
    public static void main(String[] args)
    {
        System.out.println("main BEGIN");
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        Runnable barrierAction = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Barrier Action!");
            }
        };
        // 使线程步调一致
        CyclicBarrier phaseBarrier = new CyclicBarrier(THREADS, barrierAction);
        //用于确认工作是否结束
        CountDownLatch downLatch = new CountDownLatch(THREADS);
        try {
            for (int i = 0 ; i < THREADS; i++) {
                executorService.execute(new CyclicBarrierTask(phaseBarrier, downLatch, i + 1));
            }
            System.out.println("AWAIT");
            downLatch.await();
        }catch (InterruptedException e){}
        finally {
            executorService.shutdown();
            System.out.println("main END");
        }
    }
}
