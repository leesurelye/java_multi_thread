package org.thread.chapter3.obj;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingRequestQueue{
    // 使用重入锁，线程安全的队列
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();
    public Request getRequest(){
        Request request = null;
        try {
            request = queue.take();
        } catch (InterruptedException e){}
        return request;
    }

    public void putRequest(Request request){
        try{
            queue.put(request);
        }catch (InterruptedException e){}
    }
}
