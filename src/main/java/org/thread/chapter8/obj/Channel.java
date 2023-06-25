package org.thread.chapter8.obj;

import org.thread.chapter8.thread.WorkerThread;

public class Channel
{
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int tail;
    private int head;
    private int count;
    private final WorkerThread[] threadPool;
    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.threadPool = new WorkerThread[threads];
        for(int i = 0; i< threads; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }
    public void startWorks()
    {
        for(int i = 0; i < this.threadPool.length; i++){
            this.threadPool[i].start();
        }
    }
    public synchronized void putReques(Request request) {
        if (this.count >= MAX_REQUEST) {
            try{
                wait();
            }catch (InterruptedException e){}
        }
        this.requestQueue[this.tail] = request;
        this.tail = (this.tail + 1) % MAX_REQUEST;
        this.count ++;
        notifyAll();
    }
    public synchronized Request getRequest() {
        while (this.count <= 0){
            try{
                wait();
            }catch (InterruptedException e){}
        }
        Request request = this.requestQueue[this.head];
        this.head = (this.head + 1) % MAX_REQUEST;
        this.count --;
        notifyAll();
        return request;
    }
}
