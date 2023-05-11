package org.thread.chapter2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ReadSyncThread extends Thread {
    private final List<Integer> list;

    public ReadSyncThread(List<Integer> list)
    {
        super("ReadSyncThread");
        this.list = list;
    }

    @Override
    public void run()
    {
        while (true) {
            synchronized (list){ // 拿到list的锁
                for(int i : list){
                    System.out.println(i);
                }
            }
        }
    }
}

/**
 * 线程安全的List， 读写线程同时对一个list改写不会出现问题
 */
public class SynchronizedListExample
{
    public static void main(String[] args)
    {
        final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        // 写线程是显示调用add方法和remove方法，可以复用ArrayListExample中的写线程
        new WriterThread(list).start();
        // 读线程是隐式调用迭代器，需要同步
        // new ReadThread(list).start(); 调用这个Thread同样会抛出读时修改异常
        new ReadSyncThread(list).start();
    }
}
