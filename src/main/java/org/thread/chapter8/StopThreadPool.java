package org.thread.chapter8;

import org.thread.chapter8.obj.Channel;
import org.thread.chapter8.thread.ClientThread;

/**
 * 程序运行5秒后停止
 */
public class StopThreadPool
{
    public static void main(String[] args)
    {
        Channel channel = new Channel(5);
        channel.startWorks();
        ClientThread alice = new ClientThread("Alice", channel);
        ClientThread bob = new ClientThread("Bob", channel);
        ClientThread chris = new ClientThread("Chris", channel);
        alice.start();
        bob.start();
        chris.start();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){}
        alice.stopThread();
        bob.stopThread();
        chris.stopThread();
        // 终止所有工人线程
        channel.stopAllWorks();
    }
}
