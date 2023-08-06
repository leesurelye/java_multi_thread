package org.thread.chapter11.case2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Thread Specific Log
 */
public class TSLog
{
    private PrintWriter writer = null;
    public TSLog(String filename) {
        try {
            this.writer = new PrintWriter(new FileWriter(filename));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // writer log
    public void println(String s) {
        this.writer.println(s);
    }
    // close log
    public void close()
    {
        System.out.println("===== End of log ======");
        this.writer.close();
    }
}
