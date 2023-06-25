package org.thread.chapter8.thread;

import org.thread.chapter8.obj.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public class AdvancedClientThread extends Thread
{
    private final ExecutorService executorService;

    public AdvancedClientThread(String name, ExecutorService executorService)
    {
        super(name);
        this.executorService = executorService;
    }

    @Override
    public void run(){
        try {
            int i = 0;
            while (true) {
                Request request = new Request(getName(), String.valueOf(i));
                this.executorService.execute(request);
                Thread.sleep(1000);
                i ++;
            }
        }catch (InterruptedException e) {
        }catch (RejectedExecutionException e){
            System.out.println(getName() + " : " + e);
        }
    }
}
