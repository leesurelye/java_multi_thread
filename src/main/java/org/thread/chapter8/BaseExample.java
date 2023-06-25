package org.thread.chapter8;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.thread.ClientThread;
import org.thread.chapter8.thread.WorkerThread;

/**
 * 代码清单：8-1 ～ 8-5
 */
public class BaseExample
{
    public static void main(String[] args)
    {
        Channel channel = new Channel(5); // 开启五个线程
        channel.startWorks(); //启动线程
        new ClientThread("Alice", channel).start();
        new ClientThread("Bob", channel).start();
        new ClientThread("Chris", channel).start();
    }
}
