package org.thread.chapter1;

import org.common.obj.Tool;

import java.util.Random;

class EatThread extends Thread {
    private final String name;

    private final Tool leftHand;

    private final Tool rightHand;

    private final Random random = new Random(123);

    public EatThread(String name, Tool leftHand, Tool rightHand)
    {
        super(name);
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    private void eat() throws InterruptedException{
        synchronized (leftHand) {
            System.out.println(name + " takes up " + leftHand + " (left) ");
            synchronized (rightHand) {
                System.out.println(name + " takes up " + rightHand + " (right) ");
                Thread.sleep(random.nextInt(5000));
                System.out.println(name + " puts down " + rightHand + " (right) ");
            }
            System.out.println(name + " puts down " + rightHand + " (left) ");
        }
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                eat();
            }
        }catch (Exception e) {}
    }
}

/**
 * Example for Fork and Spoon, classic
 */
public class ForkAndSpoonExample
{
    public static void main(String[] args)
    {
        Tool fork = new Tool("Fork");
        Tool spoon = new Tool("Spoon");
        // 造成死锁
        new EatThread("Alice", fork, spoon).start();
        new EatThread("Bob", fork, spoon).start();
    }
}
