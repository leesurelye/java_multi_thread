package org.thread.chapter9;

import org.thread.chapter9.obj.Data;
import org.thread.chapter9.obj.Host;

public class ExceptionExample
{
    public static void main(String[] args)
    {
        try {
            System.out.println("Main BEGIN");
            Host host = new Host();
            // an exception occure
            Data a = host.request(-1, 'A');
            System.out.println("data = " + a.getContent());
            System.out.println("Main END");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
