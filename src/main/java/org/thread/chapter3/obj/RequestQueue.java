package org.thread.chapter3.obj;

import org.thread.chapter3.obj.Request;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue{
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
