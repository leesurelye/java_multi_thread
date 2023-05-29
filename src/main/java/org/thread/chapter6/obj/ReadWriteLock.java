package org.thread.chapter6.obj;

/**
 * 当线程想要获取读锁时：
 * - 如果有线程正在执行写入，则等待
 * - 如果有线程正在执行读取，则无需等待
 * <br>
 * 当线程想要获取写锁：
 * - 如果有线程正在写，则等待
 * - 如果有线程读， 则等待
 */
public final class ReadWriteLock
{
    private int readingReaders = 0; // 正在读取中的线程个数
    private int waitingWriters = 0; // 正在等待写入的线程个数
    private int writingWriters = 0; // 正在写入的线程个数
    private boolean preferWriter = true; // 若写入优先， 则为true

    // 加锁的时候需要同步
    public synchronized void readLock() throws InterruptedException {
        // 如果已经有写入的线程； 或者写入优先，且已经有等待写入的线程， 则读线程等待, r
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders ++;
    }

    public synchronized void readUnLock() {
        readingReaders --;
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters ++;
        try {
            // 没有读的，也没有写的
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters --;
        }
        writingWriters ++;
    }

    public synchronized void writeUnlock(){
        writingWriters -- ;
        preferWriter = false;
        notifyAll();
    }
}
