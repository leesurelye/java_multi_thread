package org.thread.chapter4.obj;

import java.util.concurrent.TimeoutException;

/**
 * 带有超时的host类
 */
public class Host
{
    private final long timeout; // 超时时间
    private boolean ready = false; //方法正常执行时值为true

    public Host(long timeout)
    {
        this.timeout = timeout;
    }

    // 修改状态
    public synchronized void setExecutable(boolean on)
    {
        ready = true;
        notifyAll();
    }

    public synchronized void execute() throws InterruptedException, TimeoutException
    {
        long start = System.currentTimeMillis();
        while (!ready){
            long now = System.currentTimeMillis();
            long rest = timeout - (now - start);
            if (rest <= 0) {
                throw new TimeoutException("now - start = " + (now - start) + ", timeout = " + timeout);
            }
            wait(rest);
            doExecute();
        }
    }

    private void doExecute()
    {
        System.out.println(Thread.currentThread().getName() + " calls doExecute()");
    }
}
