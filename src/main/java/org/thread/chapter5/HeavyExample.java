package org.thread.chapter5;

import org.thread.chapter5.thread.HeavyThread;

public class HeavyExample
{
    public static void main(String[] args)
    {
        HeavyThread heavyThread = new HeavyThread();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}
        heavyThread.interrupt();
    }
}
