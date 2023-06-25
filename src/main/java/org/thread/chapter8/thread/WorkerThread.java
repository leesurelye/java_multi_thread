package org.thread.chapter8.thread;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.obj.Request;

public class WorkerThread extends Thread
{
    private final Channel channel;

    public WorkerThread(String name, Channel channel)
    {
        super(name);
        this.channel = channel;
    }

    public void run()
    {
        while (true) {
            Request request = channel.getRequest();
            // 使用工作线程去执行request的请求
            request.execute();
        }
    }
}
