package org.thread.chapter3.thread;

import org.thread.chapter3.obj.Request;
import org.thread.chapter3.obj.RequestQueue;

import java.util.Random;

public class ServerThread extends Thread
{
    private final RequestQueue queue;
    private final Random random = new Random(123);

    public ServerThread(RequestQueue queue, String name)
    {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                //每隔2秒取一次请求
                Thread.sleep(random.nextInt(2000));
                Request request = this.queue.getRequest();
                System.out.println(currentThread().getName() + " gets request : " + request);
            }
        } catch (InterruptedException e) {
        }
//        try {
//            for (int i = 0; i < 10000; i++) {
//                Request request = this.queue.getRequest();
//                System.out.println(currentThread().getName() + " gets request : " + request);
//                Thread.sleep(random.nextInt(1000));
//            }
//        }catch (InterruptedException e) {
//        }
    }
}