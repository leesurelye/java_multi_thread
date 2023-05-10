package org.example.service;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory
{
    public static void main(String[] args)
    {
        ThreadFactory factory = Executors.defaultThreadFactory();
        factory.newThread(new MyRunnable("thread_factory_1:")).start();
        factory.newThread(new MyRunnable("thread_factory_2:")).start();
    }
}
