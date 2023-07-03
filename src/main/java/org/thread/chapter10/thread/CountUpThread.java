package org.thread.chapter10.thread;

public class CountUpThread extends Thread
{
    private long counter = 0;
    /**
     * 用于标注是否发出了终止请求，请求后变为true
     */
    private volatile boolean shutdownRequested = false;

    /**
     * 发送终止请求， 使用interrupt的原因：
     * 若线程正在sleep, interrupt()可以中断正在sleep的线程
     * 若线程正在wait, interrupt()也可以对线程下达中断wait指令，
     * 否则就需要等线程sleep完成之后才能响应中断请求，不能立即响应中断请求
     */
    public synchronized void shutdownRequest()
    {
        shutdownRequested = true;
        interrupt();
    }

    public synchronized boolean isShutdownRequested()
    {
        return shutdownRequested;
    }

    /**
     * 在开发过程中，捕获了InterruptedException，使用isInterrupted()方法就可以检测中断，但是为什么还要设置
     * shutdownRequested标志？
     * 正常来说，这样做完成没有问题，但是只要在线程执行的一处忽略了InterruptedException，上面的方法就失效了
     *
     */
    @Override
    public void run()
    {
        try {
            while (!isShutdownRequested()) {
                doWork();
            }
        }catch (InterruptedException e) {
            // 忽略了InterruptedException
        } finally {
            doShutdown();
        }
    }

    private void doWork() throws InterruptedException
    {
        counter ++;
        System.out.println("doWork(): counter = " + counter);
        Thread.sleep(100);
    }

    /**
     * 终止处理
     */
    private void doShutdown()
    {
        System.out.println("doShutdown(), counter = " + counter);
    }
}
