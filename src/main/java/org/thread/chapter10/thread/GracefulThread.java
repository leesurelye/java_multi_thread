package org.thread.chapter10.thread;

public class GracefulThread extends Thread
{
    private volatile boolean shutdownRequested = false;
    public final void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }
    // not thread safe.
    public final boolean isShutdownRequested() {
        return shutdownRequested;
    }

    @Override
    public final void run()
    {
        try {
            while (isShutdownRequested()) {
                doWork();
            }
        } catch (InterruptedException e){}
        finally {
            doShutdown();
        }
    }

    protected void doWork() throws InterruptedException {
        // Not implement error
    }

    protected void doShutdown() {
        // Not implement error
    }
}
