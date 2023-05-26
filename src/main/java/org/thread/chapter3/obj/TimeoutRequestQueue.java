package org.thread.chapter3.obj;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 带有超时的请求队列，当发生死锁时候，可以自动放弃
 */
public class TimeoutRequestQueue
{
    private final long timeout;
    private final Queue<Request> queue = new LinkedList<>();

    public TimeoutRequestQueue(long timeout)
    {
        this.timeout = timeout;
    }

    public synchronized Request getRequest()
    {
        long start = System.currentTimeMillis();
        while (queue.peek()==null){
            long now = System.currentTimeMillis();
            long reset = timeout + start - now;
            if (reset <= 0){
                throw new RuntimeException("Timeout :" + Thread.currentThread().getName());
            }
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request)
    {
        queue.offer(request);
        notifyAll();
    }
}
