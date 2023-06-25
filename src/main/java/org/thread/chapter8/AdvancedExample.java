package org.thread.chapter8;

import org.thread.chapter8.thread.AdvancedClientThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 代码清单 8-6 ~8-8
 */
public class AdvancedExample
{
    public static void main(String[] args)
    {
//        Channel channel = new Channel(5); // 开启五个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        channel.startWorks(); //启动线程
//        new ClientThread("Alice", channel).start();
//        new ClientThread("Bob", channel).start();
//        new ClientThread("Chris", channel).start();
        try {
            new AdvancedClientThread("Alice", executorService).start();
            new AdvancedClientThread("Bob", executorService).start();
            new AdvancedClientThread("Chris", executorService).start();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        } finally {
            executorService.shutdown();
        }
    }
}
