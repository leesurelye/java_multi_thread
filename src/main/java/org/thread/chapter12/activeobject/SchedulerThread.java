package org.thread.chapter12.activeobject;

public class SchedulerThread extends Thread
{
    private final ActivateQueue queue;

    public SchedulerThread(ActivateQueue queue)
    {
        this.queue = queue;
    }

    public void invoke(MethodRequest request){
        this.queue.putRequest(request);
    }
    @Override
    public void run()
    {
        while (true){
            MethodRequest request = this.queue.getRequest();
            request.execute();
        }
    }
}
