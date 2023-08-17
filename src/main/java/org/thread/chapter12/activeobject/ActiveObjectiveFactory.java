package org.thread.chapter12.activeobject;

public class ActiveObjectiveFactory
{
    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivateQueue queue = new ActivateQueue();
        // this thread execute getRequest from queue
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        // this proxy
        Proxy proxy = new Proxy(schedulerThread, servant);
        schedulerThread.start();
        return proxy;
    }
}
