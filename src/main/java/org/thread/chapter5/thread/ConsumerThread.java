package org.thread.chapter5.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * 使用exchanger将空的缓冲区传递给ProducerThread.
 * 接收被填满的缓冲区
 * 使用缓冲区中的字符
 */
public class ConsumerThread extends Thread
{
    private final Exchanger<char []> exchanger;

    private char[] buffer;

    public ConsumerThread(String threadName, Exchanger<char[]> exchanger, char[] buffer)
    {
        super(threadName);
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " : BEFORE change");
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + " : AFTER change");
                for(int i = 0; i < buffer.length; i++){
                    System.out.println(getName() + " <-- " + buffer[i]);
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
