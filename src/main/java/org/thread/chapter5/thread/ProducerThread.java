package org.thread.chapter5.thread;

import java.util.concurrent.Exchanger;

/**
 * - 填充字符，直至缓冲区被填满
 * - 使用exchanger方法后，将填满的缓冲区传递给ConsumerThread
 * - 传递缓冲区后，接收空的缓冲区
 */
public class ProducerThread extends Thread
{
    private final Exchanger<char []> exchanger;
    private char[] buffer;

    private char index = 0;

    public ProducerThread(String threadName,  Exchanger<char[]> exchanger, char[] buffer)
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
                // 填充交换区
                for(int i=0; i< buffer.length; i++){
                    buffer[i] = nextChar();
                    System.out.println(Thread.currentThread().getName() + " --> " + buffer[i]);
                }
                // 交换缓冲区
                System.out.println(Thread.currentThread().getName() + " : BEFORE change");
                // 交换缓冲区
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + " : AFTER change");
            }
        } catch (InterruptedException e) {}
    }

    private char nextChar() throws InterruptedException {
        char c = (char) ('A' + index % 26);
        index ++;
        Thread.sleep(1000);
        return c;
    }
}
