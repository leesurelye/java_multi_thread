package org.thread.chapter7;

import org.thread.chapter7.obj.ThreadFactoryHost;

import java.util.concurrent.Executors;

public class BaseExample
{
    public static void main(String[] args)
    {
        System.out.println("main BEGIN");
//        ThreadFactoryHost host = new ThreadFactoryHost(Thread::new);
        ThreadFactoryHost host = new ThreadFactoryHost(Executors.defaultThreadFactory());
        host.request1(10, 'A');
        host.request1(20, 'B');
        host.request1(30, 'C');
        System.out.println("main END");
    }
}
