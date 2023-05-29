package org.thread.chapter6.obj;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteData extends Data
{
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public ReadWriteData(int size)
    {
        super(size);
    }

    @Override
    public char[] read() {
        readLock.lock();
        try{
            return super.doRead();
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public void write(char c){
        writeLock.lock();
        try{
            super.doWrite(c);
        }finally {
            writeLock.unlock();
        }
    }
}
