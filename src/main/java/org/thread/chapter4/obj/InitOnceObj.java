package org.thread.chapter4.obj;

public class InitOnceObj
{
    private boolean initialized = false;
    public synchronized void init() {
        if (initialized) return;
        doInit();
        // 状态仅变化一次的量，通常成为闭锁(latch), 这个变量一旦初始化量，就再也不会发生变化。
        initialized = true;
    }

    private void doInit() {
        // do someInit
        System.out.println(this.getClass().getName() + " initialized.");
    }
}
