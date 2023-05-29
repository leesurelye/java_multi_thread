package org.thread.chapter5;

import org.thread.chapter5.obj.Table;
import org.thread.chapter5.thread.EaterThread;
import org.thread.chapter5.thread.MakerThread;

public class BaseExample
{
    public static void main(String[] args)
    {
        Table table = new Table(3);
        MakerThread makerThread1 = new MakerThread("Maker-1", table);
        MakerThread makerThread2 = new MakerThread("Maker-2", table);
        MakerThread makerThread3 = new MakerThread("Maker-3", table);
        EaterThread eaterThread1 = new EaterThread("Eater-1", table);
        EaterThread eaterThread2 = new EaterThread("Eater-2", table);
        EaterThread eaterThread3 = new EaterThread("Eater-3", table);

        makerThread1.start();
        makerThread2.start();
        makerThread3.start();
        eaterThread1.start();
        eaterThread2.start();
        eaterThread3.start();

        // 运行约10秒后，停止运行
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}

        System.out.println("**** calling interrupt *****");
        eaterThread1.interrupt();
        eaterThread2.interrupt();
        eaterThread3.interrupt();
        makerThread1.interrupt();
        makerThread2.interrupt();
        makerThread3.interrupt();
        // 只要有一个线程存活，则Main线程存活
    }
}
