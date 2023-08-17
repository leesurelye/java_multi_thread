package org.thread.chapter12.activeobject;

class Proxy implements ActiveObject
{
    private final SchedulerThread schedulerThread;
    private final Servant servant;

    public Proxy(SchedulerThread schedulerThread, Servant servant)
    {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result<String> makeString(int count, char fillChar)
    {
        FutureResult<String> futureResult = new FutureResult<>();
        schedulerThread.invoke(new MakeStringRequest(servant, futureResult, count, fillChar));
        return futureResult;
    }

    @Override
    public void displayString(String str)
    {
        schedulerThread.invoke(new DisplayStringRequest(servant, str));
    }
}
