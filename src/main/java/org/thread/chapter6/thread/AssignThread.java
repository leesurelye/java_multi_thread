package org.thread.chapter6.thread;

import org.thread.chapter6.obj.Database;

public class AssignThread extends Thread
{
    private final Database<String, String> database;

    private final String key;

    private final String value;

    public AssignThread(Database<String, String> database, String key, String value)
    {
        super("Assign");
        this.key = key;
        this.value = value;
        this.database = database;
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                System.out.println(getName() + " :assign (" + key + ", " + value + " )");
                database.assign(key, value);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {}
    }
}
