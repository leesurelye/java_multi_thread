package org.thread.chapter8.thread;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.obj.Request;

public class WorkerThread extends Thread
{
    private final Channel channel;
    private volatile boolean terminated = false;

    public WorkerThread(String name, Channel channel)
    {
        super(name);
        this.channel = channel;
    }

    public void run()
    {
        try {
            // 如果不是终止态，就继续执行request
            while (!terminated) {
                try {
                    Request request = channel.getRequest();
                    // 使用工作线程去执行request的请求
                    request.execute();
                }catch (InterruptedException e) {
                    terminated = true;
                }

            }
        } finally {
            System.out.println(Thread.currentThread().getName() + " is terminated");
        }
    }

    public void stopThread()
    {
        terminated = true;
        // 不要使用stop()
        interrupt();
    }
}
