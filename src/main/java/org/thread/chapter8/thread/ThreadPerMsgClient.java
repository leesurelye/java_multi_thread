package org.thread.chapter8.thread;

import org.thread.chapter8.obj.Request;
import org.thread.chapter8.obj.ThreadPerMsg;

public class ThreadPerMsgClient extends Thread
{
    private final ThreadPerMsg perMsg;

    public ThreadPerMsgClient(String name, ThreadPerMsg perMsg)
    {
        super(name);
        this.perMsg = perMsg;
    }

    @Override
    public void run()
    {
        try {
            int i = 0;
            while (true) {
                Request request = new Request(getName(), String.valueOf(i));
                perMsg.handle(request);
                i ++;
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
