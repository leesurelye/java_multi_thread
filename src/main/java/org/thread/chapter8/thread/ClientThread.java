package org.thread.chapter8.thread;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.obj.Request;

//发送工作请求的Thread
public class ClientThread extends Thread
{
    private final Channel channel;
    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run()
    {
        try{
            int i = 0;
            while (true) {
                Request request = new Request(getName(), String.valueOf(i));
                channel.putReques(request);
                Thread.sleep(1000);
                i ++;
            }
        }catch (InterruptedException e) {}
    }
}
