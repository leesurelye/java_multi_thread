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

    public Channel(int threads)
    {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.threadPool = new WorkerThread[threads];
        for (int i = 0; i < threads; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWorks()
    {
        for (int i = 0; i < this.threadPool.length; i++) {
            this.threadPool[i].start();
        }
    }

    /**
     * 不可以使用Thread的stop方法，因为即使是加锁的线程也会被stop方法立即终止，无法确保安全性，详细见chapter 5.6
     */
    public void stopAllWorks()
    {
        for (WorkerThread workerThread : this.threadPool) {
            workerThread.stopThread();
        }
    }

    public synchronized void putReques(Request request) throws InterruptedException
    {
        while (this.count >= MAX_REQUEST) {
            wait();
        }
        this.requestQueue[this.tail] = request;
        this.tail = (this.tail + 1) % MAX_REQUEST;
        this.count++;
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException
    {
        while (this.count <= 0) {
            wait();
        }
        Request request = this.requestQueue[this.head];
        this.head = (this.head + 1) % MAX_REQUEST;
        this.count--;
        notifyAll();
        return request;
    }
}
