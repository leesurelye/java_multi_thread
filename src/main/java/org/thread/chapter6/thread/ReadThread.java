package org.thread.chapter6.thread;

import org.thread.chapter6.obj.Data;

public class ReadThread extends Thread
{
    private final Data data;

    public ReadThread(String threadName, Data data)
    {
        super(threadName);
        this.data = data;
    }

    @Override
    public void run()
    {
        try{
            while (true) {
                char[] read = data.read();
                System.out.println(getName() + " reads " + String.valueOf(read));
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {}
    }
}
