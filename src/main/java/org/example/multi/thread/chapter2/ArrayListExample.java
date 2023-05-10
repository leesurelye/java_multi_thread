package org.example.multi.thread.chapter2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 写线程，会不断地想List中添加数据，然后移除首元素
 */
class WriterThread extends Thread
{
    private final List<Integer> list;

    public WriterThread(List<Integer> list)
    {
        super("WriterThread");
        this.list = list;
    }

    @Override
    public void run()
    {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
        }
    }
}

class ReadThread extends Thread {
    private final List<Integer> list;

    public ReadThread(List<Integer> list)
    {
        super("ReadThread");
        this.list = list;
    }

    @Override
    public void run()
    {
        while(true){
            for(int n : list) {
                System.out.println(n);
            }
        }
    }
}
/**
 * ArrayList 是线程不安全的
 * <br>
 * 多个线程同时读写会造成数据不一致的情况,程序抛出java.util.ConcurrentModificationException异常
 */
public class ArrayListExample
{
    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<>();
        new WriterThread(list).start();
        new ReadThread(list).start();
    }
}
