package org.thread.chapter5.thread;

import javafx.scene.control.Tab;
import org.thread.chapter5.obj.Table;

import java.util.Random;

public class MakerThread extends Thread
{
    private final Random random = new Random(1234L);

    private final Table table;

    // 蛋糕的Id
    private static int id = 0;

    public MakerThread(String threadName, Table table)
    {
        super(threadName);
        this.table = table;
    }

    @Override
    public void run()
    {
        // 不停地生产蛋糕
        try{
            while (true){
                String cake = "[ Cake No." + nextId() + " by " + getName() + "]";
                table.put(cake);
                Thread.sleep(1000L);
            }
        }catch (InterruptedException e) {}
    }

    private static synchronized int nextId()
    {
        return id ++;
    }
}
