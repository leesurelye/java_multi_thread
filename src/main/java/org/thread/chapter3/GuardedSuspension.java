package org.thread.chapter3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Request{
    private final int name;

    public Request(int name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 守护对象<br>
 *
 * 这个对象中有两种状态，即有元素和无元素
 * 线程需要根据对象的两种状态来决定执行还是等待
 * <br>
 * <br>
 * 守护对象中包被守护的方法(guardedMethod) 和改变状态的方法(stateChangingMethod).<br>
 * 1. 当线程执行guardedMethod时，需要判断守护条件，通过while和wait()来实现<br>
 * 2. stateChangingMethod则通过notify/notifyAll实现
 */
class RequestQueue{
    // Queue: FIFO
    private final Queue<Request> queue = new LinkedList<>();
    /**
     * 从队列中拿到请求，如果没有拿到请求独享，则等待；如果拿到了，则返回{@return Request}
     */
    public synchronized Request getRequest()
    {
        // 如果书写成 synchronized(queue)， 则会报错java.lang.IllegalMonitorStateException
        while(queue.peek() == null) {
            try{
                // 如果为空，则让执行该语句的当前线程进入该对象的等待队列
                wait();
            } catch (InterruptedException e){}
        }
        // 移除队首元素
        return this.queue.remove();
    }

    /**
     * 添加一个{@param request}到request list中
     */
    public synchronized void putRequest(Request request) {
        // 如果放了数据，就通知该对象等待队列中的所有线程，抢锁
        // offer 向队尾添加元素
        this.queue.offer(request);
        // 一旦守护条件成立，就需要通知等待线程
        notifyAll();
    }
}

class ClientThread extends Thread{
    private final RequestQueue queue;
    private final Random random = new Random(123);

    public ClientThread(RequestQueue queue)
    {
        super("Client");
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try{
            int i = 0;
            while(true) {
                // 每隔1秒发送一次请求
                Thread.sleep(random.nextInt(1000));
                Request request = new Request(i++);
                System.out.println(currentThread().getName() + " puts request : " + request);
                this.queue.putRequest(request);
            }
        }catch (InterruptedException e) {}
    }
}

class ServerThread extends Thread {
    private final RequestQueue queue;
    private final Random random = new Random(123);

    public ServerThread(RequestQueue queue)
    {
        super("Server");
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try{
            while (true) {
                //每隔2秒取一次请求
                Thread.sleep(random.nextInt(2000));
                Request request = this.queue.getRequest();
                System.out.println(currentThread().getName() + " gets request : " + request);
            }
        }catch (InterruptedException e) {}
    }
}
/**
 * 如果执行现在的操作会造成问题，就让执行的线程等待
 *<br>
 * 案例中参与的类：<br>
 * - request： 请求实体
 * - 守护对象：requestQueue: 存放请求的队列
 * - ClientThread: 向请求队列中放数据
 * - ServerThread: 向请求队列中拿数据，如果没有，则等待
 * Main
 */
public class GuardedSuspension
{
    public static void main(String[] args)
    {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue).start();
        new ServerThread(requestQueue).start();
    }
}
