package org.thread.chapter8;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.thread.ClientThread;

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
        executorService.execute(new ClientThread("Alice"));
        executorService.execute(new ClientThread(""));
    }
}
