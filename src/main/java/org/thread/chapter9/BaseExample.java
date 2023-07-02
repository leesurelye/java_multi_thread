package org.thread.chapter9;

import org.thread.chapter9.obj.Data;
import org.thread.chapter9.obj.Host;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BaseExample
{
    static DateFormat format = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args)
    {
        System.out.println("Main BEGIN " + format.format(System.currentTimeMillis()));
        Host host = new Host();
        Data data1 = host.request(10, 'A');
        Data data2 = host.request(10, 'B');
        Data data3 = host.request(10, 'C');
        System.out.println("Main other job BEGIN" + format.format(System.currentTimeMillis()));
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {}
        System.out.println("Main other job END" + format.format(System.currentTimeMillis()));

        try {
            System.out.println("data1=" + data1.getContent() + format.format(System.currentTimeMillis()));
            System.out.println("data2=" + data2.getContent() + format.format(System.currentTimeMillis()));
            System.out.println("data3=" + data3.getContent() + format.format(System.currentTimeMillis()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Main END " + format.format(System.currentTimeMillis()) );
    }
}
