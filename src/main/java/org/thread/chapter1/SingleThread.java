package org.thread.chapter1;

/**
 * 共享资源的类，需要限制线程访问该类的互斥 synchronized
 */
class Gate {
    private int counter = 0;

    private String name;

    private String address;

    public synchronized void pass(String name, String address) {
        this.counter ++;
        this.name = name;
        this.address = address;
        check();
    }

    /**
     * Q:为什么这里不需要加 synchronized ？ <br>
     * A: 因为调用check的只有一个，而且该方法本身加了synchronized， 就代表调用check方法的
     * 时候只有一个线程调用，其他线程在pass方法入口处等待
     */
    private void check(){
        if (name.charAt(0) != address.charAt(0)){
            System.out.println("********** ERROR **********" + this);
        }
    }

    /**
     * Q: 为什么这里也需要加 synchronized？<br>
     * <br>
     * A: 当UserThread类线程正在执行pass方法时，其他线程 X 调用了toString()方法，在X线程引用了
     * name字段但是没有引用address字段期间， UserThread可能会修改address的值，这样以来，toString()
     * 方法对于线程X创建字符串时候使用的name和address就不一致。
     * <br>
     * <br>
     * 也就是说，该类声明为Public会被外部的其他线程访问，该方法虽然只是读数据，但是读数据的过程中，
     * 无法保证没有其他线程在写数据。因此会造成读写数据不一致的情况。
     */
    public synchronized String toString(){
        return "No. :" + counter + ":" + name + "," + address;
    }
}

class UserThread extends Thread{
    private final Gate gate;

    private final String myName;

    private final String myAddress;

    public UserThread(Gate gate, String myName, String myAddress)
    {
        this.gate = gate;
        this.myName = myName;
        this.myAddress = myAddress;
    }

    @Override
    public void run()
    {
        System.out.println(myName + "BEGIN");
        try {
            while (true) {  // 表示人不断地通过
                gate.pass(myName, myAddress);
                Thread.sleep(100);
            }
        }catch (InterruptedException e) {}
    }
}

/**
 * ============= Main =============== <br>
 * Chapter 1. Single Threaded Execution <br>
 * - 使用场景：当多个线程同时访问一个临界资源时，需要考虑使用该模式，确保临界资源的线程安全 <br>
 * 确保同一时间只能有一个线程执行<br>
 *
 * 此处这里会出现ERROR信息，因为某个线程执行check的时候，其他线程在不断地执行pass方法<br>
 * 修该了name和address的值。<br>
 *
 * 使用STE模式会造成死锁，程序就失去了生存性。 <br>
 * 在STE模式中，死锁的发生条件有三个，破坏任何一个都可以防止死锁： <br>
 * 1. 存在多个临界资源对象<br>
 * 2. 线程拿到其中一个临界资源的锁后，还去获取另一个临界资源的锁<br>
 * 3. 获取临界资源对象锁的顺序不是固定的。<br>
 */
public class SingleThread
{
    public static void main(String[] args)
    {
        Gate gate = new Gate();
        new UserThread(gate, "A", "Aaa").start();
        new UserThread(gate, "B", "Bbb").start();
        new UserThread(gate, "C", "Ccc").start();
    }
}
