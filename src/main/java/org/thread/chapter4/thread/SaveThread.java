package org.thread.chapter4.thread;

import org.thread.chapter4.obj.Data;

import java.io.IOException;

/**
 * 定期保存数据内容的线程
 */
public class SaveThread extends Thread
{
    private final Data data;

    public SaveThread(Data data, String name)
    {
        super(name);
        this.data = data;
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                // 每隔一段时间，就保存数据
                Thread.sleep(1000);
                data.save();
            }
        } catch (IOException | InterruptedException e) {
        }
    }
}
