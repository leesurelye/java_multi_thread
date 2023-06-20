package org.thread.chapter7.obj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper
{
    private final DateFormat dateFormat = new SimpleDateFormat("mm:ss");
    public void handle(int count, char c)
    {
        System.out.println("\t handle (" + count + " , " + c + " ) BEGIN. " + dateFormat.format(new Date(System.currentTimeMillis())));
        for(int i = 0; i < count ; i++) {
            slowly();
            System.out.print(c);
        }
        System.out.println();
        System.out.println("\t handle (" + count + " , " + c + " ) END. " + dateFormat.format(new Date(System.currentTimeMillis())));
    }
    private void slowly()
    {
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){}
    }
}
