package org.thread.chapter12.activeobject;

public class RealResult<T> implements Result<T>
{

    private final T result;

    public RealResult(T result)
    {
        this.result = result;
    }

    @Override
    public T getResultValue()
    {
        return this.result;
    }
}
