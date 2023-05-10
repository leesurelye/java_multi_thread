package org.example.service;

class MyRunnable implements Runnable{
    private String message;
    public MyRunnable(String message){
        this.message = message;
    }

    @Override
    public void run()
    {
        try{
            for(int i=0;i<1000; i++){
                System.out.println(message + i);
                // 使用interrupt() 唤醒sleep的线程
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupt by user");
        }
    }
}

public class MyRunnableExample
{
    public static void main(String[] args)
    {
        new Thread(new MyRunnable("Thread_1:")).start();
        new Thread(new MyRunnable("Thread_2:")).start();
    }
}
