package org.thread.chapter4;

import org.thread.chapter4.obj.Host;
import java.util.concurrent.TimeoutException;

public class TimeoutExample
{
    public static void main(String[] args)
    {
        Host host = new Host(1000);
        try {
            System.out.println("execute BEGIN");
            host.execute();
        } catch (TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
