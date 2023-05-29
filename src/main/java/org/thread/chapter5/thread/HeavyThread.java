package org.thread.chapter5.thread;

import org.thread.chapter5.obj.HeavyJob;

public class HeavyThread extends Thread
{
    public HeavyThread()
    {
        super("HeavyJob");
    }

    @Override
    public void run()
    {
        try {
            HeavyJob.execute(10000);
        } catch (InterruptedException e) {}
    }
}
