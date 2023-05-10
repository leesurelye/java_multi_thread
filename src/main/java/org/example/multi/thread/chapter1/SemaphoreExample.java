package org.example.multi.thread.chapter1;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Log {
    public static void printLog(String s){
        System.out.println(Thread.currentThread().getName() + ":" + s);
    }
}

/**
 * 有限资源
 */
class BoundedResource {
    private final Semaphore semaphore;

    // 资源的许可个数
    private final int permits;

    private final static Random random = new Random(1234);

    public BoundedResource(int permits)
    {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    private void doUser() throws InterruptedException {
        Log.printLog("BEGIN: used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(3000));
        Log.printLog("END: used = " + (permits - semaphore.availablePermits()));
    }
    public void use() throws InterruptedException{
        semaphore.acquire();
        try{
            doUser();
        }finally {
            semaphore.release();
        }
    }
}


class SemaphoreThread extends Thread {
    private final BoundedResource resource;
    private final Random random = new Random(13445);
    public SemaphoreThread(BoundedResource resource)
    {
        this.resource = resource;
    }

    @Override
    public void run()
    {
        try{
            resource.use();
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {}
    }
}
/**
 * Single Thread Execution 用于确保某个区域只能由一个线程执行
 * Semaphore 用于确保某个区域只能由N个线程执行
 * 如果能够使用的资源由N个，需要这些资源的线程个数多于N个，就需要交通管制
 */
public class SemaphoreExample
{
    public static void main(String[] args)
    {
        // 设置3个资源
        BoundedResource resource = new BoundedResource(3);
        // 10个线程使用3个资源
        for(int i = 0; i < 10; i++) {
            new SemaphoreThread(resource).start();
        }
    }
}
