package org.thread.chapter11.case2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
//    public static void main(String[] args)
//    {
//        new ClientThread("Alice").start();
//        new ClientThread("Bob").start();
//        new ClientThread("Chris").start();
//    }
//    practice 11-8
    private static final int TASKS = 10;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i ++){
            Thread thread = new Thread(() -> {
                Log.println("Hello");
                Log.close();
            });
            executorService.execute(thread);
        }
    }
}
