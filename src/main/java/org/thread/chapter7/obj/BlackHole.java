package org.thread.chapter7.obj;

public class BlackHole
{
    public static void enter(Object obj)
    {
        System.out.println("step 1");
        magic(obj);
        System.out.println("step 2");
        // 拿不到obj锁
        synchronized (obj) {
            System.out.println("step 3 (never reached here)");
        }
    }
    public static void magic(final Object obj)
    {
        // 获取obj的锁，但是如果使用synchronized，当跳出synchronized代码块的时候，会释放锁
        //启动一个线程，一直拿着这个obj的锁
        // 没有获取锁之前，原来的线程不可以从magic方法中返回
        Thread thread = new Thread(){
            @Override
            public void run(){
                synchronized (obj){
                    synchronized (this){
                        this.setName("Locked");
                        this.notifyAll(); // 通知拿到了obj的锁
                    }
                    while (true) {

                    }
                }
            }
        };
        synchronized (thread) {
            thread.setName("");
            thread.start();
            while (thread.getName().equals("")) {
                try {
                    thread.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
