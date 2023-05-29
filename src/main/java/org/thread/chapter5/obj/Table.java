package org.thread.chapter5.obj;

public class Table
{
    // 循环队列
    private final String[] buffer;

    private int tail;

    private int head;

    private int count;

    public Table(int count)
    {
        this.buffer = new String[count];
    }

    // throws InterruptedException 表示该方法可以取消
    public synchronized void put(String cake) throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " puts :" + cake);
        while (count >= buffer.length){
            System.out.println(Thread.currentThread().getName() + " wait begin.");
            wait();
            System.out.println(Thread.currentThread().getName() + " wait end.");
        }
        buffer[tail] = cake;
        tail = (tail + 1) % this.buffer.length;
        this.count ++ ;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException{
        while (this.count <= 0) {
            System.out.println(Thread.currentThread().getName() + " wait begin");
            wait();
            System.out.println(Thread.currentThread().getName() + " wait end");
        }
        String cake = buffer[head];
        head = (head + 1) % this.buffer.length;
        this.count -- ;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes :" + cake);
        return cake;
    }

    public synchronized void clear() throws InterruptedException {
        head = 0;
        tail = 0;
        count = 0;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " clear table.");
    }
}
