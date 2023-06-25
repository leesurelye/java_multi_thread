package org.thread.chapter8.thread;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.obj.Request;
import org.thread.chapter8.obj.ThreadPerMsg;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

//发送工作请求的Thread
public class ClientThread extends Thread
{
    private final Channel channel;
    private volatile boolean terminated = false;
    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run()
    {
        try{
            int i = 0;
            while (!terminated) {
                try {
                    Request request = new Request(getName(), String.valueOf(i));
                    channel.putReques(request);
                    Thread.sleep(1000);
                    i ++;
                }catch (InterruptedException e) {
                    terminated = true;
                }
            }
        }finally {
            System.out.println(Thread.currentThread().getName() + " is terminated");
        }
    }
    public void stopThread()
    {
        terminated = true;
        interrupt();
    }
}
