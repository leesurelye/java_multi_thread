package org.thread.chapter7;

import org.thread.chapter7.obj.Host1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseExample1
{
    public static void main(String[] args)
    {
        System.out.println("main BEGIN");
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 方式一
//        ThreadFactoryHost host = new ThreadFactoryHost(Thread::new);
        // 方式二
//        Host1 host = new Host1(command -> new Thread(command).start());
        Host1 host = new Host1(executorService);
        try {
            host.request(10, 'A');
            host.request(20, 'B');
            host.request(30, 'C');
        } finally {
            executorService.shutdown();
            System.out.println("main END");
        }
    }
}
