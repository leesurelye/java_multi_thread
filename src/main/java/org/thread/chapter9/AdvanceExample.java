package org.thread.chapter9;

import org.thread.chapter9.obj.AdvanceFutureDate;
import org.thread.chapter9.obj.AdvancedHost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AdvanceExample
{
    static DateFormat format = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args)
    {
        System.out.println("Main BEGIN " + format.format(System.currentTimeMillis()));
        AdvancedHost host = new AdvancedHost();
        AdvanceFutureDate a = host.request(10, 'A');
        AdvanceFutureDate b = host.request(10, 'B');
        AdvanceFutureDate c = host.request(10, 'C');
        try {
            System.out.println("data1=" + a.getContent());
            System.out.println("data2=" + b.getContent());
            System.out.println("data3=" + c.getContent());
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Main END " + format.format(System.currentTimeMillis()));
    }
}
