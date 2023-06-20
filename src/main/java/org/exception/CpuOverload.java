package org.exception;

public class CpuOverload
{
    public static void main(String[] args)
    {
        // 启动十条不活跃的线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(60 * 10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "InActivate_Thread_" + i).start();
        }
        // 启动一个不断循环的线程
        new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
            }
        }, "Activate_Thread").start();
    }
}
