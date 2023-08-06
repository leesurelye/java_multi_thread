package org.thread.chapter11.case2;

public class ClientThread extends Thread
{
    public ClientThread(String name){
        super(name);
        Log.println(name + " ClientThread is constructed");
    }

    @Override
    public void run()
    {
        System.out.println(getName() + " BEGIN");
        for (int i = 0; i< 10; i++) {
            Log.println(getName() + ": i = " + i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //  Log.close();
        //  不再需要Log.close()
        System.out.println(getName() + " END");
    }
}
