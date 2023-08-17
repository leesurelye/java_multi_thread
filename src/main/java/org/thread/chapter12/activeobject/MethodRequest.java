package org.thread.chapter12.activeobject;

public abstract class MethodRequest<T>
{
    protected final Servant servant;
    protected final FutureResult<T> futureResult;

    public MethodRequest(Servant servant, FutureResult<T> futureResult)
    {
        this.servant = servant;
        this.futureResult = futureResult;
    }
    public abstract void execute();
}
