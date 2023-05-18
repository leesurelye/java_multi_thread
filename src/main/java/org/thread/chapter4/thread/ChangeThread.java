package org.thread.chapter4.thread;

import org.thread.chapter4.obj.Data;

import java.io.IOException;
import java.util.Random;

/**
 * 修改并保存数据的线程
 */
public class ChangeThread extends Thread
{
    private final Data data;

    public ChangeThread(Data data, String name)
    {
        super(name);
        this.data = data;
    }

    @Override
    public void run()
    {
        try {
            int i = 0;
            // 不停地修改数据
            while (true) {
                data.change(i++ + " ");
                // 每一次修改完数据后，就休息 1s
                Thread.sleep(1000);
                // 显示保存
                data.save();
            }
        } catch (InterruptedException | IOException e) {
        }
    }
}
