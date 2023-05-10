package org.example.service;


class MyThread extends Thread
{
    private String message;

    public MyThread(String message){
        this.message = message;
    }

    @Override
    public void run()
    {
        try {
            for (int i = 0; i < 1000; i++) {
                System.out.println(message + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupt by user!");
        }
    }
}


public class MyThreadExample
{
    public static void main(String[] args)
    {
       new MyThread("Thread_1:").start();
       new MyThread("Thread_2:").start();
    }
}
