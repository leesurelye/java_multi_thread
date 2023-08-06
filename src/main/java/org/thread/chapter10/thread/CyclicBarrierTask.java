package org.thread.chapter10.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTask implements Runnable
{
    private static final int PHASE = 5;
    private final CyclicBarrier cyclicBarrier;
    private final CountDownLatch downLatch;
    private final int context;

    public CyclicBarrierTask(CyclicBarrier cyclicBarrier, CountDownLatch downLatch, int context)
    {
        this.cyclicBarrier = cyclicBarrier;
        this.downLatch = downLatch;
        this.context = context;
    }

    @Override
    public void run()
    {
        try {
            // 每三个线程步调一致
            for (int i=0; i< 3; i++) {
                doPhrase(i);
                cyclicBarrier.await();
            }
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        } finally {
            downLatch.countDown();
        }
    }
    private void doPhrase(int phrase)
    {
        System.out.printf("%s : CyclicBarrierTask: BEGIN : count= %s, phrase = %s%n",
                Thread.currentThread().getName(), context, phrase);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            System.out.printf("%s : CyclicBarrierTask: END : count= %s, phrase = %s%n",
                    Thread.currentThread().getName(), context, phrase);
        }
    }
}
