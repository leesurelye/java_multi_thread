package org.thread.chapter5;

import org.thread.chapter5.thread.ConsumerThread;
import org.thread.chapter5.thread.ProducerThread;

import java.util.concurrent.Exchanger;

public class ExchangerExample
{
    public static void main(String[] args)
    {
        Exchanger<char []> exchanger = new Exchanger<>();
        char[] buffer1 = new char[10];
        char[] buffer2 = new char[10];
        new ProducerThread("Producer", exchanger, buffer1).start();
        new ConsumerThread("Consumer", exchanger, buffer2).start();
    }
}
