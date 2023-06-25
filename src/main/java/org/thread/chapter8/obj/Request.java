package org.thread.chapter8.obj;

import java.util.Random;

public class Request
{
    private final String name;

    private final String number;

    private static final Random random = new Random(1234L);

    public Request(String name, String number)
    {
        this.name = name;
        this.number = number;
    }

    public void execute()
    {
        System.out.println(Thread.currentThread().getName() + " execute() " + this);
        try{
            Thread.sleep(random.nextInt(1000));
        }catch (InterruptedException e){}
    }
    @Override
    public String toString()
    {
        return "Request{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
