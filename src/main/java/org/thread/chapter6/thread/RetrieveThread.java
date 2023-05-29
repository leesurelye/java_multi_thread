package org.thread.chapter6.thread;

import org.thread.chapter6.obj.Database;

import java.util.concurrent.atomic.AtomicInteger;

public class RetrieveThread extends Thread
{
    private final Database<String, String> database;

    private static final AtomicInteger atomicCounter = new AtomicInteger(0);
    private final String key;

    public RetrieveThread(Database<String, String> database, String key)
    {
        super("Retrieve");
        this.database = database;
        this.key = key;
    }

    @Override
    public void run()
    {
        try{
            while (true) {
                int count = atomicCounter.incrementAndGet();
                String value = database.retrieve(key);
                System.out.println(count + " : " + key + " => " + value);
            }
        }catch (InterruptedException e) {}
    }
}
