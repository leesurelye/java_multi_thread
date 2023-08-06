package org.thread.chapter11.case1;

public class BaseExample
{
    public static void main(String[] args)
    {
        System.out.println("BEGIN");
        for (int i = 0; i < 10; i++) {
            Log.println("main: i = " + i);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {e.printStackTrace();}
        }
        Log.close();
        System.out.println("END");
    }
}
