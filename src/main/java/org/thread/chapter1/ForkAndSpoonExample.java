package org.thread.chapter1;

import java.util.Random;

/**
 * 临界资源
 */
class Tool {
    private final String name;

    public Tool(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "[" + name +"]";
    }
}

class EatThread extends Thread {
    private final String name;

    private final Tool leftHand;

    private final Tool rightHand;

    private final Random random = new Random(123);

    public EatThread(String name, Tool leftHand, Tool rightHand)
    {
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
        new EatThread("Alice", fork, spoon).start();
        new EatThread("Bob", fork, spoon).start();
    }
}
