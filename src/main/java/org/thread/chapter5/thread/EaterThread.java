package org.thread.chapter5.thread;

import org.thread.chapter5.obj.Table;

import java.util.Random;

public class EaterThread extends Thread
{
    private final Random random = new Random(1234L);

    private final Table table;

    public EaterThread(String threadName, Table table)
    {
        super(threadName);
        this.table = table;
    }

    @Override
    public void run()
    {
        try {
            // 不停地吃
            while (true) {
                String cake = table.take();
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){}
    }
}
