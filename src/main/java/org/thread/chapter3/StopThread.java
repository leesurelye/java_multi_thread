package org.thread.chapter3;


import org.thread.chapter3.obj.RequestQueue;
import org.thread.chapter3.thread.ClientThread;
import org.thread.chapter3.thread.ServerThread;

public class StopThread
{
    public static void main(String[] args)
    {
        RequestQueue requestQueue = new RequestQueue();
        Thread alice = new ClientThread(requestQueue, "Alice");
        Thread bob = new ServerThread(requestQueue, "Bob");
        alice.start();
        bob.start();

        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){}

        // call interrupt method
        System.out.println("******** calling interrupt ********");
        alice.interrupt();
        bob.interrupt();
    }
}
