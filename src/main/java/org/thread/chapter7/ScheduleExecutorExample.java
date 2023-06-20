package org.thread.chapter7;

import org.thread.chapter7.obj.ScheduledHost;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduleExecutorExample
{
    public static void main(String[] args)
    {
        System.out.println("main BEGIN");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledHost host = new ScheduledHost(scheduledExecutorService);
        try {
            host.request(10, 'A');
            host.request(20, 'B');
            host.request(30, 'C');
        } finally {
            scheduledExecutorService.shutdown();
            System.out.println("main END");
        }
    }
}
