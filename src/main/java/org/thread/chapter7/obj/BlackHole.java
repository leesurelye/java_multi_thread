package org.thread.chapter7.obj;

public class BlackHole
{
    public static void enter(Object obj)
    {
        System.out.println("step 1");
        magic(obj);
        System.out.println("step 2");
        synchronized (obj) {
            System.out.println("step 3 (never reached here)");
        }
    }
    public static void magic(Object obj)
    {
        synchronized (obj){

        }
    }
}
