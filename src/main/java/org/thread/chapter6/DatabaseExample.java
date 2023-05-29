package org.thread.chapter6;

import org.thread.chapter6.obj.Data;
import org.thread.chapter6.obj.Database;
import org.thread.chapter6.thread.AssignThread;
import org.thread.chapter6.thread.RetrieveThread;

public class DatabaseExample
{
    public static void main(String[] args)
    {
        Database<String, String> database = new Database<>();
        new AssignThread(database, "Alice", "Alaska").start();
        new AssignThread(database, "ALice", "Australia").start();
        new AssignThread(database, "Bobby", "Brazil").start();
        new AssignThread(database, "Bobby", "Bulgaria").start();

        for(int i=0;i < 100; i++){
            new RetrieveThread(database, "Alice").start();
            new RetrieveThread(database, "Bobby").start();
        }
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e) {}

        System.exit(0);
    }

}
