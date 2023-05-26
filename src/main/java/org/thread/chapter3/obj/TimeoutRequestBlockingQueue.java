package org.thread.chapter3.obj;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TimeoutRequestBlockingQueue
{
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Request getRequest()
    {
        Request req = null;
        try {
            req = queue.poll(10L, TimeUnit.SECONDS);
            if (req == null) {
                throw new RuntimeException("Timeout : " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
        }
        return req;
    }

    public void setRequest(Request request)
    {
        try {
            boolean offer = queue.offer(request, 10L, TimeUnit.SECONDS);
            if(!offer) {
                throw new RuntimeException("Timeout : " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
        }
    }
}
