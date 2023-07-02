package org.thread.chapter9.obj;


import java.util.concurrent.ExecutionException;

public class FutureData implements Data
{
    private ExecutionException execution = null;
    private RealData realData = null;
    private boolean ready = false;

    // 使用来Guarded Suspension 模式。
    public synchronized void setRealData(RealData realData) {
        if (ready) {
            return; // balk
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }
    public synchronized void setExecution(Throwable throwable)
    {
        if (ready) {
            return;
        }
        this.execution = new ExecutionException(throwable);
        this.ready = true;
        notifyAll();
    }

    // 使用来Guarded Suspension 模式。
    @Override
    public synchronized String getContent() throws ExecutionException{
        while (!ready) {
            try {
                wait();
            }catch (InterruptedException e) {}
        }
        if (execution != null) {
            throw execution;
        }
        return this.realData.getContent();
    }
}
