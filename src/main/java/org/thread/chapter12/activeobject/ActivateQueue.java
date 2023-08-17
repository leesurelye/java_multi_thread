package org.thread.chapter12.activeobject;

public class ActivateQueue
{
    private static final int MAX_REQUEST = 100;
    private final MethodRequest[] requestQueue;

    private int tail;
    private int head;
    private int count;

    public ActivateQueue()
    {
        this.requestQueue = new MethodRequest[MAX_REQUEST];
        this.count = 0;
        this.head = 0;
        this.tail = 0;
    }

    public synchronized void putRequest(MethodRequest request){
        try {
            while (this.count >= MAX_REQUEST) {
                wait();
            }
        } catch (InterruptedException e){}
        this.requestQueue[this.tail] = request;
        this.tail = (this.tail + 1) % MAX_REQUEST;
        this.count ++;
        notifyAll();
    }
    public synchronized MethodRequest getRequest() {
        try {
            if (this.count <= 0){
                wait();
            }
        }catch (InterruptedException e){}
        MethodRequest result = this.requestQueue[this.head];
        this.head = (this.head + 1) % MAX_REQUEST;
        this.count --;
        notifyAll();
        return result;
    }
}
