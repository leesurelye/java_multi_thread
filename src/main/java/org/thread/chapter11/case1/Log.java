package org.thread.chapter11.case1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log
{
    private static PrintWriter writer = null;

    // init data
    static {
        try {
            writer = new PrintWriter(new FileWriter("log.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // write log
    public static void println(String s){
        writer.println(s);
    }
    // close log
    public static void close()
    {
        writer.println("===== End of log ======");
        writer.close();
    }
}
