package org.thread.chapter6.obj;

import java.util.Random;

public class Data
{
    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    private final Random random = new Random(1234L);
    public Data(int size)
    {
        this.buffer = new char[size];
    }

    public char[] read() throws InterruptedException {
        lock.readLock();
        try{
            return doRead();
        }finally {
            lock.readUnLock();
        }
    }
    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    protected char[] doRead() {
        char[] res = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            res[i] = buffer[i];
        }
        slowly();
        return res;
    }

    protected void doWrite(char c) {
        for(int i = 0; i< buffer.length; i++){
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(random.nextInt(100));
        }catch (InterruptedException e) {}
    }
}
