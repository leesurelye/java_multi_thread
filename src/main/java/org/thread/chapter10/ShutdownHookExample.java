package org.thread.chapter10;

public class ShutdownHookExample
{
    public static void main(String[] args)
    {
        System.out.println("Main: Begin");
        // 1. 设置未捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler()
                {
                    @Override
                    public void uncaughtException(Thread t, Throwable e)
                    {
                        System.out.println("******");
                        System.out.println("UncaughtExceptionHandler: BEGIN");
                        System.out.println("currentThread = " + Thread.currentThread());
                        System.out.println("thread = " + t);
                        System.out.println("exception = " + e);
                        System.out.println("UncaughtExceptionHandler: END");
                    }
                }
        );

        // 2. 设置退出钩子 Exit Hook
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    @Override
                    public void run()
                    {
                        System.out.println("*******");
                        System.out.println("ShutdownHook: BEGIN");
                        System.out.println("currentThread = " + currentThread());
                        System.out.println("ShutdownHook: END");
                    }
                }
        );

        // 3. 启动“整数除零计算”
        new Thread("MyThread") {
            @Override
            public void run()
            {
                System.out.println("MyThread: BEGIN");
                System.out.println("MyThread sleep...");
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){}
                System.out.println("MyThread: DIVIDE");
                // exception
                int x = 1 / 0;
                System.out.println("MyThread: END");
            }
        }.start();
        System.out.println("Main: END");
    }
}
