package org.thread.chapter5.obj;

public class HeavyJob
{
    public static void execute(int count) throws InterruptedException{
        for(int i = 0; i < count; i++){
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            doHeavyJob();
        }
    }

    private static void doHeavyJob() {
        System.out.println("doHeavyJob() begin.");
        long start = System.currentTimeMillis();
        // 无法取消的处理
        while (start + 10000 > System.currentTimeMillis()) {
            // busy loop
        }
        System.out.println("doHeavyJob() end.");
    }
}
