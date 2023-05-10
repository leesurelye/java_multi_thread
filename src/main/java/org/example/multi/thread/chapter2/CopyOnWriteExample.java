package org.example.multi.thread.chapter2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 与Collections.synchronizedList不同，CopyOnWriterArrayList是采用copy-on-write技术来
 * 避免读写冲突的。
 * <br>
 * copy-on-write 就是写时复制，当对集合执行写操作，内部已经确保安全的数组就会被整体复制。<br>
 * 复制后，就不需要担心在读数据时担心元素被修改了。<br>
 * <br>
 * <br>
 * =============== [ Attention ] =========== <br>
 * 由于copy-on-write时，每次执行都会执行复制，因此，频繁地执行写操作，不建议使用copyOnWrite,
 * 如果写操作比较少，读操作比较频繁，使用copyOnWrite会提升程序的性能。
 */
public class CopyOnWriteExample
{
    public static void main(String[] args)
    {
        final List<Integer> list = new CopyOnWriteArrayList<>();
        new WriterThread(list).start();
        new ReadThread(list).start();
    }
}
