package org.thread.chapter10;

import org.thread.chapter10.thread.CountUpThread;

public class BaseExample
{
    public static void main(String[] args)
    {
        System.out.println("Main Begin");
        try {
            CountUpThread thread = new CountUpThread();
            thread.start();

            Thread.sleep(10000);
            System.out.println("main: shutdownRequest");

            thread.shutdownRequest();
            System.out.println("Main join");
            // Main线程等待thread线程结束后才会继续向下执行
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main end");
    }
}
