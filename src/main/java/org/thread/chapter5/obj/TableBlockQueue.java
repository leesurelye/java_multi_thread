package org.thread.chapter5.obj;

import java.util.concurrent.ArrayBlockingQueue;

public class TableBlockQueue extends ArrayBlockingQueue<String>
{

    public TableBlockQueue(int capacity)
    {
        super(capacity);
    }

    public void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        super.put(cake);
    }

    public String take() throws InterruptedException {
        String cake = super.take();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
