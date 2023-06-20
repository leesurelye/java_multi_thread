package org.thread.chapter7.obj;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryHost
{
    private final Helper helper = new Helper();
    private final ThreadFactory threadFactory;

    // 线程工厂
    public ThreadFactoryHost(ThreadFactory threadFactory)
    {
        this.threadFactory = threadFactory;
    }

    public void request(final int count, final char c)
    {
        System.out.println("\t request (" + count + ", " + c + ") BEGIN");
        // 创建新的线程
        new Thread(() -> helper.handle(count, c)).start();
        System.out.println("\t request (" + count + ", " + c + ") END");
    }

    public void request1(final int count, final char c)
    {
        System.out.println("\t request (" + count + ", " + c + ") BEGIN");
        // 通过threadFactory创建新的线程
        threadFactory.newThread(()->helper.handle(count, c)).start();
        System.out.println("\t request (" + count + ", " + c + ") END");
    }
}
