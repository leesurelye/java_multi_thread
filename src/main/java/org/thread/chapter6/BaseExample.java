package org.thread.chapter6;

import org.thread.chapter6.obj.Data;
import org.thread.chapter6.obj.ReadWriteData;
import org.thread.chapter6.thread.ReadThread;
import org.thread.chapter6.thread.WriteThread;

public class BaseExample
{
    public static void main(String[] args)
    {
        Data data = new ReadWriteData(10);

        new ReadThread("read-1", data).start();
        new ReadThread("read-2", data).start();
        new ReadThread("read-3", data).start();
        new ReadThread("read-4", data).start();

        new WriteThread("write-1",data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread("write-2",data, "abcdefghijklmnopqrstuvwxyz").start();
    }
}
