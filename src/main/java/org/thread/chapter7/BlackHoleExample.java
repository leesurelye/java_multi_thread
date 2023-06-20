package org.thread.chapter7;

import org.thread.chapter7.obj.BlackHole;

public class BlackHoleExample
{
    public static void main(String[] args)
    {
        System.out.println("BEGIN");
        Object obj = new Object();
        BlackHole.enter(obj);
        System.out.println("END");
    }
}
